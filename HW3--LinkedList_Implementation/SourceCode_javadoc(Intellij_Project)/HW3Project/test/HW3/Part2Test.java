package HW3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Part2 class inin bir objesi olusturulur.
 * Olusturulan bu obje ile enable, disable ve showDisable metodlari test edilir.
 */
class Part2Test {
    Part2<String> p2;
    public Part2Test(){
        p2 = new Part2<>();
        p2.add(new ClassHasIndexAndObject(0,"Muhittin"));
        p2.add(new ClassHasIndexAndObject(1,"Ali Lukunku"));
        p2.add(new ClassHasIndexAndObject(2,"Ayten"));
        p2.add(new ClassHasIndexAndObject(3,"Pakize"));
        p2.add(new ClassHasIndexAndObject(4,"Akaminko"));
        p2.add(new ClassHasIndexAndObject(5,"Alex De Souza"));
    }

    /**
     * LinkedList icindeki bilgilerin ekrana ciktisi saglanir.
     */
    void print(){

        System.out.println("\nTest_List");
        for(int i = 0; i < p2.size(); i++)
            System.out.println(i + " - " + p2.get(i).getObject());
    }


    /**
     * Liste uzerinde index degerine gore disable edilen verinin listeden cikarilarak
     * disable olup olmadigini test etmeye yarayan unitTest metodudur.
     */
    @Test
    void disable() {
        System.out.println("-----------------------------------------");
        System.out.println("\nDISABLE_TEST");
        print();
        System.out.println("\ndisable(2)");
        p2.disable(2);
        System.out.println("disable(2)");
        p2.disable(2);
        assertEquals(4,p2.size()); // assertEquals ile size lari test ettim

        print();
        System.out.println("-----------------------------------------\n");
    }

    /**
     * Liste uzerinde index degerine gore disable edilmis verilerin bir baska disable edilmis verileri tutan
     * listeye gecip gecmedigini test etmeye yarayan unitTest metodudur.
     */
    @Test
    void showDisable() {
        System.out.println("-----------------------------------------");
        System.out.println("\nSHOWDISABLE_TEST");
        print();
        System.out.println("\ndisable(1)");
        p2.disable(1);
        assertEquals(5,p2.size());  // assertEquals ile size lari test ettim
        System.out.println("\n ------ Disable are those (showDisable) ------\n");
        p2.showDisable();

        print();
        System.out.println("-----------------------------------------\n");
    }

    /**
     * Liste uzerinden disable edilmis olan bir verinin enable edilmesi sonucu,
     * disable edilmislerin bulundugu listeden ana listede eski konumuna getirmesini test eden unitTest metodudur.
     */
    @Test
    void enable() {
        System.out.println("-----------------------------------------");
        System.out.println("\nENABLE_TEST");
        print();
        System.out.println("\ndisable(2)");
        p2.disable(2);
        System.out.println("disable(2)");
        p2.disable(2);
        //assertEquals(4,p2.size());  // assertEquals ile size lari test ettim
        System.out.println("\n ------ Disable are those (showDisable) ------\n");
        p2.showDisable();

        print();

        System.out.println("\nenable(2)");
        p2.enable(2);
        //assertEquals(5,p2.size());  // assertEquals ile size lari test ettim
        print();

        System.out.println("\n ------ Disable are those (showDisable) ------\n");
        p2.showDisable();

        System.out.println("\nenable(3)");
        p2.enable(3);
        //assertEquals(6,p2.size());  // assertEquals ile size lari test ettim
        print();

        System.out.println("\n ------ Disable are those (showDisable) ------\n");
        p2.showDisable();

        System.out.println("-----------------------------------------\n");
    }
}