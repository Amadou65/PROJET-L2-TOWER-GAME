package game.tower.typeTower;

import game.Evolution;
import game.Position;
import game.projectiles.Bomb;
import game.projectiles.ExtraBomb;
import game.tower.ProjectileTower;

public class BombTower extends ProjectileTower {
    public BombTower(String name, Position pos) {
        super(name, 2, 36, 600, 1, new Bomb(), pos);
    }

    public void getEvolution(Evolution e) {
        Evolution.EvolutionType type = e.getEvoType();
        if (!this.hasEvolution(type)) {
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
