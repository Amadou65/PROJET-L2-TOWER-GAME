import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class RandomBoardTest {
    private RandomBoard board;
    @BeforeEach
    public void before(){
        this.board = new RandomBoard();
    }
    @Test
    public void TestnextPositions(){
        
        ArrayList nextPo = new ArrayList<>();
        nextPo.add(new Position(1,0));
        nextPo.add(new Position(0,1));
        assertEquals(board.nextPositions(new Position(0,0)), nextPo);
    }
}