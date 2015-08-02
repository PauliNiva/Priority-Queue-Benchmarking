package datastructures;


public class BinomialHeap {

    private Node heap;
    private int heapSize;

    /**
     * Initializes a new empty heap.
     */
    public BinomialHeap() {
        heap = null;
        heapSize = 0;
    }

    /**
     * Inserts a new node of value x into the heap
     * by merging it with the existing tree.
     * @param x value of the node that is being added.
     */
    public void insert(int x) {
            Node node = new Node(x);
            if (isEmpty()) {
                heap = node;
                heapSize++;
            } else {
                // TODO merge
                heapSize++;
            }
    }

    /**
     * Finds the minimum of the binomial heap.
     * @return value of the minimum root as int.
     */
    public int findMin() {
        return findMinRoot().value;
    }

    /**
     * Checks if the heap is empty.
     * @return true if the heap is empty, false otherwise.
     */
    public boolean isEmpty() {
        return heap == null;
    }

    /**
     * Finds the minimum amongst the roots of the binomial trees.
     * @return minimum root.
     */
    public Node findMinRoot() {
        Node x = heap;
        Node minRoot = heap;
        int min = x.value;
        while (x != null) {
            if (x.value < min) {
                minRoot = x;
                min = minRoot.value;
            }
            x = x.sibling;
        }
        return minRoot;
    }

    /**
     * Class for binomial heap nodes.
     */
    class Node {
        int value;
        int degree;
        Node parent;
        Node child;
        Node sibling;

        /**
         * Initializes a new heap with value x.
         * @param x value that the heap is initialized with.
         */
        public Node(int x) {
            value = x;
            degree = 0;
            parent = null;
            child = null;
        }
    }
}
