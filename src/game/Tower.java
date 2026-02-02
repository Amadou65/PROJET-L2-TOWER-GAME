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
    protected String nom;

    public Tower(String nom){
        this.nom = nom;
    }

    public List<Balloon> findTarget() {       
        System.out.println("recherche d'une cible");
        return new ArrayList<Balloon>();
    }

}