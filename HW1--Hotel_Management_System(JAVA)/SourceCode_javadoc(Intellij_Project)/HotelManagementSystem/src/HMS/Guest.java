
package HMS;

import java.io.IOException;
import static java.lang.System.exit;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * HotelOperation inherit edilen Guest classi, 
 * bir misafirin neleri yapabilecegini gosteren yetkileri kisitli classtir.
 * @author Yunus CEVIK
 */
public class Guest extends HotelOperation{
    private static final String OPERANT_PERSON = "GUEST";
    
    public Guest() {}
    
    /**
     * Misafirin hangi islemleri yaptiracagini belirten metoddur.
     * Secilen degerlere gore yanlis bir deger secildigi taktirde kullanicidan tekrar input girilmesini bekler.
     *
     * @return Resepsiyonist ya da Misafirin sectigi islemlere gore integer deger return eder.
     * For example: Cancel Reservation: Press'1' = return 1;
     */
    @Override
    public int Operations(){
        Scanner guestInput = HotelOperation.scanner;
        int input = 0;
        do{
            try{
                System.out.println(String.format("\n\tOPERATIONS OF GUEST\nBooking : Press '1'\nCancel Booking : Press '2'\nExit : Press '-1'"));
                input = guestInput.nextInt();
                if(input != 1 && input != 2 && input != -1)
                    System.out.println("You have entered wrong number!\n");
                else if (input == -1){
                    System.out.println("Hotel management Sytem Shut Down\n");
                    exit(0);
                }
            }
            catch(InputMismatchException e){
                System.out.println("\nPlease enter 1-3 and -1 integer values");
                guestInput.next();
            }
        }while ( input != 1 && input != 2);
        
        return input;
    }

    
    /**
     * Main de secilen kisiler sonucu hangi sinifin neleri calistiracagini belirleyen metoddur.
     */
    @Override
    public void run() {
        try {
            super.roomsReadFile(ROOMSFILENAME);
            super.GuestsReadFile(GUESTSFILENAME);
            System.out.println(super.printRoom()+"\n");
            int operation = 0;
            int back = 0;
            String turnOver = "";
            do{
                operation = Operations();
                super.setrCapacity(super.queryRoomCapacity());
                System.out.println(super.printRoom(super.getrCapacity()));
                switch(operation){
                    case 1:
                        System.out.println("\n\tReservation");
                        back = super.bookRoom('R');
                        turnOver = "Booking";
                        System.out.println("A reservation was done.\n");
                        if(back != -2)
                            super.guestsWriteFile(GUESTSFILENAME);
                        break;
                    case 2:
                        back = super.cancelBookRoom(OPERANT_PERSON);
                        turnOver = "Cancel Rezervation";
                        break;
                    default:
                        break;
                }
            }while(back == -2);
            super.roomsWriteFile(ROOMSFILENAME);
            if(back == 1 || back == 2){
                super.registerWriteFile(REGISTERFILENAME,OPERANT_PERSON, turnOver, new Date());
            }
            System.out.println(super.printRoom());
        } catch (IOException e) {
            System.err.println("Error: "+e);
        }
    }

}
