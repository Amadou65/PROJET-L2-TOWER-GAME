package game;

// classe mère
public abstract class Tower {
    protected int power;
    protected Position position;
    protected int scope;
    protected int cadence;
    protected int cadenceActuelle;
    protected int cost;
    protected String nom;

    public Tower(String nom, int scope, int cadence, int cost, int power, Position pos) {
        this.nom = nom;
        this.scope = scope;
        this.cadence = cadence;
        this.cost = cost;
        this.power = power;
        this.cadenceActuelle = 0;
        this.position = pos;
    }

    // GETTERS ET SETTERS

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
     * Returns the position of this tower on the board.
     * 
     * @return the position
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * This is predicat
     * 
     * @return true if the tower can shoot, false the tower has
     *         cooldown(cadenceActuelle > 0) and reduces the cooldown by 1
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
     * This function resets the tower's cooldown (cadenceActuelle) to its initial
     * value (cadence) after shooting.
     */
    public void resetCadence() {
        this.cadenceActuelle = this.cadence;
    }

}