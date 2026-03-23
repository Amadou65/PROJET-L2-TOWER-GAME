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
