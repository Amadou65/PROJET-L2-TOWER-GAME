package game.tower;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public HashSet<Evolution.EvolutionType> getEvoAplied(){
        return this.evolutions;
    }
    /**
    * Retourne la liste des évolutions actuellement appliquées à la tour.
    * Utile pour l'affichage dans le menu de vente du Livrable 5.
    */
    public Set<EvolutionType> getAppliedEvolutions() {
         return Collections.unmodifiableSet(this.evolutions);
    }

    /**
    * Méthode abstraite : chaque tour doit définir comment elle 
    * réinitialise ses stats (portée, dégâts...) quand on vend une évolution.
    */
    public abstract void removeEvolution(EvolutionType type);
}
