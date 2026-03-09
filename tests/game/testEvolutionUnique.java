package game;

import game.exeptions.*;
import game.board.ClassicalBoard;
import game.tower.typeTower.*;
import game.Evolution.EvolutionType;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class testEvolutionUnique {
    @Test
    public void testEvolutionUnique() throws TypeTowerException{
        Player p = new Player();
        Board b = new ClassicalBoard(5, 5);
        Tower t = new DartMonkey("dm", new Position(0, 0));
        Evolution e = new Evolution(100, EvolutionType.SCOPE);

        // Premier achat
        p.buyEvolution(t, e);
        int creditsApresPremier = p.getCredits();

        // Tentative de second achat identique
        p.buyEvolution(t, e);
        assertEquals(creditsApresPremier, p.getCredits(), 
            "ERREUR : Le joueur a été débité une deuxième fois pour la même évolution.");
        System.out.println("SUCCÈS : Le blocage du doublon d'évolution fonctionne.");
    }
}