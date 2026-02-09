package game;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;


public class BalloonTest {

    private List<Position> dummyPath;

    @BeforeEach
    public void setUp() {
        // on crée un petit chemin fictif pour le test
        dummyPath = new ArrayList<>();
        dummyPath.add(new Position(0,0));
        dummyPath.add(new Position(0,1));
        dummyPath.add(new Position(0,2));
    }

    @Test
    public void testMutationEtSante(){
        // creation d'un ballon niveau 4(Rose)
        Balloon b = new Balloon(4, dummyPath);
        assertEquals(4, b.getHealth(), "la santé initiale devrait etre de 4 ");

        

    }
    
}
