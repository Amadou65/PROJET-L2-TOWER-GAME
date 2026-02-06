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

    public Tower(String nom, int scope, int cadence, int cost) {
        this.nom = nom;
        this.scope = scope;
        this.cadence = cadence;
        this.cost = cost;
    }

    // GETTERS
    public int getScope() {
        return scope;
    }

    public int getCadence() {
        return cadence;
    }

    public int getCost() {
        return cost;
    }

    public String getNom() {
        return nom;
    }


/**
 * this function return a list of balloons in the scope of the tower
 * @param b game board where the tower is placed
 * @return a list of balloons
 */
    public List<Balloon> findTargets(Board b) {       
        System.out.println("recherche d'une cible");
        return new ArrayList<Balloon>();
    }

}