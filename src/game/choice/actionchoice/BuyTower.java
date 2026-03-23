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
