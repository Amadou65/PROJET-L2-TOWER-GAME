package game.tower;

import java.util.HashSet;
import java.util.List;

import game.*;
import game.Evolution.EvolutionType;

public abstract class ProjectileTower extends Tower{
    public Projectile projectile;
    protected HashSet<EvolutionType> evolutions;

    public ProjectileTower(String name, int scope, int cadence, int cost, int power, Projectile p, Position pos){
        super(name, scope, cadence, cost, power, pos);
        this.projectile = p;
        this.evolutions = new HashSet<EvolutionType>();
    }

    /**
     * This function finds the first balloon in the scope of the tower and shoot it
     * @param balloons
     */
    public void shot(List<Balloon> balloons){
        Balloon target = TargetingBalloon.getBestTarget(balloons, this);
        if (target != null) {
            target.takeDamage(this.power);
            this.resetCadence();
        }

    }

    // GETTERS AND SETTERS
    
    public Projectile getProjectile() {
        return projectile;
    }

    public void setProjectileType(Projectile projectile) {
        this.projectile = projectile;
    }

    public boolean hasEvolution(EvolutionType type) {
        return evolutions.contains(type);
    }



    /**
     * Applique une évolution à la tour de type Projectile.
     * Cette méthode est abstraite et sera précisée dans chaque type de tour 
     * (BombTower, DartMonkey, etc.) pour définir les bonus exacts.
     * Elle vérifie également que l'évolution n'a pas déjà été appliquée via le HashSet.
     * @param e L'évolution à appliquer.
     */

    public abstract void getEvolution(Evolution e);
}
