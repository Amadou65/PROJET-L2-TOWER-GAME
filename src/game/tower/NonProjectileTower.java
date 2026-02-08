package game.tower;

import game.Tower;

public abstract class NonProjectileTower extends Tower{
    public Object time;
    
    public NonProjectileTower(String name, int scope, int cadence, int cost) {
        super(name, scope, cadence, cost);
    }
}
