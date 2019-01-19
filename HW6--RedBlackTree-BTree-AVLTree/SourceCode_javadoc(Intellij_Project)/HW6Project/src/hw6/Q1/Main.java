
package hw6.Q1;

/**
 * Red - Black Tree Testi.
 * @author Yunus Cevik
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("\t\t----- Red Black Tree (1) -----\n");
        RedBlackTree<Integer> rbt1 = new RedBlackTree<>();
        rbt1.add(40);
        rbt1.add(38);
        rbt1.add(27);
        rbt1.add(24);
        rbt1.add(20);
        rbt1.add(17);
        rbt1.add(16);
        rbt1.add(13);
        rbt1.add(11);
        rbt1.add(8);
        rbt1.add(5);
        rbt1.add(3);
        rbt1.add(0);
        rbt1.add(-5);
        System.out.println(rbt1.toString());
        
        System.out.println("----------------------------------------------------------\n");
        System.out.println("\t\t----- Red Black Tree (2) -----\n");
        RedBlackTree<Integer> rbt2 = new RedBlackTree<>();
        rbt2.add(5);
        rbt2.add(6);
        rbt2.add(7);
        rbt2.add(8);
        rbt2.add(10);
        rbt2.add(14);
        rbt2.add(16);
        rbt2.add(30);
        rbt2.add(34);
        rbt2.add(40);
        rbt2.add(42);
        rbt2.add(45);
        rbt2.add(50);
        rbt2.add(52);
        System.out.println(rbt2.toString());
        System.out.println("----------------------------------------------------------\n");
        
    }
    
}
