;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |Problem Set #5|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ())))
(require 2htdp/image)

;;Problem 1
;;Exercise 210. Design tabulate, which is the abstraction of the following two functions:

; Number -> [List-of Number]
; tabulate sin between n
; and 0 (inclusive) in a list
(define (tab-sin n)
  (cond
    [(= n 0) (list (sin 0))]
    [else
     (cons (sin n)
           (tab-sin (sub1 n)))]))



; Number -> [List-of Number]
; tabulate sqrt between n
; and 0 (inclusive) in a list
(define (tab-sqrt n)
  (cond
    [(= n 0) (list (sqrt 0))]
    [else
     (cons (sqrt n)
           (tab-sqrt (sub1 n)))]))


;When tabulate is properly designed, use it to define a tabulation function for sqr andtan.


;; function that 
;; (Number->Number) Number -> [Listof Number]
;; explanation f is the function to that does a calculation on a number

(define (tabulate f n)
  (cond
    [(= n 0) (list (f 0))]
    [else
     (cons (f n)
           (tabulate f (sub1 n)))]))

(check-expect (tabulate sqr 3) (list 9 4 1 0)) 

;;Problem 2
;Exercise 212. Design fold2, which is the abstraction of the following two functions:

; [List-of Number] -> Number
(define (product-2 l)
  (cond
    [(empty? l) 1]
    [else
     (* (first l)
        (product-2 (rest l)))]))

; [List-of Posn] -> Image
(define (image* l)
  (cond
    [(empty? l) emt]
    [else
     (place-dot (first l)
                (image* (rest l)))]))

; Posn Image -> Image
(define (place-dot p img)
  (place-image dot
               (posn-x p) (posn-y p)
               img))

; graphical constants:    
(define emt (empty-scene 100 100))
(define dot (circle 3 "solid" "red"))

; fold2: (X Y -> Y) Y [List-of X] -> Y
; the identity is the base case of Y
; the lambda puts an X onto a Y and makes a new Y

(define (fold2 f id l)
  (cond
    [(empty? l) id]
    [else
     (f (first l)
        (fold2 f id (rest l)))]))

(check-expect (fold2 * 1 (list 1 2 3)) 6)
(check-expect (fold2 place-dot emt (list (make-posn 10 10))) (place-image dot 10 10 emt))

;;Problem 3

;Exercise 213. Each of the following signatures describes a collection of functions:
;Describe these collections with at least one example from ISL. 
;(Number -> Boolean) 
(zero? 2)
(positive? -2)
(negative? -5)
;(Boolean String -> Boolean)


;(Number Number Number -> Number)

;(Number -> [List-of Number])

;([List-of Number] -> Boolean)



;Problem 4
;
;Exercise 214. Formulate signatures for the following functions:

; 1. sort-n, which consumes a list of numbers and a function that consumes two numbers (from the list) 
; and produces a Boolean; sort-n produces a sorted list of numbers.

; sort-n: [Listof Numbers] (Number Number -> Boolean) -> [Listof Numbers]

; 2. sort-s, which consumes a list of srings and a function that consumes two strings (from the list) 
; and produces a Boolean; sort-s produces a sorted list of strings.

; sort-s: [Listof Strings] (String String -> Boolean) -> [Listof String]

; Then abstract over the two signatures, following the above steps. Also show that the generalized signature can be instantiated to describe 
; the signature of a sort function for lists of IRs. 

; sort-X: [Listof X] (X X -> Boolean) -> [Listof X]

; if we have a [Listof IR] and a function that takes (IR IR -> Boolean) we can apply sort-X where X is IR


; Exercise 215. Formulate signatures for the following functions:

; map-n, which consumes a list of numbers and a function from numbers to numbers to produce a list of numbers.
; map-n: [Listof Number] (Number -> Number) -> [Listof Number]

; map-s, which consumes a list of srings and a function from strings to strings and produces a list of strings.
; map-s: [Listof String] (String -> String) -> [Listof String]

