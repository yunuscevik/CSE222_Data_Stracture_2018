
package HMS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Objesi uretilemeyen ancak baska classlara miras ile aktardigi metodlarin kullanimi saglayan abstract class.
 * Bu abstract class Hotel clasindan turetilme HotelManagementSystem interface inden implement edilmedir.
 * @author Yunus CEVIK
 */
public abstract class HotelOperation extends Hotel implements HotelManagementSystem{
    
    private int roomIDCount = 101;
    private final int roomNo = 101;
    private int guestOrder;
    private int roomOrder;
    private int rCapacity;
    protected static ArrayList<GuestInfo> guestArr;
    private Room room;
    protected static Scanner scanner;
    protected static final String GUESTSFILENAME = "Guests.csv";
    protected static final String ROOMSFILENAME = "Rooms.csv";
    protected static final String REGISTERFILENAME = "Register.csv";
    
    public int getrCapacity() { return rCapacity; }
    public void setrCapacity(int rCapacity) { this.rCapacity = rCapacity; }
    public int getRoomOrder() { return roomOrder; }
    public void setRoomOrder(int roomOrder) { this.roomOrder = roomOrder; }
    public int getGuestOrder() { return guestOrder; }
    public void setGuestOrder(int guestOrder) { this.guestOrder = guestOrder; }
    
    public HotelOperation() {
        roomArr = new ArrayList<>();
        guestArr = new ArrayList<>();
        guestOrder = -1;
        roomOrder = -1;
        int j = 2, k = 1;
        for(int i = 0; i < Room.getMAXROOM(); i++ ){
            room = new Room();
            if(i < 10*j){
                room.setRoomID(this.roomIDCount++);
                room.setRoomCapacity(k);
            }
            else{
                roomIDCount = roomNo + (50*j);
                j = j+2;
                k++;
                room.setRoomID(this.roomIDCount++);
                room.setRoomCapacity(k);
            }
            room.setRoomStatus('E');
            room.setGuestID(0);
            room.setGuestName("-");
            room.setGuestSurname("-");
            
            roomArr.add(room);
        }
    }
    
    /**
     *
     * Main de secilen kisiler sonucu hangi sinifin neleri calistiracagini belirleyen abstract metoddur.
     */
    @Override
    public abstract void run();
    
    /**
     * Resepsiyonist ya da Misafirin hangi islemleri yaptiracagini belirten abstract metoddur.
     * Secilen degerlere gore yanlis bir deger secildigi taktirde kullanicidan tekrar input girilmesini bekler.
     *
     * @return Resepsiyonist ya da Misafirin sectigi islemlere gore integer deger return eder.
     * For example: Cancel Reservation: Press'1' = return 1;
     */
    @Override
    public abstract int Operations();
    
    /**
     * Cesitli odalara sahip otelde kac kisilik odalarin secileceigini soran metoddur.
     * @return Secilen oda kapasitesi sonucu kac kisilik oldugunu integer reger olarak return eder.
     * For example: 1-5 kisik odalar var Room Capacity: 1 secilirse return 1. 
     * Yani sadece 1 kisilik odalar ekranda gorunur ve o odalardan secim yapilir.
     */
    @Override
    public int queryRoomCapacity() {
        Scanner scan = HotelOperation.scanner;
        int ret = 0;
        do{
            try{
                System.out.printf("\n1-5\nRoom Capacity: ");
                ret = scan.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("\nPlease enter integer value");
                scan.next();
            }
            if(!(ret > 0 && ret < 6))
                System.out.println("You must select capacity of room between 1 and 5 !");
        }while(!(ret > 0 && ret < 6));
        
        return ret;
    }
    
