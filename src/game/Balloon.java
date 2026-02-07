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
        if (!frozen && !isPopped() && currentTargetIndex < path.size()) {
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
        return this.distance;
    }

    // ... le reste de tes méthodes (isPopped, takeDamage, etc.) ...
    public boolean isPopped() { return this.health <= 0; }
    public int getGridX() { return (int) Math.round(x); }
    public int getGridY() { return (int) Math.round(y); }
    public int getLevel() { return this.level; }
    public boolean hasReachedEnd() { return currentTargetIndex >= path.size(); }

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
public double getDistance(){
    
}

}