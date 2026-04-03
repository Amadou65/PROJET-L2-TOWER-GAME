package game;

import java.util.*;
import game.tower.*;
import game.tower.typeTower.*;
import game.exeptions.TypeTowerException;
import game.exeptions.NoEvolutionException;

/**
 * Livrable4 est la classe parent commune à Livrable4a et Livrable4b.
 * Elle regroupe les méthodes utilitaires partagées entre les deux scénarios :
 * - placement de tours via le joueur (sans impacter les crédits de départ)
 * - achat d'évolutions sur les tours de type ProjectileTower
 * - fabrique de tours (buildTower)
 * - gestion progressive des évolutions au fil des manches
 *
 * <p>
 * Cette classe ne contient pas de méthode main ; ce sont ses sous-classes
 * Livrable4a et Livrable4b qui définissent le scénario complet.
 * </p>
 */
public class Livrable4 {

    /** Types d'évolutions utilisés pour l'ajout/suppression progressive */
    private static final Evolution.EvolutionType[] EVO_TYPES = {
        Evolution.EvolutionType.POWER,
        Evolution.EvolutionType.CADENCE,
        Evolution.EvolutionType.SCOPE,
        Evolution.EvolutionType.PROJECTILE
    };

    /**
     * Collecte les cases libres (hors chemin) du plateau, puis
     * achète et place 2 tours de chaque type via {@code player.buyTower()}.
    * Le coût de ces tours de setup est remboursé pour préserver
    * le budget initial de la manche.
     *
     * @param board  le plateau de jeu
     * @param player le joueur (propriétaire des crédits)
     * @param height nombre de lignes du plateau
     * @param width  nombre de colonnes du plateau
     * @return la liste des tours effectivement achetées et placées
     */
    public static List<Tower> placeTowers(Board board, Player player, int height, int width) {
        int initialCredits = player.getCredits();

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

        if (freeCells.size() < types.length) {
            System.out.println("[SETUP][WARN] Cases libres insuffisantes : "
                    + freeCells.size() + " disponibles pour " + types.length + " tours.");
        }

        // 3. Acheter chaque tour via player.buyTower() puis rembourser son coût
        // pour garantir un setup fixe de 2 tours par type.
        List<Tower> placedTowers = new ArrayList<>();
        int idx = 0;
        for (String type : types) {
            if (idx >= freeCells.size())
                break;
            Position pos = freeCells.get(idx++);
            Tower t = buildTower(type, pos);
            if (t != null) {
                int creditsAvant = player.getCredits();
                player.buyTower(t, pos, board);
                if (player.getCredits() < creditsAvant) {
                    placedTowers.add(t);
                    // Les tours initiales sont offertes pour ce scénario.
                    player.addCredits(t.getCost());
                    System.out.println("[SETUP] Tour " + t.getNom()
                            + " placée en ("
                            + t.getX() + "," + t.getY() + ")");
                } else {
                    System.out.println("[SETUP][WARN] Impossible de placer " + type
                            + " en (" + pos.getX() + "," + pos.getY() + ")");
                }
            }
        }

        player.setCredits(initialCredits);

        if (placedTowers.size() < types.length) {
            System.out.println("[SETUP][WARN] " + placedTowers.size() + "/" + types.length
                    + " tours placées. Vérifiez la taille du plateau et le chemin.");
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
            if (player.canUpgrade(t, evopower)) {
                try {
                    player.buyEvolution(t, evopower);
                } catch (TypeTowerException e) {
                    System.out.println("[EVOL] " + t.getNom()
                            + " ne peut pas évoluer : " + e.getMessage());
                }
            }

            // Tentative d'achat CADENCE
            if (player.canUpgrade(t, evocadence)) {
                try {
                    player.buyEvolution(t, evocadence);
                } catch (TypeTowerException e) {
                    System.out.println("[EVOL] " + t.getNom()
                            + " ne peut pas évoluer : " + e.getMessage());
                }
            }
        }
    }

