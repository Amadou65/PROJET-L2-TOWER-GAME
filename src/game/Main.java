package game;

import game.board.RandomBoard;
import game.exeptions.NegativeValueException;
import game.exeptions.ZeroValueException;
import game.board.ClassicalBoard;


public class Main {
    public static void main(String[] args) throws ZeroValueException, NegativeValueException {
        System.out.println("=== TEST DU LIVRABLE 1 : TOWER DEFENSE ===\n");
    

    // 1. Test du Mode A : RandomBoard (Chemin calculé)
        System.out.println("--- MODE A : PLATEAU ALÉATOIRE ---");
        Board modeA = new RandomBoard(6,11);
        modeA.applyPathToGrid(modeA.path());
        System.out.println(modeA.display());
        System.out.println("\n");

        // 2. Test du Mode B : ClassicalBoard (Plateau libre)
        System.out.println("--- MODE B : PLATEAU CLASSIQUE ---");
        Board modeB = new ClassicalBoard(6,11);
        modeB.applyPathToGrid(modeB.path());
        System.out.println(modeB.display());


        System.out.println("\n=== FIN DES TESTS DU LIVRABLE 1 ===");

    }

    
}
