package game;

import game.board.ClassicalBoard;
import game.board.LeftStartRandomBoard;
import game.listchooser.InteractiveListChooser;
import game.listchooser.ListChooser;
import game.listchooser.RandomListChooser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Livrable 6: complete game loop.
 * <p>
 * Rules handled here:
 * <ul>
 *   <li>Game runs in rounds until player has no life left.</li>
 *   <li>Before each round, player action phase is available.</li>
 *   <li>Each round launches at least 15 balloons.</li>
 *   <li>Supports board mode A (single random left-start path) and
 *       board mode B (multiple straight paths).</li>
 *   <li>Supports interactive and random choice modes.</li>
 * </ul>
 */
public final class Livrable6 {
    private static final int MIN_BALLOONS_PER_ROUND = 15;
    private static final int MAX_BALLOONS_PER_ROUND = 45;
    private static final int BALLOON_GROWTH_STEP = 2;
    private static final int MAX_PATH_GENERATION_ATTEMPTS = 500;
    private static final int MAX_RANDOM_ACTIONS_PER_ROUND = 12;

    private Livrable6() {
    }

    /**
     * Supported board modes for final delivery.
     */
    public enum BoardMode {
        A,
        B
    }

    /**
     * Supported choice modes for final delivery.
     */
    public enum ChoiceMode {
        INTERACTIVE,
        RANDOM
    }

