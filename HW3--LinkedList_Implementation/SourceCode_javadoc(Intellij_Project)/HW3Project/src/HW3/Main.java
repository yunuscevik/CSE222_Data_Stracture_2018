
package HW3;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * main(String[] args) metodunu bulunduran ve programi tekikleyen classtir.
 * @author Yunus CEVIK
 */
public class Main {

    /**
     * Part1, Part2, Part3 claslarinin objeleri olusturulup icerisinde bulunan 
     * metodların kullanilarak bir senaryo akisi saglanmis cagrilan programlarin run edilmesidir.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Part1 p1 = new Part1();
        String titles = "Semester Course Code\t\tCourse Title\t\tECTS C.\tGTU C.\tH+T+L";
        try {
            System.out.println("\t\t---------- PART1 <Course>----------\n");
            System.out.println("\t---------- getByCode(code) code=> \"XXX XXX\" ----------");
            LinkedList<Course> p1Print = p1.getByCode("XXX XXX");
            System.out.println(titles);
            for(int i = 0; i < p1Print.size(); i++)
                System.out.println(p1Print.get(i).toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        try{
            System.out.println("\n\t---------- listSemesterCourses(semester) semester=> 2 ----------");
            LinkedList<Course> p1Print = p1.listSemesterCourses(2);
            System.out.println(titles);
            for(int i = 0; i < p1Print.size(); i++)
                System.out.println(p1Print.get(i).toString());
        } catch (Exception e) {
            System.out.println(e);
        }  
        try{
            System.out.println("\n\t---------- getByRange(start_index, last_index) start_index=> 10, last_index=> 20 ----------");
            LinkedList<Course> p1Print = p1.getByRange(10, 20);
            System.out.println(titles);
            for(int i = 0; i < p1Print.size(); i++)
                System.out.println(p1Print.get(i).toString());
        } catch (Exception e) {
            System.out.println(e);
        }

        //------------------------------------------------------------------------------------------------------------//
        Part2<Course> p2 = new Part2<>(p1.getList());
        LinkedList a = new LinkedList();
        Part2<Course> b = new Part2<>(a);

        System.out.println("\n\n\t\t---------- PART2 - Generic <E> ( This Example <Course> ) ----------\n");
        System.out.println("\t---------- GTU Computer Engineering Course ----------");
        System.out.println("Index "+titles);
        System.out.println(p2.toString());
        
        System.out.println("\n\t---------- disable(index) index=> 0, 5, 13 ----------");
        p2.disable(0);
        p2.disable(5);
        p2.disable(13);
        System.out.println("\t---------- showDisable() ----------");
        System.out.println("Index "+titles);
        p2.showDisable();
        System.out.println("\t---------- GTU Computer Engineering Course ----------");
        System.out.println("Index "+titles);
        System.out.println(p2.toString());
        System.out.println("\n\t---------- disable(index) index=> 21, 7, 17 ----------");
        p2.disable(21);
        p2.disable(7);
        p2.disable(17);
        System.out.println("\t---------- showDisable() ----------");
        System.out.println("Index "+titles);
        p2.showDisable();

        System.out.println("\n\t---------- enable(index) index=> 6, 24, 21 ----------");
        p2.enable(6);
        p2.enable(24);
        p2.enable(21);
        System.out.println("\t---------- showDisable() ----------");
        System.out.println("Index "+titles);
        p2.showDisable();
        System.out.println("\t---------- GTU Computer Engineering Course ----------");
        System.out.println("Index "+titles);
        System.out.println(p2.toString());
        System.out.println("\n\t---------- enable(index) index=> 0, 15, 9 ----------");
        p2.enable(0);
        p2.enable(15);
        p2.enable(9);
        System.out.println("\t---------- showDisable() ----------");
        System.out.println("Index "+titles);
        p2.showDisable();
        
        System.out.println("\t---------- GTU Computer Engineering Course ----------");
        System.out.println("Index "+titles);
        System.out.println(p2.toString());
        //------------------------------------------------------------------------------------------------------------//
        Part3<Course> p3 = new Part3<>();
        try {
            System.out.println("\n\n\t\t---------- PART3 Generic <E> ( This Example <Course> ) ----------\n");
            System.out.println("\t---------- add(Courses) ----------");
            for(int i = 0; i < 10; i+=3)
                p3.add(p1.getList().get(i));
            System.out.println("\t---------- add(index, Course) ----------");
            p3.add(3, (Course) p1.getList().getLast());
            System.out.println("\t---------- hasNext() - next() ----------");
            System.out.println(titles);
            while(p3.hasNext())
                System.out.println(p3.next());
            
            System.out.println("\n\t---------- size() ----------");
            System.out.println("size => " + p3.size());
            
            System.out.println("\n\t---------- remove() ----------");
            p3.remove();
            System.out.println("\t---------- remove(Course) ----------");
            p3.remove(p1.getList().getFirst());
            System.out.println("\t---------- remove(index) index=> 2 ----------");
            p3.remove(2);
            System.out.println("\t---------- hasNext() - next() ----------");
            System.out.println(titles);
            while(p3.hasNext())
                System.out.println(p3.next());
            
            
            System.out.println("\n\t---------- add(Courses) ----------");
            p3.add(p1.getList().get(15));
            p3.add(p1.getList().get(20));
            p3.add(p1.getList().getLast());
            p3.add(p1.getList().get(30));
            p3.add(p1.getList().get(40));
            p3.add(p1.getList().getFirst());
            p3.add(p1.getList().get(41));
            
            System.out.println("\t---------- hasNext() - next() ----------");
            System.out.println(titles);
            while(p3.hasNext())
                System.out.println(p3.next());
            
            System.out.println("\n\t---------- nextInSemester(semester) semester=> 1 ----------");
            System.out.println(titles);
            System.out.println(p3.nextInSemester(1));
            System.out.println(p3.nextInSemester(1));
            System.out.println(p3.nextInSemester(1));
            System.out.println(p3.nextInSemester(1));
            System.out.println(p3.nextInSemester(1));
            System.out.println(p3.nextInSemester(1));
            System.out.println(p3.nextInSemester(1));
            System.out.println(p3.nextInSemester(1));
            System.out.println(p3.nextInSemester(1));
 
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
    }
     
}
