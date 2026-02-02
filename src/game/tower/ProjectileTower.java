package game.tower;

import java.util.ArrayList;
import java.util.List;

import game.Tower;

public class ProjectileTower extends Tower{
    public Object projectile;

    public void shot() {
        System.out.println("tirer un projectile");
    }

    public List findTarget() {
        System.out.println("recherche d'une cible spécifique");
        return new ArrayList();
    }
}
