package game;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class BalloonTest {

    @Test
    public void testBalloonMutationAndHealth() {
        ArrayList<Position> path = new ArrayList<>();
        path.add(new Position(0,0));
        path.add(new Position(0,1));

        Balloon b = new Balloon(4, path); // Un ballon Rose
        assertEquals(4, b.getLevel());

        b.takeDamage(2); // On lui tire dessus
        assertEquals(2, b.getLevel(), "Le ballon devrait être niveau 2 après 2 dégâts");
        assertFalse(b.isPopped());

        b.takeDamage(2);
        assertTrue(b.isPopped(), "Le ballon devrait être éclaté à 0 PV");
    }

    @Test

    public void testSpeedLogic() {
        
    
}
