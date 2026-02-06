package game.tower.typeTower;
import game.projectiles.Dart;
import game.tower.ProjectileTower;

public class DartMonkey extends ProjectileTower{
    
    public DartMonkey(String name){
        super(name, 100, 20, 200, new Dart());
    }
}
