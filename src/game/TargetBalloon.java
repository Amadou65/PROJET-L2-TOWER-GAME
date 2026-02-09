package game;

import java.util.List;

/**
 * Classe utilitaire pour gérer la détection et le ciblage des ballons.
 */
public class TargetingSystem {

    /**
     * Calcule la distance réelle (Euclidienne) entre une tour et un ballon.
     * Utilise les coordonnées précises (double) du ballon.
     */
    public static double calculateDistance(Tower t, Position towerPos, Balloon b) {
        double dx = b.getX() - towerPos.getX();
        double dy = b.getY() - towerPos.getY();
        // Formule de Pythagore pour la distance réelle
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Sélectionne la cible prioritaire parmi les ballons actifs.
     * @param actif La liste des ballons sur le plateau.
     * @param t La tour qui cherche une cible (utilise son 'scope').
     * @param towerPos La position fixe de la tour.
     * @return Le ballon le plus avancé dans le scope, ou null.
     */
    public static Balloon getBestTarget(List<Balloon> actif, Tower t, Position towerPos) {
        Balloon bestTarget = null;
        double maxProgress = -1.0;

        for (Balloon b : actif) {
            // 1. Calcul de la distance
            double dist = calculateDistance(t, towerPos, b);

            // 2. Vérification de la portée (scope)
            if (dist <= t.scope) {
                
                // 3. Sélection du ballon le plus avancé 
                // On utilise b.getDistance() pour la progression totale
                if (b.getDistance() > maxProgress) {
                    maxProgress = b.getDistance();
                    bestTarget = b;
                }
            }
        }
        return bestTarget;
    }
}