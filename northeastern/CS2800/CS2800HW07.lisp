; **************** BEGIN INITIALIZATION FOR ACL2s B MODE ****************** ;
; (Nothing to see here!  Your actual file is after this initialization code);

#|
Pete Manolios
Fri Jan 27 09:39:00 EST 2012
----------------------------

Made changes for spring 2012.


Pete Manolios
Thu Jan 27 18:53:33 EST 2011
----------------------------

The Beginner level is the next level after Bare Bones level.

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
(include-book "ccg" :uncertified-okp nil :dir :acl2s-modes :ttags ((:ccg)) :load-compiled-file nil);v4.0 change

;; #+acl2s-startup (er-progn (assign fmt-error-msg "Problem loading DataDef+RandomTesting book.~%Please choose \"Recertify ACL2s system books\" under the ACL2s menu and retry after successful recertification.") (value :invisible))
;; (include-book "countereg-gen/top" :uncertified-okp nil :dir :system :load-compiled-file :comp)

#+acl2s-startup (er-progn (assign fmt-error-msg "Problem loading ACL2s customizations book.~%Please choose \"Recertify ACL2s system books\" under the ACL2s menu and retry after successful recertification.") (value :invisible))
(include-book "custom" :dir :acl2s-modes :uncertified-okp nil
                                         :load-compiled-file
                                         :comp :ttags :all)

#+acl2s-startup (er-progn (assign fmt-error-msg "Problem setting up ACL2s Beginner mode.") (value :invisible))


;Settings common to all ACL2s modes
(acl2s-common-settings)

; Non-events:
(acl2::set-guard-checking :all)

(defconst *testing-upper-bound* 1000)  

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
              2  ;cons-atom ;changed Jan 15th 2013 from 20
              5  ;nat-list
              1  ;cons-cons-atom ;changed Jan 15th 2013 from 10
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

(acl2s-defaults :set num-trials 50)

