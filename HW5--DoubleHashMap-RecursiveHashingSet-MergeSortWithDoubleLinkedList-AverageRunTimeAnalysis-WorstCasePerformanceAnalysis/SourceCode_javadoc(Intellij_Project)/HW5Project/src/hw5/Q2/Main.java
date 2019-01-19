
package hw5.Q2;



/**
 * RecursiveHashingSet Main class
 * @author Yunus Cevik
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("\t----- Size: 7 - Hash Table RecursiveHashingSet<Integer> -----");
        RecursiveHashingSet<Integer> q4a = new RecursiveHashingSet<>(7);
        System.out.println("Is Empty: " + q4a.isEmpty() + "\n");
        System.out.println(q4a.add(12) + " <- " + "q4a.add(12)");
        System.out.println(q4a.add(22) + " <- " + "q4a.add(22)"); 
        System.out.println(q4a.add(19) + " <- " + "q4a.add(19)"); 
        System.out.println(q4a.add(135) + " <- " + "q4a.add(135)");
        System.out.println(q4a.add(25) + " <- " + "q4a.add(25)");
        System.out.println(q4a.add(50) + " <- " + "q4a.add(50)"); 
        System.out.println(q4a.add(43) + " <- " + "q4a.add(43)"); 
        System.out.println(q4a.add(21) + " <- " + "q4a.add(21)");
        System.out.println(q4a.add(7) + " <- " + "q4a.add(7)");
        System.out.println(q4a.add(123) + " <- " + "q4a.add(123)"); 
        System.out.println(q4a.add(258) + " <- " + "q4a.add(258)"); 
        System.out.println(q4a.add(701) + " <- " + "q4a.add(701)");
        
        System.out.println("\nIs Empty: " + q4a.isEmpty());
        System.out.println("Size: " + q4a.size());
        System.out.println("contains : " + q4a.contains(22) + " <- " + "q4a.contains(22)");
        System.out.println("remove : " + q4a.remove(22) + " <- " + "q4a.remove(22)");
        System.out.println("contains : " + q4a.contains(22) + " <- " + "q4a.contains(22)");
        System.out.println("remove : " + q4a.remove(22) + " <- " + "q4a.remove(22)");
        System.out.println("Size: " + q4a.size());
        
        
        System.out.println("contains : " + q4a.contains(135) + " <- " + "q4a.contains(135)");
        System.out.println("remove : " + q4a.remove(135) + " <- " + "q4a.remove(135)");
        System.out.println("contains : " + q4a.contains(135) + " <- " + "q4a.contains(135)");
        System.out.println("remove : " + q4a.remove(135) + " <- " + "q4a.remove(135)");
        System.out.println("Size: " + q4a.size());
        
        
        
        System.out.println("\n\n\t----- Size: 21 - Hash Table RecursiveHashingSet<Integer> -----");
        RecursiveHashingSet<Integer> q4b = new RecursiveHashingSet<>(21);
        System.out.println("Is Empty: " + q4b.isEmpty() + "\n");
        System.out.println(q4b.add(21) + " <- " + "q4b.add(21)");
        System.out.println(q4b.add(22) + " <- " + "q4b.add(22)"); 
        System.out.println(q4b.add(19) + " <- " + "q4b.add(19)"); 
        System.out.println(q4b.add(28) + " <- " + "q4b.add(28)");
        System.out.println(q4b.add(63) + " <- " + "q4b.add(63)");
        System.out.println(q4b.add(50) + " <- " + "q4b.add(50)"); 
        System.out.println(q4b.add(43) + " <- " + "q4b.add(43)"); 
        System.out.println(q4b.add(42) + " <- " + "q4b.add(42)");
        System.out.println(q4b.add(7) + " <- " + "q4b.add(7)");
        System.out.println(q4b.add(123) + " <- " + "q4b.add(123)"); 
        System.out.println(q4b.add(258) + " <- " + "q4b.add(258)"); 
        System.out.println(q4b.add(701) + " <- " + "q4b.add(701)");
        
        System.out.println("\nIs Empty: " + q4b.isEmpty());
        System.out.println("Size: " + q4b.size());
        System.out.println("contains : " + q4b.contains(63) + " <- " + "q4b.contains(63)");
        System.out.println("remove : " + q4b.remove(63) + " <- " + "q4b.remove(63)");
        System.out.println("contains : " + q4b.contains(63) + " <- " + "q4b.contains(63)");
        System.out.println("remove : " + q4b.remove(63) + " <- " + "q4b.remove(63)");
        System.out.println("Size: " + q4b.size());
        
        
        System.out.println("contains : " + q4b.contains(135) + " <- " + "q4a.contains(135)");
        System.out.println("remove : " + q4b.remove(135) + " <- " + "q4a.remove(135)");
        System.out.println("contains : " + q4b.contains(135) + " <- " + "q4a.contains(135)");
        System.out.println("remove : " + q4b.remove(135) + " <- " + "q4a.remove(135)");
        System.out.println("Size: " + q4b.size());
        
        
    }
    
    
}
