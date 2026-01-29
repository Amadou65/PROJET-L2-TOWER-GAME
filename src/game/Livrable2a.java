package game;
import java.util.*;
import game.board.RandomBoard;

public class Livrable2a {
    public static void main(String[] args) {

        int height = args.length > 0 ? Integer.parseInt(args[0]) : 6;
        int width = args.length > 1 ? Integer.parseInt(args[1]) : 11;
        RandomBoard rb = new RandomBoard(height, width);
        int nbBalloons = args.length > 2 ? Integer.parseInt(args[2]) : 10;

        List<Balloon> balloons = new ArrayList<>();

        for (int i = 0; i < nbBalloons; i++) {
            balloons.add(new Balloon(1));
        }

        GameEngine ge = new GameEngine(new ArrayList<>(balloons), rb.path(), rb);
        ge.game();

    }
}
