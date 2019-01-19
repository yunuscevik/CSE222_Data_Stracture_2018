
package hw5.Q1;

import java.util.Collection;
import java.util.Map;
import java.util.Set;



/**
 * Map interface ve double LinkedList ile Hash Map.
 * @author Yunus Cevik
 * @param <K> Key
 * @param <V> Value
 */
public class DoubleHashMap<K, V> implements Map<K, V>{

    private Pair<K, V>[] dhmTable;
    private int size;
    private int deleteSize;
    private final int CAPACITY;
    private final int PRIME = 7;
    private final double LOAD_THRESHOLD = 0.75;
    
    public DoubleHashMap(){
        CAPACITY = 7;
        dhmTable = new Pair[getCAPACITY()];
    }
    public DoubleHashMap(int capacity){
        this.CAPACITY = capacity;
        dhmTable = new Pair[getCAPACITY()];
    }
    public Pair<K, V>[] getDhmTable() {
        return dhmTable;
    }
    
    private int getSize() {
        return size;
    }
    
    public int getCAPACITY() {
        return CAPACITY;
    }

    public int getDeleteSize() {
        return deleteSize;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    protected class Pair < K, V > {

        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }
    }
    /**
     * Key degerinin hashCode() metodunun cagrilmis halinin tablo boyutuna gore
     * modu alindigi takdirde key degerinin tabloda hangi yerde oldugunu gosteren
     * indeks degerini verir.
     * @param key Indeks degerinin hesaplanmasi icin gelen parametre
     * @return Double Hashing Map uzerinde key degerine sahip pair bilgisinin hangi indekste olacagini dondurur.
     */
    private int hash1(K key){
        return Math.abs(key.hashCode() % dhmTable.length);
    }
    /**
     * Belirlenen sabit bir PRIME degerinen key degerinin hashCode() metodunun cagrilmis halinin
     * tablo boyutuna gore modunun alinmis hali cikarilarak yeni bir indeks degeri belirlenir.
     * Bu olay collision oldugu takdirde yapilir ve double hash mantigi bu sekilde yapilir ve collisionlar engellemeye calisilir.
     * @param key Indeks degerinin hesaplanmasi icin gelen parametre
     * @return Double Hashing Map uzerinde key degerine sahip pair bilgisinin hangi indekste olacagini dondurur.
     */
    private int hash2(K key){
        return Math.abs(PRIME - (key.hashCode() % PRIME));
    }
    /**
     * Double Hashing Map icerisinde bulunan verilerin sayisini verir.
     * @return Tablodaki elemen sayisini verir.
     */
    @Override
    public int size() {
        return getSize();
    }
    /**
     * Double Hashing Map tablosunun bos olup olmadigini belirtir.
     * @return Tabloda eleman yoksa True, varsa False dondurur.
     */
    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public boolean containsKey(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsValue(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Parametre olarak gelen key degerini tablo icerisinde aratarak kacinci indekse sahip oldugunu veren metottur.
     * Bu metotta gelen key degerinin tablo uzerinde nerede oldugunu bilmek icin key.hashCode() metotu cagrilir ve tablonun boyutuna bolunerek
     * indeks degeri hesaplanir ve tabloda bu indeks degerine gidilerek eleman var ise bu indeks degeri dondurulur.
     * @param key Double Hashing Map de arama yapilmasi icin gelen key parametresi.
     * @return Double Hashing Map de bulunan degerin indeksini verir.
     */
    private int find(Object key) {
        int index = key.hashCode() % dhmTable.length;
        if (index < 0)
            index += dhmTable.length;

        while ( (dhmTable[index] != null) && (!key.equals(dhmTable[index].getKey()))) {
            index++;
            if (index >= dhmTable.length)
              index = 0; 
        }
        return index;
    }
    /**
     * Find metodu cagrilarak aranan key degerini kacinci indekste oldugunu tespit edilir.
     * Daha sonra bu indeks degerine tablo uzerinden erisilerek aranan key degeri ile ayni key degerine
     * saptanir ise value bilgisi dondurulur. Eger key degerleri bir biri ile uyusmuyor ise null dondurulur.
     * @param key Double Hashing Map de arama yapilmasi icin gelen key parametresi.
     * @return Eger key degeri tabloda var ise value bilgisini dondurur. Yoksa null dondurur.
     */
    @Override
    public V get(Object key) {
        int index = find(key);
        if (dhmTable[index] != null)
            return dhmTable[index].getValue();
        else
            return null; 
    }
    /**
     * Key ve value bilgilerini tabloya ekleyebilmek icin ilk basta hash1 metodu uygulanarak
     * indeks degeri belirlenir ve bu indeks eger bos ise key ve value bilgisi pair olarak eklenir.
     * Ancak bu indekse sahip bir deger varsa ve key degerleri ayni ise value bilgisi guncellenir.
     * Eger indeks degerinde her hangi bir key degeri var ise collision oldugundan dolayi hash2 metodu
     * uygulanir ve collision durumu onlenmeye calisilir. Boylece collision olmadan tablo uzerinde 
     * ekleme islemi gerceklestirilir.
     * @param key Double Hashing Map de olup olmadiginin kontrolu icin gelen key parametresi
     * @param value Key degerinin belirtigi value bilgisi.
     * @return Ekleme islemi basarili ise eklenen Pair' in value bilgisi dondururlur.
     * Ekleme basarisiz ise null dondurulur.
     */
    @Override
    public V put(K key, V value) {
              
        int index = hash1(key);

        if (dhmTable[index] != null)
        {
            if((dhmTable[index].getKey()).equals(key)){
                V oldVal = dhmTable[index].getValue();
                dhmTable[index] = new Pair(key, value);
                return oldVal;
            }
            else{
                int index2 = hash2(key);
                
                if (dhmTable[index2] == null){   
                    dhmTable[index2] = new Pair(key, value);
                    size++;
                    double loadFactor = (double) (getSize() + getDeleteSize()) / dhmTable.length;
                    if (loadFactor > LOAD_THRESHOLD)
                        rehash();
                    return null;
                }
                else{
                    int i = 1;
                    while (true)
                    {
                        int newIndex = (index + i * index2) % dhmTable.length;
                        if (dhmTable[newIndex] == null)
                        {   
                            dhmTable[newIndex] = new Pair(key, value);
                            size++;
                            double loadFactor = (double) (getSize() + getDeleteSize()) / dhmTable.length;
                            if (loadFactor > LOAD_THRESHOLD)
                              rehash();
                            return null;
                        }
                        i++;
                    }
                }
            }
        }
        
        else{
            dhmTable[index] = new Pair(key, value);
            size++;
        }
        return dhmTable[index].getValue();
    }
    /**
     * Tablo uzerinde eklenecek bir indeks kalmadigi takdirde bu rehash islemi yapilarak
     * tablo boyutu iki katinin bir fazlasina cikarilir. Daha sonra eski elemanlar yeni hash tabloya gore
     * tekrardan put metodu ile eklenir.
     */
    private void rehash() {

        Pair <K, V>[] tempTable = dhmTable;
        dhmTable = new Pair[2 * tempTable.length + 1];
        
        size = 0;
        deleteSize = 0;
        for (Pair<K, V> tempPair : tempTable) {
            if ((tempPair != null) && (tempPair != (new Pair(null,null)))) {
                put(tempPair.getKey(), tempPair.getValue());
            }
        }
    }
    /**
     * Key degerinin belirledigi indeks degerine tablo uzerinden ulasilarak bu yer deki bilgi yerine
     * null atanarak silme islemi gerceklestirilir. Silinen deger geri dondurulur.
     * @param key Double Hashing Map uzerinde silinecek Pair'in aranmasi icin gelen key degeri.
     * @return Eger silme basarili ise silinen Pair' in value bilgisi, basarisiz ise null dondurulur.
     */
    @Override
    public V remove(Object key) {
        int index = find(key);
        if (dhmTable[index] == null)
            return null;
        V retVal = dhmTable[index].getValue();
        deleteSize++;
        dhmTable[index] = new Pair<>(null,null);
        size--;
        return retVal;        
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
