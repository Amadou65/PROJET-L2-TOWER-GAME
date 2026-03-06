package game;
import game.board.ClassicalBoard;
import game.tower.typeTower.DartMonkey;
import game.tower.ProjectileTower;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    public void testGetersPlayer(){
        Player p = new Player();

        assertEquals(20, p.getHealth());
        assertEquals(2500, p.getCredits());

        p.onHit();
        assertEquals(19, p.getHealth());

        assertTrue(p.isAlife());
    }

    @Test
    public void testSellBuyTower(){
        Player p = new Player();
        Tower t = new DartMonkey("drt", new Position(0, 0));
        Position pos = new Position(0, 0);
        Board b = new ClassicalBoard(6, 11);

        p.buyTower(t, pos, b);
        assertEquals(2300, p.getCredits());

        p.sellTower(t, b, pos);
        assertEquals(2500, p.getCredits());
    }

    @Test
    public void testBuyEvolution(){
        Player p = new Player();
        Board b = new ClassicalBoard(6, 11);
        ProjectileTower t = new DartMonkey("drt", new Position(0, 0));
        Evolution e = new Evolution(100, Evolution.EvolutionType.SCOPE);

        p.buyEvolution(t, b, t.getPosition(),e);

        // Check that the player's credits have been reduced by the cost of the evolution
        assertEquals(2400, p.getCredits());

        // Check that the tower has the evolution applied
        assertTrue(t.hasEvolution(Evolution.EvolutionType.SCOPE));
        assertFalse(t.hasEvolution(Evolution.EvolutionType.POWER));

        // Check that the player's journal has recorded the evolution purchase
        assertEquals(1, p.getJournal().getNbTypeEvolution(Evolution.EvolutionType.SCOPE));
        assertEquals(0, p.getJournal().getNbTypeEvolution(Evolution.EvolutionType.POWER));
        
        // Check that the total number of evolutions purchased is recorded correctly
        assertEquals(1, p.getJournal().getNbTypeEvolution(Evolution.EvolutionType.ALL));
    }
}