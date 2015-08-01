package datastructures;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LeftistHeapTest {

    LeftistHeap heap;

    @Before
    public void setUp() {
        heap = new LeftistHeap();
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

    @Test
    public void deleteMinDeletesTheMinimum() {
        heap.insert(23);
        heap.insert(14);
        heap.insert(41);
        heap.insert(124);
        heap.insert(2);
        heap.insert(34);
        heap.insert(7);
        heap.insert(35);
        heap.insert(112);
        heap.insert(124);
        heap.insert(5);
        Assert.assertEquals(2, heap.deleteMin());
        Assert.assertEquals(5, heap.deleteMin());
        Assert.assertEquals(7, heap.deleteMin());
    }

    @Test
    public void mergeTwoLeftistHeaps() {
        LeftistHeap anotherHeap = new LeftistHeap();
        heap.insert(23);
        heap.insert(14);
        heap.insert(41);
        heap.insert(124);
        heap.insert(34);
        heap.insert(7);
        heap.insert(35);
        heap.insert(112);
        heap.insert(124);
        heap.insert(5);
        anotherHeap.insert(32);
        anotherHeap.insert(74);
        anotherHeap.insert(65);
        anotherHeap.insert(4363);
        anotherHeap.insert(4);
        anotherHeap.insert(3);
        anotherHeap.insert(543);
        anotherHeap.insert(25);
        heap.merge(anotherHeap);
        Assert.assertEquals(3, heap.deleteMin());
        Assert.assertEquals(4, heap.deleteMin());
        Assert.assertEquals(5, heap.deleteMin());
    }

    @Test
    public void clearTheHeap() {
        Assert.assertTrue(heap.isEmpty());
        heap.insert(23);
        heap.insert(14);
        heap.insert(41);
        heap.insert(124);
        heap.insert(34);
        Assert.assertFalse(heap.isEmpty());
        heap.clear();
        Assert.assertTrue(heap.isEmpty());
    }

    @Test
    public void mergeTheHeapWithItself() {
        heap.insert(124);
        heap.insert(34);
        heap.insert(7);
        heap.insert(35);
        heap.merge(heap);
        Assert.assertEquals(7, heap.deleteMin());
        Assert.assertEquals(34, heap.deleteMin());
    }

    @Test
    public void deleteMinOnAnEmptyHeap() {
        Assert.assertEquals(Integer.MIN_VALUE, heap.deleteMin());
    }
}
