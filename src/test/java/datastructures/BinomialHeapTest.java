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
    public void mergeTwoBinomialHeaps() {
        BinomialHeap anotherHeap = new BinomialHeap();
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
        heap.merge(anotherHeap.findMinRoot());
        Assert.assertEquals(3, heap.deleteMin().getValue());
        Assert.assertEquals(4, heap.deleteMin().getValue());
        Assert.assertEquals(5, heap.deleteMin().getValue());
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
    public void mergeTwoBinomialHeapsOfDifferentDegree() {
        BinomialHeap anotherHeap = new BinomialHeap();
        heap.insert(34);
        heap.insert(5);
        anotherHeap.insert(32);
        anotherHeap.insert(74);
        anotherHeap.insert(65);
        anotherHeap.insert(4363);
        anotherHeap.insert(4);
        anotherHeap.insert(3);
        anotherHeap.insert(543);
        anotherHeap.insert(25);
        heap.merge(anotherHeap.findMinRoot());
        Assert.assertEquals(3, heap.deleteMin().getValue());
        Assert.assertEquals(4, heap.deleteMin().getValue());
        Assert.assertEquals(5, heap.deleteMin().getValue());
    }

    @Test
    public void mergeTwoBinomialHeapsOFDifferentDegreeTheOtherWay() {
        BinomialHeap anotherHeap = new BinomialHeap();
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
        anotherHeap.insert(4);
        anotherHeap.insert(3);
        heap.insert(543);
        heap.insert(25);
        heap.merge(anotherHeap.findMinRoot());
        Assert.assertEquals(3, heap.deleteMin().getValue());
        Assert.assertEquals(4, heap.deleteMin().getValue());
        Assert.assertEquals(5, heap.deleteMin().getValue());
    }

    @Test
    public void decreaseKeyWorks() {
        heap.insert(3);
        heap.insert(5);
        heap.insert(8);
        heap.insert(2);
        heap.decreaseKey(3, 15);
        heap.decreaseKey(233, 122);
        Assert.assertEquals(2, heap.findMin().getValue());
        heap.decreaseKey(3, 1);
        Assert.assertEquals(1, heap.deleteMin().getValue());
    }
}
