package game;

import game.exeptions.*;
import game.tower.typeTower.*;
import game.Evolution.EvolutionType;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestEvolutionPower {
@Test
public void testEvolutionPower() throws TypeTowerException {
    Player p = new Player();
    Tower t = new DartMonkey("dm", new Position(0, 0));
    Evolution e = new Evolution(250, EvolutionType.POWER);

    p.buyEvolution(t, e);

    assertEquals(1, p.getJournal().getNbTypeEvolution(EvolutionType.POWER), 
        "ERREUR : L'évolution de puissance n'a pas été enregistrée.");
    System.out.println("SUCCÈS : Méthode Power validée.");
}

}