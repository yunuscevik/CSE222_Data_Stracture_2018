
package hw4;

import java.util.LinkedList;
import java.util.Queue;



/**
 * Binary Tree yapisini General Tree olarak kullanan ve ayrica Binary Tree metodlari ile sonradan implement edilen
 * add, levelOrderSearch, postOrderSearch ve preOrderTraverse metodlarini kullanma imkani saglayan bir siniftir.
 * @author Yunus Cevik
 * @param <E> Generic Type
 */
public class Part1<E> extends BinaryTree<E>{
    /**
     * Default Constructor
     */
    public Part1(){
        super.root = new Node<>();
    }
    /**
     * Tek parametre alan Constructor
     * @param root Root degerine parametre olarak verilecek Node.
     */
    public Part1(Node <E> root) {
        super.root = root;
    }
    /**
     * Bir agac yapisina Node eklemek icin kullanilir.
     * @param parentItem Agac yapisi uzerinde bulunan Node degerlerinden hangisinine ekleme yapacagimizi belirtiriz.
     * @param childItem Belirtilen Parent degerine yeni bir Child eklemek icin belirtilen parametredir.
     * @param levelOrPost Hangi Search metodunu kullanarak arama islemini gerceklestirecegimizi belirttigimiz parametre
     * @return ChildItem basarili olarak eklendiginde True. Ekleme islemi basarisiz ise False dondurulur.
     */
    public boolean add(E parentItem, E childItem,int levelOrPost){
        boolean ret = false;
        Node<E> parent = null;
        if(root.data != null){
            if(levelOrPost == 1)
                parent = levelOrderSearch(root, parentItem, false);
            else if(levelOrPost == 2)
                parent= postOrderSearch(root, parentItem);

            if(parent != null){

                if(parent.left == null){
                    // Parent in eger child yok ise child olarak parentin soluna eklenir.
                    parent.left = new Node<>(childItem);
                    System.out.println("(ADD) parentItem: " + parentItem +"\t-\t"+"childITem: "+ childItem + "\t(LEFT NODE - Child)");
                    ret = true;
                }
                else{
                    // Parent in eger child i var ise child in sag tarafina sibling olarak eklenir.
                    Node<E> tempNode = parent.left;
                    while(tempNode.right != null)
                        tempNode = tempNode.right;

                    tempNode.right = new Node<>(childItem);
                    System.out.println("(ADD) parentItem: " + parentItem +"\t-\t"+"childITem: "+ childItem + "\t(RIGHT OF LEFT NODE - Sibling)");
                    ret = true;
                }
            }
        }
        else{
            // Root eger bos ise ilk gelen degerler root ve root un child i olarak belirlenir.
            root.data = parentItem;
            root.left = new Node<>(childItem);
            System.out.println("(ADD) parentItem: " + parentItem +"\t(ROOT NODE)\t-\t"+"childITem: "+ childItem + "\t(LEFT NODE OF ROOT)");
            ret = true;
        }
        return ret;
    }
    /**
     * Binary Tree yapisini General Tree olarak level level olarak search etmeye yarayan metoddur.
     * @param node Level level olarak search edilecek Node.
     * @param parentItem Hangi parent a gore arama yapacagini belirten parametre.
     * @param print Bu parametre, eger True ise sadece Traverse islemi yapar ve uzerinden gectigi degerleri ekrana basar.
     * False ise parentItem parametresine gore arama yapar.
     * @return Search yapılan parentItem degeri eger agac yapisinda var ise bulundugu Node dondurulur.
     */
    private Node<E> levelOrderSearch(Node<E> node, E parentItem, boolean print){
        Node<E> ret = null;
        Node<E> temp = null;

        if(node == null)
            return null;

        Queue<Node> q =new LinkedList<>();
        q.add(node);
        while(true)
        {
            int nodeCount = q.size();
            if(nodeCount == 0)
                break;

            while(nodeCount > 0)
            {
                temp = q.peek();
                if(print)
                    System.out.print(temp.data + " ");
                q.remove();
                if(temp.left != null){
                    q.add(temp.left);
                    Node<E> temp1 = temp.left;
                    while(temp1.right != null){
                        q.add(temp1.right);
                        temp1 = temp1.right;
                    }
                }
                nodeCount--;
                if(temp.data == parentItem)
                    ret = temp;
            }
        }
        return ret;
    }

    /**
     * Binary Tree yapisini General Tree olarak post order traverse olarak search etmeye yarayan metoddur.
     * @param node Post order traverse ile search edilecek Node.
     * @param parentItem Hangi parent a gore arama yapacagini belirten parametre.
     * @return Search yapılan parentItem degeri eger agac yapisinda var ise bulundugu Node dondurulur.
     */
    private Node<E> postOrderSearch(Node<E> node, E parentItem)
    {
        Node<E> retNode = null;
        Node<E> retNode2= null;
        if (node == null)
            return null;
        else if(node.data == parentItem)
            return node;

        retNode = postOrderSearch(node.left, parentItem);

        retNode2 = postOrderSearch(node.right, parentItem);

        if(retNode == null && retNode2 == null)
            return null;
        else if(retNode != null && retNode2 == null)
            return retNode;
        else
            return retNode2;
    }

    /**
     * Perform a preorder traversal.
     * @param node The local root
     * @param depth The depth
     * @param sb The string buffer to save the output
     */
    @Override
    protected void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("  ");
        }
        if (node == null) {
            sb.append("null\n");
        }
        else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth , sb);
        }
    }
    /**
     * Pre Order Traverse olarak gezinip ekrana hangi node lardan gectigini gosterir
     * @param node Traverse isleminde root node undan baslanarak uzerinden gectigimiz node degerleri.
     * @param depth Derinlik degerinin ne kadar oldugunu alan parametre
     * @param sb Traverse edilen node degerlerinin string olarak Call by referance parametresi.
     */
    private void printPreOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append(" ");
        }
        if(node != null){
            sb.append(node.toString());
            sb.append(" ");
            printPreOrderTraverse(node.right, depth , sb);
            printPreOrderTraverse(node.left, depth , sb);
        }
    }
    /**
     * Post Order Traverse olarak gezinerek hangi node degerleri uzerinden gectigini ekrana basar.
     * @param node Traverse isleminde root node undan baslanarak uzerinden gectigimiz node degerleri.
     */
    private void printPostorderTraverse(Node<E> node){
        if(node == null)
            return;
        printPostorderTraverse(node.left);
        System.out.print(node.data + " ");
        printPostorderTraverse(node.right);
    }
    /**
     * Level Order Traverse olarak ekrana cikti verir.
     */
    public void printLevelOrder(){
        System.out.println("\nLevel Order Traverse");
        levelOrderSearch(this.root, (E) this.root.data, true);
        System.out.println();
    }
    /**
     * Post Order Traverse olarak ekrana cikti verir.
     */
    public void printPostorder()  {
        System.out.println("\nPost Order Traverse");
        printPostorderTraverse(this.root);
        System.out.println();

    }
    /**
     * Pre Order Traverse olarak ekrana cikti verir.
     */
    public void printPreOrderTraverse()  {
        System.out.println("\nPre Order Traverse");
        StringBuilder sb = new StringBuilder();
        printPreOrderTraverse(this.root, 1, sb);
        System.out.println(sb);

    }
}
