package benchmarks;

import datastructures.*;

import java.util.Random;

/**
 * Class for benchmarking the insertion capabilities of the different heaps.
 */
public class Insert {

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
     * Method for creating an array filled with random values.
     * @param arraySize size of the array that is being created.
     * @return int array filled with random integers.
     */
    private int[] createArrayWithRandomValues(int arraySize) {
        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = Math.abs(new Random().nextInt());
        }
        return array;
    }

    /**
     * Method for benchmarking inserts into a heap.
     * @param arraySize size of the array that is being benchmarked.
     */
    public void insertBenchmark(int arraySize) {
        long binary = 0;
        long unary = 0;
        long binaryD = 0;
        long ternary = 0;
        long quaternary = 0;
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
            DHeap binaryHeapD = new DHeap(arraySize, 2);
            DHeap ternaryHeap = new DHeap(arraySize, 3);
            DHeap quaternaryHeap = new DHeap(arraySize, 4);
            Treap treap = new Treap();

            long startTime = System.nanoTime();
            insert(binaryHeap);
            binary += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            insert(binomialHeap);
            binomial += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            insert(fibonacciHeap);
            fibonacci += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            insert(leftistHeap);
            leftist += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            insert(pairingHeap);
            pairing += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            insert(skewHeap);
            skew += (System.nanoTime() - startTime);

            if (arraySize <= 30000) {
                startTime = System.nanoTime();
                insert(unaryHeap);
                unary += (System.nanoTime() - startTime);
            }

            startTime = System.nanoTime();
            insert(binaryHeapD);
            binaryD += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            insert(ternaryHeap);
            ternary += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            insert(quaternaryHeap);
            quaternary += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            insert(treap);
            treapTimer += (System.nanoTime() - startTime);

        }

        binary /= 10 * 1000000;
        binomial /= 10 * 1000000;
        fibonacci /= 10 * 1000000;
        leftist /= 10 * 1000000;
        pairing /= 10 * 1000000;
        skew /= 10 * 1000000;
        unary /= 10 * 1000000;
        binaryD /= 10 * 1000000;
        ternary /= 10 * 1000000;
        quaternary /= 10 * 1000000;
        treapTimer /= 10 * 1000000;

        System.out.println("Average inserting time when the number \nof the integers being inserted was " + arraySize + ":\n");
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
        System.out.println("BinaryD heap: " + binaryD + " ms.");
        System.out.println("Ternary heap: " + ternary + " ms.");
        System.out.println("Quaternary heap: " + quaternary + " ms.");
        System.out.println("Treap: " + treapTimer + " ms.");
        System.out.println();
        System.out.println();
    }
}
