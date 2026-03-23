package game;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import game.choice.evolutionchoice.*;


public class ChoiceTest {
    @Test
    public void testEvolutionChoice() {
        Cadence cadenceChoice = new Cadence();

        assertEquals(Evolution.EvolutionType.CADENCE, cadenceChoice.getChoice(), "ERREUR : La méthode getChoice() de Cadence ne retourne pas le bon type d'évolution.");
        System.out.println("SUCCÈS : La méthode getChoice() de Cadence retourne le bon type d'évolution.");
    }
}