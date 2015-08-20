package benchmarks;

import datastructures.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArraySortingTest {

    ArraySorting arraySorting;
    Node[] array;

    @Before
    public void setUp() {
        arraySorting = new ArraySorting();
        array = new Node[5];
    }

    @Test
    public void sortSortsTheArray() {
        arraySorting.arraySortingBenchmark(5);
    }
}
