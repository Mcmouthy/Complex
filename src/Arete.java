/**
 * Created by yhaffner on 08/03/17.
 */
public class Arete {
    Sommet sommet1;
    Sommet sommet2;
    int distance;

    public Arete(Sommet sommet1,Sommet sommet2, int distance){
        this.sommet1 = sommet1;
        this.sommet2 = sommet2;
        this.distance = distance;
    }

    public Sommet getOtherSideSommet(Sommet s){
        return s.equals(sommet1) ? sommet2 : sommet1;
    }
}
