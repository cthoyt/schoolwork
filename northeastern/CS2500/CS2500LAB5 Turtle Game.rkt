;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |Lab #5 Turtle Game|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ())))
;; A Turtle Command Execution Engine
(require 2htdp/image)
(require 2htdp/universe)

;; -----------------------------------------------------------------------------
;; constant definitions
(define SIZE 400)
(define MID (quotient SIZE 2))
(define STEP (quotient SIZE 200))
(define START (make-posn MID MID))
(define LEFT (make-posn (- STEP) 0))
(define RIGHT (make-posn STEP 0))
(define DOWN (make-posn 0 STEP))
(define UP (make-posn 0 (- STEP)))

(define BG (empty-scene SIZE SIZE))
(define TC "lightseagreen")
(define TS (quotient SIZE 20))
(define TA 330)
(define TURTLE-DOWN  (isosceles-triangle TS TA "solid" TC))
(define TURTLE-RIGHT (rotate 90 TURTLE-DOWN))
(define TURTLE-UP    (rotate 90 TURTLE-RIGHT))
(define TURTLE-LEFT (rotate 90 TURTLE-UP))

;; -----------------------------------------------------------------------------
;; data representation 
(define-struct turtle (place dir todo))

;; A TW (turtle world) is (make-turtle Posn Direction Command*)

;; A Direction is one of: 
;; -- 'up
;; -- 'down 
;; -- 'left 
;; -- 'right 

;; Command* is one of: 
;; -- empty 
;; -- (cons ACommand Command*) 

;; A Command is one of: 
;; -- 'turn-left 
;; -- 'turn-right 
;; -- 'move 

;; interpretation: 
;; (make-turtle p d c) means the turtle is at Posn p, it is facing in 
;; Direction d, and it has the list of commands c left to execute. 


(define t0 (make-turtle START 'up '()))

#;(define t1
  (make-turtle START 'up W))

;; after executing 'turn-left, we get
(define c2 '(move turn-right move))
(define t2 (make-turtle START 'left c2))

;; after executing 'move, we get: (posn+ START LEFT)
(define t3 
  (make-turtle (make-posn (- MID STEP) MID)  'left '(turn-right move)))

(define full-round-trip
  '(turn-left turn-right turn-right turn-right turn-right))

;; -----------------------------------------------------------------------------
;; main function 

;; Command* -> Posn 
;; run a Turtle program on a turtle that starts in the middle and faces right, 
;; compute the final position in which the turtle ends up 

;; (This is a somewhat unusual case where you can test the main function well.) 

(check-expect (turtle-interpret c2) (make-posn (+ MID STEP) (+ MID STEP)))
(check-expect (turtle-interpret full-round-trip) START)
(check-expect (turtle-interpret '(turn-left move)) (make-posn MID (- MID STEP)))

(define (turtle-interpret cmds)
  (turtle-place
   (big-bang (make-turtle START 'right cmds)
             [to-draw render-turtle]
             [on-tick execute-one-command]
             [check-with turtle?]
             [stop-when done?])))

;; -----------------------------------------------------------------------------
;; wish list

;; TW -> Image 
;; render the current turtle world 
(define (render-turtle t)
  (place-image (switch-dir (turtle-dir t)) (posn-x (turtle-place t)) (posn-y (turtle-place t)) (empty-scene 500 500)))

;; switch-dir : Direction -> Image
;;figures out which pic to use based on dir
(define (switch-dir dir)
  (cond [(symbol=? (turtle-dir t) 'up) TURTLE-UP]
        [(symbol=? (turtle-dir t) 'down) TURTLE-DOWN]
        [(symbol=? (turtle-dir t) 'left) TURTLE-LEFT]
        [(symbol=? (turtle-dir t) 'right) TURTLE-RIGHT]))

;; TW -> TW 
;; execute the first argument on the todo list of commands (if any) 
(define (execute-one-command t)
  (if (empty? (turtle-todo))
      t
      (make-turtle
       (turtle-place ((first (turtle-todo t)) t))
       (turtle-dir ((first (turtle-todo t)) t))
       (rest (turtle-todo)))))



(define (turn-left tw) ...)
(define (turn-right tw) ...)
(define (move tw) ...)