    /**
     * Launches the complete game loop for a given board/choice mode.
     *
     * @param launcherName displayed in usage/help messages
     * @param boardMode board mode (A or B)
     * @param choiceMode choice mode (interactive or random)
     * @param args CLI args
     * @throws Exception if board creation fails
     */
    public static void run(String launcherName, BoardMode boardMode, ChoiceMode choiceMode, String[] args)
            throws Exception {
        if (!isValidArgCount(boardMode, args)) {
            printUsage(launcherName, boardMode);
            return;
        }

        int width;
        int height;
        int nbChemins;
        try {
            width = parseStrictPositive(args[0], "largeur");
            height = parseStrictPositive(args[1], "hauteur");
            nbChemins = boardMode == BoardMode.B ? parseStrictPositive(args[2], "nbChemins") : 1;
            validateDimensions(width, height);
            validateRequestedPaths(boardMode, width, height, nbChemins);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            printUsage(launcherName, boardMode);
            return;
        }

        Board board;
        List<List<Position>> allPaths;

        try {
            if (boardMode == BoardMode.A) {
                BoardWithPath generated = generateValidLeftStartBoard(height, width);
                board = generated.board;
                allPaths = new ArrayList<>();
                allPaths.add(generated.path);
            } else {
                ClassicalBoard classicalBoard = new ClassicalBoard(height, width);
                allPaths = generateDistinctStraightPaths(classicalBoard, nbChemins);
                for (List<Position> p : allPaths) {
                    classicalBoard.applyPathToGrid(p);
                }
                board = classicalBoard;
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            printUsage(launcherName, boardMode);
            return;
        }

        ListChooser<Object> chooser = buildChooser(choiceMode);
        Player player = new Player();
        Journal journal = player.getJournal();
        Random random = new Random();

        System.out.println("========================================");
        System.out.println("         LIVRABLE 6 - DEBUT JEU        ");
        System.out.println("========================================");
        System.out.println("Mode plateau   : " + boardMode);
        System.out.println("Mode choix     : " + choiceMode);
        System.out.println("Dimensions     : " + width + "x" + height);
        if (boardMode == BoardMode.B) {
            System.out.println("Nb chemins     : " + allPaths.size());
        }
        printPathSummary(allPaths, boardMode);
        System.out.println("Crédits init   : " + player.getCredits());
        System.out.println("Vies initiales : " + player.getHealth());
        System.out.println(board.display());

        int manche = 1;
        int manchesJouees = 0;
        while (player.isAlife()) {
            System.out.println("\n========================================");
            System.out.println("              MANCHE " + manche);
            System.out.println("========================================");

            Livrable5.displayTowerEvolutions(board);
            int maxActions = choiceMode == ChoiceMode.RANDOM ? MAX_RANDOM_ACTIONS_PER_ROUND : Integer.MAX_VALUE;
            Livrable5.playerActionPhase(board, player, chooser, height, width, maxActions);

            int balloonsThisRound = computeBalloonCount(manche);
            List<Balloon> reserve = buildReserve(allPaths, balloonsThisRound, manche, random);

            System.out.println("[MANCHE " + manche + "] Ballons lancés : " + balloonsThisRound);
            GameEngine engine = new GameEngine(reserve, board, player);
            engine.game();
            manchesJouees++;

            if (!player.isAlife()) {
                break;
            }

            System.out.println("[MANCHE " + manche + "] Fin de manche | Vies: "
                    + player.getHealth() + " | Crédits: " + player.getCredits());
            manche++;
        }

        System.out.println("\n========================================");
        System.out.println("             GAME OVER");
        System.out.println("========================================");
        System.out.println("Manches jouées        : " + manchesJouees);
        System.out.println("Vies restantes        : " + player.getHealth());
        System.out.println("Crédits finaux        : " + player.getCredits());
        System.out.println("Ballons détruits      : " + journal.getBalloonsDestroyed());
        System.out.println("Crédits gagnés        : " + journal.getTotalCreditsGained());
        System.out.println("Crédits dépensés      : " + journal.getTotalCreditsSpent());
        System.out.println("Évolutions achetées   : " + journal.getUpgradesPurchased());
    }

    private static boolean isValidArgCount(BoardMode boardMode, String[] args) {
        if (boardMode == BoardMode.A) {
            return args.length == 2;
        }
        return args.length == 3;
    }

    private static int parseStrictPositive(String raw, String label) {
        try {
            int value = Integer.parseInt(raw);
            if (value <= 0) {
                throw new IllegalArgumentException("Argument invalide: " + label + " doit être > 0.");
            }
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Argument invalide: " + label + " doit être un entier.");
        }
    }

    private static void printUsage(String launcherName, BoardMode boardMode) {
        if (boardMode == BoardMode.A) {
            System.err.println("Usage: java " + launcherName + " <largeur> <hauteur>");
        } else {
            System.err.println("Usage: java " + launcherName + " <largeur> <hauteur> <nbChemins>");
        }
    }

    @SuppressWarnings("unchecked")
    private static ListChooser<Object> buildChooser(ChoiceMode choiceMode) {
        if (choiceMode == ChoiceMode.INTERACTIVE) {
            return (ListChooser<Object>) new InteractiveListChooser<>();
        }
        return (ListChooser<Object>) new RandomListChooser<>();
    }

    private static int computeBalloonCount(int manche) {
        int extra = Math.max(0, (manche - 1) / BALLOON_GROWTH_STEP);
        return Math.min(MAX_BALLOONS_PER_ROUND, MIN_BALLOONS_PER_ROUND + extra);
    }

    private static List<Balloon> buildReserve(List<List<Position>> allPaths, int count, int manche, Random random) {
        List<Balloon> reserve = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Position> path = allPaths.get(random.nextInt(allPaths.size()));
            int level = pickBalloonLevel(manche, random);
            reserve.add(new Balloon(level, path));
        }
        return reserve;
    }

    private static int pickBalloonLevel(int manche, Random random) {
        int[] levelPool;
        if (manche <= 4) {
            levelPool = new int[] {1, 1, 1, 2};
        } else if (manche <= 10) {
            levelPool = new int[] {1, 2, 2, 4};
        } else {
            levelPool = new int[] {2, 4, 4, 4};
        }
        return levelPool[random.nextInt(levelPool.length)];
    }

