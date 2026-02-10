package game.tower.typeTower;

import game.Evolution;
import game.projectiles.Bomb;
import game.projectiles.ExtraBomb;
import game.tower.ProjectileTower;

public class BombTower extends ProjectileTower{
    BombTower(String name){
        super(name, 2, 36, 600, 1, new Bomb());
    }

    public void getEvolution(Evolution e) {
        Evolution.EvolutionType type = e.getEvoType();
        if(!this.hasEvolution(type)){
            switch (type) {
                case PROJECTILE:
                    this.setProjectileType(new ExtraBomb());
                    break;
                case SCOPE:
                    this.scope += 1;
                    break;
                case CADENCE:
                    this.cadence -= 9;
                    break;
                case POWER:
                    this.power += 1;
                    
                    break;
            }
            this.evolutions.add(type);
        }

    }
}
