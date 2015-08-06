package datastructures;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FibonacciHeapTest {

    FibonacciHeap heap;

    @Before
    public void setUp() {
        heap = new FibonacciHeap();
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
    public void mergeTwoFibonacciHeaps() {
        FibonacciHeap anotherHeap = new FibonacciHeap();
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
        FibonacciHeap mergedHeap = heap.merge(heap, anotherHeap);
        Assert.assertEquals(3, mergedHeap.deleteMin());
        Assert.assertEquals(4, mergedHeap.deleteMin());
        Assert.assertEquals(5, mergedHeap.deleteMin());
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
    public void consolidatesTreesBranchesWork() {
        heap.insert(23);
        Assert.assertEquals(23, heap.deleteMin());
        FibonacciHeap anotherHeap = new FibonacciHeap();
        heap.insert(14);
        anotherHeap.insert(41);
        Assert.assertEquals(14, heap.deleteMin());
        heap.clear();
        heap.insert(41);
        anotherHeap.insert(14);
        FibonacciHeap mergedHeap = heap.merge(heap, anotherHeap);
        Assert.assertEquals(14, mergedHeap.deleteMin());
    }

    @Test
    public void mergeTwoEmptyFibonacciHeaps() {
        FibonacciHeap anotherHeap = new FibonacciHeap();
        FibonacciHeap mergedHeap = heap.merge(heap, anotherHeap);
        Assert.assertEquals(Integer.MIN_VALUE, mergedHeap.deleteMin());
    }

    @Test
    public void mergeTwoFibonacciHeapsWhenOtherIsEmpty() {
        FibonacciHeap anotherHeap = new FibonacciHeap();
        heap.insert(2);
        FibonacciHeap mergedHeap = heap.merge(heap, anotherHeap);
        Assert.assertEquals(2, mergedHeap.deleteMin());
    }

    @Test
    public void mergeTwoFibonacciHeapsOfDifferentDegree() {
        FibonacciHeap anotherHeap = new FibonacciHeap();
        heap.insert(23);
        heap.insert(5);
        anotherHeap.insert(32);
        anotherHeap.insert(74);
        anotherHeap.insert(65);
        anotherHeap.insert(4363);
        anotherHeap.insert(4);
        anotherHeap.insert(3);
        anotherHeap.insert(543);
        anotherHeap.insert(25);
        FibonacciHeap mergedHeap = heap.merge(heap, anotherHeap);
        Assert.assertEquals(3, mergedHeap.deleteMin());
        Assert.assertEquals(4, mergedHeap.deleteMin());
        Assert.assertEquals(5, mergedHeap.deleteMin());
    }

    @Test
    public void mergeTwoFibonacciHeapsOfDifferentDegreeTheOtherWay() {
        FibonacciHeap anotherHeap = new FibonacciHeap();
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
        heap.insert(32);
        heap.insert(74);
        heap.insert(65);
        heap.insert(4363);
        heap.insert(4);
        heap.insert(3);
        heap.insert(543);
        anotherHeap.insert(25);
        FibonacciHeap mergedHeap = heap.merge(heap, anotherHeap);
        Assert.assertEquals(3, mergedHeap.deleteMin());
        Assert.assertEquals(4, mergedHeap.deleteMin());
        Assert.assertEquals(5, mergedHeap.deleteMin());
    }

    @Test
    public void mergeTwoFibonacciHeapsWhenOtherIsNull() {
        FibonacciHeap mergedHeap = heap.merge(heap, null);
        Assert.assertEquals(Integer.MIN_VALUE, mergedHeap.deleteMin());
        FibonacciHeap anotherMergedHeap = heap.merge(null, heap);
        Assert.assertEquals(Integer.MIN_VALUE, anotherMergedHeap.deleteMin());
    }
}
