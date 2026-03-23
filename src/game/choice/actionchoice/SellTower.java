/**
 * Cette classe représente une option de choix pour vendre une tour dans le jeu. 
 * Elle étend la classe abstraite Choice et implémente la méthode getChoice() 
 * pour retourner une chaîne de caractères indiquant l'action de vente.
 */


package game.choice.actionchoice;
import game.choice.Choice;

public class SellTower extends Choice<String> {
    
    public String toString() {
        return "Sell Tower";
    }

    public String getChoice(){
        return "Sell";
    }
}
