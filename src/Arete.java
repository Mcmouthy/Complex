/**
 * Created by yhaffner on 08/03/17.
 */
public class Arete {
    Sommet sommet1;
    Sommet sommet2;

    public Arete(Sommet sommet1,Sommet sommet2){
        this.sommet1 = sommet1;
        this.sommet2 = sommet2;
    }

    public Sommet getOtherSideSommet(Sommet s){
        return s.equals(sommet1) ? sommet2 : sommet1;
    }
}
