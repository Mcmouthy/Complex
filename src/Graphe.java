import java.io.*;
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

    public int[][] calculPlusCourtesDistances() {
        int[][] distance = new int[sommets.size()][sommets.size()];
        for (int i =0;i<sommets.size();i++){
            distance[i]=algoDijsktra(sommets.get(i));
        }
        return distance;
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
            Sommet fantome = new Sommet("");
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
            s.marque=false;
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

    public boolean isGrapheComplet(){
        for(Sommet s : sommets){
            if (s.voisins.size()!=sommets.size()-1){
                return false;
            }
        }
        return true;
    }

    public int traceCircuit(Sommet source){ //cet algo ne fonctionne pas si un sommet a deux voisins non marqués
                                            // avec la même distance

        int distance=0;
        Sommet source2=source;
        source2.marque=true;
        Sommet next=source2.voisins.get(0).getOtherSideSommet(source2);
        while(!checkAllMarked()){
            int mindist=INFINI;
            for(int i=0;i<source2.voisins.size();i++) {
                if (source2.voisins.get(i).distance < mindist && !source2.voisins.get(i).getOtherSideSommet(source2).marque) {
                    mindist = source2.voisins.get(i).distance;
                    next = source2.voisins.get(i).getOtherSideSommet(source2);
                }
            }
            distance+=mindist;
            source2=next;
            source2.marque=true;
            next=source2.voisins.get(0).getOtherSideSommet(source2);
        }
        for (int j=0;j<source2.voisins.size();j++){
            if (source2.voisins.get(j).getOtherSideSommet(source2).equals(source)){
                distance+=source2.voisins.get(j).distance;
            }
        }
        return distance;
    }

    public static Graphe initGraphe(String filename) throws IOException {
        Graphe g = new Graphe();
        BufferedReader bf= new BufferedReader(new FileReader(filename));
        String line=bf.readLine();
        String[] tab=line.split(",");
        for (int i=0;i<tab.length;i++){
            g.addSommet(new Sommet(tab[i]));
        }
        line=bf.readLine();
        while(!line.equals("")){
            String[] tab2=line.split(":");
            String[] arrete=tab2[1].split(",");
            for (int j=0;j<tab2.length;j++){
                g.sommets.get(j).addVoisin(g.getBynom(arrete[j].charAt(0)),Integer.parseInt(arrete[j].substring(1)));
            }
        }
        return g;
    }

    private Sommet getBynom(char c) {
        for(Sommet s:sommets){
            if (s.nom.equals(""+c))return s;
        }
        return null;
    }
}
