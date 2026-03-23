/**
 * Cette classe représente une option de choix pour l'évolution de type "Power" dans le jeu. 
 * Elle étend la classe abstraite Choice et implémente la méthode getChoice() 
 * pour retourner le type d'évolution correspondant à "Power".
 */


package game.choice.evolutionchoice;
import game.Evolution;
import game.choice.Choice;

public class Power extends Choice<Evolution.EvolutionType> {

    public String toString() {
        return "Evolution methode Power";
    }

    public Evolution.EvolutionType getChoice() {
        return Evolution.EvolutionType.POWER;
    }
}
