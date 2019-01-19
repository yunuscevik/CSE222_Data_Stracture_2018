
package hw6.Q2;


/**
 * Btree Testi.
 * @author Yunus Cevik
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static  void main(String args[]) {
        System.out.println("\t\t----- BTree<Integer> (1) (order: 5) -----\n");
        BTree<Integer> bTree1 = new BTree<>(5);
        bTree1.add(-25);
        bTree1.add(-26);
        bTree1.add(0);
        bTree1.add(5);
        bTree1.add(69);
        bTree1.add(81);
        bTree1.add(100);
        bTree1.add(10);
        bTree1.add(13);
        bTree1.add(15);
        bTree1.add(41);
        bTree1.add(53);
        bTree1.add(20);
        bTree1.add(25);
        bTree1.add(30);
        System.out.println( bTree1.toString());
        
        System.out.println("----------------------------------------------------------\n");
        
        System.out.println("\t\t----- BTree<Double> (2) (order: 7) -----\n");
        BTree<Double> bTree2 = new BTree<>(7);
        bTree2.add(111.66);
        bTree2.add(120.5);
        bTree2.add(10.1);
        bTree2.add(11.8);
        bTree2.add(92.1);
        bTree2.add(98.11);
        bTree2.add(20.33);
        bTree2.add(25.71);
        bTree2.add(41.52);
        bTree2.add(42.0);
        bTree2.add(51.52);
        bTree2.add(86.63);
        bTree2.add(100.0);
        bTree2.add(101.2);
        bTree2.add(106.9);
        bTree2.add(1.0);
        bTree2.add(3.52);
        bTree2.add(5.63);
        bTree2.add(11.0);
        bTree2.add(12.2);
        bTree2.add(200.9);

        System.out.println( bTree2.toString());
        
        System.out.println("----------------------------------------------------------\n");
    }
    
}
