package datastructures;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TreapTest {

    Treap treap;
    Node node;

    @Before
    public void setUp() {
        treap = new Treap();
    }

    @Test
    public void newTreapIsEmpty() {
        Assert.assertTrue(treap.isEmpty());
    }

    @Test
    public void emptyTreapFindMinReturnsCorrectly() {
        Assert.assertEquals(null, treap.findMinNode());
    }

    @Test
    public void treapIsNotEmptyAfterInsert() {
        Assert.assertTrue(treap.isEmpty());
        treap.insert(3);
        Assert.assertFalse(treap.isEmpty());
    }

    @Test
    public void findMinFindsTheMinimum() {
        treap.insert(23);
        treap.insert(14);
        treap.insert(4);
        treap.insert(124);
        treap.insert(2);
        treap.insert(34);
        treap.insert(7);
        treap.insert(3);
        treap.insert(112);
        treap.insert(124);
        treap.insert(5);
        Assert.assertEquals(2, treap.findMinNode().getValue());
    }

    @Test
    public void removingMinRemovesTheMinimum() {
        treap.insert(23);
        treap.insert(41);
        treap.insert(124);
        treap.insert(2);
        treap.insert(34);
        treap.insert(7);
        treap.insert(35);
        treap.insert(112);
        treap.insert(1266);
        treap.insert(5);
        Assert.assertEquals(2, treap.findMinNode().getValue());
        treap.remove(treap.findMinValue());
        Assert.assertEquals(5, treap.findMinNode().getValue());
        treap.remove(treap.findMinValue());
        Assert.assertEquals(7, treap.findMinNode().getValue());
    }

    @Test
    public void nodeWithOneParameterInitializesCorrectly() {
        node = new Node(1);
        Assert.assertTrue(node.getLeftChild() == null);
        Assert.assertTrue(node.getRightChild() == null);
    }
}