    /**
     * Resepsiyonist ya da Misafirin cagirmasi sonucu oda bilgilerinin girilerek o odayi rezerve yapma isini gerceklestiren metoddur.
     * Ayrica onceden giris yapmis olan Misafir var ise sadece kimlik numarasi ile misafirlerin kayitlarinin tutuldugu veri tabani taranir.
     * Daha sonra ayni kisi sisteme giris yapmis ise diger bilgilerini girmesine gerek olmadan odayi rezerve yapalir.
     * @param status Hangi metodun cagirdigini belirten parametredir.
     * @return return -2: Rezervasyon isleminden bir onceki menuye geri donmek icin kullanilir. 
     * return RoomID: Islem tamamlandiktan sonra baska bir metodun kullanilmasi icin retun edilen RoomID vardir.
     */
    @Override
    public int bookRoom(char status) {
        GuestInfo guestInfo = new GuestInfo();
        int guestID = 0, roomID = 0;
        boolean check = false;
        Scanner scan = HotelOperation.scanner;
        boolean save = true;
        System.out.println("\n\nInformations");
        do{
            if((roomID = roomAndGuestIDInput('R')) == -2)
                return -2;
            do{
                try{

                    System.out.printf("GuestID Number: ");
                    guestID = scan.nextInt();
                    if(guestID == -2)
                        return -2;
                    check = true;
                }
                catch(InputMismatchException e){
                    System.out.println("\nPlease enter integer value");
                    scan.next();
                }
            }while(check == false);
            if(checkGuestID(guestID,roomID,'R') == false){
                guestInfo.setGuestID(guestID);
                roomArr.get(getRoomOrder()).setGuestID(guestID);
                System.out.printf("Guest Name: ");
                guestInfo.setGuestName(scan.next().toUpperCase());
                roomArr.get(getRoomOrder()).setGuestName(guestInfo.getGuestName());
                System.out.printf("Guest Surname: ");
                guestInfo.setGuestSurname(scan.next().toUpperCase());
                roomArr.get(getRoomOrder()).setGuestSurname(guestInfo.getGuestSurname());
                if((save = confirm("\nDo you confirm ?")) == true){
                    if(status == 'R')
                        roomArr.get(getRoomOrder()).setRoomStatus('R');
                    else if(status == 'I')
                        roomArr.get(getRoomOrder()).setRoomStatus('F');
                    guestArr.add(guestInfo);
                }
            }
            else{
                roomArr.get(getRoomOrder()).setGuestID(guestID);
                System.out.printf("Guest Name: %s\n",guestArr.get(getGuestOrder()).getGuestName());
                roomArr.get(getRoomOrder()).setGuestName(guestArr.get(getGuestOrder()).getGuestName());
                System.out.printf("Guest Surname: %s\n",guestArr.get(getGuestOrder()).getGuestSurname());
                roomArr.get(getRoomOrder()).setGuestSurname(guestArr.get(getGuestOrder()).getGuestSurname());
                if((save = confirm("\nDo you confirm ?")) == true)
                    if(status == 'R')
                        roomArr.get(getRoomOrder()).setRoomStatus('R');
                    else if(status == 'I')
                        roomArr.get(getRoomOrder()).setRoomStatus('F');
            }
                    
        }while(save == false);

        return 1;
    }
    
