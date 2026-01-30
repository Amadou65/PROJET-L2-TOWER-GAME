package game;
import java.util.*;
import game.board.RandomBoard;

public class Livrable2a {
    public static void main(String[] args) {

        System.out.println("Livrable 2a: A");

        // Creation et affichage du board
        Board board = new RandomBoard(5, 5);
        System.out.println(board.display());

        // On obtient le chemin et on cree des ballons
        List<Position> path = board.path();
        Balloon balloon = new Balloon(1, path);
        Balloon balloon2 = new Balloon(2, path);
        Balloon balloon3 = new Balloon(3, path);
        Balloon balloon4 = new Balloon(1, path);

        System.out.println("Balloon created with level: " + balloon.getLevel());
        System.out.println("Balloon2 created with level: " + balloon2.getLevel());
        System.out.println("Balloon3 created with level: " + balloon3.getLevel());
        System.out.println("Balloon4 created with level: " + balloon4.getLevel());

        // On ajoute les ballons dans une liste et on lance le jeu
        List<Balloon> balloons = new ArrayList<>();
        balloons.add(balloon);
        balloons.add(balloon2);
        balloons.add(balloon3); 
        balloons.add(balloon4);

        // On lance le jeu
        GameEngine gameEngine = new GameEngine(balloons, path, board);
        gameEngine.game();
    }
}
