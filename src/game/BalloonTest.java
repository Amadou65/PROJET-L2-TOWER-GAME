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

        // on simule un dégat de 2
        b.takeDamage(2);
        assertEquals(2, b.getHealth(), "Aprés 2 dégats, la santé doit etre de 2 ");
        assertFalse(b.isPopped(), "le ballon n'est doit pas etre eclaté s'il reste de la santé");

        // On finit le ballon
        b.takeDamage(2);
        assertTrue(b.isPopped(), "Le ballon doit être éclaté quand la santé atteint 0");



    }

    @Test 
    public void testEvolutionVitesse() {
        // on crée deux ballons de niveaux differents
        Balloon rose = new Balloon(4, dummyPath); // Niveau 4
        Balloon rouge = new Balloon(1, dummyPath); // Niveau 1

        
    }
    
}
