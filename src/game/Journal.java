package game;

import game.Evolution.EvolutionType;

public class Journal {
    // Statistiques des ballons
    private int balloonsDestroyed;
    private int creditGainedTotal;
    private int creditUsedTotal;
    private int towersPurchased;
    private int towerSold;
    private int evolutionsPurchased;
    private int evolutionSold;
    private int healthLost;
    private int healthRestore;
    // ndEvolution par type
    private int nbEvolSCOPE;
    private int nbEvolPOWER;
    private int nbEvolCADENCE;
    private int nbEvolPROJECTILE;

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
        this.towerSold = 0;
        this.healthLost = 0;
        this.healthRestore = 0;
        
        // nbEvolutions total
        this.evolutionsPurchased = 0;
        this.evolutionSold = 0;
        // ndEvolution par type
        this.nbEvolSCOPE = 0;
        this.nbEvolPOWER = 0;
        this.nbEvolCADENCE = 0;
        this.nbEvolPROJECTILE = 0;
    }
    /**
     * Record one balloon destroyed
     */
    public void recordBalloonDestroyed() {
        this.balloonsDestroyed++;
        this.healthRestore++;
        this.creditGainedTotal += 10;
    }

    /**
     * Record Tower that was be purchased
     * @param towerCost (int) ammount of tower
     */
    public void recordTowerPurchased(int towerCost) {
        this.towersPurchased++;
        this.creditUsedTotal += towerCost;
    }

    public void recordTowerSold(){
        this.towerSold++;
    }

    /**
     * Record purchase of Evolution. Also record type of evolution in counter for this journal
     * @param e
     */
    public void recordEvolutionApplied(Evolution e) {
        this.evolutionsPurchased++;
        this.creditUsedTotal += e.getCost();
        this.recordNbTypeEvolution(e.getEvoType());
    }

    public void recordEvolutionSold(){
        this.evolutionSold++;
    }

    /**
     * Records that the player lost one health point.
     */
    public void recordHealthLost() {
        this.healthLost++;
    }

    /**
     * Records Evolution purchase by type. This method is called when an evolution is applied to a tower
     * @param et the type of evolution purchased
     */
    public void recordNbTypeEvolution(EvolutionType et){
        switch (et) {
            case CADENCE:
                nbEvolCADENCE++;
                break;
            case POWER:
                nbEvolPOWER++;
                break;
            case SCOPE:
                nbEvolSCOPE++;
                break;
            case PROJECTILE:
                nbEvolPROJECTILE++;
                break;
        
            default:
                System.out.println("This type not exist");
        }
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
        return evolutionsPurchased;
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

    /**
     * Returns the number of evolutions purchased for a specific evolution type. By default, it returns the total number of evolutions. We can choose default by EvolutionType.ALL
     * @param et the evolution type
     * @return (int) the number of evolutions purchased for the specified type
     */
    public int getNbTypeEvolution(EvolutionType et){
        switch (et) {
            case CADENCE:
                return nbEvolCADENCE;
            case POWER:
                return nbEvolPOWER;
            case SCOPE:
                return nbEvolSCOPE;
            case PROJECTILE:
                return nbEvolPROJECTILE;
        
            default:
                return evolutionsPurchased;        
        }
    }
}