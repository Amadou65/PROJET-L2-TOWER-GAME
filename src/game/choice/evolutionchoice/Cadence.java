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