    /**
     * Tente d'appliquer UNE évolution sur la première tour éligible trouvée.
     * Parcourt les types d'évolutions (POWER, CADENCE, SCOPE, PROJECTILE)
     * et les tours jusqu'à trouver une combinaison possible.
     *
     * @param towers la liste des tours sur le plateau
     * @param player le joueur (dont les crédits sont débités)
     * @return une description de l'évolution appliquée, ou null si aucune n'a pu être appliquée
     */
    public static String applyOneEvolution(List<Tower> towers, Player player) {
        for (Evolution.EvolutionType type : EVO_TYPES) {
            int cost;
            switch (type) {
                case POWER: cost = 200; break;
                case CADENCE: cost = 150; break;
                case SCOPE: cost = 180; break;
                case PROJECTILE: cost = 250; break;
                default: cost = 200; break;
            }
            Evolution evo = new Evolution(cost, type);

            for (Tower t : towers) {
                if (!(t instanceof ProjectileTower)) continue;
                ProjectileTower pt = (ProjectileTower) t;
                if (pt.hasEvolution(type)) continue;
                if (player.getCredits() < cost) continue;

                try {
                    player.buyEvolution(t, evo);
                    return type + " sur " + t.getNom();
                } catch (TypeTowerException e) {
                    // Tour non éligible, on continue
                }
            }
        }
        return null; // Aucune évolution possible
    }

    /**
     * Tente de supprimer UNE évolution sur la première tour qui en possède une.
     * Parcourt les tours et retire la première évolution trouvée.
     *
     * @param towers la liste des tours sur le plateau
     * @param player le joueur (qui est remboursé)
     * @return une description de l'évolution retirée, ou null si aucune n'a pu être retirée
     */
    public static String removeOneEvolution(List<Tower> towers, Player player) {
        for (Tower t : towers) {
            if (!(t instanceof ProjectileTower)) continue;
            ProjectileTower pt = (ProjectileTower) t;

            for (Evolution.EvolutionType type : EVO_TYPES) {
                if (!pt.hasEvolution(type)) continue;

                int cost;
                switch (type) {
                    case POWER: cost = 200; break;
                    case CADENCE: cost = 150; break;
                    case SCOPE: cost = 180; break;
                    case PROJECTILE: cost = 250; break;
                    default: cost = 200; break;
                }
                Evolution evo = new Evolution(cost, type);

                try {
                    player.sellEvolution(t, evo);
                    return type + " de " + t.getNom();
                } catch (TypeTowerException | NoEvolutionException e) {
                    // Erreur inattendue, on continue
                }
            }
        }
        return null; // Aucune évolution à retirer
    }

    /**
     * Affiche les évolutions actuelles de toutes les tours.
     *
     * @param towers la liste des tours
     */
    public static void displayEvolutions(List<Tower> towers) {
        for (Tower t : towers) {
            if (t instanceof ProjectileTower) {
                ProjectileTower pt = (ProjectileTower) t;
                System.out.println("  " + t.getNom() + " : " + pt.getEvoAplied());
            }
        }
    }

    /**
     * Fabrique une tour du type demandé à la position donnée.
     *
     * @param type le nom du type de tour (ex : "DartMonkey", "BombTower")
     * @param pos  la position où la tour sera placée
     * @return l'instance créée, ou {@code null} si le type est inconnu
     */
    public static Tower buildTower(String type, Position pos) {
        switch (type) {
            case "DartMonkey":
                return new DartMonkey("DartMonkey", pos);
            case "BombTower":
                return new BombTower("BombTower", pos);
            case "SniperMonkey":
                return new SniperMonkey("SniperMonkey", pos);
            case "SuperMonkey":
                return new SuperMonkey("SuperMonkey", pos);
            case "TackShooter":
                return new TackShooter("TackShooter", pos);
            case "IceTower":
                return new IceTower("IceTower", pos);
            case "SlowdownTower":
                return new SlowdownTower("SlowdownTower", pos);
            default:
                System.out.println("[WARN] Type de tour inconnu : " + type);
                return null;
        }
    }
}
