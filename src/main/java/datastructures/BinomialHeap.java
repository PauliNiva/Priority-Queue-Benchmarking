package datastructures;


public class BinomialHeap {

    private Node root;
    private int heapSize;

    /**
     * Initializes a new empty root.
     */
    public BinomialHeap() {
        root = null;
        heapSize = 0;
    }

    /**
     * Inserts a new node of value x into the root
     * by merging it with the existing tree.
     * @param x value of the node that is being added.
     */
    public void insert(int x) {
            Node node = new Node(x);
            if (isEmpty()) {
                root = node;
                heapSize++;
            } else {
                merge(node);
                heapSize++;
            }
    }

    /**
     * Finds the minimum of the binomial root.
     * @return value of the minimum root as int.
     */
    public int findMin() {
        return findMinRoot().value;
    }

    /**
     * Checks if the root is empty.
     * @return true if the root is empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Finds the minimum amongst the roots of the binomial trees.
     * @return minimum root.
     */
    public Node findMinRoot() {
        Node x = root;
        Node minRoot = root;
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
     * Method to merge two heaps.
     * @param heap that is being merged.
     */
    private void merge(Node heap) {
        Node tmp1 = root;
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
                    if (tmp == root) {
                        root = tmp1;
                    }
                }
            }
        }
        if (tmp1 == null) {
            tmp1 = root;
            while (tmp1.sibling != null) {
                tmp1 = tmp1.sibling;
            }
            tmp1.sibling = tmp2;
        }
        Node tmp = root;
        Node tmpNext = root.sibling;
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
                        root = tmpNext;
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
     * Class for binomial root nodes.
     */
    class Node {
        int value;
        int degree;
        Node parent;
        Node child;
        Node sibling;

        /**
         * Initializes a new root with value x.
         * @param x value that the root is initialized with.
         */
        public Node(int x) {
            value = x;
            degree = 0;
            parent = null;
            child = null;
        }
    }
}
