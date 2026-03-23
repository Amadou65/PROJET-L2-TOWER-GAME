package game.choice.towerchoice;
import game.Tower;
import game.choice.Choice;
import game.tower.typeTower.TackShooter;

public class TackShooterTower extends Choice<Tower> {
    
    public String toString() {
        return "Tack Shooter";
    }

    public Tower getChoice() {
        return new TackShooter(null, null);
    }
}
