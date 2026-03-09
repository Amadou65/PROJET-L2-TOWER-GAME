package game;

import game.exeptions.*;
import game.tower.typeTower.*;
import game.Evolution.EvolutionType;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class TestEvolutionCadence {
@Test
public void testEvolutionCadence() throws TypeTowerException {
    Player p = new Player();
    Tower t = new DartMonkey("dm", new Position(0, 0));
    Evolution e = new Evolution(150, EvolutionType.CADENCE);

    p.buyEvolution(t, e);

    assertEquals(1, p.getJournal().getNbTypeEvolution(EvolutionType.CADENCE), 
        "ERREUR : L'évolution de cadence n'a pas été enregistrée.");
    System.out.println("SUCCÈS : Méthode Cadence validée.");
}
}