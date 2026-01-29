package game.board;
import game.board.ClassicalBoard;

import game.Position;
import java.util.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class TestClassicalBoard {

    @Test
    public void testGetPointsBound() {
        ClassicalBoard board = new ClassicalBoard(6, 11);
        List<Position> points = board.getPointsBound();
        assertEquals(30, points.size());
        assertTrue(points.contains(points.get(0)));
    }

    @Test
    public void testPath() {
        ClassicalBoard board = new ClassicalBoard(6, 11);
        List<Position> path = board.path();
        if (path.get(0).getX() == 0){
            for (int i = 0; i < path.size(); i++){
                assertEquals(i, path.get(i).getX());
            }
        }
        else if (path.get(0).getY() == 0){
            for (int i = 0; i < path.size(); i++){
                assertEquals(i, path.get(i).getY());
            }
        }
    }
}
