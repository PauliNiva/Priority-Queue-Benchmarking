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
     * NOT IMPLEMENTED
     * @param node NOT IMPLEMENTED
     */
    @Override
    public void insert(Node node) {
        // NOT IMPLEMENTED
    }

    /**
     * Inserts a new node of value x into the heap
     * by merging it with the existing tree.
     * @param value value of the node that is being added.
     */
    @Override
    public void insert(int value) {
        root = merge(new Node(value), root);
    }

    /**
     * Finds the minimum node, but does not remove it.
     * @return the minimum node.
     */
    @Override
    public Node findMinNode() {
        return root;
    }

    /**
     * Finds the minimum value, but does not remove it.
     * @return the minimum value as int.
     */
    @Override
    public int findMinValue() {
        return root.getValue();
    }

    /**
     * Deletes the minimum node.
     * Then the method merges the subtrees.
     * @return the deleted minimum node.
     */
    @Override
    public Node deleteMin() {
        if (isEmpty()) {
            return null;
        } else {
            Node min = root;
            root = merge(root.getLeftChild(), root.getRightChild());
            return min;
        }
    }

    /**
     * NOT IMPLEMENTED
     * @param node NOT IMPLEMENTED
     * @param newValue NOT IMPLEMENTED
     */
    @Override
    public void decreaseKey(Node node, int newValue) {
        // NOT IMPLEMENTED
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
        if (x.getValue() < y.getValue()) {
            Node tmp = x.getLeftChild();
            x.setLeftChild(merge(x.getRightChild(), y));
            x.setRightChild(tmp);
            return x;
        } else {
            Node tmp = y.getRightChild();
            y.setRightChild(merge(y.getLeftChild(), x));
            y.setLeftChild(tmp);
            return y;
        }
    }

    public int getHeapSize() {
        return 0;
    }
}
