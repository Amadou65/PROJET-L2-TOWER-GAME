package game;

import game.exeptions.*;
import game.tower.typeTower.*;
import game.Evolution.EvolutionType;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestEvolutionProjectile {
@Test
public void testEvolutionProjectile() throws TypeTowerException {
    Player p = new Player();
    Tower t = new BombTower("bt", new Position(0, 0));
    Evolution e = new Evolution(400, EvolutionType.PROJECTILE);

    p.buyEvolution(t, e);

    assertEquals(1, p.getJournal().getNbTypeEvolution(EvolutionType.PROJECTILE), 
        "ERREUR : Le changement de projectile n'a pas été enregistré.");
    System.out.println("SUCCÈS : Méthode Projectile validée.");
}
}