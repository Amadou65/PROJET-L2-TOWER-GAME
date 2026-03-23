package game.choice.towerchoice;

import game.choice.Choice;
import game.tower.typeTower.SlowdownTower;
import game.Tower;

public class SlowDownTowerTower extends Choice<Tower> {

    public String toString(){
        return "Slow Down Tower";
    }

    public Tower getChoice(){
        return new SlowdownTower(null,null);
    }
    
}

