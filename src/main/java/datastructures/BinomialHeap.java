package datastructures;

/**
 * Binomial heap is a heap similar to a binary heap but also supports quick merging of two heaps
 * by using a binomial tree structure. These have to satisfy the binomial heap properties that are,
 * that each binomial tree in a heap obeys the min-heap property and that there can only be either
 * one or zero binomial trees for each order, including zero order.
 */
public class BinomialHeap {

    private Node min;

    /**
     * Initializes a new empty binomial heap.
     */
    public BinomialHeap() {
        min = null;
    }

    /**
     * Inserts a new node of value x into the min
     * by merging it with the existing tree.
     * @param x value of the node that is being added.
     */
    public void insert(int x) {
            Node node = new Node(x);
            if (isEmpty()) {
                min = node;
            } else {
                merge(node);
            }
    }

    /**
     * Finds the minimum of the binomial min.
     * @return value of the minimum min as int.
     */
    public int findMin() {
        return findMinRoot().value;
    }

    /**
     * Method to delete the node with a minimum value.
     * @return value of the minimum node as int.
     */
    public int deleteMin() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        } else {
            Node minRoot = findMinRoot();
            Node tmp = min;
            Node tmpPrev = null;
            while (minRoot.value != tmp.value) {
                tmpPrev = tmp;
                tmp = tmp.sibling;
            }
            if (tmpPrev == null) {
                min = tmp.sibling;
            } else {
                tmpPrev.sibling = tmp.sibling;
            }
            tmp = tmp.child;
            Node tmpOriginal = tmp;
            while (tmp != null) {
                tmp.parent = null;
                tmp = tmp.sibling;
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
            return minRoot.value;
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
            if (tmp1.degree == tmp2.degree) {
                Node tmp = tmp2;
                tmp2 = tmp2.sibling;
                tmp.sibling = tmp1.sibling;
                tmp1.sibling = tmp;
                tmp1 = tmp.sibling;
            } else {
                if (tmp1.degree < tmp2.degree) {
                    if ((tmp1.sibling == null) || (tmp1.sibling.degree > tmp2.degree)) {
                        Node tmp = tmp2;
                        tmp2 = tmp2.sibling;
                        tmp.sibling = tmp1.sibling;
                        tmp1.sibling = tmp;
                        tmp1 = tmp.sibling;
                    } else {
                        tmp1 = tmp1.sibling;
                    }
                } else {
                    Node tmp = tmp1;
                    tmp1 = tmp2;
                    tmp2 = tmp2.sibling;
                    tmp1.sibling = tmp;
                    if (tmp == min) {
                        min = tmp1;
                    }
                }
            }
        }
        if (tmp1 == null) {
            tmp1 = min;
            while (tmp1.sibling != null) {
                tmp1 = tmp1.sibling;
            }
            tmp1.sibling = tmp2;
        }
        Node tmp = min;
        Node tmpNext = min.sibling;
        Node tmpPrev = null;
        while (tmpNext != null) {
            if ((tmp.degree != tmpNext.degree) || ((tmpNext.sibling != null) && (tmpNext.sibling.degree == tmp.degree))) {
                tmpPrev = tmp;
                tmp = tmpNext;
            } else {
                if (tmp.value <= tmpNext.value) {
                    tmp.sibling = tmpNext.sibling;
                    tmpNext.parent = tmp;
                    tmpNext.sibling = tmp.child;
                    tmp.child = tmpNext;
                    tmp.degree++;
                } else {
                    if (tmpPrev == null) {
                        min = tmpNext;
                    } else {
                        tmpPrev.sibling = tmpNext;
                    }
                    tmp.parent = tmpNext;
                    tmp.sibling = tmpNext.child;
                    tmpNext.child = tmp;
                    tmpNext.degree++;
                    tmp = tmpNext;
                }
            }
            tmpNext = tmp.sibling;
        }
    }

    /**
     * Finds the minimum amongst the roots of the binomial trees.
     * @return minimum min.
     */
    public Node findMinRoot() {
        Node x = min;
        Node minRoot = min;
        int min = x.value;
        while (x != null) {
            if (x.value < min) {
                minRoot = x;
                min = minRoot.value;
            }
            x = x.sibling;
        }
        return minRoot;
    }


    /**
     * Class for binomial min nodes.
     */
    class Node {
        int value;
        int degree;
        Node parent;
        Node child;
        Node sibling;

        /**
         * Initializes a new min with value x.
         * @param x value that the min is initialized with.
         */
        public Node(int x) {
            value = x;
            degree = 0;
            parent = null;
            child = null;
        }

        /**
         * This is an auxiliary method for deleteMin method.
         * It reverses the root list.
         * @param tmp the min node of the root list to be reversed.
         * @return the min of the reversed root list.
         */
        private Node reverseRootList(Node tmp) {
            Node newHead;
            if (sibling != null) {
                newHead = sibling.reverseRootList(this);
            } else {
                newHead = this;
            }
            sibling = tmp;
            return newHead;
        }
    }
}
