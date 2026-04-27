package game;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Livrable6Test {

    @Test
    @DisplayName("Livrable 6 : chaque manche lance au moins 15 ballons")
    public void testMinimumBalloonsPerRound() throws Exception {
        Method computeBalloonCount = Livrable6.class.getDeclaredMethod("computeBalloonCount", int.class);
        computeBalloonCount.setAccessible(true);

        for (int round = 1; round <= 40; round++) {
            int count = (Integer) computeBalloonCount.invoke(null, round);
            assertTrue(count >= 15, "La manche " + round + " doit lancer au moins 15 ballons.");
        }
    }

    @Test
    @DisplayName("Livrable 6 : le nombre de ballons progresse puis reste plafonne")
    public void testBalloonProgression() throws Exception {
        Method computeBalloonCount = Livrable6.class.getDeclaredMethod("computeBalloonCount", int.class);
        computeBalloonCount.setAccessible(true);

        int round1 = (Integer) computeBalloonCount.invoke(null, 1);
        int round3 = (Integer) computeBalloonCount.invoke(null, 3);
        int round100 = (Integer) computeBalloonCount.invoke(null, 100);

        assertEquals(15, round1);
        assertTrue(round3 > round1, "La difficulte doit progresser apres les premieres manches.");
        assertEquals(45, round100, "Le nombre de ballons doit rester plafonne.");
    }

    @Test
    @DisplayName("Livrable 6 : les quatre wrappers affichent leur usage sans erreur")
    public void testWrappersUsage() {
        assertUsageContains(() -> TowerDefenseAInteractive.main(new String[] {}),
                "game.TowerDefenseAInteractive", "<largeur> <hauteur>");
        assertUsageContains(() -> TowerDefenseARandom.main(new String[] {}),
                "game.TowerDefenseARandom", "<largeur> <hauteur>");
        assertUsageContains(() -> TowerDefenseBInteractive.main(new String[] {}),
                "game.TowerDefenseBInteractive", "<largeur> <hauteur> <nbChemins>");
        assertUsageContains(() -> TowerDefenseBRandom.main(new String[] {}),
                "game.TowerDefenseBRandom", "<largeur> <hauteur> <nbChemins>");
    }

    private static void assertUsageContains(ThrowingRunnable runnable, String className, String args) {
        PrintStream originalErr = System.err;
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        try {
            System.setErr(new PrintStream(err, true, StandardCharsets.UTF_8));
            assertDoesNotThrow(() -> runnable.run());
        } finally {
            System.setErr(originalErr);
        }

        String output = err.toString(StandardCharsets.UTF_8);
        assertTrue(output.contains("Usage:"), "Le wrapper doit afficher un usage.");
        assertTrue(output.contains(className), "L'usage doit mentionner le bon point d'entree.");
        assertTrue(output.contains(args), "L'usage doit mentionner les bons arguments.");
    }

    private interface ThrowingRunnable {
        void run() throws Exception;
    }
}
