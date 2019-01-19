
package HMS;

import java.io.IOException;
import java.util.Date;

/**
 * Objesi uretilemeyen ancak Polimorfic cagrilarda kullanilan interface.
 * @author Yunus CEVIK
 */
public interface HotelManagementSystem {
    /**
     * Main de secilen kisiler sonucu hangi sinifin neleri calistiracagini belirleyen metoddur.
     */
    public void run();
    /**
     * Resepsiyonist ya da Misafirin hangi islemleri yaptiracagini belirten metoddur.
     * Secilen degerlere gore yanlis bir deger secildigi taktirde kullanicidan tekrar input girilmesini bekler.
     *
     * @return Resepsiyonist ya da Misafirin sectigi islemlere gore integer deger return eder.
     * For example: Cancel Reservation: Press'1' = return 1;
     */
    public int Operations();
    /**
     * Cesitli odalara sahip otelde kac kisilik odalarin secileceigini soran metoddur.
     * @return Secilen oda kapasitesi sonucu kac kisilik oldugunu integer reger olarak return eder.
     * For example: 1-5 kisik odalar var Room Capacity: 1 secilirse return 1. 
     * Yani sadece 1 kisilik odalar ekranda gorunur ve o odalardan secim yapilir.
     */
    public int queryRoomCapacity();
    /**
     * Resepsiyonist ya da Misafirin cagirmasi sonucu oda bilgilerinin girilerek o odayi rezerve yapma isini gerceklestiren metoddur.
     * Ayrica onceden giris yapmis olan Misafir var ise sadece kimlik numarasi ile misafirlerin kayitlarinin tutuldugu veri tabani taranir.
     * Daha sonra ayni kisi sisteme giris yapmis ise diger bilgilerini girmesine gerek olmadan odayi rezerve yapalir.
     * @param status Hangi metodun cagirdigini belirten parametredir.
     * @return return -2: Rezervasyon isleminden bir onceki menuye geri donmek icin kullanilir. 
     * return RoomID: Islem tamamlandiktan sonra baska bir metodun kullanilmasi icin retun edilen RoomID vardir.
     */
    public int bookRoom(char status);
    /**
     * Resepsiyonist ya da Misafirin cagirmasi sonucu oda bilgilerinin girilerek rezerve edilmis odayi iptal edip oda bilgilerini sifirlayan metoddur.
     * @param operantPerson Iptal isini yapan kisi
     * @return return -2: Rezervasyon isleminden bir onceki menuye geri donmek icin kullanilir. Ya da return 0.
     * @throws IOException Metod icerisinde cagrilan dosyaya yazma islemi sonucu,
     * herhangi bir sorunla karsilasilabilir gibisinden firlatilan exceptiondir.
     */
    public int cancelBookRoom(String operantPerson) throws IOException;
    /**
     * Parametrelere verilen degerler sonucu girilen oda numarasinin var olup olmadigini ve
     * kim tarafindan cagrildigini belirten status degeri ile revervasyon yapma yada iptal etme, check in ya da check out yapma islemlerinde
     * nasil odalari kontrol edecegini belirten metoddur.  Yani metodlar icinde cagrilmasi ile farkli yapida calisan bir metoddur.
     * @param id control edilecek oda numarasi
     * @param status Hangi metodun cagirdigini belirten parametredir.
     * @return Eger oda numarasi mevcut ve olumlu bir islem gerceklestirilmis ise "true", degil ise "false" return eder.
     */
    public boolean checkRoomID(int id, char status);
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
    public boolean checkGuestID(int guestID,int roomID,char status);
    /**
     * Oda numaralari ve misafir kisilerin input girecegi metoddur. 
     * Ancak status durumu = 'R' rezervasyon durumunda sadece kullaniciya oda numarasi girdisi sunmaktadir.
     * @param status Bu metodu hangi metodun ne amacla cagirdigini belirten parametre.
     * @return return -2: Rezervasyon isleminden bir onceki menuye geri donmek icin kullanilir. 
     * return RoomID: Islem tamamlandiktan sonra baska bir metodun kullanilmasi icin retun edilen RoomID vardir.
     */
    public int roomAndGuestIDInput(char status);
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
    public void registerWriteFile(String fileName, String operantPerson, String turnOver, Date date) throws IOException;
    
    /**
     * "Rooms.csv" dosyasinda oda ile ilgili yapilan degisikliklerin kaydedildigi dosya yazma metodudur.
     * @param fileName Islem yapilmasi istenen dosya ismi
     * @throws IOException Metod icerisinde cagrilan dosyaya yazma islemi sonucu,
     * herhangi bir sorunla karsilasilabilir gibisinden firlatilan exceptiondir.
     */
    public void roomsWriteFile(String fileName) throws IOException;
    /**
     * "Guests.csv" dosyasinda oda ile ilgili yapilan degisikliklerin kaydedildigi dosya yazma metodudur.
     * @param fileName Islem yapilmasi istenen dosya ismi
     * @throws IOException Metod icerisinde cagrilan dosyaya yazma islemi sonucu,
     * herhangi bir sorunla karsilasilabilir gibisinden firlatilan exceptiondir.
     */
    public void guestsWriteFile(String fileName) throws IOException;
    /**
     * "Rooms.csv" dosyasi icerisinde bulunan degerlerin satir satir okunup, 
     * split edilerek roomArr arrayine atilmasi islemini yapan metoddur.
     * @param fileName Islem yapilmasi istenen dosya ismi
     */
    public void roomsReadFile(String fileName);
    /**
     * "Guests.csv" dosyasi icerisinde bulunan degerlerin satir satir okunup, 
     * split edilerek guestArr arrayine atilmasi islemini yapan metoddur.
     * @param fileName Islem yapilmasi istenen dosya ismi
     */
    public void GuestsReadFile(String fileName);
}
