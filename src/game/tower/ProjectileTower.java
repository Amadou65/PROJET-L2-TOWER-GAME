package game.tower;

import java.util.ArrayList;
import java.util.List;

import game.Projectile;
import game.Tower;

public class ProjectileTower extends Tower{
    public Projectile projectile;

    public ProjectileTower(int scope, int cadence, int cost, Projectile p){
        super("titi");
        this.cadence = cadence;
        this.scope = scope;
        this.cost = cost;
        this.projectile = p;
    }

    public void shot() {
        System.out.println("tirer un projectile");
    }

    public List findTarget() {
        System.out.println("recherche d'une cible spécifique");
        return new ArrayList();
    }
}
