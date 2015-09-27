;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |Lab 8|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ())))
;; zero: [X->X] -> [X->X]
;; makes a function that applies f once in a row
(define (zero f) 
  (λ (x) x))

;; one: [X->X] -> [X->X]
;; makes a function that applies f once in a row
(define (one f) 
  (λ (x) (f x)))

(check-expect ((one add1) 0) 1)

;; two: [X->X] -> [X->X]
;; makes a function that applies f twice in a row
(define (two f) 
  (λ (x) (f (f x))))

(check-expect ((two add1) 0) 2)

;; three: [X->X] -> [X->X]
;; makes a function that  applies f thrice in a row
(define (three f) 
  (λ (x) (f (f (f x)))))

(check-expect ((three add1) 0) 3)


(define (four f) 
  (λ (x) (f (f (f (f x))))))
  
;; rep->nat : Repeater -> Natural
;; counts how many times the function composes
(define (rep->nat f)
  ((f add1) 0))

(check-expect (rep->nat two) 2)
(check-expect (rep->nat three) 3)
(check-expect (rep->nat (λ (f) (λ (x) ((three f) ((two f) x))))) 5)

;; apply f one more time to its repeater
;; Repeater [X->X] -> Repeater
(define (rep-add1 r f)
  (λ (x) (f ((r f) x))))

(check-expect ((rep-add1 two add1) 0) 3)

;; Nat [X->X] -> [X->X]
(define (nat->rep n f)
  (foldr compose identity (make-list n f)))

(check-expect ((nat->rep 2 add1) 0) 2)
(check-expect ((nat->rep 3 add1) 0) 3)

; A Repeater is a function:
; [X -> X] -> [X -> X]

;; rep+ : Repeater Repeater -> Repeater 
;; Church Numerals!
(define (rep+ r1 r2)
  ;; f : [X->X] 
  (λ (f) 
    ;; X : X                  
    (λ (X) ((r1 f) ((r2 f) X)))))

(check-expect (((rep+ one two) add1) 0) ((three add1) 0))
(check-expect (((rep+ two two) add1) 0) ((four add1) 0))


;; rep-expt : Reapeater Reapeater -> Repeater
(define (rep* r1 r2)
  ;; f: [X->X]
  (λ (f)
    (r1 (r2 f))))
    
(check-expect (((rep* two two) add1) 0) ((four add1) 0))
(check-expect (((rep* three two) add1) 0) ((nat->rep 6 add1) 0))

;; rep-expt : Reapeater Reapeater -> Repeater
(define (rep-expt r1 r2) 
  (r2 r1))

(check-expect (((rep-expt two two) add1) 0) ((four add1) 0))
(check-expect (((rep-expt one one) add1) 0) ((one add1) 0))
(check-expect (((rep-expt three two) add1) 0) ((nat->rep 9 add1) 0))


  
;; x-expression-has-content-attribute: 
  
(define (x-expression-has-content-attribute x) 
  (cond [(cons? x) 
            (if (attribute? (first (first (rest x))))
                (or (ormap (λ (a) (has-attribute a 'content)) (first (rest x))) ;; list of attribute
                    (ormap x-expression-has-content-attribute (first (rest (rest x))))
                ;; list of xepres
                (ormap x-expression-has-content-attribute (first (rest x))))]
        
        [else false]))

;; attribute? Any -> Boolean
(define (attribute? x)
  (and (cons? x) 
       (symbol? (first x)) 
       (cons? (rest x)) 
       (string? (first (rest x)))
       (empty? (rest (rest x)))))

(check-expect (attribute? '(a "b")) true)
(check-expect (attribute? '(a)) false)
(check-expect (attribute? '(a b)) false)
(check-expect (attribute? '(a "b" "c")) false)

;; has-attribute : Attribute Symbol -> Boolean
;; does this attribute contain the symbol name?
(define (has-attribute a s)
  (symbol=? (first a) s))
  
(check-expect (has-attribute '(a "b") 'a) true)
(check-expect (has-attribute '(a "b") 'c) false)
  
  
