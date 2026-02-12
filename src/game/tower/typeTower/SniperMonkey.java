package game.tower.typeTower;

import game.Evolution;
import game.Position;
import game.projectiles.VerySharpDart;
import game.tower.ProjectileTower;

public class SniperMonkey extends ProjectileTower{

    public SniperMonkey(String name, Position pos) {
        super(name, 10000, 40, 500, 1, new VerySharpDart(), pos);
    }

    public void getEvolution(Evolution e) {
        Evolution.EvolutionType type = e.getEvoType();
        if(!this.hasEvolution(type)){
            switch (type) {
                case SCOPE:
                    this.scope += 1;
                    break;
                case CADENCE:
                    this.cadence -= 10;
                    break;
                case POWER:
                    this.power += 1;
                    
                    break;
                case PROJECTILE:
                    break;
            }
            this.evolutions.add(type);
        }

    }
}
