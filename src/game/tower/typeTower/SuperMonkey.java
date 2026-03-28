package game.tower.typeTower;

import game.Evolution;
import game.Position;
import game.projectiles.SharpDart;
import game.projectiles.VerySharpDart;
import game.tower.ProjectileTower;

public class SuperMonkey extends ProjectileTower {

    public SuperMonkey(String name, Position pos) {
        super(name, 2, 6, 1200, 1, new SharpDart(), pos);
    }

    public void getEvolution(Evolution e) {
        Evolution.EvolutionType type = e.getEvoType();
        if (!this.hasEvolution(type)) {
            switch (type) {
                case SCOPE:
                    this.scope += 1;
                    break;
                case CADENCE:
                    this.cadence -= 1;
                    break;
                case POWER:
                    this.power += 1;

                    break;
                case PROJECTILE:
                    this.setProjectileType(new VerySharpDart());
                    break;
                default:
                    System.out.println("This type not exist");
            }
            this.evolutions.add(type);
        }

    }

    public void removeEvolution(Evolution e){
        Evolution.EvolutionType type = e.getEvoType();
        if (this.hasEvolution(type)) {
            switch (type) {
                case SCOPE:
                    this.scope -= 1;
                    break;
                case CADENCE:
                    this.cadence += 1;
                    break;
                case POWER:
                    this.power -= 1;

                    break;
                case PROJECTILE:
                    this.setProjectileType(new SharpDart());
                    break;
                default:
                    System.out.println("This type not exist");
            }
            this.evolutions.remove(type);
        }
    }
}
