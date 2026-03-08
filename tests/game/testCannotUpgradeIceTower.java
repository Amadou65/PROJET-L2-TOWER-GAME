import game.*;
import game.board.ClassicalBoard;
import game.Evolution.EvolutionType;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@Test
public void testCannotUpgradeIceTower() {
    Player p = new Player();
    Board b = new ClassicalBoard(5, 5);
    Tower t = new IceTower(); 
    Evolution e = new Evolution(250, EvolutionType.POWER);
    int creditsInitiaux = p.getCredits();

    p.buyUpgrade(t, b, new Position(0, 0), e);

    assertEquals(creditsInitiaux, p.getCredits(), 
        "ERREUR : La tour de glace a été améliorée (interdit).");
    System.out.println("SUCCÈS : L'interdiction d'évolution sur IceTower est respectée.");
}