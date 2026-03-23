package game.choice.towerchoice;

import game.choice.Choice;
import game.tower.typeTower.IceTower;
import game.Tower;

public class IceTowerTower extends Choice<Tower> {

    public String toString(){
        return "Ice Tower";
    }

    public Tower getChoice(){
        return new IceTower(null,null);
    }
    
}
