// classe mere
package game;
import game.tower.ProjectileTower;

public abstract class Evolution {

    protected ProjectileTower tower;
    protected int cost;

    public static enum EvolutionType {
        PROJECTILE,
        SCOPE,
        CADENCE,
        POWER
    }

    public Evolution(ProjectileTower tower, int cost) {
        this.tower = tower;
        this.cost = cost;
    }

    // GETTERS
    public int getCost() {
        return cost;
    }

    public Tower getTower() {
        return tower;
    }
}
