package hw5.Q4;

import java.util.LinkedList;



/**
 * AvarageRunTimeAnalysis Main class
 * @author Yunus Cevik
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AvarageRunTimeAnalysis arta = new AvarageRunTimeAnalysis();
        int size = 300;
        for(int i = 0; i < 10; i++){
            System.out.println("\n\t\t---------- " + (i+1) +". OPERATION (Size: "+ ((i+1)*size) +") (Avarage - Case)----------");
            LinkedList<Integer> list = new LinkedList<>();
            Integer arr[] = new Integer[((i+1)*size)];
            arta.producerArrayAndList(list, arr, ((i+1)*size));
            arta.AvgSortsPrint(list, arr, ((i+1)*size),"List" +(i+1),"Array"+(i+1), false);
        }
    }
    
    
}
