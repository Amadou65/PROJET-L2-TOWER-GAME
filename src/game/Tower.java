package game;
import java.util.ArrayList;
import java.util.List;

// classe mère
public abstract class Tower {
    protected int power;
    protected Position position;
    protected int scope;
    protected int cadence;
    protected int cadenceActuelle;
    protected int cost;
    protected String nom;

    public Tower(String nom, int scope, int cadence, int cost, int power) {
        this.nom = nom;
        this.scope = scope;
        this.cadence = cadence;
        this.cost = cost;
        this.power = power;
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

    public int getPower() {
        return power;
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

    /**
     * This function checks if a balloon is within the scope of the tower
     * @param balloon
     * @return true if the balloon is within the tower's scope, false otherwise
     */
    private boolean isInScope(Balloon balloon) {
        // Calculate distance between tower and balloon
        int distance = Math.abs(balloon.getGridX() - this.getX()) + Math.abs(balloon.getGridY() - this.getY());
        return distance <= scope;
    }


    /**
     * This is predicat 
     * @return true if the tower can shoot, false the tower has cooldown(cadenceActuelle > 0) and reduces the cooldown by 1
     */
    public boolean canShoot() {
        if (cadenceActuelle > 0) {
            cadenceActuelle--;
            return false;
        } else {
            return true;
        }
    }

    /**
     * This function resets the tower's cooldown (cadenceActuelle) to its initial value (cadence) after shooting.
     */
    public void resetCadence() {
        this.cadenceActuelle = this.cadence;
    }
        

}