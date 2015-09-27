;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname lab8) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ())))
(require "7teach.rkt")
(require 2htdp/batch-io)

(define wp-page
  '(html
     (head
       (title "My first generated web page"))
     (body
       (p "This is my first generated web page. More to come"))))


;; (write-file "html/1.html" (xexpr-as-string wp-page))
;; (show "html/1.html")

;; NaturalNumber -> String
;; makes a html file name
#; (define (make-filename n) ...)
(define (make-filename n)
  (string-append "html/" (number->string n) ".html"))

(check-expect (make-filename 0) "html/0.html")
(check-expect (make-filename 5000000) "html/5000000.html")

; Nat -> Sexpr
; generate the nth web page
(define (my-nth-page n)
  (local ((define n-as-string (number->string n)))
    `(html
       (head
         (title
           ,(string-append "My " n-as-string " generated web page.")))
       (body
         (p
          ,(string-append "This is my "
                          n-as-string
                          " generated web page. More to come."))))))


;; Nat -> String
;; 
#;(define (build-page n) ...)
(define (build-page n)
  (write-file (make-filename n) (xexpr-as-string (my-nth-page n))))

(build-page 5)
#;(show "html/5.html")


;; Nat -> [Listof String]
;; purpose - make a bunch of htmls's
(define (make-batch n)
  (build-list n build-page))
  
(check-expect (make-batch 3) (list "html/0.html" "html/1.html" "html/2.html"))

;; Symbol Sexpr Sexpr -> Sexpr
;; replace any of the symbol in e1 with e2
#;(define (sexpr-temp s)
  (cond [(symbol? s) ...]
        [(string? s) ...]
        [(number? s) ...]
        [(cons? s) ...]))
        
#;(define (sexpr-list-temp s)
  (cond [(empty? s) ...]
        [(cons? s) ... (sexpr-temp (first s) )... (sexpr-list-temp (rest s))....]))

(define (replace-symbol s e1 e2)
  (cond [(symbol? e2) (if (symbol=? s e2) e1 e2)]
        [(string? e2) e2]
        [(number? e2) e2]
        [(cons? e2) (replace-symbol-list s e1 e2)]))

;; Symbol Sexpr [Listof Sexpr] -> [Listof Sexpr] 
(define (replace-symbol-list s e1 e2)
   (cond [(empty? e2) empty]
         [(cons? e2) (cons (replace-symbol s e1 (first e2)) 
                          (replace-symbol-list s e1 (rest e2)))]))

(check-expect (replace-symbol 'a 'b '(a a c c d d)) '(b b c c d d))
(check-expect (replace-symbol 'a 'b '(a (a c) c d d)) '(b (b c) c d d))
(check-expect (replace-symbol 'a 'b 'a) 'b)

; All PlayXexprs are of the form:
;    (cons 'playlist (cons [Listof Attribute] [Listof SongXexpr]))
; where the only Attribute is associated with 'name.
; Interpretation: a PlayXexpr is a data representation for playlists
; 
; All SongXexprs are of the form:
;    (cons 'song [Listof Attribute])
; where the valid Attributes are: 'album, 'artist, 'title, and 'year
; Interpretation: a SongXexpr is a data representation for songs in a playlist
; 
; An Attribute is a:
;   (list Symbol String)
; Interpretation: '(a "b") represents the attribute
; a = "b" in a piece of XML data

;; Attribute Symbol -> [Maybe String]
;; returns associated string content, if the symbol is not valid -> false
(define (retrieve a s)
  (if (symbol=? (first a) s)
      (second a)
      false))
(check-expect (retrieve '(a b) 'a) 'b)
(check-expect (retrieve '(a b) 'q) false)

;; [List-of Attribute] Symbol -> [Maybe String]
;; returns first occurance of an associated string content, else false
(define (retrieve-from-list loa s)
  (cond [(empty? loa) false]
        [else (if (string? (retrieve (first loa) s))
                  (retrieve (first loa) s)
                  (retrieve-from-list (rest loa) s))]))
(check-expect (retrieve-from-list '((a "b") (q "p")) 'a) "b")
(check-expect (retrieve-from-list '((a "b") (q "p")) 'z) false)

;; SongXexpr -> String
;; gets the title
(define (get-title s)
  (retrieve-from-list (first (rest s)) 'title))
(check-expect (get-title '(song
   ((album "Wish You Were Here")
    (artist "Pink Floyd")
    (title "Shine on You Crazy Diamond, Pts. 1-5")
    (year "1975")))) "Shine on You Crazy Diamond, Pts. 1-5")

;; PlayXexpr -> [List-of String]
;; produces a list of all song titles in a given PlayXexpr
(define (all-songs p)
  (map get-title (rest (rest p))))
(check-expect (all-songs (read-plain-xexpr "ahmed.xml")) 
              (list "Shine on You Crazy Diamond, Pts. 1-5" "Shine on You Crazy Diamond, Pts. 6-9" "Nick's Boogie" "Childhood's End" "Dogs" "Goodbye Blue Sky" "Echoes"))

;; 

; String -> Xexpr
; generate a Xexper
(define (song-page song-title)
    `(html
       (head
         (title ,song-title
          ))
       (body
         (p
          ,(string-append "This is the page for: " song-title)))))

;; PlayXexpr -> [List-of String]
;; creates a web-page for each song
(define (song-page-batch p)
  (map (λ (title) 
         (write-file (string-append title ".html") (xexpr-as-string (song-page title))))
  (all-songs p)))



