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
