package game.board;
import game.Board;
import game.Position;     
import java.util.Random;
import java.util.ArrayList;

public class RandomBoard extends Board {
    private Random randomNumber = new Random();
    public RandomBoard() {
        super();
    }
    /**
     * method that create the list of the starting positions
     * @return list of a possible start
     */
 public ArrayList<Position> creerListeDepart() {
        ArrayList<Position> liste_depart = new ArrayList<>();
        int h = this.getHeight();
        int w = this.getWidth();

        // Correction : on parcourt tout le périmètre sans oublier de cases
        for (int i = 0; i < h; i++) {
            liste_depart.add(new Position(i, 0));
            liste_depart.add(new Position(i, w - 1));
        }
        for (int j = 1; j < w - 1; j++) {
            liste_depart.add(new Position(0, j));
            liste_depart.add(new Position(h - 1, j)); // Utilisation de h-1 au lieu de h-2
        }
        return liste_depart;
    }

    /**
     * method that gives a random path for the grid
     * @return the list of the path
     */
public ArrayList<Position> path() {
        ArrayList<Position> liste_depart = this.creerListeDepart();
        Position pos_depart = liste_depart.get(randomNumber.nextInt(liste_depart.size()));
        
        ArrayList<Position> path = new ArrayList<>();
        path.add(pos_depart);
        
        boolean found = false;
        while (!found) {
            Position current = path.get(path.size() - 1);

            // Condition de victoire : longueur > 12, sur un bord, et pas le même côté
            if (path.size() >= 12 && isEdge(current) && !isSameSide(current, pos_depart)) {
                found = true;
            } else {
                ArrayList<Position> possible_path = this.nextPositions(current);
                
                // On filtre pour ne pas repasser sur une case déjà visitée (éviter les cercles)
                ArrayList<Position> valid_choices = new ArrayList<>();
                for (Position p : possible_path) {
                    boolean alreadyVisited = false;
                    for (Position visited : path) {
                        if (p.equals(visited)) { alreadyVisited = true; break; }
                    }
                    if (!alreadyVisited) { valid_choices.add(p); }
                }

                if (valid_choices.isEmpty()) {
                    // Si on est bloqué (cul-de-sac), on recommence tout le chemin
                    path.clear();
                    pos_depart = liste_depart.get(randomNumber.nextInt(liste_depart.size()));
                    path.add(pos_depart);
                } else {
                    // On choisit une position au hasard parmi les choix valides
                    path.add(valid_choices.get(randomNumber.nextInt(valid_choices.size())));
                }
            }
        }
        return path;
    }
     /*
     * methode that give the next cell
     * 
     * @param the current position
     * @return boolean
     */
    public ArrayList<Position> nextPositions(Position pos) {
        ArrayList<Position> nextPoList = new ArrayList<>();
        int x = pos.getX();
        int y = pos.getY();

        // On vérifie les 4 directions et on les ajoute si elles sont dans la grille
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] d : directions) {
            int nx = x + d[0];
            int ny = y + d[1];
            if (nx >= 0 && nx < getHeight() && ny >= 0 && ny < getWidth()) {
                nextPoList.add(new Position(nx, ny));
            }
        }
        return nextPoList;
    }
    /**
     * this method look that the path doesn't make circle
     * @param path the list of the path
     * @return boolean that say if the path is doing a circle
     */
    
public boolean isDoingCircle(ArrayList<Position> path) {
        // Une boucle simple pour vérifier si le dernier élément ajouté existe déjà avant
        Position last = path.get(path.size() - 1);
        for (int i = 0; i < path.size() - 1; i++) {
            if (path.get(i).equals(last)) return true;
        }
        return false;
    }
    /*
    /**
     * methode that say if a cell is the bord of the grid
     * 
     * @param the position of the cell
     * @return boolean
     */
    public boolean isEdge(Position pos) {
        return pos.getX() == 0 || pos.getX() == this.getHeight() - 1 || 
               pos.getY() == 0 || pos.getY() == this.getWidth() - 1;
    }

    /**
     * methode that say if a cell is not at the same side as an another
     * 
     * @param two positions
     * @return boolean
     */
    public boolean isSameSide(Position pos1, Position pos2) {
        if (pos1.getX() == pos2.getX() || pos1.getY() == pos2.getY()) {
            return true;
        }
        return false;
    }

}