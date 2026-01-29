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

        Balloon b = new Balloon(4, path); // Un ballon Rose (Niveau 4)
        assertEquals(4, b.getLevel());
    
}
