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
