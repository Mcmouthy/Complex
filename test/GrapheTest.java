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

        Sommet a = new Sommet("A");
        grapheConnexe.addSommet(a);
        Sommet b = new Sommet("B");
        grapheConnexe.addSommet(b);
        Sommet c = new Sommet("C");
        grapheConnexe.addSommet(c);
        Sommet d = new Sommet("D");
        grapheConnexe.addSommet(d);
        a.addVoisin(b, 2);
        b.addVoisin(d, 7);
        a.addVoisin(c, 4);
        d.addVoisin(c, 8);
        b.addVoisin(c, 5);


        grapheNonConnexe = new Graphe();

        Sommet s1 = new Sommet("Gibraltar");
        grapheNonConnexe.addSommet(s1);
        Sommet s2 = new Sommet("Simigo");
        grapheNonConnexe.addSommet(s2);
        Sommet s3 = new Sommet("Nartali");
        grapheNonConnexe.addSommet(s3);
        Sommet s4 = new Sommet("Portibus");
        grapheNonConnexe.addSommet(s4);
        s1.addVoisin(s2, 2);
        s2.addVoisin(s4, 5);
    }

    @Test
    public void testConnexe() {
        Assert.assertTrue(grapheConnexe.isConnexe());
        Assert.assertFalse(grapheNonConnexe.isConnexe());
    }

    @Test
    public void testAlgoDij() {
        int[] tab=grapheConnexe.algoDijsktra(grapheConnexe.sommets.get(0));
        int[] b={0,2,4,9};
        Assert.assertArrayEquals(tab,b);
    }

    @Test
    public void testAlgoDijFinal(){
        int[][] tab=grapheConnexe.calculPlusCourtesDistances();
        int[][] b={{0,2,4,9},
                   {2,0,5,7},

                   {4,5,0,8},
                   {9,7,8,0}
        };
        Assert.assertArrayEquals(tab,b);
    }


}