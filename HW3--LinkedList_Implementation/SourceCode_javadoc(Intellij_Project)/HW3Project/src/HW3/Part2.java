
package HW3;

import com.sun.istack.internal.NotNull;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * LinkedList ten extends edilmis bu generic class ile LinkedList metodlari ile birlikte 
 * sonradan implement edilen enable, disable ve showDisable metodlari kullanilir.
 * @author Yunus CEVIK
 * @param <E> Generic olarak belirtilir. Ancak hangi tipte verilirse o tipe donusur.
 */
public class Part2<E> extends LinkedList<ClassHasIndexAndObject>{
    private final LinkedList<E> disableList;

    /**
     * Default Constructor = Disable edilen verilerin tutulacagi LinkedList objesi olusturulur.
     */
    public Part2(){ disableList = new LinkedList<>(); }
    /**
     * Tek parametre alan Constructor = LinkedList parametresi alir ve LinkedList ten extends edilen bu class a aktarilir.
     * @param link LinkedList parametresi.
     */
    public Part2(@NotNull LinkedList<E> link){
        disableList = new LinkedList<>();
        Iterator iter = link.iterator();
        int i = 0;
        while (iter.hasNext()) {
            ClassHasIndexAndObject list = new ClassHasIndexAndObject();
            list.setIndex(i);
            list.setObject(iter.next());
            this.add(list);
            i++;
        }
    }

    /**
     *  Override edilmis add metodunda ClassHasIndexAndObject classi icinde bulunan index degerlerine gore
     *  siralama uzerinde degisiklik yapip tekrar ekleme islemi gercekleşmektedir.
     * @param other ClassHasIndexAndObject tipinde alinan parametre
     * @return Ekleme islemi basarili ise True, degil ise False dondurur.
     */
   @Override
    public boolean add(ClassHasIndexAndObject other) {
        boolean ret = false;
        boolean flag = false;
        if(other.getIndex() < this.size()) {
            for (int i = 0; i < this.size(); i++) {
                if (i == other.getIndex()) {
                    this.add(other.getIndex(), other);
                    ret = true;
                    flag = true;
                    i++;
                }
                if (flag == true) {
                    this.get(i).setIndex(this.get(i).getIndex() + 1);
                }
            }
        }
        else if(other.getIndex() > this.size())
            System.out.println("Adding failed. The node with this index on the list was not found.");
        else
            ret = super.add(other);
        return ret;
    }

    /**
     * Disable edilmis olan veriyi index numarasina gore enable etme isine yarayan metoddur.
     * Ayrica enable edilmis olan veri disable edilmeden once olan konumuna tekrardan gecer.
     * @param index Enable edilecek verinin index degeri.
     */
    public void enable(int index){
        int count = 0;
        boolean flag = false;
        ClassHasIndexAndObject enabled;
        if(disableList.isEmpty())
            System.out.println("No courses to enable");
        else{
            Iterator<E> iter = disableList.iterator();
            while(iter.hasNext() && flag == false){
                enabled = (ClassHasIndexAndObject) iter.next();
                if(enabled.getIndex() == index){                 
                    this.add(index - count, enabled);
                    flag = disableList.remove(enabled);
                }
                else if(enabled.getIndex() < index)
                    count++;
            }
            if(flag == false)
                System.out.println("The course that have "+ index +". index can not be enabled."
                        + " Because it has not been disabled or has been already enabled.");
        }
    }
    /**
     * Index numarasi belirtilmis listeden ders disable edilerek cikarilir ve baska bir liseye konulur.
     * @param index Disable edilecek verinin index degeri.
     */
    public void disable(int index){
        boolean find = false;
        ClassHasIndexAndObject disableL = new ClassHasIndexAndObject();
        for(int i = 0; i < this.size() && find == false; i++){
            if(i == index){
                disableL.setIndex(((ClassHasIndexAndObject) this.get(index)).getIndex());
                disableL.setObject(((ClassHasIndexAndObject) this.get(index)).getObject());
                disableList.add((E) disableL);
                this.remove(index);
                find = true;
            }
        }
        if(find == false)
            System.out.println("The course that have "+ index +". index can not be enabled.");
    }
    
    /**
     * Disable edilmis verileri ekran ciktisi olarak gosterir.
     */
    public void showDisable(){
        String returnString = "";
        Iterator<E> iter = disableList.iterator();
        while(iter.hasNext()){
            ClassHasIndexAndObject tempItem = (ClassHasIndexAndObject) iter.next();
            returnString += String.format("%d ->\t%s",tempItem.getIndex(),tempItem.getObject())+"\n"; 
        }
        if(returnString.isEmpty())
            System.out.println("Disable courses are not available.");
        else
            System.out.println(returnString);
    }


    /**
     * Liste icinde bulunan tum verileri String olarak return eder.
     * @return Liste icinde bulunan tum verileri String olarak return eder.
     */
    @Override
    public String toString() {
        String returnString = "";
        int i = 0;
        Iterator<E> iter = (Iterator<E>) this.iterator();
        while(iter.hasNext()){
            ClassHasIndexAndObject tempItem = (ClassHasIndexAndObject) iter.next();
            returnString += String.format("%d ->\t%s",i,tempItem.getObject()+"\n");
            i++;
        }
        return returnString;
    }
  
}
