package HMS;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest {
    /**
     * Misafirin yapacagi islemlerin secilecegi metodu test eder.
     */
    @Test
    void operations() {
        HotelOperation.scanner = new Scanner("1 2 -1");
        Guest guest = new Guest();
        assertEquals(1,guest.Operations());
        assertEquals(2,guest.Operations());

    }
}