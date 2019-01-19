package HW3;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Part1 class inin bir objesi olusturulur.
 * Olusturulan bu obje ile getByCode, listSemesterCourses ve getByRange metodlari test edilir.
 */
class Part1Test {
    Part1 p1 = new Part1();
    String titles = "Semester Course Code\t\tCourse Title\t\tECTS C.\tGTU C.\tH+T+L";

    /**
     * Parametre olarak girilen bir Course code ile o code a ait tum Course lari listeleyen metodun unitTest metodudur.
     */
    @Test
    void getByCode() {
        System.out.println("\t\t---------- PART1_TEST <Course>----------\n");
        try {

            System.out.println("\t---------- getByCode(code) code=> \"XXX XXX\" ----------");
            LinkedList<Course> p1Print = p1.getByCode("XXX XXX");
            System.out.println(titles);
            for(int i = 0; i < p1Print.size(); i++) {
                assertEquals("XXX XXX",p1Print.get(i).getCourseCode());
                System.out.println(p1Print.get(i).toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Parametre olarak girilen bir Semester degeri ile o semester e ait tum Course lari listeleyen metodun unitTest metodudur.
     */
    @Test
    void listSemesterCourses() {
        System.out.println("\t\t---------- PART1_TEST <Course>----------\n");
        try{
            System.out.println("\n\t---------- listSemesterCourses(semester) semester=> 2 ----------");
            LinkedList<Course> p1Print = p1.listSemesterCourses(2);
            System.out.println(titles);
            for(int i = 0; i < p1Print.size(); i++) {
                assertEquals(2,p1Print.get(i).getSemester());
                System.out.println(p1Print.get(i).toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Parametre olarak girilen baslangic ve bitis indexlerinin arasinda bulunan tum Course lari listeleyen metodun unitTest metodudur.
     */
    @Test
    void getByRange() {
        System.out.println("\t\t---------- PART1_TEST <Course>----------\n");
        try{
            System.out.println("\t---------- getByRange(start_index, last_index) start_index=> 10, last_index=> 20 ----------");
            LinkedList<Course> p1Print = p1.getByRange(10, 20);
            System.out.println(titles);
            for(int i = 0; i < p1Print.size(); i++)
                System.out.println(p1Print.get(i).toString());
            assertEquals(10,p1Print.size());
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}