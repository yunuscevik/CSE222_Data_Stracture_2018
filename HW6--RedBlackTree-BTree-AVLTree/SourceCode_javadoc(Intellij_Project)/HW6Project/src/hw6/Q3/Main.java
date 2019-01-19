/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw6.Q3;



/**
 * Binary Tree'nin AVL Tree olup olmadiginin Testi.
 * @author Yunus Cevik
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("\t\t----- AVL<Integer> (1) -----\n");
        BinarySearchTree<Integer> bst1 = new BinarySearchTree();
        for(int i = 10; i < 20; i++)
            bst1.add(i);
        try {
            System.out.println(bst1 + "\n\n");
            AVLTree<Integer> avl1 = new AVLTree<>(bst1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("----------------------------------------------------------\n");
        System.out.println("\t\t----- AVL<Integer> (2) -----\n");
        
        BinarySearchTree<Integer> bst2 = new BinarySearchTree();
        bst2.add(20);
        bst2.add(9);
        bst2.add(40);
        bst2.add(7);
        bst2.add(11);
        bst2.add(32);
        bst2.add(51);
        bst2.add(5);
        bst2.add(8);
        bst2.add(10);
        bst2.add(15);
        bst2.add(30);
        bst2.add(33);
        bst2.add(50);
        bst2.add(59);
        
        try {
            System.out.println(bst2 + "\n\n");
            AVLTree<Integer> avl2 = new AVLTree<>(bst2);
            System.out.println(avl2.toString() + "\n\n");
            System.out.println(avl2.delete(7) + " <= bst2.delete(7)\n");
            System.out.println(avl2.delete(11) + " <= bst2.delete(11)\n");
            System.out.println(avl2.toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }
    
}
