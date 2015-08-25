package datastructures;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

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

    @Test
    public void cutWorks() {
        Node node1 = new Node(13);
        Node node2 = new Node(34);
        Node node3 = new Node(14);
        Node node4= new Node(124);
        Node node5 = new Node(18);
        Node node6 = new Node(114);
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
    }

    @Test
    public void test_InsertRemoveMin() {
        // This is a stress test that inserts numerous random elements and
        // ensures that they come out in increasing order by value. This
        // extreme case uncovered multiple bugs in nearly every public
        // implementation of fibonacci heap.
        FibonacciHeap heap = new FibonacciHeap();
        Assert.assertTrue(heap.isEmpty());
        Assert.assertEquals(0, heap.getHeapSize());
        // Insert a known minimum value.
        heap.insert(1);
        // Insert a lot of random numbers.
        Random random = new Random();
        for (int ii = 1; ii <= 49999; ii++) {
            int r = random.nextInt();
            if (r < 0) {
                // Insure only positive values are stored.
                r += Integer.MAX_VALUE;
            }
            heap.insert(r);
        }
        Assert.assertEquals(50000, heap.getHeapSize());
        // Ensure the numbers come out in increasing order.
        int ii = 1;
        int count = 0;
        while (!heap.isEmpty()) {
            Node tmp = heap.deleteMin();
            int v = tmp.getValue();
            count++;
            int vi = v;
            Assert.assertTrue(vi >= ii);
            ii = vi;
        }
        // Ensure no elements were lost on the way out.
        Assert.assertEquals(50000, count);
        Assert.assertTrue(heap.isEmpty());
        Assert.assertEquals(0, heap.getHeapSize());
    }
}
