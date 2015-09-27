; **************** BEGIN INITIALIZATION FOR ACL2s BB MODE ****************** ;
; (Nothing to see here!  Your actual file is after this initialization code);

#|
Pete Manolios
Fri Jan 27 09:39:00 EST 2012
----------------------------

Made changes for spring 2012.

Pete Manolios
Thu Jan 20 08:50:00 EST 2011
----------------------------

The idea of the Bare Bones level is to introduce ACL2 as a
programming language with contracts (a "typed" ACL2) to the
students, using a "minimal" subset of primitive functions.
For example, in the case of the Booleans, all that is built-in
are the constants t and nil and the functions if and equal.

Everything else is built on top of that. 

|#
#+acl2s-startup (er-progn (assign fmt-error-msg "Problem loading the TRACE* book.~%Please choose \"Recertify ACL2s system books\" under the ACL2s menu and retry after successful recertification.") (value :invisible))
; only load for interactive sessions: 
#+acl2s-startup (include-book "trace-star" :uncertified-okp nil :dir :acl2s-modes :ttags ((:acl2s-interaction)) :load-compiled-file nil);v4.0 change

#+acl2s-startup (assign evalable-printing-abstractions nil)

;arithmetic book
#+acl2s-startup (er-progn (assign fmt-error-msg "Problem loading arithmetic-5/top book.~%Please choose \"Recertify ACL2s system books\" under the ACL2s menu and retry after successful recertification.") (value :invisible))
(include-book "arithmetic-5/top" :dir :system)

;basic thms/lemmas about lists
#+acl2s-startup (er-progn (assign fmt-error-msg "Problem loading coi/lists book.~%Please choose \"Recertify ACL2s system books\" under the ACL2s menu and retry after successful recertification.") (value :invisible))
(include-book "coi/lists/basic" :dir :system)

#+acl2s-startup (er-progn (assign fmt-error-msg "Problem loading ACL2's lexicographic-ordering-without-arithmetic book.~%This indicates that either your ACL2 installation is missing the standard books are they are not properly certified.") (value :invisible))
(include-book "ordinals/lexicographic-ordering-without-arithmetic" :dir :system)

#+acl2s-startup (er-progn (assign fmt-error-msg "Problem loading the CCG book.~%Please choose \"Recertify ACL2s system books\" under the ACL2s menu and retry after successful recertification.") (value :invisible))
(include-book "ccg" :uncertified-okp nil :dir :acl2s-modes :ttags
              ((:ccg)) :load-compiled-file nil);v4.0 change

;#+acl2s-startup (er-progn (assign fmt-error-msg "Problem loading the EVALABLE-LD-PRINTING book.~%Please choose \"Recertify ACL2s system books\" under the ACL2s menu and retry after successful recertification.") (value :invisible))
; only load for interactive sessions: 
;#+acl2s-startup (include-book "evalable-ld-printing" :uncertified-okp nil :dir :acl2s-modes :ttags ((:acl2s-interaction)) :load-compiled-file nil);v4.0 change

;; #+acl2s-startup (er-progn (assign fmt-error-msg "Problem loading DataDef+RandomTesting book.~%Please choose \"Recertify ACL2s system books\" under the ACL2s menu and retry after successful recertification.") (value :invisible))
;; (include-book "countereg-gen/top" :uncertified-okp nil :dir :system :load-compiled-file :comp)

#+acl2s-startup (er-progn (assign fmt-error-msg "Problem loading ACL2s customizations book.~%Please choose \"Recertify ACL2s system books\" under the ACL2s menu and retry after successful recertification.") (value :invisible))
(include-book "custom" :dir :acl2s-modes :uncertified-okp nil
                                         :load-compiled-file
                                         :comp :ttags :all)

#+acl2s-startup (er-progn (assign fmt-error-msg "Problem setting up ACL2s Bare Bones mode.") (value :invisible))

;Settings common to all ACL2s modes
(acl2s-common-settings)

; Non-events:
(acl2::set-guard-checking :all)

(defconst *testing-upper-bound* 50)

(defun nth-small-pos-testing (n)
  (declare (xargs :guard (natp n)))
  (let ((n-small (mod n *testing-upper-bound*)))
    (nth-pos n-small)))

