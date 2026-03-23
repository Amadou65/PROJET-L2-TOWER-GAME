/**
 * Cette classe représente une option de choix pour construire une tour de type "Sniper Monkey" dans le jeu. 
 * Elle étend la classe abstraite Choice et implémente la méthode getChoice() 
 * pour retourner une instance de SniperMonkey.
 */


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
