
package HW3;

import java.util.NoSuchElementException;

/**
 * Generic bir array ile liste yapisi olusturulur.
 * Ayrica implement edilen add, remove, hasNext, next, nextInSemester ve size metodlari kullanilir.
 * @author Yunus CEVIK
 * @param <E> Generic olarak belirtilir. Ancak hangi tipte verilirse o tipe donusur.
 */
public class Part3<E> extends Part3_Abstract<E>{
    private E[] list;
    private int used;
    private int capacity;
    private int count;
    private int countSemester;
    private int tail;
    private int head;
    /**
     * Default Constructor = Belirli bir kapasitede array olusturulur.
     */
    public Part3() {
        used = 0;
        capacity = 50;
        this.list =  (E[]) new Object[capacity];
        count = 0;
        countSemester = 0;
    }
    /**
     * Tek parametre alan Constructor = Generic bir arrayi parametre olarak alan bu Constructor,
     * ayni zamanda kendi icinde bulunan arraye parametreden gelenleri kopyalar.
     * @param other Herhangi bir array i belirten parametrik deger.
     */
    public Part3(E[] other){
        used = 0;
        capacity = 50;
        this.list =  (E[]) new Object[capacity];
        count = 0;
        countSemester = 0;
        for(int i = 0; i < other.length; i++){
            if(full() == true)
                reallocate();
            list[i] = other[i];
            used++;
            
        }
    }

    
    
    
    // ---- getter ---- //
    public Object[] getList() { return list; }
    public int getCapacity() { return capacity; }
    private int getUsed() { return used; }
    // ---- setter ---- //
    private void setCapacity(int cap) { this.capacity = cap; }
    private void setUsed(int us) { this.used = us; }
    public boolean empty() { return (getUsed() == 0); }

    /**
     * Arrayin ilk belirtilen kapasitesi ile elemanlarin sayisi esit oldugunda tekrardan yer almak icin kullanilan metoddur.
     */
    private void reallocate(){
        setCapacity(getCapacity()*2);
        Object [] newData = new Object[getCapacity()];
        System.arraycopy(list, 0, newData, 0, getUsed());
        list =  (E[]) newData;
    }
    /**
     * Hafizadan alinan yerin doldugunu bildirir.
     * @return Kapasite ve Size bilgileri birbirine esit ise True, degil ise False return eder.
     */
    public boolean full(){
        return (getCapacity() == getUsed());
    }
    
