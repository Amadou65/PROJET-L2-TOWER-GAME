/**
 * Cette classe représente une option de choix pour construire une tour de type "Dart Monkey" dans le jeu. 
 * Elle étend la classe abstraite Choice et implémente la méthode getChoice() 
 * pour retourner une instance de DartMonkey.
 */


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
