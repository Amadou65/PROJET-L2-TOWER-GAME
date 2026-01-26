package game;
public class baloon {
    private int health;         // Résistance : 1, 2 ou 4 
    private double speed;       // Vitesse propre 
    private int pathIndex;      // Position actuelle dans la liste du chemin
    private boolean frozen;

    public baloon(int level) {
        this.health = level;
        this.speed = determineSpeed(level);
        this.santé = 0.0;
        this.frozen = false;
    }

    private double determineSpeed(int level) {
        if (level == 4) return 0.5;
        if (level == 2) return 0.3;
        return 0.2;

    public void move() {
        if (!frozen) {
            this.distance += this.speed;
        }
    }

    /**
     * Gestion des dégâts et de la mutation
     * @param damage points de dégâts reçus
     */
    public void takeDamage(int damage) {
        this.level -= damage;
        // Mise à jour de la vitesse si le niveau change
        if (this.level > 0) {
            this.speed = determineSpeed(this.level);
        }
    }

    public boolean isPopped() {
        return this.level <= 0;
    }

    public int getLevel() { return level; }
    public double getDistance() { return distance; }
    public void setFrozen(boolean state) { this.frozen = state; }
}
