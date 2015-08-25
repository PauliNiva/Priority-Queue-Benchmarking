package algorithms;

import datastructures.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


public class DijkstraTest {

    private Dijkstra dijkstra1;
    private Dijkstra dijkstra2;
    BinaryHeap binaryHeap = new BinaryHeap(128);
    BinomialHeap binomialHeap = new BinomialHeap();
    DHeap dHeap = new DHeap(128, 3);
    FibonacciHeap fibonacciHeap = new FibonacciHeap();


    @Before
    public void setUp() {
        dijkstra1 = new Dijkstra(smallGraph(), distances(),0);
        dijkstra2 = new Dijkstra(smallGraph(), distances2(),0);
    }
    public Node[] smallGraph() {
        Node[] nodes =new Node[6];
        for(int i  =0; i < 6; i++) {
            nodes[i] = new Node(i);
        }
        return nodes;
    }

    public int[][] distances(){
        int[][] graph = new int[6][6];
        graph[0][1] = graph[1][0]=7;
        graph[0][2] = graph[2][0]=9;
        graph[0][5] = graph[5][0]=14;
        graph[1][2] = graph[2][1]=10;
        graph[1][3] = graph[3][1]=15;
        graph[2][3] = graph[3][2]=11;
        graph[2][5] = graph[5][2]=2;
        graph[3][4] = graph[4][3]=6;
        graph[4][5] = graph[5][4]=9;
        return graph;
    }

    public int[][] distances2() {
        return new int[][]
                {       {0,5,0,1,0,0},
                        {5,0,0,0,0,7},
                        {0,0,0,16,19,4},
                        {1,0,16,0,2,0},
                        {0,0,19,2,0,3},
                        {0,7,4,0,3,0}};

    }

    @Test
    public void distancesWithBinaryHeapTest(){
        int[] distances = dijkstra1.findShortestPaths(binaryHeap);
        Assert.assertTrue(distances[0] == 0);
        Assert.assertTrue(distances[1] == 7);
        Assert.assertTrue(distances[2] == 9);
        Assert.assertTrue(distances[3] == 20);
        Assert.assertTrue(distances[4] == 20);
        Assert.assertTrue(distances[5] == 11);
    }

    @Test
    public void distancesWithBinaryHeapTest2(){
        int[] distances = dijkstra2.findShortestPaths(binaryHeap);
        Assert.assertTrue(distances[0] == 0);
        Assert.assertTrue(distances[1] == 5);
        Assert.assertTrue(distances[2] == 10);
        Assert.assertTrue(distances[3] == 1);
        Assert.assertTrue(distances[4] == 3);
        Assert.assertTrue(distances[5] == 6);
    }


    @Test
    public void distancesWithBinomialHeapTest(){
        int[] distances = dijkstra1.findShortestPaths(binomialHeap);
        Assert.assertTrue(distances[0] == 0);
        Assert.assertTrue(distances[1] == 7);
        Assert.assertTrue(distances[2] == 9);
        Assert.assertTrue(distances[3] == 20);
        Assert.assertTrue(distances[4] == 20);
        Assert.assertTrue(distances[5] == 11);
    }
    @Test
    public void distancesWithBinomialHeapTest2(){
        int[] distances = dijkstra2.findShortestPaths(binomialHeap);
        Assert.assertTrue(distances[0] == 0);
        Assert.assertTrue(distances[1] == 5);
        Assert.assertTrue(distances[2] == 10);
        Assert.assertTrue(distances[3] == 1);
        Assert.assertTrue(distances[4] == 3);
        Assert.assertTrue(distances[5] == 6);
    }

    @Test
    public void distancesWithDHeapTest(){
        int[] distances = dijkstra1.findShortestPaths(dHeap);
        Assert.assertTrue(distances[0] == 0);
        Assert.assertTrue(distances[1] == 7);
        Assert.assertTrue(distances[2] == 9);
        Assert.assertTrue(distances[3] == 20);
        Assert.assertTrue(distances[4] == 20);
        Assert.assertTrue(distances[5] == 11);
    }

    @Test
    public void distancesWithDHeapTest2(){
        int[] distances = dijkstra2.findShortestPaths(dHeap);
        Assert.assertTrue(distances[0] == 0);
        Assert.assertTrue(distances[1] == 5);
        Assert.assertTrue(distances[2] == 10);
        Assert.assertTrue(distances[3] == 1);
        Assert.assertTrue(distances[4] == 3);
        Assert.assertTrue(distances[5] == 6);
    }

    @Test
    public void distancesWithFibonacciHeap(){
        int[] distances = dijkstra1.findShortestPaths(fibonacciHeap);
        Assert.assertTrue(distances[0] == 0);
        Assert.assertTrue(distances[1] == 7);
        Assert.assertTrue(distances[2] == 9);
        Assert.assertTrue(distances[3] == 20);
        Assert.assertTrue(distances[4] == 20);
        Assert.assertTrue(distances[5] == 11);
    }
    @Test
    public void distancesWithFibonacciHeap2(){
        int[] distances = dijkstra2.findShortestPaths(fibonacciHeap);
        Assert.assertTrue(distances[0] == 0);
        Assert.assertTrue(distances[1] == 5);
        Assert.assertTrue(distances[2] == 10);
        Assert.assertTrue(distances[3] == 1);
        Assert.assertTrue(distances[4] == 3);
        Assert.assertTrue(distances[5] == 6);
    }
}