
package hw5.Q5;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Yunus Cevik
 */
public class WorstCasePerformanceAnalysis{
    
    private final Random rand;
    public WorstCasePerformanceAnalysis() {
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
    public double WorstCaseMergeSortWithDoubleLinkedList(LinkedList<Integer> list,boolean print){
        long startTime = 0;
        long endTime = 0;
        long sum = 0;
        if(print)
            System.out.print("Merge Sort With Double Linked List Elapsed Nano Times: ( ");
        startTime = System.nanoTime();
            MergeSortWithDoubleLinkedList.sort(list, 0, list.size()-1);
        endTime = System.nanoTime();
        if(print)
            System.out.print(endTime - startTime);
        sum = (endTime - startTime);
        if(print)    
            System.out.println(" )");
        return (double)sum;
    }
    
    /**
     * Arrayin Elapsed Nano Times' inin hesaplandigi metot.
     * @param arr Random uretilen array.
     * @param size Arrayin size bilgisi
     * @param type Hangi sort algoritmasinin calisacagi
     * @param print Ekran ciktisi verip vermek icin kullanilan parametre
     * @return Ortalama Elapsed Nano Time dondurulur.
     */
    public double WorstCaseSorts(Integer[] arr, int size,SortType type, boolean print){
        long startTime = 0;
        long endTime = 0;
        long sum = 0;
        switch(type){
            case MERGESORT:
                if(print)
                    System.out.print("Merge Sort Elapsed Nano Times: ( ");
                startTime = System.nanoTime();
                    MergeSort.sort(arr);
                endTime = System.nanoTime();
                if(print)
                    System.out.print(endTime - startTime);
                sum = (endTime - startTime);
                
                break;
            case INSERTIONSORT:
                if(print)
                    System.out.print("Insertion Sort Elapsed Nano Times: ( ");
                startTime = System.nanoTime();
                    InsertionSort.sort(arr);
                endTime = System.nanoTime();
                if(print)
                    System.out.print(endTime - startTime);
                sum = (endTime - startTime);
                break;
            case HEAPSORT:
                if(print)
                    System.out.print("Heap Sort Elapsed Nano Times: ( ");
                startTime = System.nanoTime();
                    HeapSort.sort(arr);
                endTime = System.nanoTime();
                if(print)
                    System.out.print(endTime - startTime);
                sum = (endTime - startTime);
                break;
            case QUICKSORT:
                if(print)
                    System.out.print("Quick Sort Elapsed Nano Times: ( ");
                startTime = System.nanoTime();
                    QuickSort.sort(arr);
                endTime = System.nanoTime();
                if(print)
                    System.out.print(endTime - startTime);
                sum = (endTime - startTime);
                break;    
        }
        if(print)
            System.out.println(" )");
        
        return (double)sum; 
    }
    
    
    
    public void WorstCaseSortsPrint(LinkedList<Integer> list, Integer[] array, int size){
        Integer[][] tempArray = new Integer[4][size];
        for(int i = 0; i < 4; i++)
            System.arraycopy(array, 0, tempArray[i], 0, size);
        
        //--------------------------------------------------------------
        WorstCaseMergeSortWithDoubleLinkedList(list,false);
        WorstCaseSorts(tempArray[0], size, WorstCasePerformanceAnalysis.SortType.MERGESORT,false);
        reverseListAndArr(list, tempArray[0], size);
        WorstCaseMergeSortWithDoubleLinkedList(list,true);
        WorstCaseSorts(tempArray[0], size, WorstCasePerformanceAnalysis.SortType.MERGESORT,true);
        //--------------------------------------------------------------
        WorstCaseSorts(tempArray[1], size, WorstCasePerformanceAnalysis.SortType.INSERTIONSORT,false);
        reverseListAndArr(list, tempArray[1], size);
        WorstCaseSorts(tempArray[1], size, WorstCasePerformanceAnalysis.SortType.INSERTIONSORT,true);
        //--------------------------------------------------------------
        WorstCaseSorts(tempArray[2], size, WorstCasePerformanceAnalysis.SortType.HEAPSORT,false);
        reverseListAndArr(list, tempArray[2], size);
        WorstCaseSorts(tempArray[2], size, WorstCasePerformanceAnalysis.SortType.HEAPSORT,true);
        //--------------------------------------------------------------
        WorstCaseSorts(tempArray[3], size, WorstCasePerformanceAnalysis.SortType.QUICKSORT,false);
        reverseListAndArr(list, tempArray[3], size);
        WorstCaseSorts(tempArray[3], size, WorstCasePerformanceAnalysis.SortType.QUICKSORT,true);
    }
    
    private void reverseListAndArr(LinkedList<Integer> list,Integer[] arr, int size){
        LinkedList<Integer> tempList = new LinkedList<>();
        Integer tempArr[] = new Integer[size];
        
        for(int i = 0, j = size-1; i < size; i++,j--){
            tempList.add(list.get(j));
            tempArr[i] = arr[j];
        }
        for(int i = 0; i < size; i++){
            list.set(i, tempList.get(i));
            arr[i] = tempArr[i];
        }    
    }
}
