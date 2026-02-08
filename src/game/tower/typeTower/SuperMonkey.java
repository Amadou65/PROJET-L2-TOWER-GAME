package game.tower.typeTower;

import game.Evolution.EvolutionType;
import game.projectiles.SharpDart;
import game.tower.ProjectileTower;

public class SuperMonkey extends ProjectileTower{

    public SuperMonkey(String name){
        super(name, 2, 6, 1200, new SharpDart());
    }

    public void getEvolution(EvolutionType type) {
        //TODO
        
    }
}
