;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |Problem Set 10|) (read-case-sensitive #t) (teachpacks ((lib "lab8-teachpack.rkt" "installed-teachpacks"))) (htdp-settings #(#t quasiquote repeating-decimal #f #t none #f ((lib "lab8-teachpack.rkt" "installed-teachpacks")))))
;; Homework 10
;; Authors:
;;   Charles Hoyt hoyt.ch@husky.neu.edu
;;   Amanda Kerr  kerr.a@husky.neu.edu

;; PROBLEM 1

;; [Listof Number] Number -> [Listof Number]
;; sorts a list of x based on the field with getter f
;; QuickSort with Threshold (generative recursion)
#; (define (quick-sort-t l threshold) ... )
(define (quick-sort-t l threshold)
  (cond [(empty? l) empty]
        [else  
         (cond [(< (length l) threshold) 
                (insertion-sort l)]
               [else
                (local [(define pivot (first l))
                        (define less    (filter (λ (e) (< e pivot)) l))
                        (define same    (filter (λ (e) (= e pivot)) l))
                        (define greater (filter (λ (e) (> e pivot)) l))]
                  (append (quick-sort-t greater threshold)
                          same
                          (quick-sort-t less threshold)))])]))

(check-expect (quick-sort-t '() 2) '())
(check-expect (quick-sort-t '(4 3 4 1 5) 20) '(5 4 4 3 1))
(check-expect (quick-sort-t '(4 3 4 1 5 ) 2) '(5 4 4 3 1))

;; insertion-sort : [Listof Number] -> [Listof Number]
;; sort the list of numbers in descending order
#; (define (insertion-sort l) ...)
(define (insertion-sort l)
  (cond
    [(empty? l) empty]
    [(cons? l) (insert (first l) (insertion-sort (rest l)))]))

(check-expect (insertion-sort '()) '())
(check-expect (insertion-sort '(4)) '(4))
(check-expect (insertion-sort '(4 3 4)) '(4 4 3))

;; insert : Number [Listof Number] (sorted)  ->  [Listof Number] (sorted)
;; inserts a number in a sorted list of numbers
#; (define (insert n l)...)
(define (insert n l)
  (cond
    [(empty? l) (cons n empty)]
    [else (cond
            [(>= n (first l)) (cons n l)]
            [(<  n (first l)) (cons (first l) (insert n (rest l)))])]))

(check-expect (insert 3 '()) '(3))
(check-expect (insert 3 '(5 4 3 2 1)) '(5 4 3 3 2 1))

;; PROBLEM 2

;; A [File X] is one of:
;; - empty
;; - (cons Number [File X])
;; - (cons 'NL [File X])

(define-struct rr (table costs))
;; A Check is a (make-rr Number [Listof Number]) where: 
;; - the number is the table number
;; - the list contains the amounts charged to the table

;; file->list-of-checks : [File Number] -> [Listof Check]
;; parses the file and converts to records
#;(define (file->list-of-checks fon) ...) 
(define (file->list-of-checks fon)
  (map file->check (parse fon 'NL)))

(check-expect (file->list-of-checks
               '(1 2.30 4.00 12.50 13.50 NL 2 4.00 18.00 NL 4 2.30 12.50))
              (list (make-rr 1 '(2.30 4.00 12.50 13.50))
                    (make-rr 2 '(4.00 18.00))
                    (make-rr 4 '(2.30 12.50))))

;; parse: [Listof X] X -> [Listof [Listof X]]
;; splits the list into sublists, discarding the delimiter
#; (define (parse l del) ...)
(define (parse l del)
  (cond [(empty? l) '(())] 
        [else
         (cond [(member? del l)
                (local [(define del-index (add1 (get-index l del)))
                        (define left (take l (sub1 del-index)))
                        (define right (take-rest l del-index))]
                  (cons left (parse right del)))]
               [else (list l)])]))


(check-expect (parse '(1 D 3) 'D) '((1) (3)))
(check-expect (parse '(1 2 3) 'D) '((1 2 3)))
(check-expect (parse '(1 D 2 D 3) 'D) '((1) (2) (3)))

;; file->check : [File Number] -> Check
;; converts a delimited file into a check (no NL's)
#; (define (file->check file) ...)
(define (file->check file)
  (make-rr (first file) (rest file)))

(check-expect (file->check '(1 2.30 4.00 12.50 13.50))
              (make-rr 1 '(2.30 4.00 12.50 13.50)))

;; take: [Listof Number] Number -> [Listof Number]
;; gets a sublist of the first x items
#; (define (take l x) ...)
(define (take l x)
  (cond [(empty? l) empty]
        [(cons? l)
         (cond [(< 0 x)
                (cons (first l) 
                      (take (rest l) (sub1 x)))]
               [else '()])]))

(check-expect (take '(1 2 3 4) 2) '(1 2))
(check-expect (take '(1 2 3 4) 0) '())

;; take-rest: [Listof Number] Number -> [Listof Number]
;; get a sublist after the first x items
#; (define (take-rest l x) ...)
(define (take-rest l x)
  (reverse (take (reverse l) (- (length l) x))))  

(check-expect (take-rest '(a b c d) 2) '(c d))
(check-expect (take-rest '(a b c d) 0) '(a b c d))

;; get-index : [Listof (Symbol or Number)] Symbol -> Number
;; gets the index of the first value
#; (define (get-index l s) ...)
(define (get-index l s)
  (cond [(not (member? s l)) -1]
        [(cons? l)
         (cond [(and (symbol? (first l)) (symbol=? (first l) s)) 0]
               [else (add1 (get-index (rest l) s))])]))

(check-expect (get-index '(a b c) 'a)  0)
(check-expect (get-index '(a b c) 'b)  1)
(check-expect (get-index '(a b c) 'q) -1)

;; PROBLEM 3

(define INTERVAL-MIN 1)
(define f1 (λ (x) x))
(define f2 (λ (x) (sqr x)))

;; integrate-dc: [Number -> Number] Number Number -> Number
;; integrates a continuous [Number -> Number] from the left limit to right limit
#; (define (integrate-dc f left right) ...)
(define (integrate-dc f left right)
  (cond
    [(<= (- right left) INTERVAL-MIN)
     (trapezoid f left right)]
    [else 
     (local [(define mid (* 1/2 (+ left right)))]     
       (+ (integrate-dc f left mid)   
          (integrate-dc f mid right)))]))

(check-expect (integrate-dc f1 0 2) 2)
(check-expect (integrate-dc f2 0 2) 3)

;; trapezoid:  [Number -> Number] Number Number -> Number
;; makes a trapezoidal approximation of area under curve f
#; (define (trapezoid f left right) ... )
(define (trapezoid f left right)
  (* 1/2 (- right left) (+ (f right) (f left))))

(check-expect (trapezoid f1 0 1) 0.5)
(check-expect (trapezoid f1 1 2) 1.5)
(check-expect (trapezoid f2 0 1) 0.5)
(check-expect (trapezoid f2 1 2) 2.5)

;; PROBLEM 4 and 5

;; A [Matrix X] is a [Listof [Listof X]]

(define m1 '((2 3) 
             (3 7)))
(define m2 '((1 2 3) 
             (1 3 5)
             (1 5 7)))
(define m3 '((2 3 3 10)
             (0 3 9 21)
             (0 -3 -8 -19)))
(define m4-swap '((2 3 3 8)
                  (2 3 -2 3)
                  (4 -2 2 4)))

;; subtract : [Listof Number] [Listof Number] -> [Listof Number]
;; subtracts the first list from the second (equal length lists)
#; (define (subtract l1 l2) ...)
(define (subtract l1 l2)
  (cond [(and (empty? l1) (empty? l2)) empty]
        [(and (cons? l1) (cons? l2))
         (cons (- (first l2) (first l1))
               (subtract (rest l1) (rest l2)))]
        [else (error "lists not equal len")]))

(check-error (subtract '() '(1)))
(check-expect (subtract '() '()) '())
(check-expect (subtract '(3) '(5)) '(2))
(check-expect (subtract '(1 2 3) '(4 5 6)) '(3 3 3))

;; scale : [Listof Number] Number -> [Listof Number]
;; scale each item in the list by k
#; (define (scale l k) ...)
(define (scale l k)
  (map (λ (x) (* k x)) l))

(check-expect (scale '() 5) '())
(check-expect (scale '(1 2 3) 5) '(5 10 15))

;; eliminate : [Listof Number] [Listof Number] -> [Listof Number]
;; takes two lists with equal number of leading zeros
;; eliminates the first element of the second list by using the first list
;; returns the eliminated second list
#; (define (eliminate l1 l2) ...)
(define (eliminate l1 l2)
  (local [(define first (first-pos l1))]
    (subtract (scale l1 (/ (list-ref l2 first) 
                           (list-ref l1 first))) 
              l2)))

(check-expect (eliminate '(1) '(3)) '(0))  
(check-expect (eliminate (first m1) (second m1)) '(0 2.5))

;; swap? : [Matrix Number] -> Boolean
;; checks if the first two rows should be swapped
(define (swap? m)
  (cond [(empty? m) false]
        [(empty? (rest m)) false]
        [else (= 0 (list-ref (first m) 
                             (first-pos (second m))))]))

(check-expect (swap? '()) false)
(check-expect (swap? '((1))) false)
(check-expect (swap? '((1) (0))) false)
(check-expect (swap? '((0) (1))) true)

;; swap : [Matrix Number] -> [Matrix Number]
;; switch the first two rows of the matrix. assumes matrix has 2+ rows
(define (swap m)
  `(,(second m)
    ,(first m)
    ,@(rest (rest m))))

(check-expect (swap '((0) (1))) '((1) (0)))

;; triangulate: [Matrix Number] -> [Matrix Number]
;; reduces a matrix to an upper-right triangular matrix
#; (define (triangulate m) ...)
(define (triangulate m)
  (cond [(empty? m) empty]
        [else 
         (local [(define matrix (if (swap? m) (swap m) m))
                 (define pivot-row (first matrix))]
           (cons pivot-row 
                 (triangulate
                  (map (λ (row) (eliminate pivot-row row)) (rest matrix)))))]))

(check-expect (triangulate m1) '((2 3) (0 2.5)))
(check-expect (triangulate m2) '((1 2 3) 
                                 (0 1 2) 
                                 (0 0 -2))) 
(check-expect (triangulate m3) '((2 3 3 10)
                                 (0 3 9 21)
                                 (0 0 1 2)))
(check-expect (triangulate m4-swap) '((2 3 3 8)
                                      (0 -8 -4 -12)
                                      (0 0 -5 -5)))


;; first-pos : [Listof Number] -> Number
;; finds the index of the first occurence of a non-zero number
#; (define (first-pos l) ...)
(define (first-pos l)
  (cond [(empty? l) -1]
        [(cons? l)
         (if (not (zero? (first l)))
             0
             (add1 (first-pos (rest l))))]))

(check-expect (first-pos '(4)) 0)
(check-expect (first-pos '(0 4)) 1)
(check-expect (first-pos '(0 0 4)) 2)