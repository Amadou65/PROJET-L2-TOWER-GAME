package game;

import java.util.*;

public abstract class Board {
    // Les cellules du plateau
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
     * méthode qui affiche la grille au joueur
     * 
     * @return la grille sous forme de chaîne de caractères
     */
    public String display() {
        int rows = grid.length;
        int cols = grid[0].length;
        String s = "";

        // 1. En-tête des colonnes (0 1 2 3 ... 9 0 1...)
        s += "   "; // Espaces pour décaler par rapport aux index de lignes
        for (int j = 0; j < cols; j++) {
            s += (j % 10) + " ";
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
     * méthode qui retourne la hauteur de la grille
     * 
     * @return la hauteur
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * méthode qui retourne la largeur de la grille
     * 
     * @return la largeur
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * méthode qui place un ballon dans une cellule spécifique
     */
    public void putBallon(Balloon ball, Cell cell) {
        cell.addBalloon(ball);
    }

    /**
     * méthode qui définit les cellules comme étant le chemin
     */
    public void setCellAsPath() {
    }

    /**
     * méthode qui retourne la cellule à une position donnée
     * 
     * @param pos la position recherchée
     */
    public Cell getCell(Position pos) {
        return this.grid[pos.getX()][pos.getY()];
    }

    /**
     * méthode qui ajoute une tour sur le plateau
     * 
     * @param t la tour à ajouter
     */
    public void addTower(Tower t, Cell cell) {
        cell.addTower(t);
        tower_list.add(t);
    }

    /**
     * méthode qui retire une tour du plateau
     */
    public void removeTower(Tower t, Cell cell) {
        cell.removeTower(t);
        tower_list.remove(t);
    }

    /**
     * méthode qui applique visuellement le chemin sur la grille
     */
    public abstract void applyPathToGrid(List<Position> positions);

    /**
     * méthode qui retourne le chemin sous forme de liste de positions
     */
    public abstract List<Position> path();

    /**
     * méthode qui retourne tous les ballons dans la portée d'une tour
     */
    public List<Balloon> getBallonsInRange(Position towerPos, int scope){
        List<Balloon> inRange = new ArrayList<>();

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                // Calcul de la distance entre la tour et chaque case
                double dist = Math.sqrt(
                        Math.pow(i - towerPos.getX(), 2)
                      + Math.pow(j - towerPos.getY(), 2));

                if (dist <= scope) {
                    inRange.addAll(grid[i][j].getBallons());
                }
            }
        }

        return inRange;
    }

    /**
     * méthode qui calcule la distance réelle (euclidienne)
     * entre une tour et un ballon
     */
    public double calculateDistance(Position towerPos, Balloon b) {
        double dx = b.getX() - towerPos.getX();
        double dy = b.getY() - towerPos.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * méthode qui retourne tous les ballons présents sur le plateau
     */
    public List<Balloon> getAllBallons() {
        List<Balloon> ballons = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                ballons.addAll(grid[i][j].getBallons());
            }
        }
        return ballons;
    }

    /**
     * méthode qui sélectionne le meilleur ballon à cibler pour une tour
     * le ballon choisi est celui qui est le plus avancé dans le chemin
     */
    public Balloon targetBalloon(Tower t, Position towerPos) {
        Balloon bestTarget = null;
        double maxProgress = -1.0;

        List<Balloon> actif = getAllBallons();

        for (Balloon b : actif) {
            // 1. calcul de la distance réelle
            double dist = calculateDistance(towerPos, b);

            // 2. vérification de la portée de la tour
            if (dist <= t.scope) {

                // 3. sélection du ballon le plus avancé
                if (b.getDistance() > maxProgress) {
                    maxProgress = b.getDistance();
                    bestTarget = b;
                }
            }
        }
        return bestTarget;
    }
}
