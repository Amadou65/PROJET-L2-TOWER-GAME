package game;

public class Journal {
    // Statistiques des ballons
    private int balloonsDestroyed;
    private int creditGainedTotal;
    private int creditUsedTotal;
    private int towersPurchased;
    private int upgradesPurchased;
    private int healthLost;
    private int healthRestore;

    /**
     * Constructeur par défaut
     */
    public Journal() {
        resetStatistics();
    }

    /**
     * Réinitialise toutes les statistiques à 0
     */
    public void resetStatistics() {
        this.balloonsDestroyed = 0;
        this.creditGainedTotal = 0;
        this.creditUsedTotal = 0;
        this.towersPurchased = 0;
        this.upgradesPurchased = 0;
        this.healthLost = 0;
        this.healthRestore = 0;
    }

    public void recordBalloonDestroyed() {
        this.balloonsDestroyed++;
        this.healthRestore++;
        this.creditGainedTotal += 10;
    }

    public void recordTowerPurchased(int towerCost) {
        this.towersPurchased++;
        this.creditUsedTotal += towerCost;
    }

    public void recordUpgradeApplied(int upgradeCost) {
        this.upgradesPurchased++;
        this.creditUsedTotal += upgradeCost;
    }

    /**
     * Records that the player lost one health point.
     */
    public void recordHealthLost() {
        this.healthLost++;
    }

    // Getters

    public int getBalloonsDestroyed() {
        return balloonsDestroyed;
    }

    public int getTotalCreditsGained() {
        return creditGainedTotal;
    }

    public int getTowersPurchased() {
        return towersPurchased;
    }

    public int getUpgradesPurchased() {
        return upgradesPurchased;
    }

    public int getTotalCreditsSpent() {
        return creditUsedTotal;
    }

    public int getHealthLost() {
        return healthLost;
    }

    public int getHealthRestore() {
        return healthRestore;
    }
}