(defun nth-small-integer-testing (n)
  (declare (xargs :guard (natp n)))
  (let ((n-small (mod n *testing-upper-bound*)))
    (nth-integer n-small)))

(defun nth-small-nat-testing (n)
  (declare (xargs :guard (natp n)))
  (let ((n-small (mod n *testing-upper-bound*)))
    (nth-nat n-small)))

(defun nth-small-neg-testing (n)
  (declare (xargs :guard (natp n)))
  (let ((n-small (mod n *testing-upper-bound*)))
    (nth-neg n-small)))

(defun nth-small-positive-ratio-testing (n)
  (declare (xargs :guard (natp n)))
  (let ((n-small (mod n *testing-upper-bound*)))
    (nth-positive-ratio n-small)))

(defun nth-small-negative-ratio-testing (n)
  (declare (xargs :guard (natp n)))
  (let ((n-small (mod n *testing-upper-bound*)))
    (nth-negative-ratio n-small)))

(defun nth-small-rational-testing (n)
  (declare (xargs :guard (natp n)))
  (let ((n-small (mod n *testing-upper-bound*)))
    (nth-rational n-small)))

(defun nth-small-positive-rational-testing (n)
  (declare (xargs :guard (natp n)))
  (let ((n-small (mod n *testing-upper-bound*)))
    (nth-positive-rational n-small)))

(defun nth-small-negative-rational-testing (n)
  (declare (xargs :guard (natp n)))
  (let ((n-small (mod n *testing-upper-bound*)))
    (nth-negative-rational n-small)))

(defun nth-small-acl2-number-testing (n)
  (declare (xargs :guard (natp n)))
  (let ((n-small (mod n *testing-upper-bound*)))
    (nth-acl2-number n-small)))

(defun nth-small-complex-rational-testing (n)
  (declare (xargs :guard (natp n)))
  (let ((n-small (mod n *testing-upper-bound*)))
    (nth-complex-rational n-small)))

