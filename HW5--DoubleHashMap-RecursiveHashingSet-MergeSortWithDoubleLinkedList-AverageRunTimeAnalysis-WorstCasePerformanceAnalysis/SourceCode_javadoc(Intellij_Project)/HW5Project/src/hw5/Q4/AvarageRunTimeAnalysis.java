/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw5.Q4;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Merge Sort with Double LinkedList, Merge Sort, Insertion Sort, Heap Sort ve Quick Sort 
 * algoritmalarinin 10 farkli size degeri ile 10 defa calistirilarak nano saniye cinsinden 
 * hesaplanarak ortalamalarinin alindigi classtir.
 * @author Yunus Cevik
 */
public class AvarageRunTimeAnalysis{
    
    private final Random rand;
    public AvarageRunTimeAnalysis() {
        rand = new Random();
    }
    /**
     * Kitaptabin belirttigi sort tipleri
     */
    public enum SortType {MERGESORT,INSERTIONSORT,HEAPSORT,QUICKSORT;}
    /**
     * Random List ve Array uretir.
     * @param produceList Random uretilecek liste
     * @param produceArray Random urerilecek array
     * @param size Arrayin size bilgisi
     */
    public void producerArrayAndList(LinkedList<Integer> produceList, Integer[] produceArray, int size){
        for(int i = 0; i < size; i++){
            int val = rand.nextInt(255);
            produceList.add(val);
            produceArray[i] = val;
        }
    }
    
    public void printList(LinkedList<Integer> list, String title){
        System.out.print("\n"+ title + ": ");
        Iterator<Integer> iter = list.iterator();
        int i=0;
        int j = 20;
        System.out.print("( ");
        while(iter.hasNext()){
            System.out.print(iter.next());
            if(i != list.size()-1)
                System.out.print(", ");
            if(i == j){
                j+=20;
                System.out.println();
            }
            i++;
        }
        System.out.println(" )");
    }
    
    public void printArray(Integer[] arr, int size, String title){
        System.out.print("\n"+ title + ": ");
        System.out.print("( ");
        for(int i = 0, j = 20; i < size; i++){
            System.out.print(arr[i]);
            if(i != size-1)
                System.out.print(", ");
            if(i == j){
                j += 20;
                System.out.println();
            }   
        }
        System.out.println(" )");
    }
    /**
     * Listenin Elapsed Nano Times' inin hesaplandigi metot.
     * @param list Random uretilmis liste
     * @param print Ekran ciktisi verip vermek icin kullanilan parametre
     * @return Ortalama Elapsed Nano Time dondurulur.
     */
    public double AvgMergeSortWithDoubleLinkedList(LinkedList<Integer> list, boolean print){
        long startTime = 0;
        long endTime = 0;
        long sum = 0;
        int j = 20;
        if(print) 
            System.out.print("Elapsed Nano Times: ( ");
  
        for(int i = 0; i < list.size(); i++){
            startTime = System.nanoTime();
                MergeSortWithDoubleLinkedList.sort(list, 0, list.size()-1);
            endTime = System.nanoTime();
            if(print) {
                System.out.print(endTime - startTime);
                if (i != list.size() - 1)
                    System.out.print(", ");
                if (i == j) {
                    j += 20;
                    System.out.println();
                }
            }
            sum += (endTime - startTime);
        }
        if(print)
            System.out.print(" )");
        return ((double)sum / (double)list.size());
    }
    
