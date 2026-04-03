package game.board;

import game.Position;
import game.exeptions.NegativeValueException;
import game.exeptions.ZeroValueException;

import java.util.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class TestClassicalBoard {

    @Test
    public void testGetPointsBound() throws NegativeValueException, ZeroValueException {
        ClassicalBoard board = new ClassicalBoard(6, 11);
        List<Position> points = board.getPointsBound();
        assertEquals(30, points.size());
        assertTrue(points.contains(points.get(0)));
    }

    @Test
    public void testPath() throws NegativeValueException, ZeroValueException {
        ClassicalBoard board = new ClassicalBoard(6, 11);
        List<Position> path = board.path();

        Position start = path.get(0);
        Position end = path.get(path.size() - 1);

        if (start.getX() == 0 || start.getX() == board.getHeight() - 1) {
            assertEquals(board.getHeight(), path.size());
            for (int i = 1; i < path.size(); i++) {
                assertEquals(path.get(0).getY(), path.get(i).getY());
                assertEquals(1, Math.abs(path.get(i).getX() - path.get(i - 1).getX()));
            }
            assertEquals(Math.abs(end.getX() - start.getX()), path.size() - 1);
        } else {
            assertEquals(board.getWidth(), path.size());
            for (int i = 1; i < path.size(); i++) {
                assertEquals(path.get(0).getX(), path.get(i).getX());
                assertEquals(1, Math.abs(path.get(i).getY() - path.get(i - 1).getY()));
            }
            assertEquals(Math.abs(end.getY() - start.getY()), path.size() - 1);
        }
    }

    @Test
    public void testPathIsStableAcrossCalls() throws NegativeValueException, ZeroValueException {
        ClassicalBoard board = new ClassicalBoard(6, 11);
        List<Position> firstPath = board.path();
        List<Position> secondPath = board.path();

        assertEquals(firstPath, secondPath);
    }

    @Test
    public void testExceptions(){
        assertThrows(NegativeValueException.class, () -> new ClassicalBoard(-1,2));

        assertThrows(ZeroValueException.class, () -> new ClassicalBoard(2,0));
    }
}
