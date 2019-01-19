
package HW3;

import java.util.NoSuchElementException;

/**
 * Objesi uretilemeyen ancak baska classlara miras ile aktardigi metodlarin kullanimi saglayan abstract class.
 * Bu abstract class Part3_Interface interface inden implement edilmedir.
 * @author Yunus CEVIK
 * @param <E> Generic olarak belirtilir. Ancak hangi tipte verilirse o tipe donusur.
 */
public abstract class Part3_Abstract<E> implements Part3_Interface<E> {
    /**
     * Listenin Size bilgisini verir.
     * @return Listenin Size bilgisini return eder.
     */
    @Override
    public abstract int size();
    /**
     * Listenin sonuna parametreden gelen veriyi ekleme islemi yapar.
     * @param value Parametre olarak gelen generic deger.
     * @return Add islemi yapildiginda True degeri donderir.
     */
    @Override
    public abstract boolean add(E value);
    /**
     * Liste uzerinde parametreden gelen index degeri ve veriyi index degerine gore ekleme islemi yapar.
     * Ayrica listede araya ekleme islemi yapildiginda kaydirmasi gerekirse, kaydirma isleminide gerceklestirir.
     * @param index Liste uzerinde hangi index e eklenmesini bildiren parametre.
     * @param value Parametre olarak gelen generic deger.
     */
    @Override
    public abstract void add(int index, E value);
    /**
     * Listenin son elemanini silmeye yarar.
     * @return Silinen elemani return eder.
     */
    @Override
    public abstract E remove();
    /**
     * Parametrede gelen degere gore liste uzerinde arama yapar ve listede varsa bu deger silinir.
     * @param value Silinecek deger parametrede belirtilir.
     * @return Silme islemi basariyla gerceklesmis ise True, gerceklesmemis ise False return eder.
     */
    @Override
    public abstract boolean remove(E value);
    /**
     * Parametrede gelen index degerine gore liste uzerinde arama yapar ve listede varsa o index e sahip deger silinir.
     * @param index Silinecek degerin index i parametrede belirtilir.
     * @return Silinen elemani return eder.
     */
    @Override
    public abstract E remove(int index);
    /**
     * Next isleminin yapilip yapilmayacagini check eder.
     * @return Next isleminin yapilip yapilmayacagini check eder ve ona gore True ya da False return eder.
     */
    @Override
    public abstract boolean hasNext();
    /**
     * Liste uzerinde next metodu listedeki verilere sirayla ulasir ve ilerleme gerceklestirilir.
     * @return Liste uzerinde siradaki veri dondurulur
     * @throws NoSuchElementException Listede eleman yoksa exception firlatilir.
     */
    @Override
    public abstract E next() throws NoSuchElementException;
    /**
     * Semester degerine gore liste uzerinde bulunan o semestere sahip olan veriler uzerinde 
     * ilerlemek ve verileri return etmek icin bu metod kullanilir.
     * Ayrica semester degerine gore son bulunan semester dan sonra bu metod cagrildigi taktirde 
     * ilk bulunan semester verisine gider. Circular bir yapi icermektedir.
     * @param semester Liste uzerinde hangi semester bilgilerini istedigimizle ilgili parametre.
     * @return Semester bilgisine gore liste icinde varsa o semester degerine sahip veri return edilir.
     * @throws NoSuchElementException Listede semester degerine ait eleman yoksa exception firlatilir.
     */
    public abstract E nextInSemester(int semester) throws NoSuchElementException;
}
