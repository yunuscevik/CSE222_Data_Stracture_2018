package hw6.Q3;

/**
 * Kitabın Source Code'larinin yani sira Binary Tree alan constructor, Helper metotlar
 * (addAllBinaryTreeToAVLTree,isAVLTree, findMaxDepth, findMinDepth) ve delete metodu bana aittir.
 * @param <E> Generic Type
 */
public class AVLTree < E extends Comparable < E >> extends BinarySearchTreeWithRotate < E > {

    public AVLTree() {}
    
    /**
     * Binary Tree alan Constructor
     * @param binaryTree Binary Tree alan Constructor parametresi 
     * @throws Exception Eger binary Tree AVL Tree ise kopyalama yapilir, degilse Exception firlatilir.
     */
    public  AVLTree(BinaryTree<E> binaryTree) throws Exception{
        boolean result = isAVLTree(binaryTree);
        if (result) {
            addAllBinaryTreeToAVLTree(binaryTree,this);
            System.out.println("Binary Tree is a AVL Tree.\n");
        }
        else 
            throw new Exception("Binary Tree is not AVL Tree.\n");
    }
    /**
     * Binary Tree icindekileri AVL Tree ye ekler
     * @param binaryTree AVL Tree ye eklenecek Binary Tree
     * @param avl Binary Tree degerlerinin eklenecegi AVL Tree
     */
    private void addAllBinaryTreeToAVLTree(BinaryTree<E> binaryTree,AVLTree<E> avl) {
        if (binaryTree != null) {
            avl.add(binaryTree.getData());
            addAllBinaryTreeToAVLTree(binaryTree.getLeftSubtree(), avl);
            addAllBinaryTreeToAVLTree(binaryTree.getRightSubtree(), avl);
        }
    }
    /**
     * AVL Tree olup olmadigina bakar.
     * @param binaryTreeRoot AVL Tree kontrolu icin gelen Binary Tree 
     * @return Eger AVL Tree ise True, degilse False.
     */
    private boolean isAVLTree(BinaryTree<E> binaryTreeRoot) {
        return (findMaxDepth(binaryTreeRoot) - findMinDepth(binaryTreeRoot)) <= 1;
    }
    /**
     * Maksimum derinligi bulur.
     * @param binaryTreeRoot Binary Tree icindeki maksimum derinligi bulmak icin parametre
     * @return Eger null bir deger ise 0, degil ise recursive cagri ile maksimum derinlikleri birer artırarak bulur.
     */
    private int findMaxDepth(BinaryTree<E> binaryTreeRoot) {
        if (binaryTreeRoot == null)
            return 0;
        return 1 + Math.max(findMaxDepth(binaryTreeRoot.getLeftSubtree()), findMaxDepth(binaryTreeRoot.getRightSubtree()));
    }
    /**
     * Minimum derinligi bulur.
     * @param binaryTreeRoot Binary Tree icindeki minimum derinligi bulmak icin parametre
     * @return Eger null bir deger ise 0, degil ise recursive cagri ile minimum derinlikleri birer artırarak bulur.
     */
    private int findMinDepth(BinaryTree<E> binaryTreeRoot) {
        if (binaryTreeRoot == null)
            return 0;
        return 1 + Math.min(findMinDepth(binaryTreeRoot.getLeftSubtree()), findMinDepth(binaryTreeRoot.getRightSubtree()));
    }
    
    
    /** Flag to indicate that height of tree has increased. */
    private boolean increase;

    /** Flag to indicate that height of tree has decreased */
    private boolean decrease;

    private static class AVLNode<E> extends Node<E> {

        /** Constant to indicate left-heavy */
        public static final int LEFT_HEAVY = -1;
        /** Constant to indicate balanced */
        public static final int BALANCED = 0;
        /** Constant to indicate right-heavy */
        public static final int RIGHT_HEAVY = 1;
        /** balance is right subtree height - left subtree height */
        private int balance;

        // Methods
        /**
         * Construct a node with the given item as the data field.
         * @param item The data field
         */
        public AVLNode(E item) {
            super(item);
            balance = BALANCED;
        }


        /**
         * Return a string representation of this object.
         * The balance value is appended to the contents.
         * @return String representation of this object
         */
        @Override
        public String toString() {
            return balance + ": " + super.toString();
        }
    }

    // Methods
    /**
     * add starter method.
     * @pre the item to insert implements the Comparable interface.
     * @param item The item being inserted.
     * @return true if the object is inserted; false
     *         if the object already exists in the tree
     * @throws ClassCastException if item is not Comparable
     */
    @Override
    public boolean add(E item) {
        increase = false;
        if(item == null)
            return increase;
        root = add((AVLNode<E>) root, item);
        return addReturn;
    }

