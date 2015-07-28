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
        Assert.assertEquals(Integer.MIN_VALUE, heap.findMin());
        heap.insert(1);
        Assert.assertEquals(1, heap.findMin());
    }

    @Test
    public void insertAndHeapUpWorks() {
        DHeap heap = new DHeap(8, 2);
        heap.insert(4);
        Assert.assertEquals(4, heap.findMin());
        heap.insert(5);
        heap.insert(2);
        heap.insert(6);
        Assert.assertEquals(2, heap.findMin());
        heap.insert(3);
        heap.insert(7);
        heap.deleteMin();
        Assert.assertEquals(3, heap.findMin());
    }

    @Test
    public void removingMinWhenQueueIsNotEmpty() {
        DHeap heap = new DHeap(8, 3);
        heap.insert(5);
        heap.insert(3);
        heap.insert(7);
        heap.insert(2);
        heap.insert(6);
        Assert.assertEquals(2, heap.deleteMin());
        Assert.assertEquals(3, heap.deleteMin());
        Assert.assertEquals(5, heap.deleteMin());
        Assert.assertEquals(6, heap.deleteMin());
        Assert.assertEquals(7, heap.deleteMin());
    }

    @Test
    public void removingMinWhenQueueIsEmpty() {
        Assert.assertEquals(Integer.MIN_VALUE, heap.deleteMin());
    }

    @Test
    public void removingMinAfterWhichQueueIsEmpty() {
        heap.insert(3);
        Assert.assertEquals(3, heap.deleteMin());
        Assert.assertEquals(Integer.MIN_VALUE, heap.deleteMin());
    }

    @Test
    public void removingWhenAllValuesAreSame() {
        heap.insert(3);
        heap.insert(3);
        heap.insert(3);
        Assert.assertEquals(3, heap.deleteMin());
        heap.deleteMin();
        Assert.assertEquals(3, heap.deleteMin());
    }
}
