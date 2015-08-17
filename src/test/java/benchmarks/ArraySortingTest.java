package benchmarks;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArraySortingTest {

    ArraySorting arraySorting;
    int[] array;

    @Before
    public void setUp() {
        arraySorting = new ArraySorting();
        array = new int[5];
    }

    @Test
    public void sortSortsTheArray() {
        arraySorting.arraySortingBenchmark(5);
    }
}
