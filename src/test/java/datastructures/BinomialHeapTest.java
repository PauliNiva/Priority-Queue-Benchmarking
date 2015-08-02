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
    public void newLeftistHeapIsEmpty() {
        Assert.assertTrue(heap.isEmpty());
    }

    @Test
    public void heapIsNotEmptyAfterInsert() {
        Assert.assertTrue(heap.isEmpty());
        heap.insert(3);
        Assert.assertFalse(heap.isEmpty());
    }
}
