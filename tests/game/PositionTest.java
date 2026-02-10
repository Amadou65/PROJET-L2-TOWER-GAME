package game;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    public void testPositionConstrucrotAndGet() {
        Position pos = new Position(2, 3);

        assertEquals(2, pos.getX());
        assertEquals(3, pos.getY());
    }

    @Test
    public void testPositionEquality() {
        Position pos1 = new Position(2, 3);
        Position pos2 = new Position(2, 3);
        Position pos3 = new Position(3, 2);

        assertTrue(pos1.equals(pos2));
        assertFalse(pos1.equals(pos3));
    }

    @Test
    public void testPositionDisplay() {
        Position pos = new Position(1, 1);
        assertEquals("(1, 1)", pos.toString());
    }
}