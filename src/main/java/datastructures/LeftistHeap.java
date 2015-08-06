package datastructures;


/**
 * Leftist heap is a variant of binary heap. Every node has an s-value which is the distance to the nearest leaf.
 * In contrast to a binary heap, a leftist tree attempts to be very unbalanced.
 * In addition to the heap property, leftist trees have additional property that the
 * right child of each node has the lower s-value.
 */
public class LeftistHeap {

    private Node root;

    /**
     * Initializes a new empty leftist heap.
     */
    public LeftistHeap() {
        root = null;
    }

    /**
     * Inserts a new node of value x into the heap
     * by merging it with the existing tree.
     * @param x value of the node that is being added.
     */
    public void insert(int x) {
        root = merge(new Node(x), root);
    }

    /**
     * Finds the minimum value, but does not remove it.
     * @return the minimum value as int.
     */
    public int findMin() {
        return root.value;
    }

    /**
     * Deletes the minimum value.
     * Then the method merges the subtrees.
     * @return the deleted minimum value as int.
     */
    public int deleteMin( ) {
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
     * Method to merge two Leftist heaps together.
     * @param anotherHeap heap that is being merged with current heap.
     */
    public void merge(LeftistHeap anotherHeap) {
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
        if (x.value > y.value) {
            Node tmp = x;
            x = y;
            y = tmp;
        }
        x.rightChild = merge(x.rightChild, y);
        if (x.leftChild == null) {
            x.leftChild = x.rightChild;
            x.rightChild = null;
        } else {
            if (x.leftChild.sValue < x.rightChild.sValue) {
                Node tmp = x.leftChild;
                x.leftChild = x.rightChild;
                x.rightChild = tmp;
            }
            x.sValue = x.rightChild.sValue + 1;
        }
        return x;
    }

    /**
     * Class for leftist heap nodes.
     */
    class Node {

        private int value;
        private int sValue;
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
         * Constructs the node with value, references to both childs and
         * initial s-value of 0.
         * @param value numerical value being set as int.
         * @param leftChild reference to nodes left child.
         * @param rightChild reference to nodes right child.
         */
        public Node(int value, Node leftChild, Node rightChild) {
            this.value = value;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.sValue = 0;
        }
    }
}
