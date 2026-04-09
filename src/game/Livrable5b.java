package game;

import java.util.*;
import game.board.ClassicalBoard;
import game.exeptions.NegativeValueException;
import game.exeptions.ZeroValueException;
import game.exeptions.TooLongPathException;
import game.listchooser.InteractiveListChooser;
import game.listchooser.RandomListChooser;

/**
 * Livrable5b : programme principal du Livrable 5 — Mode multi-chemins.
 *
 * <p>Crée un plateau classique avec {@code nbChemins} chemins rectilignes distincts.
 * Le placement des tours est interactif (InteractiveListChooser). Les évolutions et
 * suppressions d'évolutions entre les manches sont automatiques (RandomListChooser).</p>
 *
 * <p>Contrainte sur le nombre de chemins : 1 ≤ nbChemins ≤ largeur / 2.
 * Cette limite garantit que suffisamment de colonnes restent libres pour placer
 * des tours (les cellules de chemin ne peuvent pas accueillir de tour).</p>
 *
 * <p>À chaque manche, un ballon est créé par chemin (vitesse aléatoire).
 * 10 manches sont jouées :</p>
 * <ul>
 *   <li>Manches 1–5 : application automatique d'une évolution via action.</li>
 *   <li>Manches 6–10 : suppression automatique d'une évolution via action.</li>
 * </ul>
 *
 * <pre>
 * java -jar livrable5b.jar &lt;hauteur&gt; &lt;largeur&gt; &lt;nbChemins&gt;
 * </pre>
 */
public class Livrable5b extends Livrable5 {

    public static void main(String[] args)
            throws ZeroValueException, NegativeValueException, TooLongPathException {

        int height    = args.length > 0 ? Integer.parseInt(args[0]) : 8;
        int width     = args.length > 1 ? Integer.parseInt(args[1]) : 12;
        int nbChemins = args.length > 2 ? Integer.parseInt(args[2]) : 2;

        // --- Validation du nombre de chemins ---
        if (nbChemins <= 0) {
            throw new NegativeValueException(
                    "Le nombre de chemins doit être strictement positif (reçu : " + nbChemins + ").");
        }
        int maxChemins = width / 2;
        if (nbChemins > maxChemins) {
            throw new TooLongPathException(
                    "Trop de chemins : " + nbChemins + " demandés, maximum " + maxChemins
                    + " pour un plateau de largeur " + width
                    + " (les cellules de chemin ne peuvent pas accueillir de tours).");
        }
        if (height <= 0 || width <= 0) {
            System.err.println("Erreur : hauteur et largeur doivent être strictement positifs.");
            return;
        }

        System.out.println("=== LIVRABLE 5B — Mode Multi-chemins ===");
        System.out.println("Plateau : " + height + "x" + width
                + " | Chemins : " + nbChemins
                + " | Ballons par manche : " + nbChemins + " (1 par chemin)");

        // --- PHASE 1 : Plateau classique avec nbChemins chemins distincts ---
        ClassicalBoard board = new ClassicalBoard(height, width);
        List<List<Position>> allPaths = new ArrayList<>();
        int attempts = 0;
        while (allPaths.size() < nbChemins && attempts < 200) {
            attempts++;
            List<Position> path = board.generateNewPath();
            boolean duplicate = false;
            for (List<Position> used : allPaths) {
                if (used.get(0).equals(path.get(0))) { duplicate = true; break; }
            }
            if (!duplicate) {
                allPaths.add(path);
                board.applyPathToGrid(path);
            }
        }

        System.out.println(allPaths.size() + " chemin(s) généré(s) :");
        for (int i = 0; i < allPaths.size(); i++) {
            List<Position> p = allPaths.get(i);
            System.out.println("  Chemin " + (i + 1) + " : " + p.get(0)
                    + " → " + p.get(p.size() - 1) + " (" + p.size() + " cases)");
        }
        System.out.println(board.display());

        // --- PHASE 2 : Placement de 2 tours de chaque type via actions (interactif) ---
        Player player = new Player();
        player.setCredits(15000);
        InteractiveListChooser<Object> interactiveChooser = new InteractiveListChooser<>();
        RandomListChooser<Object> randomChooser = new RandomListChooser<>();

        Livrable5.placeTowersViaActions(board, player, interactiveChooser, height, width);

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

            // Manches 1–5 : appliquer automatiquement une évolution via action
            if (manche <= 5) {
                System.out.println("\n→ Application automatique d'une évolution (manche " + manche + "/5)");
                Livrable5.applyOneEvolutionViaAction(board, player, randomChooser);
            } else {
                // Manches 6–10 : supprimer automatiquement une évolution via action
                System.out.println("\n→ Suppression automatique d'une évolution (manche " + manche + "/10)");
                Livrable5.removeOneEvolutionViaAction(board, player, randomChooser);
            }

            // Créer 1 ballon par chemin avec une vitesse aléatoire
            List<Balloon> reserve = new ArrayList<>();
            for (List<Position> path : allPaths) {
                int lvl = levels[rng.nextInt(levels.length)];
                reserve.add(new Balloon(lvl, path));
            }

            // Lancer la manche — s'arrête quand tous les ballons ont fini leur parcours
            System.out.println("\n--- LANCEMENT DE LA MANCHE " + manche
                    + " (" + allPaths.size() + " ballons) ---");
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
