1. This implementation is in Java 7
2. This code requires compilation before running
  - `cd` within this folder
  - build by running `sh build.bash`
  - run with `. sol`
3. My solution makes use of a Disjoint Subset Data Structure. 
  - First, it creates a subset to represent each variable.
  - Next, it joins the subsets of the arguments of the constraints that are EQUAL.
  - Last, it checks that none of the arguments of the constraints that are NOT EQUAL belong to the same subset.
4. My solution passes all tests in reasonable time.
5. My solution takes a little less than five seconds for the large input of 2.5 million constraints.
6. The worst case run time is O(n + m log n) where m is the number of constraints and n is the number of variables. 
  - Creation and loading of the data structure takes O(n) steps
  - All m constraints are addressed in a maximum of O(log n) time each because the implementation of the data structure ensures that at most log n operations can be performed on any element before the each element belongs to the same set
  - Finally, the not-equal constraints are processed in O(m). 
