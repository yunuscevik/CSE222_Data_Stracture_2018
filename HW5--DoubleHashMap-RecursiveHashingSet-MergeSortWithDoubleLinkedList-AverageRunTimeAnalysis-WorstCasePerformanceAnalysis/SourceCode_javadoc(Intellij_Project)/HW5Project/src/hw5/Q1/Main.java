
package hw5.Q1;



/**
 * DoubleHashMap Main class
 * @author Yunus Cevik
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DoubleHashMap<Integer,String> q1a = new DoubleHashMap<>(7);
        System.out.println("\t----- Size: 7 - Hash Table DoubleHashMap<Integer,String> (Rehash)-----");
        q1a.put(1, "ali");
        q1a.put(1, "veli");
        q1a.put(3, "ayse");
        q1a.put(4, "fatma");
        q1a.put(11, "ali");
        q1a.put(20, "veli");
        q1a.put(330, "hasan");
        q1a.put(330, "hüseyin"); 

        for(int i = 0; i < q1a.getDhmTable().length; i++){
            if(q1a.getDhmTable()[i] != null)
                System.out.println("index: " + i +"\t-----> { "+ q1a.getDhmTable()[i].getKey() + " : "+ q1a.getDhmTable()[i].getValue()+ " }");
            else
                System.out.println("index: " + i +"\t-----> { null : null }");
        }
        System.out.println("\nKey: 330 -> " + q1a.remove(330) + " <- " + "q1a.remove(330)");
        System.out.println("Key: 4 -> " + q1a.remove(4) + " <- " + "q1a.remove(4)\n");
        for(int i = 0; i < q1a.getDhmTable().length; i++){
            if(q1a.getDhmTable()[i] != null)
                System.out.println("index: " + i +"\t-----> { "+ q1a.getDhmTable()[i].getKey() + " : "+ q1a.getDhmTable()[i].getValue()+ " }");
            else
                System.out.println("index: " + i +"\t-----> { null : null }");
        }
        
        
        DoubleHashMap<String,Double> q1b = new DoubleHashMap<>(11);
        System.out.println("\n\n\t----- Size: 11 - Hash Table DoubleHashMap<String,Double> -----");
        q1b.put("ali", 3.14);
        q1b.put("veli", 2.78);
        q1b.put("ayse", 4.00);
        q1b.put("fatma", 12.99);
        q1b.put("ali", 1.1);
        q1b.put("veli", 2.22);
        q1b.put("hasan", 45.56);
        q1b.put("hüseyin", 14.45); 
                    
        for(int i = 0; i < q1b.getDhmTable().length; i++){
            if(q1b.getDhmTable()[i] != null)
                System.out.println("index: " + i +"\t-----> { "+ q1b.getDhmTable()[i].getKey() + " : "+ q1b.getDhmTable()[i].getValue()+ " }");
            else
                System.out.println("index: " + i +"\t-----> { null : null }");
        }
        System.out.println("\nKey: \"ayse\" -> " + q1b.remove("ayse") + " <- " + "q1b.remove(\"ayse\")");
        System.out.println("Key: \"ali\" -> " + q1b.remove("ali") + " <- " + "q1b.remove(\"ali\")\n");
        for(int i = 0; i < q1b.getDhmTable().length; i++){
            if(q1b.getDhmTable()[i] != null)
                System.out.println("index: " + i +"\t-----> { "+ q1b.getDhmTable()[i].getKey() + " : "+ q1b.getDhmTable()[i].getValue()+ " }");
            else
                System.out.println("index: " + i +"\t-----> { null : null }");
        }
        
    }
    
    
}
