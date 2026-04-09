package game;

import java.util.*;
import game.board.LeftStartRandomBoard;
import game.exeptions.NegativeValueException;
import game.exeptions.ZeroValueException;
import game.listchooser.RandomListChooser;

/**
 * Livrable5a : programme principal du Livrable 5 — Mode automatique.
 *
 * <p>Crée un plateau aléatoire dont le chemin part du bord gauche,
 * place 2 tours de chaque type via le mécanisme d'action (RandomListChooser),
 * puis joue 10 manches :</p>
 * <ul>
 *   <li>Manches 1–5 : application d'une évolution aléatoire via action.</li>
 *   <li>Manches 6–10 : suppression d'une évolution aléatoire via action.</li>
 * </ul>
 *
 * <pre>
 * java -jar livrable5a.jar &lt;hauteur&gt; &lt;largeur&gt; &lt;nbBallons&gt;
 * </pre>
 */
public class Livrable5a extends Livrable5 {

    public static void main(String[] args) throws ZeroValueException, NegativeValueException {
        int height    = args.length > 0 ? Integer.parseInt(args[0]) : 8;
        int width     = args.length > 1 ? Integer.parseInt(args[1]) : 12;
        int nbBallons = args.length > 2 ? Integer.parseInt(args[2]) : 5;

        if (nbBallons < 0) {
            throw new IllegalArgumentException("Le nombre de ballons ne peut pas être négatif.");
        }

        System.out.println("=== LIVRABLE 5A — Mode Automatique ===");
        System.out.println("Plateau : " + height + "x" + width
                + " | Ballons par manche : " + nbBallons);

        // --- PHASE 1 : Plateau aléatoire avec départ bord gauche ---
        LeftStartRandomBoard board = new LeftStartRandomBoard(height, width);
        List<Position> path = board.path();
        board.applyPathToGrid(path);
        System.out.println("Chemin généré : " + path.size() + " cases");
        System.out.println("Départ : " + path.get(0)
                + " → Arrivée : " + path.get(path.size() - 1));
        System.out.println(board.display());

        // --- PHASE 2 : Placement de 2 tours de chaque type via actions ---
        // 2 × 7 types = 14 tours, coût total ≈ 9 300 crédits
        Player player = new Player();
        player.setCredits(15000);
        RandomListChooser<Object> chooser = new RandomListChooser<>();
        Livrable5.placeTowersViaActions(board, player, chooser, height, width);

        System.out.println("\nPlateau après placement des tours :");
        System.out.println(board.display());

        // --- PHASE 3 : Boucle de 10 manches ---
        int[] levels = { 1, 2, 4 };
        Random rng = new Random();

        for (int manche = 1; manche <= 10; manche++) {
            System.out.println("\n========================================");
            System.out.println("   MANCHE " + manche + "/10");
            System.out.println("========================================");

            // Afficher l'état des évolutions des tours
            Livrable5.displayTowerEvolutions(board);

            // Manches 1–5 : appliquer une évolution via action
            if (manche <= 5) {
                System.out.println("\n→ Application d'une évolution (manche " + manche + "/5)");
                Livrable5.applyOneEvolutionViaAction(board, player, chooser);
            } else {
                // Manches 6–10 : retirer une évolution via action
                System.out.println("\n→ Suppression d'une évolution (manche " + manche + "/10)");
                Livrable5.removeOneEvolutionViaAction(board, player, chooser);
            }

            // Créer nbBallons ballons avec des vitesses aléatoires
            List<Balloon> reserve = new ArrayList<>();
            for (int i = 0; i < nbBallons; i++) {
                int lvl = levels[rng.nextInt(levels.length)];
                reserve.add(new Balloon(lvl, path));
            }

            // Lancer la manche — s'arrête quand tous les ballons ont fini leur parcours
            System.out.println("\n--- LANCEMENT DE LA MANCHE " + manche
                    + " (" + nbBallons + " ballons) ---");
            GameEngine engine = new GameEngine(reserve, board, player);
            engine.game();
        }

        System.out.println("\n========================================");
        System.out.println("   FIN DES 10 MANCHES");
        System.out.println("========================================");
        System.out.println("Crédits finaux : " + player.getCredits());
        System.out.println("Vies restantes : " + player.getHealth());
        Livrable5.displayTowerEvolutions(board);
    }
}
