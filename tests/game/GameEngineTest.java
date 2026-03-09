package game;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import game.board.ClassicalBoard;
import game.tower.typeTower.DartMonkey;

public class GameEngineTest {

    private List<Balloon> reserve;
    private Board board;
    private GameEngine engine;

    @BeforeEach
    public void setUp() {
        // Configuration d'un plateau simple 5x5
        board = new ClassicalBoard(5, 5);
        List<Position> path = board.path();
        board.applyPathToGrid(path);
        path = board.path();

        // Initialisation d'une réserve de 2 ballons
        reserve = new ArrayList<>();
        reserve.add(new Balloon(1, path));
        reserve.add(new Balloon(2, path));

        engine = new GameEngine(reserve, board);
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
        assertDoesNotThrow(() -> engine.game(), "La méthode game() ne devrait pas lever d'exception.");
    }

    @Test
    @DisplayName("Vérification de la gestion des ballons dans la réserve")
    public void testReserveDepletion() {
        // Avant de lancer, la réserve a 2 ballons
        assertEquals(2, reserve.size());

        // On lance le moteur
        engine.game();

        // Après le jeu, la réserve doit être vide
        assertTrue(reserve.isEmpty(), "La réserve de ballons devrait être vide à la fin de la partie.");
    }

    @Test
    @DisplayName("Test de l'état du joueur après une partie")
    public void testPlayerStateAfterGame() {
        // On lance le jeu
        engine.game();
    }

    /**
     * Vérifie que le joueur perd au moins une vie quand un ballon s'échappe.
     * Sur un plateau 5x5 sans tour, tous les ballons passent : le joueur doit
     * avoir moins de 20 vies à la fin.
     */
    @Test
    @DisplayName("Le joueur perd une vie quand un ballon s'échappe")
    public void testPlayerLosesLifeWhenBalloonEscapes() {
        Player player = new Player();
        GameEngine engineWithPlayer = new GameEngine(reserve, board, player);
        engineWithPlayer.game();
        // Sans tour, les ballons s'échappent → le joueur perd des vies
        assertTrue(player.getHealth() < 20,
                "Le joueur devrait avoir perdu au moins une vie.");
    }


}