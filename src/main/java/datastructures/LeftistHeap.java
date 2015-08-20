package datastructures;


/**
 * Leftist heap is a variant of binary heap. Every node has an s-value which is the distance to the nearest leaf.
 * In contrast to a binary heap, a leftist tree attempts to be very unbalanced.
 * In addition to the heap property, leftist trees have additional property that the
 * rightChild child of each node has the lower s-value.
 */
public class LeftistHeap implements Heap {

    private Node root;

    /**
     * Initializes a new empty leftist heap.
     */
    public LeftistHeap() {
        root = null;
    }

    /**
     * Inserts a node into the heap by merging it
     * with existing tree.
     * @param node node that is being added.
     */
    @Override
    public void insert(Node node) {
        root = merge(node, root);
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
     * Finds the minimum node, but does not remove it.
     * @return the minimum node.
     */
    @Override
    public Node findMin() {
        return root;
    }

    /**
     * Finds the value of minimum node.
     * @return the value of minimum node as int.
     */
    @Override
    public int findMinimum() {
        return root.getValue();
    }

    /**
     * Deletes the minimum node.
     * Then the method merges the subtrees.
     * @return the deleted minimum node.
     */
    @Override
    public Node deleteMin( ) {
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
    public void decreaseKey(datastructures.Node node, int newValue) {
        // NOT IMPLEMENTED
    }

    /**
     * NOT IMPLEMENTED
     * @param index NOT IMPLEMENTED
     * @param newValue NOT IMPLEMENTED
     */
    @Override
    public void decreaseKey(int index, int newValue) {
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
     * Method to mergePairs two Leftist heaps together.
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
        if (x.getValue() > y.getValue()) {
            Node tmp = x;
            x = y;
            y = tmp;
        }
        x.setRightChild(merge(x.getRightChild(), y));
        if (x.getLeftChild() == null) {
            x.setLeftChild(x.getRightChild());
            x.setRightChild(null);
        } else {
            if (x.getLeftChild().getSValue() < x.getRightChild().getSValue()) {
                Node tmp = x.getLeftChild();
                x.setLeftChild(x.getRightChild());
                x.setRightChild(tmp);
            }
            x.setSValue(x.getRightChild().getSValue() + 1);
        }
        return x;
    }
}