(defpkg "ACL2S B" ; beginner
  (union-eq '(t nil 
              ;if ; see macro below
              equal

              defun defunc ;for function definitions

              ; + * unary-- unary-/ < ; see definitions below
              numerator denominator
              rationalp integerp

              consp cons ; car cdr

              cond ; macro: explain
              list ; macro: explain

              lambda
              let let* ; macro: explain

              quote

              symbolp symbol-name symbol-package-name
              ;stringp
              ;charp

              check=

              and or iff implies not booleanp 
              ;+ * 
              / posp negp natp <= > >= zp - atom 
              ; true-listp 
              endp 
              ;caar cadr cdar cddr 
              ;caaar caadr cadar caddr cdaar cdadr cddar cdddr
              ;caaaar caaadr caadar caaddr cadaar cadadr caddar cadddr
              ;cdaaar cdaadr cdadar cdaddr cddaar cddadr cdddar cddddr
              
              
              defdata nat string pos rational integer boolean all neg
              acl2-number true-list char symbol oneof listof enum record
              ;; i need them for defdata why?
              
              trace*

              defthm thm defconst in-package
              test?
              acl2s-defaults testing-enabled 
              verbosity-level defunc-verbosity-level
              num-trials num-counterexamples num-witnesses
              subgoal-timeout defunc-timeout defunc-strict)
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


(acl2::in-package "ACL2S B")

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

(defunc second (x)
  :input-contract (and (consp x) (consp (rest x)))
  :output-contract t
  (acl2::cadr x))

(defunc third (x)
  :input-contract (and (consp x) (consp (rest x)) (consp (rest (rest x))))
  :output-contract t
  (acl2::caddr x))

(defunc fourth (x)
  :input-contract (and (consp x) (consp (rest x)) 
                       (consp (rest (rest x)))
                       (consp (rest (rest (rest x)))))
  :output-contract t
  (acl2::cadddr x))

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
                          (equal (acl2::car term) 'acl2::implies))
               (cons 'implies (acl2::cdr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::/))
               (cons '/ (acl2::cdr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::car))
               (cons 'first (acl2::cdr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::cdr))
               (cons 'rest (acl2::cdr term)))
              ((acl2::and (consp term)
                          (consp (acl2::cdr term))
                          (equal (acl2::car term) 'acl2::not)
                          (equal (acl2::caadr term) 'acl2::>))
               (cons '<= (acl2::cdadr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::not))
               (cons 'not (acl2::cdr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::*))
               (cons '* (acl2::cdr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::+))
               (cons '+ (acl2::cdr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::/))
               (cons '/ (acl2::cdr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::-))
               (cons '- (acl2::cdr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::<))
               (cons '< (acl2::cdr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::>))
               (cons '> (acl2::cdr term)))
              ((acl2::and (consp term)
                          (equal (acl2::car term) 'acl2::acl2-number))
               (cons 'acl2-number (acl2::cdr term)))
              (t nil)))

; A hack to help proofs go through in this mode.
(acl2::in-theory (acl2::enable rest))

(acl2::table acl2::user-defined-functions-table
             'acl2::untranslate-preprocess
             'my-preprocess)

(defunc len (a) 
  :input-contract t 
  :output-contract (natp (len a))
  (if (atom a)
      0
    (+ 1 (len (rest a)))))

(defthm intp-len 
  (integerp (len x))
  :rule-classes ((:type-prescription) (:rewrite)))

(acl2::defmacro listp (a)
  `(acl2::true-listp ,a))

(defunc append (a b) 
  :input-contract (and (listp a) (listp b))
  :output-contract (and (listp (append a b))
                        (equal (len (append a b)) (+ (len a) (len b))))
  (acl2::append a b))

(defthm append-length
  (equal (len (acl2::append a b))
         (+ (len a) (len b))))

(defunc app (a b) 
  :input-contract (and (listp a) (listp b))
  :output-contract (and (listp (app a b))
                        (equal (len (app a b)) (+ (len a) (len b))))
  (acl2::append a b))

(defunc rev (a) 
  :input-contract (listp a) 
  :output-contract (and (listp (rev a))
                        ;(equal (len (rev a)) (len a))
                        )
  (if (endp a)
      nil
    (append (rev (rest a)) (list (first a)))))

(defunc in (a X) 
  :input-contract (listp x)
  :output-contract (booleanp (in a X))
  (if (endp x)
      nil
    (or (equal a (first X))
        (in a (rest X)))))

(defunc remove-dups (a) 
  :input-contract (listp a) 
  :output-contract (listp (remove-dups a))
  (if (endp a)
      nil
    (if (in (first a) (rest a))
        (remove-dups (rest a))
      (cons (first a) (remove-dups (rest a))))))

(defunc nth (n l)
  :input-contract (and (natp n) (listp l))
  :output-contract t
  (if (endp l)
      nil
    (if (zp n)
        (first l)
      (nth (- n 1) (rest l)))))

(defunc nthrest (n l)
  :input-contract (and (natp n) (listp l))
  :output-contract (listp (nthrest n l))
  (if (endp l)
      nil
    (if (zp n)
        l
      (nthrest (- n 1) (rest l)))))

(defthm natp-acl2-len-tp 
  (natp (acl2::len x))
  :rule-classes ((:type-prescription) (:rewrite)))

(defthm integerp-acl2-len-tp 
  (integerp (acl2::len x))
  :rule-classes ((:type-prescription) (:rewrite)))

#|
(defunc string-len (l)
  :input-contract (stringp l)
  :output-contract (natp (string-len l))
  (acl2::length l))
|#


; harshrc 29 March 2012 -- added nth-list for Pete
(defun nth-list (n)
  (acl2::nth-true-list n))



;;Settings specific to this mode(copied from R&I mode)
(acl2::in-package "ACL2")
(set-backchain-limit '(50 100))
(set-rewrite-stack-limit 500)
(acl2s-defaults :set subgoal-timeout 60)
(acl2s-defaults :set defunc-timeout 200) 

(set-irrelevant-formals-ok :warn)
(set-bogus-mutual-recursion-ok :warn)
(set-ignore-ok :warn)

;for beginner users dont be strict in admitting defunc
;(acl2::acl2s-defaults :set acl2::defunc-strict 0)  
(acl2s-defaults :set num-trials 500)

;(assign evalable-ld-printingp t)
;(assign evalable-printing-abstractions '(list cons))
;(assign triple-print-prefix "; ")

(cw "~@0Beginner mode loaded.~%~@1"
    #+acl2s-startup "${NoMoReSnIp}$~%" #-acl2s-startup ""
    #+acl2s-startup "${SnIpMeHeRe}$~%" #-acl2s-startup "")


(acl2::in-package "ACL2S B")

; ***************** END INITIALIZATION FOR ACL2s B MODE ******************* ;
;$ACL2s-SMode$;Beginner
#|

CS 2800 Homework 7 - Spring 2014

Student names: PUT BOTH NAMES HERE

Technical instructions:

- open this file in ACL2s as hw07.lisp

- set the session mode to "Beginner"

- insert your solutions into this file where indicated (for instance as "...")

- only add to the file. Do not remove or comment out anything pre-existing.

- make sure the entire file is accepted by ACL2s in Beginner mode. In
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

About Beginner mode:

In contrast to the Bare Bones mode, many elementary functions are built in,
including many Boolean functions (and, or, not, recognizers), many
arithmetic functions (<= etc), and many list related functions (e.g. 'in'
for list membership). If in doubt, try it out. If you need a function that
does not seem to be built in to Beginner mode, you must define it.

You can type something like

:doc <name>

at the prompt, to get basic help about function <name>, provided it is defined.

Also note that some functions are defined in Beginner mode to be more
flexible than the way we defined them in Bare Bones mode. For example, the
Boolean functions and, or can now take any number of arguments:

(check= (or (booleanp (+ 1 2)) (symbolp nil) (equal (/ 1 3) 0)) t)

|#

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Part A: Termination
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

#|

Remember to switch from BEGINNER mode to (full-featured) ACL2s mode as
mentioned at the beginning of this problem set.

In this problem you will investigate the termination of a function that
operates on natural numbers _modulo n_ : this means we only consider
natural numbers i in the range 0 <= i < n (note that n is excluded); there
are exactly n such numbers. Arithmetic operations such as addition "wrap
around", as we will see.

We will implicitly introduce the datatype nat-mod-n of such restricted
natural numbers. This is a _parameterized_ datatype: it depends on the
parameter n.

|#

; Define a recognizer nat-mod-n-p for the datatype nat-mod-n. Since the
; type is parameterized by n, so is the recognizer, which hence takes not
; only some a of type All as input, but also n of type Nat:

; nat-mod-n-p : All x Nat -> Boolean

; (nat-mod-n-p a n) returns T iff a is a natural number less than n.

; Carefully think about the contracts; the signature given above says it all.
;
; Define 3 tests.

(defunc nat-mod-n-p (a n)
  :input-contract (natp n)
  :output-contract (booleanp (nat-mod-n-p a n))
    (if (natp a) (and (<= 0 a) (< a n)) nil))

(check= t (nat-mod-n-p 0 6))
(check= t (nat-mod-n-p 5 6))
(check= nil (nat-mod-n-p 6 6))
(check= nil (nat-mod-n-p 7 6))
(check= nil (nat-mod-n-p 2/3 6))

; For example, suppose we define the concrete type of numbers 0,1,2,3:

(defdata mod-4 (oneof 0 1 2 3))

; then the following theorem holds (and ACL2s should be able to prove it
; if you defined nat-mod-n-p correctly):

(thm (equal (mod-4p a) (nat-mod-n-p a 4)))

; What does the theorem state?

; This theorem states that the nat-mod-n-p function when given the parameter n=4 
; acts the same as the recognizer for the enumeration of the Mod 4 set

; Now define a function

; plus-mod-n : nat-mod-n x nat-mod-n x Nat -> nat-mod-n

; such that (plus-mod-n a b n) returns the sum of a and b modulo n. That is, it
; returns the sum a+b, unless that sum equals or exceeds n, in which case
; the result "wraps around": the result is the remainder of a+b when
; divided by n. See examples below.

; Hints: (i) There is a very simple way of defining this function; you
; don't even need any kind of remainder function. (ii) Use let to store the
; natural number a+b in a local variable.

; Define 3 more tests.
;
; Note the way the input contract is written. Make sure you understand why.

(defunc plus-mod-n (a b n)
  :input-contract (if (natp n) (and (nat-mod-n-p a n) (nat-mod-n-p b n)) nil)
  :output-contract (nat-mod-n-p (plus-mod-n a b n) n)
  (if (nat-mod-n-p (+ a b) n)
    (+ a b)
    (- (+ a b) n)))

(check= (plus-mod-n 2 3 10) 5)
(check= (plus-mod-n 2 3  5) 0)
(check= (plus-mod-n 2 3  4) 1)

(check= (plus-mod-n 0 0 7) 0)
(check= (plus-mod-n 1 6 7) 0)
(check= (plus-mod-n 6 6 7) 5)
(check= (plus-mod-n 1 2 7) 3)
;Not allowed: (check= (plus-mod-n 0 7 7) 0)


; Now define an analogous function

; minus-mod-n : nat-mod-n x nat-mod-n x Nat -> nat-mod-n

; such that (minus-mod-n a b n) returns a-b modulo n. See tests below, and define 3 more.

; Think about what happens when a-b is negative.

(defunc minus-mod-n (a b n)
  :input-contract (if (natp n) (and (nat-mod-n-p a n) (nat-mod-n-p b n)) nil)
  :output-contract (nat-mod-n-p (minus-mod-n a b n) n)
  (if (nat-mod-n-p (- a b) n)
    (- a b)
    (+ (- a b) n)))

(check= (minus-mod-n 5 3 10) 2)
(check= (minus-mod-n 3 5 10) 8)
(check= (minus-mod-n 3 5  6) 4)

(check= (minus-mod-n 1 0 5) 1)
(check= (minus-mod-n 1 1 5) 0)
(check= (minus-mod-n 1 2 5) 4)
(check= (minus-mod-n 1 3 5) 3)
(check= (minus-mod-n 1 4 5) 2)


; We are now switching into program mode, as we are about to define a
; function that ACL2 has difficulties proving terminating (for good reasons):

:program

(acl2::acl2s-defaults :set acl2::testing-enabled nil)

; Write a function

; ring-rendezvous : nat-mod-n x nat-mod-n x Nat -> Boolean

; such that ring-rendezvous(a,b,n) implements the following loop:

; as long as a and b are not equal:
;   decrement a by 1 modulo n
;   increment b by 1 modulo n
; end of loop

; This can be accomplished as follows. Your function should return t if a
; and b are equal; this simply terminates the loop. Otherwise, the function
; is called recursively, replacing the arguments a and b according to the
; decrement/increment expressions shown above. Note that you have to use
; the plus-mod-n and minus-mod-n functions defined before to properly
; implement decrement and increment modulo n (do not use the built-in
; functions - and +). Parameter n always stays the same.

(defunc ring-rendezvous (a b n)
  :input-contract (if (natp n) (and (nat-mod-n-p a n) (nat-mod-n-p b n)) nil)
  :output-contract (booleanp (ring-rendezvous a b n))
  (if (equal a b)
    t
    (ring-rendezvous (minus-mod-n a 1 n) (plus-mod-n b 1 n) n)))

(defunc evenp (x)
  :input-contract t
  :output-contract (booleanp (evenp x))
  (if (integerp x) (integerp (/ x 2)) nil))


; This function is accepted in program mode. (You may want to convince
; yourself that the function is not accepted without the :program switch.)

; Now turn off guard checking (which creates a warning in subsequent test
; cases that makes them unreadable):

(acl2::set-guard-checking :none)

; Now try out a few cases, including:

; (ring-rendezvous 2 3 7) = (ring-rendezvous 1 4 7) = (ring-rendezvous 0 5 7) = (ring-rendezvous 6 6 7)    

; (ring-rendezvous 3 4 7) = (ring-rendezvous 2 5 7) = (ring-rendezvous 1 6 7) = (ring-rendezvous 0 0 7)

; (ring-rendezvous 2 4 8) = (ring-rendezvous 1 5 8) = (ring-rendezvous 0 6 8) = (ring-rendezvous 7 7 8)

; (ring-rendezvous 3 4 6) = (ring-rendezvous 2 5 6) = (ring-rendezvous 1 0 6) = (ring-rendezvous 0 1 6)
;                         = (ring-rendezvous 5 2 6) = (ring-rendezvous 4 3 6) = (ring-rendezvous 3 4 6)
; doesn't terminate - eventually tries to call the same as the original.

; (do not remove the comment sign in front of these tests, or your file may
; not be accepted).

; In the comments _behind_ the tests above, state your findings regarding
; what happens in these function calls.

; Now turn on function call tracing for ring-rendezvous:

(acl2::trace! ring-rendezvous)

(ring-rendezvous 2 3 7) 
(ring-rendezvous 3 4 7) 
(ring-rendezvous 2 4 8)
(ring-rendezvous 5 2 7)#|ACL2s-ToDo-Line|#

;(ring-rendezvous 3 4 6)

; and run the above test cases again.

; Looking at these test cases, come up with conjectures for restrictions on
; the inputs (a b n) such that the function terminates.

; Conversely, what happens in the call (ring-rendezvous 3 4 6) ? Show that the
; function does not terminate on this input.

; Try to prove your conjectures from above using measure functions, or try
; to make your conjectures tight, i.e.: the function terminates exactly if

; measure function
(defunc m-rend (a b n)
  :input-contract (if (natp n) (and (nat-mod-n-p a n) (nat-mod-n-p b n)) nil)
  :output-contract (integer (m-rend a b n))
  (+ (if (> a b) n 0) (minus-mod-n a b n)))
  
; ic => (< (m-rend a b n)
;          (m-rend (minus-mod-n a 1 n) (plus-mod-n b 1 n) n))

; the value (minus-mod-n a b n) represents twice the number 
; of recursions left before termination occurs 

; this is correct because when a=b in k recursions,
; (a - k) mod n = (b + k) mod n
; => (a - b) mod n = 2k
; there must be some natural number k to satisfy this equation
;   or the call will not terminate. 

; Tight conditions: 
; ic ^ n is even => terminates if a + b is even
; ic ^ n is odd => always terminates

#|
(thm (implies (and  (natp n) 
                    (nat-mod-n-p a n) 
                    (nat-mod-n-p b n)
                    (or (evenp n) (evenp (plus-mod-n a b n))))
              (ring-rendezvous a b n)))
|#

; Now write another function
;
; ring-lap : nat-mod-n x nat-mod-n x Nat -> Boolean
;
; such that ring-lap(a,b,n) implements the following loop:
;
; as long as a and b are not equal:
;   increment a by 1 modulo n
;   increment b by 2 modulo n
; end of loop

; This can be accomplished as follows. Your function should return t if a
; and b are equal; this simply terminates the loop. Otherwise, the function
; is called recursively, replacing the arguments a and b by the increment
; expressions shown above. Note that you may reuse the plus-mod-n function
; defined before to properly implement increment modulo n (do not use the
; built-in functions - and +). Parameter n always stays the same.

(defunc ring-lap (a b n)
  :input-contract (if (natp n) (and (nat-mod-n-p a n) (nat-mod-n-p b n)) nil)
  :output-contract (booleanp (ring-lap a b n))
  (if (equal a b)
    t
    (ring-lap (plus-mod-n a 1 n) (plus-mod-n b 2 n) n)))

; This function is accepted in program mode. (You may want to convince
; yourself that the function is not accepted without the :program switch.)

; Now turn off guard checking (which creates a warning in subsequent test
; cases that makes them unreadable):

(acl2::set-guard-checking :none)

; Now try out a few cases, including:

; (ring-lap 2 3 7) ; terminates
; (ring-lap 3 4 7) ; terminates
; (ring-lap 2 4 8) ; terminates
; (ring-lap 3 4 6) ; terminates

; (do not remove the comment sign in front of these tests, or your file may
; not be accepted).

; In the comments _behind_ the tests above, add your findings regarding
; what happens in these function calls.

; Now turn on function call tracing for ring-lap:

(acl2::trace! ring-lap)

; and run the above test cases again.

; Looking at these test cases, come up with conjectures for restrictions on
; the inputs (a b n) such that the function terminates. For what choice
; of inputs does ring-lap not terminate?

; Try to prove your conjectures from above using measure functions, or try
; to make your conjectures tight, i.e.: the function terminates exactly if

; ring-lap will terminate under any input, 
; given they satisfy its input contract.

; measure function:
(defunc m-lap (a b n)
  :input-contract (if (natp n) (and (nat-mod-n-p a n) (nat-mod-n-p b n)) nil)
  :output-contract (integerp (m-lap a b n))
  (minus-mod-n a b n))

; this is correct because when a=b in k recursions,
; (a + k) mod n = (b + 2k) mod n
; => (a - b) mod n = k

; the ring lap will hold for all inputs a,b,c 
; that satisfy the IC of ring-lap

; ic => (< (m-lap a b n) 
;          (m-lap (plus-mod-n a 1 n) (plus-mod-n b 2 n) n))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Part B: Equational reasoning 
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

#|

The following questions ask for "equational proofs" (the type of proofs we
have been doing to far) about ACL2s programs. When writing your proofs be
sure to justify each step in the style shown in class. You can use basic
arithmetic facts for free, but in the justification write "arithmetic",
e.g.,

    (first x) + (len (rest x)) + 0

= { Arithmetic }

    (len (rest x)) + (first x)

You may use infix notation (like x+y+z) for arithmetic operators (as done
above), instead of the LISP style prefix notation (like (+ x (+ y z))).

Here are the common definitions used for the remainder of the questions.

(defunc len (x)
  :input-contract t
  :output-contract (natp (len x))
  (if (atom x)
    0
    (+ 1 (len (rest x)))))

(defunc atom (x)
  :input-contract t
  :output-contract (booleanp (atom x))
  (not (consp a)))

(defunc not (a)
  :input-contract (booleanp a)
  :output-contract (booleanp (not a))
  (if a nil t))

(defunc listp (l)
  :input-contract t
  :output-contract (booleanp (listp l))
  (or (equal l ())
      (consp l)))

(defunc endp (a)
  :input-contract (listp a)
  :output-contract (booleanp (endp a))
  (not (consp a)))

(defunc twice (l)
  :input-contract (listp l)
  :output-contract (listp (twice l))
  (if (endp l)
    nil
    (cons (first l) (cons (first l) (twice (rest l))))))

(defunc app (a b)
  :input-contract (and (listp a) (listp b))
  :output-contract (listp (app a b))
  (if (endp a)
    b
    (cons (first a) (app (rest a) b))))

(defunc rev (x)
  :input-contract (listp x)
  :output-contract (and (listp (rev x)) (equal (len x) (len (rev x))))
  (if (endp x)
    nil
    (app (rev (rest x)) (list (first x)))))

Recall that for each of the defunc's above we get both a definitional
axiom (ic => function application = function body), and a contract
theorem (ic => oc). Definitional axioms and contract theorems are there for
you to use, as ACL2s has accepted these functions.

For the following claims, run some tests to conjecture whether they are
true or false. In the former case, prove them using equational reasoning.
In the latter case, disprove them by means of a counterexample. A
counterexample is an assignment to ALL free variables in the conjecture
that makes the conjecture evaluate to false. Do not give "partial
counterexamples" (leaving it to us to complete them).

(a)

(implies (listp y)
         (equal (len (rev (cons x y)))
                (+ 1 (len (rev y)))))
                
                
Hint: output contract of rev. If you make use of that, make sure you quote
an appropriate theorem or axiom.

  (len (rev (cons x y))) 
  { ic=>oc of rev }
= (len (cons x y))
  { thm. from class: (len (cons x y)) = 1 + (len y) }
= (+ 1 (len y))
  { ic=>oc of rev }
= (+ 1 (len (rev y)))

(b)

(implies (and (listp x)
              (listp y)
              (listp z))
         (implies (equal (twice (app       x    z)) (app (twice       x   ) (twice z)))
                  (equal (twice (app (cons x y) z)) (app (twice (cons x y)) (twice y)))))

Counterexample:
x='(1), y='(2), z='(3) 

(implies (equal (twice (app '(1) '(3))) 
                (app (twice '(1)) (twice '(3))))
         (equal (twice (app (cons '(1) '(2)) '(3))) 
                (app (twice (cons '(1) '(2))) (twice '(2))))))
                
= (implies (equal (twice '(1 3)) 
                  (app '(1 1) '(3 3))
         (equal (twice '((1) 2 3))
                (app '((1) (1) 2 2) '(2 2))
                
= (implies (equal '(1 1 3 3) 
                  '(1 1 3 3))
         (equal '((1) (1) 2 2 3 3)
               '((1) (1) 2 2 2 2))
               
= (implies t nil)
= nil

(c)

(implies (and (listp x)
              (listp y))
         (equal (app (rev x) (rev y))
                (rev (app x y))))

counterexample x='(a b), y='(c d)
(app (rev '(a b)) (rev '(c d))) = (app '(b a) '(d c)) = '(b a d c)
(rev (app '(a b) '(c d))) = (rev '(a b c d)) = '(d c b a)

(d)
(implies (listp x)
         (and (implies (endp x) 
                       (equal (len (twice x)) (* 2 (len x))))
              (implies (and (not (endp x)) (equal (len (twice (rest x))) (* 2 (len (rest x)))))
                       (equal (len (twice x)) (* 2 (len x))))))

Using this theorem (A=>(B^C)) = (A=>B ^ A=>C) prove seperately

Part B
(implies (listp x)
         (implies (endp x) 
                       (equal (len (twice x)) (* 2 (len x)))))
{use theorem (A=>(B=>C) = (A^B)=>C) }
= (implies (and (listpx) (endp x))
         (equal (len (twice x)) (* 2 (len x)))))
         
{skip rewriting conjecture contract, def. of twice, def of len }
= (equal (len nil) (* 2 (len nil)))
{ def. of nil}
= (equal 0 0)
{ axiom of equal }
= t

Part C
(implies (listp x)                   
          (implies (and (not (endp x)) 
                        (equal (len (twice (rest x))) (* 2 (len (rest x)))))
                   (equal (len (twice x)) (* 2 (len x))))))
{ use theorem (A=>(B=>C) = (A^B)=>C) }
= (implies (and (listp x) 
              (not (endp x)) 
              (equal (len (twice (rest x))) (* 2 (len (rest x)))))
         (equal (len (twice x)) (* 2 (len x))))))
{skip rewriting conjecture contract, def. of len, def of twice}
= (equal (len (cons (first x) (first x) (twice (rest x))) (* 2 (+ 1 (len (rest x)))))))
{def. of len (x2), arithetic}        
= (equal (+ 2 (len (twice (rest x)))) (+ 2 (* 2 (len (rest x))))))
{arithmetic}
= (equal (len (twice (rest x))) (* 2 (len (rest x)))))

Finally, (listp x)=>B ^ (listp x)=>C is valid so conjecture is proven.
|#