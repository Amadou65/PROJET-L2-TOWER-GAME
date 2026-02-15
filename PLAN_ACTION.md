# Plan d'Action - Projet Tower Defense
## Date: 15 février 2026

---

## 🎯 Vue d'Ensemble

Suite à l'analyse complète du projet (voir [ANALYSE_PROJET.md](ANALYSE_PROJET.md)), ce document présente un plan d'action concret pour l'équipe.

---

## ✅ Travaux Complétés (15 février 2026)

- [x] Analyse complète du code (ANALYSE_PROJET.md généré)
- [x] Résolution du conflit de merge dans `Board.java`
- [x] Ajout des méthodes manquantes (`Cell.getBallons()`, `Balloon.getX/Y()`)
- [x] Vérification de la compilation (code compile sans erreurs)
- [x] Exécution des tests (23/27 passent, 4 échecs pré-existants)

---

## 🔴 Priorité HAUTE - À faire cette semaine

### 1. Corriger les Tests Échoués

**Responsable suggéré**: Habiba + Yassin  
**Temps estimé**: 2-3 heures

#### Tests à corriger:

1. **BalloonTest.testBalloonMutationAndHealth()**
   - Problème: Le ballon ne change pas de niveau après avoir pris des dégâts
   - Attendu: niveau 2 après 2 dégâts sur un ballon niveau 4
   - Obtenu: niveau 4 (pas de changement)
   - Fichier: `src/game/Balloon.java`, méthode `takeDamage()`
   - Action: Vérifier la logique de mutation de niveau

2. **RandomBoardTest.TestnextPositions()**
   - Problème: Ordre des positions retournées incorrect
   - Attendu: `[(0, 1), (1, 0)]`
   - Obtenu: `[(1, 0), (0, 1)]`
   - Fichier: `src/game/board/RandomBoard.java`
   - Action: Vérifier l'ordre de retour ou ajuster le test

3. **RandomBoardTest.TestIsSameSideWhenItsOk()**
   - Problème: La méthode `isSameSide()` ne détecte pas correctement
   - Fichier: `src/game/board/RandomBoard.java`
   - Action: Revoir la logique de détection des côtés

4. **RandomBoardTest.TestIsEdgeWhenItsOk()**
   - Problème: La méthode `isEdge()` ne fonctionne pas comme prévu
   - Fichier: `src/game/board/RandomBoard.java`
   - Action: Vérifier la détection des bords

### 2. Finaliser le Système de Ciblage

**Responsable suggéré**: Yassin  
**Temps estimé**: 2 heures

- [ ] Tester `TargetingBalloon.getBestTarget()` avec différents scénarios
- [ ] Vérifier que les tours ciblent correctement le ballon le plus avancé
- [ ] Tester le changement de cible quand un ballon sort de portée
- [ ] Ajouter des tests unitaires pour `TargetingBalloon`

### 3. Tester le Combat Complet

**Responsable suggéré**: Amadou  
**Temps estimé**: 2-3 heures

- [ ] Créer un test d'intégration tours + ballons
- [ ] Vérifier que les projectiles infligent bien les dégâts
- [ ] Tester les tours non-projectiles (IceTower, SlowdownTower)
- [ ] Valider que les ballons meurent correctement
- [ ] Vérifier que le joueur gagne des crédits

---

## 🟡 Priorité MOYENNE - Semaines 6-7

### 4. Refactoring du Code

**Responsable suggéré**: Amadou + Ivkin  
**Temps estimé**: 3-4 heures

#### A. Extraire les Constantes Magiques

Créer `src/game/GameConstants.java`:

```java
package game;

public class GameConstants {
    // GameEngine
    public static final int SPAWN_INTERVAL = 20;
    public static final int FRAME_DELAY_MS = 50;
    
    // Player
    public static final int INITIAL_HEALTH = 20;
    public static final int INITIAL_CREDITS = 2500;
    public static final int BALLOON_REWARD = 10;
    
    // Board
    public static final int MIN_PATH_LENGTH = 12;
    
    // Balloon speeds
    public static final double SPEED_LEVEL_1 = 0.05;
    public static final double SPEED_LEVEL_2 = 0.1;
    public static final double SPEED_LEVEL_4 = 0.15;
}
```

Puis remplacer tous les magic numbers dans:
- `GameEngine.java`
- `Player.java`
- `Balloon.java`
- `board/RandomBoard.java`

#### B. Refactoriser GameEngine.game()

Diviser la méthode `game()` (99 lignes) en méthodes plus petites:

```java
public void game() {
    int time = 0;
    GameStats stats = new GameStats(reserve.size());
    
    while ((!reserve.isEmpty() || !actif.isEmpty()) && player.isAlive()) {
        time++;
        spawnPhase(time);
        movementPhase(stats);
        combatPhase(time);
        renderPause();
    }
    
    displayFinalReport(time, stats);
}

private void spawnPhase(int time) { ... }
private void movementPhase(GameStats stats) { ... }
private void combatPhase(int time) { ... }
private void renderPause() { ... }
private void displayFinalReport(int time, GameStats stats) { ... }
```

### 5. Compléter le Système d'Évolution