    /**
     * Listenin icindeki eleman bilgisini verir.
     * @return Listenin Size bilgisini return eder.
     */
    @Override
    public int size() { return getUsed(); }
    /**
     * Listenin sonuna parametreden gelen veriyi ekleme islemi yapar.
     * @param value Parametre olarak gelen generic deger.
     * @return Add islemi yapildiginda True degeri donderir.
     */
    @Override
    public boolean add(E value){
        if(full() == true)
            reallocate();
        list[getUsed()] = value;
        setUsed(getUsed() + 1);
        return true;
        
    }
    /**
     * Liste uzerinde parametreden gelen index degeri ve veriyi index degerine gore ekleme islemi yapar.
     * Ayrica listede araya ekleme islemi yapildiginda kaydirmasi gerekirse, kaydirma isleminide gerceklestirir.
     * @param index Liste uzerinde hangi index e eklenmesini bildiren parametre.
     * @param value Parametre olarak gelen generic deger.
     */
    @Override
    public void add(int index, E value){
        if(index >=0 && index < size()){
            Object[] tempList = new Object[this.getCapacity()];
            for(int i = 0,j = 0; i < size(); i++,j++){
                if(i != index)
                    tempList[i] = list[j];
                else{
                    tempList[index] = value;
                    setUsed(getUsed() + 1);
                    tempList[++i] = list[j];
                }
            }
            list = (E[]) tempList;
        }
        else
            System.out.println("You can not add the"+ index +". index element");
    }
    /**
     * Listenin son elemanini silmeye yarar.
     * @return Silinen elemani return eder.
     */
    @Override
    public E remove(){
        E ret = null;
        if(!empty()){
            ret = (E) list[size()-1];
            setUsed(getUsed() - 1);
        }
        else
            System.out.println("Deletion failed. The list is empty.");
        return ret;
    }
    /**
     * Parametrede gelen degere gore liste uzerinde arama yapar ve listede varsa bu deger silinir.
     * @param value Silinecek deger parametrede belirtilir.
     * @return Silme islemi basariyla gerceklesmis ise True, gerceklesmemis ise False return eder.
     */
    @Override
    public boolean remove(E value){
        boolean check = false;
        if(!empty()){
            for(int i = 0; i < size(); i++){
                if(list[i].equals(value))
                    check = true;
            }
            if(check == true){   
                Object [] tempList = new Object[getCapacity()];
                for(int i = 0,j = 0; i < size(); i++,j++){
                    if(!(list[i].equals(value)))
                        tempList[i] = list[j];
                    else
                        tempList[i] = list[++j];
                }
                setUsed(getUsed() - 1);
                list = (E[]) tempList;
                return true;
            }
            else
                System.out.println(value + " value is not found on the list. It can not be deleted for this reason.");
        }
        else
            System.out.println("Deletion failed. The list is empty.");
        return false;
        
    }
    /**
     * Parametrede gelen index degerine gore liste uzerinde arama yapar ve listede varsa o index e sahip deger silinir.
     * @param index Silinecek degerin index i parametrede belirtilir.
     * @return Silinen elemani return eder.
     */
    @Override
    public E remove(int index){
        E ret = null;
        if(index >=0 && index < size()){
            if(!empty()){
                Object [] tempList = new Object[getCapacity()];
                for(int i = 0,j = 0; i < size(); i++,j++){
                    if(i != index)
                        tempList[i] = list[j];
                    else{
                        ret = (E) list[j];
                        tempList[i] = list[++j];
                    }
                }
                setUsed(getUsed() - 1);
                list = (E[]) tempList;
            }
            else
                System.out.println("Silme başarısız eleman yok");
        }
        else
            System.out.println(index + ". indexden silme yapamazsınız");
        return ret;
        
    }
    /**
     * Next isleminin yapilip yapilmayacagini check eder.
     * @return Next isleminin yapilip yapilmayacagini check eder ve ona gore True ya da False return eder.
     */
    @Override
    public boolean hasNext(){
        boolean check = true;
        if(count < size())
            check = true;
        else{
            check = false;
            count = 0;
        }
        return check;
        
    }
    /**
     * Liste uzerinde next metodu listedeki verilere sirayla ulasir ve ilerleme gerceklestirilir.
     * @return Liste uzerinde siradaki veri dondurulur
     * @throws NoSuchElementException Listede eleman yoksa exception firlatilir.
     */
    @Override
    public E next() throws NoSuchElementException{
        if (! hasNext())
            throw new NoSuchElementException();
        return (E) list[count++];
    }
    /**
     * Parametrede bulunan semester bilgisine gore o semestere ait veri olup olmadigi kontrol edilir.
     * Ayrica semester degerine gore ilk ve son bulunan semester degeri belirlenir.
     * @param semester Liste uzerinde hangi semester bilgilerini istedigimizle ilgili parametre.
     * @return Semester bilgisine gore liste icinde varsa True, yoksa False return edilir.
     */
    private boolean hasNextInSemester(int semester){
        boolean first = true;
        if(countSemester == 0){
            Course a = new Course();
            if(list[0].getClass() == a.getClass()){
                for(int i = 0; i < size(); i++){
                    if(((Course)list[i]).getSemester() == semester){
                        if(first){
                            head = i;
                            first = false;
                        }
                        tail = i;
                        countSemester++;
                    }
                }
            }
            else
                System.out.println(list.getClass().getTypeName() + " does not work with this method");
        }
        return countSemester != 0;
    }
    /**
     * Semester degerine gore liste uzerinde bulunan o semestere sahip olan veriler uzerinde 
     * ilerlemek ve verileri return etmek icin bu metod kullanilir.
     * Ayrica semester degerine gore son bulunan semester dan sonra bu metod cagrildigi taktirde 
     * ilk bulunan semester verisine gider. Circular bir yapi icermektedir.
     * @param semester Liste uzerinde hangi semester bilgilerini istedigimizle ilgili parametre.
     * @return Semester bilgisine gore liste icinde varsa o semester degerine sahip veri return edilir.
     * @throws NoSuchElementException Listede semester degerine ait eleman yoksa exception firlatilir.
     */
    @Override
    public E nextInSemester(int semester) throws NoSuchElementException{
        E ret = null;
        if(countSemester == 0){
            boolean hasSemester = hasNextInSemester(semester);
            if(!hasSemester)
                throw new NoSuchElementException("No element found");
        }
        while(((Course)list[head]).getSemester() != semester)
            head++;
        
        if(head <= tail){
            ret = list[head];
            countSemester--;
        }
        head++;
        
        return ret;
    }
}
