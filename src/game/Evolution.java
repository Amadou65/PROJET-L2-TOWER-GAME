// classe mere
package game;

public abstract class Evolution {

    protected int cost;
    protected EvolutionType evoType;

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
