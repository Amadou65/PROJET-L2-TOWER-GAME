package game;

import java.util.*;
import game.board.ClassicalBoard;

/**
 * Livrable4b : scénario B du Livrable 4.
 * <p>
 * Crée un plateau classique (chemins rectilignes), place des tours
 * via le joueur (avec déduction des crédits), achète des évolutions
 * sur les tours éligibles, puis lance la manche.
 * </p>
 *
 * <p>
 * Usage :
 * </p>
 * 
 * <pre>
 * java -jar livrable4b.jar &lt;hauteur&gt; &lt;largeur&gt; &lt;nbBallons&gt;
 * </pre>
 */
public class Livrable4b extends Livrable4 {

    public static void main(String[] args) {
        int height = args.length > 0 ? Integer.parseInt(args[0]) : 8;
        int width = args.length > 1 ? Integer.parseInt(args[1]) : 12;
        int nbBallons = args.length > 2 ? Integer.parseInt(args[2]) : 5;

        System.out.println("=== LIVRABLE 4B ===");
        System.out.println("Plateau classique : " + height + "x" + width
                + " | Ballons : " + nbBallons);