package hw5.Q5;

import java.util.LinkedList;



/**
 * WorstCasePerformanceAnalysis Main class
 * @author Yunus Cevik
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WorstCasePerformanceAnalysis arta = new WorstCasePerformanceAnalysis();
        System.out.println("\n\t\t---------- Size:" + 100 +" OPERATION (Worst - Case) ----------");
        LinkedList<Integer> list1 = new LinkedList<>();
        Integer arr1[] = new Integer[100];
        arta.producerArrayAndList(list1, arr1, 100);
        arta.WorstCaseSortsPrint(list1, arr1, 100);
        
        
        System.out.println("\n\t\t---------- Size:" + 1000 +" OPERATION (Worst - Case) ----------");
        LinkedList<Integer> list2 = new LinkedList<>();
        Integer arr2[] = new Integer[1000];
        arta.producerArrayAndList(list2, arr2, 1000);
        arta.WorstCaseSortsPrint(list2, arr2, 1000);
        
        
        System.out.println("\n\t\t---------- Size:" + 5000 +" OPERATION (Worst - Case) ----------");
        LinkedList<Integer> list3 = new LinkedList<>();
        Integer arr3[] = new Integer[5000];
        arta.producerArrayAndList(list3, arr3, 5000);
        arta.WorstCaseSortsPrint(list3, arr3, 5000);
        
        
        System.out.println("\n\t\t---------- Size:" + 10000 +" OPERATION (Worst - Case) ----------");
        LinkedList<Integer> list4 = new LinkedList<>();
        Integer arr4[] = new Integer[10000];
        arta.producerArrayAndList(list4, arr4, 10000);
        arta.WorstCaseSortsPrint(list4, arr4, 10000);
        
    }
    
    
}
