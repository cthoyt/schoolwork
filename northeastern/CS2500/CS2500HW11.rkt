;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |Problem Set 11|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ())))
;; Problem Set 11
;; Authors:
;;   Charles Hoyt hoyt.ch@husky.neu.edu
;;   Amanda Kerr  kerr.a@husky.neu.edu


;; PROBLEM 1

;; A Graph is represented by an adjacency list of Nodes
;;     i.e [Listof '(Node [Listof Node])]
;; A Node is a Symbol

;; Example:
(define g1
  '((A (G E))
    (B (E F))
    (C (D))
    (D ())
    (E (C F))
    (F (D G))
    (G ())))

;; find-route : Node Node Graph  ->  [Maybe [Listof Node]
;; to create a path from src to trg in G
;; if there is no path, the function produces false
(define (find-route src trg G)
  (cond [(symbol=? src trg) (list trg)]
        [else 
         (local 
           ;; Node Graph -> [Listof Node]
           [(define (neighbors node g)
              (first (rest (first 
                            (filter (λ (n) (symbol=? (first n) node)) g)))))
            ;; [Listof Node] -> [Maybe [Listof Node]]
            (define (find-route/list outgoing)
              (cond
                [(empty? outgoing) false]
                [else 
                 (local [(define so-far (find-route (first outgoing) trg G))]
                   (cond
                     [(boolean? so-far) (find-route/list (rest outgoing))]
                     [else so-far]))]))          
            (define possible-route (find-route/list (neighbors src G)))]
           (cond [(boolean? possible-route) false]
                 [else (cons src possible-route)]))]))

(check-expect (find-route 'A 'G g1) '(A G))
(check-expect (find-route 'A 'D g1) '(A E C D))
(check-expect (find-route 'B 'C g1) '(B E C))
(check-expect (find-route 'D 'A g1) false)


;; PROBLEM 5

;; A Lam is (λ (Variable) Expression)

;; A Variable is a Symbol

;; An Expression is one of:
;; - Variable
;; - Lam
;; - (Expression Expression)

;; 1. (lambda (x) y) where:
;;      x is the parameter of the lambda but doesn't show up in the body, so it is free


;; 2. ((lambda (x) x) (lambda (x) x)) can be rewritten for clarity as:
;;    ((lambda (x) x) (lambda (y) y)) where:
;;          x is bound within the first lambda. 
;;          The expression (lambda (y) y) gets put in to the parameter x, then 
;;          bound to the x in the body y is the parameter of the second lamdba 
;;          that gets bound to its respective body

;; 3. ((((lambda (y) (lambda (x) y)) 
;;           (lambda (z) z))
;;           (lambda (w) w))


;;         the body of the first lambda is another lambda that takes in an x. 
;;         that x does not appear in its respective body (it is free), but 
;;         the y from the enclosing lambda gets bound within it. 

;;         the third lambda has a z that is bound. this lambda is passed to y. 
;;         the y in the inner lambda is replaced and the expression 
;;         (λ (x) (λ (z) z) is returned

;;         the fourth lambda has a w that is bound. this lambda is passed to x. 
;;         since x is free, the expression (λ (z) z) is returned.


;; 4. Make up an expression in which x occurs both free and bound.

;; Example 1: ((lambda (x) y) (lambda (x) x))
;; Example 2: ((lambda (x) x) (lambda (x) y))