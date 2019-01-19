/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HMS;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * HotelOperation inherit edilen Receptionist classi, 
 * bir resepsiyonistin neleri yapabilecegini gosteren tum yetkilere sahip classtir.
 * @author Yunus CEVIK
 */
public class Receptionist extends HotelOperation{
    private static final String OPERANT_PERSON = "RECEPTIONIST";
    private static final String NAME = "Yunus";
    private static final String PASSWORD = "1234";
    public Receptionist() {}
    
    /**
     * Resepsiyonistin login olmasi icin istenen Kullanici adi ve password bilgilerini isteyen metoddur.
     * Suanlik tek bir resepsiyonist oldugundan test icin su sekilde kontrol edilmektedir.
     * FOR TESTING : User Name: = 'Yunus'
     * FOR TESTING : Password: = '1234'
     */
    private void receptionistLogIn(){
        Scanner receptionistInput = HotelOperation.scanner;
        String receptionistName = "";
        String receptionistPassword = "";
        System.out.println("\n\tRECEPTIONIST LOG IN");
        System.out.println("(FOR TESTING : User Name: = 'Yunus')");
        System.out.println("(FOR TESTING : Password: = '1234')\n");
        do{
            System.out.printf("User Name: ");
            receptionistName = receptionistInput.next();
            System.out.printf("Password: ");
            receptionistPassword = receptionistInput.next();
            if(!(receptionistName.equals(NAME) && receptionistPassword.equals(PASSWORD)))
                System.out.println("The user name or password is incorrect. Please enter again!\n");
        }while(!(receptionistName.equals(NAME) && receptionistPassword.equals(PASSWORD)));
    }
    
    /**
     * Resepsiyonist hangi islemleri yaptiracagini belirten metoddur.
     * Secilen degerlere gore yanlis bir deger secildigi taktirde kullanicidan tekrar input girilmesini bekler.
     *
     * @return Resepsiyonist ya da Misafirin sectigi islemlere gore integer deger return eder.
     * For example: Cancel Reservation: Press'1' = return 1;
     */
    @Override
    public int Operations(){
        Scanner receptionistInput = HotelOperation.scanner;
        int input = 0;
        do{
            try{
                System.out.println(String.format("\n\tOPERATIONS OF RECEPTIONIST\nBooking : Press '1'\nCancel Booking : Press '2'\nCheck In : Press '3'\nCheck Out : Press '4'\nAll Records : Press '5'\nExit : Press '-1'"));
                input = receptionistInput.nextInt();
                if(!(input > 0 && input <=5 ) && input != -1 && input != -2)
                    System.out.println("You have entered wrong number!\n");
                else if (input == -1){
                    System.out.println("Hotel management Sytem Shut Down\n");
                    exit(0);
                }
            }
            catch(InputMismatchException e){
                System.out.println("\nPlease enter 1-4 and -1 integer values");
                receptionistInput.next();
            }
        }while (!(input > 0 && input <=5));
        
        return input;
    }

