package game;

import java.util.*;
import game.board.RandomBoard;

public class Livrable2a {
    public static void main(String[] args) {

        System.out.println("=== Démarrage du Test Livrable 2a ===");

        // 1. On crée le plateau
        RandomBoard board = new RandomBoard(5, 5);
        
        // Affiche le plateau au tout début pour vérifier la génération
        System.out.println(board.display());

        // 2. CRUCIAL : On récupère le chemin généré par le board !
        // (Vérifie que ta classe Board a bien une méthode getPath())
        ArrayList<Position> path = board.path(); 

        if (path == null || path.isEmpty()) {
            System.out.println("Erreur : Le plateau n'a pas généré de chemin !");
            return;
        }

        // 3. On crée les ballons avec le VRAI chemin
        ArrayList<Balloon> balloons = new ArrayList<>();
        balloons.add(new Balloon(1, path));
        balloons.add(new Balloon(2, path));
        balloons.add(new Balloon(3, path));
        balloons.add(new Balloon(1, path));

        System.out.println("Nombre de ballons en réserve : " + balloons.size());

        // 4. On lance le moteur
        GameEngine gameEngine = new GameEngine(balloons, path, board);
        gameEngine.game();

        System.out.println("=== Fin du Test Livrable 2a ===");
    }
}