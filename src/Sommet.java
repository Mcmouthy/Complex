import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yhaffner on 08/03/17.
 */
public class Sommet {
    List<Arete> voisins;
    boolean marque;
    int minDistance;

    public Sommet(){
        voisins = new ArrayList<>();
        marque = false;
    }

    public void addVoisin(Sommet s,int distance){
        Arete arete = new Arete(this,s,distance);
        voisins.add(arete);
        s.voisins.add(arete);
    }
}
