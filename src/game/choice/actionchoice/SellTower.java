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
