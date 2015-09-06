package benchmarks;


import datastructures.*;

import java.util.Random;

/**
 * Class for benchmarking the delete capabilities of different heaps.
 */
public class Delete {

    private int[] array;

    /**
     * Method for inserting random values into a heap.
     * @param heap that is values are being inserted.
     */
    private void insert(Heap heap) {
        for (int i : array) {
            heap.insert(i);
        }
    }

    /**
     * Method for deleting the values from the heap one by one.
     * @param heap heap where the values are being deleted.
     */
    private void delete(Heap heap) {
        for (int i = 0; i < array.length; i++) {
            heap.deleteMin();
        }
    }

    /**
     * Method for creating an array filled with random values.
     * @param arraySize size of the array that is being created.
     * @return int array filled with random integers.
     */
    private int[] createArrayWithRandomValues(int arraySize) {
        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = new Random().nextInt();
        }
        return array;
    }

    /**
     * Method for benchmarking deletes from a heap.
     * @param arraySize size of the array that is being benchmarked.
     */
    public void deleteBenchmark(int arraySize) {
        long binary = 0;
        long unary = 0;
        long unaryIterative = 0;
        long binaryD = 0;
        long binaryDIterative = 0;
        long ternary = 0;
        long ternaryIterative = 0;
        long quaternary = 0;
        long quaternaryIterative = 0;
        long binomial = 0;
        long fibonacci = 0;
        long leftist = 0;
        long pairing = 0;
        long skew = 0;
        long treapTimer = 0;

        for (int i = 0; i < 10; i++) {
            array = createArrayWithRandomValues(arraySize);
            BinaryHeap binaryHeap = new BinaryHeap(arraySize);
            BinomialHeap binomialHeap = new BinomialHeap();
            FibonacciHeap fibonacciHeap = new FibonacciHeap();
            LeftistHeap leftistHeap = new LeftistHeap();
            PairingHeap pairingHeap = new PairingHeap();
            SkewHeap skewHeap = new SkewHeap();
            DHeap unaryHeap = new DHeap(arraySize, 1);
            DHeapIterative unaryHeapIterative = new DHeapIterative(arraySize, 1);
            DHeap binaryHeapD = new DHeap(arraySize, 2);
            DHeapIterative binaryHeapDIterative = new DHeapIterative(arraySize, 2);
            DHeap ternaryHeap = new DHeap(arraySize, 3);
            DHeapIterative ternaryHeapIterative = new DHeapIterative(arraySize, 3);
            DHeap quaternaryHeap = new DHeap(arraySize, 4);
            DHeapIterative quaternaryHeapIterative = new DHeapIterative(arraySize, 4);
            Treap treap = new Treap();

            insert(binaryHeap);
            insert(binomialHeap);
            insert(fibonacciHeap);
            insert(leftistHeap);
            insert(pairingHeap);
            insert(skewHeap);
            if (arraySize <= 30000) {
                insert(unaryHeap);
            }
            if (arraySize <= 50000) {
                insert(unaryHeapIterative);
            }
            insert(binaryHeapD);
            insert(binaryHeapDIterative);
            insert(ternaryHeap);
            insert(ternaryHeapIterative);
            insert(quaternaryHeap);
            insert(quaternaryHeapIterative);
            insert(treap);

            long startTime = System.nanoTime();
            delete(binaryHeap);
            binary += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            delete(binomialHeap);
            binomial += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            delete(fibonacciHeap);
            fibonacci += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            delete(leftistHeap);
            leftist += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            delete(pairingHeap);
            pairing += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            delete(skewHeap);
            skew += (System.nanoTime() - startTime);

            if (arraySize <= 30000) {
                startTime = System.nanoTime();
                delete(unaryHeap);
                unary += (System.nanoTime() - startTime);
            }

            if (arraySize <= 50000) {
                startTime = System.nanoTime();
                delete(unaryHeapIterative);
                unaryIterative += (System.nanoTime() - startTime);
            }

            startTime = System.nanoTime();
            delete(binaryHeapD);
            binaryD += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            delete(binaryHeapDIterative);
            binaryDIterative += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            delete(ternaryHeap);
            ternary += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            delete(ternaryHeapIterative);
            ternaryIterative += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            delete(quaternaryHeap);
            quaternary += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            delete(quaternaryHeapIterative);
            quaternaryIterative += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            delete(treap);
            treapTimer += (System.nanoTime() - startTime);

        }

        binary /= 10 * 1000000;
        binomial /= 10 * 1000000;
        fibonacci /= 10 * 1000000;
        leftist /= 10 * 1000000;
        pairing /= 10 * 1000000;
        skew /= 10 * 1000000;
        unary /= 10 * 1000000;
        unaryIterative /= 10 * 1000000;
        binaryD /= 10 * 1000000;
        binaryDIterative /= 10 * 1000000;
        ternary /= 10 * 1000000;
        ternaryIterative /= 10 * 1000000;
        quaternary /= 10 * 1000000;
        quaternaryIterative /= 10 * 1000000;
        treapTimer /= 10 * 1000000;

        System.out.println("Average deleting time when the number \nof the integers being deleted was " + arraySize + ":\n");
        System.out.println("Binary heap: " + binary + " ms.");
        System.out.println("Binomial heap: " + binomial + " ms.");
        System.out.println("Fibonacci heap: " + fibonacci + " ms.");
        System.out.println("Leftist heap: " + leftist + " ms.");
        System.out.println("Pairing heap: " + pairing + " ms.");
        System.out.println("Skew heap: " + skew + " ms.");
        if (arraySize <= 30000) {
            System.out.println("Unary heap: " + unary + " ms.");
        } else {
            System.out.println("Unary heap: Not tested as the size of array is too large.");
        }
        if (arraySize <= 50000) {
            System.out.println("Iterative unary heap: " + unaryIterative + " ms.");
        } else {
            System.out.println("Unary heap: Not tested as the size of array is too large.");
        }
        System.out.println("BinaryD heap: " + binaryD + " ms.");
        System.out.println("Iterative binaryD heap: " + binaryDIterative + " ms.");
        System.out.println("Ternary heap: " + ternary + " ms.");
        System.out.println("Iterative ternary heap: " + ternaryIterative + " ms.");
        System.out.println("Quaternary heap: " + quaternary + " ms.");
        System.out.println("Iterative Quaternary heap: " + quaternaryIterative + " ms.");
        System.out.println("Treap: " + treapTimer + " ms.");
        System.out.println();
        System.out.println();
    }
}
