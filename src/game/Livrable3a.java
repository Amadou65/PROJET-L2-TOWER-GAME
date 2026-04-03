package game;

import java.util.*;
import game.board.LeftStartRandomBoard;
import game.exeptions.NegativeValueException;
import game.exeptions.ZeroValueException;
import game.tower.typeTower.*;

/**
 * Livrable 3a: Creates a random board with a path starting from the left
 * border.
 * Places 2 towers of each type randomly, spawns the requested number of
 * balloons,
 * and logs all game events with their timestamp.
 *
 * 
 */
public class Livrable3a {

    public static void main(String[] args) throws ZeroValueException, NegativeValueException  {
        int height = args.length > 0 ? Integer.parseInt(args[0]) : 8;
        int width = args.length > 1 ? Integer.parseInt(args[1]) : 12;
        int nbBalloons = args.length > 2 ? Integer.parseInt(args[2]) : 5;

        System.out.println("=== LIVRABLE 3A ===");
        System.out.println("Plateau : " + height + "x" + width
                + " | Ballons : " + nbBalloons);

        // 1. Créer un plateau aléatoire avec chemin partant du bord gauche
        LeftStartRandomBoard board = new LeftStartRandomBoard(height, width);
        List<Position> path = board.path();
        board.applyPathToGrid(path);

        System.out.println("Chemin généré : " + path.size() + " cases");
        System.out.println("Départ : " + path.get(0) + " → Arrivée : " + path.get(path.size() - 1));
        System.out.println(board.display());

        // 2. Placer 2 tours de chaque type aléatoirement (hors chemin)
        placeTowers(board, height, width);

        // 3. Créer les ballons avec vitesses aléatoires
        List<Balloon> balloons = new ArrayList<>();
        int[] levels = { 1, 2, 4 };
        Random rng = new Random();
        for (int i = 0; i < nbBalloons; i++) {
            int randomLevel = levels[rng.nextInt(levels.length)];
            balloons.add(new Balloon(randomLevel, path));
        }

        // 4. Lancer le moteur de jeu
        GameEngine engine = new GameEngine(balloons, board);
        engine.game();
    }

    /**
     * Places 2 towers of each type on random cells that are not on the path.
     */
    private static void placeTowers(Board board, int height, int width) {
        // Collecter les cases libres (hors chemin)
        List<Position> freeCells = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Position p = new Position(i, j);
                if (!board.getCell(p).isPath()) {
                    freeCells.add(p);
                }
            }
        }
        Collections.shuffle(freeCells);

        // Place each tower directly on the board
        List<Tower> towers = createTowers(freeCells);
        for (Tower t : towers) {
            board.addTower(t, board.getCell(t.getPosition()));
            System.out.println("[SETUP] Tour " + t.getNom()
                    + " placée en (" + t.getX() + "," + t.getY() + ")");
        }
    }

    /**
     * Creates 2 instances of each of the 7 tower types at the given positions.
     */
    private static List<Tower> createTowers(List<Position> freeCells) {
        List<Tower> towers = new ArrayList<>();
        int idx = 0;
        String[][] types = {
                { "DartMonkey" }, { "DartMonkey" },
                { "BombTower" }, { "BombTower" },
                { "SniperMonkey" }, { "SniperMonkey" },
                { "SuperMonkey" }, { "SuperMonkey" },
                { "TackShooter" }, { "TackShooter" },
                { "IceTower" }, { "IceTower" },
                { "SlowdownTower" }, { "SlowdownTower" }
        };

        for (String[] type : types) {
            if (idx >= freeCells.size())
                break;
            Position pos = freeCells.get(idx++);
            Tower t = buildTower(type[0], pos);
            if (t != null)
                towers.add(t);
        }
        return towers;
    }

    private static Tower buildTower(String type, Position pos) {
        switch (type) {
            case "DartMonkey":
                return new DartMonkey("DartMonkey", pos);
            case "BombTower":
                return new BombTower("BombTower", pos);
            case "SniperMonkey":
                return new SniperMonkey("SniperMonkey", pos);
            case "SuperMonkey":
                return new SuperMonkey("SuperMonkey", pos);
            case "TackShooter":
                return new TackShooter("TackShooter", pos);
            case "IceTower":
                return new IceTower("IceTower", pos);
            case "SlowdownTower":
                return new SlowdownTower("SlowdownTower", pos);
            default:
                return null;
        }
    }
}
