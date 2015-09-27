;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname |Data definitions Balloon Game|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ())))
;; Designing Programs
(require 2htdp/image)
(require 2htdp/universe)


;; A Balloon is a PositiveRealNumber
;; where the number is the radius of the circle

(define balloon-example 3)

;; A WorldState is a structure: (make-balloonstate Number Number)
;; where the first number is the radius of the balloon
;; and the second is time

(define-struct world (radius time))

(define INITIAL-WORLD (make-world 50 0))

(define BACKGROUND (empty-scene 100 100))

(define SHRINK-TIME0 10)
(define SHRINK-TIME1 20)
(define SHRINK-TIME2 30)
(define BASE-SHRINK-SPEED 10)
(define BASE-INCREASE-SPEED 7)
(define TICK 1)
;; shrink-world: SW -> SW
#;
(define (shrink-world world)
  ...)

;; decrease the radiius of the world based on the time

;;template
#;
(define (shrink-world world)
  (cond [(< (world-time world) SHRINK-TIME0)...]
        [(< (world-time world) SHRINK-TIME1)...]
        [(< (world-time world) SHRINK-TIME2)...]
        [else...]
))

(define (shrink-world world)
  (make-world (- (world-radius world) 
                 (cond [(< (world-time world) SHRINK-TIME0) BASE-SHRINK-SPEED]
                       [(< (world-time world) SHRINK-TIME1) (+ 10 BASE-SHRINK-SPEED)]
                        [(< (world-time world) SHRINK-TIME2) (+ 15 BASE-SHRINK-SPEED)]
                         [else (+ 25 BASE-SHRINK-SPEED)])) 
                 (+ TICK (world-time world))))


;; tock: WorldState -> WorldState
;; dereases the radius of our balloon



;;grow-world: SW Number Number MouseEvent -> SW
;;purpose - if a mouse button has been pressed then increase the size of the world
;;signature:
#;
(define (grow-world world x y mouse-event) ... )

;;template
#;
(define (grow-world world x y mouse-event)
  cond(   [(string=? mouse-event "button-down") ...]
          [else ...]))

;;code
(define (grow-world world x y mouse-event)
  (cond  [(string=? mouse-event "button-down") (make-world (+ BASE-INCREASE-SPEED (world-radius world)) (world-time world)) ]
          [else world]))


;;draw-world:SW -> image
;;purpose - draws a circle that represents th world
(define (draw-world world)
  (overlay (circle (world-radius world) "solid" "red") (empty-scene 400 400)))





;; draw: WorldState -> Image
;; draws a 

(big-bang INITIAL-WORLD
          (on-tick shrink-world)
          (to-draw draw-world)
          (on-mouse grow-world)
          ) 



