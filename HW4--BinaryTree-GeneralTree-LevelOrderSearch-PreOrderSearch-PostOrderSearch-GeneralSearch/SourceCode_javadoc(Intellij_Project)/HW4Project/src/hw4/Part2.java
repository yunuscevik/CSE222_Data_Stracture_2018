
package hw4;

/**
 * Binary Tree yapisini Multi Dimension Tree olarak olusturup 
 * Search Tree interface icindeki metodlarin implementinin saglandigi bir siniftir.
 * @author Yunus Cevik
 * @param <E> Generic Type
 */
public class Part2< E extends MultiArgs> extends BinaryTree<E> implements SearchTree<E> {

    private boolean addRet;
    private E delRet;
    /**
     * Multi Dimension agac yapisina bir eleman eklemek icin kullanilan metoddur.
     * @param item Agaca eklenecek parametre degeri.
     * @return Eleman basarili olarak eklendiginde True. Ekleme islemi basarisiz ise False dondurulur.
     */
    @Override
    public boolean add(E item) {
        root = add(root, item, 0);
        return addRet;
    }
    /**
     * Recursive calisir ve aldigi elemani agacta bir Node'a ekler. Ancak Binary Tree uzerinde eklenecek node
     * sag tarafami sol tarafa mi oldugunu planeLine parametresi belirler. 
     * Her plane de Node un gösterdigi liste uzerinden item degerinin listesindeki degerler karsilastirilir.
     * Böylece ne tarafa ekleyecegi belirlenir.
     * @param node Agac uzerinde her hangi bir veri kumesi.
     * @param item Agaca eklenecek eleman.
     * @param planeLine Agac uzerinde hangi boyutun kontrolunun yapilacagini belirler.
     * @return Eklenen elemanin node'unu dondurur.
     */
    private Node<E> add(Node<E> node, E item, int planeLine) {
        if (node == null) {
            addRet = true;
            return new Node<>(item);
        }
        else if (item.getArgs(planeLine % MultiArgs.DIMENSION).compareTo(node.data.getArgs(planeLine % MultiArgs.DIMENSION)) <= 0) {
            node.left = add(node.left, item, ++planeLine);
            return node;
        }
        else {
            node.right = add(node.right, item, ++planeLine);
            return node;
        }
    }
    /**
     * Agac uzerinde belirtilen elemanin olup olmadigini kontrol eder.
     * @param target Agac uzerinde hangi elemanin olup olmadigini kontrol etmek icin verilen parametre.
     * @return Eger eleman agac uzerinde varsa True, yoksa False dondurur.
     */
    @Override
    public boolean contains(E target) {
        return find(target) != null;
    }
    /**
     * Agac uzerinde search islemi yapar ve aranan elemani bulup node' unun listesini dondurur.
     * @param target aranacak eleman degeri.
     * @return Eger elemani bulursa Node' un gosterdigi liste uzerindeki elemanlari verir. Bulamaz ise null dondurur.
     */
    @Override
    public E find(E target) {
        return find(root, target, 0);
    }
    /**
     * Recursive olarak calisir ve istenen elemani agac uzerinde arar ve node' un gosterdigi listeyi verir.
     * @param node Agac uzerinde her hangi bir veri kumesi.
     * @param target aranacak eleman degeri.
     * @param planeLine Agac uzerinde hangi boyutun kontrolunun yapilacagini belirler.
     * @return Eger elemani bulursa Node' un gosterdigi liste uzerindeki elemanlari verir. Bulamaz ise null dondurur. 
     */
    private E find(Node<E> node, E target, int planeLine) {
        if (node == null)
            return null;

        int compResult = target.getArgs(planeLine % MultiArgs.DIMENSION).compareTo(node.data.getArgs(planeLine % MultiArgs.DIMENSION));
        if (compResult == 0){
            if(target.equals(node.data))
                return node.data;
        }
        if (compResult <= 0)
            return find(node.left, target, ++planeLine);
        else
            return find(node.right, target, ++planeLine);
    }
    /**
     * Agac uzerinde silme islemi yapar ve sildigi elemenin node'unun listesini dondurur.
     * @param target Silinecek eleman degeri
     * @return Eger silinecek elemani silerse sildigi elemanin listesini dondurur. Silmezse null dondurur.
     */
    @Override
    public E delete(E target) {
        root = delete(root, target, 0);
        return delRet;
    }

    /**
     * Recursive olarak calisir. Elemani agac uzerinde arar ve buldugu zaman siler. 
     * Sildigi elemanin Node' unu dondurur. Ayrica silinen elemanin node' una bagli diger node'lar,
     * bir ustte bulunan node yapisina planeLine ile degerine gore karsilastirma yapar 
     * ve diger node'larin sag taraftami sol tarafta mi olacagi tekrardan belirlenir.
     * @param node Agac uzerinde her hangi bir veri kumesi.
     * @param item Silinecek eleman degeri
     * @param planeLine Agac uzerinde hangi boyutun kontrolunun yapilacagini belirler.
     * @return Eger silinecek elemani silerse sildigi elemanin Node'unu dondurur. Silmezse null dondurur.
     */
    private Node<E> delete(Node<E> node, E item , int planeLine) {
        if (node == null) {
            delRet = null;
            return node;
        }

        int result = item.getArgs(planeLine % MultiArgs.DIMENSION).compareTo(node.data.getArgs(planeLine % MultiArgs.DIMENSION));
        if (result == 0) {
            if(item.equals(node.data)){
                delRet = node.data;
                if (node.left == null)
                    return node.right;
                else if (node.right == null)
                    return node.left;
                else {
                    if (node.left.right == null) {
                      node.data = node.left.data;
                      node.left = node.left.left;
                      return node;
                    }
                    else {
                      node.data = findBiggestChildHelper(node.left);
                      return node;
                    }
                }
            }
        }
        if (result <= 0) {
            node.left = delete(node.left, item, ++planeLine);
            return node;
        }
        else{
            node.right = delete(node.right, item, ++planeLine);
            return node;
        }

    }
    /**
     * Agac uzerinde silme islemi yapar.
     * @param target Silinecek eleman degeri. 
     * @return Eger belirtilen eleman silinirse True, silinmez ise False dondurur.
     */
    @Override
    public boolean remove(E target) {
        return delete(target) != null;
    }
    /**
     * Silme isleminde en buyuk cocuk degerini bulmak icin kullanilan recursive calisan helper metot.
     * @param node Agac uzerinde her hangi bir veri kumesi.
     * @return Agac uzerindeki en buyuk eleman listesini verir.
     */
    private E findBiggestChildHelper(Node<E> node) {
        if (node.right.right == null) {
            E ret = node.right.data;
            node.right = node.right.left;
            return ret;
        }
        else
            return findBiggestChildHelper(node.right);

    }
    /**
     * Pre Order Traverse olarak gezinip ekrana hangi node lardan gectigini gosterir. 
     * Ayrica hangi plane de bulundugunuda belirtir.
     * @param node Traverse isleminde root node undan baslanarak uzerinden gectigimiz node degerleri.
     * @param depth Derinlik degerinin ne kadar oldugunu alan parametre
     * @param sb Traverse edilen node degerlerinin string olarak Call by referance parametresi.
     */
    @Override
    protected void preOrderTraverse(Node < E > node, int depth, StringBuilder sb) {
        int i;
        for (i = 1; i < depth; i++)
            sb.append("  ");
        if (node == null)
            sb.append("null\n");
        else {
            sb.append(node.toString());
            sb.append("\t------------------------------> ").append((char)('a'+((i-1) % MultiArgs.DIMENSION))).append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

}
