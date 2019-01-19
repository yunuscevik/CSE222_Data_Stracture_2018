package hw4;

import org.junit.Test;

import static org.junit.Assert.*;

public class Part2Test {
    Part2<MultiArgs<Integer>> p2 = new Part2<>();

    @Test
    public void add() {
        System.out.println("---------- p2.add(new MultiArgs<>(dimensionValue: int, ...args: int,int,int) ----------");
        try {
            assertTrue("Add Failed", p2.add(new MultiArgs<>(3, 2, 4, 5)));
            assertTrue("Add Failed", p2.add(new MultiArgs<>(3, 1, 3, 5)));
            assertTrue("Add Failed", p2.add(new MultiArgs<>(3, 4, 2, 5)));
            assertTrue("Add Failed", p2.add(new MultiArgs<>(3, 5, 4, 5)));
            assertTrue("Add Failed", p2.add(new MultiArgs<>(3, 3, 4, 5)));
            assertTrue("Add Failed", p2.add(new MultiArgs<>(3, 10, 4, 5)));
            assertTrue("Add Failed", p2.add(new MultiArgs<>(3, 53, 45, 51)));
            assertTrue("Add Failed", p2.add(new MultiArgs<>(3, 50, 30, 67)));
            assertTrue("Add Failed", p2.add(new MultiArgs<>(3, 10, 46, 5)));
            System.out.println("Add Results => true\n");
            System.out.println("MULTI DIMENSION TREE\n\n"+p2.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void contains() {
        try {
            add();
            System.out.println("---------- p2.contains(new MultiArgs<>(dimensionValue: 3, ...args: 53,45,51) ----------");
            boolean contains = p2.contains(new MultiArgs<>(3, 53, 45, 51));
            System.out.println("Contains Result => " + contains + "\n");

            System.out.println("---------- p2.contains(new MultiArgs<>(dimensionValue: 3, ...args: 100,100,100) ----------");
            contains = p2.contains(new MultiArgs<>(3, 100, 100, 100));
            System.out.println("Contains Result => " + contains + "\n");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void find() {
        try {
            add();
            System.out.println("---------- p2.find(new MultiArgs<>(dimensionValue: 3, ...args: 5,4,5) ----------");
            MultiArgs<Integer> foundValue = p2.find(new MultiArgs<>(3,5,4,5));
            System.out.println("Found Value => "+foundValue.toString() + "\n");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void delete() {
        try {
            add();
            System.out.println("---------- p2.delete(new MultiArgs<>(dimensionValue: 3, ...args: 3,4,5) ----------");
            MultiArgs<Integer> deletedValue = p2.delete(new MultiArgs<>(3,3,4,5));
            System.out.println("Deleted Value => "+deletedValue.toString() + "\n");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void remove() {
        try {
            add();
            System.out.println("---------- p2.remove(new MultiArgs<>(dimensionValue: 3, ...args: 3,4,5) ----------");
            boolean remove = p2.remove(new MultiArgs<>(3,3,4,5));
            System.out.println("Remove Result => "+ remove + "\n");


            System.out.println("---------- p2.remove(new MultiArgs<>(dimensionValue: 3, ...args: 3,4,5) ----------");
            remove = p2.remove(new MultiArgs<>(3,3,4,5));
            System.out.println("Remove Result => "+ remove + "\n");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}