package datastructures;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

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
        BinomialHeap yetAnother = heap.merge(anotherHeap);
        Assert.assertEquals(3, yetAnother.deleteMin().getValue());
        Assert.assertEquals(4, yetAnother.deleteMin().getValue());
        Assert.assertEquals(5, yetAnother.deleteMin().getValue());
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
        BinomialHeap yetAnother = heap.merge(anotherHeap);
        Assert.assertEquals(3, yetAnother.deleteMin().getValue());
        Assert.assertEquals(4, yetAnother.deleteMin().getValue());
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
        BinomialHeap yetAnother = heap.merge(anotherHeap);
        Assert.assertEquals(3, yetAnother.deleteMin().getValue());
        Assert.assertEquals(4, yetAnother.deleteMin().getValue());
        Assert.assertEquals(5, yetAnother.deleteMin().getValue());
    }

    @Test
    public void InsertRemoveMinStressTest() {
        BinomialHeap heap = new BinomialHeap();
        Assert.assertTrue(heap.isEmpty());
        Assert.assertEquals(0, heap.getHeapSize());
        heap.insert(1);
        Random random = new Random();
        for (int ii = 1; ii <= 49999; ii++) {
            int r = random.nextInt();
            if (r < 0) {
                r += Integer.MAX_VALUE;
            }
            heap.insert(r);
        }
        Assert.assertEquals(50000, heap.getHeapSize());
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
        Assert.assertEquals(50000, count);
        Assert.assertTrue(heap.isEmpty());
        Assert.assertEquals(0, heap.getHeapSize());
    }
}
