package game;
import game.Player;
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
    }
}
