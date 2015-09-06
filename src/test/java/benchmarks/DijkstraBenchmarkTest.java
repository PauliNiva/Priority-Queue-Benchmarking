package benchmarks;


import datastructures.Node;
import org.junit.Before;
import org.junit.Test;

public class DijkstraBenchmarkTest {

    DijkstraBenchmark dijkstraBenchmark;
    Node[] array;

    @Before
    public void setUp() {
        dijkstraBenchmark = new DijkstraBenchmark();
        array = new Node[100];
    }

    @Test
    public void sortSortsTheArray() {
        dijkstraBenchmark.benchmark(100);
    }
}
