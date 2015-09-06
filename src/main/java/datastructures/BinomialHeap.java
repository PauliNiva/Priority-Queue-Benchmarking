package datastructures;

/**
 * Binomial heap is a heap similar to a binary heap but also supports quick merging of two heaps
 * by using a binomial tree structure. These have to satisfy the binomial heap properties that are,
 * that each binomial tree in a heap obeys the heap-heap property and that there can only be either
 * one or zero binomial trees for each order, including zero order.
 */
public class BinomialHeap implements Heap {

    private Node head;
    private int heapSize;

    /**
     * Initializes a new empty binomial heap.
     */
    public BinomialHeap() {
        head = null;
        heapSize = 0;
    }

    /**
     * Gets the size of the heap.
     * @return the number of elements in the heap.
     */
    @Override
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
        BinomialHeap heap = new BinomialHeap();
        heap.head = node;
        BinomialHeap newHeap = this.merge(heap);
        head = newHeap.head;
        heapSize++;
    }

    /**
     * Inserts a new node of value x into the heap
     * by merging it with the existing tree.
     * @param value value of the node that is being added.
     */
    @Override
    public void insert(int value) {
        BinomialHeap heap = new BinomialHeap();
        Node node = new Node(value);
        heap.head = node;
        BinomialHeap newHeap = this.merge(heap);
        head = newHeap.head;
        heapSize++;
    }

    /**
     * Finds the minimum amongst the roots of the binomial trees.
     * @return minimum heap.
     */
    @Override
    public Node findMinNode() {
        if (head == null) {
            return null;
        }
        Node tmp = head;
        Node tmp1 = tmp.sibling;
        while (tmp1 != null) {
            if (tmp1.value < tmp.value) {
                tmp = tmp1;
            }
            tmp1 = tmp1.sibling;
        }
        return tmp;
    }

    /**
     * Finds tha value of minimum node.
     * @return value of heap node as int.
     */
    @Override
    public int findMinValue() {
        return findMinNode().value;
    }

    /**
     * Method to delete the node with a minimum value.
     * @return the minimum node.
     */
    @Override
    public Node deleteMin() {
        if (head == null) {
            return null;
        }
        Node tmp = head;
        Node tmp1 = tmp.sibling;
        Node tmp1Predecessor = tmp;
        Node tmpPredecessor = null;
        while (tmp1 != null) {
            if (tmp1.value < tmp.value) {
                tmp = tmp1;
                tmpPredecessor = tmp1Predecessor;
            }
            tmp1Predecessor = tmp1;
            tmp1 = tmp1.sibling;
        }
        if (tmp == head) {
            head = tmp.sibling;
        } else {
            tmpPredecessor.sibling = tmp.sibling;
        }
        BinomialHeap heap = new BinomialHeap();
        Node node = tmp.child;
        while (node != null) {
            Node next = node.sibling;
            node.sibling = heap.head;
            heap.head = node;
            node = next;
        }
        BinomialHeap newHeap = this.merge(heap);
        head = newHeap.head;
        heapSize--;
        return tmp;
    }

    /**
     * Method for decreasing the value of the node.
     * @param node node whose values are being decreased.
     * @param newValue value to which the value of the node
     *                 is being decreased to.
     */
    @Override
    public void decreaseKey(Node node, int newValue) {
        if (node.value > newValue && newValue > 0) {
            node.value = newValue;
            Node tmp = node;
            Node tmp1 = tmp.parent;
            while (tmp1 != null && (tmp.value < tmp1.value )) {
                int i = tmp.value;
                tmp.value = tmp1.value;
                tmp1.value = i;
                i = tmp.dijkstraPriority;
                tmp.dijkstraPriority = tmp1.dijkstraPriority;
                tmp1.dijkstraPriority = i;
                tmp = tmp1;
                tmp1 = tmp.parent;
            }
        }
    }

    /**
     * Checks if the heap is empty.
     * @return true if the heap is empty, false otherwise.
     */
    public boolean isEmpty(){
        return getHeapSize() == 0;
    }

    /**
     * Empties the heap.
     */
    public void clear() {
        head = null;
        heapSize = 0;
    }

    /**
     * Method to merge two heaps.
     * @param heap that is being merged.
     * @return new merged heap.
     */
    public BinomialHeap merge(BinomialHeap heap) {
        BinomialHeap newHeap = new BinomialHeap();
        newHeap.head = mergeRootLists(this, heap);
        head = null;
        heap.head = null;
        if (newHeap.head == null) {
            return newHeap;
        }
        Node tmpPredecessor = null;
        Node tmp = newHeap.head;
        Node tmpNext = tmp.sibling;
        while (tmpNext != null) {
            if (tmp.degree != tmpNext.degree || (tmpNext.sibling != null && tmpNext.sibling.degree == tmp.degree)) {
                tmpPredecessor = tmp;
                tmp = tmpNext;
            } else {
                if (tmp.value < tmpNext.value) {
                    tmp.sibling = tmpNext.sibling;
                    pair(tmpNext, tmp);
                } else {
                    if (tmpPredecessor == null) {
                        newHeap.head = tmpNext;
                    } else{
                        tmpPredecessor.sibling = tmpNext;
                    }
                    pair(tmp, tmpNext);
                    tmp = tmpNext;
                }
            }
            tmpNext = tmp.sibling;
        }
        return newHeap;
    }

    /**
     * Pair binomial trees.
     * @param x root of one binomial tree which will be parent.
     * @param y root of another binomial tree which will be child;
     */
    private void pair(Node x, Node y) {
        x.parent = y;
        x.sibling = y.child;
        y.child = x;
        y.degree++;
    }

    /**
     * Merges the root lists with head of the trees from heap1 and heap2
     * @param heap1 one heap that is being merged.
     * @param heap2 other heap that is being merged.
     * @return the head of the merged root list.
     */
    private Node mergeRootLists(BinomialHeap heap1, BinomialHeap heap2) {
        if (heap1.head == null) {
            return heap2.head;
        }
        else if (heap2.head == null) {
            return heap1.head;
        } else {
            Node head;
            Node tail;
            Node heap1Next = heap1.head;
            Node heap2Next = heap2.head;
            if (heap1.head.degree <= heap2.head.degree) {
                head = heap1.head;
                heap1Next = heap1Next.sibling;
            } else {
                head = heap2.head;
                heap2Next = heap2Next.sibling;
            }
            tail = head;
            while (heap1Next != null && heap2Next != null) {
                if (heap1Next.degree <= heap2Next.degree) {
                    tail.sibling = heap1Next;
                    heap1Next = heap1Next.sibling;
                } else {
                    tail.sibling = heap2Next;
                    heap2Next = heap2Next.sibling;
                }
                tail = tail.sibling;
            }
            if (heap1Next != null) {
                tail.sibling = heap1Next;
            } else {
                tail.sibling = heap2Next;
            }
            return head;
        }
    }
}
