package game;

import java.util.*;
import game.board.LeftStartRandomBoard;

/**
 * Livrable4a : scénario A du Livrable 4.
 * <p>
 * Crée un plateau aléatoire dont le chemin part obligatoirement du bord gauche,
 * place des tours via le joueur (avec déduction des crédits), puis joue
 * 10 manches avec gestion progressive des évolutions :
 * <ul>
 *   <li>Manches 1 à 5 : une évolution est ajoutée à chaque manche (si possible)</li>
 *   <li>Manches 6 à 10 : une évolution est retirée à chaque manche (si possible)</li>
 * </ul>
 * </p>
 *
 * <p>Usage :</p>
 * <pre>
 * java -jar livrable4a.jar &lt;hauteur&gt; &lt;largeur&gt; &lt;nbBallons&gt;
 * </pre>
 */
public class Livrable4a extends Livrable4 {

    public static void main(String[] args) {
        int height = args.length > 0 ? Integer.parseInt(args[0]) : 8;
        int width = args.length > 1 ? Integer.parseInt(args[1]) : 12;
        int nbBallons = args.length > 2 ? Integer.parseInt(args[2]) : 5;

        if(nbBallons < 0) {
            throw new IllegalArgumentException("Le nombre de ballons ne peut pas être négatif.");
        }

        // Validation des arguments
        if (height <= 0 || width <= 0 || nbBallons <= 0) {
            System.err.println("Erreur : tous les arguments doivent être strictement positifs.");
            System.err.println("Usage : java -jar livrable4a.jar <hauteur> <largeur> <nbBallons>");
            return;
        }

        System.out.println("=== LIVRABLE 4A ===");
        System.out.println("Plateau aléatoire : " + height + "x" + width
                + " | Ballons par manche : " + nbBallons);

        // --- PHASE 1 : Plateau aléatoire avec départ depuis le bord gauche ---
        LeftStartRandomBoard board = new LeftStartRandomBoard(height, width);
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

            // --- Créer les ballons pour cette manche ---
            List<Balloon> reserve = new ArrayList<>();
            for (int i = 0; i < nbBallons; i++) {
                int lvl = levels[rng.nextInt(levels.length)];
                reserve.add(new Balloon(lvl, path));
            }

            // --- Lancer la manche ---
            System.out.println("\n--- Lancement de la manche " + manche + " (" + nbBallons + " ballons) ---");
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
