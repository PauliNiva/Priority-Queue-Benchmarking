package main;

import benchmarks.*;

public class Main {

    public static void main(String[] args) {
        ArraySorting arraySorting = new ArraySorting();
        Insert insert = new Insert();
        Delete delete = new Delete();
        DijkstraBenchmark dijkstra = new DijkstraBenchmark();
        insert.insertBenchmark(1000);
        insert.insertBenchmark(10000);
        insert.insertBenchmark(30000);
        insert.insertBenchmark(100000);
        insert.insertBenchmark(1000000);
        delete.deleteBenchmark(100);
        delete.deleteBenchmark(1000);
        delete.deleteBenchmark(10000);
        delete.deleteBenchmark(100000);
        delete.deleteBenchmark(1000000);
        arraySorting.arraySortingBenchmark(10000);
        arraySorting.arraySortingBenchmark(30000);
        arraySorting.arraySortingBenchmark(100000);
        arraySorting.arraySortingBenchmark(1000000);
        dijkstra.benchmark(100);
        dijkstra.benchmark(1000);
        dijkstra.benchmark(5000);
        dijkstra.benchmark(10000);
    }
}

