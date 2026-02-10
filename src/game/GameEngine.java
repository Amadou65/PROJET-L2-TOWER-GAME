package game;

import java.util.*; 

public class GameEngine {
    private List<Balloon> reserve;
    private List<Balloon> actif;
    private Board board;
    private Player player;

    public GameEngine(List<Balloon> reserve, Board board) {
        this.reserve = reserve;
        this.actif = new ArrayList<>();
        this.board = board;
        this.player = new Player();
    }

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
            }

            // 2. PHASE DE MOUVEMENT ET MISE À JOUR (Boucle inversée pour pouvoir supprimer)
            for (int i = actif.size() - 1; i >= 0; i--) {
                Balloon b = actif.get(i);
                int oldX = b.getGridX();
                int oldY = b.getGridY();

                b.move();

                // CAS A : BALLON ÉCLATÉ
                if (b.isPopped()) {
                    totalPopped++;
                    player.setCredits(player.getCredits() + 10);
                    board.getCell(new Position(oldX, oldY)).removeBallon(b);
                    actif.remove(i);
                    System.out.println("[SCORE] Ballon éclaté ! (" + totalPopped + "/" + initialCount + ")");
                } 
                // CAS B : BALLON ÉCHAPPÉ
                else if (b.hasReachedEnd()) {
                    totalEscaped++;
                    player.onHit();
                    board.getCell(new Position(oldX, oldY)).removeBallon(b);
                    actif.remove(i);
                    System.out.println("[ALERTE] Un ballon s'est échappé !");
                } 
                // CAS C : MOUVEMENT CLASSIQUE (Changement de case)
                else if (oldX != b.getGridX() || oldY != b.getGridY()) {
                    board.getCell(new Position(oldX, oldY)).removeBallon(b);
                    board.getCell(new Position(b.getGridX(), b.getGridY())).putBallon(b);
                }
            } // Fin de la boucle de mouvement

            // 3. PHASE DE TIRS DES TOURS
            for (Tower tower : board.tower_list){
                // CAS A : UNE TOUR PROJECTILE TOWER
                if (tower.isProjectileTower()){
                    if ( time % tower.getCadence() == 0)
                        tower.shot();
                }

                // CAS B : UNE TOUR NONPROJECTILE TOWER
                else{
                    if (time % tower.getCadence() == 0){
                        tower.freeze();
                    }
                }
            }

            // 4. PAUSE POUR LE RENDU (50ms = 20 FPS)
            try { 
                Thread.sleep(50); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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