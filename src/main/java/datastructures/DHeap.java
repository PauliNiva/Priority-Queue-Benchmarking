package datastructures;

/**
 * D-Heap is a generalization of a binary heap in which the nodes have d children instead of two.
 */
public class DHeap implements Heap {

    private Node[] array;
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
        array =  new Node[capacity];
        heapSize = 0;
        d = numberOfChildren;
    }

    public int getHeapSize() {
        return heapSize;
    }

    /**
     * Inserts a node to the heap.
     * This is done by putting it in after the arrays last node.
     * Then it heaps it up until the heap property is restored.
     * @param node node that is being inserted.
     */
    @Override
    public void insert(Node node) {
        node.setIndex(heapSize);
        array[heapSize] = node;
        siftUp(heapSize);
        heapSize++;
    }

    /**
     * Inserts value to the heap.
     * It does this by creating a new node with value and putting
     * it in after the arrays last node. Then it heaps it up until
     * the heap property is restored.
     * @param value int that is being inserted.
     */
    @Override
    public void insert(int value) {
        Node node = new Node(value);
        node.setIndex(heapSize);
        array[heapSize] = node;
        siftUp(heapSize);
        heapSize++;
    }

    /**
     * Finds the minimum node in the array.
     * @return if the array is not empty, it returns the minimum node
     * which is at the root. If the array is empty, it returns null.
     */
    @Override
    public Node findMinNode() {
        if (isEmpty()) {
            return null;
        } else {
            return array[ROOT];
        }
    }

    /**
     * Finds the value of the minimum node
     * @return if the heap is not empty, it return the value of the root,
     * otherwise it returns the Integer min value.
     */
    @Override
    public int findMinValue() {
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
            array[heapSize - 1].setIndex(ROOT);
            array[ROOT] = array[heapSize - 1];
            heapSize--;
            if (!isEmpty()) {
                siftDown(ROOT);
            }
        }
        return removed;
    }

    /**
     * Method for decreasing the value of a specific node.
     * @param node node whose value is being decreased.
     * @param newValue new value that is being inserted.
     */
    @Override
    public void decreaseKey(Node node, int newValue) {
        decreaseKey(node.getIndex(), newValue);
    }

    /**
     * Method for decreasing the value of a specific index.
     * @param index index at which the value that is being decreased resides.
     * @param newValue new value that is being inserted.
     */
    public void decreaseKey(int index, int newValue) {
        if (array[index].getValue() > newValue) {
            array[index].setValue(newValue);
            siftUp(index);
        }
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
     * @param index place where the node that is being moved is at.
     */
    public void siftUp(int index) {
        int parentIndex;
        Node tmp;
        if (index != 0) {
            parentIndex = getParentIndex(index);
            if (array[parentIndex].getValue() > array[index].getValue()) {
                tmp = array[parentIndex];
                array[index].setIndex(parentIndex);
                array[parentIndex] = array[index];
                tmp.setIndex(index);
                array[index] = tmp;
                siftUp(parentIndex);
            }
        }
    }

    /**
     * Method that moves the node down in the tree until the
     * heap property is restored.
     * @param index place where the node that is being moved is at.
     */
    public void siftDown(int index) {
        int child;
        Node tmp = array[index];
        while (getNthChildIndex(index, 1) < heapSize) {
            child = getMinChild(index);
            if (array[child].getValue() < tmp.getValue()) {
                array[child].setIndex(index);
                array[index] = array[child];
            } else {
                break;
            }
            index = child;
        }
        tmp.setIndex(index);
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
     * Method to get the index of the child node with a min value amongst the children.
     * @param index index of the node whose child with min value is being get.
     * @return index of the child node with min value as int.
     */
    private int getMinChild(int index) {
        int bestCandidate = getNthChildIndex(index, 1);
        int n = 2;
        int position = getNthChildIndex(index, n);
        while ((n <= d) && (position < heapSize)) {
            if (array[position].getValue() < array[bestCandidate].getValue()) {
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
