package datastructures;

/**
 * Binomial heap is a heap similar to a binary heap but also supports quick merging of two heaps
 * by using a binomial tree structure. These have to satisfy the binomial heap properties that are,
 * that each binomial tree in a heap obeys the heap-heap property and that there can only be either
 * one or zero binomial trees for each order, including zero order.
 */
public class BinomialHeap implements Heap {

    private Node heap;
    int heapSize;

    /**
     * Initializes a new empty binomial heap.
     */
    public BinomialHeap() {
        heap = null;
    }

    public int getHeapSize() {
        return heapSize;
    }

    /**
     * Inserts a node into the heap by merging with
     * existing tree.
     * @param node node that is being added.
     */
    @Override
    public void insert(Node node) {
        if (isEmpty()) {
            heap = node;
            heapSize++;
        } else {
            merge(node);
            heapSize++;
        }
    }

    /**
     * Inserts a new node of value x into the heap
     * by merging it with the existing tree.
     * @param value value of the node that is being added.
     */
    @Override
    public void insert(int value) {
        Node node = new Node(value);
        if (isEmpty()) {
            heap = node;
            heapSize++;
        } else {
            merge(node);
            heapSize++;
        }
    }

    /**
     * Finds the minimum amongst the roots of the binomial trees.
     * @return minimum heap.
     */
    public Node findMinNode() {
        Node tmp = heap;
        Node minRoot = heap;
        int minValue;
        if (heap != null) {
            minValue = tmp.getValue();
        } else {
            minValue = Integer.MAX_VALUE;
        }
        while (tmp != null) {
            if (tmp.getValue() < minValue) {
                minRoot = tmp;
                minValue = minRoot.getValue();
            }
            tmp = tmp.getSibling();
        }
        return minRoot;
    }

    /**
     * Finds tha value of minimum node.
     * @return value of heap node as int.
     */
    @Override
    public int findMinValue() {
        if (findMinNode() != null) {
            return findMinNode().getValue();
        } else {
            return Integer.MAX_VALUE;
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
            Node minRoot = findMinNode();
            Node tmp = heap;
            Node tmpPrev = null;
            if (minRoot != null || tmp != null) {
                while (minRoot.getValue() != tmp.getValue()) {
                    tmpPrev = tmp;
                    tmp = tmp.getSibling();
                }
                if (tmpPrev == null) {
                    heap = tmp.getSibling();
                } else {
                    tmpPrev.setSibling(tmp.getSibling());
                }
                tmp = tmp.getChild();
            }
            Node tmpOriginal = tmp;
            while (tmp != null) {
                tmp.setParent(null);
                tmp = tmp.getSibling();
            }
            if ((heap == null) && (tmpOriginal == null)) {
            } else {
                if (tmpOriginal == null) {
                } else {
                    if (heap == null) {
                        heap = tmpOriginal.reverseRootList(null);
                    } else {
                        merge(tmpOriginal.reverseRootList(null));
                    }
                }
            }
            heapSize--;
            return minRoot;
        }
    }

    /**
     * Method for decreasing the value of the node.
     * @param node node whose values are being decreased.
     * @param newValue value to which the value of the node
     *                 is being decreased to.
     */
    @Override
    public void decreaseKey(Node node, int newValue) {
        if (node.getValue() > newValue) {
            node.setValue(newValue);
            while (node.getParent() != null && node.getParent().getValue() > newValue) {
                int value = node.getValue();
                int dijkstraPriority = node.getDijkstraPriority();
                node.setValue(node.getParent().getValue());
                node.setDijkstraPriority(node.getParent().getDijkstraPriority());
                node.getParent().setValue(value);
                node.getParent().setDijkstraPriority(dijkstraPriority);
                node = node.getParent();
            }
            if (heap == null || node.getValue() < heap.getValue()) {
                heap = node;
            }
        }
    }

    /**
     * Checks if the heap is empty.
     * @return true if the heap is empty, false otherwise.
     */
    public boolean isEmpty() {
        return getHeapSize() == 0;
    }

    /**
     * Empties the heap.
     */
    public void clear() {
        heap = null;
        heapSize = 0;
    }

    /**
     * Method to merge two heaps.
     * @param heap that is being merged.
     */
    public void merge(Node heap) {
        Node tmp1 = this.heap;
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
                    if (tmp == this.heap) {
                        this.heap = tmp1;
                    }
                }
            }
        }
        if (tmp1 == null) {
            tmp1 = this.heap;
            while (tmp1.getSibling() != null) {
                tmp1 = tmp1.getSibling();
            }
            tmp1.setSibling(tmp2);
        }
        Node tmp = this.heap;
        Node tmpNext = this.heap.getSibling();
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
                        this.heap = tmpNext;
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
}
