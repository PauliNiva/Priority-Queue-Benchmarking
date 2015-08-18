package benchmarks;


import org.junit.Before;
import org.junit.Test;

public class DeleteTest {

    Delete delete;

    @Before
    public void setUp() {
        delete = new Delete();
    }

    @Test
    public void sortSortsTheArray() {
        delete.deleteBenchmark(5);
    }
}
