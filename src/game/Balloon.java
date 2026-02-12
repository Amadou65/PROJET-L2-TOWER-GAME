package game;

import java.util.*;

public class Balloon {
    // ... tes autres attributs ...
    private double x, y; 
    private double distance; // <--- AJOUT : Pour suivre la distance totale
    private double speed;
    private int health;
    private int currentTargetIndex;
    private List<Position> path;
    private int level;
    private boolean frozen;

    public Balloon(int level, List<Position> path) {
        this.health = level;
        this.level = level;
        this.path = path;
        this.distance = 0.0; // Initialisation à 0
        this.currentTargetIndex = 1;
        this.speed = determineSpeed(level);
        this.frozen = false;
        // ... (le reste du constructeur)
        if (path != null && !path.isEmpty()) {
            this.x = path.get(0).getX();
            this.y = path.get(0).getY();
        }
    }

    private double determineSpeed(int level) {
        if (level == 4) return 0.15; 
        if (level == 2) return 0.1;
        return 0.05;
    }

    public void move() {
        if (!this.frozen && !isPopped() && currentTargetIndex < path.size()) {
            Position target = path.get(currentTargetIndex);
            double dx = target.getX() - this.x;
            double dy = target.getY() - this.y;
            double distToTarget = Math.sqrt(dx * dx + dy * dy);

            // On calcule la distance réelle qu'on va parcourir ce tour-ci
            double actualMove = Math.min(distToTarget, speed);

            if (distToTarget <= speed) {
                this.x = target.getX();
                this.y = target.getY();
                currentTargetIndex++; 
            } else {
                this.x += (dx / distToTarget) * speed;
                this.y += (dy / distToTarget) * speed;
            }
            
            // MISE À JOUR : On ajoute le mouvement à la distance totale
            this.distance += actualMove;
        }
    }

    // AJOUT : La méthode getDistance
    public double getDistance() {
        return (currentTargetIndex - 1) + (Math.sqrt(x*x + y*y) % 1);
        // Un calcul simple pour savoir qui est le plus avancé sur le chemin
    }

    // ... le reste de tes méthodes (isPopped, takeDamage, etc.) ...
    public boolean isPopped() { return this.health <= 0; }
    public int getGridX() { return (int) Math.round(x); }
    public int getGridY() { return (int) Math.round(y); }
    public int getLevel() { return this.level; }
    public boolean hasReachedEnd() { return currentTargetIndex >= path.size(); }
    public int getHealth() { return this.health; }
    public double getSpeed() { return this.speed; }

    /**
     * methode Take damage
     * 
     */
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health > 0) {
            this.speed = determineSpeed(this.health);
        }
    }

    /**
     * methode qui gèle le ballon
    */
    public void freeze() {
        this.frozen = true;
    }

    /**
     * methode qui ralentit le ballon
     */
    public void slowDown() {
        this.speed *= 0.5; // Réduction de la vitesse à 50%
    }

    /**
     * methode qui dégel le ballon
     */
    public void unSlowDown() {
        this.speed *= 2.0; // Rétablissement de la vitesse normale
    }
}