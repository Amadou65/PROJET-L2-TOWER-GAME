/**
 * Cette classe représente une option de choix pour construire une tour de type "Super Monkey" dans le jeu. 
 * Elle étend la classe abstraite Choice et implémente la méthode getChoice() 
 * pour retourner une instance de SuperMonkey.
 */


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
