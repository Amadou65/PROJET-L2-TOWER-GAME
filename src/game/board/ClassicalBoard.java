package game.board;
import java.util.*;
import game.Position;
import game.Board;

public class ClassicalBoard extends Board {
    public ClassicalBoard(int height, int width) {
        super(height, width);
    }

    /* Returns the list of all boundary cells */
    public List<Position> getPointsBound(){
        List<Position> points = new ArrayList<>();
        // on ajoute les points des bords du haut et du bas
        for (int i = 0; i < this.getWidth(); i++){
            points.add(new Position(i, 0));
            points.add(new Position(i, (this.getHeight() - 1)));
        }
        // on ajoute les points des bords de gauche et de droite mais sans les coins deja ajoutes
        for (int j = 1; j < this.getHeight() - 1; j++){
            points.add(new Position(0, j));
            points.add(new Position((this.getWidth() - 1), j));
        }

        return points;
    }

    /* Returns a random path from a boundary point */
    public List<Position> randomPath(){
        
        List<Position> path = new ArrayList<>();

        // on choisit un point de depart aleatoire
        List<Position> boundaryPoints = this.getPointsBound();
        Position start = boundaryPoints.get((int) (Math.random() * boundaryPoints.size()));

        // on construit le chemin en fonction du point de depart
        if(start.getX() == 0){
            for(int i = 0; i < this.getWidth(); i++){
                path.add(new Position(i, start.getY()));
            }
        }
        else if(start.getX() == (this.getWidth() - 1)){
            for(int i = this.getWidth() - 1; i >= 0; i--){
                path.add(new Position(i, start.getY()));
            }
        }
        else if(start.getY() == 0){
            for(int j = 0; j < this.getHeight(); j++){
                path.add(new Position(start.getX(), j));
            }
        }
        else{
            for(int j = this.getHeight() - 1; j >= 0; j--){
                path.add(new Position(start.getX(), j));
            }
        }

        return path;
    }



}
