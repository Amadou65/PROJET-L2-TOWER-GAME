package game.tower.typeTower;

import game.projectiles.VerySharpDart;
import game.tower.ProjectileTower;

public class SniperMonkey extends ProjectileTower{

    public SniperMonkey(){
        super(10000,40,500, new VerySharpDart());
    }
}
