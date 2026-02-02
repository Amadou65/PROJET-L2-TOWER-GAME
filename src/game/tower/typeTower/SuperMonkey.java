package game.tower.typeTower;

import game.projectiles.SharpDart;
import game.tower.ProjectileTower;

public class SuperMonkey extends ProjectileTower{

    public SuperMonkey(){
        super(200,6,1200, new SharpDart());
    }
}
