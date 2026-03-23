/**
 * Cette classe représente une option de choix pour faire évoluer une tour dans le jeu. 
 * Elle étend la classe abstraite Choice et implémente la méthode getChoice() 
 * pour retourner une chaîne de caractères indiquant l'action d'évolution.
 */

package game.choice.actionchoice;
import game.choice.Choice;

public class EvolveTower extends Choice<String> {
    
    public String toString() {
        return "Evolve Tower";
    }

    public String getChoice(){
        return "Evolve";
    }
}
