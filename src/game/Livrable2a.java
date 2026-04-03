package game;
import java.util.*;
import game.board.RandomBoard;
import game.exeptions.NegativeValueException;
import game.exeptions.ZeroValueException;

public class Livrable2a {
    public static void main(String[] args) throws ZeroValueException, NegativeValueException  {

        System.out.println("Livrable 2a: A");

        // Creation et affichage du board
        int height = args.length > 0 ? Integer.parseInt(args[0]) : 5;
        int width = args.length > 1 ? Integer.parseInt(args[1]) : 5;
        int nbBalloons = args.length > 2 ? Integer.parseInt(args[2]) : 5;

        Board board = new RandomBoard(height, width);

        List<Balloon> balloons = new ArrayList<>();

        List<Position> path = board.path();

        board.applyPathToGrid(path);

        System.out.println(board.display());
        System.out.println("path start at: " + path.get(0) + " and end at: " + path.get(path.size() - 1));

        // On obtient le chemin et on cree des ballons
        for(int i =0; i < nbBalloons; i++){
            int randomLevel = (int) (Math.random() * 3) + 1;
            Balloon balloon = new Balloon(randomLevel, path);
            System.out.println("Balloon created with level: " + balloon.getLevel());
            balloons.add(balloon);
        }

        // On ajoute les ballons dans une liste et on lance le jeu


        // On lance le jeu
        GameEngine gameEngine = new GameEngine(balloons, board);
        gameEngine.game();
    }
}