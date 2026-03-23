package game;

import game.exeptions.*;
import game.tower.typeTower.*;
import game.Evolution.EvolutionType;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class TestCannotUpgradeIceTower{
    @Test
    public void testCannotUpgradeIceTower() throws TypeTowerException {
        Player p = new Player();
        Tower t = new IceTower("it", new Position(0, 0)); 
        Evolution e = new Evolution(250, EvolutionType.POWER);
        int creditsInitiaux = p.getCredits();

        assertThrows(TypeTowerException.class, () -> p.buyEvolution(t, e));

        assertEquals(creditsInitiaux, p.getCredits(), 
            "ERREUR : La tour de glace a été améliorée (interdit).");
        System.out.println("SUCCÈS : L'interdiction d'évolution sur IceTower est respectée.");
}

}