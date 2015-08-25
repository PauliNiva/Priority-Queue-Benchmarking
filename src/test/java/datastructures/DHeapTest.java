package datastructures;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DHeapTest {

    DHeap heap;

    @Before
    public void setUp() {
        heap = new DHeap(4, 1);
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
        Assert.assertEquals(null, heap.findMinNode());
        heap.insert(1);
        Assert.assertEquals(1, heap.findMinNode().getValue());
    }

    @Test
    public void insertAndHeapUpWorks() {
        DHeap heap = new DHeap(8, 2);
        heap.insert(4);
        Assert.assertEquals(4, heap.findMinNode().getValue());
        heap.insert(5);
        heap.insert(2);
        heap.insert(6);
        Assert.assertEquals(2, heap.findMinNode().getValue());
        heap.insert(3);
        heap.insert(7);
        heap.deleteMin();
        Assert.assertEquals(3, heap.findMinNode().getValue());
    }

    @Test
    public void removingMinWhenQueueIsNotEmpty() {
        DHeap heap = new DHeap(8, 3);
        heap.insert(5);
        heap.insert(3);
        heap.insert(7);
        heap.insert(2);
        heap.insert(6);
        Assert.assertEquals(2, heap.deleteMin().getValue());
        Assert.assertEquals(3, heap.deleteMin().getValue());
        Assert.assertEquals(5, heap.deleteMin().getValue());
        Assert.assertEquals(6, heap.deleteMin().getValue());
        Assert.assertEquals(7, heap.deleteMin().getValue());
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
        Assert.assertEquals(2, heap.findMinNode().getValue());
        heap.decreaseKey(3, 1);
        Assert.assertEquals(1, heap.deleteMin().getValue());
    }
}
