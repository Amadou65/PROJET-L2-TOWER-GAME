package game;

import java.util.*;
import game.board.ClassicalBoard;

/**
 * Livrable4b : scénario B du Livrable 4.
 * <p>
 * Crée un plateau classique avec N chemins rectilignes distincts,
 * place des tours via le joueur, puis joue 10 manches avec gestion
 * progressive des évolutions :
 * <ul>
 *   <li>Manches 1 à 5 : une évolution est ajoutée à chaque manche (si possible)</li>
 *   <li>Manches 6 à 10 : une évolution est retirée à chaque manche (si possible)</li>
 * </ul>
 * À chaque manche, un ballon est créé sur chaque chemin.
 * </p>
 *
 * <p>Usage :</p>
 * <pre>
 * java -jar livrable4b.jar &lt;hauteur&gt; &lt;largeur&gt; &lt;nbChemins&gt;
 * </pre>
 */
public class Livrable4b extends Livrable4 {

    public static void main(String[] args) {
        int height = args.length > 0 ? Integer.parseInt(args[0]) : 8;
        int width = args.length > 1 ? Integer.parseInt(args[1]) : 12;
        int nbChemins = args.length > 2 ? Integer.parseInt(args[2]) : 3;

        System.out.println("=== LIVRABLE 4B ===");
        System.out.println("Plateau classique : " + height + "x" + width
                + " | Chemins : " + nbChemins);

        // --- PHASE 1 : Plateau classique avec PLUSIEURS chemins rectilignes ---
        ClassicalBoard board = new ClassicalBoard(height, width);

        // Générer N chemins distincts
        List<List<Position>> allPaths = new ArrayList<>();
        int attempts = 0;
        while (allPaths.size() < nbChemins && attempts < 200) {
            attempts++;
            List<Position> path = board.generateNewPath();

            // Vérifier que ce chemin n'est pas un doublon (même point de départ)
            boolean duplicate = false;
            for (List<Position> used : allPaths) {
                if (used.get(0).equals(path.get(0))) {
                    duplicate = true;
                    break;
                }
            }
            if (duplicate) continue;

            allPaths.add(path);
            board.applyPathToGrid(path);
        }

        System.out.println(allPaths.size() + " chemins distincts générés :");
        for (int i = 0; i < allPaths.size(); i++) {
            List<Position> p = allPaths.get(i);
            System.out.println("  Chemin " + (i + 1) + " : " + p.get(0)
                    + " → " + p.get(p.size() - 1) + " (" + p.size() + " cases)");
        }
        System.out.println(board.display());

        // --- PHASE 2 : Créer le joueur et acheter les tours ---
        Player player = new Player();
        System.out.println("\n--- ACHAT DES TOURS (crédits initiaux : "
                + player.getCredits() + ") ---");
        List<Tower> towers = Livrable4.placeTowers(board, player, height, width);
        System.out.println("Crédits restants après tours : " + player.getCredits());

        // --- PHASE 3 : Boucle de 10 manches ---
        Random rng = new Random();
        int[] levels = { 1, 2, 4 };

        for (int manche = 1; manche <= 10; manche++) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║          MANCHE " + manche + " / 10                    ║");
            System.out.println("╚══════════════════════════════════════════╝");

            // --- Gestion des évolutions ---
            if (manche <= 5) {
                // Manches 1 à 5 : ajouter une évolution
                String result = Livrable4.applyOneEvolution(towers, player);
                if (result != null) {
                    System.out.println("[MANCHE " + manche + "] ✨ Évolution ajoutée : " + result);
                } else {
                    System.out.println("[MANCHE " + manche + "] Aucune évolution possible à ajouter.");
                }
            } else {
                // Manches 6 à 10 : retirer une évolution
                String result = Livrable4.removeOneEvolution(towers, player);
                if (result != null) {
                    System.out.println("[MANCHE " + manche + "] 🔻 Évolution retirée : " + result);
                } else {
                    System.out.println("[MANCHE " + manche + "] Aucune évolution à retirer.");
                }
            }

            // Afficher les évolutions des tours
            System.out.println("[MANCHE " + manche + "] État des évolutions :");
            Livrable4.displayEvolutions(towers);

            // --- Créer un ballon par chemin pour cette manche ---
            List<Balloon> reserve = new ArrayList<>();
            for (int i = 0; i < allPaths.size(); i++) {
                int lvl = levels[rng.nextInt(levels.length)];
                reserve.add(new Balloon(lvl, allPaths.get(i)));
            }

            // --- Lancer la manche ---
            System.out.println("\n--- Lancement de la manche " + manche
                    + " (" + reserve.size() + " ballons, un par chemin) ---");
            GameEngine engine = new GameEngine(reserve, board, player);
            engine.game();

            // Vérifier si le joueur est encore en vie
            if (!player.isAlife()) {
                System.out.println("\n💀 GAME OVER à la manche " + manche + " !");
                break;
            }

            System.out.println("[MANCHE " + manche + "] Terminée | Vies : " + player.getHealth()
                    + " | Crédits : " + player.getCredits());
        }

        System.out.println("\n====================================");
        System.out.println("       FIN DES 10 MANCHES          ");
        System.out.println("====================================");
        System.out.println("Vies restantes : " + player.getHealth());
        System.out.println("Crédits finaux : " + player.getCredits());
    }
}
