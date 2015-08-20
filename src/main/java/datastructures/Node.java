package datastructures;


import java.util.Random;

/**
 * Node class for the nodes of every heap.
 *
 * TODO Check for attribute redundancies as time permits (LOW PRIORITY).
 */
public class Node {

    private int node;
    private int value;
    private int sValue;
    private int degree;
    private int priority;
    private Node parent;
    private Node left;
    private Node right;
    private Node child;
    private Node sibling;
    private Node leftChild;
    private Node rightChild;
    private Node nextSibling;
    private Node previousSibling;
    private boolean colored = false;

    /**
     * Initializes a new node with value x.
     * @param x value that is being inserted into the node.
     */
    public Node(int x) {
        value = x;
        degree = 0;
        parent = null;
        child = null;
        left = this;
        right = this;
        leftChild = null;
        rightChild = null;
        previousSibling = null;
        priority = new Random().nextInt();
    }

    /**
     * Constructs the node with value, references to both childs and
     * initial s-value of 0.
     * @param value numerical value being set as int.
     * @param leftChild reference to nodes leftChild child.
     * @param rightChild reference to nodes rightChild child.
     */
    public Node(int value, Node leftChild, Node rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.sValue = 0;
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

    /**
     * This is an auxiliary method for deleteMin method.
     * It reverses the root list.
     * @param tmp the min node of the root list to be reversed.
     * @return the min of the reversed root list.
     */
    public Node reverseRootList(Node tmp) {
        Node newHead;
        if (sibling != null) {
            newHead = sibling.reverseRootList(this);
        } else {
            newHead = this;
        }
        sibling = tmp;
        return newHead;
    }

    /**
     * Method to find a node with a specific value.
     * @param value value of the node that is being searched.
     * @return the node with the speficied value.
     */
    public Node findANodeWithValue(int value) {
        Node node = null;
        Node tmp = this;
        while (tmp != null) {
            if (tmp.value == value) {
                node = tmp;
                break;
            }
            if (tmp.child == null) {
                tmp = tmp.sibling;
            } else {
                node = tmp.child.findANodeWithValue(value);
                if (node == null) {
                    tmp = tmp.sibling;
                } else {
                    break;
                }
            }
        }
        return node;
    }

    /**
     * Gets the node.
     * @return node as int.
     */
    public int getNode(){
        return node;
    }

    /**
     * Sets the node as specific value.
     * @param value integer that is being set.
     */
    public void setNode(int value){
        this.node = value;
    }

    /**
     * Gets the value.
     * @return value as int.
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value
     * @param value integer being set.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Gets the sValue.
     * @return sValue as int.
     */
    public int getSValue() {
        return sValue;
    }

    /**
     * Sets the sValue
     * @param sValue integer being set.
     */
    public void setSValue(int sValue) {
        this.sValue = sValue;
    }

    /**
     * Gets the degree.
     * @return degree as int.
     */
    public int getDegree() {
        return degree;
    }

    /**
     * Sets the degree.
     * @param degree integer being set.
     */
    public void setDegree(int degree) {
        this.degree = degree;
    }

    /**
     * Gets the priority.
     * @return priority as int.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the priority
     * @param value integer being set.
     */
    public void setPriority(int value) {
        priority = value;
    }

    /**
     * Gets the parent node.
     * @return parent node.
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Sets the parent node
     * @param node node being set as parent.
     */
    public void setParent(Node node) {
        parent = node;
    }

    /**
     * Gets the left node.
     * @return node.
     */
    public Node getLeft() {
        return left;
    }

    /**
     * Sets the left node.
     * @param node that is being set.
     */
    public void setLeft(Node node) {
        left = node;
    }

    /**
     * Gets the right node.
     * @return node.
     */
    public Node getRight() {
        return right;
    }

    /**
     * Sets the right node.
     * @param node that is being set.
     */
    public void setRight(Node node) {
        right = node;
    }

    /**
     * Gets the child node.
     * @return node.
     */
    public Node getChild() {
        return child;
    }

    /**
     * Sets the child node.
     * @param node that is being set.
     */
    public void setChild(Node node) {
        child = node;
    }

    /**
     * Gets the sibling node.
     * @return node.
     */
    public Node getSibling() {
        return sibling;
    }

    /**
     * Sets the sibling node.
     * @param node that is being set.
     */
    public void setSibling(Node node) {
        sibling = node;
    }

    /**
     * Gets the left child node..
     * @return node.
     */
    public Node getLeftChild() {
        return leftChild;
    }

    /**
     * Sets the left child node.
     * @param node that is being set.
     */
    public void setLeftChild(Node node) {
        leftChild = node;
    }

    /**
     * Gets the right child node.
     * @return node.
     */
    public Node getRightChild() {
        return rightChild;
    }

    /**
     * Sets the right child node.
     * @param node that is being set.
     */
    public void setRightChild(Node node) {
        rightChild = node;
    }

    /**
     * Gets the next sibling node.
     * @return node.
     */
    public Node getNextSibling() {
        return nextSibling;
    }

    /**
     * Sets the next sibling node.
     * @param node that is being set.
     */
    public void setNextSibling(Node node) {
        nextSibling = node;
    }

    /**
     * Gets the previous sibling node.
     * @return node.
     */
    public Node getPreviousSibling() {
        return previousSibling;
    }

    /**
     * Sets the previous sibling node.
     * @param node that is being set.
     */
    public void setPreviousSibling(Node node) {
        previousSibling = node;
    }

    /**
     * Gets the boolean value if the node is colored
     * or not.
     * @return true, if node is colored, false otherwise.
     */
    public boolean getColored() {
        return colored;
    }

    /**
     * Method that sets the colored value to true;
     */
    public void color() {
        colored = true;
    }

    /**
     * Method that sets the colored value to false.
     */
    public void decolor() {
        colored = false;
    }
}
