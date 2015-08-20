package datastructures;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinaryHeapTest {

    BinaryHeap heap;

    @Before
    public void setUp() {
        heap = new BinaryHeap(4);
    }

    @Test
    public void newBinaryHeapArrayIsEmpty() {
        Assert.assertEquals(true, heap.isEmpty());
    }

    @Test
    public void insertWorks() {
        heap.insert(1);
        Assert.assertEquals(false, heap.isEmpty());
        Assert.assertEquals(1, heap.size());
    }

    @Test
    public void findMinWorks() {
        Assert.assertEquals(null, heap.findMin());
        heap.insert(1);
        Assert.assertEquals(1, heap.findMin().getValue());
    }

    @Test
    public void insertAndHeapUpWorks() {
        BinaryHeap heap = new BinaryHeap(8);
        heap.insert(4);
        Assert.assertEquals(4, heap.findMin().getValue());
        heap.insert(5);
        heap.insert(2);
        heap.insert(6);
        Assert.assertEquals(2, heap.findMin().getValue());
        heap.insert(3);
        heap.insert(7);
        heap.deleteMin();
        Assert.assertEquals(3, heap.findMin().getValue());
    }

    @Test
    public void removingMinWhenQueueIsNotEmpty() {
        BinaryHeap heap = new BinaryHeap(8);
        heap.insert(5);
        heap.insert(3);
        heap.insert(7);
        heap.insert(2);
        heap.insert(6);
        Assert.assertEquals(2, heap.deleteMin().getValue());
    }

    @Test
    public void removingMinWhenQueueIsEmpty() {
        Assert.assertEquals(null, heap.deleteMin());
    }

    @Test
    public void removingMinAfterWhichQueueIsEmpty() {
        heap.insert(3);
        Assert.assertEquals(3, heap.deleteMin().getValue());
        Assert.assertEquals(null, heap.deleteMin());
    }

    @Test
    public void removingWhenAllValuesAreSame() {
        heap.insert(3);
        heap.insert(3);
        heap.insert(3);
        Assert.assertEquals(3, heap.deleteMin().getValue());
        heap.deleteMin();
        Assert.assertEquals(3, heap.deleteMin().getValue());
    }

    @Test
    public void decreaseKeyWorks() {
        heap.insert(3);
        heap.insert(5);
        heap.insert(8);
        heap.insert(2);
        heap.decreaseKey(3, 15);
        Assert.assertEquals(2, heap.findMin().getValue());
        heap.decreaseKey(3, 1);
        Assert.assertEquals(1, heap.deleteMin().getValue());
    }
}
