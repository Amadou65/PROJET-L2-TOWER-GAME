package game;
import java.util.List;

/**
 * Classe utilitaire pour gérer la détection et le ciblage des ballons par les tours.
 */

public class TargetingSystem {

    /**
     * Calcule la distance euclidienne entre une tour et un ballon.
     * Utilise les coordonnées précises (double) pour la précision demandée.
     */
    public static double calculateDistance(Tower t, Position towerPos, Balloon b) {
        double dx = b.getX() - towerPos.getX();
        double dy = b.getY() - towerPos.getY();
        return Math.sqrt(dx * dx + dy * dy); // Formule de Pythagore [cite: 53, 62]
    }

    /**
     * Sélectionne le ballon le plus "avancé" dans le rayon d'action de la tour.
     * * @param actif La liste des ballons présents sur le plateau 
     * @param t La tour qui cherche une cible
     * @param towerPos La position de cette tour sur la grille 
     * @return Le ballon le plus proche de la fin de parcours, ou null si aucun n'est à portée.
     */
    public static Balloon getBestTarget(List<Balloon> actif, Tower t, Position towerPos) {
        Balloon bestTarget = null;
        double maxProgress = -1.0;

        for (Balloon b : actif) {  
            // 1. Calcul de la distance réelle entre la tour et le ballon
            double dist = calculateDistance(t, towerPos, b);

            // 2. Vérification si le ballon est dans le périmètre d'action (scope) 
            if (dist <= t.scope) {
                
                // 3. Priorité au ballon le plus proche de sa fin de parcours 
                // On utilise b.getDistance() qui suit la progression totale sur le chemin.
                if (b.getDistance() > maxProgress) {
                    maxProgress = b.getDistance();
                    bestTarget = b;
                }
            }
        }
        return bestTarget;
    }
}