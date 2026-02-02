package game;
import game.Player;
import game.board.ClassicalBoard;
import game.tower.*;

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
        Tower t = new ProjectileTower();
        t.cost = 100;
        Position pos = new Position(0, 0);
        Board b = new ClassicalBoard(6, 11);

        p.buyTower(t, pos, b);
        assertEquals(2400, p.getCredits());

        p.sellTower(t, b, pos);
        assertEquals(2500, p.getCredits());
    }
}