    /**
     * Main de secilen kisiler sonucu hangi sinifin neleri calistiracagini belirleyen metoddur.
     */
    @Override
    public void run() {
        try {
            int operation = 0;
            int back = 0;
            String turnOver = "";
            super.roomsReadFile(ROOMSFILENAME);
            super.GuestsReadFile(GUESTSFILENAME);
            receptionistLogIn();
            System.out.println(super.printRoom()+"\n");
            do{
                operation = Operations();
                if(operation != 5){
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
                            break;
                        case 3:
                            
                            back = checkINorOut("CHECK IN");
                            turnOver = "Check IN";
                            if(back != -2)
                                super.guestsWriteFile(GUESTSFILENAME);
                            break;
                        case 4:
                            back = checkINorOut("CHECK OUT");
                            break;
                        
                        default:
                            break;
                    }
                }
                else
                    registerReadFile(REGISTERFILENAME);
            }while(back == -2);
            if(operation != 5){
                super.roomsWriteFile(ROOMSFILENAME);
                if(back == 1 || back == 2){
                    super.registerWriteFile(REGISTERFILENAME, OPERANT_PERSON, turnOver, new Date());
                }
                System.out.println(super.printRoom());
            }
            
        } catch (IOException e) {
            System.err.println("Error: "+e);
        }
        
    }
    
    /**
     * Check IN ya da Check OUT yapilmasi icin resepsiyonistin kullanabildigi metoddur. 
     * Yapilacak isleme gore metodlar cagrilir ve islem gerceklestirilir. 
     * @param inORout CHECK IN ya da CHECK OUT oldugunu belirten parametredir.
     * @return return -2: Check IN ya da Check OUT islemlerinden bir onceki menuye geri donmek icin kullanilir. Ya da return 0.
     * @throws IOException Metod icerisinde cagrilan dosyaya yazma islemi sonucu,
     * herhangi bir sorunla karsilasilabilir gibisinden firlatilan exceptiondir.
     */
    protected int checkINorOut(String inORout) throws IOException{
        System.out.println("\n\t"+inORout);
        boolean tF = true;
        int count = 0;
        boolean qB = true;
        if("CHECK IN".equals(inORout))
            qB = confirm("\nDid you make a reservation?");
        for(int i = 0; i < Room.getMAXROOM(); i++){
            if("CHECK IN".equals(inORout)){
                if(qB == true){
                    if(roomArr.get(i).getRoomStatus() == 'R' && roomArr.get(i).getRoomCapacity() == super.getrCapacity())
                        count++;
                }
                else{
                    if(roomArr.get(i).getRoomStatus() == 'E' && roomArr.get(i).getRoomCapacity() == super.getrCapacity())
                        count++;
                }

            }
            else if("CHECK OUT".equals(inORout)){
                if(roomArr.get(i).getRoomStatus() == 'F' && roomArr.get(i).getRoomCapacity() == super.getrCapacity())
                    count++;
            }
        }
        
        do{
            if("CHECK IN".equals(inORout)){
                if(qB == true){
                    System.out.printf( "%d rooms in the hotel have been booked.", count);
                    if(count > 0){
                        if(roomAndGuestIDInput('I') == -2)
                            return -2;
                        tF = checkIn();
                    }
                }
                else{
                    System.out.printf( "%d rooms in the hotel have not been booked and checked in.", count);
                    if(super.bookRoom('I') == -2)
                        return -2;
                }
                
            }
            else if(count > 0 && "CHECK OUT".equals(inORout)){
                System.out.printf("%d rooms in the hotel have been checked in.", count);
                if(roomAndGuestIDInput('O') == -2)
                    return -2;
                tF = checkOut();
            }
        }while(!tF);

        
        if("CHECK IN".equals(inORout) && count > 0)
            return 2;
        return 0;
    }
    /**
     * Check IN yapilip yapilamayacagini belirten metoddur. 
     * Eger rezervasyon yaptirdiysa oda numarası ve kimlik numarasiyla, rezerve yaptirmadiysa tum bilgileri ile check in yaptirabilir.
     * Check IN yapilmasi icin kullanicidan  confirm() metoduna Yes(Y/y) cevabini vermesi yeterlidir.
     * @return Kullanici Check IN yapabilir ve onay verdiginde return true - yapamaz ya da onay vermez ise return false.
     */
    private boolean checkIn() {
        boolean tF = true;
        if((roomArr.get(getRoomOrder()).getRoomStatus() == 'R' || roomArr.get(getRoomOrder()).getRoomStatus() == 'E') && (tF = confirm("\nDo you confirm ?") == true))
            roomArr.get(getRoomOrder()).setRoomStatus('F');
        System.out.println("\nCheck in was done.\n");
        return tF;
    }
    /**
     * Check OUT yapilip yapilamayacagini belirten metoddur.
     * Check OUT yapilmasi icin kullanicidan  confirm() metoduna Yes(Y/y) cevabini vermesi yeterlidir.
     * Ayrica Check OUT yapildigi taktirde oda bilgilerinin tutuldugu ArrayListten de Check OUT yapilan oda bilgileri sifirlanir.
     * @return Kullanici Check IN yapabilir ve onay verdiginde return true - yapamaz ya da onay vermez ise return false.
     * @throws IOException Metod icerisinde cagrilan dosyaya yazma islemi sonucu,
     * herhangi bir sorunla karsilasilabilir gibisinden firlatilan exceptiondir.
     */
    private boolean checkOut() throws IOException {
        boolean tF = true;
        if((roomArr.get(getRoomOrder()).getRoomStatus() == 'F') && (tF = confirm("\nDo you confirm ?"))){
            super.registerWriteFile(REGISTERFILENAME, "Receptionist","Check OUT", new Date());
            roomArr.get(getRoomOrder()).setRoomStatus('E');
            roomArr.get(getRoomOrder()).setGuestID(0);
            roomArr.get(getRoomOrder()).setGuestName("-");
            roomArr.get(getRoomOrder()).setGuestSurname("-");
            System.out.println("\nCheck out was done.\n");
        }
        return tF;
    }
    /**
     * Resepsiyonist yapmis oldugu tum bilgileri gormek icin 
     * "Register.csv" dosyasi icindeki bilgileri ekraninda gorebilmesi icin olusturulmus metoddur.
     * @param fileName Islem yapilmasi istenen dosya ismi
     */
    public void registerReadFile(String fileName){
        String line = "";
        String printRegister = "";
        File registerFile = new File(fileName);
        if(!registerFile.exists()){
            try {
                super.guestsWriteFile(GUESTSFILENAME);
            } catch (IOException ex) {
                System.err.println("yoook");
            }
        }
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            System.out.println();
            while((line = br.readLine()) != null){
                printRegister += (line + "\n");
            }
            System.out.println(printRegister);
        } catch (FileNotFoundException ex) {
            System.err.println("File not found or the file can not be opened !!!");
            System.out.println("Hotel management Sytem Shut Down\n");
            exit(0);
        } catch (IOException ex) {
            System.out.println("Hotel management Sytem Shut Down\n");
            exit(0);
        }
    }
}
