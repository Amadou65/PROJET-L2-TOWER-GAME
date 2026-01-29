package game;

import java.util.List;
import java.util.Random;

/**
 * Gestionnaire de mouvement fluide avec vitesses aléatoires.
 */
public class BalloonMovementManager {

    private double x;              // Position réelle X
    private double y;              // Position réelle Y
    private double speed;          // Vitesse v (générée aléatoirement)
    private int currentTargetIndex; // Index de la prochaine étape
    private List<Position> path;   // Le chemin (Random ou Classical)
    
    private static final Random random = new Random();

    /**
     * Constructeur
     * @param path Le chemin à suivre
     */
    public BalloonMovementManager(List<Position> path) {
        this.path = path;
        this.currentTargetIndex = 1;

        // --- GÉNÉRATION D'UNE VITESSE ALÉATOIRE ---
        // Exemple : entre 0.05 (lent) et 0.20 (rapide) unités par tic.
        // Un ballon à 0.1 met 10 tics pour traverser une case de 1.0.
        this.speed = 0.05 + (0.15 * random.nextDouble());

        if (path != null && !path.isEmpty()) {
            this.x = path.get(0).getX();
            this.y = path.get(0).getY();
        }
    }

    /**
     * Calcule la progression du ballon.
     * @return false si le ballon a atteint la fin, true sinon.
     */
    public boolean update() {
        if (path == null || currentTargetIndex >= path.size()) {
            return false;
        }

        Position target = path.get(currentTargetIndex);

        // Calcul de la distance vers la prochaine étape
        double dx = target.getX() - this.x;
        double dy = target.getY() - this.y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Si le ballon est à portée de la cible avec sa vitesse actuelle
        if (distance <= speed) {
            // On se cale sur le point exact (gestion du virage)
            this.x = target.getX();
            this.y = target.getY();
            currentTargetIndex++; 
        } else {
            // Déplacement fluide : Position += (Vecteur Direction * Vitesse)
            this.x += (dx / distance) * speed;
            this.y += (dy / distance) * speed;
        }

        return currentTargetIndex < path.size();
    }

    // --- GETTERS ---
    
    public double getX() { return x; }
    public double getY() { return y; }
    public double getSpeed() { return speed; }

    /**
     * Retourne la coordonnée X arrondie pour la grille
     */
    public int getGridX() { return (int) Math.round(x); }

    /**
     * Retourne la coordonnée Y arrondie pour la grille
     */
    public int getGridY() { return (int) Math.round(y); }
    
    public boolean isAtEnd() {
        return currentTargetIndex >= path.size();
    }
}