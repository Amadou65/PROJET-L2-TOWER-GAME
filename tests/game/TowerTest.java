package game;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import game.Evolution.EvolutionType;
import game.exeptions.TypeTowerException;
import game.tower.typeTower.*;
import game.tower.*;

public class TowerTest {
    @Test
    public void testTowerConstructorAndGetters() {
        Tower t = new DartMonkey("drt", new Position(0, 0));

        assertEquals("drt", t.getNom());
        assertEquals(200, t.getCost());
        assertEquals(20, t.getCadence());
        assertEquals(1, t.getScope());
        assertEquals(1, t.getPower());
    }

    @Test
    public void testFindTargets() {
        // Create a tower and some balloons
        Tower t = new DartMonkey("drt", new Position(0, 0));

        ArrayList<Position> path1 = new ArrayList<>();
        path1.add(new Position(0, 0));
        path1.add(new Position(0, 1));

        ArrayList<Position> path2 = new ArrayList<>();
        path2.add(new Position(5, 5));
        path2.add(new Position(0, 1));

        ArrayList<Position> path3 = new ArrayList<>();
        path3.add(new Position(1, 0));
        path3.add(new Position(0, 1));

            
        Balloon b1 = new Balloon(0, path1); // within scope
        Balloon b2 = new Balloon(2, path2); // outside scope
        Balloon b3 = new Balloon(1, path3); // within scope
    
        List<Balloon> balloons = List.of(b1, b2, b3);
    
        // Set tower position
        t.position = new Position(0, 0);
    
        // Find targets
        List<Balloon> targets = TargetingBalloon.getAllTargets(balloons, t);
    
        // Check that only the balloons within scope are returned
        assertTrue(targets.contains(b1));
        assertTrue(targets.contains(b3));
        assertFalse(targets.contains(b2));
    }
    
    @Test
    public void EvolutionTest(){
        ProjectileTower t = new DartMonkey("drt", new Position(0, 0));
        Evolution e = new Evolution(300, EvolutionType.SCOPE);

        assertEquals(1, t.getScope());
        t.getEvolution(e);
        assertEquals(2, t.getScope());

    }

    @Test
    public void testDoubleEvolutionImpossible() {
        ProjectileTower t = new DartMonkey("drt", new Position(0, 0));
        Evolution e = new Evolution(300, EvolutionType.SCOPE);

        // Premier achat
        t.getEvolution(e);
        assertEquals(2, t.getScope(), "La portée devrait être de 2");

        // Deuxième achat
        t.getEvolution(e);
        assertEquals(2, t.getScope(), "La portée ne doit pas augmenter une seconde fois pour la même évolution !");
    }

    @Test
    public void testPlayerMoneyForUpgrade() throws TypeTowerException {
        Player p = new Player();
        p.setCredits(100); 
        
        Tower t = new DartMonkey("drt", new Position(0, 0));
        Evolution e = new Evolution(500, EvolutionType.POWER);
        
        // On essaie d'acheter via le Player
        p.buyEvolution(t, e);
        
        // On vérifie que la puissance n'a pas augmenté
        assertEquals(1, t.getPower(), "La tour ne devrait pas évoluer si le joueur est pauvre");
        assertEquals(100, p.getCredits(), "Le solde ne devrait pas avoir bougé");
    }

    @Test
    public void testTargetingUsesPreciseBalloonPosition() {
        Tower t = new DartMonkey("drt", new Position(0, 0));

        ArrayList<Position> path = new ArrayList<>();
        path.add(new Position(0, 1));
        path.add(new Position(0, 2));

        Balloon b = new Balloon(1, path);
        for (int i = 0; i < 8; i++) {
            b.move();
        }

        assertTrue(b.getY() > 1.0, "Le ballon doit être hors de portée réelle");
        assertTrue(TargetingBalloon.calculateDistance(t, b) > t.getScope());
        assertTrue(TargetingBalloon.getAllTargets(List.of(b), t).isEmpty(),
                "Le ciblage ne doit plus utiliser les coordonnées arrondies.");
    }
}
