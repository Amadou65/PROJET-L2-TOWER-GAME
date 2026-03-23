/**
 * Cette classe représente une option de choix pour l'évolution de type "Scope" dans le jeu. 
 * Elle étend la classe abstraite Choice et implémente la méthode getChoice() 
 * pour retourner le type d'évolution correspondant à "Scope".
 */


package game.choice.evolutionchoice;
import game.Evolution;
import game.choice.Choice;

public class Scope extends Choice<Evolution.EvolutionType> {

    public String toString() {
        return "Evolution methode Scope";
    }

    public Evolution.EvolutionType getChoice() {
        return Evolution.EvolutionType.SCOPE;
    }
}
