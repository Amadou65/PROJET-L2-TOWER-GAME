import game.*;
import game.board.ClassicalBoard;
import game.Evolution.EvolutionType;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@Test
public void testEvolutionUnique() {
    Player p = new Player();
    Board b = new ClassicalBoard(5, 5);
    Tower t = new DartMonkey();
    Evolution e = new Evolution(100, EvolutionType.SCOPE);

    // Premier achat
    p.buyUpgrade(t, b, new Position(0, 0), e);
    int creditsApresPremier = p.getCredits();

    // Tentative de second achat identique
    p.buyUpgrade(t, b, new Position(0, 0), e);

    assertEquals(creditsApresPremier, p.getCredits(), 
        "ERREUR : Le joueur a été débité une deuxième fois pour la même évolution.");
    System.out.println("SUCCÈS : Le blocage du doublon d'évolution fonctionne.");
}