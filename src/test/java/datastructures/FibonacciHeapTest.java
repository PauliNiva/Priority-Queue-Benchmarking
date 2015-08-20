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
        Assert.assertEquals(2, heap.findMin().getValue());
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
        Assert.assertEquals(2, heap.deleteMin().getValue());
        Assert.assertEquals(5, heap.deleteMin().getValue());
        Assert.assertEquals(7, heap.deleteMin().getValue());
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
        Assert.assertEquals(3, mergedHeap.deleteMin().getValue());
        Assert.assertEquals(4, mergedHeap.deleteMin().getValue());
        Assert.assertEquals(5, mergedHeap.deleteMin().getValue());
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
        Assert.assertEquals(null, heap.deleteMin());
    }

    @Test
    public void consolidatesTreesBranchesWork() {
        heap.insert(23);
        Assert.assertEquals(23, heap.deleteMin().getValue());
        FibonacciHeap anotherHeap = new FibonacciHeap();
        heap.insert(14);
        anotherHeap.insert(41);
        Assert.assertEquals(14, heap.deleteMin().getValue());
        heap.clear();
        heap.insert(41);
        anotherHeap.insert(14);
        FibonacciHeap mergedHeap = heap.merge(heap, anotherHeap);
        Assert.assertEquals(14, mergedHeap.deleteMin().getValue());
    }

    @Test
    public void mergeTwoEmptyFibonacciHeaps() {
        FibonacciHeap anotherHeap = new FibonacciHeap();
        FibonacciHeap mergedHeap = heap.merge(heap, anotherHeap);
        Assert.assertEquals(null, mergedHeap.deleteMin());
    }

    @Test
    public void mergeTwoFibonacciHeapsWhenOtherIsEmpty() {
        FibonacciHeap anotherHeap = new FibonacciHeap();
        heap.insert(2);
        FibonacciHeap mergedHeap = heap.merge(heap, anotherHeap);
        Assert.assertEquals(2, mergedHeap.deleteMin().getValue());
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
        Assert.assertEquals(3, mergedHeap.deleteMin().getValue());
        Assert.assertEquals(4, mergedHeap.deleteMin().getValue());
        Assert.assertEquals(5, mergedHeap.deleteMin().getValue());
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
        Assert.assertEquals(3, mergedHeap.deleteMin().getValue());
        Assert.assertEquals(4, mergedHeap.deleteMin().getValue());
        Assert.assertEquals(5, mergedHeap.deleteMin().getValue());
    }

    @Test
    public void mergeTwoFibonacciHeapsWhenOtherIsNull() {
        FibonacciHeap mergedHeap = heap.merge(heap, null);
        Assert.assertEquals(null, mergedHeap.deleteMin());
        FibonacciHeap anotherMergedHeap = heap.merge(null, heap);
        Assert.assertEquals(null, anotherMergedHeap.deleteMin());
    }

    @Test
    public void decreaseKeyWorks() {
        Node node1 = new Node(13);
        Node node2 = new Node(34);
        Node node3 = new Node(14);
        Node node4= new Node(124);
        Node node5 = new Node(18);
        heap.insert(node5);
        Assert.assertEquals(18, heap.findMin().getValue());
        heap.decreaseKey(node5, 17);
        Assert.assertEquals(17, heap.findMin().getValue());
        heap.insert(node2);
        heap.insert(node1);
        Assert.assertEquals(13, heap.findMin().getValue());
        heap.decreaseKey(node5, 12);
        Assert.assertEquals(12, heap.findMin().getValue());
        heap.insert(node4);
        heap.insert(node3);
        heap.decreaseKey(node4, 10);
        Assert.assertEquals(10, heap.findMin().getValue());
        heap.decreaseKey(node5, 11);
        heap.decreaseKey(node5, 17);
        heap.decreaseKey(node5, 9);
    }

    @Test
    public void cutWorks() {
        Node node1 = new Node(13);
        Node node2 = new Node(34);
        Node node3 = new Node(14);
        Node node4= new Node(124);
        Node node5 = new Node(18);
        Node node6 = new Node(114);
        heap.insert(node5);
        Assert.assertEquals(18, heap.findMin().getValue());
        heap.decreaseKey(node5, 17);
        Assert.assertEquals(17, heap.findMin().getValue());
        heap.insert(node2);
        heap.insert(node1);
        Assert.assertEquals(13, heap.findMin().getValue());
        heap.decreaseKey(node5, 12);
        Assert.assertEquals(12, heap.findMin().getValue());
        heap.insert(node4);
        heap.insert(node3);
        heap.decreaseKey(node4, 10);
        Assert.assertEquals(10, heap.findMin().getValue());
        heap.cut(node2, node1);
        heap.cascadeCut(node6);
    }
}
