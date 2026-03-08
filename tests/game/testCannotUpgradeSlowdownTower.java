import game.*;
import game.board.ClassicalBoard;
import game.Evolution.EvolutionType;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@Test
public void testCannotUpgradeSlowdownTower() {
    Player p = new Player();
    Board b = new ClassicalBoard(5, 5);
    Tower t = new SlowdownTower();
    Evolution e = new Evolution(150, EvolutionType.SCOPE);
    int creditsInitiaux = p.getCredits();

    p.buyUpgrade(t, b, new Position(0, 0), e);

    assertEquals(creditsInitiaux, p.getCredits(), 
        "ERREUR : La tour de ralentissement a été améliorée (interdit).");
    System.out.println("SUCCÈS : L'interdiction d'évolution sur SlowdownTower est respectée.");
}