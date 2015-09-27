#lang racket

(require (only-in net/sendurl send-url/file))

(provide 
 ;; String -> Nothing 
 ;; send the specified file name as a URL to default browser 
 (rename-out [send-url/file show])

 ;; --------------------------------------------------------------------------------------------------
 ;; data definition 
 
 ;   Xexpr.v3 is one of: 
 ;   -- Symbol 
 ;   -- String 
 ;   -- Number 
 ;   -- (cons Symbol (cons [List-of Attribute] [List-of Xexpr.v3]))
 ;   -- (cons Symbol [List-of Xexpr.v3])
 ;
 ;   Attribute is:
 ;      (list Symbol String)
 ;   (list 'a "some text") is called an a-Attribute and "some text" is a's value.
 
 ;; ---------------------------------------------------------------------------------------------------
 ;; function names and purpose statements 

 ;; Any -> Boolean 
 ;; is the given value an Xexpr.v3? 
 ;; effect: display bad piece if x is not an Xexpr.v3
 xexpr?
 
 ;; String -> Xexpr.v3
 ;; given a file name, read an Xexpr.v3 from a file that contains at least one XML element
 
 ;; the XML element does not contain any strings as elements other than whitespace, 
 ;; and all whitespace between embedded elements is eliminated 
 read-plain-xexpr
 
 ;; String -> Xexpr.v3
 ;; given a file name, read an Xexpr.v3 from a file that contains at least one XML element 
 read-xexpr
 
 ;; String -> Boolean 
 ;; false, if this url returns a '404'; true otherwise
 url-exists? 
 
 ;; String -> [Maybe Xexpr.v3]
 ;; given a URL, find web site and retrieve the first XML element, false if the web page isn't found
 ;; read HTML as XML (if possible) 
 ;; effect: signals an error in case of network problems 
 read-xexpr/web
 
 ;; String -> [Maybe Xexpr.v3]
 ;; given a URL, find web site and retrieve the first XML element, false if the web page isn't found 
 ;; read HTML as XML (if possible) 
 ;; effect: signals an error in case of network problems 
 
 ;; the XML element does not contain any strings as elements other than whitespace, 
 ;; and all whitespace between embedded elements is eliminated 
 read-plain-xexpr/web
 
 ;; String -> [Listof String]
 ;; produce the list of (fully resolve) .html references in a elements from url
 url-html-neighbors 
 
 ;; Xexpr.v3 -> String
 ;; turn the X-expression into a string 
 xexpr-as-string)

;; ---------------------------------------------------------------------------------------------------
;; implementation 

;; ---------------------------------------------------------------------------------------------------
;; ---------------------------------------------------------------------------------------------------
;; exported functions 

(require htdp/error
         (for-syntax racket/base syntax/parse)
         ;; --- xml/html 
         net/url
         (except-in xml/xml xexpr?)
         html
         srfi/13)

(module+ test
  (require rackunit))

(define-syntax-rule
  (def-reader (name f s ...) body ...)
  (define (name f s ...)
    (check-input-file f 'name)
    (let ()
      body ...)))

;; ---------------------------------------------------------------------------------------------------
(define (xexpr? x0)
  (define tag (gensym))
  
  ;; Any -> Boolean 
  (define (xexpr-aux? x)
    (cond
      [(string? x) #t]
      [(symbol? x) #t]
      [(number? x) #t]
      [(and (cons? x) (symbol? (first x)))
       (define body (rest x))
       (or (and (list-of-attributes? (first body)) (list-of-xexpr? (rest body))) 
           (list-of-xexpr? body))]
      [else (raise (cons tag x))]))
  
  ;; Any -> Boolean 
  (define (list-of-attributes? xs)
    (and (list? xs)
         (for/and ((x xs))
           (and (list? x) (= (length x) 2) (symbol? (first x)) (string? (second x))))))
  
  ;; Any -> Boolean 
  (define (list-of-xexpr? xs)
    (and (or (list? xs) (raise (cons tag xs)))
         (andmap xexpr-aux? xs)))
  
  ;; -- IN -- 
  (with-handlers (((lambda (x) (and (cons? x) (eq? (car x) tag)))
                   (lambda (x) 
                     (pretty-print `(,(cdr x) occurs in ,x0))
                     #f)))
    (xexpr-aux? x0)))

(define (url-html-neighbors u)
  (define x (read-xexpr/web u))
  (if x (url-html-neighbors-aux u x) '()))

(define (url-exists? url:string)
  (retrieve url:string (lambda (_ h) (not (404? h)))))

(def-reader (read-plain-xexpr f)
  (read-xexpr-aux 'read-plain-xexpr f fix-up))

(def-reader (read-xexpr f)
  (read-xexpr-aux 'read-xexpr f values))

(define (read-xexpr/web url:string)
  (read-xexpr/web-aux 'read-xexpr/web url:string values))

(define (read-plain-xexpr/web url:string)
  (read-xexpr/web-aux 'read-xexpr/web url:string fix-up))

(define (xexpr-as-string x) 
  (check-arg 'xexpr->string (and (pair? x) (xexpr? x)) 'xexpr "first" x)
  (string-trim (call-with-output-string (curry display-xml/content (xexpr->xml x)))))

;; -----------------------------------------------------------------------------
;; auxiliaries 

;; String [-> X] -> X
(define (read-from-file-or-device f rd)
  (define device #f)
  (if device 
      (parameterize ((current-input-port [(cadr device)])) (rd))
      (with-input-from-file f #:mode 'text rd)))

;; effect: ensure that f is a file in current directory or report error for t
(define (check-input-file f t)
  (define d? #f)
  (check-arg t (or (string? f) d?) (error-message '()) "first" f)
  (check-arg t (or d? (file-exists? f)) "name of file in program's folder" "first" f))

;; [Listof Symbol] -> String 
(define (error-message los)
  (string-append "string or one of: " (string-join (map ~e los) ", ")))

;; ---------------------------------------------------------------------------------------------------
;; Symbol String [XML -> XML] -> Xexpr
;; read an Xexpr from a file that contains one XML element 
(define (read-xexpr-aux tag f fix-up)
  (define raw (read-from-file-or-device f read-html-as-xml #;read-xml/element))
  (check-result 'read-plain-xexpr xexpr? 'xexpr (xml->xexpr (fix-up (first raw)))))

;; Symbol String [XML -> XML] -> Xexpr
(define (read-xexpr/web-aux tag url:string fix-up)
  (retrieve 
   url:string
   (lambda (url h)
     (cond
       [(404? h) #f]
       [else 
        (define e (filter element? (call/input-url url get-impure-port read-html-as-xml)))
        (and (cons? e) (check-result tag xexpr? 'xexpr (xml->xexpr (fix-up (first e)))))]))))

(define fix-up (eliminate-whitespace '() (lambda (x) #t)))

;; ---------------------------------------------------------------------------------------------------
;; String [URL String -> X] -> X 
;; retrieve the first text line from the url represented by url:string, hand url and line to consumer
(define (retrieve url:string consumer)
  (define URL (string->url url:string))
  (with-handlers ([exn:fail:network? 
                   (lambda (e) 
                     (define msg (format "working url, details:\n ~a" (exn-message e)))
                     (check-arg 'read-xexpr/web #f msg "" url:string))])
    (consumer URL (call/input-url URL get-impure-port read-line))))

;; ---------------------------------------------------------------------------------------------------
;; String -> Boolean 
;; does the string contain "404"
(define (404? s) (pair? (regexp-match "404" s)))

;; ---------------------------------------------------------------------------------------------------
;; String Xexpr -> [Listof String]
;; retrieve the domain-local neighbors of url that point to HTML files 

(module+ test 
  (check-equal? (url-html-neighbors-aux
                 "http://fun.com/"
                 '(div 
                   (a "hello")
                   (a ((href " one.html")) "world")
                   (a ((href "Papers/ignore.pdf")) "world")
                   (img ((alt "two")))
                   (img ((alt "three") (src "ignore.jpg")))))
                '("http://fun.com/one.html")))

(define (url-html-neighbors-aux u xexpr)
  (define url (string->url u))
  (for/fold ((result '())) ((e (xexpr-elements xexpr 'a)))
    (cond
      [(and (cons? (rest e)) (loa? (second e)))
       (define html-targets
         (for/fold ((htmls '())) ((attributes-of-a-element (second e)))
           (cond
             [(symbol=? (first attributes-of-a-element) 'href)
              (define value:str (string-trim-both (second attributes-of-a-element)))
              (define value:url (combine-url/relative url value:str))
              (if (not (url-ends-in-html? value:url)) 
                  htmls 
                  (cons (url->string value:url) htmls))]
             [else htmls])))
       (append html-targets result)]
      [else result])))

;; ---------------------------------------------------------------------------------------------------
;; URL -> Boolean 
;; does the url end in html? 

(module+ test
  (check-true (url-ends-in-html? (string->url "seconds.html")))
  (check-false (url-ends-in-html? (string->url "seconds.pdf"))))

(define (url-ends-in-html? u)
  (define q (reverse (map path/param-path (url-path u))))
  (and (cons? q) (pair? (regexp-match ".html$" (first q)))))

;; ---------------------------------------------------------------------------------------------------
;; Xexpr Symbol -> [Listof Xexpr]
;; retrieve all elements whose tag is 'tag'

(module+ test 
  (check-equal? (xexpr-elements '(p () (p ((align "center")) "hello") (a)) 'p) 
                (list '(p () (p ((align "center")) "hello") (a))
                      '(p ((align "center")) "hello"))))

(define (xexpr-elements x0 tag)
  (define (K- fst rst) rst)
  (xexpr-abs x0 
             '() 
             K-
             K- 
             append 
             (lambda (e loa rst) (if (symbol=? (first e) tag) (cons e rst) rst))
             (lambda (e rst) (if (symbol=? (first e) tag) (cons e rst) rst))))

;; ---------------------------------------------------------------------------------------------------
;; Xexpr
;; [Attribute -> X]
;; Y 
;; [String Y -> Y] 
;; [Xexpr Y -> Y]
;; [Xexpr [Listof X] Z -> W] 
;; [Symbol Z -> W] 
;; -> 
;; W

;; traverse X-expression and combine results 
(define (xexpr-abs x0 body0 attr-combine string-combine xexpr-combine loa-combine plain-combine)
  ;; Xexpr -> W
  (define (f-xexpr x)
    (cond
      [(and (cons? (rest x)) (loa? (second x))) 
       (loa-combine x (f-loa (second x)) (f-xbody (rest (rest x))))]
      [else (plain-combine x (f-xbody (rest x)))]))
  ;; Xbody -> Z
  (define (f-xbody x)
    (cond
      [(empty? x) body0]
      [(string? (first x)) (string-combine (first x) (f-xbody (rest x)))]
      [(cons? (first x)) (xexpr-combine (f-xexpr (first x)) (f-xbody (rest x)))]
      [else (f-xbody (rest x))]))
  ;; LOA -> [Listof X]
  (define (f-loa x)
    (cond
      [(empty? x) '()]
      [else (attr-combine (first x) (f-loa (rest x)))]))
  ;; -- IN -- 
  (f-xexpr x0))

;; String or (cons Symbol Y) or empty or (cons (list Symbol String) Any) --> Boolean
;; is the given value a loa, possibly in front of some other Xexpr elements 
(define (loa? x)
  (or (empty? x) (and (cons? x) (cons? (first x)))))

;; ---------------------------------------------------------------------------------------------------
;; tester 

(define-syntax (simulate-file stx)
  (syntax-case stx ()
    [(simulate-file) 
     (raise-syntax-error #f "expects at least one sub-expression" stx)]
    [(simulate-file reader str ...) #'(simulate-file/proc (f2h reader) str ...)]))

(define (simulate-file/proc reader . los)
  (define _1 (check-proc "simulate-file" reader 1 "reader" "one argument"))
  (define _2 
    (andmap 
     (lambda (f)
       (check-arg "simulate-file" (string? f) "sequence of strings" "" f))
     los))
  (define t (make-temporary-file "drracket-temporary-file-~a"))
  (dynamic-wind 
   (lambda ()
     (with-output-to-file t 
       (lambda () (for-each displayln los))
       #:mode 'text
       #:exists 'replace))
   (lambda () 
     (reader (path->string t)))
   (lambda ()
     (delete-file t))))
