
package game;
import java.util.*;
import game.board.ClassicalBoard;

public class Livrable2b {

    public static void main(String[] args) {

        System.out.println("Livrable 2b: B");

        // Creation et affichage du board
        int height = args.length >= 0 ? Integer.parseInt(args[0]) : 5;
        int width = args.length > 0 ? Integer.parseInt(args[1]) : 5;
        int nbBalloons = args.length > 1 ? Integer.parseInt(args[1]) : 4;
        
        Board board = new ClassicalBoard(height, width);
        ArrayList<Balloon> balloons = new ArrayList<>();
        ArrayList<ArrayList<Position>> pathes = new ArrayList<>();

        for(int i = 0; i < nbBalloons; i++){
            int randomLevel = (int) Math.random()*3;
            List<Position> path = board.path();
            pathes.add((ArrayList<Position>) path);
            balloons.add(new Balloon(randomLevel, path));
            System.out.println("Balloon created with level: " + balloons.get(i).getLevel());
        }
        board.applyPathToGrid();
        System.out.println(board.display());
        // On ajoute les ballons dans une liste et on lance le jeu
        
        // On lance le jeu
        GameEngine gameEngine = new GameEngine(balloons, pathes.get(0), board);
        gameEngine.game();
    }
}
