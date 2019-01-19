
package HW3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Icerisinde LinkedList objesi bulunan bu class, bu objenin icine "Courses(CSV).csv" dosyasindan 
 * okunan bilgilerle doldurulmus Course bilgilerini LinkedList metodlari ve sonradan implement edilen
 * getbyCode, listSemesterCourses ve getByRange bu metodlarla kullanma imkani saglar.
 * @author Yunus CEVIK
 */

public class Part1 {
    private final LinkedList<Course> list;
    private static final String COURSEFILE = "Courses(CSV).csv";
    public LinkedList<Course> getList() {
        return list;
    }
    /**
     * Default Constructor = LinkedList objesi oluşturup csvReadFile metodu ile okunan bilgileri icine doldurur.
     */
    public Part1(){
        list = new LinkedList();
        csvReadFile(COURSEFILE);
    }
    /**
     * Parametresinde aldigi Course code u ile Course larin icinde tutuldugu listede arama yapar
     * ve bu Course Code' una sahip Course lari bir liste olarak return eder.
     * @param code Course classi icinde bulunan Course Code.
     * @return Course Code degerine sahip Course lari bir Course tipinde LinkedList olarak return eder.
     * @throws Exception Course Code listede olmayan bir deger olarak verildiginde hata icin bilgilendirme exception i firlatilir.
     */
    public LinkedList<Course> getByCode (String code) throws Exception{
        LinkedList returnList = new LinkedList();
        Iterator<Course> iter = list.iterator();
        while(iter.hasNext()){
            Course tempCourse = (Course) iter.next();
            if(code.equals(tempCourse.getCourseCode()))
                returnList.add(tempCourse);
        }
        if(returnList.isEmpty())
            throw new Exception( "The course with the " + code + " course code was not found.");

        return returnList;
    }
    /**
     * Parametresinde aldigi semester degeri ile Course larin icinde tutuldugu listede arama yapar
     * ve semester degerine sahip Course lari bir liste olarak return eder.
     * @param semester Course classi icinde bulunan semester degeri.
     * @return Semester degerine sahip Course lari bir Course tipinde LinkedList olarak return eder.
     * @throws Exception Semester degeri listede olmayan bir deger olarak verildiginde hata icin bilgilendirme exception i firlatilir.
     */
    public LinkedList<Course> listSemesterCourses (int semester) throws Exception{
        LinkedList returnList = new LinkedList();
        Iterator iter = list.iterator();
        while(iter.hasNext()){
            Course tempCourse = (Course) iter.next();
            if(semester == tempCourse.getSemester())
                returnList.add(tempCourse);
        }
        if(returnList.isEmpty())
            throw new Exception("The course with "+ semester +". semester was not found.");    
        return returnList;
    }
    /**
     * Verilen index ler arasinda bulunan Course bilgilerini bir liste olarak return eder.
     * @param start_index Baslangic yapilacak ilk index degeri.
     * @param last_index Bitis yapilacak son index degeri.
     * @return Index ler arasinda bulunan Course bilgilerini bir Course tipinde LinkedList olarak return eder.
     * @throws Exception Range degerleri listeye uymuyorsa hata icin bilgilendirme exception i firlatilir.
     */
    public LinkedList<Course> getByRange(int start_index, int last_index) throws Exception{
        if(!(start_index >= 0 && list.size()> start_index))
            throw new Exception("You can not access the listenin elements from "+start_index+" to "+last_index+" indexes."); 
        LinkedList returnList = new LinkedList();
        for(int i = start_index; i < last_index; i++)
            returnList.add(list.get(i));
        return returnList;
    }
    /**
     * CSV dosyasindan okuma islemi yaparak LinkedList objesinin icine ekler islem 
     * basarili ise true basarisiz ise false return eder.
     * @param fileName Okuma yapilacak dosya ismi
     * @return Okuma islemi basarili ise True, basarisiz ise False
     */
    private boolean csvReadFile(String fileName){
        String line = "";
        String csvSplitBy = ";";
        boolean check = false;
        File file = new File(fileName);
        if(!file.exists()){
            System.out.println("File not found or the file can not be opened !!!");
            return check;
        }
        else{
            check = true;
        
            try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
                line = br.readLine();
                while((line = br.readLine()) != null) {
                    String[] courseInf = line.split(csvSplitBy);
                    Course course = new Course();

                    course.setSemester(Integer.parseInt(courseInf[0]));
                    course.setCourseCode(courseInf[1]);
                    course.setCourseTitle(courseInf[2]);
                    course.setECTSCredits(courseInf[3]);
                    course.setGTUCredits(courseInf[4]);
                    course.setHTL(courseInf[5]);

                    list.add(course);

                }
            } catch (FileNotFoundException ex) {
                System.err.println("File not found or the file can not be opened !!!\n" + ex);
                check = false;
            } catch (IOException ex) {
                System.err.println("File not found or the file can not be opened !!!\n" + ex);
                check = false;
            }
        }
        return check;
    }
    
}
