;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname |Lab 2 Kubala Hoyt|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ())))
(require 2htdp/image)
(require 2htdp/universe)

;; ------------EXERCISE THREE----------------------

(define-struct animal (name species age b-hour d-hour))
;An Animal is represented by a structure:
; (make-animal String String Age Time Time)
; Age is restricted to natural numbers
; A Time is a string that takes the form of HH:MM 
  
;; -----------EXERCISE FOUR------------------------
  
(define-struct food (name expiration-date))
; (make-food String Date)
; Date is a string that takes the form of YYYY-MM-dd

(define-struct new-animal (name species age b-hour d-hour food))
; Added in food

;;------------EXERCISE FIVE---------------------------

;;(define (item-processor item))
;  (... (item-tag item) ... (item-price item)...) 

;;------------EXERCISE SIX---------------------------

;;(define (phd-processor phd))
;  (... (phd-name phd) ... (phd-grant phd) ... (phd-pay-rate phd)...)

;;------------EXERCISE SEVEN---------------------------

;;(define (animal-processor animal))
;(...(animal-name animal) ...(animal-species animal) ...(animal-age animal) ...(animal-b-hour animal) ...(animal-d-hour animal) ...(animal-food animal))

;;------------EXERCISE EIGHT---------------------------

; re-assign: PhD GrantId -> PhD
;Template: (define (re-assign phd new-grantid) (phd-name phd) ... (phd-pay-rate phd)...)
;;(define (re-assign phd new-grantid) 
;;  (make-phd ((phd-name phd) new-grantid (phd-pay-rate phd))))

;;------------EXERCISE NINE---------------------------

; A 3D is a structure:  
;   (make-3d Number Number Number) 
; interpretation: (make-3d a b c) represents a point in  
; R3, the 3-dimensional space 
;distance0: 3D -> Number
;(define (distance0 r3)
;  (... (3d-a r3) (3d-b r3) (3d-c r3)))
(define-struct 3d (a b c))

(define (distance0 r3)
  (sqrt (+ (sqr (3d-a r3)) (sqr (3d-b r3)) (sqr (3d-c r3)))))

;;------------EXERCISE TEN---------------------------

;;(define (animal-birthday animal)
;;(make-new-animal (animal-name animal) (animal-species animal) (+ (animal-age animal) 1) (animal-b-hour animal) (animal-d-hour animal) (animal-food animal)))

;;------------EXERCISE ELEVEN---------------------------

(make-new-animal (animal-b-hour animal) (animal-d-hour animal))

(define (mealtime-check time animal)
  (or (string=? time (animal-b-hour animal)) (string=? time (animal-d-hour animal))))

;;------------EXERCISE TWELVE---------------------------

;;(define (animal-dog-years animal)
;;(make-new-animal (animal-name animal) (animal-species animal) (* (animal-age animal) 7) (animal-b-hour animal) (animal-d-hour animal) (animal-food animal)))


;;------------EXERCISE THIRTEEN---------------------------

(define image-1 (star 50 "solid" "red"))
(define image-2 (circle 25 "outline" "magenta"))

(big-bang (make-posn (make-posn 50 50) (make-posn 100 100)))

(define (mouse-click world posn mouse-event)
  (cond [(string=? mouse-event "button-down")...]
        [else world]))



          