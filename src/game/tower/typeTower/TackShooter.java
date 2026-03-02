package game.tower.typeTower;

import game.Evolution;
import game.Position;
import game.projectiles.Needle;
import game.projectiles.SharpDart;
import game.tower.ProjectileTower;

public class TackShooter extends ProjectileTower {

    public TackShooter(String name, Position pos) {
        super(name, 1, 24, 350, 1, new Needle(), pos);
    }

    public void getEvolution(Evolution e) {
        Evolution.EvolutionType type = e.getEvoType();
        if (!this.hasEvolution(type)) {
            switch (type) {
                case SCOPE:
                    this.scope += 1;
                    break;
                case CADENCE:
                    this.cadence -= 6;
                    break;
                case POWER:
                    this.power += 1;

                    break;
                case PROJECTILE:
                    this.setProjectileType(new SharpDart());
                    break;
            }
            this.evolutions.add(type);
        }

    }
}
