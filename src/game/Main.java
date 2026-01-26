package game;

import game.board.RandomBoard;
import game.board.ClassicalBoard;


public class Main {
    public static void main(String[] args) {
        System.out.println("=== TEST DU LIVRABLE 1 : TOWER DEFENSE ===\n");
    }

    // 1. Test du Mode A : RandomBoard (Chemin calculé)
        System.out.println("--- MODE A : PLATEAU ALÉATOIRE ---");
        Board modeA = new RandomBoard(6, 11);
        modeA.display();
        System.out.println("\n");

        // 2. Test du Mode B : ClassicalBoard (Plateau libre)
        System.out.println("--- MODE B : PLATEAU CLASSIQUE ---");
}
