package game;

import java.util.*;
import game.board.ClassicalBoard;
import game.tower.typeTower.*;

/**
 * Livrable 3b: Creates a random board with N straight-line paths.
 * Places one balloon per path (random speeds), 2 towers of each type randomly,
 * and logs all game events with their timestamp.
 *
 *
 */
public class Livrable3b {

    public static void main(String[] args) {
        int height = args.length > 0 ? Integer.parseInt(args[0]) : 8;
        int width = args.length > 1 ? Integer.parseInt(args[1]) : 12;
        int nbPaths = args.length > 2 ? Integer.parseInt(args[2]) : 3;

        System.out.println("=== LIVRABLE 3B ===");
        System.out.println("Plateau : " + height + "x" + width
                + " | Chemins rectilignes : " + nbPaths);

        ClassicalBoard board = new ClassicalBoard(height, width);

        // 1. Générer N chemins rectilignes distincts + 1 ballon par chemin
        List<Balloon> balloons = new ArrayList<>();
        int[] levels = { 1, 2, 4 };
        Random rng = new Random();

        int genCount = 0;
        int attempts = 0;
        List<List<Position>> usedPaths = new ArrayList<>();

        while (genCount < nbPaths && attempts < 100) {
            attempts++;
            List<Position> path = board.generateNewPath();

            // Vérifier que ce chemin n'est pas déjà utilisé (même départ)
            boolean duplicate = false;
            for (List<Position> used : usedPaths) {
                if (used.get(0).equals(path.get(0))) {
                    duplicate = true;
                    break;
                }
            }
            if (duplicate)
                continue;

            usedPaths.add(path);
            board.applyPathToGrid(path);

            int randomLevel = levels[rng.nextInt(levels.length)];
            Balloon b = new Balloon(randomLevel, path);
            balloons.add(b);
            genCount++;
            System.out.println("Chemin " + genCount + " : "
                    + path.get(0) + " → " + path.get(path.size() - 1)
                    + " | Ballon niveau " + randomLevel);
        }

        System.out.println(board.display());

        // 2. Placer 2 tours de chaque type aléatoirement
        placeTowers(board, height, width);

        // 3. Lancer le moteur de jeu
        GameEngine engine = new GameEngine(balloons, board);
        engine.game();
    }

    /**
     * Places 2 towers of each type on random free cells (not on any path).
     */
    private static void placeTowers(Board board, int height, int width) {
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

        int idx = 0;
        String[] towerTypes = {
                "DartMonkey", "DartMonkey",
                "BombTower", "BombTower",
                "SniperMonkey", "SniperMonkey",
                "SuperMonkey", "SuperMonkey",
                "TackShooter", "TackShooter",
                "IceTower", "IceTower",
                "SlowdownTower", "SlowdownTower"
        };

        for (String type : towerTypes) {
            if (idx >= freeCells.size())
                break;
            Position pos = freeCells.get(idx++);
            Tower t = buildTower(type, pos);
            if (t != null) {
                board.addTower(t, board.getCell(pos));
                System.out.println("[SETUP] Tour " + t.getNom()
                        + " placée en (" + t.getX() + "," + t.getY() + ")");
            }
        }
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
