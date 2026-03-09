package game;

import game.*;
import game.exeptions.*;
import game.tower.typeTower.*;
import game.Evolution.EvolutionType;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class testCannotUpgradeSlowdownTower {
    @Test
    public void testCannotUpgradeSlowdownTower() throws TypeTowerException {
        Player p = new Player();
        Tower t = new SlowdownTower("st", new Position(0, 0));
        Evolution e = new Evolution(150, EvolutionType.SCOPE);
        int creditsInitiaux = p.getCredits();

        assertThrows(TypeTowerException.class, () -> p.buyEvolution(t, e));

        assertEquals(creditsInitiaux, p.getCredits(), 
            "ERREUR : La tour de ralentissement a été améliorée (interdit).");
        System.out.println("SUCCÈS : L'interdiction d'évolution sur SlowdownTower est respectée.");
}
}