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
     * Inserts value x into the treap.
     * If x is already in treap it does nothing.
     * @param x value of the node that is being inserted.
     */
    @Override
    public void insert(int x) {
        root = insert(x, root);
    }

    /**
     * Finds the minimum in the treap.
     * @return if the treap is empty returns Integer.MAX_VALUE, minimum value as int otherwise.
     */
    @Override
    public int findMin() {
        if (isEmpty()) {
            return Integer.MAX_VALUE;
        }
        Node pointer = root;
        while (pointer.leftChild != nullNode) {
            pointer = pointer.leftChild;
        }
        return pointer.value;
    }

    /**
     * Method to delete min node.
     * @return value of min node as int.
     */
    @Override
    public int deleteMin() {
        int min = findMin();
        remove(findMin());
        return min;
    }

    /**
     * Checks if the treap is empty.
     * @return true if the heap is empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == nullNode;
    }

    /**
     * Auxiliary method that inserts x into a subtreap.
     * @param x the that is being inserted.
     * @param node root node of the (sub)tree.
     * @return the new root.
     */
    private Node insert(int x, Node node) {
        if (node == nullNode) {
            node = new Node(x, nullNode, nullNode);
        } else if (x - node.value < 0) {
            node.leftChild = insert(x, node.leftChild);
            if (node.leftChild.priority < node.priority) {
                node = rotateWithLeftChild(node);
            }
        } else if (x - node.value > 0) {
            node.rightChild = insert(x, node.rightChild);
            if (node.rightChild.priority < node.priority) {
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
            if (x - node.value < 0) {
                node.leftChild = remove(x, node.leftChild);
            } else if (x - node.value > 0) {
                node.rightChild = remove(x, node.rightChild);
            } else {
                if (node.leftChild.priority < node.rightChild.priority) {
                    node = rotateWithLeftChild(node);
                } else {
                    node = rotateWithRightChild(node);
                }
                if(node != nullNode) {
                    node = remove(x, node);
                } else {
                    node.leftChild = nullNode;
                }
            }
        }
        return node;
    }

    /**
     * Rotates node with its leftChild child.
     */
    private Node rotateWithLeftChild(Node node1) {
        Node node2 = node1.leftChild;
        node1.leftChild = node2.rightChild;
        node2.rightChild = node1;
        return node2;
    }

    /**
     *  Rotates node with its rightChild child.
     */
    private Node rotateWithRightChild(Node node1) {
        Node node2 = node1.rightChild;
        node1.rightChild = node2.leftChild;
        node2.leftChild = node1;
        return node2;
    }

    /**
     * Class for treap nodes.
     */
    static class Node {

        int value;
        int priority;
        Node leftChild;
        Node rightChild;

        /**
         * Initializes a new node with x as value.
         * and children as nulls.
         * @param x value of the new node.
         */
        public Node(int x) {
            value = x;
            leftChild = null;
            rightChild = null;
            priority = new Random().nextInt();
        }

        /**
         * Initializes a new node with x as value.
         * and children named left and right.
         * @param x value of the new node.
         */
        public Node(int x, Node left , Node right) {
            value  = x;
            leftChild = left;
            rightChild = right;
            priority = new Random().nextInt();
        }

        /**
         * Initializes a new null node.
         * @param x Value of null node.
         * @param y Priority of null node.
         */
        public Node(int x, int y) {
            value  = x;
            leftChild = null;
            rightChild = null;
            priority = y;
        }
    }
}
