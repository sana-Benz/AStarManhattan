import java.util.*;
/**
 * Résout le problème de fuite du prisonnier dans un labyrinthe avec propagation du feu.
 */
public class LabyrintheSolver {

    /**
     * Classe interne pour représenter une position dans le labyrinthe.
     */
    static class Position {
        int row, col;
        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    // Déplacements permis (N, S, E, O)
    private static final int[][] DIRECTIONS = {
            {-1, 0}, // vers le haut
            { 1, 0}, // vers le bas
            { 0,-1}, // vers la gauche
            { 0, 1}  // vers la droite
    };


    /**
     * Résout le labyrinthe en déterminant si le prisonnier peut s'échapper avant le feu.
     *
     * @param grid Grille représentant le labyrinthe.
     * @return true si le prisonnier peut s'échapper, false sinon.
     */
    public static boolean solveLabyrinthe(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        Position prisoner = null;
        Position exit = null;
        Queue<Position> fireQueue = new LinkedList<>();

        // Localiser les positions initiales (prisonnier, sortie, feu)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char c = grid[i][j];
                if (c == 'D') {
                    prisoner = new Position(i, j);
                } else if (c == 'S') {
                    exit = new Position(i, j);
                } else if (c == 'F') {
                    fireQueue.add(new Position(i, j));
                }
            }
        }

        // 1) Calcul du temps d’arrivée du feu
        int[][] fireTime = propagateFire(grid, fireQueue);

        // 2) Tentative de fuite du prisonnier via A* (avec heuristique Manhattan)
        return movePrisoner(grid, prisoner, exit, fireTime);
    }

    /**
     * Calcule le temps d’arrivée du feu dans chaque cellule libre, par un BFS multi-source
     * (les sources étant les cases contenant déjà 'F').
     */
    private static int[][] propagateFire(char[][] grid, Queue<Position> initialFires) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Tableau de temps d’arrivée du feu, initialisé à l'infini
        int[][] fireTime = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(fireTime[i], Integer.MAX_VALUE);
        }

        // File pour la propagation BFS
        Queue<Position> queue = new LinkedList<>();

        // Les cellules déjà en feu sont à t=0
        while (!initialFires.isEmpty()) {
            Position f = initialFires.poll();
            fireTime[f.row][f.col] = 0;
            queue.offer(f);
        }

        // BFS pour propager le feu
        while (!queue.isEmpty()) {
            Position fire = queue.poll();
            int currentTime = fireTime[fire.row][fire.col];

            for (int[] dir : DIRECTIONS) {
                int nr = fire.row + dir[0];
                int nc = fire.col + dir[1];

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols
                        && grid[nr][nc] != '#'  // pas de mur
                        && currentTime + 1 < fireTime[nr][nc]) {
                    fireTime[nr][nc] = currentTime + 1;
                    queue.offer(new Position(nr, nc));
                }
            }
        }

        return fireTime;
    }

    /**
     * Déplacement du prisonnier en utilisant une approche BFS avec heuristique.
     *
     * @param grid Grille représentant le labyrinthe.
     * @param prisoner Position initiale du prisonnier.
     * @param exit Position de la sortie.
     * @param fireTime Temps d'arrivée du feu dans chaque cellule.
     * @return true si le prisonnier peut atteindre la sortie, false sinon.
     */
    private static boolean movePrisoner(char[][] grid,
                                        Position prisoner,
                                        Position exit,
                                        int[][] fireTime) {
        int rows = grid.length;
        int cols = grid[0].length;

        // dist[r][c] : le coût (temps) minimal pour atteindre la case (r, c)
        int[][] dist = new int[rows][cols];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // PriorityQueue pour le A*, triée par f(n) = g(n) + h(n)
        // où g(n) = dist (le temps déjà écoulé), et h(n) = heuristique Manhattan
        PriorityQueue<Position> openSet = new PriorityQueue<>(
                Comparator.comparingInt(p -> dist[p.row][p.col] + manhattanCost(p, exit))
        );

        // Initialisation
        dist[prisoner.row][prisoner.col] = 0;
        openSet.offer(prisoner);

        // A*
        while (!openSet.isEmpty()) {
            Position current = openSet.poll();

            // Si on atteint la sortie, c’est gagné
            if (current.row == exit.row && current.col == exit.col) {
                return true;
            }

            int currentTime = dist[current.row][current.col];

            // Parcourir les voisins orthogonaux
            for (int[] dir : DIRECTIONS) {
                int nr = current.row + dir[0];
                int nc = current.col + dir[1];

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols
                        && grid[nr][nc] != '#') { // pas un mur
                    int newTime = currentTime + 1;

                    // On ne doit pas arriver en même temps ou après le feu
                    if (newTime < fireTime[nr][nc] && newTime < dist[nr][nc]) {
                        dist[nr][nc] = newTime;
                        openSet.offer(new Position(nr, nc));
                    }
                }
            }
        }

        // Pas de chemin possible avant le feu
        return false;
    }

    /**
     * Calcule la distance de Manhattan entre deux positions.
     *
     * @param p Position actuelle.
     * @param goal Position cible.
     * @return Distance de Manhattan entre les deux positions.
     */
    private static int manhattanCost(Position p, Position goal) {
        return Math.abs(p.row - goal.row) + Math.abs(p.col - goal.col);
    }
}
