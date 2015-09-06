package datastructures;


import java.util.Random;

/**
 * Node class for the nodes of every heap.
 *
 * TODO Check for attribute redundancies as time permits (LOW PRIORITY).
 */
public class Node {

    public int dijkstraPriority;
    public int value;
    public int sValue;
    public int degree;
    public int priority;
    public Node parent;
    public Node left;
    public Node right;
    public Node child;
    public Node sibling;
    public Node leftChild;
    public Node rightChild;
    public Node nextSibling;
    public Node previousSibling;
    public boolean colored;
    public boolean mark;
    public int index;

    /**
     * Initializes a new dijkstraPriority with value x.
     * @param x value that is being inserted into the dijkstraPriority.
     */
    public Node(int x) {
        index = 0;
        dijkstraPriority = x;
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
        colored = false;
    }

    /**
     * Initializes a new fibonacci heap node.
     * @param x Dijkstra priority of the new node.
     * @param y value of the new node.
     * @param i mock parameter to differentiate the fibonacci node from
     *          new Dijkstra priority.
     */
    public Node(int x, int y, int i) {
        dijkstraPriority = x;
        value = y;
        right = this;
        left = this;
    }

    /**
     * Constructs the dijkstraPriority with value, references to both childs and
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
     * Initializes a new null dijkstraPriority.
     * @param x Value of null dijkstraPriority.
     * @param y Priority of null dijkstraPriority.
     */
    public Node(int x, int y) {
        value  = x;
        leftChild = null;
        rightChild = null;
        priority = y;
    }

    /**
     * Cuts this from its parent and then does the same for its parent
     * and continues all the way to the top of the tree in same manner.
     * @param min heaps minimum node, to which other nodes will be added.
     */
    public void cascadingCut(Node min) {
        Node tmp = parent;
        if (tmp != null) {
            if (mark) {
                tmp.cut(this, min);
                tmp.cascadingCut(min);
            } else {
                mark = true;
            }
        }
    }

    /**
     * Removes the node from the child list of this node.
     * @param node child to be removed from this node's child list
     * @param min the minimum heap node, to which x is added.
     */
    public void cut(Node node, Node min) {
        node.left.right = node.right;
        node.right.left = node.left;
        degree--;
        if (degree == 0) {
            child = null;
        } else if (child == node) {
            child = node.right;
        }
        node.right = min;
        node.left = min.left;
        min.left = node;
        node.left.right = node;
        node.parent = null;
        node.mark = false;
    }

    /**
     * Make this node a child of the given parent node. All links
     * are updated, parents degree is incremented and
     * mark is set to false.
     * @param  parent  the new parent node.
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
        mark = false;
    }

    public int getIndex(){
        return index;
    }

    public void setIndex(int newIndex) {
        index = newIndex;
    }

    /**
     * Gets the dijkstraPriority.
     * @return dijkstraPriority as int.
     */
    public int getDijkstraPriority(){
        return dijkstraPriority;
    }

    /**
     * Sets the dijkstraPriority as specific value.
     * @param value integer that is being set.
     */
    public void setDijkstraPriority(int value){
        this.dijkstraPriority = value;
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
     * Gets the parent dijkstraPriority.
     * @return parent dijkstraPriority.
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Sets the parent dijkstraPriority
     * @param node dijkstraPriority being set as parent.
     */
    public void setParent(Node node) {
        parent = node;
    }

    /**
     * Gets the left dijkstraPriority.
     * @return dijkstraPriority.
     */
    public Node getLeft() {
        return left;
    }

    /**
     * Sets the left dijkstraPriority.
     * @param node that is being set.
     */
    public void setLeft(Node node) {
        left = node;
    }

    /**
     * Gets the right dijkstraPriority.
     * @return dijkstraPriority.
     */
    public Node getRight() {
        return right;
    }

    /**
     * Sets the right dijkstraPriority.
     * @param node that is being set.
     */
    public void setRight(Node node) {
        right = node;
    }

    /**
     * Gets the child dijkstraPriority.
     * @return dijkstraPriority.
     */
    public Node getChild() {
        return child;
    }

    /**
     * Sets the child dijkstraPriority.
     * @param node that is being set.
     */
    public void setChild(Node node) {
        child = node;
    }

    /**
     * Gets the sibling dijkstraPriority.
     * @return dijkstraPriority.
     */
    public Node getSibling() {
        return sibling;
    }

    /**
     * Sets the sibling dijkstraPriority.
     * @param node that is being set.
     */
    public void setSibling(Node node) {
        sibling = node;
    }

    /**
     * Gets the left child dijkstraPriority..
     * @return dijkstraPriority.
     */
    public Node getLeftChild() {
        return leftChild;
    }

    /**
     * Sets the left child dijkstraPriority.
     * @param node that is being set.
     */
    public void setLeftChild(Node node) {
        leftChild = node;
    }

    /**
     * Gets the right child dijkstraPriority.
     * @return dijkstraPriority.
     */
    public Node getRightChild() {
        return rightChild;
    }

    /**
     * Sets the right child dijkstraPriority.
     * @param node that is being set.
     */
    public void setRightChild(Node node) {
        rightChild = node;
    }

    /**
     * Gets the next sibling dijkstraPriority.
     * @return dijkstraPriority.
     */
    public Node getNextSibling() {
        return nextSibling;
    }

    /**
     * Sets the next sibling dijkstraPriority.
     * @param node that is being set.
     */
    public void setNextSibling(Node node) {
        nextSibling = node;
    }

    /**
     * Gets the previous sibling dijkstraPriority.
     * @return dijkstraPriority.
     */
    public Node getPreviousSibling() {
        return previousSibling;
    }

    /**
     * Sets the previous sibling dijkstraPriority.
     * @param node that is being set.
     */
    public void setPreviousSibling(Node node) {
        previousSibling = node;
    }
}
