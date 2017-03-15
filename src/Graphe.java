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
    int[][] distance;

    public Graphe() {
        sommets = new ArrayList<>();
        nbSommets = 0;
        nbAretes = 0;
        distance=new int[nbSommets][nbSommets];
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

    public void calculPlusCourtesDistances(Sommet source){
        source.marque=true;
        for (Arete s:source.voisins) {

        }
    }

    public boolean isConnexe(){
        for(Sommet s:sommets) s.marque = false;
        parcoursMarquageRecur(sommets.get(0));
        for(Sommet s:sommets) if(!s.marque) return false;
        return true;
    }

    public void parcoursMarquageRecur(Sommet s){
        s.marque = true;
        for(Arete a:s.voisins){
            Sommet s2 = a.getOtherSideSommet(s);
            if(!s2.marque) parcoursMarquageRecur(s2);
        }
    }
}
