package datastructures;


import java.util.Random;

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
     * @param x value of the node that is being inserted.
     */
    @Override
    public void insert(int x) {
        root = insert(x, root);
    }

    /**
     * Finds the minimum node in the treap.
     * @return if the treap is empty returns null, minimum node otherwise.
     */
    @Override
    public Node findMin() {
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
    public int findMinimum() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }
        Node pointer = root;
        while (pointer.getLeftChild() != nullNode) {
            pointer = pointer.getLeftChild();
        }
        return pointer.getValue();
    }

    /**
     * Method to delete min node.
     * @return min node.
     */
    @Override
    public Node deleteMin() {
        Node min = findMin();
        if (min != null) {
            remove(findMin().getValue());
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
     * Checks if the treap is empty.
     * @return true if the heap is empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == nullNode;
    }

    /**
     * Auxiliary method that inserts integer x into a subtreap.
     * @param x the integer that is being inserted.
     * @param node root node of the (sub)tree.
     * @return the new root.
     */
    private Node insert(int x, Node node) {
        if (node == nullNode) {
            node = new Node(x, nullNode, nullNode);
        } else if (x - node.getValue() < 0) {
            node.setLeftChild(insert(x, node.getLeftChild()));
            if (node.getLeftChild().getPriority() < node.getPriority()) {
                node = rotateWithLeftChild(node);
            }
        } else if (x - node.getValue() > 0) {
            node.setRightChild(insert(x, node.getRightChild()));
            if (node.getRightChild().getPriority() < node.getPriority()) {
                node = rotateWithRightChild(node);
            }
        }
        return node;
    }

    /**
     * Removes node that has value x from the treap.
     * If treap does not contain x, method does nothing.
     * @param x the item that is being removed.
     */
    public void remove(int x) {
        root = remove(x, root);
    }

    /**
     * Auxiliary method that removes node with valuex from a subtreap.
     * @param x the value that is being removed.
     * @param node root node of the (sub)tree.
     * @return the new root.
     */
    private Node remove(int x, Node node) {
        if (node != nullNode) {
            if (x - node.getValue() < 0) {
                node.setLeftChild(remove(x, node.getLeftChild()));
            } else if (x - node.getValue() > 0) {
                node.setRightChild(remove(x, node.getRightChild()));
            } else {
                if (node.getLeftChild().getPriority() < node.getRightChild().getPriority()) {
                    node = rotateWithLeftChild(node);
                } else {
                    node = rotateWithRightChild(node);
                }
                if(node != nullNode) {
                    node = remove(x, node);
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
}
