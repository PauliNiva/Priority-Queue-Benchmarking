package main;

import benchmarks.*;

public class Main {

    public static void main(String[] args) {
        ArraySorting arraySorting = new ArraySorting();
        arraySorting.arraySortingBenchmark(10000);
        arraySorting.arraySortingBenchmark(30000);
        arraySorting.arraySortingBenchmark(100000);
        arraySorting.arraySortingBenchmark(1000000);
    }
}

