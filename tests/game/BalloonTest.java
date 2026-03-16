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
        ArrayList<Position> path = new ArrayList<>();
        path.add(new Position(0,0));
        path.add(new Position(0,1));

        Balloon fast = new Balloon(4, path);
        Balloon slow = new Balloon(1, path);


        // On vérifie que la logique de vitesse est respectée
        fast.move();
        slow.move();
        assertTrue(fast.getDistance() > slow.getDistance(), "Le ballon niveau 4 doit avancer plus vite");
    }

    @Test
    public void testFreezeIsTemporary() {
        ArrayList<Position> path = new ArrayList<>();
        path.add(new Position(0,0));
        path.add(new Position(0,1));
        path.add(new Position(0,2));

        Balloon b = new Balloon(1, path);
        b.freeze(2);

        b.move();
        assertEquals(0.0, b.getDistance(), 0.0001);
        assertTrue(b.isFrozen());

        b.move();
        assertEquals(0.0, b.getDistance(), 0.0001);
        assertFalse(b.isFrozen());

        b.move();
        assertTrue(b.getDistance() > 0.0, "Le ballon doit repartir après la fin du gel");
    }
}
