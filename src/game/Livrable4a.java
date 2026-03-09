package game;

import java.util.*;
import game.board.LeftStartRandomBoard;

/**
 * Livrable4a : scénario A du Livrable 4.
 * <p>
 * Crée un plateau aléatoire dont le chemin part obligatoirement du bord gauche,
 * place des tours via le joueur (avec déduction des crédits), achète des
 * évolutions sur les tours éligibles, puis lance la manche.
 * </p>
 *
 * <p>
 * Usage :
 * </p>
 * 
 * <pre>
 * java -jar livrable4a.jar &lt;hauteur&gt; &lt;largeur&gt; &lt;nbBallons&gt;
 * </pre>
 */
public class Livrable4a extends Livrable4 {

    public static void main(String[] args) {
        int height = args.length > 0 ? Integer.parseInt(args[0]) : 8;
        int width = args.length > 1 ? Integer.parseInt(args[1]) : 12;
        int nbBallons = args.length > 2 ? Integer.parseInt(args[2]) : 5;

        System.out.println("=== LIVRABLE 4A ===");
        System.out.println("Plateau aléatoire : " + height + "x" + width
                + " | Ballons : " + nbBallons);
            // --- PHASE 1 : Plateau aléatoire avec départ depuis le bord gauche ---
        LeftStartRandomBoard board = new LeftStartRandomBoard(height, width);
        List<Position> path = board.path();
        board.applyPathToGrid(path);

        System.out.println("Chemin généré : " + path.size() + " cases");
        System.out.println("Départ : " + path.get(0)
                + " → Arrivée : " + path.get(path.size() - 1));
        System.out.println(board.display());