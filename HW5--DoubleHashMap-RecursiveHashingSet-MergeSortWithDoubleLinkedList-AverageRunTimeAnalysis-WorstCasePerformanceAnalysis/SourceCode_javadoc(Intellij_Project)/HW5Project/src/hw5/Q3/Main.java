
package hw5.Q3;


import java.util.LinkedList;
import java.util.Random;



/**
 * MergeSortWithDoubleLinkedList Main class
 * @author Yunus Cevik
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        LinkedList<Integer> arr = new LinkedList<>();
        Random rand = new Random();
        for(int i = 0; i < 20; i++)
            arr.add(rand.nextInt(255));
        
        System.out.print("Random Array       : ( ");
        for(int i = 0; i < arr.size(); i++){
            System.out.print(arr.get(i));
            if(i != arr.size()-1)
                System.out.print(", ");
        }
        System.out.println(" )");
        MergeSortWithDoubleLinkedList.sort(arr, 0, arr.size()-1);
        System.out.print("Merge Sorted Array : ( ");
        for(int i = 0; i < arr.size(); i++){
            System.out.print(arr.get(i));
            if(i != arr.size()-1)
                System.out.print(", ");
        }
        System.out.println(" )");


    }
    
    
}
