package game;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import game.tower.*;
import game.tower.typeTower.*;
import game.exeptions.*;
import game.Evolution.EvolutionType;
import game.board.*;

public class Livrable5Test {

    private Player player;
    private Board board;

    @BeforeEach
    public void setUp() throws NegativeValueException, ZeroValueException {
        player = new Player();
        // On initialise un plateau classique pour les tests
        board = new ClassicalBoard(10, 10);
    }

    @Test
    @DisplayName("Test : L'achat d'une tour réduit bien les crédits")
    public void testBuyTowerViaPlayerReducesCredits() {
        Position pos = new Position(1, 1);
        Tower t = new DartMonkey("Dart", pos);
        int initialCredits = player.getCredits();
        
        player.buyTower(t, pos, board);
        
        assertEquals(initialCredits - t.getCost(), player.getCredits());
        assertTrue(board.tower_list.contains(t));
    }

    @Test
    @DisplayName("Test : La vente d'une tour rembourse le coût total")
    public void testSellTowerRefundsFullCost() {
        Position pos = new Position(2, 2);
        Tower t = new DartMonkey("Dart", pos);
        player.buyTower(t, pos, board);
        int creditsAfterBuy = player.getCredits();
        
        player.sellTower(t, board, pos);
        
        assertEquals(creditsAfterBuy + t.getCost(), player.getCredits());
        assertFalse(board.tower_list.contains(t));
    }

    @Test
    @DisplayName("Test : L'achat d'une évolution est bien appliqué")
    public void testEvolveTowerAppliesCorrectly() throws TypeTowerException {
        ProjectileTower t = new DartMonkey("Dart", new Position(0,0));
        Evolution evo = new Evolution(250, EvolutionType.POWER);
        
        player.buyEvolution(t, evo);
        
        assertTrue(t.hasEvolution(EvolutionType.POWER));
        assertEquals(2500 - 250, player.getCredits());
    }

    @Test
    @DisplayName("Test : La vente d'une évolution rembourse les crédits")
    public void testSellEvolutionRefundsCredits() throws TypeTowerException, NoEvolutionException {
        ProjectileTower t = new DartMonkey("Dart", new Position(0,0));
        Evolution evo = new Evolution(250, EvolutionType.POWER);
        
        player.buyEvolution(t, evo);
        int creditsBeforeSell = player.getCredits();
        
        player.sellEvolution(t, evo);
        
        assertEquals(creditsBeforeSell + 250, player.getCredits());
        assertFalse(t.hasEvolution(EvolutionType.POWER));
    }

    @Test
    @DisplayName("Test : Impossible d'évoluer une tour de glace (Exception)")
    public void testCannotUpgradeIceTower() {
        IceTower ice = new IceTower("Ice", new Position(3,3));
        Evolution evo = new Evolution(100, EvolutionType.SCOPE);
        
        assertThrows(TypeTowerException.class, () -> {
            player.buyEvolution(ice, evo);
        });
    }

    @Test
    @DisplayName("Test : Impossible de vendre une évolution non possédée")
    public void testSellEvolutionNotOwned() {
        ProjectileTower t = new DartMonkey("Dart", new Position(0,0));
        Evolution evo = new Evolution(250, EvolutionType.POWER);
        
        assertThrows(NoEvolutionException.class, () -> {
            player.sellEvolution(t, evo);
        });
    }
}
