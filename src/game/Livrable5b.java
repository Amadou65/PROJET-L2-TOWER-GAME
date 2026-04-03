package game;

import java.util.*;
import game.board.ClassicalBoard;
import game.exeptions.NegativeValueException;
import game.exeptions.ZeroValueException;
import game.listchooser.InteractiveListChooser;
import game.exeptions.TooLongPathException;
/**
 * Livrable5b : scénario B du Livrable 5.
 * <p>
 * Crée un plateau classique (chemins rectilignes), exécute la phase
 * d'actions du joueur avec des choix interactifs (InteractiveListChooser),
 * puis lance la manche.
 * </p>
 *
 * <pre>
 * java -jar livrable5b.jar &lt;hauteur&gt; &lt;largeur&gt; &lt;nbChemins&gt;
 * </pre>
 */
public class Livrable5b extends Livrable5 {

    public static void main(String[] args)
            throws ZeroValueException, NegativeValueException, TooLongPathException {
        int height = args.length > 0 ? Integer.parseInt(args[0]) : 8;
        int width = args.length > 1 ? Integer.parseInt(args[1]) : 12;
        int nbChemins = args.length > 2 ? Integer.parseInt(args[2]) : 3;

        if(nbChemins < 0) {
            throw new NegativeValueException("Le nombre de chemins ne peut pas être négatif.");
        }
        if(nbChemins >= (int)(height*width)) {
            throw new TooLongPathException("Le nombre des chemin sont trop grand");
        }

        // Validation des arguments
        if (height <= 0 || width <= 0 || nbChemins <= 0) {
            System.err.println("Erreur : tous les arguments doivent être strictement positifs.");
            System.err.println("Usage : java -jar livrable5b.jar <hauteur> <largeur> <nbChemins>");
            return;
        }
        if (nbChemins > height) {
            System.out.println("⚠️  Le nombre de chemins demandé (" + nbChemins
                    + ") dépasse la hauteur du plateau (" + height
                    + "). Limité à " + height + " chemins.");
            nbChemins = height;
        }

        System.out.println("=== LIVRABLE 5B ===");
        System.out.println("Plateau classique : " + height + "x" + width
                + " | Chemins : " + nbChemins);

        // --- PHASE 1 : Plateau classique avec PLUSIEURS chemins rectilignes ---
        ClassicalBoard board = new ClassicalBoard(height, width);

        // Générer N chemins distincts
        List<List<Position>> allPaths = new ArrayList<>();
        int attempts = 0;
        while (allPaths.size() < nbChemins && attempts < 200) {
            attempts++;
            List<Position> path = board.generateNewPath();

            // Vérifier que ce chemin n'est pas un doublon (même point de départ)
            boolean duplicate = false;
            for (List<Position> used : allPaths) {
                if (used.get(0).equals(path.get(0))) {
                    duplicate = true;
                    break;
                }
            }
            if (duplicate) continue;

            allPaths.add(path);
            board.applyPathToGrid(path);
        }

        System.out.println(allPaths.size() + " chemins distincts générés :");
        for (int i = 0; i < allPaths.size(); i++) {
            List<Position> p = allPaths.get(i);
            System.out.println("  Chemin " + (i + 1) + " : " + p.get(0)
                    + " → " + p.get(p.size() - 1) + " (" + p.size() + " cases)");
        }
        System.out.println(board.display());
        // --- PHASE 2 : Phase d'actions du joueur (choix interactifs) ---
        Player player = new Player();
        InteractiveListChooser<Object> chooser = new InteractiveListChooser<>();
        Livrable5.playerActionPhase(board, player, chooser, height, width);
         // --- PHASE 3 : Créer les ballons et lancer la manche ---
        // --- Créer un ballon par chemin pour cette manche ---
        Random rng = new Random();
        int[] levels = { 1, 2, 4 };
            List<Balloon> reserve = new ArrayList<>();
            for (int i = 0; i < allPaths.size(); i++) {
                int lvl = levels[rng.nextInt(levels.length)];
                reserve.add(new Balloon(lvl, allPaths.get(i)));
            }

        System.out.println("\n--- LANCEMENT DE LA MANCHE ---");
        GameEngine engine = new GameEngine(reserve, board, player);
        engine.game();
    }
}
