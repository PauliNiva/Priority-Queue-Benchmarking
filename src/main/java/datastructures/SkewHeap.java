package datastructures;


/**
 * Skew heap is implemented as a binary tree. In contrast with binary heaps,
 * there are no structural constraints, so there is no guarantee that the height of the tree is logarithmic.
 * Only conditions that must be satisfied are that the heap order is enforced and every operation
 * on two skew heaps must be done using mergePairs.
 */
public class SkewHeap implements Heap {

    private Node root;

    /**
     * Initializes a new empty skew heap.
     */
    public SkewHeap() {
        root = null;
    }

    /**
     * Inserts a new node of value x into the heap
     * by merging it with the existing tree.
     * @param x value of the node that is being added.
     */
    @Override
    public void insert(int x) {
        root = merge(new Node(x), root);
    }

    /**
     * Finds the minimum value, but does not remove it.
     * @return the minimum value as int.
     */
    @Override
    public int findMin() {
        return root.value;
    }

    /**
     * Deletes the minimum value.
     * Then the method merges the subtrees.
     * @return the deleted minimum value as int.
     */
    @Override
    public int deleteMin() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        } else {
            int min = root.value;
            root = merge(root.leftChild, root.rightChild);
            return min;
        }
    }

    /**
     * Checks if the heap is empty.
     * @return true if the heap is empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Empties the heap.
     */
    public void clear( ) {
        root = null;
    }

    /**
     * Method to merge two skew heaps together.
     * @param anotherHeap heap that is being merged with current heap.
     */
    public void merge(SkewHeap anotherHeap) {
        if (this == anotherHeap) {
            return;
        } else {
            root = merge(root, anotherHeap.root);
            anotherHeap.clear();
        }
    }

    /**
     * Method to merge two node lists together.
     * @param x node that is being merged.
     * @param y node that is being merged.
     * @return merged node lists head.
     */
    public Node merge(Node x, Node y) {
        if (x == null) {
            return y;
        }
        if (y == null) {
            return x;
        }
        if (x.value < y.value) {
            Node tmp = x.leftChild;
            x.leftChild = merge(x.rightChild, y);
            x.rightChild = tmp;
            return x;
        } else {
            Node tmp = y.rightChild;
            y.rightChild = merge(y.leftChild, x);
            y.leftChild = tmp;
            return y;
        }
    }

    /**
     * Class for skew heap nodes.
     */
    class Node {

        private int value;
        private Node leftChild;
        private Node rightChild;

        /**
         * Constructs the node only with value.
         * @param value numerical value being set as int.
         */
        public Node(int value) {
            this(value, null, null);
        }

        /**
         * Constructs the node with value, references to both childs.
         * @param value numerical value being set as int.
         * @param leftChild reference to nodes leftChild child.
         * @param rightChild reference to nodes rightChild child.
         */
        public Node(int value, Node leftChild, Node rightChild) {
            this.value = value;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }
}
