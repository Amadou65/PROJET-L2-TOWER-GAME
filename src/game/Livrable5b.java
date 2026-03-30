package game;

import java.util.*;
import game.board.ClassicalBoard;
import game.listchooser.InteractiveListChooser;

/**
 * Livrable5b : scénario B du Livrable 5.
 * <p>
 * Crée un plateau classique (chemins rectilignes), exécute la phase
 * d'actions du joueur avec des choix interactifs (InteractiveListChooser),
 * puis lance la manche.
 * </p>
 *
 * <pre>
 * java -jar livrable5b.jar &lt;hauteur&gt; &lt;largeur&gt; &lt;nbBallons&gt;
 * </pre>
 */
public class Livrable5b extends Livrable5 {

    public static void main(String[] args) {
        int height = args.length > 0 ? Integer.parseInt(args[0]) : 8;
        int width = args.length > 1 ? Integer.parseInt(args[1]) : 12;
        int nbBallons = args.length > 2 ? Integer.parseInt(args[2]) : 5;

        System.out.println("=== LIVRABLE 5B — Mode Interactif ===");
        System.out.println("Plateau classique : " + height + "x" + width
                + " | Ballons : " + nbBallons);
        // --- PHASE 1 : Plateau classique ---
        ClassicalBoard board = new ClassicalBoard(height, width);
        List<Position> path = board.path();
        board.applyPathToGrid(path);

        System.out.println("Chemin généré : " + path.size() + " cases");
        System.out.println("Départ : " + path.get(0)
                + " → Arrivée : " + path.get(path.size() - 1));
        System.out.println(board.display());
