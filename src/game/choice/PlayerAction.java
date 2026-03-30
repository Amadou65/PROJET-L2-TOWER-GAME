package game.choice;

/**
 * Énumération des actions disponibles pour le joueur
 * au début de chaque manche.
 */
public enum PlayerAction {
    BUY_TOWER("Acheter une tour"),
    EVOLVE_TOWER("Évoluer une tour"),
    SELL_TOWER("Vendre une tour"),
    SELL_EVOLUTION("Vendre une évolution"),
    END_TURN("Terminer le tour");
    
    private String label;

    PlayerAction(String label) {
        this.label = label;
    }
    
    @Override
    public String toString() {
        return this.label;
    }
}

        