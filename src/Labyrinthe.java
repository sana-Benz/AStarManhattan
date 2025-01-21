/**
 * Représente un labyrinthe avec ses dimensions et sa grille.
 */
public class Labyrinthe {

    private final int rows;
    private final int cols;
    private final char[][] grid;

    /**
     * Constructeur pour initialiser un labyrinthe avec ses dimensions et sa grille.
     *
     * @param rows Nombre de lignes du labyrinthe.
     * @param cols Nombre de colonnes du labyrinthe.
     * @param grid Grille représentant le labyrinthe.
     */
    public Labyrinthe(int rows, int cols, char[][] grid) {
        this.rows = rows;
        this.cols = cols;
        this.grid = grid;
    }

    /**
     * @return Nombre de lignes du labyrinthe.
     */
    public int getRows() {
        return rows;
    }

    /**
     * @return Nombre de colonnes du labyrinthe.
     */
    public int getCols() {
        return cols;
    }

    /**
     * @return Grille représentant le labyrinthe.
     */
    public char[][] getGrid() {
        return grid;
    }

    /**
     * Affiche le labyrinthe dans la console.
     */
    public void display() {
        System.out.println("Dimensions : " + rows + "x" + cols);
        for (char[] row : grid) {
            System.out.println(new String(row));
        }
    }
}
