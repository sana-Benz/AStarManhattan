import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Lit les labyrinthes à partir d'un fichier ou de la console.
 */
public class LabyrintheReader {

    /**
     * Lit les labyrinthes à partir d'un fichier ou de la console.
     *
     * @param args Arguments d'entrée, contenant éventuellement le chemin du fichier.
     * @return Liste des labyrinthes lus.
     * @throws IOException Si une erreur se produit lors de la lecture du fichier.
     */
    public static List<Labyrinthe> readLabyrinthes(String[] args) throws IOException {
        List<Labyrinthe> labyrinthes = new ArrayList<>();

        if (args.length > 0) {
            // Lecture à partir d'un fichier
            String filePath = args[0];
            try (Scanner scanner = new Scanner(new File(filePath))) {
                labyrinthes = parseLabyrinthes(scanner);
            }
        } else {
            // Lecture à partir de la console
            System.out.println("Entrez les données du labyrinthe :");
            try (Scanner scanner = new Scanner(System.in)) {
                labyrinthes = parseLabyrinthes(scanner);
            }
        }

        return labyrinthes;
    }

    /**
     * Parse les labyrinthes à partir d'un Scanner.
     *
     * @param scanner Scanner pour lire les données du labyrinthe.
     * @return Liste des labyrinthes parsés.
     */
    private static List<Labyrinthe> parseLabyrinthes(Scanner scanner) {
        List<Labyrinthe> labyrinthes = new ArrayList<>();
        int T = scanner.nextInt(); // Nombre total de labyrinthes
        scanner.nextLine(); // Consommer la ligne restante

        for (int t = 0; t < T; t++) {
            int N = scanner.nextInt();
            int M = scanner.nextInt();
            scanner.nextLine(); // Consommer la ligne restante

            char[][] grid = new char[N][M];
            for (int i = 0; i < N; i++) {
                grid[i] = scanner.nextLine().toCharArray();
            }

            labyrinthes.add(new Labyrinthe(N, M, grid));
        }

        return labyrinthes;
    }
}
