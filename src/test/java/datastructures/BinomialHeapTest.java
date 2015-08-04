package datastructures;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinomialHeapTest {

    BinomialHeap heap;

    @Before
    public void setUp() {
        heap = new BinomialHeap();
    }

    @Test
    public void newBinomialHeapIsEmpty() {
        Assert.assertTrue(heap.isEmpty());
    }

    @Test
    public void heapIsNotEmptyAfterInsert() {
        Assert.assertTrue(heap.isEmpty());
        heap.insert(3);
        Assert.assertFalse(heap.isEmpty());
    }

    @Test
    public void findMinFindsTheMinimum() {
        heap.insert(23);
        heap.insert(14);
        heap.insert(4);
        heap.insert(124);
        heap.insert(2);
        heap.insert(34);
        heap.insert(7);
        heap.insert(3);
        heap.insert(112);
        heap.insert(124);
        heap.insert(5);
        Assert.assertEquals(2, heap.findMin());
    }
}