    /**
     * Resepsiyonist ya da Misafirin cagirmasi sonucu oda bilgilerinin girilerek rezerve edilmis odayi iptal edip oda bilgilerini sifirlayan metoddur.
     * @param operantPerson Iptal isini yapan kisi
     * @return return -2: Rezervasyon isleminden bir onceki menuye geri donmek icin kullanilir. Ya da return 0.
     * @throws IOException Metod icerisinde cagrilan dosyaya yazma islemi sonucu,
     * herhangi bir sorunla karsilasilabilir gibisinden firlatilan exceptiondir.
     */
    @Override
    public int cancelBookRoom(String operantPerson) throws IOException {
        System.out.println("\n\tCANCEL RESERVETION");
        Scanner scan = HotelOperation.scanner;
        boolean tF = true;
        int count = 0;
        for(int i = 0; i < Room.getMAXROOM(); i++){
            if(roomArr.get(i).getRoomStatus() == 'R')
                count++;
        }
        if(count > 0){
            System.out.printf("%d rooms in the hotel have been booked.", count);
            do{
                if(roomAndGuestIDInput('X') == -2)
                    return -2;  
                if(roomArr.get(getRoomOrder()).getRoomStatus() == 'R' && (tF = confirm("\nDo you confirm ?"))){
                    registerWriteFile(REGISTERFILENAME, operantPerson, "Cancel Rezervation", new Date());
                    roomArr.get(getRoomOrder()).setRoomStatus('E');
                    roomArr.get(getRoomOrder()).setGuestID(0);
                    roomArr.get(getRoomOrder()).setGuestName("-");
                    roomArr.get(getRoomOrder()).setGuestSurname("-");
                    System.out.println("\nCancel Reservation was done.\n");
                }
            }while(!tF);
        }
        else
           System.out.printf("%d rooms in the hotel have been booked.", count);
        return 0;
    }
    /**
     * Parametrelere verilen degerler sonucu girilen oda numarasinin var olup olmadigini ve
     * kim tarafindan cagrildigini belirten status degeri ile revervasyon yapma yada iptal etme, check in ya da check out yapma islemlerinde
     * nasil odalari kontrol edecegini belirten metoddur.  Yani metodlar icinde cagrilmasi ile farkli yapida calisan bir metoddur.
     * @param id control edilecek oda numarasi
     * @param status Hangi metodun cagirdigini belirten parametredir.
     * @return Eger oda numarasi mevcut ve olumlu bir islem gerceklestirilmis ise "true", degil ise "false" return eder.
     */
    @Override
    public boolean  checkRoomID(int id, char status){
        boolean check = false;
        boolean idCheck = false;
        for(int i = 0; i < roomArr.size(); i++){
            if(roomArr.get(i).getRoomID() == id){
                idCheck = true;
                if(roomArr.get(i).getRoomCapacity() == getrCapacity()){
                    if(roomArr.get(i).getRoomStatus() == 'E' && status == 'R'){
                        setRoomOrder(i);
                        check = true;
                    }
                    else if(roomArr.get(i).getRoomStatus() == 'R' && (status == 'I' || status == 'X')){
                        setRoomOrder(i);
                        check = true;
                    }
                    else if(roomArr.get(i).getRoomStatus() == 'F' && status == 'O'){
                        setRoomOrder(i);
                        check = true;
                    }
                }
            }
        }
        if(idCheck == false){
            System.out.println("Such a room does not exist. Please choose another room.");
            System.out.println("Room Capacity: "+getrCapacity()+"\nPlease select between "+ ((getrCapacity()*100)+1) +" and "+ ((getrCapacity()*100)+20));
            return false;
        }
        else if(idCheck == true && check == false){
            if(status == 'R' && check == false)
                System.out.println("The room you have chosen is full. Please choose another room.");
            else if(status == 'I' && check == false)
                System.out.println("You can not check in this room. Because it is not reserved or already full.");
            else if(status == 'O' && check == false)
                System.out.println("You can not check out this room. Because the room was not checked in.");
            else if(status == 'X' && check == false)
                System.out.println("You can not cancel booking this room. Because the room was not booking.");
            
            System.out.println("Room Capacity: "+getrCapacity()+"\nPlease select between "+ ((getrCapacity()*100)+1) +" and "+ ((getrCapacity()*100)+20));
            return false;
        }

        return check;
    }
    
    /**
     * Cagiran metodlara gore farkli calisan bir metoddur. Alinan parametrelerde statuse gore islem gerceklestirir.
     * Eger status degeri 'R' ise rezervasyon yapilacaktir ve sadece misafir kimlik numarasina gore eskiden sisteme giris yapip yapmadigi kontrol edilir.
     * Eger status degeri 'R' haricinde ise diger metodlar cagirmis demektir ve odalar icerisinde misafir kimlik numarasi ile sorgulama yapilir.
     * Kisi kimlik numarasi oda icerisinde bulunan kimlik degeri ile eslesip eslesmedigi kontrol edilir.
     * @param guestID Misafirin kimlik numarasi
     * @param roomID Otelde bulunan oda numarasi
     * @param status Bu metodu hangi metodun ne amacla cagirdigini belirten parametre.
     * @return Eger misafir kisinin kimlik numarasi mevcut yada olumlu islemler gerceklestirilmis ise "true", degil ise "false" return eder.
     */
    @Override
    public boolean checkGuestID(int guestID,int roomID,char status){
        boolean check = false;
        
        if(guestArr.isEmpty())
            check = false;
        else{
            if(status == 'R'){
                for(int i = 0; i < guestArr.size(); i++){
                    if( guestArr.get(i).guestID == guestID){
                        setGuestOrder(i);
                        return true;
                    }
                }
            }
            else{
                for(int i = 0; i < Room.getMAXROOM(); i++){
                    if(roomArr.get(i).getRoomID() == roomID && roomArr.get(i).getGuestID() == guestID){
                        setGuestOrder(i);
                        check = true;
                    }
                }
                if(check == false)
                    System.out.println("The person that has id entered  is not finding in this room.");
            }
        }
        return check;
    }
    
