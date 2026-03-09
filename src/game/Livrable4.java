package game;

import java.util.*;
import game.tower.*;
import game.tower.typeTower.*;
import game.exeptions.TypeTowerException;


/**
 * Livrable4 est la classe parent commune à Livrable4a et Livrable4b.
 * Elle regroupe les méthodes utilitaires partagées entre les deux scénarios :
 * - placement de tours via le joueur (avec déduction des crédits)
 * - achat d'évolutions sur les tours de type ProjectileTower
 * - fabrique de tours (buildTower)
 *
 * <p>
 * Cette classe ne contient pas de méthode main ; ce sont ses sous-classes
 * Livrable4a et Livrable4b qui définissent le scénario complet.
 * </p>
 */
public class Livrable4 {

    /**
     * Collecte les cases libres (hors chemin) du plateau, puis
     * achète et place 2 tours de chaque type via {@code player.buyTower()}.
     * Les crédits du joueur sont automatiquement déduits à chaque achat.
     *
     * @param board  le plateau de jeu
     * @param player le joueur (propriétaire des crédits)
     * @param height nombre de lignes du plateau
     * @param width  nombre de colonnes du plateau
     * @return la liste des tours effectivement achetées et placées
     */
    public static List<Tower> placeTowers(Board board, Player player, int height, int width) {
        // 1. Collecter toutes les cases libres (hors chemin)
        List<Position> freeCells = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Position p = new Position(i, j);
                if (!board.getCell(p).isPath()) {
                    freeCells.add(p);
                }
            }
        }
        Collections.shuffle(freeCells); // on mélange pour un placement aléatoire

        // 2. Définir les types de tours à placer (2 de chaque)
        String[] types = {
                "DartMonkey", "DartMonkey",
                "BombTower", "BombTower",
                "SniperMonkey", "SniperMonkey",
                "SuperMonkey", "SuperMonkey",
                "TackShooter", "TackShooter",
                "IceTower", "IceTower",
                "SlowdownTower", "SlowdownTower"
        };

        // 3. Acheter chaque tour via player.buyTower() (déduit les crédits)
        List<Tower> placedTowers = new ArrayList<>();
        int idx = 0;
        for (String type : types) {
            if (idx >= freeCells.size())
                break;
            Position pos = freeCells.get(idx++);
            Tower t = buildTower(type, pos);
            if (t != null) {
                player.buyTower(t, pos, board);
                placedTowers.add(t);
                System.out.println("[SETUP] Tour " + t.getNom()
                        + " achetée (" + t.cost + " crédits) en ("
                        + t.getX() + "," + t.getY() + ")");
            }
        }
        return placedTowers;
    }
    /**
     * Achète une évolution POWER puis CADENCE sur chaque tour eligible.
     * Seules les {@link ProjectileTower} peuvent recevoir des évolutions.
     * Si une tour non-eligible est passée, l'exception {@link TypeTowerException}
     * est interceptée et un message est affiché.
     *
     * @param towers la liste des tours sur lesquelles tenter les évolutions
     * @param player le joueur (dont les crédits sont débités)
     */
    public static void buyEvolutions(List<Tower> towers, Player player) {
        // On prépare deux types d'évolutions
        Evolution evopower = new Evolution(200, Evolution.EvolutionType.POWER);
        Evolution evocadence = new Evolution(150, Evolution.EvolutionType.CADENCE);

        for (Tower t : towers) {
            // Tentative d'achat POWER
            try {
                player.buyEvolution(t, evopower);
            } catch (TypeTowerException e) {
                // IceTower / SlowdownTower ne peuvent pas évoluer → on l'ignore
                System.out.println("[EVOL] " + t.getNom()
                        + " ne peut pas évoluer : " + e.getMessage());
            }

            // Tentative d'achat CADENCE
            try {
                player.buyEvolution(t, evocadence);
            } catch (TypeTowerException e) {
                System.out.println("[EVOL] " + t.getNom()
                        + " ne peut pas évoluer : " + e.getMessage());
            }
        }
    }
