package game;
import java.util.ArrayList;
import java.util.List;
// classe mère
public class Tower {
    public Object power; 
    public int scope;
    public int cadence;
    public boolean isEvolutionPossible;
    public int cost;

    

    public List findTarget() {       
        System.out.println("recherche d'une cible");
        return new ArrayList();
    }

}