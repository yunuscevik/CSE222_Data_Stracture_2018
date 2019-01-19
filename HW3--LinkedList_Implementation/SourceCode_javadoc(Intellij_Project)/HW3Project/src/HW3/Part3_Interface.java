
package HW3;

import java.util.NoSuchElementException;

/**
 * Objesi uretilemeyen ancak Polimorfic cagrilarda kullanilan interface.
 * @author Yunus CEVIK
 * @param <E> Generic olarak belirtilir. Ancak hangi tipte verilirse o tipe donusur.
 */
public interface Part3_Interface<E> {
    /**
     * Listenin Size bilgisini verir.
     * @return Listenin Size bilgisini return eder.
     */
    public int size();
    /**
     * Listenin sonuna parametreden gelen veriyi ekleme islemi yapar.
     * @param value Parametre olarak gelen generic deger.
     * @return Add islemi yapildiginda True degeri donderir.
     */
    public boolean add(E value);
    /**
     * Liste uzerinde parametreden gelen index degeri ve veriyi index degerine gore ekleme islemi yapar.
     * Ayrica listede araya ekleme islemi yapildiginda kaydirmasi gerekirse, kaydirma isleminide gerceklestirir.
     * @param index Liste uzerinde hangi index e eklenmesini bildiren parametre.
     * @param value Parametre olarak gelen generic deger.
     */
    public void add(int index, E value);
    /**
     * Listenin son elemanini silmeye yarar.
     * @return Silinen elemani return eder.
     */
    public E remove();
    /**
     * Parametrede gelen degere gore liste uzerinde arama yapar ve listede varsa bu deger silinir.
     * @param value Silinecek deger parametrede belirtilir.
     * @return Silme islemi basariyla gerceklesmis ise True, gerceklesmemis ise False return eder.
     */
    public boolean remove(E value);
    /**
     * Parametrede gelen index degerine gore liste uzerinde arama yapar ve listede varsa o index e sahip deger silinir.
     * @param index Silinecek degerin index i parametrede belirtilir.
     * @return Silinen elemani return eder.
     */
    public E remove(int index);
    /**
     * Next isleminin yapilip yapilmayacagini check eder.
     * @return Next isleminin yapilip yapilmayacagini check eder ve ona gore True ya da False return eder.
     */
    public boolean hasNext();
    /**
     * Liste uzerinde next metodu listedeki verilere sirayla ulasir ve ilerleme gerceklestirilir.
     * @return Liste uzerinde siradaki veri dondurulur
     * @throws NoSuchElementException Listede eleman yoksa exception firlatilir.
     */
    public E next() throws NoSuchElementException;
}
