package game;

import java.util.*;
import game.board.ClassicalBoard;
import game.exeptions.NegativeValueException;
import game.exeptions.ZeroValueException;
import game.exeptions.TooLongPathException;
import game.listchooser.InteractiveListChooser;

/**
 * Livrable5b : programme principal du Livrable 5 — Mode interactif.
 *
 * <p>Crée un plateau classique (chemins rectilignes), place 2 tours de chaque
 * type via le mécanisme d'action (InteractiveListChooser), puis joue 10 manches :</p>
 * <ul>
 *   <li>Manches 1–5 : l'utilisateur choisit la tour à faire évoluer via action.</li>
 *   <li>Manches 6–10 : l'utilisateur choisit l'évolution à supprimer via action.</li>
 * </ul>
 *
 * <pre>
 * java -jar livrable5b.jar &lt;hauteur&gt; &lt;largeur&gt; &lt;nbBallons&gt;
 * </pre>
 */
public class Livrable5b extends Livrable5 {

    public static void main(String[] args)
            throws ZeroValueException, NegativeValueException, TooLongPathException {
        int height    = args.length > 0 ? Integer.parseInt(args[0]) : 8;
        int width     = args.length > 1 ? Integer.parseInt(args[1]) : 12;
        int nbBallons = args.length > 2 ? Integer.parseInt(args[2]) : 5;

        if (nbBallons < 0) {
            throw new NegativeValueException("Le nombre de ballons ne peut pas être négatif.");
        }
        if (height <= 0 || width <= 0) {
            System.err.println("Erreur : hauteur et largeur doivent être strictement positifs.");
            return;
        }

        System.out.println("=== LIVRABLE 5B — Mode Interactif ===");
        System.out.println("Plateau : " + height + "x" + width
                + " | Ballons par manche : " + nbBallons);

        // --- PHASE 1 : Plateau classique avec 1 chemin rectiligne ---
        ClassicalBoard board = new ClassicalBoard(height, width);
        List<Position> path = board.generateNewPath();
        board.applyPathToGrid(path);
        System.out.println("Chemin généré : " + path.size() + " cases");
        System.out.println("Départ : " + path.get(0)
                + " → Arrivée : " + path.get(path.size() - 1));
        System.out.println(board.display());

        // --- PHASE 2 : Placement de 2 tours de chaque type via actions (interactif) ---
        Player player = new Player();
        player.setCredits(15000);
        InteractiveListChooser<Object> chooser = new InteractiveListChooser<>();
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

            // Manches 1–5 : appliquer une évolution via action (choix interactif)
            if (manche <= 5) {
                System.out.println("\n→ Application d'une évolution (manche " + manche + "/5)");
                Livrable5.applyOneEvolutionViaAction(board, player, chooser);
            } else {
                // Manches 6–10 : retirer une évolution via action (choix interactif)
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
