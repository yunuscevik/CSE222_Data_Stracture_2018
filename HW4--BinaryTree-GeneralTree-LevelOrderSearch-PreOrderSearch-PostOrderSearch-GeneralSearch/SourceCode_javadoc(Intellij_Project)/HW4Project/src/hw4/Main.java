
package hw4;

/**
 * main(String[] args) metodunu bulunduran ve programi tekikleyen classtir.
 * @author Yunus Cevik
 */
public class Main {
    private static final int LEVELORDERSEARCH = 1;
    private static final int POSTORDERSEARCH = 2;

    /**
     * Part1, Part2, Part3 claslarinin objeleri olusturulup icerisinde bulunan
     * metodların kullanilarak bir senaryo akisi saglanmis cagrilan programlarin run edilmesidir.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("\n------------------------------------------------");
        System.out.println("--------------------  PART1 --------------------");
        System.out.println("------------------------------------------------\n");
        Part1<Integer> p1 = new Part1<>();
        System.out.println("---------- p1.add(parentItem: int, childItem: int, , levelOrPost: LEVELORDERSEARCH) ----------");
        p1.add(1,2,LEVELORDERSEARCH);
        p1.add(1,4,LEVELORDERSEARCH);
        p1.add(1,7,LEVELORDERSEARCH);
        p1.add(2,3,LEVELORDERSEARCH);
        p1.add(2,6,LEVELORDERSEARCH);
        p1.add(3,5,LEVELORDERSEARCH);
        p1.add(3,20,LEVELORDERSEARCH);
        p1.add(4,21,LEVELORDERSEARCH);
        p1.add(4,22,LEVELORDERSEARCH);
        p1.add(2,30,LEVELORDERSEARCH);
        p1.add(5,32,LEVELORDERSEARCH);
        p1.add(5,33,LEVELORDERSEARCH);
        System.out.println("Add Results => true\n");
        System.out.println("INTEGER TYPE OF TREE\n\n"+ p1.toString());
        System.out.println("\n---------- Traverses ----------");
        p1.printLevelOrder();
        p1.printPostorder();
        p1.printPreOrderTraverse();

        System.out.println("----------------------------------------------------\n");

        Part1<String> p1_String = new Part1<>();
        System.out.println("\n---------- p1.add(parentItem: String, childItem: Sting, levelOrPost: POSTORDERSEARCH) ----------");
        p1_String.add("Ali","Ayşe",POSTORDERSEARCH);
        p1_String.add("Ali","Veli",POSTORDERSEARCH);
        p1_String.add("Veli","Ziya",POSTORDERSEARCH);
        p1_String.add("Veli","Cabbar",POSTORDERSEARCH);
        p1_String.add("Ayşe","Fatma",POSTORDERSEARCH);
        p1_String.add("Ayşe","Hayriye",POSTORDERSEARCH);
        p1_String.add("Ali","Huriye",POSTORDERSEARCH);
        p1_String.add("Fatma","Abbas",POSTORDERSEARCH);
        p1_String.add("Hayriye","Zeynep",POSTORDERSEARCH);
        p1_String.add("Zeynep","Furkan",POSTORDERSEARCH);
        p1_String.add("Zeynep","Betül",POSTORDERSEARCH);
        p1_String.add("Abbas","Muhittin",POSTORDERSEARCH);
        System.out.println("Add Results => true\n");
        System.out.println("STRING TYPE OF TREE\n\n"+ p1_String.toString());
        System.out.println("\n---------- Traverses ----------");
        p1_String.printLevelOrder();
        p1_String.printPostorder();
        p1_String.printPreOrderTraverse();

        System.out.println("\n------------------------------------------------");
        System.out.println("--------------------  PART2 --------------------");
        System.out.println("------------------------------------------------\n");
        Part2<MultiArgs<Integer>> p2 = new Part2<>();
        try {
            System.out.println("---------- p2.add(new MultiArgs<>(dimensionValue: int, ...args: int,int,int) ----------");

            p2.add(new MultiArgs<>(3,2,4,5));
            p2.add(new MultiArgs<>(3,1,3,5));
            p2.add(new MultiArgs<>(3,4,2,5));

            p2.add(new MultiArgs<>(3,5,4,5));
            p2.add(new MultiArgs<>(3,3,4,5));
            p2.add(new MultiArgs<>(3,10,4,5));

            p2.add(new MultiArgs<>(3,53,45,51));
            p2.add(new MultiArgs<>(3,50,30,67));
            p2.add(new MultiArgs<>(3,10,46,5));
            System.out.println("Add Results => true\n");
            System.out.println("MULTI DIMENSION TREE\n\n"+p2.toString());


            System.out.println("---------- p2.contains(new MultiArgs<>(dimensionValue: 3, ...args: 53,45,51) ----------");
            boolean contains = p2.contains(new MultiArgs<>(3,53,45,51));
            System.out.println("Contains Result => "+ contains + "\n");


            System.out.println("---------- p2.find(new MultiArgs<>(dimensionValue: 3, ...args: 5,4,5) ----------");
            MultiArgs<Integer> foundValue = p2.find(new MultiArgs<>(3,5,4,5));
            System.out.println("Found Value => "+foundValue.toString() + "\n");

            System.out.println("---------- p2.delete(new MultiArgs<>(dimensionValue: 3, ...args: 3,4,5) ----------");
            MultiArgs<Integer> deletedValue = p2.delete(new MultiArgs<>(3,3,4,5));
            System.out.println("Deleted Value => "+deletedValue.toString() + "\n");

            System.out.println("---------- p2.remove(new MultiArgs<>(dimensionValue: 3, ...args: 3,4,5) ----------");
            boolean remove = p2.remove(new MultiArgs<>(3,3,4,5));
            System.out.println("Remove Result => "+ remove + "\n");


            System.out.println("---------- p2.remove(new MultiArgs<>(dimensionValue: 3, ...args: 4,2,5) ----------");
            remove = p2.remove(new MultiArgs<>(3,4,2,5));
            System.out.println("Remove Result => "+ remove + "\n");

            System.out.println("MULTI DIMENSION TREE\n\n"+p2.toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
