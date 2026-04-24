package game;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import game.choice.EvolutionChoice;


public class ChoiceTest {
    @Test
    public void testEvolutionChoice() {
        EvolutionChoice cadenceChoice = new EvolutionChoice(Evolution.EvolutionType.CADENCE, 300);

        assertEquals(Evolution.EvolutionType.CADENCE, cadenceChoice.getType(), "ERREUR : La méthode getType() de Cadence ne retourne pas le bon type d'évolution.");
        System.out.println("SUCCÈS : La méthode getType() de Cadence retourne le bon type d'évolution.");
    }
}