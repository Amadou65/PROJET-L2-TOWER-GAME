package game.tower.typeTower;

import game.tower.NonProjectileTower;
import game.*;
import java.util.*;

public class IceTower extends NonProjectileTower{
    private static final int FREEZE_DURATION_TICKS = 10;

    public IceTower(String name, Position pos) {
        super(name, 1, 30, 400, 1, pos);
    }

    public void freeze(List<Balloon> balloons) {
        List<Balloon> targets = TargetingBalloon.getAllTargets(balloons, this);
        for (Balloon b : targets) {
            b.freeze(FREEZE_DURATION_TICKS);
        }
    }
}
