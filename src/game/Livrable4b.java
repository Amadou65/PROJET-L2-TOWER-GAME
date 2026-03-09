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
                // --- PHASE 1 : Plateau classique (chemin rectiligne) ---
        ClassicalBoard board = new ClassicalBoard(height, width);
        List<Position> path = board.path();
        board.applyPathToGrid(path);

        System.out.println("Chemin généré : " + path.size() + " cases");
        System.out.println("Départ : " + path.get(0)
                + " → Arrivée : " + path.get(path.size() - 1));
        System.out.println(board.display());
        // --- PHASE 2 : Créer le joueur et acheter les tours ---
        Player player = new Player();
        System.out.println("\n--- ACHAT DES TOURS (crédits initiaux : "
                + player.getCredits() + ") ---");
        List<Tower> towers = Livrable4.placeTowers(board, player, height, width);
        System.out.println("Crédits restants après tours : " + player.getCredits());
        // --- PHASE 3 : Acheter des évolutions sur les tours éligibles ---
        System.out.println("\n--- ACHAT DES ÉVOLUTIONS ---");
        Livrable4.buyEvolutions(towers, player);
        System.out.println("Crédits restants après évolutions : " + player.getCredits());
        // --- PHASE 4 : Créer les ballons et lancer la manche ---
        List<Balloon> reserve = new ArrayList<>();
        int[] levels = { 1, 2, 4 };
        Random rng = new Random();
        for (int i = 0; i < nbBallons; i++) {
            int lvl = levels[rng.nextInt(levels.length)];
            reserve.add(new Balloon(lvl, path));
        }
        System.out.println("\n--- LANCEMENT DE LA MANCHE ---");
        // On passe le player existant pour conserver les stats du journal
        GameEngine engine = new GameEngine(reserve, board, player);
        engine.game();
    }
}