    /**
     * Oda numaralari ve misafir kisilerin input girecegi metoddur. 
     * Ancak status durumu = 'R' rezervasyon durumunda sadece kullaniciya oda numarasi girdisi sunmaktadir.
     * @param status Bu metodu hangi metodun ne amacla cagirdigini belirten parametre.
     * @return return -2: Rezervasyon isleminden bir onceki menuye geri donmek icin kullanilir. 
     * return RoomID: Islem tamamlandiktan sonra baska bir metodun kullanilmasi icin retun edilen RoomID vardir.
     */
    @Override
    public int roomAndGuestIDInput(char status) {
        Scanner scan = HotelOperation.scanner;
        int roomID = -1, guestID = -1;
        do{
            System.out.println("\nPrevious Menu : Press '-2'");
            try{
                System.out.printf("RoomID: ");
                roomID = scan.nextInt();
                if(roomID == -2)
                    return -2;
            }
            catch(InputMismatchException e){
                System.out.println("\nPlease enter integer value");
                scan.next();
            }
        }while(checkRoomID(roomID, status) == false);
        if(status != 'R'){
            do{
                try{
                    System.out.printf("GuestID: ");
                    guestID = scan.nextInt();
                    if(guestID == -2)
                        return -2;
                }
                catch(InputMismatchException e){
                    System.out.println("\nPlease enter integer value");
                    scan.next();
                }
            }while(checkGuestID(guestID,roomID,status) == false);
        }
        return roomID;
    }
    
    /**
     * "Register.csv" dosyasinda tum yapilan islemlerin tutuldugu bu dosyaya yazma metodudur. 
     * Oda bilgileri ile birlikte girilen parametre degerlerini dosyaya yazma islemini saglar.
     * @param fileName Islem yapilmasi istenen dosya ismi
     * @param operantPerson Yapilmasi gereken islemi geceklestiren kisi. (Resepsiyonist ya da Guest)
     * @param turnOver Yapilmasi gereken islemin ne oldugunu belirten parametre.
     * @param date Yapilmasi gerek islemin ne zaman yapildigini belirten tarih.
     * @throws IOException Metod icerisinde cagrilan dosyaya yazma islemi sonucu,
     * herhangi bir sorunla karsilasilabilir gibisinden firlatilan exceptiondir.
     */
    @Override
    public void registerWriteFile(String fileName, String operantPerson, String turnOver, Date date) throws IOException {
        String camma = ";";
        File RegisterFile = new File(fileName);
        if(!RegisterFile.exists()){
            RegisterFile.createNewFile();
            FileWriter registerWriter = new FileWriter(RegisterFile,true);
            try (BufferedWriter bWriter = new BufferedWriter(registerWriter)) {
                bWriter.write("ROOM ID;ROOM STATUS;ROOM CAPACITY;GUEST ID;GUEST NAME;GUEST SURNAME;OPERANT PERSON;TURNOVER;DATE");
            }
        }

        FileWriter registerWriter = new FileWriter(RegisterFile,true);
        try (BufferedWriter bWriter = new BufferedWriter(registerWriter)) {    
            bWriter.write("\n"+roomArr.get(getRoomOrder()).getRoomID()+camma
                +roomArr.get(getRoomOrder()).getRoomStatus()+camma+roomArr.get(getRoomOrder()).getRoomCapacity()+camma
                +roomArr.get(getRoomOrder()).getGuestID()+camma+roomArr.get(getRoomOrder()).getGuestName()+camma
                +roomArr.get(getRoomOrder()).getGuestSurname()+camma+operantPerson+camma
                +turnOver+camma+date);
        }
    }
    
