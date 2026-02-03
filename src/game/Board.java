package game;

import java.util.*;

public abstract class Board {
    // The cell of the Board
    //
    protected int height;
    protected int width;
    protected ArrayList<Tower> tower_list;
    protected Cell[][] grid;

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new Cell[height][width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.grid[i][j] = new Cell(new Position(i, j));
            }
        }

        this.tower_list = new ArrayList<>();

    }

    /**
     * methode that show the grid to the player
     * 
     * @return the grid
     */
    public String display() {
        int rows = grid.length;
        int cols = grid[0].length;
        String s = "";

        // 1. En-tête des colonnes (0 1 2 3 ... 9 0 1...)
        s += "   "; // Espaces pour décaler par rapport aux index de lignes
        for (int j = 0; j < cols; j++) {
            s += (j % 10) + " "; // On affiche le chiffre des unités
        }
        s += "\n";

        // 2. Création de la ligne de séparation "+-+-+-+"
        String ligneSeparation = "  +";
        for (int j = 0; j < cols; j++) {
            ligneSeparation += "-+";
        }
        ligneSeparation += "\n";

        // 3. Construction du corps du plateau
        for (int i = 0; i < rows; i++) {
            s += ligneSeparation;
            s += i + " "; // Index de la ligne sur le côté
            for (int j = 0; j < cols; j++) {
                s += "|" + grid[i][j].getSymbol();
            }
            s += "|\n";
        }

        // 4. Dernière ligne de fermeture
        s += ligneSeparation;

        return s;
    }

    /**
     * methode that return the height of the grid
     * 
     * @return the height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * methode that return the width of the grid
     * 
     * @return the width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * methode that put a ballloon at a scepecific cell
     */
    public void putBallon(Balloon ball, Cell cell) {
    };

    /**
     * methode that set all the cell in the with the positin as path
     */
    public void setCellAsPath() {
    };

    /**
     * method that give the cell at the position
     * 
     * @param pos
     */
    public Cell getCell(Position pos) {
        return this.grid[pos.getX()][pos.getY()];
    }

    /**
     * methods that add a tower to the board
     * 
     * @param Tower
     */
    public void addTower(Tower t, Cell cell) {
        cell.addTower(t);
    }

    /**
     * methode that remove a tower in the board
     */
    public void removeTower(Tower t, Cell cell) {
        cell.removeTower(t);
    }

    /**
     * methode that apply the path to the grid visually
     */
    public abstract void applyPathToGrid(List<Position> positions);

    public abstract List<Position> path();
}