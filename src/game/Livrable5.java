package game;

import java.util.*;
import game.tower.*;
import game.tower.typeTower.*;
import game.listchooser.ListChooser;
import game.choice.*;
import game.exeptions.TypeTowerException;

/**
 * Livrable5 est la classe parent commune à Livrable5a et Livrable5b.
 * Elle implémente la gestion des actions du joueur au début de chaque manche :
 * acheter/placer des tours, évoluer, vendre des tours ou des évolutions.
 *
 * <p>Le joueur peut réaliser autant d'actions qu'il le veut tant que
 * ses crédits restent positifs. Lorsqu'il a terminé, la manche démarre.</p>
 */
public class Livrable5 {

    /**
     * Phase d'actions du joueur : boucle de choix via le ListChooser
     * jusqu'à ce que le joueur choisisse END_TURN ou qu'il n'ait plus de crédits.
     *
     * @param board   le plateau de jeu
     * @param player  le joueur
     * @param chooser le sélecteur de choix (interactif ou aléatoire)
     * @param height  hauteur du plateau
     * @param width   largeur du plateau
     */
    @SuppressWarnings("unchecked")
    public static void playerActionPhase(Board board, Player player,
            ListChooser chooser, int height, int width) {

        System.out.println("\n========================================");
        System.out.println("   PHASE D'ACTIONS DU JOUEUR");
        System.out.println("========================================");

        boolean continueActions = true;

        while (continueActions && player.getCredits() > 0) {
            // Afficher l'état actuel
            System.out.println("\n--- État actuel ---");
            System.out.println("Crédits : " + player.getCredits());
            System.out.println("Tours sur le plateau : " + board.tower_list.size());
            System.out.println(board.display());

            // Proposer les actions au joueur
            List<PlayerAction> actions = Arrays.asList(PlayerAction.values());
            PlayerAction chosen = (PlayerAction) chooser.choose(
                    "Quelle action souhaitez-vous réaliser ?", actions);

            if (chosen == null || chosen == PlayerAction.END_TURN) {
                continueActions = false;
                System.out.println("[ACTION] Fin de la phase d'actions.");
            } else {
                switch (chosen) {
                    case BUY_TOWER:
                        handleBuyTower(board, player, chooser, height, width);
                        break;
                    case EVOLVE_TOWER:
                        handleEvolveTower(board, player, chooser);
                        break;
                    case SELL_TOWER:
                        handleSellTower(board, player, chooser);
                        break;
                    case SELL_EVOLUTION:
                        handleSellEvolution(board, player, chooser);
                        break;
                    default:
                        break;
                }
            }
        }

        if (player.getCredits() <= 0) {
            System.out.println("[ACTION] Plus de crédits — fin automatique de la phase d'actions.");
        }
    }

    // =========================================================================
    //  ACHETER UNE TOUR
    // =========================================================================

    /**
     * Gère l'achat et le placement d'une tour.
     * 1. Propose la liste des types de tours achetables (filtrée par crédits)
     * 2. Propose la liste des cases libres
     * 3. Appelle player.buyTower()
     */
    @SuppressWarnings("unchecked")
    private static void handleBuyTower(Board board, Player player,
            ListChooser chooser, int height, int width) {

        // 1. Construire la liste des types de tours achetables
        String[][] towerSpecs = {
                { "DartMonkey", "200", "1", "20" },
                { "BombTower", "600", "2", "36" },
                { "SniperMonkey", "300", "999", "60" },
                { "SuperMonkey", "1200", "200", "300" },
                { "TackShooter", "400", "1", "10" },
                { "IceTower", "400", "100", "1500" },
                { "SlowdownTower", "500", "100", "1500" }
        };

     List<TowerChoice> availableTowers = new ArrayList<>();
        for (String[] spec : towerSpecs) {
            int cost = Integer.parseInt(spec[1]);
            if (player.getCredits() >= cost) {
                availableTowers.add(new TowerChoice(
                        spec[0], cost,
                        Integer.parseInt(spec[2]),
                        Integer.parseInt(spec[3])));
            }
        }

  if (availableTowers.isEmpty()) {
            System.out.println("[ACHAT] Aucune tour achetable avec vos crédits actuels.");
            return;
        }

        // 2. Choisir un type de tour
        TowerChoice towerChoice = (TowerChoice) chooser.choose(
                "Quelle tour souhaitez-vous acheter ?", availableTowers);
        if (towerChoice == null) {
            System.out.println("[ACHAT] Achat annulé.");
            return;
        }
  
        // 3. Collecter les cases libres
        List<Position> freeCells = getFreeCells(board, height, width);
        if (freeCells.isEmpty()) {
            System.out.println("[ACHAT] Aucune case libre disponible !");
            return;
        }

         // 4. Choisir une position
        Position pos = (Position) chooser.choose(
                "Où placer la tour " + towerChoice.getTowerType() + " ?", freeCells);
        if (pos == null) {
            System.out.println("[ACHAT] Placement annulé.");
            return;
        }

    
}
