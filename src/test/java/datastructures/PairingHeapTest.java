package datastructures;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

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
        Assert.assertEquals(2, heap.findMinNode().getValue());
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
    public void heapIsEmptyAfterInsertAndDelete() {
        Assert.assertTrue(heap.isEmpty());
        heap.insert(3);
        Assert.assertEquals(3, heap.findMinNode().getValue());
        Assert.assertFalse(heap.isEmpty());
        Assert.assertEquals(3, heap.deleteMin().getValue());
        Assert.assertTrue(heap.isEmpty());
    }

    @Test
    public void mergeNullBranches() {
        // TODO asserts.
        Node x =  new Node(1);
        Node y =  new Node(2);
        heap.merge(x, null);
        heap.merge(null, y);
        heap.merge(null, null);
        heap.mergePairs(null);
        heap.mergePairs(y);
        heap.clear();
    }

    @Test
    public void decreaseKeyWorks() {
        Node node1 = new Node(13);
        Node node2 = new Node(34);
        Node node3 = new Node(14);
        Node node4= new Node(124);
        Node node5 = new Node(18);
        heap.insert(node5);
        Assert.assertEquals(18, heap.findMinNode().getValue());
        heap.decreaseKey(node5, 17);
        Assert.assertEquals(17, heap.findMinNode().getValue());
        heap.insert(node2);
        heap.insert(node1);
        Assert.assertEquals(13, heap.findMinNode().getValue());
        heap.decreaseKey(node5, 12);
        Assert.assertEquals(12, heap.findMinNode().getValue());
        heap.insert(node4);
        heap.insert(node3);
        heap.decreaseKey(node4, 10);
        Assert.assertEquals(10, heap.findMinNode().getValue());
        heap.decreaseKey(node5, 11);
        heap.decreaseKey(node5, 17);
        heap.decreaseKey(node5, 9);
    }
}
