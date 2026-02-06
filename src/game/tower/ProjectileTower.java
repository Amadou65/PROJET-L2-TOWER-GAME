package game.tower;

import java.util.ArrayList;
import java.util.List;

import game.Projectile;
import game.Tower;

public class ProjectileTower extends Tower{
    public Projectile projectile;

    public ProjectileTower(String name, int scope, int cadence, int cost, Projectile p){
        super(name, scope, cadence, cost);
        this.projectile = p;
    }

    public void shot() {
        System.out.println("tirer un projectile");
    }
}
