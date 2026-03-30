package game;

import java.util.*;
import game.board.LeftStartRandomBoard;
import game.listchooser.RandomListChooser;

/**
 * Livrable5a : scénario A du Livrable 5.
 * <p>
 * Crée un plateau aléatoire dont le chemin part du bord gauche,
 * exécute la phase d'actions du joueur avec des choix aléatoires
 * (RandomListChooser), puis lance la manche.
 * </p>
 *
 * <pre>
 * java -jar livrable5a.jar &lt;hauteur&gt; &lt;largeur&gt; &lt;nbBallons&gt;
 * </pre>
 */
public class Livrable5a extends Livrable5 {

    public static void main(String[] args) {
        int height = args.length > 0 ? Integer.parseInt(args[0]) : 8;
        int width = args.length > 1 ? Integer.parseInt(args[1]) : 12;
        int nbBallons = args.length > 2 ? Integer.parseInt(args[2]) : 5;

        System.out.println("=== LIVRABLE 5A — Mode Aléatoire ===");
        System.out.println("Plateau aléatoire : " + height + "x" + width
                + " | Ballons : " + nbBallons);

        // --- PHASE 1 : Plateau aléatoire avec départ bord gauche ---
        LeftStartRandomBoard board = new LeftStartRandomBoard(height, width);
        List<Position> path = board.path();
        board.applyPathToGrid(path);
        System.out.println("Chemin généré : " + path.size() + " cases");
        System.out.println("Départ : " + path.get(0)
                + " → Arrivée : " + path.get(path.size() - 1));
        System.out.println(board.display());

        // --- PHASE 2 : Phase d'actions du joueur (choix aléatoires) ---
        Player player = new Player();
        @SuppressWarnings("rawtypes")
        RandomListChooser chooser = new RandomListChooser();
        Livrable5.playerActionPhase(board, player, chooser, height, width);