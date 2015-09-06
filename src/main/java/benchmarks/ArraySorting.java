package benchmarks;

import datastructures.*;
import java.util.Random;

/**
 * Class for benchmarking the array sorting capabilities of different heaps.
 */
public class ArraySorting {

    private int[] array;

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
     * Method for sorting an array.
     * @param heap that is being used as an auxiliary datastructure.
     * @return sorted int array.
     */
    private int[] sort(Heap heap) {
        int[] sortedArray = new int[array.length];
        for (int i : array) {
            heap.insert(i);
        }
        for (int i = 0; i < array.length; i++){
            sortedArray[i] = heap.findMinValue();
            heap.deleteMin();
        }
        return sortedArray;
    }

    /**
     * Method for benchmarking a sorting of an array with random values.
     * @param arraySize size of the array that is being benchmarked.
     */
    public void arraySortingBenchmark(int arraySize) {
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

        for (int i = 0; i < 10; i++) {
            array = createArrayWithRandomValues(arraySize);

            long startTime = System.nanoTime();
            sort(binaryHeap);
            binary += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            sort(binomialHeap);
            binomial += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            sort(fibonacciHeap);
            fibonacci += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            sort(leftistHeap);
            leftist += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            sort(pairingHeap);
            pairing += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            sort(skewHeap);
            skew += (System.nanoTime() - startTime);

            if (arraySize <= 30000) {
                startTime = System.nanoTime();
                sort(unaryHeap);
                unary += (System.nanoTime() - startTime);
            }

            if (arraySize <= 50000) {
                startTime = System.nanoTime();
                sort(unaryHeapIterative);
                unaryIterative += (System.nanoTime() - startTime);
            }

            startTime = System.nanoTime();
            sort(binaryHeapD);
            binaryD += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            sort(binaryHeapDIterative);
            binaryDIterative += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            sort(ternaryHeap);
            ternary += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            sort(ternaryHeapIterative);
            ternaryIterative += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            sort(quaternaryHeap);
            quaternary += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            sort(quaternaryHeapIterative);
            quaternaryIterative += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            sort(treap);
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

        System.out.println("Average sorting time when the size \nof the array being sorted was " + arraySize + ":\n");
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
