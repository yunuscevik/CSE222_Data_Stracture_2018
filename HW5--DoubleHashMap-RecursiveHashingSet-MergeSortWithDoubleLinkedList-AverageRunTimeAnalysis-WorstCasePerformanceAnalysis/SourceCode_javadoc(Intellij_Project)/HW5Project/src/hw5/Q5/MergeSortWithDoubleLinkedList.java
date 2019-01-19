
package hw5.Q5;

import java.util.LinkedList;

/**
 * Double LinkedList ile Merge Sort
 * @author Yunus Cevik
 */
public class MergeSortWithDoubleLinkedList{
    
    private static LinkedList<Integer> leftList;
    private static LinkedList<Integer> rightList;
    private static int size1;
    private static int size2;

    /**
     * Siralama islemi yapan metottur.alinan liste sag ve sol listeler olarak ikiye bolunur ve 
     * recursive olarak cagrilir. Merge Sort algoritmasi Divide and Conquer algoritma yapisindadir. 
     * Ayrica recursive olarak bolunerek olusturulan listeler daha sonra Merge metodu ile birlestirilerek 
     * kucukten buyuge dogru sirali bir yapi olusturur.
     * @param list Sort isleminde kullanilacak liste
     * @param left Listenin sol tarafinin size bilgisi 
     * @param right Listenin sag tarafinin size bilgisi 
     */
    public static void sort(LinkedList<Integer> list, int left, int right){
        
        if (left < right){
            int midle = (int) ((left+right)/2);
 
            sort(list, left, midle);
            sort(list , midle + 1, right);
 
            merge(list, left, midle, right);
        }
    }
    
    /**
     * Merge Sort algoritmasinin temelinde listeyi bolerek islem yaparken merge metodu ilede bu bolunmus 
     * degerler siralanarak birlestirilir. Son olarak siralanmis bir liste elde edilmis olur.
     * @param list Sort isleminde kullanilacak liste
     * @param left Listenin sol tarafinin size bilgisi 
     * @param right Listenin sag tarafinin size bilgisi 
     * @param mid Listenin ortasinin indeks degeri.
     */
    private static void merge(LinkedList<Integer> list, int left, int mid, int right){
        size1 = (mid - left + 1);
        size2 = (right - mid);
        
        leftList = new LinkedList<>();
        rightList = new LinkedList<>();
        
        for (int i = 0; i < size1; i++)
            leftList.add(list.get((int)left + i));
        for (int i = 0; i < size2; i++)
            rightList.add(list.get((int)(mid + 1+ i)));

        int i = 0, j = 0;
        int k = left;
        while (i < size1 && j < size2){
            if (leftList.get(i) <= rightList.get(j)){
                list.set(k, leftList.get(i));
                i++;
            }
            else{
                list.set(k, rightList.get(j));
                j++;
            }
            k++;
        }
        while (i < size1){
            list.set(k, leftList.get(i));
            i++;
            k++;
        }
        while (j < size2){
            list.set(k, rightList.get(j));
            j++;
            k++;
        }
    }
 
    
    
}