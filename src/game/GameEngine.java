package game;

import java.util.*;

public class GameEngine {
    private ArrayList<Balloon> reserve;
    private ArrayList<Balloon> actif;
    private ArrayList<Position> path;
    private Board board;
    private Player player;

    public GameEngine(ArrayList<Balloon> reserve, ArrayList<Position> path, Board board) {
        this.reserve = reserve;
        this.actif = new ArrayList<>();
        this.path = path;
        this.board = board;
        this.player = new Player(); // Initialisation du joueur
    }

    /**
     * Méthode principale qui lance la boucle de jeu
     */
    public void game() {
        int time = 0;
        System.out.println("--- DÉMARRAGE DE LA PARTIE ---");

        // La boucle s'arrête si plus d'ennemis OU si le joueur meurt
        while ((!reserve.isEmpty() || !actif.isEmpty()) && player.isAlife()) {
            time++;

            // 1. APPARITION (Tous les 20 tics)
            if (time % 20 == 0 && !reserve.isEmpty()) {
                Balloon b = reserve.remove(reserve.size() - 1);
                this.actif.add(b);
                
                // Placement initial sur la grille
                board.getCell(new Position(b.getGridX(), b.getGridY())).putBallon(b);
                System.out.println("[SPAWN] Un nouveau ballon entre en jeu !");
            }

            // 2. MISE À JOUR DES BALLONS (Boucle inversée pour la sécurité)
            for (int i = actif.size() - 1; i >= 0; i--) {
                Balloon b = actif.get(i);
                
                // On mémorise la position de grille AVANT le mouvement
                int oldX = b.getGridX();
                int oldY = b.getGridY();

                b.move(); // Déplacement fluide (mathématiques de ton camarade)

                // On vérifie l'état du ballon APRÈS le mouvement
                if (b.isPopped()) {
                    System.out.println("[BOOM] Un ballon a été éclaté ! Bravo !");
                    player.setCredits(player.getCredits() + 10);
                    board.getCell(new Position(oldX, oldY)).removeBallon(b);
                    actif.remove(i);
                } 
                else if (b.hasReachedEnd()) {
                    System.out.println("[OUCH] Un ballon a franchi la ligne d'arrivée !");
                    player.onHit(); // Perte de vie
                    board.getCell(new Position(oldX, oldY)).removeBallon(b);
                    actif.remove(i);
                } 
                else if (oldX != b.getGridX() || oldY != b.getGridY()) {
                    // CHANGEMENT DE CASE : Le ballon a franchi la limite visuelle de la case
                    board.getCell(new Position(oldX, oldY)).removeBallon(b);
                    board.getCell(new Position(b.getGridX(), b.getGridY())).putBallon(b);
                }
            }

            // 3. PAUSE (Pour que le jeu soit visible dans la console)
            try {
                Thread.sleep(50); // Environ 20 tics par seconde
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 4. FIN DE PARTIE
        System.out.println("--- FIN DE LA PARTIE ---");
        if (!player.isAlife()) {
            System.out.println("GAME OVER... Le joueur n'a plus de PV.");
        } else {
            System.out.println("VICTOIRE ! Tous les ballons ont été neutralisés.");
        }
    }
}