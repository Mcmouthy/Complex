import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yhaffner on 08/03/17.
 */
public class Graphe {
    List<Sommet> sommets;
    int nbSommets;
    int nbAretes;

    public Graphe() {
        sommets = new ArrayList<>();
        nbSommets = 0;
        nbAretes = 0;
    }

    public void addSommet(Sommet s) {
        sommets.add(s);
        actualizeNumbers();
    }

    private void actualizeNumbers() {
        nbSommets = sommets.size();
        HashSet<Arete> aretes = new HashSet<>();
        for (Sommet s : sommets)
            for (Arete a : s.voisins)
                aretes.add(a);
        nbAretes = aretes.size();
    }


}