    private static List<List<Position>> generateDistinctStraightPaths(ClassicalBoard board, int nbChemins) {
        List<List<Position>> paths = new ArrayList<>();
        Set<String> pathKeys = new HashSet<>();
        int attempts = 0;

        while (paths.size() < nbChemins && attempts < MAX_PATH_GENERATION_ATTEMPTS) {
            attempts++;
            List<Position> candidate = board.generateNewPath();
            if (candidate.isEmpty()) {
                continue;
            }

            String pathKey = canonicalPathKey(candidate);

            if (!pathKeys.contains(pathKey)) {
                paths.add(candidate);
                pathKeys.add(pathKey);
            }
        }

        if (paths.size() < nbChemins) {
            throw new IllegalArgumentException(
                    "Impossible de générer " + nbChemins + " chemins distincts sur ce plateau.");
        }

        return paths;
    }

    private static BoardWithPath generateValidLeftStartBoard(int height, int width) throws Exception {
        for (int attempts = 0; attempts < MAX_PATH_GENERATION_ATTEMPTS; attempts++) {
            LeftStartRandomBoard leftBoard = new LeftStartRandomBoard(height, width);
            List<Position> path = leftBoard.path();
            if (isValidLeftStartPath(path, height, width)) {
                leftBoard.applyPathToGrid(path);
                return new BoardWithPath(leftBoard, path);
            }
        }

        throw new IllegalArgumentException("Impossible de générer un chemin valide pour le plateau A.");
    }

    private static boolean isValidLeftStartPath(List<Position> path, int height, int width) {
        if (path == null || path.size() < 2) {
            return false;
        }

        Position start = path.get(0);
        Position end = path.get(path.size() - 1);
        boolean startsOnLeft = start.getY() == 0;
        boolean endsOnEdge = end.getX() == 0 || end.getX() == height - 1
                || end.getY() == 0 || end.getY() == width - 1;
        boolean leavesLeftSide = end.getY() != 0;
        return startsOnLeft && endsOnEdge && leavesLeftSide;
    }

    private static String canonicalPathKey(List<Position> path) {
        Position first = path.get(0);
        Position last = path.get(path.size() - 1);
        String firstKey = first.getX() + "," + first.getY();
        String lastKey = last.getX() + "," + last.getY();
        if (firstKey.compareTo(lastKey) <= 0) {
            return firstKey + "-" + lastKey;
        }
        return lastKey + "-" + firstKey;
    }

    private static void printPathSummary(List<List<Position>> allPaths, BoardMode boardMode) {
        if (boardMode == BoardMode.A && !allPaths.isEmpty()) {
            List<Position> p = allPaths.get(0);
            System.out.println("Chemin A       : " + p.get(0) + " -> " + p.get(p.size() - 1)
                    + " (" + p.size() + " cases)");
            return;
        }

        for (int i = 0; i < allPaths.size(); i++) {
            List<Position> p = allPaths.get(i);
            System.out.println("Chemin B-" + (i + 1) + "     : " + p.get(0) + " -> "
                    + p.get(p.size() - 1) + " (" + p.size() + " cases)");
        }
    }

    private static void validateRequestedPaths(BoardMode boardMode, int width, int height, int nbChemins) {
        if (boardMode != BoardMode.B) {
            return;
        }

        int maxDistinctPaths = width + height;
        if (nbChemins > maxDistinctPaths) {
            throw new IllegalArgumentException(
                    "nbChemins trop grand: maximum possible sur ce plateau = " + maxDistinctPaths + ".");
        }
    }

    private static void validateDimensions(int width, int height) {
        if (width < 2 || height < 2) {
            throw new IllegalArgumentException("Dimensions invalides: largeur et hauteur doivent être >= 2.");
        }
    }

    private static final class BoardWithPath {
        private final Board board;
        private final List<Position> path;

        private BoardWithPath(Board board, List<Position> path) {
            this.board = board;
            this.path = path;
        }
    }
}
