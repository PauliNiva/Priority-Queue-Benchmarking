package datastructures;


/**
 * Treap is a closely related form of binary search tree data structure that maintains a dynamic set of
 * ordered keys and allow binary searches among the keys.
 * After any sequence of insertions and deletions of keys, the shape of the tree is a random variable
 * with the same probability distribution as a random binary tree;
 * in particular, with high probability its height is proportional to the logarithm of the number of keys,
 * so that each search, insertion, or deletion operation takes logarithmic time to perform.
 */
public class Treap implements Heap {

    private Node root;
    private static Node nullNode = new Node(Integer.MAX_VALUE, Integer.MAX_VALUE);

    /**
     * Initializes a new treap with a root as null node.
     */
    public Treap() {
        root = nullNode;
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
     * Inserts value x into the treap.
     * If x is already in treap it does nothing.
     * @param value value of the node that is being inserted.
     */
    @Override
    public void insert(int value) {
        root = insert(value, root);
    }

    /**
     * Finds the minimum node in the treap.
     * @return if the treap is empty returns null, minimum node otherwise.
     */
    @Override
    public Node findMinNode() {
        if (isEmpty()) {
            return null;
        }
        Node pointer = root;
        while (pointer.getLeftChild() != nullNode) {
            pointer = pointer.getLeftChild();
        }
        return pointer;
    }

    /**
     * Finds the minimum value in the treap.
     * @return if the treap is empty returns Integer.MAX_VALUE, minimum value as int otherwise.
     */
    @Override
    public int findMinValue() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        } else {
            Node pointer = root;
            while (pointer.getLeftChild() != nullNode) {
                pointer = pointer.getLeftChild();
            }
            return pointer.getValue();
        }
    }

    /**
     * Method to delete min node.
     * @return min node.
     */
    @Override
    public Node deleteMin() {
        Node min = findMinNode();
        if (min != null) {
            remove(findMinNode().getValue());
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
     * Checks if the treap is empty.
     * @return true if the heap is empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == nullNode;
    }

    /**
     * Auxiliary method that inserts integer x into a subtreap.
     * @param value the integer that is being inserted.
     * @param node root node of the (sub)tree.
     * @return the new root.
     */
    private Node insert(int value, Node node) {
        if (node == nullNode) {
            node = new Node(value, nullNode, nullNode);
        } else if (value - node.getValue() < 0) {
            node.setLeftChild(insert(value, node.getLeftChild()));
            if (node.getLeftChild().getPriority() < node.getPriority()) {
                node = rotateWithLeftChild(node);
            }
        } else if (value - node.getValue() > 0) {
            node.setRightChild(insert(value, node.getRightChild()));
            if (node.getRightChild().getPriority() < node.getPriority()) {
                node = rotateWithRightChild(node);
            }
        }
        return node;
    }

    /**
     * Removes node that has value x from the treap.
     * If treap does not contain x, method does nothing.
     * @param value the item that is being removed.
     */
    public void remove(int value) {
        root = remove(value, root);
    }

    /**
     * Auxiliary method that removes node with specific value from a subtreap.
     * @param value the value that is being removed.
     * @param node root node of the (sub)tree.
     * @return the new root.
     */
    private Node remove(int value, Node node) {
        if (node != nullNode) {
            if (value - node.getValue() < 0) {
                node.setLeftChild(remove(value, node.getLeftChild()));
            } else if (value - node.getValue() > 0) {
                node.setRightChild(remove(value, node.getRightChild()));
            } else {
                if (node.getLeftChild().getPriority() < node.getRightChild().getPriority()) {
                    node = rotateWithLeftChild(node);
                } else {
                    node = rotateWithRightChild(node);
                }
                if(node != nullNode) {
                    node = remove(value, node);
                } else {
                    node.setLeftChild(nullNode);
                }
            }
        }
        return node;
    }

    /**
     * Rotates node with its left child.
     */
    private Node rotateWithLeftChild(Node node1) {
        Node node2 = node1.getLeftChild();
        node1.setLeftChild(node2.getRightChild());
        node2.setRightChild(node1);
        return node2;
    }

    /**
     *  Rotates node with its right child.
     */
    private Node rotateWithRightChild(Node node1) {
        Node node2 = node1.getRightChild();
        node1.setRightChild(node2.getLeftChild());
        node2.setLeftChild(node1);
        return node2;
    }

    public int getHeapSize() {
        return 0;
    }
}
