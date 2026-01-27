package game;

public class Balloon {
    private int health;         // Résistance : 1, 2 ou 4 
    private double speed;       // Vitesse propre 
    private double distance;    // Distance parcourue
    private boolean frozen;

    public Balloon(int level) {
        this.health = level;
        this.speed = determineSpeed(level);
        this.distance = 0.0;
        this.frozen = false;
    }

    private double determineSpeed(int level) {
        if (level == 4) return 0.5;
        if (level == 2) return 0.3;
        return 0.2;
    }  // Missing closing brace was here

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
        this.health -= damage;
        // Mise à jour de la vitesse si le niveau change
        if (this.health > 0) {
            this.speed = determineSpeed(this.health);
        }
    }

    public boolean isPopped() {
        return this.health <= 0;
    }

    public int getHealth() { return health; }
    public double getDistance() { return distance; }
    public double getSpeed() { return speed; }
    public boolean isFrozen() { return frozen; }
    public void setFrozen(boolean state) { this.frozen = state; }
    
    // Optional: You might want this method if you need the level/health
    public int getLevel() { return health; }
}