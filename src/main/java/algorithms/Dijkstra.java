package algorithms;

import datastructures.*;

/**
 * Dijkstra's algorithm is an algorithm for finding the shortest paths between nodes in a graph.
 * This variant fixes a single node as the start node and finds shortest paths from the source to
 * all other nodes in the graph, producing a shortest path tree.
 */
public class Dijkstra {

    private Node[] nodes;
    private int[][] distances;
    private int startNode;

    /**
     * Initializes the Dijkstra's algorithm.
     * @param nodes nodes that make up the graph
     * @param distances distances between the nodes.
     * @param startNode start node from which the shortest paths are
     *                  being calculated.
     */
    public Dijkstra(Node[] nodes, int[][] distances, int startNode) {
        this.nodes = nodes;
        this.distances = distances;
        this.startNode = startNode;
    }

    /**
     * Method to find the shortest paths.
     * @param heap heap that is being used as an auxiliary data structure.
     * @return array that contains the distance to each node from the
     * start node.
     */
    public int[] findShortestPaths(Heap heap){
        int[] distanceArray = new int[nodes.length];
        for (int i = 0; i < distanceArray.length; i++) {
            if (i != startNode) {
                distanceArray[i] = Integer.MAX_VALUE;
                nodes[i].setValue(distanceArray[i]);
            }
            heap.insert(nodes[i]);
        }
        while (heap.findMinNode() != null) {
            Node tmp = heap.findMinNode();
            heap.deleteMin();
            for (int i = 0; i < distances.length; i++) {
                if (distances[tmp.getDijkstraPriority()][i] > 0) {
                    int newDistance = distanceArray[tmp.getDijkstraPriority()] + distances[tmp.getDijkstraPriority()][i];
                    if (newDistance < distanceArray[i]) {
                        distanceArray[i] = newDistance;
                        heap.decreaseKey(nodes[i], newDistance);
                    }
                }
            }
        }
        return distanceArray;
    }
}
