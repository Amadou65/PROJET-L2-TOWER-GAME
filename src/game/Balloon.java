package game;

import java.util.*;

public class Balloon {
    // Attributs
    private int health;
    private double speed;
    private boolean frozen;
    private int currentTargetIndex; 
    private int level;
    
    // Methode deplacement
    private double x, y; 
    private List<Position> path;

    public Balloon(int level, List<Position> path) {
        this.health = level;
        this.level = level;
        this.frozen = false;
        this.path = path;
        this.currentTargetIndex = 1; 
        this.speed = determineSpeed(level);

        // Position de départ
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

    /**
     * MÉTHODE MOVE (Fusionnée)
     */
    public void move() {
        if (!frozen && !isPopped() && currentTargetIndex < path.size()) {
            Position target = path.get(currentTargetIndex);
            double dx = target.getX() - this.x;
            double dy = target.getY() - this.y;
            double dist = Math.sqrt(dx * dx + dy * dy);

            if (dist <= speed) {
                this.x = target.getX();
                this.y = target.getY();
                currentTargetIndex++; 
            } else {
                this.x += (dx / dist) * speed;
                this.y += (dy / dist) * speed;
            }
        }
    }

    // --- Méthodes de gestion des dégâts ---
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health > 0) {
            this.speed = determineSpeed(this.health);
        }
    }

    public boolean isPopped() { return this.health <= 0; }
    
    // --- Méthodes pour  GameEngine ---
    public int getGridX() { return (int) Math.round(x); }
    public int getGridY() { return (int) Math.round(y); }
    public int getLevel() { return this.level; }
    public boolean hasReachedEnd() { return currentTargetIndex >= path.size(); }
}