package benchmarks;


import org.junit.Before;
import org.junit.Test;

public class InsertTest {

    Insert insert;

    @Before
    public void setUp() {
        insert = new Insert();
    }

    @Test
    public void sortSortsTheArray() {
        insert.insertBenchmark(5);
    }
}
