package game.tower.typeTower;

import game.Evolution;
import game.Position;
import game.projectiles.Dart;
import game.projectiles.SharpDart;
import game.tower.ProjectileTower;

public class DartMonkey extends ProjectileTower {

    public DartMonkey(String name, Position pos) {
        super(name, 1, 20, 200, 1, new Dart(), pos);
    }

    public void getEvolution(Evolution e) {
        Evolution.EvolutionType type = e.getEvoType();
        if (!this.hasEvolution(type)) {
            switch (type) {
                case SCOPE:
                    this.scope += 1;
                    break;
                case CADENCE:
                    this.cadence -= 5;
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
