package game.tower.typeTower;

import game.tower.NonProjectileTower;

public class IceTower extends NonProjectileTower{

    public IceTower(String name) {
        super(name, 100, 30, 400);
    }
    public int time() {              
        return 0;
    }
}
