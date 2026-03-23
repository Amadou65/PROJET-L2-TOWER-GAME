/**
 * Cette classe représente une option de choix pour construire une tour de type "Bomb Tower" dans le jeu. 
 * Elle étend la classe abstraite Choice et implémente la méthode getChoice() 
 * pour retourner une instance de BombTower.
 */


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
