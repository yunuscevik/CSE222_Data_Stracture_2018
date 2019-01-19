package HMS;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Receptionist classi icinde bulunan bazi metodlarin testini icerir.
 */
class ReceptionistTest {

    /**
     * Resepsiyonistin yapacagi islemlerin secilecegi metodu test eder.
     */
    @Test
    void operations() {
        HotelOperation.scanner = new Scanner("1 2 3 4 5 -1");
        Receptionist res = new Receptionist();
        assertEquals(1,res.Operations());
        assertEquals(2,res.Operations());
        assertEquals(3,res.Operations());
        assertEquals(4,res.Operations());
        assertEquals(5,res.Operations());

    }

    /**
     * Check-in ya da Check-out islemlerinin secilip bu metodlarin testinin yapildigi metoddur.
     * @throws IOException Yanlis degerlerin girilmesi sonucu firlatilan exceptiondir.
     */
    @Test
    void checkINorOut() throws IOException {
        Receptionist res = new Receptionist();
        HotelOperation.scanner = new Scanner("y 1 101 -2 -1");
        assertEquals(0,res.checkINorOut("CHECK IN"));
        HotelOperation.scanner = new Scanner("y 1 101 -2 -1");
        assertEquals(0,res.checkINorOut("CHECK OUT"));

    }
}