**Responsable suggéré**: Ivkin  
**Temps estimé**: 2-3 heures

- [ ] Implémenter `Tower.upgrade(Evolution e)` (actuellement en TODO)
- [ ] Tester l'achat d'améliorations
- [ ] Vérifier que les coûts sont correctement déduits
- [ ] Valider que les bonus s'appliquent (portée, cadence, puissance)
- [ ] Ajouter des tests pour le système d'évolution

### 6. Mettre à Jour l'UML

**Responsable suggéré**: Amadou  
**Temps estimé**: 2 heures

- [ ] Ouvrir `UML LIVRABLE 3.mdj`
- [ ] Ajouter `TargetingBalloon` class
- [ ] Ajouter `BalloonMovementManager` class
- [ ] Mettre à jour la hiérarchie des tours (7 types au lieu de 3)
- [ ] Ajouter les nouvelles méthodes dans Board, Cell, Balloon
- [ ] Exporter en PDF

---

## 🟢 Priorité BASSE - Semaines 8-9

### 7. Uniformiser le Nommage

**Responsable suggéré**: Tous  
**Temps estimé**: 1-2 heures

Choisir une convention (anglais recommandé) et renommer:

```java
// Avant
private List<Balloon> actif;
public boolean isAlife()

// Après
private List<Balloon> active;
public boolean isAlive()
```

Autres variables à renommer:
- `list_ball` → `balloonList` ou `balloons`
- `tower_list` → `towerList` ou `towers`
- Commentaires français → anglais (optionnel)

### 8. Optimisations de Performance

**Responsable suggéré**: Yassin  
**Temps estimé**: 3-4 heures (optionnel)

- [ ] Implémenter spatial indexing pour `getBallonsInRange()`
- [ ] Utiliser une liste triée pour les ballons (par distance)
- [ ] Cacher les calculs de distance fréquents
- [ ] Profiler le code pour identifier les bottlenecks

### 9. Améliorer la Couverture de Tests

**Responsable suggéré**: Habiba  
**Temps estimé**: 2-3 heures

Tests manquants:
- [ ] Tests pour `TargetingBalloon`
- [ ] Tests pour `Evolution` system
- [ ] Tests d'intégration GameEngine complet
- [ ] Tests de cas limites (crédits négatifs, plateau vide, etc.)

---

## 📅 Planning Recommandé

### Semaine 6 (Actuelle)
- Lundi-Mardi: Corriger les 4 tests échoués
- Mercredi: Finaliser système de ciblage
- Jeudi-Vendredi: Tester combat complet

### Semaine 7
- Lundi-Mardi: Refactoring (constantes + GameEngine)
- Mercredi: Compléter système d'évolution
- Jeudi-Vendredi: Mettre à jour UML + documentation

### Semaine 8
- Début interface graphique (Livrable 3)
- Uniformiser nommage si temps disponible

### Semaine 9
- Continuer interface graphique
- Optimisations si nécessaire
- Tests supplémentaires

---

## 🎓 Répartition Suggérée par Membre

### Amadou
- [x] Analyse complète du projet
- [ ] Tester combat complet (intégration)
- [ ] Refactoring GameEngine.game()
- [ ] Mettre à jour UML
- [ ] Interface graphique (semaine 8)

### Yassin
- [ ] Corriger tests RandomBoard (nextPositions, isSameSide, isEdge)
- [ ] Finaliser système de ciblage
- [ ] Tests TargetingBalloon
- [ ] Optimisations performance (optionnel)

### Ivkin
- [ ] Extraire constantes magiques
- [ ] Compléter système d'évolution (TODO)
- [ ] Tester achats/améliorations
- [ ] Refactoring nommage

### Habiba
- [ ] Corriger test BalloonTest (mutation)
- [ ] Vérifier logique takeDamage() dans Balloon
- [ ] Ajouter tests manquants
- [ ] Améliorer couverture de tests

---

## 📊 Indicateurs de Succès

### Cette Semaine
- [ ] 27/27 tests passent (100%)
- [ ] Code compile sans warnings
- [ ] Système de combat fonctionne end-to-end
- [ ] Documentation à jour

### Dans 2 Semaines
- [ ] Code refactorisé et plus maintenable
- [ ] UML synchronisé avec le code
- [ ] Système d'évolution complet
- [ ] Note de qualité: A- (90/100)

### Dans 4 Semaines
- [ ] Interface graphique opérationnelle
- [ ] Tous les livrables 1-3 complétés
- [ ] Préparation du livrable 4

---

## 🔗 Ressources

- **Analyse complète**: [ANALYSE_PROJET.md](ANALYSE_PROJET.md)
- **README**: [README.md](README.md)
- **UML Actuel**: `UML LIVRABLE 3.mdj`
- **Tests**: `tests/game/`

---

## 💬 Questions / Problèmes

Si vous rencontrez des difficultés:
1. Consulter l'analyse détaillée (ANALYSE_PROJET.md)
2. Discuter en équipe pendant les séances
3. Documenter dans le journal de bord
4. Demander aide au professeur si bloqué

---

**Créé par**: GitHub Copilot Agent  
**Date**: 15 février 2026  
**Version**: 1.0  
**Prochaine révision**: Fin de semaine 6
