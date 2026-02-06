package game.tower.typeTower;

import game.tower.NonProjectileTower;

public class SlowdownTower extends NonProjectileTower{

    public SlowdownTower(String name) {
        super(name, 1, 30, 500);
    }
    
    public int time() {              
        return 0;
    }
}
