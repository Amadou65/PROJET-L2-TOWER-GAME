package game.tower.typeTower;

import game.Evolution.EvolutionType;
import game.projectiles.VerySharpDart;
import game.tower.ProjectileTower;

public class SniperMonkey extends ProjectileTower{

    public SniperMonkey(String name){
        super(name, 10000, 40, 500, new VerySharpDart());
    }

    public void getEvolution(EvolutionType type) {
        //TODO
        
    }
}
