package datastructures;

/**
 * Binomial heap is a heap similar to a binary heap but also supports quick merging of two heaps
 * by using a binomial tree structure. These have to satisfy the binomial heap properties that are,
 * that each binomial tree in a heap obeys the min-heap property and that there can only be either
 * one or zero binomial trees for each order, including zero order.
 */
public class BinomialHeap implements Heap {

    private Node min;

    /**
     * Initializes a new empty binomial heap.
     */
    public BinomialHeap() {
        min = null;
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
     * Inserts a new node of value x into the min
     * by merging it with the existing tree.
     * @param x value of the node that is being added.
     */
    @Override
    public void insert(int x) {
            Node node = new Node(x);
            if (isEmpty()) {
                min = node;
            } else {
                merge(node);
            }
    }

    /**
     * Finds the minimum node in the heap.
     * @return minimum node.
     */
    @Override
    public Node findMin() {
        return findMinRoot();
    }

    /**
     * Finds tha value of minimum node.
     * @return value of min node as int.
     */
    @Override
    public int findMinimum() {
        if (findMinRoot() != null) {
            return findMinRoot().getValue();
        } else {
            return Integer.MIN_VALUE;
        }
    }

    /**
     * Method to delete the node with a minimum value.
     * @return the minimum node.
     */
    @Override
    public Node deleteMin() {
        if (isEmpty()) {
            return null;
        } else {
            Node minRoot = findMinRoot();
            Node tmp = min;
            Node tmpPrev = null;
            while (minRoot.getValue() != tmp.getValue()) {
                tmpPrev = tmp;
                tmp = tmp.getSibling();
            }
            if (tmpPrev == null) {
                min = tmp.getSibling();
            } else {
                tmpPrev.setSibling(tmp.getSibling());
            }
            tmp = tmp.getChild();
            Node tmpOriginal = tmp;
            while (tmp != null) {
                tmp.setParent(null);
                tmp = tmp.getSibling();
            }
            if ((min == null) && (tmpOriginal == null)) {
            } else {
                if (tmpOriginal == null) {
                } else {
                    if (min == null) {
                        min = tmpOriginal.reverseRootList(null);
                    } else {
                        merge(tmpOriginal.reverseRootList(null));
                    }
                }
            }
            return minRoot;
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
     * Method to decrease the value of a node.
     * @param oldValue value of the node that is being decreased.
     * @param newValue the new value that is being inserted.
     */
    public void decreaseKey(int oldValue, int newValue) {
        Node tmp = min.findANodeWithValue(oldValue);
        if (tmp == null || oldValue <= newValue) {
            return;
        }
        tmp.setValue(newValue);
        Node tmpParent = tmp.getParent();
        while ((tmpParent != null) && (tmp.getValue() < tmpParent.getValue())) {
            int i = tmp.getValue();
            tmp.setValue(tmpParent.getValue());
            tmpParent.setValue(i);
            tmp = tmpParent;
            tmpParent = tmpParent.getParent();
        }
    }

    /**
     * Checks if the min is empty.
     * @return true if the min is empty, false otherwise.
     */
    public boolean isEmpty() {
        return min == null;
    }

    /**
     * Empties the heap.
     */
    public void clear() {
        min = null;
    }

    /**
     * Method to merge two heaps.
     * @param heap that is being merged.
     */
    public void merge(Node heap) {
        Node tmp1 = min;
        Node tmp2 = heap;
        while ((tmp1 != null) && (tmp2 != null)) {
            if (tmp1.getDegree() == tmp2.getDegree()) {
                Node tmp = tmp2;
                tmp2 = tmp2.getSibling();
                tmp.setSibling(tmp1.getSibling());
                tmp1.setSibling(tmp);
                tmp1 = tmp.getSibling();
            } else {
                if (tmp1.getDegree() < tmp2.getDegree()) {
                    if ((tmp1.getSibling() == null) || (tmp1.getSibling().getDegree() > tmp2.getDegree())) {
                        Node tmp = tmp2;
                        tmp2 = tmp2.getSibling();
                        tmp.setSibling(tmp1.getSibling());
                        tmp1.setSibling(tmp);
                        tmp1 = tmp.getSibling();
                    } else {
                        tmp1 = tmp1.getSibling();
                    }
                } else {
                    Node tmp = tmp1;
                    tmp1 = tmp2;
                    tmp2 = tmp2.getSibling();
                    tmp1.setSibling(tmp);
                    if (tmp == min) {
                        min = tmp1;
                    }
                }
            }
        }
        if (tmp1 == null) {
            tmp1 = min;
            while (tmp1.getSibling() != null) {
                tmp1 = tmp1.getSibling();
            }
            tmp1.setSibling(tmp2);
        }
        Node tmp = min;
        Node tmpNext = min.getSibling();
        Node tmpPrev = null;
        while (tmpNext != null) {
            if ((tmp.getDegree() != tmpNext.getDegree()) || ((tmpNext.getSibling() != null)
                    && (tmpNext.getSibling().getDegree() == tmp.getDegree()))) {
                tmpPrev = tmp;
                tmp = tmpNext;
            } else {
                if (tmp.getValue() <= tmpNext.getValue()) {
                    tmp.setSibling(tmpNext.getSibling());
                    tmpNext.setParent(tmp);
                    tmpNext.setSibling(tmp.getChild());
                    tmp.setChild(tmpNext);
                    tmp.setDegree(tmp.getDegree() + 1);
                } else {
                    if (tmpPrev == null) {
                        min = tmpNext;
                    } else {
                        tmpPrev.setSibling(tmpNext);
                    }
                    tmp.setParent(tmpNext);
                    tmp.setSibling(tmpNext.getChild());
                    tmpNext.setChild(tmp);
                    tmpNext.setDegree(tmpNext.getDegree() + 1);
                    tmp = tmpNext;
                }
            }
            tmpNext = tmp.getSibling();
        }
    }

    /**
     * Finds the minimum amongst the roots of the binomial trees.
     * @return minimum min.
     */
    public Node findMinRoot() {
        Node x = min;
        Node minRoot = min;
        int minValue;
        if (min != null) {
            minValue = x.getValue();
        } else {
            minValue = Integer.MIN_VALUE;
        }
        while (x != null) {
            if (x.getValue() < minValue) {
                minRoot = x;
                minValue = minRoot.getValue();
            }
            x = x.getSibling();
        }
        return minRoot;
    }
}
