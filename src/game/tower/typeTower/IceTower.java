package game.tower.typeTower;

import game.tower.NonProjectileTower;

public class IceTower extends NonProjectileTower{

    public IceTower(String name) {
        super(name, 1, 30, 400, 1);
    }
    public int time() {              
        return 0;
    }
}
