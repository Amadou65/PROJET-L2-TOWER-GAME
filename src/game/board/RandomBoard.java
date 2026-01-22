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
        // si on est sur un bord en bas
        if (x == this.getHeight() - 1) {
            // si on est en bas sur le bord droit alors on peut aller a droite et en haut
            if (y == this.getWidth() - 1) {
                nextPoList.add(new Position(x, y - 1));
                nextPoList.add(new Position(x - 1, y));
            }
            // si on est en bas sur le bord gauche alors on peut aller a gauche et en haut
            if (y == 0) {
                nextPoList.add(new Position(x, y + 1));
                nextPoList.add(new Position(x - 1, y));
            }
            // si non on peut monter aller a gauche et a droite
            else{
                nextPoList.add(new Position(x, y - 1));
                nextPoList.add(new Position(x - 1, y));
                nextPoList.add(new Position(x, y + 1));
            }
        }
        // si on est au bord en haut
        else if (x == 0) {
            // si on est en haut sur le bord gauche alors on peut aller a droite ou en bas
            if (y == 0) {
                nextPoList.add(new Position(x, y + 1));
                nextPoList.add(new Position(x + 1, y));
            }
            // si on est en haut sur le bord droit alors on peut aller a gauche ou en bas
            if (y == this.getWidth() - 1) {
                nextPoList.add(new Position(x, y - 1));
                nextPoList.add(new Position(x + 1, y));
            }
            // si non on peut descendre aller a gauche et a droite
            else{
                nextPoList.add(new Position(x, y - 1));
                nextPoList.add(new Position(x + 1, y));
                nextPoList.add(new Position(x, y + 1));
            }
        // si on est sur le cote gauche
        else if (x == 0 && y != 0 && y!= getHeight() - 1){
            nextPoList.add(new Position(x + 1, y));
            nextPoList.add(new Position(x, y - 1));
            nextPoList.add(new Position(x, y + 1));

        }
        else if (y == 0 && x != 0 && x!= getWidth() - 1){
            nextPoList.add(new Position(x + 1, y));
            nextPoList.add(new Position(x - 1, y));
            nextPoList.add(new Position(x, y + 1));
        } else {
            // si on est pas sur un bord on peut aller dans toutes les directions
            nextPoList.add(new Position(x + 1, y));
            nextPoList.add(new Position(x - 1, y));
            nextPoList.add(new Position(x, y + 1));
            nextPoList.add(new Position(x, y - 1));

        }
        return nextPoList;
    }
    /**
     * this method look that the path doesn't make circle
     * @param path the list of the path
     * @return boolean that say if the path is doing a circle
     */
    
    public boolean isDoingCircle(ArrayList<Position> path){
        ArrayList<Position> nexPositions = this.nextPositions(path.get(path.size() - 1));
        for(int i =0; i < nexPositions.size(); i ++){
            for (int j = 0; j < path.size(); j ++){
           // Dans le bloc pour le bas 
                if (nexPositions.get(i) != path.get(j)){
                    return false;
                }
                else if (nexPositions.get(i).equals(path.get(j))){
                    return true;
                }
            }
        }
        return true;
    }
    /*
    /**
     * methode that say if a cell is the bord of the grid
     * 
     * @param the position of the cell
     * @return boolean
     */
    public boolean isEdge(Position pos) {
        // si c 'est sur un bord on retourne vraie
        if (pos.getX() == this.getHeight() - 1 || pos.getY() == this.getWidth() - 1) {
            return true;
        }
        else if (pos.getX() == 0 || pos.getY() == 0){
            return true;
        }
        return false;
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