    /**
     * "Rooms.csv" dosyasinda oda ile ilgili yapilan degisikliklerin kaydedildigi dosya yazma metodudur.
     * @throws IOException Metod icerisinde cagrilan dosyaya yazma islemi sonucu,
     * herhangi bir sorunla karsilasilabilir gibisinden firlatilan exceptiondir.
     */
    @Override
    public void roomsWriteFile(String fileName) throws IOException{
        String camma = ";";
        File RoomsFile = new File(fileName);
        FileWriter roomsWriter = new FileWriter(RoomsFile,false);
        if(!RoomsFile.exists()){
            RoomsFile.createNewFile();
        }

        try (BufferedWriter rooms = new BufferedWriter(roomsWriter)) {    
             rooms.write("ROOM ID;ROOM STATUS;ROOM CAPACITY;GUEST ID;GUEST NAME;GUEST SURNAME");
             for(int i = 0; i < Room.getMAXROOM(); i++){
                rooms.write("\n"+roomArr.get(i).getRoomID()+camma+roomArr.get(i).getRoomStatus()+camma
                                +roomArr.get(i).getRoomCapacity()+camma+roomArr.get(i).getGuestID()+camma
                                +roomArr.get(i).getGuestName()+camma+roomArr.get(i).getGuestSurname());
             }
                
        }
    }
    
