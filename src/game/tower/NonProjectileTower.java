package game.tower;

import java.util.List;

import game.Tower;
import game.Balloon;
import game.Position;

public abstract class NonProjectileTower extends Tower{
    public Object time;
    
    public NonProjectileTower(String name, int scope, int cadence, int cost, int power, Position pos) {
        super(name, scope, cadence, cost, power, pos);
    }

    abstract public void freeze(List<Balloon> balloons);
}
