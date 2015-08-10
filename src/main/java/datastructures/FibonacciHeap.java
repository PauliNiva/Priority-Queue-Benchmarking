package datastructures;


/**
 * Fibonacci heap is a heap structure that consists a forest of trees.
 * Compared to binomial trees the structure of this heap is more flexible.
 * The trees do not have to be a prescribed shape.
 * This allows postponing the work of some operations to later operations.
 */
public class FibonacciHeap {

    private Node min;

    /**
     * Initializes a new empty Fibonacci heap.
     */
    public FibonacciHeap() {
        min = null;
    }

    /**
     * Inserts a new node of value x into the heap.
     * @param x value of the node that is being added.
     */
    public void insert(int x) {
        Node node = new Node(x);
        if (isEmpty()) {
            min = node;
        } else {
            node.right = min;
            node.left = min.left;
            min.left = node;
            node.left.right = node;
            if (x < min.value) {
                min = node;
            }
        }
    }

    /**
     * Finds the minimum of the Fibonacci heap.
     * @return value of the minimum head as int.
     */
    public int findMin() {
        return min.value;
    }

    /**
     * Deletes the minimum value.
     * Then the method consolidates the trees if necessary.
     * @return the deleted minimum value as int.
     */
    public int deleteMin() {
        Node tmp = min;
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        } else {
            if (tmp.child != null) {
                tmp.child.parent = null;
                for (Node node = tmp.child.right; node != tmp.child; node = node.right) {
                    node.parent = null;
                }
                Node minLeft = min.left;
                Node tmpChildLeft = tmp.child.left;
                min.left = tmpChildLeft;
                tmpChildLeft.right = min;
                tmp.child.left = minLeft;
                minLeft.right = tmp.child;
            }
            tmp.left.right = tmp.right;
            tmp.right.left = tmp.left;
            if (tmp == tmp.right) {
                min = null;
            } else {
                min = tmp.right;
                consolidateTrees();
            }
            return tmp.value;
        }
    }

    /**
     * Checks if the heap is empty.
     * @return true if the min is empty, false otherwise.
     */
    public boolean isEmpty() {
        return min == null;
    }

    /**
     * Empties the heap.
     */
    public void clear() {
        min = null;
    }

    /**
     * Method that merges two heaps together into a brand new one.
     * @param heap1 heap to be merged.
     * @param heap2 heap to be merged.
     * @return new heap where the two old ones are merged.
     */
    public FibonacciHeap merge(FibonacciHeap heap1, FibonacciHeap heap2) {
        FibonacciHeap mergedHeap = new FibonacciHeap();
        if (heap1 != null && heap2 != null) {
            mergedHeap.min = heap1.min;
            if (mergedHeap.min != null) {
                if (heap2.min != null) {
                    mergedHeap.min.right.left = heap2.min.left;
                    heap2.min.left.right = mergedHeap.min.right;
                    mergedHeap.min.right = heap2.min;
                    heap2.min.left = mergedHeap.min;
                    if (heap2.min.value < heap1.min.value) {
                        mergedHeap.min = heap2.min;
                    }
                }
            } else {
                mergedHeap.min = heap2.min;
            }
        }
        return mergedHeap;
    }

    /**
     * Consolidates the trees in the heap by joining trees of equal degree.
     */
    public void consolidateTrees() {
        Node[] degreeArray = new Node[45];
        Node start = min;
        Node tmp = min;
        do {
            Node tmp1 = tmp;
            Node tmpNext = tmp.right;
            int degree = tmp1.degree;
            while (degreeArray[degree] != null) {
                Node tmp2 = degreeArray[degree];
                if (tmp1.value > tmp2.value) {
                    Node tmp3 = tmp2;
                    tmp2 = tmp1;
                    tmp1 = tmp3;
                }
                if (tmp2 == start) {
                    start = start.right;
                }
                if (tmp2 == tmpNext) {
                    tmpNext = tmpNext.right;
                }
                tmp2.link(tmp1);
                degreeArray[degree] = null;
                degree++;
            }
            degreeArray[degree] = tmp1;
            tmp = tmpNext;
        } while (tmp != start);
        min = start;
        for (Node node : degreeArray) {
            if (node != null && node.value < min.value) {
                min = node;
            }
        }
    }


    /**
     * Class for Fibonacci head nodes.
     */
    class Node {
        int value;
        int degree;
        Node parent;
        Node child;
        Node left;
        Node right;

        /**
         * Initializes a new head with value x.
         * @param x value that the head is initialized with.
         */
        public Node(int x) {
            value = x;
            degree = 0;
            parent = null;
            child = null;
            left = this;
            right = this;
        }

        /**
         * This method turns caller node to child node of the parameter node.
         * @param parent node that is being linked as parent.
         */
        public void link(Node parent) {
            left.right = right;
            right.left = left;
            this.parent = parent;
            if (parent.child == null) {
                parent.child = this;
                right = this;
                left = this;
            } else {
                left = parent.child;
                right = parent.child.right;
                parent.child.right = this;
                right.left = this;
            }
            parent.degree++;
        }
    }
}
