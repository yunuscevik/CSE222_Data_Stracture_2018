package HMS;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Hotel Management Systemin tum metod ve durumlarini test eden bir classtir.
 */
class MainTest {

    /**
     * Bir dosyadan alinan input degerleriyle programi test eder.
     * @throws FileNotFoundException Dosya bulunmadiginda firlatilan hatadir.
     */
    @Test
    void main() throws FileNotFoundException {
        System.out.println("MAINTEST\n");
        String[] args = null;
        final InputStream original = System.in;
        final FileInputStream fips = new FileInputStream("mainTest.txt");
        System.setIn(fips);
        Main.main(args);
        System.setIn(original);
    }
}