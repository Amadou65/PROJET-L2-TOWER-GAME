package game.tower;

import java.util.HashSet;
import java.util.List;

import game.Projectile;
import game.Tower;
import game.Balloon;
import game.Evolution;
import game.Evolution.EvolutionType;

public abstract class ProjectileTower extends Tower{
    public Projectile projectile;
    protected HashSet<EvolutionType> evolutions;

    public ProjectileTower(String name, int scope, int cadence, int cost, int power, Projectile p){
        super(name, scope, cadence, cost, power);
        this.projectile = p;
        this.evolutions = new HashSet<EvolutionType>();
    }

    public void shot(List<Balloon> balloons){
        List<Balloon> targets = findTargets(balloons);
        targets.get(0).takeDamage(projectile.giveDamage());
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public void setProjectileType(Projectile projectile) {
        this.projectile = projectile;
    }

    public boolean hasEvolution(EvolutionType type) {
        return evolutions.contains(type);
    }

    public abstract void getEvolution(Evolution e);
}
