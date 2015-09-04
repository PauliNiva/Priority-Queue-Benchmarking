package benchmarks;


import algorithms.Dijkstra;
import datastructures.*;

import java.util.Random;

public class DijkstraBenchmark {

    /**
     * Method for benchmarking heaps ability to find shortest paths.
     * @param size size of the graph that is being benchmarked.
     */
    public void benchmark(int size) {
        long binary = 0;
        long unary = 0;
        long binaryD = 0;
        long ternary = 0;
        long quaternary = 0;
        long binomial = 0;
        long fibonacci = 0;
        long pairing = 0;

        BinaryHeap binaryHeap = new BinaryHeap(size);
        BinomialHeap binomialHeap = new BinomialHeap();
        FibonacciHeap fibonacciHeap = new FibonacciHeap();
        DHeap unaryHeap = new DHeap(size, 1);
        DHeap binaryHeapD = new DHeap(size, 2);
        DHeap ternaryHeap = new DHeap(size, 3);
        DHeap quaternaryHeap = new DHeap(size, 4);
        PairingHeap pairingHeap = new PairingHeap();

        for (int i = 0; i < 10; i++) {
            Dijkstra dijkstra = new Dijkstra(createGraph(size), createMatrix(size),0);

            long startTime = System.nanoTime();
            dijkstra.findShortestPaths(binaryHeap);
            binary += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            //dijkstra.findShortestPaths(binomialHeap);
            binomial += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            dijkstra.findShortestPaths(fibonacciHeap);
            fibonacci += (System.nanoTime() - startTime);

            if (size <= 0) {
                startTime = System.nanoTime();
                dijkstra.findShortestPaths(unaryHeap);
                unary += (System.nanoTime() - startTime);
            }

            startTime = System.nanoTime();
            dijkstra.findShortestPaths(binaryHeapD);
            binaryD += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            dijkstra.findShortestPaths(ternaryHeap);
            ternary += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            dijkstra.findShortestPaths(quaternaryHeap);
            quaternary += (System.nanoTime() - startTime);

            startTime = System.nanoTime();
            dijkstra.findShortestPaths(pairingHeap);
            pairing += (System.nanoTime() - startTime);
        }

        binary /= 10 * 1000000;
        binomial /= 10 * 1000000;
        fibonacci /= 10 * 1000000;
        unary /= 10 * 1000000;
        binaryD /= 10 * 1000000;
        ternary /= 10 * 1000000;
        quaternary /= 10 * 1000000;
        pairing /= 10 * 1000000;

        System.out.println("Average time for finding shortest paths \nwhen the size of the graph was " + size + ":\n");
        System.out.println("Binary heap: " + binary + " ms.");
        //System.out.println("Binomial heap: " + binomial + " ms.");
        System.out.println("Fibonacci heap: " + fibonacci + " ms.");
        if (size <= 100) {
            System.out.println("Unary heap: " + unary + " ms.");
        } else {
            System.out.println("Unary heap: Not tested as the size of array is too large.");
        }
        System.out.println("BinaryD heap: " + binaryD + " ms.");
        System.out.println("Ternary heap: " + ternary + " ms.");
        System.out.println("Quaternary heap: " + quaternary + " ms.");
        System.out.println("Pairing heap: " + pairing + " ms.");
        System.out.println();
        System.out.println();
    }

    private static Node[] createGraph(int size){
        Node[] graph = new Node[size];
        for (int i = 0; i < size; i++){
            graph[i] = new Node(i);
        }
        return graph;
    }

    private static int[] createRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            int noEgde = new Random().nextInt(7 - 1) + 1;
            if (noEgde == 5) {
                array[i] = 0;
            } else {
                array[i] = new Random().nextInt(100000 - 1) + 1;
            }
        }
        return array;
    }

    private static int[][] createMatrix(int size){
        int[][] matrix = new int[size][size];
        for(int i = 0; i < size; i++){
            matrix[i] = createRandomArray(size);
        }
        return matrix;
    }
}
