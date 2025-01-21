import java.io.IOException;
import java.util.List;
/**
 * Point d'entrée principal pour exécuter le programme.
 */
public class Main {

    public static void main(String[] args) {
        try {
            // Lire les labyrinthes
            List<Labyrinthe> labyrinthes = LabyrintheReader.readLabyrinthes(args);

            // Traiter chaque labyrinthe
            int caseNumber = 1;
            for (Labyrinthe labyrinthe : labyrinthes) {
                System.out.println("Labyrinthe " + caseNumber + ":");
                labyrinthe.display();

                // On teste avec Manhattan
                boolean result = LabyrintheSolver.solveLabyrinthe(labyrinthe.getGrid());
                System.out.println(result ? "Y" : "N");

                caseNumber++;
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture des données : " + e.getMessage());
        }
    }
}