    /**
     * Recursive add method. Inserts the given object into the tree.
     * @post addReturn is set true if the item is inserted,
     *       false if the item is already in the tree.
     * @param localRoot The local root of the subtree
     * @param item The object to be inserted
     * @return The new local root of the subtree with the item
     *         inserted
     */
    private AVLNode < E > add(AVLNode < E > localRoot, E item) {
        if (localRoot == null) {
            addReturn = true;
            increase = true;
            return new AVLNode <> (item);
        }
        if (item.compareTo(localRoot.data) == 0) {
            // Item is already in the tree.
            increase = false;
            addReturn = false;
            return localRoot;
        }

        else if (item.compareTo(localRoot.data) < 0) {
            // item < data
            localRoot.left = add( (AVLNode < E > ) localRoot.left, item);

            if (increase) {
                decrementBalance(localRoot);
                if (localRoot.balance < AVLNode.LEFT_HEAVY) {
                    increase = false;
                    return rebalanceLeft(localRoot);
                }
            }
            return localRoot; // Rebalance not needed.
        }
        else {
            // item > data
            localRoot.right = add( (AVLNode < E > ) localRoot.right, item);
            if (increase) {
                incrementBalance(localRoot);
                if (localRoot.balance > AVLNode.RIGHT_HEAVY)
                    return rebalanceRight(localRoot);
                else
                    return localRoot;
            }
            else
                return localRoot;
        }

    }
    /**
     * AVL Tree uzerinden silme islemi yapilir.
     * @param item Silinecek deger.
     * @return AVL Tree uzerinden silinen deger geri donderilir.
     */
    @Override
    public E delete(E item) {
        decrease = false;
        if(item == null)
            return null;
        root = delete( (AVLNode<E>) root, item);
        return deleteReturn;
    }

