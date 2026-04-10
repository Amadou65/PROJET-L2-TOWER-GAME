package game;

import java.util.*;
import game.tower.*;
import game.exeptions.ZeroValueException;

/**
 * GameEngine is the central game loop manager.
 * It handles balloon spawning, movement, tower attacks, and event logging.
 */
public class GameEngine {
    private List<Balloon> reserve;
    private List<Balloon> actif;
    private Board board;
    private Player player;
    private Journal journal;

    /**
     * Creates a new GameEngine.
     * 
     * @param reserve the list of balloons waiting to enter the board
     * @param board   the game board
     */
    public GameEngine(List<Balloon> reserve, Board board) throws ZeroValueException{
        if(reserve == null) {
            throw new IllegalArgumentException("La réserve de ballons ne peut pas être nulle.");
        }
        this.reserve = reserve;
        this.actif = new ArrayList<>();
        this.board = board;
        this.player = new Player();
        this.journal = player.getJournal();
    }

    /**
     * Creates a new GameEngine with an existing player.
     * 
     * @param reserve the list of balloons waiting to enter the board
     * @param board   the game board
     * @param player  the player
     */
    public GameEngine(List<Balloon> reserve, Board board, Player player) {
        this.reserve = reserve;
        this.actif = new ArrayList<>();
        this.board = board;
        this.player = player;
        this.journal = player.getJournal();
    }

    /**
     * Returns the player managed by this engine.
     * 
     * @return the player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Runs the main game loop.
     * Balloons spawn every 20 ticks, move each tick, and towers fire according to
     * their cadence.
     * Logs every event with its timestamp.
     */
    public void game() {
        int time = 0;
        int totalPopped = 0;
        int totalEscaped = 0;
        int initialCount = reserve.size();

        System.out.println("--- DÉMARRAGE DE LA MANCHE (" + initialCount + " ballons) ---");

        while ((!reserve.isEmpty() || !actif.isEmpty()) && player.isAlife()) {
            time++;

            // 1. PHASE DE SPAWN : Un ballon sort tous les 20 tics
            if (time % 20 == 0 && !reserve.isEmpty()) {
                Balloon b = reserve.remove(reserve.size() - 1);
                this.actif.add(b);
                board.getCell(new Position(b.getGridX(), b.getGridY())).putBallon(b);
                System.out.println("[t=" + time + "] Ballon spawné (niveau " + b.getLevel() + ")");
            }

            // 2. PHASE DE MOUVEMENT (boucle inversée pour suppression sûre)
            for (int i = actif.size() - 1; i >= 0; i--) {
                Balloon b = actif.get(i);
                int oldX = b.getGridX();
                int oldY = b.getGridY();
                boolean wasFrozenBeforeMove = b.isFrozen();

                b.move();

                // Détection du redémarrage (dégel)
                if (wasFrozenBeforeMove && !b.isFrozen()) {
                    System.out.println("[t=" + time + "] ▶️  Ballon REDÉMARRÉ (dégelé)");
                }

                // CAS A : BALLON ÉCLATÉ
                if (b.isPopped()) {
                    totalPopped++;
                    player.addCredits(10);
                    journal.recordBalloonDestroyed();
                    board.getCell(new Position(oldX, oldY)).removeBallon(b);
                    actif.remove(i);
                    System.out.println("[t=" + time + "] 💥 Ballon DÉTRUIT ! (" + totalPopped + "/" + initialCount
                            + ") | Crédits: +" + 10);

                    // CAS B : BALLON ÉCHAPPÉ
                } else if (b.hasReachedEnd()) {
                    totalEscaped++;
                    player.onHit();
                    board.getCell(new Position(oldX, oldY)).removeBallon(b);
                    actif.remove(i);
                    System.out.println("[t=" + time + "] 💨 Ballon SORTI ! Vies restantes: " + player.getHealth());

                    // CAS C : MOUVEMENT (changement de case)
                } else if (oldX != b.getGridX() || oldY != b.getGridY()) {
                    board.getCell(new Position(oldX, oldY)).removeBallon(b);
                    board.getCell(new Position(b.getGridX(), b.getGridY())).putBallon(b);
                }
            }

            // 3. PHASE DE TIRS DES TOURS
            for (Tower tower : board.tower_list) {

                // CAS A : TOUR À PROJECTILE
                if (tower instanceof ProjectileTower) {
                    if (tower.canShoot()) {
                        ProjectileTower pt = (ProjectileTower) tower;
                        Balloon target = TargetingBalloon.getBestTarget(actif, tower);
                        if (target != null) {
                            int healthBefore = target.getHealth();
                            pt.shot(actif);
                            int healthAfter = target.getHealth();
                            if (healthAfter < healthBefore) {
                                System.out.println("[t=" + time + "] 🎯 Ballon TOUCHÉ par " + tower.getNom()
                                        + " ! Santé: " + healthBefore + " → " + healthAfter);
                            }
                        }
                    }

                    // CAS B : TOUR NON PROJECTILE (glace / ralentissement)
                } else if (tower instanceof NonProjectileTower) {
                    if (tower.getCadence() > 0 && time % tower.getCadence() == 0) {
                        NonProjectileTower npt = (NonProjectileTower) tower;
                        List<Balloon> targets = TargetingBalloon.getAllTargets(actif, tower);
                        List<Boolean> wereFrozen = new ArrayList<>();
                        for (Balloon b : targets) {
                            wereFrozen.add(b.isFrozen());
                        }

                        npt.freeze(actif);

                        for (int index = 0; index < targets.size(); index++) {
                            Balloon b = targets.get(index);
                            boolean wasFrozen = wereFrozen.get(index);
                            if (!wasFrozen && b.isFrozen()) {
                                System.out.println("[t=" + time + "] ❄️  Ballon ARRÊTÉ (gelé) par " + tower.getNom());
                            } else if (b.isSlowed()) {
                                System.out.println("[t=" + time + "] 🐌 Ballon RALENTI par " + tower.getNom());
                            }
                        }
                    }
                }
            }

            // 4. PAUSE (50ms = 20 FPS)
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // --- BILAN FINAL ---
        System.out.println("\n====================================");
        System.out.println("          BILAN DE LA PARTIE        ");
        System.out.println("====================================");
        System.out.println(player.isAlife() ? "RÉSULTAT : VICTOIRE !" : "RÉSULTAT : GAME OVER");
        System.out.println("Temps de survie       : " + time + " tics");
        System.out.println("Ballons détruits      : " + totalPopped + "/" + initialCount);
        System.out.println("Ballons échappés      : " + totalEscaped);
        System.out.println("Vies restantes        : " + player.getHealth());
        System.out.println("Crédits               : " + player.getCredits());
        System.out.println("Tours achetées        : " + journal.getTowersPurchased());
        System.out.println("====================================\n");
    }
}
