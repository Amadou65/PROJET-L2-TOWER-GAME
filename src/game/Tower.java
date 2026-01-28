package game;
import java.util.ArrayList;
import java.util.List;
// classe mère
public class Tower {
    public Object power; 
    public int scope;
    public int cadence;
    public boolean isEvolutionPossible;
    public int cost;

    public List findTarget() {       
        System.out.println("recherche d'une cible");
        return new ArrayList();
    }

}

//classe projectiletower
class ProjectileTower extends Tower {
    public Object projectile;

    public void shot() {
        System.out.println("tirer un projectile");
    }

    public void findTarget() {
        System.out.println("recherche d'une cible spécifique");
    }
}

// sous-classes de projectilesTower
class DartMonkey extends ProjectileTower {
    public Object dart;            
}

class TackShooter extends ProjectileTower {
    public Object needle;      
}

class BombTower extends ProjectileTower {
    public Object bomb;            
}

class SniperMonkey extends ProjectileTower {
    public Object verySharpDart;    
}

class SuperMonkey extends ProjectileTower {
    public Object sharpDart;         
}


//classe nonprojectileTower
class NonProjectileTower extends Tower {
    public Object time;              
}

class IceTower extends NonProjectileTower {
    public int time() {              
        return 0;
    }
}

class SlowdownTower extends NonProjectileTower {
    public int time() {              
        return 0;
    }
}