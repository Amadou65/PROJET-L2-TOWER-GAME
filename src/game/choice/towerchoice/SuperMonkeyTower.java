package game.choice.towerchoice;
import game.Tower;
import game.choice.Choice;
import game.tower.typeTower.SuperMonkey;

public class SuperMonkeyTower extends Choice<Tower> {
    
    public String toString() {

        return "Super Monkey";
    }

    public Tower getChoice() {
        return new SuperMonkey(null, null);
    }
}
