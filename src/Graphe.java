import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by yhaffner on 08/03/17.
 */
public class Graphe {

    final static int INFINI = 65535;
    List<Sommet> sommets;
    int nbSommets;
    int nbAretes;
    int[][] distance;

    public Graphe() {
        sommets = new ArrayList<>();
        nbSommets = 0;
        nbAretes = 0;
        distance = new int[nbSommets][nbSommets];
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

    public void calculPlusCourtesDistances(Sommet source) {

    }


    public int[] algoDijsktra(Sommet source){
        source.minDistance=0;
        for (Arete a:source.voisins){
            Sommet v=a.getOtherSideSommet(source);
            if (source.minDistance+a.distance<v.minDistance && !v.marque){
                v.minDistance=source.minDistance+a.distance;
            }
        }
        source.marque=true;
        while(!checkAllMarked()) {
            Sommet fantome = new Sommet();
            for (Sommet s : sommets) {
                if (s.minDistance < fantome.minDistance && !s.marque) {
                    fantome = s;
                }
            }

            for (Arete ar : fantome.voisins) {
                Sommet som = ar.getOtherSideSommet(fantome);
                if (fantome.minDistance + ar.distance < som.minDistance && !som.marque) {
                    som.minDistance = fantome.minDistance + ar.distance;
                }
            }
            fantome.marque = true;
        }

        int[] distanceNoeud= new int[sommets.size()];
        for (int i=0; i<sommets.size();i++){
            distanceNoeud[i]=sommets.get(i).minDistance;
        }
        reinitValue();
        return distanceNoeud;
    }

    public boolean checkAllMarked() { // methode qui retourne true si tous les noeuds sont marqués
        for (Sommet s:sommets){
            if (!s.marque){
                return false;
            }
        }
        return true;
    }

    public void reinitValue(){ // methode qui remet tous noeuds a une mindistance de +infini
        for (Sommet s:sommets){
            s.minDistance=INFINI;
        }
    }

    public boolean isConnexe() {
        for (Sommet s : sommets) s.marque = false;
        parcoursMarquageRecur(sommets.get(0));
        for (Sommet s : sommets) if (!s.marque) return false;
        return true;
    }

    public void parcoursMarquageRecur(Sommet s) {
        s.marque = true;
        for (Arete a : s.voisins) {
            Sommet s2 = a.getOtherSideSommet(s);
            if (!s2.marque) parcoursMarquageRecur(s2);
        }
    }
}