; Then abstract over the two signatures, following the above steps. Also show that the generalized signature 
;can be instantiated to describe the signature of the map-IR function above. 

; map-X: [Listof X] (X -> X) -> [Listof X]
; if X is IR then : [Listof IR] (IR -> IR) -> [Listof IR]


;Problem 5 
;Interpret the following data definition:
;; [List-of [Number -> Number]]


; purpose: The function consumes a list of functions from numbers to numbers and produces the list of results of applying these functions to 0.
; 0: [List-of [Number -> Number]] -> [Listof Number]
#; (define (0 l) ...)
#; (define (0 l)
     (cond [(empty? l) ...]
           [(cons? l) (first l) (0 (rest l))]))

(define (op0 l)
  (cond [(empty? l) empty]
        [(cons? l) (cons ((first l) 0) (op0 (rest l)))]))

(check-expect (op0 (list (λ (x) x) (λ (x) (add1 x)))) (list 0 1))

;Problem 6 

; purpose: find a string in a list given a comparator
; abstract-find-string: [Listof String] (String String -> Boolean) String -> Boolean
#; (define (find-string l s) ...)
#; (define (list-template l)
     (cond [(empty? l) ...]
           [(cons? l) ...(first l)... (list-template (rest l)) ...]))

(define (find-string l s)
  (cond [(empty? l) false]
        [(cons? l) 
         (or (string=? (first l) s) 
             (find-string (rest l) s))]))

(check-expect (find-string (list "hi" "bye") "hi") true)
(check-expect (find-string (list "hi" "bye") "HI") false)


; purpose: find a string in a list given a comparator
; abstract-find-string: [Listof String] (String String -> Boolean) String -> Boolean
#; (define (abstact-find-string l f s) ...)

(define (abstract-find-string l f s)
  (cond [(empty? l) false]
        [(cons? l) 
         (or (f (first l) s) 
             (abstract-find-string (rest l) f s))]))

(check-expect (abstract-find-string (list "hi" "bye") string=? "hi") true)
(check-expect (abstract-find-string (list "hi" "bye") string=? "HI") false)
(check-expect (abstract-find-string (list "hi" "bye") string-ci=? "HI") true)

;Problem 7
;
;The local meteorological society keeps a list of records about the weather each day. They track the following attributes: zip code, humidity (as a percentage), and high and low temperatures (in Fahrenheit) for the day.
;Here is the data definition for a weather record:
(define-struct weather (zip humidity high low))
;; A Weather is a structure:
;;    (make-weather String Number Number Number)
;; interpretation: (make-weather z hum hi lo) is a day's weather
;;   record where:
;; – z is the 5-digit zip code where the data was collected
;; – hum is the humidity as a percentage
;; – hi and lo represent the day's high and low temperatures in
;;    degrees Fahrenheit, and hi is greater than or equal to lo
;Design a function muggy that, given a list of weather records, a humidity humid-threshold
;(expressed as a percentage), and a temperature temp-threshold (expressed in degrees Fahrenheit), 
;eliminates from the list any weather records with a humidity lower than humid-threshold or a high temperature lower than temp-threshold.

(define-struct w-constraint (humidity temp))
;; A WC (Weather Constrain) is a (make-w-constraint Number Number)
;; where the first number is the min humidity threshold
;; is the min temp threshold

; purpose - remove Weather values with hi below temp threshold or below humidity threshold
; muggy: [Listof Weather] WC -> [Listof Weather]
#; (define (muggy l wc) ...)
;; we've already done a list template
(define (muggy l wc)
  (cond [(empty? l) empty]
        [(cons? l) (if (ok-weather? (first l) wc)
                     (cons (first l) (muggy (rest l) wc))
                     (muggy (rest l) wc))]))


(define weather1 (make-weather 06473 100 100 0))
(check-expect (muggy (list weather1) (make-w-constraint 50 50)) (list weather1))
(check-expect (muggy (list weather1) (make-w-constraint 120 0)) empty)
(check-expect (muggy (list weather1) (make-w-constraint 0 120)) empty)


