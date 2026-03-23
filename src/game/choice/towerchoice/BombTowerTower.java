package game.choice.towerchoice;
import game.choice.Choice;
import game.Tower;
import game.tower.typeTower.BombTower;

public class BombTowerTower extends Choice<Tower> {
    
    public String toString() {
        return "Bomb Tower";
    }

    public Tower getChoice() {
        return new BombTower(null, null);
    }
}
