package game.tower.typeTower;

import game.Evolution.EvolutionType;
import game.projectiles.Bomb;
import game.tower.ProjectileTower;

public class BombTower extends ProjectileTower{
    BombTower(String name){
        super(name, 2, 36, 600, new Bomb());
    }

    public void getEvolution(EvolutionType type) {
        //TODO
        
    }
}
