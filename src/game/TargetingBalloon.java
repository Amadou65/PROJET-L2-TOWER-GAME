package game;

import java.util.*;

/**
 * Classe utilitaire pour gérer la détection et le ciblage des ballons.
 */
public class TargetingBalloon {

    /**
     * Calcule la distance réelle (Euclidienne) entre une tour et un ballon.
     * Utilise les coordonnées précises (double) du ballon.
     */
    public static double calculateDistance(Tower t, Balloon b) {
        double dx = b.getX() - t.getX();
        double dy = b.getY() - t.getY();
        // Formule de Pythagore pour la distance réelle
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Sélectionne tous les ballons dans la portée d'une tour.
     * @param balloons
     * @param t
     * @return la liste des ballons dans la portée de la tour
     */
    public static List<Balloon> getAllTargets(List<Balloon> balloons, Tower t) {
        List<Balloon> inRange = new ArrayList<>();
        for (Balloon b : balloons) {
            if (b.isPopped()) continue;
            double dist = calculateDistance(t, b);
            if (dist <= t.getScope()) {
                inRange.add(b);
            }
        }
        return inRange;
    }

    /**
     * Sélectionne la cible prioritaire parmi les ballons actifs.
     * @param balloons La liste des ballons sur le plateau.
     * @param t La tour qui cherche une cible (utilise son 'scope').
     * @return Le ballon le plus avancé dans le scope, ou null.
     */
    public static Balloon getBestTarget(List<Balloon> balloons, Tower t) {
        Balloon bestTarget = null;
        double maxProgress = -1.0;
        List<Balloon> inRange = TargetingBalloon.getAllTargets(balloons, t);

        for (Balloon b : inRange) {
            if (b.getDistance() > maxProgress) {
                maxProgress = b.getDistance();
                bestTarget = b;
            }
        }
        return bestTarget;
    }
}
