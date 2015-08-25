package datastructures;


public interface Heap {

    void insert(Node node);
    void insert(int value);
    Node findMinNode();
    int findMinValue();
    Node deleteMin();
    void decreaseKey(Node node, int newValue);
    int getHeapSize();
}
