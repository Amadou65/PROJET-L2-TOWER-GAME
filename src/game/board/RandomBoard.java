package game.board;
import game.Board;
import game.Position;     
import java.util.Random;
import java.util.ArrayList;

public class RandomBoard extends Board {
    public RandomBoard() {
        super();
    }
    /**
     * method that create the list of the starting positions
     * @return list of a possible start
     */
    public ArrayList<Position> creerListeDepart() {
        ArrayList<Position> liste_depart = new ArrayList<>();
        for (int i = 0; i < this.getHeight(); i++) {
            liste_depart.add(new Position(i, 0));
            liste_depart.add(new Position(i, this.getWidth() - 1));
            
        }
        for (int j = 1; j < this.getWidth()-1; j++) {
            liste_depart.add(new Position(0, j));
            liste_depart.add(new Position(this.getHeight() - 2, j));
        }
        return liste_depart;
    }

    /**
     * method that gives a random path for the grid
     * @return the list of the path
     */
    public ArrayList<Position> path() {
        // le chemin doit commencer sur une case du board au hasard
        ArrayList<Position> liste_depart = this.creerListeDepart();
        // maintenant qu'on a la liste , on peut choisir une postion au hasard pour
        // debuter
        // creer un objet random
        Random randomNumber = new Random();
        // on va choisir entre 0 et la taile de la lste liste_depart un nombre
        // aleatoire et on va prendre la position a ce nombre la
        Position pos_depart = liste_depart.get(randomNumber.nextInt(liste_depart.size()));
        // Maintenant qu'on connait la position de depart on peut commencer le parcours
        // du chemin
        boolean found = false;
        // on va creer la liste du chemin
        ArrayList<Position> path = new ArrayList<>();
        // on ajoute la position de depart a la liste des chemins visités
        path.add(pos_depart);
        while (!found) {
            // Tant que le chemin ne fait pas de rond on peut continuer si il le fait on recommence jusqua ce qu'on obtienne un bon chemin
            if (! isDoingCircle(path)){

            
            // si la longueur de la liste visitée n'est pas superieur a 12 et n'est pas sur
            // un bord ou si la longueur est > et que on est pas sur un bord
            if (path.size() < 12 || path.size() >= 12 && ! isEdge(path.get(path.size() - 1)) || path.size() >= 12 && isEdge(path.get(path.size() - 1))
                    && !isSameSide(path.get(path.size() - 1), pos_depart) ) {
                ArrayList<Position> possible_path = this.nextPositions((path.get(path.size() - 1)));
                Random choice = new Random();
                int index_aleatoire = choice.nextInt(possible_path.size());
                // on verifie que la nouvelle case ne fait pas partie d'une case déja visitée
                for (int i = 0; i < path.size(); i++) {
                    if (possible_path.get(index_aleatoire).equals(path.get(i))) {
                        index_aleatoire = choice.nextInt(possible_path.size());
                    }

                }
                Position case_choisi = possible_path.get(index_aleatoire);

                // on ajoute cette case a liste des cases vsités
                path.add(case_choisi);
                found = false;
            }
            // on va verifier si la longueur de la liste est superieur a 12 qu'on ait sur un
            // bord et pas sur le meme bord de depart
            else if (path.size() > 12 && isEdge(path.get(path.size() - 1))
                    && !isSameSide(path.get(path.size() - 1), pos_depart)) {
                found = true;
            }

        
    
        // Quand on finit on peur retourner la liste du chemin
        
        }
        
        else{
            path = new ArrayList<>();
            path.add(pos_depart);
            found = false;
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
            // si on est en bas sur le bord gauche alors on peut aller a droite et en haut
            if (y == this.getWidth() - 1) {
                nextPoList.add(new Position(x, y + 1));
                nextPoList.add(new Position(x - 1, y));
            }
            // si on est en bas sur le bord droit alors on peut aller a gauche et en haut
            if (y == 0) {
                nextPoList.add(new Position(x, y - 1));
                nextPoList.add(new Position(x - 1, y));
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
        ArrayList<Position> nexPositions = nextPositions(path.get(path.size() - 1));
        for(int i =0; i < nexPositions.size(); i ++){
            for (int j = 0; j < path.size(); j ++){
                if (nexPositions.get(i) != path.get(j)){
                    return false;
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
        if (pos.getX() == this.getHeight() || pos.getY() == this.getWidth() - 1) {
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