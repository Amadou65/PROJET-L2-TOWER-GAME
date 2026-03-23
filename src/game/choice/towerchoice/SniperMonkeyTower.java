package game.choice.towerchoice;
import game.Tower;
import game.choice.Choice;
import game.tower.typeTower.SniperMonkey;

public class SniperMonkeyTower extends Choice<Tower> {
    
    public String toString() {
        return "Sniper Monkey";
    }

    public Tower getChoice() {
        return new SniperMonkey(null, null);
    }
}
