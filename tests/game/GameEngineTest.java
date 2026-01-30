package game;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import game.board.Board;
import game.board.ClassicalBoard;

public class GameEngineTest {

    private List<Balloon> reserve;
    private List<Position> path;
    private Board board;
    private GameEngine engine;

    @BeforeEach
    public void setUp() {
        // Configuration d'un plateau simple 5x5
        board = new ClassicalBoard(5, 5);
        board.applyPathToGrid();
        path = board.path();
        
        // Initialisation d'une réserve de 2 ballons
        reserve = new ArrayList<>();
        reserve.add(new Balloon(1, path));
        reserve.add(new Balloon(2, path));
        
        engine = new GameEngine(reserve, path, board);
    }

    @Test
    @DisplayName("Test de l'initialisation du GameEngine")
    public void testInitialization() {
        // On vérifie que l'objet est bien créé
        assertNotNull(engine, "Le moteur de jeu devrait être instancié.");
    }

    @Test
    @DisplayName("Test d'une partie courte jusqu'à la fin")
    public void testGameFullRun() {
        // On lance le jeu. Avec 2 ballons et un petit plateau, 
        // cela devrait se terminer rapidement.
        assertDoesNotThrow(() -> engine.game(), "La méthode game() ne devrait pas lever d'exception.");
    }