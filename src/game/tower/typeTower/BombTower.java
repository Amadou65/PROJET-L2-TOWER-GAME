package game.tower.typeTower;

import game.projectiles.Bomb;
import game.tower.ProjectileTower;

public class BombTower extends ProjectileTower{
    BombTower(){
        super(150,36,600, new Bomb());
    }
}
