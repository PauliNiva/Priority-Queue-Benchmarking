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
     * Gets the size of the heap.
     * @return size of the heap as int.
     */
    @Override
    public int getHeapSize() {
        return heapSize;
    }

    /**
     * Inserts a new node into the heap.
     * @param node node that is being inserted.
     */
    @Override
    public void insert(Node node) {
        if (min != null) {
            node.right = min;
            node.left = min.left;
            min.left = node;
            node.left.right = node;
            if (node.value < min.value) {
                min = node;
            }
        } else {
            min = node;
        }
        heapSize++;
    }

    /**
     * Inserts a new node with value into the heap.
     * @param value value of the node that is being added.
     */
    @Override
    public void insert(int value) {
        int x = value;
        Node node = new Node(x, value, value);
        if (min != null) {
            node.right = min;
            node.left = min.left;
            min.left = node;
            node.left.right = node;
            if (value < min.value) {
                min = node;
            }
        } else {
            min = node;
        }
        heapSize++;
    }

    /**
     * Finds the minimum node of the Fibonacci heap.
     * @return minimum node.
     */
    @Override
    public Node findMinNode() {
        return min;
    }

    /**
     * Finds the value of the minimum node.
     * @return value of the minimum node as int.
     */
    @Override
    public int findMinValue() {
        return min.value;
    }

    /**
     * Deletes the minimum node.
     * Then the method consolidates the trees if necessary.
     * @return the deleted minimum node.
     */
    @Override
    public Node deleteMin() {
        Node tmp = min;
        if (tmp == null) {
            return null;
        }
        if (tmp.child != null) {
            tmp.child.parent = null;
            for (Node x = tmp.child.right; x != tmp.child; x = x.right) {
                x.parent = null;
            }
            Node minLeft = min.left;
            Node tmpChildLeft = tmp.child.left;
            min.left = tmpChildLeft;
            tmpChildLeft.right = min;
            tmp.child.left = minLeft;
            minLeft.right = tmp.child;
        }
        tmp.left.right = tmp.right;
        tmp.right.left = tmp.left;
        if (tmp == tmp.right) {
            min = null;
        } else {
            min = tmp.right;
            consolidate();
        }
        heapSize--;
        return tmp;
    }

    /**
     * Method to decrease the value of a node.
     * @param node whose value is being decreased.
     * @param newValue the new value being inserted to a node.
     */
    public void decreaseKey(Node node, int newValue) {
        decreaseKey(node, newValue, false);
    }

    /**
     * Decrease the key value of a node, or bubble it up to the
     * top of the heap for a delete operation.
     * @param node node whose key is being decreased.
     * @param newValue new key value for the node.
     * @param delete true if deleting node, false otherwise.
     */
    private void decreaseKey(Node node, int newValue, boolean delete) {
        if (!delete && newValue > node.value) {
        } else {
            node.value = newValue;
            Node tmp = node.parent;
            if (tmp != null && (delete || newValue < tmp.value)) {
                tmp.cut(node, min);
                tmp.cascadingCut(min);
            }
            if (delete || newValue < min.value) {
                min = node;
            }
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
        heapSize = 0;
    }

    /**
     * Method that merges two heaps together into a brand new one.
     * @param heap1 heap to be merged.
     * @param heap2 heap to be merged.
     * @return new heap where the two old ones are merged.
     */
    public static FibonacciHeap merge(FibonacciHeap heap1, FibonacciHeap heap2) {
        FibonacciHeap newHeap = new FibonacciHeap();
        if (heap1 != null && heap2 != null) {
            newHeap.min = heap1.min;
            if (newHeap.min != null) {
                if (heap2.min != null) {
                    newHeap.min.right.left = heap2.min.left;
                    heap2.min.left.right = newHeap.min.right;
                    newHeap.min.right = heap2.min;
                    heap2.min.left = newHeap.min;
                    if (heap2.min.value < heap1.min.value) {
                        newHeap.min = heap2.min;
                    }
                }
            } else {
                newHeap.min = heap2.min;
            }
            newHeap.heapSize = heap1.heapSize + heap2.heapSize;
        }
        return newHeap;
    }

    /**
     * Consolidates the trees in the heap by joining trees of equal degree.
     */
    private void consolidate() {
        Node[] degreeArray = new Node[45];
        Node start = min;
        Node tmp = min;
        do {
            Node tmp1 = tmp;
            Node tmpNext = tmp.right;
            int degree = tmp1.degree;
            while (degreeArray[degree] != null) {
                Node tmp2 = degreeArray[degree];
                if (tmp1.value > tmp2.value) {
                    Node tmp3 = tmp2;
                    tmp2 = tmp1;
                    tmp1 = tmp3;
                }
                if (tmp2 == start) {
                    start = start.right;
                }
                if (tmp2 == tmpNext) {
                    tmpNext = tmpNext.right;
                }
                tmp2.link(tmp1);
                degreeArray[degree] = null;
                degree++;
            }
            degreeArray[degree] = tmp1;
            tmp = tmpNext;
        } while (tmp != start);
        min = start;
        for (Node a : degreeArray) {
            if (a != null && a.value < min.value) {
                min = a;
            }
        }
    }

    /**
     * Deletes a node from the heap.
     * @param node node that is being removed from heap.
     */
    public void delete(Node node) {
        decreaseKey(node, 0, true);
        deleteMin();
    }
}