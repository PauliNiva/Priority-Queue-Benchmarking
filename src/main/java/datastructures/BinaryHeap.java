package datastructures;

/**
 * Binary heap is implemented as a binary tree with two additional constraints:
 * It conserves the heap property and the heap is a complete binary tree.
 */
public class BinaryHeap implements Heap {

    private int[] array;
    private int heapSize;
    private int capacity;
    private static final int ROOT = 0;

    /**
     * Initializes a new empty array.
     * @param arraySize size of the array - how many elements the array can contain.
     */
    public BinaryHeap(int arraySize) {
        capacity = arraySize;
        array =  new int[capacity];
        heapSize = 0;
    }

    /**
     * Inserts value to the heap.
     * It does this by putting the value in after the arrays last value.
     * Then it heaps it up until the heap property is restored.
     * @param x int that is being inserted.
     */
    @Override
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
    @Override
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
    @Override
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
        int leftChildIndex = getLeftChildIndex(index);
        int rightChildIndex = getRightChildIndex(index);
        int minIndex;
        int tmp;
        if (rightChildIndex >= heapSize) {
            if (leftChildIndex >= heapSize) {
                return;
            } else {
                minIndex = leftChildIndex;
            }
        } else {
            if (array[leftChildIndex] <= array[rightChildIndex]) {
                minIndex = leftChildIndex;
            } else {
                minIndex = rightChildIndex;
            }
        }
        if (array[index] > array[minIndex]) {
            tmp = array[minIndex];
            array[minIndex] = array[index];
            array[index] = tmp;
            siftDown(minIndex);
        }
    }

    /**
     * Gets the size of the array.
     * @return number of elements that the array contains as int.
     */
    public int size() {
        return heapSize;
    }

    /**
     * Gets the index of the leftChild child.
     * @param index index of the value whose leftChild child is being get.
     * @return index of the leftChild child as int.
     */
    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    /**
     * Gets the index of the rightChild child.
     * @param index index of the value whose rightChild child is being get.
     * @return index of the rightChild child as int.
     */
    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    /**
     * Gets the elements getParent index.
     * @param index index of the value whose getParent is being get.
     * @return index of the getParent as int.
     */
    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    /**
     * Method for decreasing the value of a specific index.
     * @param index index at which the value that is being decreased resides.
     * @param i new value that is being inserted.
     */
    public void decreaseKey(int index, int i) {
        if (array[index] > i) {
            array[index] = i;
            siftUp(index);
        }
    }
}