    /**
     * "Guests.csv" dosyasinda oda ile ilgili yapilan degisikliklerin kaydedildigi dosya yazma metodudur.
     * @throws IOException Metod icerisinde cagrilan dosyaya yazma islemi sonucu,
     * herhangi bir sorunla karsilasilabilir gibisinden firlatilan exceptiondir.
     */
    @Override
    public void guestsWriteFile(String fileName) throws IOException {
        String camma = ";";
        File GuestsFile = new File(fileName);
        FileWriter roomsWriter = new FileWriter(GuestsFile,false);
        if(!GuestsFile.exists()){
            GuestsFile.createNewFile();
        }

        try (BufferedWriter guests = new BufferedWriter(roomsWriter)) {   
            guests.write("GUEST ID;GUEST NAME;GUEST SURNAME");
            for(int i = 0; i < guestArr.size(); i++){
               guests.write("\n"+guestArr.get(i).getGuestID()+camma+guestArr.get(i).getGuestName()+camma+guestArr.get(i).getGuestSurname());
            }     
        }
    }
    
    
    /**
     * "Rooms.csv" dosyasi icerisinde bulunan degerlerin satir satir okunup, 
     * split edilerek roomArr arrayine atilmasi islemini yapan metoddur.
     * @param fileName Islem yapilmasi istenen dosya ismi
     */
    @Override
    public void roomsReadFile(String fileName){
        String line = "";
        String csvSplitBy = ";";
        File roomsFile = new File(fileName);
        if(!roomsFile.exists()){
            try {
                roomsWriteFile(fileName);
            } catch (IOException ex) {
                System.err.println("File write failed");
            }
        }
        try(BufferedReader br = new BufferedReader(new FileReader(ROOMSFILENAME))){
            line = br.readLine();
            for (int i = 0; (line = br.readLine()) != null ; i++) {
                String[] roomInf = line.split(csvSplitBy);
                roomArr.get(i).setRoomID(Integer.parseInt(roomInf[0]));
                roomArr.get(i).setRoomStatus(roomInf[1].charAt(0));
                roomArr.get(i).setRoomCapacity(Integer.parseInt(roomInf[2]));
                roomArr.get(i).setGuestID(Integer.parseInt(roomInf[3]));
                roomArr.get(i).setGuestName(roomInf[4]);
                roomArr.get(i).setGuestSurname(roomInf[5]);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("File not found or the file can not be opened !!!");
            System.out.println("Hotel management Sytem Shut Down\n");
            exit(0);
        } catch (IOException ex) {
            System.out.println("Hotel management Sytem Shut Down\n");
            exit(0);
        }
    }
    
    /**
     * "Guests.csv" dosyasi icerisinde bulunan degerlerin satir satir okunup, 
     * split edilerek guestArr arrayine atilmasi islemini yapan metoddur.
     * @param fileName Islem yapilmasi istenen dosya ismi
     */
    @Override
    public void GuestsReadFile(String fileName) {
        String line = "";
        String csvSplitBy = ";";

        File guestFile = new File(fileName);
        if(!guestFile.exists()){
            try {
                guestsWriteFile(fileName);
            } catch (IOException ex) {
                System.err.println("File write failed");
            }
        }
        guestArr.clear();
        try(BufferedReader br = new BufferedReader(new FileReader(GUESTSFILENAME))){
            line = br.readLine();
            for (int i = 0; (line = br.readLine()) != null ; i++) {
                String[] guestInf = line.split(csvSplitBy);
                GuestInfo guest = new GuestInfo();
                guest.setGuestID(Integer.parseInt(guestInf[0]));
                guest.setGuestName(guestInf[1]);
                guest.setGuestSurname(guestInf[2]);
                guestArr.add(guest);
                
            }
        } catch (FileNotFoundException ex) {
            System.err.println("File not found or the file can not be opened !!!");
            System.out.println("Hotel management Sytem Shut Down\n");
            exit(0);
        } catch (IOException ex) {
            System.out.println("Hotel management Sytem Shut Down\n");
            exit(0);
        }
    }
    
    /**
     * Yapilan islem sonucu onaylamak icin Yes(Y/y) ya da No(N/n) olarak kullaniciya soru sorulur.
     * Kullanicinin yapacagi isleme gore true ya da false dondurulur
     * @param title Onaylanacak operasyonun başlığı
     * @return return true = Yes(Y/y) - return false = No(N/n)
     */
    public boolean confirm(String title){
        Scanner scan = HotelOperation.scanner;
        String yesNo = "";
        boolean save = true;
        do{
            System.out.printf(""+title+"\nYES: Press 'Y/y'\nNO: Press 'N/n'\n");
            yesNo = scan.next();
            if(yesNo.equals("Y") || yesNo.equals("y"))
                save = true;
            else if(yesNo.equals("N") || yesNo.equals("n"))
                save = false;
        }while(!(yesNo.equals("Y") || yesNo.equals("y") || yesNo.equals("N") || yesNo.equals("n")));
        
        return save;
    }
    
    /**
     * Tum odalari ve odalarin durumlarini bir String de tablo formatiyla tutup o String ifade return edilir ve ekrana basilir.
     * @return Tablo formatinda tutulmus odalar ve odalarin durumlari String olarak return edilir.
     */
    public String printRoom(){
        String ret = "";
         System.out.println("\n\tThe Conditions of The Rooms\n");
        for(int i = 0, j = 1 ; i < Room.getMAXROOM(); i++){
            if(i < j*10 ){
                ret += (roomArr.get(i).getRoomID() +":"+ roomArr.get(i).getRoomStatus()+"\t");
            }
            else{
                ret += "\n";
                j++;
                ret += (roomArr.get(i).getRoomID() +":"+ roomArr.get(i).getRoomStatus()+"\t");
            }
        }
        ret += "\n\nNote: The room capacity is parallel to the first digit of the room numbers.\n";
        return ret;
    }
    /**
     * Oda durumuna gore(Kac kisilik oda ise) bu tip odalari ve odalarin durumlarini bir String de tablo formatiyla tutup o String ifade return edilir ve ekrana basilir.
     * @param roomSize Odanin kac kisi oldugunu gosteren parametredir.
     * @return Tablo formatinda tutulmus odalar ve odalarin durumlari String olarak return edilir.
     */
    public String printRoom(int roomSize){
        String ret = "";
        for(int i = 0, j = 1 ; i < Room.getMAXROOM(); i++){
                if(i < j*10 ){
                    if(roomArr.get(i).getRoomID() > roomSize*100 && roomArr.get(i).getRoomID() < (roomSize+1)*100)
                        ret += (roomArr.get(i).getRoomID() +":"+ roomArr.get(i).getRoomStatus()+"\t");
                }
                else{
                    ret += "\n";
                    j++;
                    if(roomArr.get(i).getRoomID() > roomSize*100 && roomArr.get(i).getRoomID() < (roomSize+1)*100)
                        ret += (roomArr.get(i).getRoomID() +":"+ roomArr.get(i).getRoomStatus()+"\t");
                }
        }
        return ret;
    }
}
