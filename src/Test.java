import java.io.IOException;

/**
 * Created by dylan on 29/03/17.
 */
public class Test {
    public static void main(String[] args){
        try {
            Graphe g=Graphe.initGraphe("src/graph.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