    /**
     * Silmek islemi icin helper metottur.
     * @param localRoot Silme isleminin yapilacagi AVL Tree
     * @param item Silinecek deger
     * @return AVLNode
     */
    private AVLNode<E> delete(AVLNode<E> localRoot, E item) {
        //Tree is empty or item is not found
        if (localRoot == null) {
            deleteReturn = null;
            return localRoot;
        }
        //Item is found the case like be binarySearchTree's delete method.
        if (item.compareTo(localRoot.data) == 0) {
            deleteReturn = localRoot.data;
            // item is at local root.
            if (localRoot.left == null) {
                decrease = true;
                return (AVLNode<E>) localRoot.right;
            }
            else if (localRoot.right == null) {
                decrease = true;
                return (AVLNode<E>) localRoot.left;
            }
            else {
                if (localRoot.left.right == null) {
                    localRoot.data = localRoot.left.data;
                    localRoot.left = localRoot.left.left;
                    return localRoot;
                }
                else {
                    localRoot.data = findLargestChild((AVLNode<E>) localRoot.left);
                    return localRoot;
                }
            }
        }
        //Item smallar than localRoot's data
        else if (item.compareTo(localRoot.data) < 0) {
            localRoot.left = delete( (AVLNode<E>) localRoot.left, item);
            if (decrease) {
                incrementBalance(localRoot);
                if (localRoot.balance > AVLNode.RIGHT_HEAVY)
                    return rebalanceRightForDelete( (AVLNode < E > ) localRoot);
                else
                    return localRoot;
            }
            else
                return localRoot;
        }
        //localRoot's smallar than Item data
        else {
            localRoot.right = delete( (AVLNode < E > ) localRoot.right, item);
            if (decrease) {
                decrementBalance(localRoot);
                if (localRoot.balance < AVLNode.LEFT_HEAVY)
                    return rebalanceLeftForDelete(localRoot);
                else
                    return localRoot;
            }
            else
                return localRoot;
        }
    }
    /**
     * Silme isleminde en buyuk cocuk degerini bulmak icin kullanilan recursive calisan helper metot.
     * @param parent Silinecek sol cocuk nodlari
     * @return En buyuk cocugun verileri.
     */
    private E findLargestChild(AVLNode <E> parent) {
        if (parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            decrementBalance(parent);
            return returnValue;
        }
        else {
            E returnValue = findLargestChild( (AVLNode < E > ) parent.right);
            if ( ( (AVLNode <E> ) parent.right).balance < AVLNode.LEFT_HEAVY )
                parent.right = rebalanceLeft( (AVLNode < E > ) parent.right);
            //Largest child yer degistireceginden, yani aslinda largest child
            //location'undan silineceginden onun parent'inin balanceside
            //değişiyor. Onuda dengelemeliyiz.
            if (decrease)
                decrementBalance(parent);
            return returnValue;
        }
    }
    /**
     * Verilen node'un artan dengesi.
     * @param node Artarak dengelemek icin gonderilen AVL Node.
     */
    private void incrementBalance(AVLNode<E> node) {
        ++node.balance;
        //right heavy tree
        if (node.balance > AVLNode.BALANCED) {
            increase = true;
            decrease = false;
        }
        else {
            increase = false;
            decrease = true;
        }
    }
    /**
     * Method to decrement the balance field and to reset the value of
     * increase.
     * @pre The balance field was correct prior to an insertion [or
     *      removal,] and an item is either been added to the left[
     *      or removed from the right].
     * @post The balance is decremented and the increase flags is set
     *       to false if the overall height of this subtree has not
     *       changed.
     * @param node The AVL node whose balance is to be incremented
     */
    private void decrementBalance(AVLNode<E> node) {
        // Decrement the balance.
        node.balance--;
        if (node.balance == AVLNode.BALANCED) {
            // If now balanced, overall height has not increased.
            increase = false;
        }
    }
    /**
     * Silme islemi ile agacin sol tarafini dengede tutar.
     * @param localRoot Dengesiz agac
     * @return Yeniden dengelenen dugum dondurulur.
     */
    private AVLNode<E> rebalanceLeftForDelete(AVLNode<E> localRoot) {
        increase = false;
        decrease = true;
        AVLNode <E> leftChild = (AVLNode<E> ) localRoot.left;
        if (leftChild.balance > AVLNode.BALANCED ) {
            AVLNode<E> leftRightChild = (AVLNode<E> ) leftChild.right;

            if (leftRightChild.balance < AVLNode.BALANCED) {
                localRoot.balance = AVLNode.BALANCED;
                leftChild.balance = AVLNode.LEFT_HEAVY;
                leftRightChild.balance = AVLNode.BALANCED;
            }
            else if (leftRightChild.balance > AVLNode.BALANCED) {
                localRoot.balance = AVLNode.RIGHT_HEAVY;
                leftChild.balance = AVLNode.BALANCED;
                leftRightChild.balance = AVLNode.BALANCED;
            }
            else {
                localRoot.balance = AVLNode.BALANCED;
                leftChild.balance = AVLNode.BALANCED;
            }
            localRoot.left = rotateLeft(leftChild);
        }
        return (AVLNode<E> ) rotateRight(localRoot);
    }
    /**
     * Silme işlemi ile ağacın sağ tarafını dengede tutar.
     * @param localRoot Dengesiz agac
     * @return Yeniden dengelenen dugum dondurulur.
     */
    private AVLNode < E > rebalanceRightForDelete(AVLNode < E > localRoot) {
        increase = false;
        decrease = true;
        AVLNode < E > rightChild = (AVLNode < E > ) localRoot.right;
        if (rightChild.balance < AVLNode.BALANCED ) {
            AVLNode < E > rightLeftChild = (AVLNode < E > ) rightChild.left;

            if (rightLeftChild.balance > AVLNode.BALANCED) {
                rightChild.balance = AVLNode.RIGHT_HEAVY;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            else if (rightLeftChild.balance < AVLNode.BALANCED) {
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.LEFT_HEAVY;
            }
            else {
                rightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            localRoot.right = rotateRight(rightChild);
        }
        return (AVLNode < E > ) rotateLeft(localRoot);
    }
    
    /*<listing chapter="9" number="3">*/
    /**
     * Method to rebalance left.
     * @pre localRoot is the root of an AVL subtree that is
     *      critically left-heavy.
     * @post Balance is restored.
     * @param localRoot Root of the AVL subtree
     *        that needs rebalancing
     * @return a new localRoot
     */
    private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot) {
        // Obtain reference to left child.
        AVLNode<E> leftChild = (AVLNode<E>) localRoot.left;
        // See whether left-right heavy.
        if (leftChild.balance > AVLNode.BALANCED) {
            // Obtain reference to left-right child.
            AVLNode<E> leftRightChild = (AVLNode<E>) leftChild.right;
            // Adjust the balances to be their new values after
            // the rotations are performed.
            if (leftRightChild.balance < AVLNode.BALANCED) {
                leftChild.balance = AVLNode.LEFT_HEAVY;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            } else if (leftRightChild.balance > AVLNode.BALANCED) {
                leftChild.balance = AVLNode.BALANCED;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.RIGHT_HEAVY;
            } else {
                leftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            // Perform left rotation.
            localRoot.left = rotateLeft(leftChild);
        } else { //Left-Left case
            // In this case the leftChild (the new root)
            // and the root (new right child) will both be balanced
            // after the rotation.
            leftChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
        // Now rotate the local root right.
        return (AVLNode<E>) rotateRight(localRoot);
    }
    
    /**
     * Agacin sag tarafini dengede tutar.
     * @param localRoot Dengeli olmayan AVL Node
     * @return Yeniden dengelenen node.
     */
    private AVLNode<E> rebalanceRight(AVLNode<E> localRoot) {

        increase = false;
        decrease = true;
        AVLNode<E> rightChild = (AVLNode<E> ) localRoot.right;
        if (rightChild.balance < AVLNode.BALANCED) {
            AVLNode<E> rightLeftChild = (AVLNode<E> ) rightChild.left;

            if (rightLeftChild.balance > AVLNode.BALANCED) {
                localRoot.balance = AVLNode.LEFT_HEAVY;
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
            }
            else if (rightLeftChild.balance < AVLNode.BALANCED) {
                localRoot.balance = AVLNode.BALANCED;
                rightChild.balance = AVLNode.RIGHT_HEAVY;
                rightLeftChild.balance = AVLNode.BALANCED;
            }
            else {
                localRoot.balance = AVLNode.BALANCED;
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
            }
            localRoot.right = rotateRight(rightChild);
        }
        else {
            rightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
        return (AVLNode<E> ) rotateLeft(localRoot);

    }
}