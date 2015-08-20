package datastructures;

/**
 * Binary heap is implemented as a binary tree with two additional constraints:
 * It conserves the heap property and the heap is a complete binary tree.
 */
public class BinaryHeap implements Heap {

    private Node[] array;
    private int heapSize;
    private int capacity;
    private static final int ROOT = 0;

    /**
     * Initializes a new empty array.
     * @param arraySize size of the array - how many elements the array can contain.
     */
    public BinaryHeap(int arraySize) {
        capacity = arraySize;
        array =  new Node[capacity];
        heapSize = 0;
    }

    /**
     * Inserts node to the heap.
     * It does this by putting the node in after the last node in array.
     * Then it heaps it up until the heap property is restored.
     * @param node that is being inserted.
     */
    @Override
    public void insert(Node node) {
        array[heapSize] = node;
        siftUp(heapSize);
        heapSize++;
    }

    /**
     * Inserts node to the heap.
     * It does this by creating a new node with value as its parameter
     * and then puts the node in after the last node in array.
     * Then it heaps it up until the heap property is restored.
     * @param value that is being inserted.
     */
    @Override
    public void insert(int value) {
        Node node = new Node(value);
        array[heapSize] = node;
        siftUp(heapSize);
        heapSize++;
    }

    /**
     * Finds the node with minimum value in the array.
     * @return if the array is not empty, it returns the node
     * which is at the root. If the array is empty, it returns null.
     */
    @Override
    public Node findMin() {
        if (isEmpty()) {
            return null;
        } else {
            return array[ROOT];
        }
    }

    /**
     * Finds the minimum value in the array.
     * @return if the array is not empty, it returns the value of minimum value
     * which is at the root. If the array is empty, it returns null.
     */
    @Override
    public int findMinimum() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        } else {
            return array[ROOT].getValue();
        }
    }

    /**
     * Removes the minimum node.
     * It does this by extracting the root, then moving the last node into the roots
     * place and then heaping it down until the heap property is restored.
     * @return the minimum node.
     */
    @Override
    public Node deleteMin() {
        Node removed;
        if (isEmpty()) {
            return null;
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
     * Method for decreasing the value of a specific index.
     * @param index index at which the value that is being decreased resides.
     * @param newValue new value that is being inserted.
     */
    @Override
    public void decreaseKey(int index, int newValue) {
        if (array[index].getValue() > newValue) {
            array[index].setValue(newValue);
            siftUp(index);
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
     * Checks if the array is empty.
     * @return true if the array is empty, false otherwise.
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * Method that moves the node recursively up the tree until the
     * heap property is restored.
     * @param index place where the value that is being moved is at.
     */
    public void siftUp(int index) {
        int parentIndex;
        Node tmp;
        if (index != 0) {
            parentIndex = getParentIndex(index);
            if (array[parentIndex].getValue() > array[index].getValue()) {
                tmp = array[parentIndex];
                array[parentIndex] = array[index];
                array[index] = tmp;
                siftUp(parentIndex);
            }
        }
    }

    /**
     * Method that moves the node down in the tree until the
     * heap property is restored.
     * @param index place where the value that is being moved is at.
     */
    public void siftDown(int index) {
        int leftChildIndex = getLeftChildIndex(index);
        int rightChildIndex = getRightChildIndex(index);
        int minIndex;
        Node tmp;
        if (rightChildIndex >= heapSize) {
            if (leftChildIndex >= heapSize) {
                return;
            } else {
                minIndex = leftChildIndex;
            }
        } else {
            if (array[leftChildIndex].getValue() <= array[rightChildIndex].getValue()) {
                minIndex = leftChildIndex;
            } else {
                minIndex = rightChildIndex;
            }
        }
        if (array[index].getValue() > array[minIndex].getValue()) {
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
     * Gets the index of the left child.
     * @param index index of the value whose left child is being get.
     * @return index of the leftChild child as int.
     */
    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    /**
     * Gets the index of the right child.
     * @param index index of the value whose rightChild child is being get.
     * @return index of the right child as int.
     */
    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    /**
     * Gets the elements parent index.
     * @param index index of the value whose getParent is being get.
     * @return index of the getParent as int.
     */
    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }
}
