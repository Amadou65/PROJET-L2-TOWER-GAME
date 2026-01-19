package game.board;

import game.Position;
import java.util.Random;
import java.util.ArrayList;

public class RandomBoard extends Board {
    public RandomBoard() {
        super();
    }

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
     * 
     */
    public ArrayList<Position> path() {
        // le chemin doit commencer sur une case du board au hasard
        ArrayList<Position> liste_depart = new ArrayList<>();
        for (int i = 0; i < this.getHeight(); i++) {
            liste_depart.add(new Position(i, 0));
            liste_depart.add(new Position(i, this.getWidth() - 1));
            
        }
        for (int j = 1; j < this.getWidth()-1; j++) {
            liste_depart.add(new Position(0, j));
            liste_depart.add(new Position(this.getHeight() - 2, j));
        }
        // maintenant qu'on a la liste , on peut choisir une postion au hasard pour
        // debuter
        // creer un objet random
        Random randomNumber = new Random();
        // on va choisir entre 0 et la taile de la lste liste_depart un nombre
        // aleatoire et on va prendre la position a ce nombre la
        //int size_liste_depart = liste_depart.size();
        int index_pos = randomNumber.nextInt(liste_depart.size());
        Position pos_depart = liste_depart.get(index_pos);
        // Maintenant qu'on connait la position de depart on peut commencer le parcours
        // du chemin
        boolean found = false;
        // on va creer la liste du chemin
        ArrayList<Position> path = new ArrayList<>();
        // on va creer aussi la liste des chemins visitées
        ArrayList<Position> visited = new ArrayList<>();
        // on ajoute la position de depart a la liste des chemins visités
        visited.add(pos_depart);
        while (!found) {
            // si la longueur de la liste visitée n'est pas superieur a 12 et n'est pas sur
            // un bord
            if (path.size() < 12) {
                ArrayList<Position> possible_path = this.nextPositions((pos_depart));
                Random choice = new Random();
                int index_aleatoire = choice.nextInt(possible_path.size());
                // on verifie que la nouvelle case ne fait pas partie d'une case déja visitée
                for (int i = 0; i < visited.size(); i++) {
                    if (possible_path.get(index_aleatoire).equals(visited.get(i))) {
                        index_aleatoire = choice.nextInt(possible_path.size());
                    }

                }
                Position case_choisi = possible_path.get(index_aleatoire);

                // on ajoute cette case a liste des cases vsités
                visited.add(case_choisi);
            }
            // on va verifier si la longueur de la liste est superieur a 12 qu'on ait sur un
            // bord et pas sur le meme bord de depart
            else if (path.size() > 12 && isEdge(path.get(path.size() - 1))
                    && !isSameSide(path.get(path.size() - 1), pos_depart)) {
                found = true;
            }
            // Dans ce cas on a une liste > 12 mais on est pas sur un bord ou on est sur le
            // meme bord de depart
            else {
                ArrayList<Position> possible_path = this.nextPositions((pos_depart));
                Random choice = new Random();
                int index_aleatoire = choice.nextInt(possible_path.size());
                // on verifie que la nouvelle case ne fait pas partie d'une case déja visitée
                for (int i = 0; i < visited.size(); i++) {
                    if (possible_path.get(index_aleatoire).equals(visited.get(i))) {
                        index_aleatoire = choice.nextInt(possible_path.size());
                    }

                }
                Position case_choisi = possible_path.get(index_aleatoire);

                // on ajoute cette case a liste des cases vsités
                visited.add(case_choisi);
            }

        }
        // Quand on finit on peur retourner la liste du chemin
        return path;
    }

    /**
     * methode that give the next cell
     * 
     * @param the current position
     * @return list of the potential next position
     */
    public ArrayList<Position> nextPositions(Position pos) {
        ArrayList<Position> nextPoList = new ArrayList<>();
        int x = pos.getX();
        int y = pos.getY();
        // si on est sur un bord en bas
        if (x == this.getHeight()) {
            // si on est en bas sur le bord gauche alors on peut aller a droite et en haut
            if (y == this.getWidth()) {
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
            if (y == this.getWidth()) {
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
     * methode that say if a cell is the bord of the grid
     * 
     * @param the position of the cell
     * @return boolean
     */
    public boolean isEdge(Position pos) {
        // si c 'est sur un bord on retourne vraie
        if (pos.getX() == this.getHeight() || pos.getY() == this.getWidth()) {
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