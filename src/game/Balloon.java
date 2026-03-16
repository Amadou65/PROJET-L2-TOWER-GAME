package game;

import java.util.*;

/**
 * Represents a balloon enemy moving along a path.
 * A balloon has a level (1=Red, 2=Blue, 4=Pink), health, and speed.
 * It can be frozen, slowed, or take damage.
 */
public class Balloon {
    private double x, y;
    private double distance;
    private double speed;
    private double baseSpeed;
    private int health;
    private int currentTargetIndex;
    private List<Position> path;
    private int level;
    private boolean frozen;
    private int frozenTicksRemaining;
    private boolean slowed;

    /**
     * Creates a balloon at the start of the given path.
     * 
     * @param level the balloon level (1, 2 or 4) which determines health and speed
     * @param path  the list of positions forming the path
     */
    public Balloon(int level, List<Position> path) {
        this.health = level;
        this.level = level;
        this.path = path;
        this.distance = 0.0;
        this.currentTargetIndex = 1;
        this.speed = determineSpeed(level);
        this.baseSpeed = this.speed;
        this.frozen = false;
        this.frozenTicksRemaining = 0;
        this.slowed = false;
        if (path != null && !path.isEmpty()) {
            this.x = path.get(0).getX();
            this.y = path.get(0).getY();
        }
    }

    private double determineSpeed(int level) {
        if (level == 4)
            return 0.15;
        if (level == 2)
            return 0.1;
        return 0.05;
    }

    /**
     * Moves the balloon one step along the path based on its speed.
     * Does nothing if the balloon is frozen or has reached the end.
     */
    public void move() {
        if (isPopped() || currentTargetIndex >= path.size()) {
            return;
        }

        if (this.frozen) {
            this.frozenTicksRemaining--;
            if (this.frozenTicksRemaining <= 0) {
                this.unfreeze();
            }
            return;
        }

        Position target = path.get(currentTargetIndex);
        double dx = target.getX() - this.x;
        double dy = target.getY() - this.y;
        double distToTarget = Math.sqrt(dx * dx + dy * dy);

        double actualMove = Math.min(distToTarget, speed);

        if (distToTarget <= speed) {
            this.x = target.getX();
            this.y = target.getY();
            currentTargetIndex++;
        } else {
            this.x += (dx / distToTarget) * speed;
            this.y += (dy / distToTarget) * speed;
        }

        this.distance += actualMove;
    }

    /**
     * Returns the total distance traveled by the balloon.
     * Used to prioritize targets (most advanced balloon).
     * 
     * @return total distance traveled
     */
    public double getDistance() {
        return this.distance;
    }

    /**
     * Returns the precise X coordinate (double) of the balloon.
     * 
     * @return x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the precise Y coordinate (double) of the balloon.
     * 
     * @return y coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * Returns whether the balloon has been destroyed (health <= 0).
     * 
     * @return true if popped
     */
    public boolean isPopped() {
        return this.health <= 0;
    }

    /**
     * Returns the grid X coordinate (rounded int).
     * 
     * @return grid column index
     */
    public int getGridX() {
        return (int) Math.round(x);
    }

    /**
     * Returns the grid Y coordinate (rounded int).
     * 
     * @return grid row index
     */
    public int getGridY() {
        return (int) Math.round(y);
    }

    /**
     * Returns the level of the balloon.
     * 
     * @return level (1, 2 or 4)
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Returns whether the balloon has reached the end of the path.
     * 
     * @return true if escaped
     */
    public boolean hasReachedEnd() {
        return currentTargetIndex >= path.size();
    }

    /**
     * Returns the current health of the balloon.
     * 
     * @return health points
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Returns the current speed of the balloon.
     * 
     * @return speed value
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Applies damage to the balloon. Adapts speed and level to the new health.
     * 
     * @param damage amount of damage to apply
     */
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health > 0) {
            this.level = this.health; // le niveau suit la santé
            this.baseSpeed = determineSpeed(this.health);
            this.speed = this.slowed ? this.baseSpeed * 0.5 : this.baseSpeed;
        }
    }

    /**
     * Freezes the balloon (stops movement).
     */
    public void freeze() {
        freeze(1);
    }

    /**
     * Freezes the balloon for a limited number of movement ticks.
     *
     * @param durationTicks number of move() calls to skip
     */
    public void freeze(int durationTicks) {
        this.frozen = true;
        this.frozenTicksRemaining = Math.max(this.frozenTicksRemaining, durationTicks);
    }

    /**
     * Unfreezes the balloon (restarts movement).
     */
    public void unfreeze() {
        this.frozen = false;
        this.frozenTicksRemaining = 0;
    }

    /**
     * Slows down the balloon by halving its speed.
     */
    public void slowDown() {
        if (!this.slowed) {
            this.speed *= 0.5;
            this.slowed = true;
        }
    }

    /**
     * Restores the balloon to its base speed.
     */
    public void unSlowDown() {
        if (this.slowed) {
            this.speed = this.baseSpeed;
            this.slowed = false;
        }
    }

    /**
     * Returns whether the balloon is currently frozen.
     * 
     * @return true if frozen
     */
    public boolean isFrozen() {
        return this.frozen;
    }

    /**
     * Returns whether the balloon is currently slowed.
     * 
     * @return true if slowed
     */
    public boolean isSlowed() {
        return this.slowed;
    }
}
