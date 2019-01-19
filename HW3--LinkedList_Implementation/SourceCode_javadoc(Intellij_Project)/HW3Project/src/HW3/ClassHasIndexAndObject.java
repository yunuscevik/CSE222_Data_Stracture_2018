
package HW3;

/**
 * Part2 de kullanilan bu class icerisinde index ve object verilerini tutar.
 * @author Yunus CEVIK
 */
public class ClassHasIndexAndObject {
    private int index;  
    private Object object;

    public ClassHasIndexAndObject(){
        index = 0;
        object = null;
    }

    public ClassHasIndexAndObject(int i, Object o){
        index = i;
        object = o;
    }
    // ---- getter ---- //
    public int getIndex() { return index; }
    public Object getObject() { return object; }
    
    // ---- setter ---- //
    public void setIndex(int index) { this.index = index; }
    public void setObject(Object object) { this.object = object; }
    
}
