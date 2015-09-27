#|

CS 2800 Homework 8 - Spring 2014

Student names: Molly White, Charles Hoyt

Technical instructions:

- open this file in ACL2s as hw08.lisp

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
; Part A: Equational reasoning
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

#|

To prove claims, proceed as follows:

- perform conjecture contract checking, and add hypotheses (if necessary)

- use propositional reasoning to rewrite the conjecture into an implication
  with a conjunction of hypotheses in the antecedent and a single expression
  in the conclusion (if necessary).

- extract the context (the hypotheses), and determine the derived context:
  anything that follows immediately from the context expressions. Remember
  that you cannot instantiate context expressions -- these are not theorems.

- now perform the proof

When writing your equational reasoning proofs be sure to justify each step
in the style shown in class, eg.

  (len ())
= { def len }
  0

You can use basic arithmetic facts for free, but in the justification write
"arithmetic", e.g.,

  (first x) + (len (rest x)) + (sum y) + 0
= { Arithmetic }
  (sum y) + (first x) + (len (rest x))

You may use infix notation (like x+y+z) for arithmetic operators (as done
above), instead of the LISP style prefix notation (like (+ x (+ y z))).

You can of course also use previously (in class or in homework) proved
theorems. In this case, cite the theorem in the justification, and give the
substitution that shows how you instantiated the theorem.

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

Here are the common definitions used for the remainder of the questions:

(defunc fib (n)
  :input-contract (natp n)
  :output-contract (natp (fib n))
  (if (<= n 1)
    n
    (+ (fib (- n 1))
       (fib (- n 2)))))

(defunc evenp (n)
  :input-contract t
  :output-contract (booleanp (evenp n))
  (if (integerp n)
    (integerp (/ n 2))
    nil))

We want to show that, if fib(n) is even, both fib(n-1) and fib(n+1) are
odd:

(n >= 1) => ((evenp (fib n)) => (not (evenp (fib (- n 1)))) /\ (not (evenp (fib (+ n 1)))))

That is, the Fibonacci sequence defined by the fib function above does not
contain two consecutive even numbers.

Following the induction principle (which we will study in depth soon; you
do not need to understand it to be able to do this problem), we establish
the following conjecture:

(and (implies (equal n 1)
              (implies (evenp (fib n))
                       (and (not (evenp (fib (- n 1))))
                            (not (evenp (fib (+ n 1)))))))
     (implies (and (natp n)
                   (>= n 2)
                   (implies (evenp (fib n))
                            (and (not (evenp (fib (- n 1))))
                                 (not (evenp (fib (+ n 1)))))))
              (implies (evenp (fib (+ n 1)))
                       (and (not (evenp (fib    n  )))
                            (not (evenp (fib (+ n 2))))))))

Prove this conjecture using equational reasoning.

Propositional Logic Structure:

T1.
(equal n 1) => (evenp (fib n)) => (and (not (evenp (fib (- n 1))))
                                       (not (evenp (fib (+ n 1)))))))

T2.
((natp n) /\ (>= n 2) /\
  ((evenp (fib n)) => (~(evenp (fib (-n 1))) /\ ~(evenp (fib (+ n 1)))))) =>
    ((evenp (fib (+ n 1))) => (~(evenp (fib n)) /\ ~(evenp (fib (+ n 2)))))

T1.
(equal n 1) => (evenp (fib n)) => (and (not (evenp (fib (- n 1))))
                                       (not (evenp (fib (+ n 1)))))))

C1. (equal n 1)
= { def. natp }
C2. (natp n)

= { def. fib, C1, C2 }
(evenp (if (<= 1 1) 1 ...)) => ...
= { def. <=, def. if }
(evenp 1) => ...
= { def. evenp }
False => ...
= { def. implies }
True

T1 == True

T2.
((natp n) /\ (>= n 2) /\
  ((evenp (fib n)) => (~(evenp (fib (-n 1))) /\ ~(evenp (fib (+ n 1)))))) =>
    ((evenp (fib (+ n 1))) => (~(evenp (fib n)) /\ ~(evenp (fib (+ n 2)))))
    
C1. (natp n)
C2. (>= n 2)
C3. (evenp (fib n)) => (~(evenp (fib (- n 1))) /\ ~(evenp (fib (+ n 1))))
= { C3 contrapositive }
C4. (evenp (fib (- n 1))) /\ (evenp (fib (+ n 1))) => ~(evenp (fib n))

(evenp (fib (+ n 1))) => ...
= { C3 -> T }
F => ...
True

(evenp (fib (+ n 1))) => ...
= { C3 -> F, C4 }
T => ...
= {}
(~(evenp (fib n)) /\ ~(evenp (fib (+ n 2))))
= { C4 }
(T /\ ...)
= { def. and }
~(evenp (fib (+ n 2)))
= { def. fib }
~F
= { def. not }
T


|#

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Part B: Induction 
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

#|

For each of the problems below: State your conjecture precisely, 
including the base case and the inductive hypothesis.

1. Prove that every number in the sequence 1003, 10013, 100113, ....
is divisible by 17. 

Note: 

closed form of this sequence: a(n) = (17/9) * (1 + 53 * 10^n)

non-closed form form: a(n+1) = a(n) + 17 * 53 * 10^n
proof:
a(n+1) - a(n) =
= (17/9) * (1 + 53 * 10^(n+1) - (17/9) * (1 + 53 * 10^n) 
= (17/9) * (1 + 53 * 10^(n+1) - (1 + 53 * 10^n))
= (17/9) * (1 + 53 * 10^(n+1) - 1 - 53 * 10^n)
= (17/9) * (53 * 10^(n+1) - 53 * 10^n)
= (17/9) * 53 * (10^(n+1) - 10^n)
= (17/9) * 53 * (10*10^n - 10^n)
= (17/9) * 53 * (9*10^n)
= 17 * 53 * 10^n

Base case: (17 | a(0))
a(0)/17 = 1003/17 = 59

Inductive step: assume (17 | a(n)) to prove (17 | a(n+1))

a(n+1) = a(n) + 17 * 53 * 10^n

Use theorem of divisibility: (n | x) ^ (n | y) => n | (x + y)

1) a(n) must be divisible by 17 via the inductive hypothesis
2) 17 * 53 * 10^n is obviously divisble by 17 since it's a factor

therefore, all elements in the sequence are divisible by 17

2. A number of straight lines are drawn in the plane, dividing it into 
regions. Each line is shaded red on one side and black on the other. 
Show that there is a region with red shading throughout its internal 
boundary. 

Base case: one line: though the "internal boundary" on the right side is
  effectively unbounded, it still follows the condition. 
  
Inductive step: Any new line added will either do one of two things:
 A) intersect the current region over which there is a red internal boundary
    The side of the new line that is red defines a new, smaller boundary
 B) not intersect or exact overlap with current line within boundary 
    - the region stays the same and still exists

Note: assumes no overlapping lines that have opposite colors for each. 
This would be nonsensical and could lead to a contradiction.

3. Prove that there exists n0 >= 0 such that 2^n >= n^2 for all natural n >= n0. 

base case: n0 = 5, 2^5 = 32, 5^2 = 25, 32 >= 25

inductive case: assume 2^n >= n^2 to prove 2^(n+1) >= (n+1)^2
{arithmetic}
2*(2^n) >= (n^2) + 2n + 1
{subtract n case from n+1 case (linear combination)}
2^n >= 2n + 1
{this is true for all n>3, which we already have determined
 from the base case condition.}

|#