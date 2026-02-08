package game.tower.typeTower;
import game.Evolution;
import game.projectiles.Dart;
import game.tower.ProjectileTower;

public class DartMonkey extends ProjectileTower{
    
    public DartMonkey(String name){
        super(name, 1, 20, 200, 1, new Dart());
    }

    public void getEvolution(Evolution e) {
        Evolution.EvolutionType type = e.getEvoType();
        if(!this.hasEvolution(type)){
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
                    break;
            }
            this.evolutions.add(type);
        }

    }
}
