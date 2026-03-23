/**
 * Cette classe représente une option de choix pour l'évolution de type "Projectile" dans le jeu. 
 * Elle étend la classe abstraite Choice et implémente la méthode getChoice() 
 * pour retourner le type d'évolution correspondant à "Projectile".
 */


package game.choice.evolutionchoice;
import game.Evolution;
import game.choice.Choice;

public class Projectile extends Choice<Evolution.EvolutionType> {

    public String toString() {
        return "Evolution methode Projectile";
    }

    public Evolution.EvolutionType getChoice() {
        return Evolution.EvolutionType.PROJECTILE;
    }
}
