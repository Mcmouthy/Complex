import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by yhaffner on 15/03/17.
 */
public class GrapheTest {
    private Graphe grapheConnexe;
    private Graphe grapheNonConnexe;

    @Before
    public void b4() {
        grapheConnexe = new Graphe();

        Sommet a = new Sommet();
        grapheConnexe.addSommet(a);
        Sommet b = new Sommet();
        grapheConnexe.addSommet(b);
        Sommet c = new Sommet();
        grapheConnexe.addSommet(c);
        Sommet d = new Sommet();
        grapheConnexe.addSommet(d);
        a.addVoisin(b, 2);
        b.addVoisin(d, 7);
        a.addVoisin(c, 4);
        d.addVoisin(c, 8);
        b.addVoisin(c, 5);


        grapheNonConnexe = new Graphe();

        Sommet s1 = new Sommet();
        grapheNonConnexe.addSommet(s1);
        Sommet s2 = new Sommet();
        grapheNonConnexe.addSommet(s2);
        Sommet s3 = new Sommet();
        grapheNonConnexe.addSommet(s3);
        Sommet s4 = new Sommet();
        grapheNonConnexe.addSommet(s4);
        s1.addVoisin(s2, 2);
        s2.addVoisin(s4, 5);
    }

    @Test
    public void testConnexe() {
        Assert.assertTrue(grapheConnexe.isConnexe());
        Assert.assertFalse(grapheNonConnexe.isConnexe());
    }
}