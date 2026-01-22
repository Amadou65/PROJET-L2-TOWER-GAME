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
}