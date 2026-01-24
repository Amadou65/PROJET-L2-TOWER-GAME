package game;
public class baloon {
    private int health;         // Résistance : 1, 2 ou 4 
    private double speed;       // Vitesse propre 
    private int pathIndex;      // Position actuelle dans la liste du chemin
    private boolean frozen;

    public baloon(int level) {
        this.health = level;
        this.speed = 1.0; 
        this.pathIndex = 0;
        this.frozen = false;
    }

    public void move() {
        if (!frozen) {
            this.pathIndex++; 
        }
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public boolean isPopped() {
        return this.health <= 0;
    }

    public int getReward() {
        return 10;
    }
}