; ok-weather? : Weather WC -> Boolean
; see if the value returned by this function is less than the thresholds
#; (define (ok-weather? w wc) ...)
(define (ok-weather? w wc)
  (and (< (w-constraint-temp wc) (weather-high w)) (< (w-constraint-humidity wc) (weather-humidity w))))

(check-expect (ok-weather? weather1 (make-w-constraint 99 99)) true)
(check-expect (ok-weather? weather1 (make-w-constraint 0 100)) false)
(check-expect (ok-weather? weather1 (make-w-constraint 100 0)) false)

;Problem 8 
;
;Design a function near-origin that, given a list of Posns and a distance dist,
;eliminates all the posns in the list that are distance dist or more away from the origin (0,0).
;

; purpose: elimiate Posn's in list that are farther than the given dist from (0,0)
; truncate-posn : [Listof Posn] Number -> [Listof Posn]
#; (define (near-origin l dist) ...)
(define (near-origin l dist)
  (cond [(empty? l) empty]
        [(cons? l) (if (keep-posn? (first l) dist)
                     (cons (first l) (near-origin (rest l) dist))
                     (near-origin (rest l) dist))]))

(check-expect (near-origin (list (make-posn 25 25) (make-posn 50 50)) 5) empty)
(check-expect (near-origin (list (make-posn 25 25) (make-posn 50 50)) 50) (list (make-posn 25 25)))
(check-expect (near-origin (list (make-posn 25 25) (make-posn 50 50)) 1000) (list (make-posn 25 25) (make-posn 50 50)))

;; Determine if a distance is close enough to keep
;; keep-posn? : Posn Number -> Boolean
#; (define (keep-posn? posn dist) ...)
(define (keep-posn? posn dist)
  (< (distance posn (make-posn 0 0)) dist))

(check-expect (keep-posn? (make-posn 10 10) 100) true)
(check-expect (keep-posn? (make-posn 10 10) 1) false)

;; Calculate the distance between two Posns (dis) 
;; distance: Posn Posn -> Number
#; (define (distance p1 p2) 5)

(define (distance p1 p2)
	(sqrt (+ (sqr (- (posn-y p1) (posn-y p2))) (sqr (- (posn-x p1) (posn-x p2))))))
  
(check-expect (distance (make-posn 0 0) (make-posn 3 4)) 5)


;Problem 9 
;
;Note the similarities and differences between muggy and near-origin. 
;Design a function that abstracts over the differences and then use it to 
;implementmuggy.v2 and near-origin.v2. Be sure to test that the latter two behave the same asmuggy and near-origin, respectively.

;Abstraction 
;signature [Listof X] (X Y -> Boolean) Y -> [Listof X]
;purpose: abstract the remove function based on a comparator f to value y
(define (abstract-truncate l f y)
  (cond [(empty? l) empty]
        [(cons? l) (if (f (first l) y)
                     (cons (first l) (abstract-truncate (rest l) f y))
                     (abstract-truncate (rest l) f y))]))

(define (muggy.v2 l wc) 
  (abstract-truncate l ok-weather? wc))

(check-expect (muggy.v2 (list weather1) (make-w-constraint 50 50)) (list weather1))
(check-expect (muggy.v2 (list weather1) (make-w-constraint 120 0)) empty)
(check-expect (muggy.v2 (list weather1) (make-w-constraint 0 120)) empty)

(define (near-origin.v2 l dist)
  (abstract-truncate l keep-posn? dist))

(check-expect (near-origin.v2 (list (make-posn 25 25) (make-posn 50 50)) 5) empty)
(check-expect (near-origin.v2 (list (make-posn 25 25) (make-posn 50 50)) 50) (list (make-posn 25 25)))
(check-expect (near-origin.v2 (list (make-posn 25 25) (make-posn 50 50)) 1000) (list (make-posn 25 25) (make-posn 50 50)))
