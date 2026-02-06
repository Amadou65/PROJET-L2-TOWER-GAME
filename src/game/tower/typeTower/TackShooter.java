package game.tower.typeTower;

import game.projectiles.Needle;
import game.tower.ProjectileTower;

public class TackShooter extends ProjectileTower{

    public TackShooter(String name){
        super(name, 80, 24, 350, new Needle());
    }
}
