package game;
import java.util.ArrayList;
import java.util.List;
// classe mère
public class Tower {
    protected Object power;
    protected Position position;
    protected int scope;
    protected int cadence;
    protected int cadenceActuelle;
    protected boolean isEvolutionPossible;
    protected int cost;
    protected String nom;

    public Tower(String nom, int scope, int cadence, int cost) {
        this.nom = nom;
        this.scope = scope;
        this.cadence = cadence;
        this.cost = cost;
        this.cadenceActuelle = 0;
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

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

/**
 * This function returns a list of balloons in the scope of the tower.
 * @param balloons list of balloons to check
 * @return a list of balloons within the tower's scope
 */
public List<Balloon> findTargets(List<Balloon> balloons) {
    List<Balloon> targets = new ArrayList<>();
    
    if (balloons != null) {
        for (Balloon balloon : balloons) {
            if (isInScope(balloon)) {
                targets.add(balloon);
            }
        }
    }
    
    return targets;
}

private boolean isInScope(Balloon balloon) {
    // Calculate distance between tower and balloon
    int distance = Math.abs(balloon.getGridX() - this.getX()) 
                 + Math.abs(balloon.getGridY() - this.getY());
    return distance <= scope;
}

}