package HMS;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract class olan HotelOperation classi icindeki bazi metodlarin testlerini icerir.
 */
class HotelOperationTest {
    HotelOperation res = new Receptionist();
    @Test
    void queryRoomCapacity() {
        HotelOperation.scanner = new Scanner("1 2 3 4 5");
        assertEquals(1,res.queryRoomCapacity());
        assertEquals(2,res.queryRoomCapacity());
        assertEquals(3,res.queryRoomCapacity());
        assertEquals(4,res.queryRoomCapacity());
        assertEquals(5,res.queryRoomCapacity());
    }

    /**
     * Resepsiyonist ya da misafirin otele rezervasyon yapmasinin testini saglayan test metodudur.
     */
    @Test
    void bookRoom() {
        HotelOperation.scanner = new Scanner("1 110 5555 Hakkı Bulut y -1");
        res.setrCapacity(res.queryRoomCapacity());
        assertEquals(1,res.bookRoom('R'));
    }
    /**
     * Resepsiyonist ya da misafirin otele rezervasyon yapmasinin testini saglayan test metodudur.
     * @throws IOException Metod icerisinde cagrilan dosyaya yazma islemi sonucu,
     * herhangi bir sorunla karsilasilabilir gibisinden firlatilan exceptiondir.
     */
    @Test
    void cancelBookRoom() throws IOException {
        bookRoom();
        HotelOperation.scanner = new Scanner("110 5555 y -1");
        assertEquals(0,res.cancelBookRoom("GUEST"));
    }

    /**
     * Odalarin bos ya da dolu oldugunu test edip status degerlerine gore farkli islemler saglayan metoddur.
     */
    @Test
    void checkRoomID() {
        res.setrCapacity(1);
        assertTrue(res.checkRoomID(110,'R'));
        assertFalse(res.checkRoomID(110,'I'));
        assertFalse(res.checkRoomID(110,'O'));

    }

    /**
     * Sistemde giris yapmis bir kisinin onceden giris yapip yapmadigini kontrol eden test metedodur.
     */
    @Test
    void checkGuestID() {
        bookRoom();
        res.setrCapacity(1);
        assertTrue(res.checkGuestID(5555,110,'R'));
        assertTrue(res.checkGuestID(5555,110,'I'));
        assertFalse(res.checkGuestID(5555,100,'O'));

    }


    /**
     *  Islemler sonunda onay verilip verilmedigini kontrol eden test metoddur.
     */
    @Test
    void confirm() {
        HotelOperation.scanner = new Scanner("Y y N n -1");
        assertTrue(res.confirm("\nDo you confirm ?"));
        assertTrue(res.confirm("\nDo you confirm ?"));
        assertFalse(res.confirm("\nDo you confirm ?"));
        assertFalse(res.confirm("\nDo you confirm ?"));
    }
}