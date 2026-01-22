package game.board;
import game.board.RandomBoard;
import game.Position;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class RandomBoardTest {
    private RandomBoard board;
    private ArrayList<Position> nextPo; 
    @BeforeEach
    public void before(){
        this.board = new RandomBoard();
        this.nextPo = new ArrayList<>();
    }
    @Test
    public void TestnextPositions(){
        
        nextPo.add(new Position(1,0));
        nextPo.add(new Position(0,1));
        assertEquals(board.nextPositions(new Position(0,0)), nextPo);
    }
    @Test

    public void TestIsEdgeWhenItsOk(){
        assertTrue(board.isEdge(new Position(0, 0)));
        assertTrue(board.isEdge(new Position(0, 4)));
        assertTrue(board.isEdge(new Position(4, 0)));
        assertTrue(board.isEdge(new Position(4, 4)));
    }
       @Test
    public void TestIsEdgeWhenItsNotOk(){
        assertFalse(board.isEdge(new Position(2, 2)));
        assertFalse(board.isEdge(new Position(1, 3)));
        assertFalse(board.isEdge(new Position(3, 1)));
    }

    @Test
    public void TestIsSameSideWhenItsOk(){
        assertTrue(board.isSameSide(new Position(0, 0), new Position(0, 4)));
        assertTrue(board.isSameSide(new Position(4, 0), new Position(4, 4)));
        assertTrue(board.isSameSide(new Position(0, 0), new Position(4, 0)));
        assertTrue(board.isSameSide(new Position(0, 4), new Position(4, 4)));
    }
    @Test
    public void TestIsSameSideWhenItsNotOk(){
        assertFalse(board.isSameSide(new Position(0, 0), new Position(4, 4)));
        assertFalse(board.isSameSide(new Position(0, 4), new Position(4, 0)));
        assertFalse(board.isSameSide(new Position(0, 2), new Position(4, 0)));
        assertFalse(board.isSameSide(new Position(2, 0), new Position(0, 4)));
    }

    @Test 
    public void TestCreerListeDepart(){
        ArrayList<Position> liste_depart = board.creerListeDepart();
        assertEquals(30, liste_depart.size());
        assertTrue(liste_depart.contains(new Position(0,0)));
        assertTrue(liste_depart.contains(new Position(0,10)));
        assertTrue(liste_depart.contains(new Position(5,0)));
        assertTrue(liste_depart.contains(new Position(5,10)));
    }
    @Test
    public void TestisDoingCircle(){
        ArrayList<Position> path = board.path();
        ArrayList<Position> visited = new ArrayList<>();
        for (Position pos : path) {
            assertFalse(visited.contains(pos), "Le chemin repasse par une case déjà visitée: " + pos);
            visited.add(pos);
        }
    }
}
