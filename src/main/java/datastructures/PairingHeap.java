package datastructures;


/**
 * Pairing heap is a strongly simplified self-adjusting variant of a Fibonacci heap.
 * A pairing heap is a tree without any restrictions on the degrees of the nodes.
 * The only restriction is that this tree is heap ordered, that is, for any node n,
 * the key of n is not larger than the keys of any of its children.
 */
public class PairingHeap implements Heap {

    Node root;
    Node[] array = new Node[4];

    /**
     * Initializes a new empty Fibonacci heap.
     */
    public PairingHeap() {
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
     * Inserts a new node of value x into the heap.
     * @param x value of the node that is being added.
     */
    @Override
    public void insert(int x) {
        Node node = new Node(x);
        if (isEmpty()) {
            root = node;
        } else {
            root = merge(root, node);
        }
    }

    /**
     * Finds the minimum of the Pairing heap.
     * @return value of the root as int.
     */
    @Override
    public Node findMin() {
        return root;
    }

    /**
     * Finds the value of the minimum node.
     * @return the minimum value of the heap as int.
     */
    @Override
    public int findMinimum() {
        return root.getValue();
    }

    /**
     * Deletes the minimum node.
     * @return the deleted minimum node.
     */
    @Override
    public Node deleteMin() {
        if (isEmpty()) {
            return null;
        }
        Node min = root;
        if (root.getLeftChild() == null) {
            root = null;
        } else {
            root = mergePairs(root.getLeftChild());
        }
        return min;
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
    public void clear() {
        root = null;
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
        if (y.getValue() <= x.getValue()) {
            y.setPreviousSibling(x.getPreviousSibling());
            x.setPreviousSibling(y);
            x.setNextSibling(y.getLeftChild());
            if (x.getNextSibling() != null) {
                x.getNextSibling().setPreviousSibling(x);
            }
            y.setLeftChild(x);
            return y;
        } else {
            y.setPreviousSibling(x);
            x.setNextSibling(y.getNextSibling());
            if (x.getNextSibling() != null) {
                x.getNextSibling().setPreviousSibling(x);
            }
            y.setNextSibling(x.getLeftChild());
            if (y.getNextSibling() != null)
                y.getNextSibling().setPreviousSibling(y);
            x.setLeftChild(y);
            return x;
        }
    }

    /**
     * Method that merges the subheaps in pairs with two pass merge.
     * @param x the root node.
     * @return merged heaps root node.
     */
    public Node mergePairs(Node x) {
        if (x == null) {
            return null;
        }
        if (x.getNextSibling() == null) {
            return x;
        }
        int numSiblings;
        for (numSiblings = 0; x != null; numSiblings++) {
            array = growArrayIfNeeded(array, numSiblings);
            array[numSiblings] = x;
            x.getPreviousSibling().setNextSibling(null);
            x = x.getNextSibling();
        }
        array = growArrayIfNeeded(array, numSiblings);
        array[numSiblings] = null;
        int i = 0;

        for (; i + 1 < numSiblings; i += 2) {
            array[i] = merge(array[i], array[i + 1]);
        }
        int j = i - 2;
        if (j == numSiblings - 3) {
            array[j] = merge(array[j], array[j + 2]);
        }
        for (; j >= 2; j -= 2)
            array[j - 2] = merge(array[j - 2], array[j]);
        return array[0];
    }

    /**
     * Method to grow the size of an array by double if need be.
     * @param array array whose size is being doubled.
     * @param index current size of the array.
     * @return if array is full then the the copy with double the size is returned,
     * origal otherwise.
     */
    public Node[] growArrayIfNeeded(Node[] array, int index) {
        if (index == array.length) {
            Node[] arrayCopy = new Node[index * 2];
            System.arraycopy(array, 0, arrayCopy, 0, index);
            return arrayCopy;
        } else {
            return array;
        }
    }
}
