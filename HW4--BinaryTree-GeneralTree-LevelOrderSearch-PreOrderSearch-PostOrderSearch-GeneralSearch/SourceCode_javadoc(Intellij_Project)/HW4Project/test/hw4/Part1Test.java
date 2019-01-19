package hw4;


import org.junit.Test;

import static org.junit.Assert.*;

public class Part1Test {

    Part1<Integer> p1 = new Part1<>();
    private static final int LEVELORDERSEARCH = 1;
    private static final int POSTORDERSEARCH = 2;

    @Test
    public void add() {
        System.out.println("p1.add(parentItem, childItem, (LEVELORDERSEARCH - POSTORDERSEARCH)");
        assertTrue("Add Failed", p1.add(1,2,LEVELORDERSEARCH));
        assertTrue("Add Failed", p1.add(1,4,LEVELORDERSEARCH));
        assertTrue("Add Failed", p1.add(1,7,LEVELORDERSEARCH));
        assertTrue("Add Failed", p1.add(2,3,LEVELORDERSEARCH));
        assertTrue("Add Failed", p1.add(2,6,LEVELORDERSEARCH));
        assertTrue("Add Failed", p1.add(3,5,LEVELORDERSEARCH));
        assertTrue("Add Failed", p1.add(3,20,POSTORDERSEARCH));
        assertTrue("Add Failed", p1.add(4,21,POSTORDERSEARCH));
        assertTrue("Add Failed", p1.add(4,22,POSTORDERSEARCH));
        assertTrue("Add Failed", p1.add(2,30,POSTORDERSEARCH));
        assertTrue("Add Failed", p1.add(5,32,POSTORDERSEARCH));
        assertTrue("Add Failed", p1.add(5,33,POSTORDERSEARCH));
        System.out.println("successful");

    }

    @Test
    public void printLevelOrderTest() {
        add();
        p1.printLevelOrder();
        System.out.println();
    }

    @Test
    public void printPostorderTest() {
        add();
        p1.printPostorder();
        System.out.println();
    }

    @Test
    public void printPreOrderTraverseTest() {
        add();
        p1.printPreOrderTraverse();
        System.out.println();
    }
}