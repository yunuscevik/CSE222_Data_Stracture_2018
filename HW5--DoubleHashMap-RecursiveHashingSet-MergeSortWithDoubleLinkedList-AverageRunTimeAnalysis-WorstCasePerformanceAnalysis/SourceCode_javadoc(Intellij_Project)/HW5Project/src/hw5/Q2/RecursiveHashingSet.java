
package hw5.Q2;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Set interface ve recursive yapisi ile Hashing Set
 * @author Yunus Cevik
 * @param <E> E yerine yazilacak tipi belirtir.
 */
public class RecursiveHashingSet<E> implements Set<E>{
    
    private int size;
    private int CAPACITY;
    protected EntrySet<E> dataArr[];
    private boolean addRet;
    private boolean removeRet;
    private boolean containsRet;
    public RecursiveHashingSet() {
        size = 0;
        CAPACITY = 10;
        dataArr = new EntrySet[CAPACITY];
    }
    public RecursiveHashingSet(int capacity) {
        size = 0;
        this.CAPACITY = capacity;
        dataArr = new EntrySet[capacity];
    }
    private static class EntrySet<E> {
        protected E data;
        protected EntrySet<E>[] dataArr;
        private int size;

        public EntrySet() {
            data = null;
            dataArr = null;
            size = 0;
        }

        public int getSize() {
            return size;
        }

        protected void setSize(int size) {
            this.size = size;
        }
    }
    /**
     * Recursive Hashing Set Table icinde kac eleman oldugunu verir.
     * @return Recursive Hashing Set Table icinde kac eleman oldugunu verir.
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * Recursive Hashing Set Table' in bos mu dolumu oldugunu belirtir.
     * @return Eger bos ise True, dolu ise False.
     */
    @Override
    public boolean isEmpty() {
        return size != 0;
    }
    /**
     * Parametre olarak aldigi value bilgisi ile kendi icinde helper metot olan overload edilmis
     * contains metodunu cagirir ve bu metot recursive cagri ile gelen value bilgisini Hash Set
     * icinde olup olmadigini True / False olarak dondurur.
     * @param val Parametre olarak aldigi value bilgisi ile kendi icinde helper metot olan overload edilmis contains metodunu cagirir
     * @return Recursive cagri ile gelen value bilgisini Hash Set icinde olup olmadigini True / False olarak dondurur.
     */
    @Override
    public boolean contains(Object val) {
        contains(dataArr, val);
        return containsRet;
    }
    /**
     * contains(Object val) metodunun helper metodu
     * @param dataArr EntrySet<E> tipinde veri alip bu set icinde value bilgisinin olup olmadigi aranir.
     * @param val Recursive Hashing Table da aranacak value bilgisi
     */
    private void contains(EntrySet<E> dataArr [], Object val){
        containsRet = false;
        if(dataArr != null){
            int index = hash(dataArr, (E) val);
            if(dataArr[index] != null){
                if(dataArr[index].data != null && dataArr[index].data.equals(val)){
                    containsRet = true;
                    return;
                }
                else
                    contains(dataArr[index].dataArr, val);
            }
        }
    }
    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Parametrede gelen value bilgisini HashSet Table uzerinde hashCode() degerine gore belirlenen indekse yerlestirmek icin yazilmis olan
     * bu metot icerisinde overload edilmis add metodunu cagirir ve ekleme islemini gerceklestirir. Ancak collision durumu var ise tabloda bulunan
     * collision kismindan yeni bir boyutu daha düsük bir tablo acilarak bu kisminda hash metodunun belirledigi indeks degerine gore value yerlestirilir.
     * @param e Recursive Hashing Set Table' ina eklenecek value bilgisi
     * @return Eger ekleme basarili ise True, ekleme basarisiz ise False dondurulur.
     */
    @Override
    public boolean add(E e) {
        add(dataArr, e);
        return addRet;
    }
    /**
     * add(E e) helper metodudur.
     * @param dataArr EntrySet<E> tipinde veri alip bu set icinde value bilgisinin olup olmadigi kontrol eder. Buna gore ekleme islemini gerceklestirir.
     * @param e Recursive Hashing Set Table' ina eklenecek value bilgisi.
     */
    private void add(EntrySet<E> dataArr [], E e){
        addRet = false;
        if(dataArr.length <= 1){
            addRet = false;
            return;
        }    
        int index = hash(dataArr, e);
        
        if(dataArr[index] == null){
            dataArr[index] = new EntrySet<>();
            dataArr[index].data = e;
            System.out.print(" index => " + index + " - ");
            dataArr[index].setSize(dataArr[index].getSize() + 1 );
            size++;
            addRet = true;
            return;
        }
        else{
            if(dataArr[index].data != null && dataArr[index].data.equals(e)){
                System.out.println("\nThis element already exists.");
                addRet = false;
                return;
            }
            if(dataArr[index].data != null && dataArr[index].dataArr == null){
                System.out.println(dataArr[index].data + "<- ! COLLISION ! -> " + e + " Collision Index => " + index);
                int tempCap = (dataArr.length-3);
                if(tempCap <= 1)
                    tempCap = 1;
                dataArr[index].dataArr = new EntrySet[tempCap];
                add(dataArr[index].dataArr,e);
            }
            else if (dataArr[index].data != null && dataArr[index].dataArr != null){
                System.out.println(dataArr[index].data + "<- ! COLLISION ! -> " + e + " Collision Index => " + index);
                add(dataArr[index].dataArr,e);
            }
        }
    }
    /**
     * E tipinde bulunan degerinin hashCode() metodunun cagrilmasi halinin table boyutuna gore modu alindigi takdirde E tipindeki
     * degerin tabloda hangi yerde oldugunu gosteren indeks degerini verir.
     * @param dataArr EntrySet<E> tipinde veri alir
     * @param e HashCode() degerinin hesaplanmasini saglanacak value bilgisi.
     * @return  Value bilgisinin hashCode() degerine gore hesaplanir ve olustrulan indeks degeri dondururulur.
     */
    private int hash(EntrySet<E> dataArr [], E e){
        return Math.abs(e.hashCode() % dataArr.length);
    }
    /**
     * Parametrede aldigi value degerinin hash metodundan cikan indeks degeri ile HashSet Table uzerinde gosterilen indeks degerine
     * recursive olarak erisilerek tablo uzerinde silme islemi gerceklestirilir. Yani bu yere null degeri atanir.
     * @param val Recursive Hashing Set Table' ina silinecek value bilgisi
     * @return Silme islemi basarili ise True, basarisiz ise False dondurur.
     */
    @Override
    public boolean remove(Object val) {
        remove(dataArr, val);
        return removeRet;
    }
    /**
     * remove(Object val) helper metodudur.
     * @param dataArr EntrySet<E> tipinde veri alip bu set icinde value bilgisinin olup olmadigi kontrol eder. Buna gore silme islemini gerceklestirir.
     * @param val Recursive Hashing Set Table' ina silinecek value bilgisi
     */
    private void remove(EntrySet<E> dataArr [], Object val){
        removeRet = false;
        if(dataArr != null){
            int index = hash(dataArr, (E) val);
            if(dataArr[index] != null){
                if(dataArr[index].data != null && dataArr[index].data.equals(val)){
                    dataArr[index].data = null;
                    size--;
                    removeRet = true;
                    return;
                }
                else
                    remove(dataArr[index].dataArr, val);
            }
        }
    }
    @Override
    public boolean containsAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection<? extends E> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