    /**
     * Arrayin Elapsed Nano Times' inin hesaplandigi metot.
     * @param arr Random uretilen array.
     * @param size Arrayin size bilgisi
     * @param type Hangi sort algoritmasinin calisacagi
     * @param print Ekran ciktisi verip vermek icin kullanilan parametre
     * @return Ortalama Elapsed Nano Time dondurulur.
     */
    public double AvgSorts(Integer[] arr, int size,SortType type, boolean print){
        long startTime = 0;
        long endTime = 0;
        long sum = 0;
        int j = 20;
        if(print)
            System.out.print("Elapsed Nano Times: ( ");
        Integer[] tempArr = new Integer[arr.length];
        switch(type){
            case MERGESORT:
                for(int i = 0; i < size; i++){
                    System.arraycopy(arr, 0, tempArr, 0, arr.length);
                    startTime = System.nanoTime();
                        MergeSort.sort(tempArr);
                    endTime = System.nanoTime();
                    if(print) {
                        System.out.print(endTime - startTime);
                        if (i != size - 1)
                            System.out.print(", ");
                        if (i == j) {
                            j += 20;
                            System.out.println();
                        }
                    }
                    sum += (endTime - startTime);
                }
                break;
            case INSERTIONSORT:
                for(int i = 0; i < size; i++){
                    System.arraycopy(arr, 0, tempArr, 0, arr.length);
                    startTime = System.nanoTime();
                        InsertionSort.sort(tempArr);
                    endTime = System.nanoTime();
                    if(print) {
                        System.out.print(endTime - startTime);
                        if (i != size - 1)
                            System.out.print(", ");
                        if (i == j) {
                            j += 20;
                            System.out.println();
                        }
                    }
                    sum += (endTime - startTime);
                }
                break;
            case HEAPSORT:
                for(int i = 0; i < size; i++){
                    System.arraycopy(arr, 0, tempArr, 0, arr.length);
                    startTime = System.nanoTime();
                        HeapSort.sort(tempArr);
                    endTime = System.nanoTime();
                    if(print) {
                        System.out.print(endTime - startTime);
                        if (i != size - 1)
                            System.out.print(", ");
                        if (i == j) {
                            j += 20;
                            System.out.println();
                        }
                    }
                    sum += (endTime - startTime);
                }
                break;
            case QUICKSORT:
                for(int i = 0; i < size; i++){
                    System.arraycopy(arr, 0, tempArr, 0, arr.length);
                    startTime = System.nanoTime();
                        QuickSort.sort(tempArr);
                    endTime = System.nanoTime();
                    if(print) {
                        System.out.print(endTime - startTime);
                        if (i != size - 1)
                            System.out.print(", ");
                        if (i == j) {
                            j += 20;
                            System.out.println();
                        }
                    }
                    sum += (endTime - startTime);
                }
                break;    
        }
        if(print)
            System.out.print(" )");
        
        return ((double)sum / (double)size);  
    }
    
    
    
    public void AvgSortsPrint(LinkedList<Integer> list, Integer[] array, int size, String listTitle, String arrayTitle , boolean print){
        Integer[][] tempArray = new Integer[4][size];
        for(int i = 0; i < 4; i++)
            System.arraycopy(array, 0, tempArray[i], 0, size);

        if(print)
            printList(list, "***Merge Sort With Double Linked List***\n" + listTitle);
        double avgList1 = AvgMergeSortWithDoubleLinkedList(list, print);
        if(print)
            printList(list, "Ordered " + listTitle);
        System.out.printf("\nAvarage of Merge Sort With Double Linked List: %.2f\n", avgList1);

        if(print)
            printArray(tempArray[0], size, "***Merge Sort***\n" + arrayTitle);
        double avgArrMergeSort1 = AvgSorts(tempArray[0], 10, AvarageRunTimeAnalysis.SortType.MERGESORT, print);
        if(print)
            printArray(tempArray[0], size, "Ordered " + arrayTitle);
        System.out.printf("\nAvarage of Merge Sort: %.2f\n", avgArrMergeSort1);

        if(print)
            printArray(tempArray[1], size, "***Insertion Sort***\n" + arrayTitle);
        double avgArrInsertionSort1 = AvgSorts(tempArray[1], 10, AvarageRunTimeAnalysis.SortType.INSERTIONSORT ,print);
        if(print)
            printArray(tempArray[1], size, "Ordered " + arrayTitle);
        System.out.printf("\nAvarage of Insertion Sort: %.2f\n", avgArrInsertionSort1);

        if(print)
            printArray(tempArray[2], size, "***Heap Sort***\n" + arrayTitle);
        double avgArrHeapSort1 = AvgSorts(tempArray[2], 10, AvarageRunTimeAnalysis.SortType.HEAPSORT, print);
        if(print)
            printArray(tempArray[2], size, "Ordered " + arrayTitle);
        System.out.printf("\nAvarage of Heap Sort: %.2f\n", avgArrHeapSort1);

        if(print)
            printArray(tempArray[3], size, "***Quick Sort***\n" + arrayTitle);
        double avgArrQuickSort1 = AvgSorts(tempArray[3], 10, AvarageRunTimeAnalysis.SortType.QUICKSORT, print);
        if(print)
            printArray(tempArray[3], size, "Ordered " + arrayTitle);
        System.out.printf("\nAvarage of Quick Sort: %.2f\n", avgArrQuickSort1);
    }
}
