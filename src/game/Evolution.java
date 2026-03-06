// classe mere
package game;

/**
 * Représente une amélioration applicable à une tour.
 * Chaque évolution a un coût et un type spécifique qui définit 
 * quelle statistique de la tour sera impactée (Portée, Puissance, etc.).
 */

public class Evolution {

    /**
     * Types d'évolutions disponibles dans le jeu.
     * PROJECTILE : Change le type de projectile (ex: Bomb -> ExtraBomb).
     * SCOPE : Augmente le rayon de détection de la tour.
     * CADENCE : Réduit le temps d'attente entre deux tirs.
     * POWER : Augmente les dégâts de base de la tour.
     */

    protected int cost;
    protected EvolutionType evoType;

    // enum for the type of evolution
    public static enum EvolutionType {
        PROJECTILE,
        SCOPE,
        CADENCE,
        POWER
    }

    public Evolution(int cost, EvolutionType type) {
        this.cost = cost;
        this.evoType = type;
    }

    // GETTERS
    public int getCost() {
        return cost;
    }

    public EvolutionType getEvoType() {
        return evoType;
    }
}
