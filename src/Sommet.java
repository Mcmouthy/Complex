import java.util.ArrayList;
import java.util.List;

/**
 * Created by yhaffner on 08/03/17.
 */
public class Sommet {
    String nom;
    List<Arete> voisins;
    boolean marque;
    int minDistance;

    public Sommet(String nom) {
        this.nom=nom;
        voisins = new ArrayList<>();
        marque = false;
        minDistance=Graphe.INFINI;
    }

    public void addVoisin(Sommet s, int distance) {
        Arete arete = new Arete(this, s, distance);
        voisins.add(arete);
        s.voisins.add(arete);
    }
}
