
package game;
import java.util.*;
import game.board.ClassicalBoard;
import game.exeptions.NegativeValueException;
import game.exeptions.ZeroValueException;

public class Livrable2b {

    public static void main(String[] args) throws ZeroValueException, NegativeValueException  {

        System.out.println("Livrable 2b: B");

        // Creation et affichage du board
        int height = args.length > 1 ? Integer.parseInt(args[0]) : 5;
        int width = args.length > 1 ? Integer.parseInt(args[1]) : 5;
        int nbBalloons = args.length > 2 ? Integer.parseInt(args[2]) : 5;
        
        Board board = new ClassicalBoard(height, width);
        ArrayList<Balloon> balloons = new ArrayList<>();
        ArrayList<ArrayList<Position>> pathes = new ArrayList<>();

        for(int i = 0; i < nbBalloons; i++){
            int randomLevel = (int) (Math.random() * 3) + 1;
            List<Position> path = board.path();
            pathes.add((ArrayList<Position>) path);
            balloons.add(new Balloon(randomLevel, path));
            System.out.println("Balloon created with level: " + balloons.get(i).getLevel());
            System.out.println("With path start at" + path.get(0)+ " and end at " + path.get(path.size()-1));
        }

        for(ArrayList<Position> bp : pathes){
            board.applyPathToGrid(bp);
        }

        
        System.out.println(board.display());

        GameEngine gameEngine = new GameEngine(balloons, board);
        gameEngine.game();

        System.out.println(board.display());
    }
}
