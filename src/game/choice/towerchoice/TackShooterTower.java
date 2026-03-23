/**
 * Cette classe représente une option de choix pour construire une tour de type "Tack Shooter" dans le jeu. 
 * Elle étend la classe abstraite Choice et implémente la méthode getChoice() 
 * pour retourner une instance de TackShooter.
 */


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
