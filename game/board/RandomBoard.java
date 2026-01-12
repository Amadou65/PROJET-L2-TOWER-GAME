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
            
        }
    }

    /**
     * methode that give the next cell
     * @param the current position
     * @return list of the potential next position
     */
    public ArrayList<Position> nextPosition(Position pos){
        
    }
}