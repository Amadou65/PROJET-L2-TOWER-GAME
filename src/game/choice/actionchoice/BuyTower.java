/**
 * Cette classe représente une option de choix pour acheter une tour dans le jeu. 
 * Elle étend la classe abstraite Choice et implémente la méthode getChoice() 
 * pour retourner une chaîne de caractères indiquant l'action d'achat.
 */


package game.choice.actionchoice;
import game.choice.Choice;

public class BuyTower extends Choice<String> {
    
    public String toString() {
        return "Buy Tower";
    }

    public String getChoice(){
        return "Buy";
    }
}
