package datastructures;

/**
 * D-Heap is a generalization of a binary heap in which the nodes have d children instead of two.
 */
public class DHeap {

    private int[] array;
    private int heapSize;
    private int capacity;
    private int d;
    private static final int ROOT = 0;

    /**
     * Initializes a new empty array.
     * @param arraySize size of the array - how many elements the array can contain.
     * @param numberOfChildren number of children an element can have.
     */
    public DHeap(int arraySize, int numberOfChildren) {
        capacity = arraySize;
        array =  new int[capacity];
        heapSize = 0;
        d = numberOfChildren;
    }

    /**
     * Inserts value to the heap.
     * It does this by putting the value in after the arrays last value.
     * Then it heaps it up until the heap property is restored.
     * @param x int that is being inserted.
     */
    public void insert(int x) {
        array[heapSize] = x;
        siftUp(heapSize);
        heapSize++;
    }

    /**
     * Finds the minimum value in the array.
     * @return if the array is not empty, it returns the value of minimum value
     * which is at the root. If the array is empty, it returns null.
     */
    public int findMin() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        } else {
            return array[ROOT];
        }
    }

    /**
     * Removes the minimum value.
     * It does this by extracting the root, then moving the last value in to the roots
     * place and then heaping it down until the heap property is restored.
     * @return the minimum value value as int.
     */
    public int deleteMin() {
        int removed;
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        } else {
            removed = array[ROOT];
            array[ROOT] = array[heapSize - 1];
            heapSize--;
            if (!isEmpty()) {
                siftDown(ROOT);
            }
        }
        return removed;
    }

    /**
     * Checks if the array is empty.
     * @return true if the array is empty, false otherwise.
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * Method that moves the value recursively up the tree until the
     * heap property is restored.
     * @param index place where the value that is being moved is at.
     */
    public void siftUp(int index) {
        int parentIndex;
        int tmp;
        if (index != 0) {
            parentIndex = getParentIndex(index);
            if (array[parentIndex] > array[index]) {
                tmp = array[parentIndex];
                array[parentIndex] = array[index];
                array[index] = tmp;
                siftUp(parentIndex);
            }
        }
    }

    /**
     * Method that moves the value down in the tree until the
     * heap property is restored.
     * @param index place where the value that is being moved is at.
     */
    public void siftDown(int index) {
        int child;
        int tmp = array[index];
        while (getNthChildIndex(index, 1) < heapSize) {
            child = getMinChild(index);
            if (array[child] < tmp) {
                array[index] = array[child];
            } else {
                break;
            }
            index = child;
        }
        array[index] = tmp;
    }

    /**
     * Gets the size of the array.
     * @return number of elements that the array contains as int.
     */
    public int size() {
        return heapSize;
    }

    /**
     * Method to get the index of the child node with a min value.
     * @param index index of the node whose child with min value is being get.
     * @return index of the child node with min value as int.
     */
    private int getMinChild(int index) {
        int bestCandidate = getNthChildIndex(index, 1);
        int n = 2;
        int position = getNthChildIndex(index, n);
        while ((n <= d) && (position < heapSize)) {
            if (array[position] < array[bestCandidate]) {
                bestCandidate = position;
            }
            n++;
            position = getNthChildIndex(index, n);
        }
        return bestCandidate;
    }

    /**
     * Method to get the index of the parent node.
     * @param index wehre the node, whose parent is being get, is at.
     * @return the index of the parent node as int.
     */
    private int getParentIndex(int index) {
        return (index - 1) / d;
    }

    /**
     * Method to get the index of the n:th child.
     * @param index index where the node, whose child is being get, is at.
     * @param n which numerical child is being get.
     * @return the index of the n:th child as int.
     */
    private int getNthChildIndex(int index, int n) {
        return d * index + n;
    }
}
