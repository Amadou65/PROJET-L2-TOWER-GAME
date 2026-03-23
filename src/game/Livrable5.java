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

         // 5. Créer et acheter la tour
        Tower tower = buildTower(towerChoice.getTowerType(), pos);
        if (tower != null) {
            int creditsBefore = player.getCredits();
            player.buyTower(tower, pos, board);
            if (player.getCredits() < creditsBefore) {
                System.out.println("[ACHAT] ✅ Tour " + tower.getNom()
                        + " achetée en (" + pos.getX() + "," + pos.getY()
                        + ") pour " + tower.getCost() + " crédits."
                        + " Crédits restants : " + player.getCredits());
            }
        }
    }
 
    // =========================================================================
    //  ÉVOLUER UNE TOUR
    // =========================================================================

    /**
     * Gère l'achat d'une évolution pour une tour existante.
     * 1. Propose les ProjectileTower du plateau
     * 2. Propose les types d'évolutions disponibles
     * 3. Appelle player.buyEvolution()
     */
    @SuppressWarnings("unchecked")
    private static void handleEvolveTower(Board board, Player player,
            ListChooser chooser) {

        // 1. Récupérer les ProjectileTower sur le plateau
        List<TowerChoice> evolvableTowers = new ArrayList<>();
        for (Tower t : board.tower_list) {
            if (t instanceof ProjectileTower) {
                evolvableTowers.add(new TowerChoice(t));
            }
        }
  
        if (evolvableTowers.isEmpty()) {
            System.out.println("[EVOL] Aucune tour évolutive sur le plateau.");
            return;
        }

        // 2. Choisir la tour à évoluer
        TowerChoice towerChoice = (TowerChoice) chooser.choose(
                "Quelle tour souhaitez-vous évoluer ?", evolvableTowers);
        if (towerChoice == null) {
            System.out.println("[EVOL] Évolution annulée.");
            return;
        }

        ProjectileTower pt = (ProjectileTower) towerChoice.getTower();

        // 3. Proposer les évolutions disponibles (non déjà appliquées)
        Object[][] evoSpecs = {
                { Evolution.EvolutionType.POWER, 250 },
                { Evolution.EvolutionType.CADENCE, 150 },
                { Evolution.EvolutionType.SCOPE, 100 },
                { Evolution.EvolutionType.PROJECTILE, 300 }
        };

        List<EvolutionChoice> availableEvos = new ArrayList<>();
        for (Object[] spec : evoSpecs) {
            Evolution.EvolutionType type = (Evolution.EvolutionType) spec[0];
            int cost = (int) spec[1];
            if (!pt.hasEvolution(type) && player.getCredits() >= cost) {
                availableEvos.add(new EvolutionChoice(type, cost));
            }
        }

        if (availableEvos.isEmpty()) {
            System.out.println("[EVOL] Aucune évolution disponible pour " + pt.getNom()
                    + " (toutes appliquées ou crédits insuffisants).");
            return;
        }

        // 4. Choisir l'évolution
        EvolutionChoice evoChoice = (EvolutionChoice) chooser.choose(
                "Quelle évolution pour " + pt.getNom() + " ?", availableEvos);
        if (evoChoice == null) {
            System.out.println("[EVOL] Évolution annulée.");
            return;
        }

        // 5. Appliquer l'évolution
        Evolution evo = new Evolution(evoChoice.getCost(), evoChoice.getType());
        try {
            player.buyEvolution(pt, evo);
        } catch (TypeTowerException e) {
            System.out.println("[EVOL] Erreur : " + e.getMessage());
        }
    }

    // =========================================================================
    //  VENDRE UNE TOUR
    // =========================================================================

    /**
     * Gère la vente d'une tour existante.
     * 1. Propose les tours du plateau
     * 2. Appelle player.sellTower()
     */
    @SuppressWarnings("unchecked")
    private static void handleSellTower(Board board, Player player,
            ListChooser chooser) {

        if (board.tower_list.isEmpty()) {
            System.out.println("[VENTE] Aucune tour à vendre.");
            return;
        }

        // 1. Construire la liste des tours vendables
        List<TowerChoice> sellableTowers = new ArrayList<>();
        for (Tower t : board.tower_list) {
            sellableTowers.add(new TowerChoice(t));
        }

         // 2. Choisir la tour à vendre
        TowerChoice towerChoice = (TowerChoice) chooser.choose(
                "Quelle tour souhaitez-vous vendre ?", sellableTowers);
        if (towerChoice == null) {
            System.out.println("[VENTE] Vente annulée.");
            return;
        }

        // 3. Vendre la tour
        Tower tower = towerChoice.getTower();
        Position pos = tower.getPosition();
        player.sellTower(tower, board, pos);
        System.out.println("[VENTE] ✅ Tour " + tower.getNom()
                + " vendue pour " + tower.getCost() + " crédits."
                + " Crédits : " + player.getCredits());
    }

    // =========================================================================
    //  VENDRE UNE ÉVOLUTION
    // =========================================================================

    /**
     * Gère la vente d'une évolution déjà achetée.
     * 1. Propose les tours évoluées
     * 2. Propose les évolutions à revendre
     * 3. Retire l'évolution et rembourse le joueur
     *
     * Note : utilise removeEvolution() de ProjectileTower et rembourse
     * directement le joueur. Quand Player.sellEvolution() sera implémenté
     * par Serhii, cette méthode pourra être simplifiée.
     */
    @SuppressWarnings("unchecked")
    private static void handleSellEvolution(Board board, Player player,
            ListChooser chooser) {

        // 1. Récupérer les tours qui ont au moins une évolution
        Evolution.EvolutionType[] allTypes = {
                Evolution.EvolutionType.POWER, Evolution.EvolutionType.CADENCE,
                Evolution.EvolutionType.SCOPE, Evolution.EvolutionType.PROJECTILE
        };

        List<TowerChoice> evolvedTowers = new ArrayList<>();
        for (Tower t : board.tower_list) {
            if (t instanceof ProjectileTower) {
                ProjectileTower pt = (ProjectileTower) t;
                boolean hasAny = false;
                for (Evolution.EvolutionType type : allTypes) {
                    if (pt.hasEvolution(type)) { hasAny = true; break; }
                }
                if (hasAny) {
                    evolvedTowers.add(new TowerChoice(t));
                }
            }
        }

        if (evolvedTowers.isEmpty()) {
            System.out.println("[REVENTE] Aucune tour n'a d'évolution à revendre.");
            return;
        }

        // 2. Choisir la tour
        TowerChoice towerChoice = (TowerChoice) chooser.choose(
                "De quelle tour souhaitez-vous revendre une évolution ?",
                evolvedTowers);
        if (towerChoice == null) {
            System.out.println("[REVENTE] Revente annulée.");
            return;
        }

        ProjectileTower pt = (ProjectileTower) towerChoice.getTower();

        // 3. Proposer les évolutions à revendre
        // Coûts de référence pour le remboursement
        Map<Evolution.EvolutionType, Integer> evoCosts = new HashMap<>();
        evoCosts.put(Evolution.EvolutionType.POWER, 250);
        evoCosts.put(Evolution.EvolutionType.CADENCE, 150);
        evoCosts.put(Evolution.EvolutionType.SCOPE, 100);
        evoCosts.put(Evolution.EvolutionType.PROJECTILE, 300);

        List<EvolutionChoice> sellableEvos = new ArrayList<>();
        for (Evolution.EvolutionType type : allTypes) {
            if (pt.hasEvolution(type)) {
                int cost = evoCosts.getOrDefault(type, 0);
                sellableEvos.add(new EvolutionChoice(type, cost));
            }
        }

        if (sellableEvos.isEmpty()) {
            System.out.println("[REVENTE] Cette tour n'a aucune évolution.");
            return;
        }

        // 4. Choisir l'évolution à revendre
        EvolutionChoice evoChoice = (EvolutionChoice) chooser.choose(
                "Quelle évolution de " + pt.getNom() + " revendre ?",
                sellableEvos);
        if (evoChoice == null) {
            System.out.println("[REVENTE] Revente annulée.");
            return;
        }

        // 5. Retirer l'évolution et rembourser
        pt.removeEvolution(evoChoice.getType());
        player.addCredits(evoChoice.getCost());
        System.out.println("[REVENTE] ✅ Évolution " + evoChoice.getType()
                + " retirée de " + pt.getNom()
                + ". Remboursement : " + evoChoice.getCost()
                + " crédits. Crédits : " + player.getCredits());
    }


}
