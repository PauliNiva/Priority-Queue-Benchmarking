package datastructures;


/**
 * Fibonacci heap is a heap structure that consists a forest of trees.
 * Compared to binomial trees the structure of this heap is more flexible.
 * The trees do not have to be a prescribed shape.
 * This allows postponing the work of some operations to later operations.
 */
public class FibonacciHeap implements Heap {

    private Node min;
    private int heapSize;

    /**
     * Initializes a new empty Fibonacci heap.
     */
    public FibonacciHeap() {
        min = null;
        heapSize = 0;
    }

    public int getHeapSize() {
        return heapSize;
    }

    /**
     * Inserts a new node into the heap.
     * @param node node that is being inserted.
     */
    @Override
    public void insert(Node node) {
        if (isEmpty()) {
            min = node;
        } else {
            int value = node.getValue();
            node.setRight(min);
            node.setLeft(min.getLeft());
            min.setLeft(node);
            node.getLeft().setRight(node);
            if (value < min.getValue()) {
                min = node;
            }
        }
        heapSize++;
    }

    /**
     * Inserts a new node with value into the heap.
     * @param value value of the node that is being added.
     */
    @Override
    public void insert(int value) {
        Node node = new Node(value);
        insert(node);
    }

    /**
     * Finds the minimum node of the Fibonacci heap.
     * @return minimum node.
     */
    @Override
    public Node findMinNode() {
        if (heapSize == 0){
            return null;
        }
        return min;
    }

    /**
     * Finds the value of the minimum node.
     * @return value of the minimum node as int.
     */
    @Override
    public int findMinValue() {
        return min.getValue();
    }

    /**
     * Deletes the minimum node.
     * Then the method consolidates the trees if necessary.
     * @return the deleted minimum node.
     */
    @Override
    public Node deleteMin() {
        Node tmp = min;
        if (isEmpty()) {
            return null;
        } else {
            if (tmp.getChild() != null) {
                tmp.getChild().setParent(null);
                Node loopPreventer = tmp.getChild().getRight();
                for (Node node = tmp.getChild().getRight(); node != tmp.getChild(); node = node.getRight()) {
                    if (loopPreventer.equals(node)) {
                        break;
                    } else {
                        node.setParent(null);
                    }
                }
                Node minLeft = min.getLeft();
                Node tmpChildLeft = tmp.getChild().getLeft();
                min.setLeft(tmpChildLeft);
                tmpChildLeft.setRight(min);
                tmp.getChild().setLeft(minLeft);
                minLeft.setRight(tmp.getChild());
            }
            tmp.getLeft().setRight(tmp.getRight());
            tmp.getRight().setLeft(tmp.getLeft());
            if (tmp == tmp.getRight()) {
                min = null;
            } else {
                min = tmp.getRight();
                consolidateTrees();
            }
            heapSize--;
            return tmp;
        }
    }

    /**
     * Method to decrease the value of a node.
     * @param node whose value is being decreased.
     * @param newValue the new value being inserted to a node.
     */
    public void decreaseKey(Node node, int newValue) {
        node.setValue(newValue);
        Node parent = node.getParent();
        if ((parent != null) && (node.getValue() < parent.getValue())) {
            cut(node, parent);
            cascadeCut(parent);
        }
        if (min == null || node.getValue() < min.getValue()) {
            min = node;
        }
    }

    /**
     * Checks if the heap is empty.
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
     * Method that merges two heaps together into a brand new one.
     * @param heap1 heap to be merged.
     * @param heap2 heap to be merged.
     * @return new heap where the two old ones are merged.
     */
    public FibonacciHeap merge(FibonacciHeap heap1, FibonacciHeap heap2) {
        FibonacciHeap mergedHeap = new FibonacciHeap();
        if (heap1 != null && heap2 != null) {
            mergedHeap.min = heap1.min;
            if (mergedHeap.min != null) {
                if (heap2.min != null) {
                    mergedHeap.min.getRight().setLeft(heap2.min.getLeft());
                    heap2.min.getLeft().setRight(mergedHeap.min.getRight());
                    mergedHeap.min.setRight(heap2.min);
                    heap2.min.setLeft(mergedHeap.min);
                    if (heap2.min.getValue() < heap1.min.getValue()) {
                        mergedHeap.min = heap2.min;
                    }
                }
            } else {
                mergedHeap.min = heap2.min;
            }
        }
        return mergedHeap;
    }

    /**
     * Consolidates the trees in the heap by joining trees of equal degree.
     */
    public void consolidateTrees() {
        Node[] degreeArray = new Node[45];
        Node start = min;
        Node tmp = min;
        do {
            Node tmp1 = tmp;
            Node tmpNext = tmp.getRight();
            int degree = tmp1.getDegree();
            while (degreeArray[degree] != null) {
                Node tmp2 = degreeArray[degree];
                if (tmp1.getValue() > tmp2.getValue()) {
                    Node tmp3 = tmp2;
                    tmp2 = tmp1;
                    tmp1 = tmp3;
                }
                if (tmp2 == start) {
                    start = start.getRight();
                }
                if (tmp2 == tmpNext) {
                    tmpNext = tmpNext.getRight();
                }
                tmp2.link(tmp1);
                degreeArray[degree] = null;
                degree++;
            }
            degreeArray[degree] = tmp1;
            tmp = tmpNext;
        } while (tmp != start);
        min = start;
        for (Node node : degreeArray) {
            if (node != null && node.getValue() < min.getValue()) {
                min = node;
            }
        }
    }

    /**
     * Method that cuts the node and puts it in root list.
     * @param node that is being removed.
     * @param parent parent node of the node.
     */
    public void cut(Node node, Node parent) {
        node.getLeft().setRight(node.getRight());
        node.getRight().setLeft(node.getLeft());
        parent.setDegree(parent.getDegree() + 1);
        if (parent.getChild() == node) {
            parent.setChild(node.getRight());
        }
        if (parent.getDegree() == 0) {
            parent.setChild(null);
        }
        node.setLeft(min);
        node.setRight(min.getRight());
        min.setRight(node);
        node.getRight().setLeft(node);
        node.setParent(null);
        node.decolor();
    }

    /**
     * Method that travels the tree upwards cutting all the
     * nodes that are colored and adds them to root list.
     * Stops at the first uncolored node ands colors it.
     * @param node where the cutting begins.
     */
    public void cascadeCut(Node node) {
        Node parent = node.getParent();
        if (parent != null) {
            if (!node.getColored()) {
                node.color();
            } else {
                cut(node, parent);
                cascadeCut(parent);
            }
        }
    }
}