(defun nth-small-all (n)
  (declare (xargs ;:guard (natp n) ))
                  :verify-guards nil))
  (mv-let (choice seed)
          (defdata::weighted-switch-nat 
            '(1  ;nil
              1  ;t
              1 ;0
              1 ;integer
              1  ;bool
              1  ;charlist
              1  ;sym
              1  ;string
              2  ;char
              1  ;acl2-num
              5 ;rational
              5 ;nat
              5 ;pos
              5  ;rational-list
              2  ;sym-list
              20 ;cons-atom
              5  ;nat-list
              10  ;cons-cons-atom
              1  ;stringlist
              10  ;atom-list
              ) n)
        
    (case choice
          (0 'nil)
          (1 't)
          (2 0)
          (3 (nth-small-integer-testing seed))
          (4 (nth (mod seed 2) *boolean-values*))
          (5 (nth-character-list seed))
          (6 (nth-symbol seed))
          (7 (nth-string seed))
          (8 (nth (mod seed (len *character-values*)) *character-values*))
          (9 (nth-small-acl2-number-testing seed))
          (10 (nth-small-rational-testing seed))
          (11 (nth-small-nat-testing seed))
          (12 (nth-small-pos-testing seed))
          (13 (nth-rational-list seed))
          (14 (nth-symbol-list seed))
          (15 (nth-cons-atom seed))
          (16 (nth-nat-list seed))
          (17 (nth-cons-ca-ca seed))
          (18 (nth-string-list seed))
          (19 (nth-atom-list seed))
          (t 'nil)))) ;this case should not come up


(defdata-testing pos :test-enumerator nth-small-pos-testing)
(defdata-testing integer :test-enumerator nth-small-integer-testing)
(defdata-testing nat :test-enumerator nth-small-nat-testing)
(defdata-testing neg :test-enumerator nth-small-neg-testing)
(defdata-testing positive-ratio :test-enumerator nth-small-positive-ratio-testing)
(defdata-testing negative-ratio :test-enumerator nth-small-negative-ratio-testing)
(defdata-testing rational :test-enumerator nth-small-rational-testing)
(defdata-testing positive-rational :test-enumerator nth-small-positive-rational-testing)
(defdata-testing negative-rational :test-enumerator nth-small-negative-rational-testing)
(defdata-testing acl2-number :test-enumerator nth-small-acl2-number-testing)
(defdata-testing complex-rational :test-enumerator nth-small-complex-rational-testing)
(defdata-testing all :test-enumerator nth-small-all)

(acl2s-defaults :set num-trials 30)

(defpkg "ACL2S BB" ; bare bones
  (union-eq '(t nil 
              ;if ; see macro below
              equal

              defun defunc ;for function definitions

              ; + * unary-- unary-/ < ;see definitions below
              numerator denominator
              rationalp integerp
              
              consp cons  

              cond ; macro: explain
              ;list ; harshrc [21st Aug 2012] commented out to allow (defdata list ...) below

              lambda
              let let* ; macro: explain

              quote

              symbolp symbol-name symbol-package-name
              ;stringp
              ;charp

              check=
              
              trace*
              )
            '()))

(defthm natp-implies-acl2-numberp
  (implies (natp x)
           (acl2-numberp x))
  :rule-classes ((:rewrite)))

(defthm posp-implies-acl2-numberp
  (implies (posp x)
           (acl2-numberp x))
  :rule-classes ((:rewrite)))

(defthm integerp-implies-acl2-numberp
  (implies (integerp x)
           (acl2-numberp x))
  :rule-classes ((:rewrite)))

(defthm rationalp-implies-acl2-numberp2
  (implies (rationalp x)
           (acl2-numberp x))
  :rule-classes ((:rewrite)))

(defthm natp-implies-rationalp
  (implies (natp x)
           (rationalp x))
  :rule-classes ((:rewrite)))

(defthm posp-implies-rationalp
  (implies (posp x)
           (rationalp x))
  :rule-classes ((:rewrite)))

(defthm integerp-implies-rationalp
  (implies (integerp x)
           (rationalp x))
  :rule-classes ((:rewrite)))




(acl2::in-package "ACL2S BB")

(defun acl2s-bb-identity-bool-guard (x)
  (acl2::declare (acl2::xargs :guard (acl2::booleanp x)))
  x)

(acl2::defmacro if (test tb fb)
  `(acl2::if (acl2s-bb-identity-bool-guard ,test) ,tb ,fb))

(acl2::defthm acl2s-bb-identity-bool-guard-backchain
  (acl2::implies (acl2::booleanp x)
                 (equal (acl2s-bb-identity-bool-guard x)
                        x)))

(acl2::defthm acl2s-bb-identity-bool-guard-equal
  (equal (acl2s-bb-identity-bool-guard (equal x y))
         (equal x y)))

(defunc first (x)
  :input-contract (consp x)
  :output-contract t
  (acl2::car x))

(defunc rest (x)
  :input-contract (consp x)
  :output-contract t
  (acl2::cdr x))

(defunc unary-- (x)
  :input-contract (rationalp x)
  :output-contract t
  (acl2::unary-- x))

(defunc unary-/ (x)
  :input-contract (acl2::and (rationalp x) (acl2::not (equal x 0)))
  :output-contract t
  (acl2::unary-/ x))

(defunc < (x y)
  :input-contract (acl2::and (rationalp x) (rationalp y))
  :output-contract (acl2::booleanp (< x y))
  (acl2::< x y))

(defunc + (x y)
  :input-contract (acl2::and (rationalp x) (rationalp y))
  :output-contract (rationalp (+ x y))
  (acl2::binary-+ x y))

(defunc * (x y)
  :input-contract (acl2::and (rationalp x) (rationalp y))
  :output-contract (rationalp (+ x y))
  (acl2::binary-* x y))

(defun my-preprocess (term wrld)
  (acl2::declare (acl2::ignore wrld))
  (acl2::cond ((acl2::and (consp term)
                          (acl2::or 
                           (equal (acl2::car term) 'acl2s-bb-identity-bool-guard)
                           (equal (acl2::car term) 'acl2s-bb-identity-consp-guard)
                           (equal (acl2::car term) 'acl2s-bb-identity-rationalp-guard)
                           (equal (acl2::car term) 'acl2s-bb-identity-rationalp-not-0-guard)))
               (acl2::cadr term))
              
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::/))
               (cons '/ (acl2::cdr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::car))
               (cons 'first (acl2::cdr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::cdr))
               (cons 'rest (acl2::cdr term)))
              
              ;; Due to a call to translate in get-free-vars in testing
              ;; code, the following functions/macros that have not been
              ;; defined in bare-bones should not be
              ;; pre-processed. --harshrc Jan 27 2012
              ;; ((acl2::and (consp term)
              ;;             (equal (acl2::car term) 'acl2::implies))
              ;;  (cons 'implies (acl2::cdr term)))
              
              ;; ((acl2::and (consp term)
              ;;             (consp (acl2::cdr term))
              ;;             (equal (acl2::car term) 'acl2::not)
              ;;             (equal (acl2::caadr term) 'acl2::>))
              ;;  (cons '<= (acl2::cdadr term)))
              ;; ((acl2::and (consp term)
              ;;             (equal (acl2::car term) 'acl2::not))
              ;;  (cons 'not (acl2::cdr term)))
              ;; ((acl2::and (consp term)
              ;;             (equal (acl2::car term) 'acl2::*))
              ;;  (cons '* (acl2::cdr term)))
              ;; ((acl2::and (consp term)
              ;;             (equal (acl2::car term) 'acl2::+))
              ;;  (cons '+ (acl2::cdr term)))
              ;; ((acl2::and (consp term)
              ;;             (equal (acl2::car term) 'acl2::/))
              ;;  (cons '/ (acl2::cdr term)))
              ;; ((acl2::and (consp term)
              ;;             (equal (acl2::car term) 'acl2::-))
              ;;  (cons '- (acl2::cdr term)))
              ;; ((acl2::and (consp term)
              ;;             (equal (acl2::car term) 'acl2::<))
              ;;  (cons '< (acl2::cdr term)))
              ;; ((acl2::and (consp term)
              ;;             (equal (acl2::car term) 'acl2::>))
              ;;  (cons '> (acl2::cdr term)))
              ;; ((acl2::and (consp term)
              ;;             (equal (acl2::car term) 'acl2::acl2-number))
              ;;  (cons 'acl2-number (acl2::cdr term)))
              (t nil)))

; A hack to help proofs go through in this mode.
(acl2::in-theory (acl2::enable rest))

;(acl2::verify-guards acl2::nth-true-list)

; harshrc 29 March 2012 -- added nth-list for Pete
;(acl2::defdata |ACL2S BB|::list acl2::true-list)
(defun listp (x) (acl2::declare (acl2::xargs :guard T)) (acl2::true-listp x))
(defun nth-list (n) (acl2::declare (acl2::xargs :guard (acl2::natp n))) (acl2::nth-true-list n))
(acl2::register-custom-type list t nth-list listp)

(COMMON-LISP::DEFMACRO |ACL2S BB|::LIST (COMMON-LISP::&REST ACL2::ARGS)
                       (ACL2::LIST-MACRO ACL2::ARGS))

(acl2::table acl2::user-defined-functions-table
             'acl2::untranslate-preprocess
             'my-preprocess)

;;Settings specific to this mode(copied from R&I mode)
(acl2::in-package "ACL2")
(set-irrelevant-formals-ok :warn)
(set-bogus-mutual-recursion-ok :warn)
(set-ignore-ok :warn)

(set-backchain-limit '(50 100))
(set-rewrite-stack-limit 500)
(acl2::acl2s-defaults :set acl2::subgoal-timeout 60)
(acl2::acl2s-defaults :set acl2::defunc-timeout 200) 


;for beginner users dont be strict in admitting defunc
;(acl2::acl2s-defaults :set acl2::defunc-strict 0)  
(acl2s-defaults :set num-trials 500)

;(assign evalable-ld-printingp t)
;(assign evalable-printing-abstractions '(list cons))
;(assign triple-print-prefix "; ")

(cw "~@0Bare Bones mode loaded.~%~@1"
    #+acl2s-startup "${NoMoReSnIp}$~%" #-acl2s-startup ""
    #+acl2s-startup "${SnIpMeHeRe}$~%" #-acl2s-startup "")

(acl2::in-package "ACL2S BB")


; ***************** END INITIALIZATION FOR ACL2s BB MODE ******************* ;

;$ACL2s-SMode$;Bare Bones
#|

CS 2800 Homework 2 - Spring 2014

Student names: Charles Hoyt, Molly White

For this homework you will need to use ACL2s.

Technical instructions:

- open this file in ACL2s as hw02.lisp

- set the session mode to "Bare Bones"

- insert your solutions into this file where indicated (for instance as "...")

- only add to the file. Do not remove or comment out anything pre-existing.

- make sure the entire file is accepted by ACL2s in Bare Bones mode. In
  particular, there must be no "..." left in the code. If you don't finish
  some problems, comment them out. The same is true for any English text
  that you may add. This file already contains many comments, so you can
  see what the syntax is.

For each function definition, you must provide both contracts and a body.

You must also ALWAYS supply your own tests. This is in addition to the
tests sometimes provided. Make sure you produce sufficiently many new
test cases. This means: cover at least the possible scenarios according
to the data definitions of the involved types. For example, a function
taking two lists should have at least 4 tests (each list being empty and
non-empty).

Beyond that, the number of tests should reflect the difficulty of the
function. For very simple ones, the above coverage of the data definition
cases may be sufficient. For complex functions with numerical output, you
want to test whether it produces the correct output on a reasonable
number if inputs.

Use good judgment. For unreasonably few test cases we will deduct points.

We will use ACL2s' check= facility for tests. This function is similar to
the equal function, except that if the evaluations of the two arguments
passed to it are not equal, the function call results in an error message
(rather than returning nil, as in the case of equal). Thus, if any call to
check= results in "not equal", your file will be rejected.

|#

; Recall, or study, the following elementary functions:

(defunc booleanp (x)
  :input-contract t
  :output-contract (booleanp (booleanp x))
  (if (equal x t)
      t
    (equal x nil)))

(defunc and (a b)
  :input-contract (if (booleanp a) (booleanp b) nil)
  :output-contract (booleanp (and a b))
  (if a b nil))

(defunc not (a)
  :input-contract (booleanp a)
  :output-contract (booleanp (not a))
  (if a nil t))

(defunc / (a b)
  :input-contract (and (and (rationalp a) (rationalp b)) (not (equal b 0)))
  :output-contract (rationalp (/ a b))
  (* a (unary-/ b)))

; This function computes the length of "anything":
; if () or not a list, the length is 0.
(defunc len (l)
  :input-contract t
  :output-contract (and (integerp (len l)) (not (< (len l) 0)))
  (if (not (consp l))
    0
    (+ 1 (len (rest l)))))

; For the following tasks, recall that the Bare Bones mode in ACL2s has
; built-in the Boolean constants t and nil (for true and false) and the
; functions if and equal.

; Define
; or: Boolean x Boolean -> Boolean

; (or a b) implements the Boolean "a or b".

(defunc or (a b)
  :input-contract (and (booleanp a) (booleanp b))
  :output-contract (booleanp (or a b))
  (if a t b))

(check= (or t   nil) t)
(check= (or nil nil) nil)
(check= (or nil t) t)
(check= (or t t) t)

; What happens if you evaluate (or 1 nil)? Try it.

#|

Throws an error because 1 doesn't satisfy the input contract

|#

; When ACL2s reports a "guard" violation, as it did when you tried
; evaluating (or 1 nil), we violated the contract of a function.

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Define
; andand: Boolean x Boolean x Boolean -> Boolean

; (andand a b c) implements "a and b and c".

; Use the and function.

(defunc andand (a b c)
  :input-contract (and (booleanp a) (and (booleanp b) (booleanp c)))
  :output-contract (booleanp (andand a b c))
  (and a (and b c)))

(check= (andand nil nil nil) nil)
(check= (andand nil nil t) nil)
(check= (andand nil t nil) nil)
(check= (andand nil t t) nil)
(check= (andand t nil nil) nil)
(check= (andand t nil t) nil)
(check= (andand t t nil) nil)
(check= (andand t t t) t)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Provide an example of an expression containing an if that has three
; arguments as required for if but whose evaluation leads to a contract
; violation.

; The solution must be given inside a comment, to prevent ACL2 from
; complaining about your expression.

#| 
   (if 1 2 3) - 1 is an invalid argument since it isn't a boolean
|#
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Define
; but-not: Boolean x Boolean -> Boolean

; (but-not a b) implements the Boolean "a and not b". In English we often
; say: "a but not b".

(defunc but-not (a b)
  :input-contract (and (booleanp a) (booleanp b))
  :output-contract (booleanp (but-not a b))
  (and a (not b)))

(check= (but-not nil nil) nil)
(check= (but-not nil t) nil)
(check= (but-not t   nil) t)
(check= (but-not t t) nil)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Define
; iff: Boolean x Boolean -> Boolean

; (iff a b) implements the Boolean "a equivalent to b".

(defunc iff (a b)
  :input-contract (and (booleanp a) (booleanp b))
  :output-contract (booleanp (iff a b))
  (if a b (not b)))

(check= (iff nil nil) t)
(check= (iff nil t) nil)
(check= (iff t nil) nil)
(check= (iff t   t)   t)

; Provide an example of an expression containing 'equal' whose evaluation
; does not lead to a contract violation, but when we replace the equal with
; 'iff', evaluation does lead to a contract violation.

; (equal 1 1) works but (iff 1 1) fails to uphold the contract with these arguments

; Can you provide an example of an expression containing 'iff' whose
; evaluation does not lead to a contract violation, but when we replace the
; iff with 'equal', evaluation leads to a contract violation? If so,
; provide an example. If not, explain.

; No, since the contract of equal is more general than iff (All vs. Boolean)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Define
; nor: Boolean x Boolean -> Boolean

; (nor a b) implements the negation of the Boolean "or".

(defunc nor (a b)
  :input-contract (and (booleanp a) (booleanp b))
  :output-contract (booleanp (nor a b))
  (not (or a b)))

(check= (nor nil nil) t)
(check= (nor nil t) nil)
(check= (nor t nil) nil)
(check= (nor t   t)   nil)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Define
; major : Boolean x Boolean x Boolean -> Boolean

; (major a b c) is t if the majority of a, b, c is t, and nil otherwise.

(defunc major (a b c)
  :input-contract (andand (booleanp a) (booleanp b) (booleanp c))
  :output-contract (booleanp (major a b c))
  (or (and a b)
      (or (and a c)
          (and b c))))

(check= (major nil nil nil) nil)
(check= (major nil nil t) nil)
(check= (major nil t nil) nil)
(check= (major nil t t) t)
(check= (major t nil nil) nil)
(check= (major t nil t) t)
(check= (major t t nil) t)
(check= (major t t t) t)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Define
; exactly-one: Boolean x Boolean -> Boolean

; (exactly-one a b) is t if exactly one of a and b is t, and nil otherwise.

(defunc exactly-one (a b)
  :input-contract (and (booleanp a) (booleanp b))
  :output-contract (booleanp (exactly-one a b))
  (equal a (not b)))

(check= (exactly-one t   t) nil)
(check= (exactly-one t nil) t)
(check= (exactly-one nil t) t)
(check= (exactly-one t t) nil)

; For the following tasks, recall that the Bare Bones mode in ACL2s has
; built-in types integer and rational, and the functions

; + * unary-- unary-/ < numerator denominator rationalp integerp

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Define
; natp: All -> Boolean

; (natp a) returns t if a is a natural number (which includes 0), nil otherwise.

(defunc natp (a)
  :input-contract t
  :output-contract (booleanp (natp a))
  (if (integerp a) (< -1 a) nil))

(check= (natp 0) t)
(check= (natp 1) t)
(check= (natp -1) nil)
(check= (natp 5/2) nil)
(check= (natp '(a b c)) nil)
(check= (natp t) nil)
(check= (natp nil) nil)

; Define
; <= : Rational x Rational -> Boolean

; (<= a b) is t if a <= b, otherwise it is nil.

(defunc <= (a b)
  :input-contract (and (rationalp a) (rationalp b))
  :output-contract (booleanp (<= a b))
  (or (equal a b) (< a b)))

(check= (<= -1 0) t)
(check= (<= 1 0) nil)
(check= (<= 0 0) t)
(check= (<= 1 1) t)
(check= (<= 5/3 5/2) t)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Define
; <=<= : Rational x Rational x Rational -> Boolean

; (<= a b c) is t if a <= b <= c, otherwise it is nil.

(defunc <=<= (a b c)
  :input-contract (andand (rationalp a) (rationalp b) (rationalp c))
  :output-contract (booleanp (<=<= a b c))
  (if (<= a b) (<= b c) nil))

(check= (<=<= 1/4 1/3 1/2) t)
(check= (<=<= 1/3 1/4 1/2) nil)
(check= (<=<= 1/3 1/2 1/4) nil)
(check= (<=<= 1/2 1/3 1/4) nil)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Define
; > : Rational x Rational -> Boolean

; (> a b) is t if a > b, otherwise it is nil.

(defunc > (a b)
  :input-contract (and (rationalp a) (rationalp b))
  :output-contract (booleanp (> a b))
  (not (<= a b)))

(check= (> 1 0) t)
(check= (> 0 1) nil)
(check= (> 1/3 0) t)
(check= (> 0 1/3) nil)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Using only the > function (defined above) and Booleans, but not using
; equal or < , define

; >= : Rational x Rational -> Boolean

; (>= a b) is t if a >= b, otherwise it is nil.

(defunc >= (a b)
  :input-contract (and (rationalp a) (rationalp b))
  :output-contract (booleanp (>= a b))
  (not (> b a)))

(check= (>= -4 -2) nil)
(check= (>= -4 -4) t)
(check= (>= -4/3 -2/3) nil)
(check= (>= -4/3 -4/3) t)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Define
; negp: All -> Boolean

; (negp a) is t if a is a negative integer, otherwise it is nil.

; Note that negp is a recognizer.

(defunc negp (a)
  :input-contract t
  :output-contract (booleanp (negp a))
  (if (integerp a) (< a 0) nil))

(check= (negp -3) t)
(check= (negp -3/4) nil)
(check= (negp 1) nil)
(check= (negp 1/3) nil)
(check= (negp 0) nil)
(check= (negp t) nil)
(check= (negp nil) nil)
(check= (negp '(a b c)) nil)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Define
; pos-rationalp: All -> Boolean

; (pos-rationalp a) is t if a is a positive rational, otherwise it is nil.

(defunc pos-rationalp (a)
  :input-contract t
  :output-contract (booleanp (pos-rationalp a))
  (if (rationalp a) (< 0 a) nil))

(check= (pos-rationalp 3/4) t)
(check= (pos-rationalp 3) t)
(check= (pos-rationalp -3) nil)
(check= (pos-rationalp -3/4) nil)
(check= (pos-rationalp 0) nil)
(check= (pos-rationalp '(a b c)) nil)
(check= (pos-rationalp t) nil)
(check= (pos-rationalp nil) nil)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Define
; negrat: Rational -> Boolean

; (negrat a) is t if a is a negative rational, otherwise it is nil.

; Note that negrat is _not_ a recognizer! How can we tell?

; It's in the signature - a recognizer would take in an All and the input 
; contract would be t

(defunc negrat (a)
  :input-contract (rationalp a)
  :output-contract (booleanp (negrat a))
  (< a 0))

(check= (negrat -3/4) t)
(check= (negrat -3) t)
(check= (negrat 0) nil)
(check= (negrat 1) nil)
(check= (negrat 1/3) nil)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Define
; abs: Rational -> Rational

; (abs a) returns the absolute value of the rational number a. (Consult a
; math book or Wikipedia if you don't know what the absolute value of a
; number is.)

; Use the negrat function.

(defunc abs (a)
  :input-contract (rationalp a)
  :output-contract (rationalp (abs a))
  (if (negrat a) (unary-- a) a))

(check= (abs 3) 3)
(check= (abs 3/4) 3/4)
(check= (abs 0) 0)
(check= (abs -3/4) 3/4)
(check= (abs -4) 4)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Define
; - : Rational x Rational -> Rational

; (- a b) returns a - b

(defunc - (a b)
  :input-contract (and (rationalp a) (rationalp b))
  :output-contract (rationalp (- a b))
  (+ a (unary-- b)))

(check= (- 5 1) 4)
(check= (- 5 0) 5)
(check= (- 5 -5) 10)
(check= (- 1 1/3) 2/3)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Define
; oddp: All -> Boolean

; (oddp a) is t if a is an odd integer, otherwise it is nil.

(defunc oddp (a)
  :input-contract t
  :output-contract (booleanp (oddp a))
  (if (integerp a) (not (integerp (/ a 2))) nil))

(check= (oddp 5) t)
(check= (oddp -5) t)
(check= (oddp 10/2) t)
(check= (oddp 5/2) nil)
(check= (oddp 2) nil)
(check= (oddp -4) nil)
(check= (oddp t) nil)
(check= (oddp nil) nil)
(check= (oddp '(a b c)) nil)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Define
; divides: Nat x Nat-{0} -> Boolean

; (divides x y) is t iff x is divisible by y.

(defunc divides (x y)
  :input-contract (andand (natp x) (natp y) (not (equal 0 y)))
  :output-contract (booleanp (divides x y))
  (integerp (/ x y)))

(check= (divides 10 2) t)
(check= (divides 10 3) nil)
(check= (divides 0 3) t)

; For the following tasks, recall that the Bare Bones mode in ACL2s has
; built-in types list and cons. Important built-in functions include:

;   cons consp listp first rest

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Define
; endp: List -> Boolean

; (endp l) returns t if l is (), nil otherwise.

(defunc endp (l)
  :input-contract (listp l)
  :output-contract (booleanp (endp l))
  (equal l ()))

(check= (endp ()) t)
(check= (endp '(a b c)) nil)

; Define the recognizer
; atom: All -> Boolean

; (atom x) returns t if x is not a cons, nil otherwise.

(defunc atom (x)
  :input-contract t
  :output-contract (booleanp (atom x))
  (not (consp x)))

(check= (atom t) t)
(check= (atom nil) t)
(check= (atom 5) t)
(check= (atom -5) t)
(check= (atom 5/3) t)
(check= (atom -5/3) t)
(check= (atom 0) t)
(check= (atom '(a b c)) nil)

; Define
; odd-len: List -> Boolean

; (odd-len l) returns true if the list l has odd length, nil otherwise.

(defunc odd-len (l)
  :input-contract (listp l)
  :output-contract (booleanp (odd-len l))
  (oddp (len l)))

(check= (odd-len ()) nil)
(check= (odd-len '(1)) t)
(check= (odd-len '(1 2)) nil)
(check= (odd-len '(1 2 3)) t)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Define
; has-threep: List -> Boolean

; (has-threep a) returns true if the list a has length at least 3, nil otherwise.

(defunc has-three (a)
  :input-contract (listp a)
  :output-contract (booleanp (has-three a))
  (<= 3 (len a)))

(check= (has-three '()) nil)
(check= (has-three '(a)) nil)
(check= (has-three '(a b)) nil)
(check= (has-three '(a b c)) t)
(check= (has-three '(a b c d)) t)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Define
; n-th: Nat x List -> All

; (n-th n l) returns the nth element of list l, COUNTING FROM 0.
; If l does not have an nth element, nil is returned.

(defunc n-th (n l)
  :input-contract (and (natp n) (listp l))
  :output-contract t
  (if (endp l)
      nil
      (if (equal 0 n)
          (first l)
          (n-th (- n 1) (rest l)))))

(check= (n-th 0  '(1 2)) 1)
(check= (n-th 1  '(1 2)) 2)
(check= (n-th 10 '(1 2)) nil)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Define
; elem: All x List -> Boolean

; (elem a X) returns t iff a is an element of list X.

(defunc elem (a X)
  :input-contract (listp x)
  :output-contract (booleanp (elem a X))
  (if (endp X) 
    nil
    (or (equal a (first X))
      (elem a (rest X)))))

(check= (elem 3 '(1 2 3 4)) t)
(check= (elem 2 '(1 3 3 4)) nil)
(check= (elem 'a '(1 3 3 4)) nil)#|ACL2s-ToDo-Line|#


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; The following expressions will result in errors if evaluated at the
; prompt. Write a brief explanation after the colon. Do NOT just copy the
; error message ACL2s prints. Write down an explanation that is consistent
; with what we learned in class.

; For example,

; x : ...

; should become

; x : x is a variable outside any function definition ("global"), which is not allowed.

; Do not remove the semicolon in front of these expressions; otherwise your
; file will not be accepted by ACL2.

; ('1) : tries to interpret '1 as a function
; ((list)) : tries to interpret (list), which evaluates to NIL, as a function call
; (quote) : to call quote, you need one argument
; (if false 2 3) : tries to interpret false as a symbol, which doesn't exist in the global scope. Should probably be nil instead
; ((+ 1 2)) : evaluates (+ 1 2) to 3, then fails because it is not a symbol
; list 1 2 : tries to evaluate list as an expression instead of as a function application because of missing parentheses