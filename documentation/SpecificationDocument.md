The target of the project is to performance test different kinds of priority queues.


Data structures to be implemented:
  - Binary heap as fixed size array and maybe as linked list if time permits.
  - D-Heap as fixed size array and maybe as linked list if time permits.
  - Leftist heap as linked list.
  - Skew heap as linked list.
  - Binomial heap as linked list.
  - Fibonacci heap as linked list.
  - Treap as linked list.
  - Pairing heap as linked list, if time permits.
All of these are implemented as min-heaps.
  
Priority order of the implementations that are labeled as 'if time permits':
  1. Pairing heap.
  2. D-Heap as linked list.
  3. Binary heap as linked list.
 
 
Performance tests include but are not limited to:
  Difference of these basic operations between heaps:
  - insert.
  - findMind.
  - deleteMin.
  Difference between these optional operations between heaps:
  - merge (when applicable).
  - decrease key (when applicable).
  Difference in performance between heaps with inputs:
  - that correlate with big and dense graphs.
  - that correlate with small and scarce graphs.
  Tests include (but are not limited to) finding the shortest path and minimum spanning tree in the said graphs.
  Additionally D-Heap is tested at least with 1, 2, 3 and 4 as values of d.
More detailed specification of testing can be found in the testing document.


Binary heap:

Binary heap is implemented as a binary tree with two additional constraints:
It conserves the heap property and the heap is a complete binary tree.
  - Insert O(log n)
  - findMin O(1)
  - deleteMin O(log n)
  - decreaseKey O(log n)
  
  
D-Heap:

D-Heap is a generalization of a binary heap in which the nodes have d children instead of two.
  - Insert O(log n / log d), where d is the number of children that a node of the tree can have.
  - findMin O(1)
  - deleteMin O(d log n / log d), where d is the number of children that a node of the tree can have.
  - decreaseKey O(log n)

Leftist heap:

Leftist heap is a variant of binary heap. very node has an s-value which is the distance to the nearest leaf.
In contrast to a binary heap, a leftist tree attempts to be very unbalanced.
In addition to the heap property, leftist trees have additional property that the right child of each node has the lower s-value.
  - Insert O(log n)
  - findMin O(1)
  - deleteMin O(log n)
  - decreaseKey O(log n)
  - merge O(log n)
  
  
Skew heap:

Skew heap is implemented as a binary tree.
In contrast with binary heaps, there are no structural constraints, so there is no guarantee that the height of the tree is logarithmic.
Only conditions that must be satisfied are that the heap order is enforced and every operation on two skew heaps must be done using merge.
  - Insert O(log n)
  - findMin O(1)
  - deleteMin O(log n)
  - decreaseKey O()
  - merge O(log n), amortized
  

Binomial heap:

Binomial heap is a heap similar to a binary heap but also supports quick merging of two heaps by using a binomial tree structure.
These have to satisfy the binomial heap properties that are, that each binomial tree in a heap obeys the min-heap property and
that there can only be either one or zero binomial trees for each order, including zero order.
  - Insert O(1), amortized.
  - findMin O(1)
  - deleteMin O(log n)
  - decreaseKey O(log n)
  - merge O(log n), where n is the size of the larger heap.
  
  
Fibonacci heap:

Fibonacci heap is a heap structure that consists a collection of trees.
Compared to binomial trees the structure of this heap is more flexible. The trees do not have to be a prescribed shape.
This allows postponing the work of some operations to later operations.
  - Insert O(1)
  - findMin O(1)
  - deleteMin O(log n), amortized.
  - decreaseKey O(1), amortized.
  - merge O(1)
  
  
Treap:

Treap is a closely related form of binary search tree data structure that maintains a dynamic set of ordered keys and allow binary searches among the keys.
After any sequence of insertions and deletions of keys, the shape of the tree is a random variable with the same probability distribution as a random binary tree;
in particular, with high probability its height is proportional to the logarithm of the number of keys, so that each search, insertion, or deletion operation takes logarithmic time to perform.
  - Insert O(log n)
  - findMin O(log n)
  - deleteMin O(log n)
  - decreaseKey O(log n)
  - merge O(m (log (n+m)/m)), where m is the size of the smaller and n is the size of the larger heap.
All complexities are amortized.  

  
Pairing heap:

Pairing heap is a strongly simplified self-adjusting variant of a Fibonacci heap.
A pairing heap is a tree without any restrictions on the degrees of the nodes.
The only restriction is that this tree is heap ordered, that is, for any node n,
the key of n is not larger than the keys of any of its children.
Operations:
  - Insert O(1)
  - findMin O(1)
  - deleteMin O(log n)
  - decreaseKey O(log n), amortized.
  - merge O(1)
  
  
References:
  - http://www.cs.cmu.edu/afs/cs/academic/class/15210-s12/www/lectures/lecture20.pdf
  - https://www8.cs.umu.se/~jopsi/dinf504/chap6.shtml
  - https://en.wikipedia.org/wiki/Pairing_heap
  - http://www.cs.cmu.edu/afs/cs/academic/class/15499-s09/www/scribe/lec10/lec10.pdf
  - https://en.wikipedia.org/wiki/Treap
  - https://en.wikipedia.org/wiki/Skew_heap
  - https://en.wikipedia.org/wiki/Leftist_tree
  - https://en.wikipedia.org/wiki/Heap_%28data_structure%29
  - https://en.wikipedia.org/wiki/Binomial_heap
  - https://en.wikipedia.org/wiki/D-ary_heap
  - https://en.wikipedia.org/wiki/Binary_heap
  - Cormen, Thomas H.; Leiserson, Charles E.; Rivest, Ronald L.; Stein, Clifford (2009) [1990]. Introduction to Algorithms (3rd ed.). MIT Press and McGraw-Hill. ISBN 0-262-03384-4.
  - Goodrich, Michael T.; Tamassia, Roberto; Goldwasser Michael H. (2014) [1998]. Data Structures and Algorithms in Java (6th ed.). John Wiley & Sons, Inc. ISBN: 0-471-73884-0. 