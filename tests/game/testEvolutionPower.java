import game.*;
import game.board.ClassicalBoard;
import game.Evolution.EvolutionType;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@Test
public void testEvolutionPower() {
    Player p = new Player();
    Board b = new ClassicalBoard(5, 5);
    Tower t = new DartMonkey(); 
    Evolution e = new Evolution(250, EvolutionType.POWER);

    p.buyUpgrade(t, b, new Position(0, 0), e);

    assertEquals(1, p.getJournal().getNbTypeEvolution(EvolutionType.POWER), 
        "ERREUR : L'évolution de puissance n'a pas été enregistrée.");
    System.out.println("SUCCÈS : Méthode Power validée.");
}