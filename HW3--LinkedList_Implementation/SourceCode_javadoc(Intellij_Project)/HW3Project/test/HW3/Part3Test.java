package HW3;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Part3 class inin objeleri olusturulur.
 * Olusturulan bu obje ile size, add, remove, next ve nextInSemester metodlari test edilir.
 */
class Part3Test {
    Part3<String> p3String;
    Part3<Course> p3Course;
    Part1 p1;
    String titles = "Semester Course Code\t\tCourse Title\t\tECTS C.\tGTU C.\tH+T+L";
    public Part3Test(){
        p1 = new Part1();
        p3String = new Part3<>();
        p3Course = new Part3<>();
    }

    /**
     * String tipindeki LinkedList icindeki bilgilerin ekrana basmasi saglanir.
     */
    void printString(){
        System.out.println("\nTest_StringList");
        for(int i = 0; i < p3String.size(); i++){
            System.out.println(i+ " - "+p3String.getList()[i]);
        }
    }

    /**
     * Course tipindeki LinkedList icindeki bilgilerin ekrana basmasi saglanir.
     */
    void printCourse(){
        System.out.println("\nTest_CourseList");
        System.out.println(titles);
        for(int i = 0; i < p3Course.size(); i++){
            System.out.println(p3Course.getList()[i]);
        }
    }

    /**
     * String tipindeki listenin icindeki elemanlarin sayisini verir.
     */
    void size() {
        System.out.println("\n\t---------- size() ----------");
        System.out.println("size => " + p3String.size());
    }

    /**
     * Object tipinde veriyi parameterik alarak, listeye eklenip eklenmedigini test eden unitTest metodudur.
     */
    @Test
    void add() {
        System.out.println("-----------------------------------------");
        System.out.println("\nadd(E e) _TEST\n");
        p3String.add("Muhittin");
        p3String.add("Ali Lukunku");
        p3String.add("Ayten");
        p3String.add("Pakize");
        p3String.add("Akaminko");
        p3String.add("Alex De Souza");
        printString();
        assertEquals(6,p3String.size());
        size();
        System.out.println("-----------------------------------------");
    }

    /**
     * Index ve Object tipinde veriyi parameterik alarak, liste uzerinde index degerinin gosterdigi yere
     * eklenip eklenmedigini test eden unitTest metodudur.
     */
    @Test
    void add1() {
        System.out.println("-----------------------------------------");
        System.out.println("\nadd(index, E e) _TEST\n");
        p3String.add("Muhittin");
        p3String.add("Ali Lukunku");
        p3String.add("Ayten");
        p3String.add("Pakize");
        p3String.add("Akaminko");
        p3String.add("Alex De Souza");
        printString();
        System.out.println("\nadd(3, Hayrullah)");
        p3String.add(3,"Hayrullah");
        printString();
        assertEquals(7,p3String.size());
        size();
        System.out.println("-----------------------------------------");

    }

    /**
     * Listenin son elemanini silip silmedigini test eden unitTest metodudur.
     */
    @Test
    void remove() {
        System.out.println("-----------------------------------------");
        System.out.println("\nremove() _TEST\n");
        p3String.add("Muhittin");
        p3String.add("Ali Lukunku");
        p3String.add("Ayten");
        p3String.add("Pakize");
        p3String.add("Akaminko");
        p3String.add("Alex De Souza");
        printString();
        System.out.println("\nremove() => " + p3String.remove());
        printString();
        assertEquals(5,p3String.size());
        size();
        System.out.println("-----------------------------------------");
    }

    /**
     * Object tipinde veriyi parameterik alarak, listeden silip silmedigini test eden unitTest metodudur.
     */
    @Test
    void remove1() {
        System.out.println("-----------------------------------------");
        System.out.println("\nremove(E value) _TEST\n");
        p3String.add("Muhittin");
        p3String.add("Ali Lukunku");
        p3String.add("Ayten");
        p3String.add("Pakize");
        p3String.add("Akaminko");
        p3String.add("Alex De Souza");
        printString();
        System.out.println("\nremove(\"Akaminko\") => " + p3String.remove("Akaminko"));
        printString();
        assertEquals(5,p3String.size());
        size();
        System.out.println("-----------------------------------------");
    }

    /**
     * Index degerini parameterik alarak, listeden silip silmedigini test eden unitTest metodudur.
     */
    @Test
    void remove2() {
        System.out.println("-----------------------------------------");
        System.out.println("\nremove(int index) _TEST\n");
        p3String.add("Muhittin");
        p3String.add("Ali Lukunku");
        p3String.add("Ayten");
        p3String.add("Pakize");
        p3String.add("Akaminko");
        p3String.add("Alex De Souza");
        printString();
        System.out.println("\nremove(3) => " + p3String.remove(3));
        printString();
        assertEquals(5,p3String.size());
        size();
        System.out.println("-----------------------------------------");
    }

    /**
     * Liste uzerinde next metodu cagrildikca ilerleyen ve liste icindeki verileri
     * dondurup dondurmedigini test eden unitTest metodudur.
     */
    @Test
    void next() {
        System.out.println("-----------------------------------------");
        System.out.println("\nnext() _TEST\n");
        p3String.add("Muhittin");
        p3String.add("Ali Lukunku");
        p3String.add("Ayten");
        p3String.add("Pakize");
        p3String.add("Akaminko");
        p3String.add("Alex De Souza");
        printString();
        try {
            System.out.print("\nnext() => ");
            System.out.println(p3String.next());
            System.out.print("\nnext() => ");
            System.out.println(p3String.next());
            assertEquals("Ayten",p3String.next());
        }catch (NoSuchElementException e){
            System.out.println(e);
        }
        size();
        System.out.println("-----------------------------------------");
    }
    /**
     * Course Listesi uzerinde nextInSemester metodu cagrildikca aldigi semester parametresine gore
     * liste icindeki verilerden sadece o semester degerine gore ileryen ve liste icindeki semester bilgisine gore verileri
     * dondurup dondurmedigini ve circular bir yapida olup olmadigini test eden unitTest metodudur.
     */
    @Test
    void nextInSemester() {
        System.out.println("-----------------------------------------");
        System.out.println("\t---------- add(Courses) ----------");
        p3Course.add(p1.getList().get(15));
        p3Course.add(p1.getList().get(20));
        p3Course.add(p1.getList().getLast());
        p3Course.add(p1.getList().get(30));
        p3Course.add(p1.getList().get(40));
        p3Course.add(p1.getList().getFirst());
        p3Course.add(p1.getList().get(41));
        System.out.println("\t---------- add(index, Course) ----------");
        p3Course.add(3, (Course) p1.getList().getLast());

        printCourse();

        System.out.println("\n\t---------- nextInSemester(semester) semester=> 1 ----------");
        System.out.println(titles);
        System.out.println(p3Course.nextInSemester(1));
        System.out.println(p3Course.nextInSemester(1));
        System.out.println(p3Course.nextInSemester(1));
        System.out.println(p3Course.nextInSemester(1));
        System.out.println(p3Course.nextInSemester(1));
        System.out.println(p3Course.nextInSemester(1));
        System.out.println(p3Course.nextInSemester(1));
        System.out.println(p3Course.nextInSemester(1));
        System.out.println(p3Course.nextInSemester(1)+"\n");

        try {
            p3String.add("Alex De Souza");
            System.out.println(p3String.nextInSemester(1));
        }catch (NoSuchElementException e){
            System.out.println("Exception => " + e);
        }
        System.out.println("-----------------------------------------");
    }
}