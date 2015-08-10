package datastructures;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PairingHeapTest {

    PairingHeap heap;

    @Before
    public void setUp() {
        heap = new PairingHeap();
    }

    @Test
    public void newFibonacciHeapIsEmpty() {
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
    public void deleteMinOnAnEmptyHeap() {
        Assert.assertEquals(Integer.MIN_VALUE, heap.deleteMin());
    }

    @Test
    public void heapIsEmptyAfterInsertAndDelete() {
        Assert.assertTrue(heap.isEmpty());
        heap.insert(3);
        Assert.assertEquals(3, heap.findMin());
        Assert.assertFalse(heap.isEmpty());
        Assert.assertEquals(3, heap.deleteMin());
        Assert.assertTrue(heap.isEmpty());
    }

    @Test
    public void mergeNullBranches() {
        // TODO asserts.
        PairingHeap.Node x =  new PairingHeap.Node(1);
        PairingHeap.Node y =  new PairingHeap.Node(2);
        heap.merge(x, null);
        heap.merge(null, y);
        heap.merge(null, null);
        heap.mergePairs(null);
        heap.mergePairs(y);
        heap.clear();
    }
}
