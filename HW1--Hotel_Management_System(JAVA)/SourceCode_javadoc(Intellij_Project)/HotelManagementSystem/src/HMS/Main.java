
package HMS;





import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * main(String[] args) metodunu bulunduran ve programi tekikleyen classtir.
 * @author Yunus CEVIK
 */
public class Main {

    /**
     * Olusturulan Receptionist ve Guest objelerinin HotelManagementSystem interface ine Polimorfic cagrilar ile programin run edilmesi.
     * Ayrica programi kullanan kisinin kim oldugu logIn() metodu cagrilarak belirlenir.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int login = 0;
        boolean repeat = true;
        HotelManagementSystem recepsionist = new Receptionist();
        HotelManagementSystem guest = new Guest();
        System.out.println("\t\t***Welcome to Hotel Management System***");
        Scanner logIN = new Scanner(System.in);
        while(repeat == true){
            login = logIn(logIN);
            HotelOperation.scanner = logIN;
            if(login == 1)
                recepsionist.run();
            else if(login == 2)
                guest.run();
            else if(login == -1)
                repeat = false;

        }
                    
        
    }
    
    /**
     * Sisteme giris yapan kisinin kim oldugunu belirlemek icin olusturulmus yardimci metoddur.
     * @return return 1: Resepsiyonist - return 2: Misafir - return -1: Sistemden cikma
     * @param login main icerisinde olusturulan tek bir scanner objesinden referans edilir.
     */
    private static int logIn(Scanner login){
        
        int ret = 0;
        do{
            try{
                System.out.println(String.format("\tLOGIN\nReceptionist : Press '1'\nGuest : Press '2'\nExit : Press '-1'"));
                ret = login.nextInt();
                if(ret != 1 && ret != 2 && ret != -1)
                    System.out.println("You have entered wrong number!\n");
                else if (ret == -1){
                    System.out.println("Hotel management System Shut Down\n");
                    return -1;
                }
            }
            catch(InputMismatchException e){
                System.out.println("\nPlease enter 1,2,-1 integer values");
                login.next(); /* Girilmesi gereken deger yanlis ve exception firlatmis ise,
                kullaniciya tekrardan sormak icin buffer a yanlis deger alinir.*/
            }
        }while ( ret != 1 && ret != 2 );

        return ret;
    }
     
}
