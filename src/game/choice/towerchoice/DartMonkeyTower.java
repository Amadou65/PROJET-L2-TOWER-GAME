package game.choice.towerchoice;
import game.Tower;
import game.choice.Choice;
import game.tower.typeTower.DartMonkey;

public class DartMonkeyTower extends Choice<Tower> {
    
    public String toString() {
        return "Dark Monkey";
    }

    public Tower getChoice() {
        return new DartMonkey(null, null);
    }
}
