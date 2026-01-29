package game;

import java.util.*;

public class GameEngine {
    private List<Balloon> reserve;
    private List<Balloon> actif;
    private List<Position> path;
    private Board board;
    private Player player;

    public GameEngine(List<Balloon> reserve, List<Position> path, Board board) {
        this.reserve = reserve;
        this.actif = new ArrayList<>();
        this.path = path;
        this.board = board;
        this.player = new Player();
    }

    public void game() {
        int time = 0;
        int totalPopped = 0;   // Compteur de ballons éclatés
        int totalEscaped = 0;  // Compteur de ballons ayant franchi la ligne
        int initialCount = reserve.size(); // Nombre total de ballons au départ

        System.out.println("--- DÉMARRAGE DE LA MANCHE (" + initialCount + " ballons) ---");

        while ((!reserve.isEmpty() || !actif.isEmpty()) && player.isAlife()) {
            time++;

            // 1. PHASE DE SPAWN
            if (time % 20 == 0 && !reserve.isEmpty()) {
                Balloon b = reserve.remove(reserve.size() - 1);
                this.actif.add(b);
                board.getCell(new Position(b.getGridX(), b.getGridY())).putBallon(b);
            }

            // 2. PHASE DE MOUVEMENT ET MISE À JOUR
            for (int i = actif.size() - 1; i >= 0; i--) {
                Balloon b = actif.get(i);
                int oldX = b.getGridX();
                int oldY = b.getGridY();

                b.move();

                // CAS A : BALLON ÉCLATÉ (VICTOIRE LOCALE)
                if (b.isPopped()) {
                    totalPopped++;
                    player.setCredits(player.getCredits() + 10);
                    board.getCell(new Position(oldX, oldY)).removeBallon(b);
                    actif.remove(i);
                    System.out.println("[SCORE] Ballon éclaté ! (" + totalPopped + "/" + initialCount + ")");
                } 
                // CAS B : BALLON ÉCHAPPÉ (DÉFAITE LOCALE)
                else if (b.hasReachedEnd()) {
                    totalEscaped++;
                    player.onHit();
                    board.getCell(new Position(oldX, oldY)).removeBallon(b);
                    actif.remove(i);
                    System.out.println("[ALERTE] Un ballon s'est échappé !");
                } 
                // CAS C : MOUVEMENT CLASSIQUE
                else if (oldX != b.getGridX() || oldY != b.getGridY()) {
                    board.getCell(new Position(oldX, oldY)).removeBallon(b);
                    board.getCell(new Position(b.getGridX(), b.getGridY())).putBallon(b);
                }

                for (Balloon b : actif) {
                   int oldX = b.getGridX();
                   int oldY = b.getGridY();
            }

            try { Thread.sleep(50); } catch (InterruptedException e) {}
        }

        // --- PHASE DE BILAN FINAL ---
        System.out.println("\n====================================");
        System.out.println("          BILAN DE LA PARTIE        ");
        System.out.println("====================================");
        
        if (!player.isAlife()) {
            System.out.println("RÉSULTAT : GAME OVER (Le joueur est mort)");
        } else {
            System.out.println("RÉSULTAT : VICTOIRE !");
        }

        System.out.println("Temps de survie : " + time + " tics");
        System.out.println("Ballons éclatés : " + totalPopped);
        System.out.println("Ballons échappés : " + totalEscaped);

        // LE MESSAGE DE PRÉCISION
        if (totalPopped == initialCount) {
            System.out.println("MÉDAILLE D'OR : Perfect ! Aucun ballon n'a survécu.");
        } else if (totalPopped > 0) {
            System.out.println("MÉDAILLE D'ARGENT : Bien joué, mais certains ont filé.");
        } else {
            System.out.println("MÉDAILLE DE BRONZE : On fera mieux la prochaine fois...");
        }
        System.out.println("====================================\n");
    }
}