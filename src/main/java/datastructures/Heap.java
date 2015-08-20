package datastructures;


public interface Heap {

    void insert(Node node);
    void insert(int value);
    Node findMin();
    int findMinimum();
    Node deleteMin();
    void decreaseKey(Node node, int newValue);
    void decreaseKey(int index, int newValue);
}
