package game;
import java.util.ArrayList;
import java.util.List;
// classe mère
public class Tower {
    protected Object power; 
    protected int scope;
    protected int cadence;
    protected boolean isEvolutionPossible;
    protected int cost;

    public Tower(){

    }

    public List findTarget() {       
        System.out.println("recherche d'une cible");
        return new ArrayList();
    }

}