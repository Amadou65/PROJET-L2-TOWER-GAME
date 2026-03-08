import game.*;
import game.board.ClassicalBoard;
import game.Evolution.EvolutionType;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@Test
public void testEvolutionProjectile() {
    Player p = new Player();
    Board b = new ClassicalBoard(5, 5);
    Tower t = new BombTower();
    Evolution e = new Evolution(400, EvolutionType.PROJECTILE);

    p.buyUpgrade(t, b, new Position(0, 0), e);

    assertEquals(1, p.getJournal().getNbTypeEvolution(EvolutionType.PROJECTILE), 
        "ERREUR : Le changement de projectile n'a pas été enregistré.");
    System.out.println("SUCCÈS : Méthode Projectile validée.");
}