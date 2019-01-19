
package hw4;

import java.util.ArrayList;

/**
 * Helper Class
 * Bir cok veriyi bir node icinde tutan sinif.
 * @author Yunus Cevik
 * @param <E> Generic Type
 */
public class MultiArgs<E extends Comparable> {
    private ArrayList<E> argsList;
    protected static int DIMENSION;

    /**
     * Constructor'in aldigi parametrelere gore bir node icinde arrayList tutarak birden fazla deger barindirilmistir.
     * @param dimensionValue Node' un icindeki listenin boyutunu belirler.
     * @param args Bircok liste degerinin girildigi parametre
     * @throws Exception Constructor' a verilen dimension degeri ile listenin elemanlari esit olmadigi taktirde hata mesaji firlatilir.
     */
    public MultiArgs(int dimensionValue, E... args) throws Exception{
        if(args.length != dimensionValue)
            throw new Exception("The entered values should be same with dimension value.");
        else{
            DIMENSION = dimensionValue;
            argsList = new ArrayList<>();
            for(int i = 0; i < dimensionValue; i++)
                argsList.add(args[i]);
        }
            
    }
    
    public E getArgs(int index){
        return argsList.get(index);
    }

    @Override
    public String toString() {
        String ret = "(";
        int i;
        for(i = 0; i < DIMENSION; i++){
            ret += getArgs(i).toString();
            if(i < DIMENSION-1)
                ret += ",";
        }
        ret += ")";
        return ret;
    }

    /**
     * Node'larin gosterdigi listelerin karsilastirilmasi.
     * @param o Karsilastirilacak obje
     * @return Eger objeler esit ise True, degilse False.
     */
    @Override
    public boolean equals(Object o) {
        for(int i = 0; i < DIMENSION; i++){
            if(argsList.get(i) != ((MultiArgs)o).argsList.get(i))
                return false;
        }
        return true;
    }
    
    
    
}
