package game.board;
import java.util.Random;
import java.util.ArrayList;
public class RandomBoard extends Board{
    public RandomBoard{
        super();
    }
    /**
     * methode that give a random path for the grid
     * 
     */
    public void path(){
        // le chemin doit commencer sur une case du bord au hasard
        ArrayList<Position> liste_depart = new ArrayList<>();
        for (int i = 0; i < this.getHeight(); i++){
            liste_depart.add(new Position(i, 0));
            liste_depart.add(new Position(i, this.getWidth() - 1));
            for (int j = 0; j < this.getWidth(); j++){
                liste_depart.add(new Position(0, j));
                liste_depart.add(new Position(this.getHeight() - 1, j)); 
            }
        }
        // maintenant qu'on a la liste , on peut choisir une postion au hasard pour debuter
        //creer un objet random
        Random randomNumber = new Random();
        // on va choisir entre 0 et la taile de la lste liste_depart un nombre aleeatoire et on va prendre la position a ce nombre la
        int size_liste_depart = liste_depart.size();
        int index_pos = randomNumber.nextInt(size_liste_depart);
        Position pos_depart = liste_depart.get(index_pos);
        // Maintenant qu'on connait la position de depart on peut commencer le parcours du chemin
        boolean found = false;
        // on va creer la liste du chemin
        ArrayList<Position> path = new ArrayList<>();
        // on va creer aussi la liste des chemins vistées
        ArrayList<Position> visited = new ArrayList<>();
        // on ajoute la position de depart a la liste des chemins visités
        visited.add(pos_depart);
        while (! found){
            // si la longueur de la liste visitée n'est pas superieur a 12 et n'est pas sur un bord

        }
    }

    /**
     * methode that give the next cell
     * @param the current position
     * @return list of the potential next position
     */
    public ArrayList<Position> nextPositions(Position pos){
        ArrayList<Position> nextPoList = new ArrayList<>();
        int x = pos.getX();
        int y = pos.getY();
        // si on est sur un bord en bas
        if (x == this.getHeight()){
            // si on est en bas sur le bord gauche alors on peut aller a droite et en haut
            if (y == this.getWidth()){
                nextPoList.add(new Position(x, y + 1));
                nextPoList.add(new Position(x - 1, y));
            }
            // si on est en bas sur le bord droit alors on peut aller a gauche et en haut
            if (y == 0){
                nextPoList.add(new Position(x, y -1));
                nextPoList.add(new Position(x - 1, y));
            }
        }
        // si on est au bord en haut 
        else if (x == 0){
            // si on est en haut sur le bord gauche alors on peut aller a droite ou en bas
            if (y ==0){
                nextPoList.add(new Position(x, y + 1));
                nextPoList.add(new Position(x + 1, y));
            }
            // si on est en haut sur le bord droit alors on peut aller a gauche ou en bas
            if (y == this.getWidth()){
                nextPoList.add(new Position(x, y -1));
                nextPoList.add(new Position(x +1, y));
            }
        }
        else{
            // si on est pas sur un bord on peut aller dans toutes les directions 
            nextPoList.add(new Position(x + 1, y));
            nextPoList.add(new Position(x -1, y));
            nextPoList.add(new Position(x , y + 1));
            nextPoList.add(new Position(x , y - 1));
            
        }
        return nextPoList;
    }

    /**
     * methode that say if a cell is the bord of the grid
     * @param the position of the cell
     * @return boolean
     */
    public boolean isedge(Position pos){
        // si c 'est sur un bord on retourne vraie
        if (pos.getX()== this.getHeight() || pos.getY() == this.getWidth()){
            return true;
        }
    }
    
}