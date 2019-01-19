
package HW3;

/**
 * GTU Computer Engineering Course ların tutuldugu classtir
 * @author Yunus CEVIK
 */
public class Course {
    private int semester;
    private String courseCode;
    private String courseTitle;
    private String ECTSCredits;
    private String GTUCredits;
    private String HTL;

    // ---- getter ---- //
    public int getSemester() { return semester; }
    public String getCourseCode() { return courseCode; }
    public String getCourseTitle() { return courseTitle; }
    public String getECTSCredits() { return ECTSCredits; }
    public String getGTUCredits() { return GTUCredits; }
    public String getHTL() { return HTL; }
    
    // ---- setter ---- //
    public void setSemester(int semester) { this.semester = semester; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }
    public void setECTSCredits(String ECTSCredits) { this.ECTSCredits = ECTSCredits; }
    public void setGTUCredits(String GTUCredits) { this.GTUCredits = GTUCredits; }
    public void setHTL(String HTL) { this.HTL = HTL; }
    
    /**
     * Course classina ait tum verileri String olarak return eder.
     * @return Semester, Course Code, Course Title, ECTS Credits, GTU Credits ve H-T-L 
     * bilgilerini tek bir String olarak return eder.
     */
    @Override
    public String toString() {
        return String.format("%d\t%s  \t%s\t%s\t%s\t%s",
                    this.getSemester(),this.getCourseCode(),this.getCourseTitle(),
                    this.getECTSCredits(),this.getGTUCredits(),this.getHTL());
    }
}
