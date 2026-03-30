package game.choice;

import game.Evolution;

/**
 * Wrapper représentant un choix d'évolution pour le ListChooser.
 * Affiche le type d'évolution et son coût de manière lisible.
 */
public class EvolutionChoice {
    private Evolution.EvolutionType type;
    private int cost;

    /**
     * Crée un choix d'évolution.
     * 
     * @param type le type d'évolution
     * @param cost le coût de l'évolution
     */
    
    public EvolutionChoice(Evolution.EvolutionType type, int cost) {
        this.type = type;
        this.cost = cost;
    }
    
    public Evolution.EvolutionType getType() {
        return type;
    }
    
    public int getCost() {
        return cost;
    }
