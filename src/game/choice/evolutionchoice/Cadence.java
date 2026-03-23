/**
 * Cette classe représente une option de choix pour l'évolution de type "Cadence" dans le jeu. 
 * Elle étend la classe abstraite Choice et implémente la méthode getChoice() 
 * pour retourner le type d'évolution correspondant à "Cadence".
 */

package game.choice.evolutionchoice;
import game.choice.Choice;
import game.Evolution;

public class Cadence extends Choice<Evolution.EvolutionType>{

    public String toString() {
        return "Evolution methode Cadence";
    }

    public Evolution.EvolutionType getChoice(){
        return Evolution.EvolutionType.CADENCE;
    }
}
