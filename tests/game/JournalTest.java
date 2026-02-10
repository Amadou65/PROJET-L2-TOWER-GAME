package game;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class JournalTest {

    private Journal journal;

    @BeforeEach
    public void setUp() {
        journal = new Journal();
    }

    @Test
    public void testInitialStatistics() {
        assertEquals(0, journal.getBalloonsDestroyed());
        assertEquals(0, journal.getTotalCreditsGained());
        assertEquals(0, journal.getTowersPurchased());
        assertEquals(0, journal.getUpgradesPurchased());   
        assertEquals(0, journal.getTotalCreditsSpent());
    }

    @Test
    public void testRecordBalloonDestroyed() {
        journal.recordBalloonDestroyed();
        assertEquals(1, journal.getBalloonsDestroyed());
        assertEquals(10, journal.getTotalCreditsGained());
    }

    @Test
    public void testRecordTowerPurchased() {
        journal.recordTowerPurchased(100);
        assertEquals(1, journal.getTowersPurchased());
        assertEquals(100, journal.getTotalCreditsSpent());
    }
}