package game.tower.typeTower;

import java.util.*;
import game.tower.NonProjectileTower;
import game.*;

public class SlowdownTower extends NonProjectileTower{

    public SlowdownTower(String name, Position pos) {
        super(name, 1, 30, 500, 1, pos);
    }

    public void freeze(List<Balloon> balloons) {
        List<Balloon> targets = TargetingBalloon.getAllTargets(balloons, this);
        for (Balloon b : balloons) {
            if(targets.contains(b)){
                b.slowDown();
            }
            else{
                b.unSlowDown();
            }
        }
    }
}
