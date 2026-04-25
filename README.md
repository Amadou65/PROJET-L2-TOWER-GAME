# l2s4-projet-2026


# Equipe

- Amadou Balde
- Serhii Ivkin
- Yassin Daho
- Habiba Boubakary

# Sujet

[Le sujet 2026](https://www.fil.univ-lille.fr/~varre/portail/l2s4-projet/sujet2026.pdf)

# Livrables

Les paragraphes concernant les livrables doivent être remplis avant la date de rendu du livrable. A chaque fois on décrira l'état du projet par rapport aux objectifs du livrable. Il est attendu un texte de plusieurs lignes qui explique la modélisation choisie, et/ou les algorithmes choisis et/ou les modifications apportées à la modélisation du livrable précédent.

Un lien vers une image de l'UML doit être fourni (une photo d'un diagramme UML fait à la main est suffisant).


## Livrable 1
Structure du plateau : Nous avons séparé la logique de positionnement (Position), l'unité de base du plateau (Cell) et la gestion de la grille (Board). Cette séparation permet une plus grande flexibilité pour l'affichage et la gestion des entités.

    Système de Défense (Tours et Projectiles) : Nous avons implémenté une structure hiérarchique pour les tours. La classe abstraite Tower est déclinée en ProjectileTower (pour les tours tirant des munitions comme DartMonkey ou BombTower) et NonProjectileTower (pour les tours à effets de zone ou de ralentissement comme IceTower). Chaque tour est associée à un type de Projectile spécifique, dont les dégâts varient selon la spécialisation.

    Système d'Évolution : Pour permettre la progression en jeu, nous avons modélisé un système d'améliorations via la classe Evolution. Ce choix permet d'appliquer différents bonus (Puissance, Cadence, Portée, Projectile) de manière modulaire sur les tours existantes.

    Les Ennemis : La classe Baloon définit les caractéristiques essentielles des cibles (vie, vitesse, force), préparant ainsi la logique de déplacement sur le chemin.

2. Algorithmes implémentés

L'effort principal de ce livrable a porté sur la génération du chemin que doivent suivre les ballons. Nous avons développé deux approches :

    ClassicalBoard : Un chemin prédéfini et linéaire pour un mode de jeu standard.

    RandomBoard (Génération aléatoire) : L'algorithme de génération de chemin dans RandomBoard est plus complexe. Il fonctionne selon les étapes suivantes :

        Sélection du départ : Choix aléatoire d'une case sur le périmètre de la grille.

        Marche aléatoire contrainte : Le chemin avance de case en case. À chaque étape, l'algorithme vérifie les positions adjacentes valides (dans la grille) et filtre celles déjà visitées pour éviter les boucles cycliques.

        Vérification des conditions de victoire (Validité) : Le chemin n'est validé que s'il respecte trois critères : une longueur minimale (12 cases), une sortie située sur un bord du plateau, et une sortie qui n'est pas sur le même côté que l'entrée. Si l'algorithme se retrouve dans un cul-de-sac, il réinitialise le parcours et recommence (méthode de "backtracking" simplifiée).

3. Modifications et Tests

Par rapport aux ébauches précédentes, nous avons renforcé la robustesse du code par l'ajout de tests unitaires JUnit. Ces tests vérifient notamment :

    La validité des positions sur les bords (isEdge).

    L'absence de cycles dans les chemins générés (isDoingCircle).

    Le respect des dimensions de la grille et des règles de distance.




## Livrable 2
Commandes de compilations:

Pour la compilation des  diffentes classes:

    mkdir -p bin
    javac -d bin -sourcepath src \
    src/game/Position.java \
    src/game/Player.java \
    src/game/Balloon.java \
    src/game/Cell.java \
    src/game/Board.java \
    src/game/GameEngine.java \
    src/game/board/RandomBoard.java \
    src/game/board/ClassicalBoard.java \
    src/game/Livrable2a.java \
    src/game/Livrable2b.java

Pour creer la java doc :

 - javadoc -d docs -sourcepath src \
    src/game/Livrable2a.java src/game/Livrable2b.java \
    src/game/GameEngine.java src/game/Balloon.java \
    src/game/Cell.java src/game/Board.java \
    src/game/Position.java src/game/Player.java

Ppour la creation des differents jar:

    - jar cfe livrable2a.jar game.Livrable2a -C bin .

    - jar cfe livrable2b.jar game.Livrable2b -C bin .

Pour l'execution des Livrables:


    - java -jar livrable2a.jar
    - java -jar livrable2b.jar

Pour l'execution de la methode Main

    - java -classpath classes game.Main

Pour les differentes classes de teste:

    - javac -d bin -cp "bin:junit-console.jar" tests/game/*.java tests/game/board/*.java

Pour executer les testes:

    - java -jar junit-console.jar --class-path bin --scan-class-path




### Atteinte des objectifs

Yassin :

    Mouvement Fluide : Le passage de coordonnées entières à des coordonnées double permet aux ballons de se déplacer à l'intérieur des cases plutôt que de sauter de l'une à l'autre.

    Vitesse Aléatoire : Chaque ballon possède sa propre vitesse générée au hasard, influençant sa progression en fonction du temps. 

    Suivi de Trajectoire : Le système utilise les listes de positions de RandomBoard ou ClassicalBoard pour diriger les ballons.

    Gestion des Virages : Le code détecte l'approche d'un point de passage et ajuste la position pour assurer une transition précise vers la direction suivante.
    
    Lien avec la Grille : Les positions précises sont arrondies pour permettre au GameEngine de situer chaque ballon dans une Cell spécifique du plateau.

    Création de la classe Tower : Définit les bases de la défense (portée, cadence, coût). Elle se sépare en tours avec projectiles (ex: DartMonkey, BombTower) qui peuvent tirer, et tours sans projectiles (ex: IceTower) qui gèrent des effets temporels.

    Création de la classe Projectile : Gère la puissance d'attaque via un système de dégâts. Chaque type possède une valeur fixe, comme le Dart (10) ou la ExtraBomb (45).

    Création de la classe Evolution : Permet d'améliorer les tours à projectiles. Elle définit des types d'upgrades spécifiques (puissance, cadence, portée, ou projectile) et gère leur coût d'achat.

Amadou :

    Développement du moteur de jeu central (GameEngine) : Mise en place de la boucle de jeu principale basée sur un système de "tics" temporels permettant de cadencer les actions.

    Gestion du cycle de vie des entités : Implémentation complète du flux des ballons : apparition depuis la réserve, gestion des ballons actifs sur le plateau, et suppression lors de l'arrivée ou de la destruction.

    Algorithme de mouvement fluide : Conception d'un système de déplacement "infra-case" utilisant une variable de distance fractionnaire. Cela permet aux ballons d'avoir des vitesses différentes et un mouvement visuel fluide plutôt que des bonds de case en case.

    Synchronisation Plateau/Moteur : Réalisation de la logique de transfert des ballons entre les cellules (Cell) du plateau lors du changement d'indice dans le chemin.

    Interface Joueur : Intégration des mécaniques de récompenses (crédits) et de pénalités (perte de vie) en temps réel selon les événements du jeu.

    Fiabilisation du code : Utilisation de boucles itératives inversées pour permettre la modification sécurisée des listes d'entités en cours de parcours (évitant les erreurs de type ConcurrentModificationException).

Serhii :

    Creation de la classe de joueur (Player) : La classe Player représente l’état global du joueur au cours de la partie.
    À l’initialisation, le joueur dispose de 20 points de vie et de 2500 crédits, conformément aux règles du jeu

    Le joueur possède un compteur de points de vie décrémenté à chaque ballon atteignant la fin de sa trajectoire.
    La méthode onHit() retire un point de vie, tandis que le prédicat isAlife() permet de vérifier si la partie peut continuer.

    Des methode buyTower() permet au joueur d’acheter une tour et de la placer sur le plateau à une position donnée.
    L’achat est validé uniquement si le joueur dispose de suffisamment de crédits. sellTower() permet de vendre le tour.

    Développement de la classe Journal : La classe Journal est chargée de conserver des statistiques globales sur la partie.
    Elle enregistre les actions importantes effectuées par le joueur, telles que :

    * l’achat de tours,

    * la nombre des credits obtenu totale,

    * l’achat d’améliorations.

    Ce mécanisme permet de centraliser les informations liées à l’évolution de la partie et de faciliter le suivi des actions du joueur.

    Optimisation des deux classes Boards avec creation des fonctions abstraite

### Difficultés restant à résoudre
Amadou :    

    Optimisation de la détection de cible : La logique permettant aux tours de scanner efficacement les ballons à portée sans ralentir le moteur de jeu reste à affiner pour le prochain livrable.

    Équilibrage des vitesses : Ajuster les ratios entre la vitesse de rafraîchissement (tics) et la progression des ballons pour garantir une difficulté progressive.

    Gestion des collisions complexes : Prévoir le comportement du moteur si plusieurs types de ballons avec des effets différents (ralentissement, gel) occupent la même cellule.

    Transition vers l'interface graphique : Adapter les logs de la console vers un affichage visuel fluide (Livrable 3).

Serhii :
    Transfert de statistique entre les manches 

    Creation des exeptions


Habiba : 
    Conception de la classe Balloon : Implémentation d'une entité autonome capable de gérer ses propres attributs de survie (santé) et de cinématique (vitesse) en fonction de son niveau de résistance (1, 2 ou 4).

    Mécanique de "Mutation" des Entités : Développement de la logique de rétrogradation de niveau lors de la réception de dégâts.Un ballon de niveau 4 (Rose) ne disparaît pas immédiatement mais "mute" en un niveau inférieur (Bleu, puis Rouge), respectant ainsi la hiérarchie de résistance du sujet.

    Dynamisme des Profils de Vitesse : Mise en place d'un système de vitesses variables corrélées au niveau de l'ennemi.L'entité adapte dynamiquement sa vitesse de progression (determineSpeed) en temps réel dès que sa structure change suite à un impact.

    Interface d'Affichage Prioritaire : Optimisation de la classe Cell pour gérer la priorité visuelle. Développement d'un mécanisme d'affichage conditionnel

    Tests de Fiabilité Unitaire (JUnit) : Création d'une suite de tests (BalloonTest) validant les scénarios critiques : calcul exact des dégâts,mutation de niveau, et vérification que la vitesse décroissante est correctement appliquée lors de la perte de résistance.

Yassin :

    Création de la classe BalloonMovementManager : Difficulté à synchroniser les coordonnées double (fluides) avec la grille de Cell en int pour que les tours détectent toujours les ballons.

    Gestion des virages : Risque que le ballon dépasse une intersection à cause de sa vitesse ; il faut forcer sa position sur le point exact du chemin avant de tourner.

    Aléatoire : La gestion des vitesses au hasard complique l'affichage, car plusieurs ballons peuvent se superposer sur une même case du plateau.

## Livrable 3

Commandes de compilation et d'exécution :

    make classes

Pour générer les JARs exécutables :

    make jar

Pour exécuter les livrables :

    - java -jar livrable3a.jar <hauteur> <largeur> <nbBallons>
    - java -jar livrable3b.jar <hauteur> <largeur> <nbChemins>

Pour générer la javadoc :

    make docs

Pour compiler et lancer les tests :

    make tests
    make runtests

### Atteinte des objectifs

Amadou :

    Correction de tous les bugs de compilation bloquants (conflits Git dans Board.java,
    méthodes manquantes getBallons(), getTowers(), getX(), getY()).

    Réécriture du GameEngine avec logs horodatés [t=N] pour chaque événement du jeu :
    ballon touché, gelé, ralenti, détruit ou sorti. Le Journal est mis à jour en temps réel.

    Création du Makefile avec toutes les cibles demandées (classes, jar, docs, tests, runtests).

Yassin :

    Implémentation de Livrable3a : plateau aléatoire dont le chemin part obligatoirement
    du bord gauche. Création de LeftStartRandomBoard, sous-classe de RandomBoard qui
    surcharge creerListeDepart() pour limiter les départs à la colonne 0.
    Placement aléatoire de 2 tours de chaque type sur les cases hors-chemin.

Serhii :

    Implémentation de Livrable3b : plateau avec N chemins rectilignes distincts.
    Chaque chemin est généré par ClassicalBoard.path() avec un point de départ
    différent. Un ballon de niveau aléatoire (1, 2 ou 4) est assigné à chaque chemin.
    Correction de ClassicalBoard qui confondait height et width dans ses boucles.

Habiba :

    Amélioration de la classe Balloon : ajout de baseSpeed pour restaurer la vitesse
    après un ralentissement, ajout de isFrozen() et isSlowed() pour les logs.
    Correction de takeDamage() pour que le level suive health (mutation visible).
    Correction de Journal.recordHealthLost() qui décrémentait au lieu d'incrémenter.

### Difficultés restant à résoudre

    Trois tests de RandomBoardTest échouent : ils testent des positions (4,4) comme
    étant sur le bord d'un plateau 6×11, ce qui est incorrect (la position (4,4) n'est
    sur aucun bord). Ce sont des bugs dans les tests eux-mêmes et non dans notre code.

### Lien UML

    Le diagramme UML est disponible à la racine du projet dans le dossier UML/.

## Livrable 4

Commandes de compilation et d'exécution :

    make classes

Pour générer les JARs exécutables :

    make jar

Pour exécuter les livrables :

    - java -jar livrable4a.jar <hauteur> <largeur> <nbBallons>
    - java -jar livrable4b.jar <hauteur> <largeur> <nbBallons>

Pour générer la javadoc :

    make docs

Pour compiler et lancer les tests :

    make tests
    make runtests
    
### Atteinte des objectifs

Amadou :

    Livrable4.java (classe parent) : concentre les méthodes utilitaires communes aux deux
    scénarios. placeTowers() achète et place 2 tours de chaque type sur les cases libres via
    player.buyTower(), ce qui déduit automatiquement les crédits. buyEvolutions() tente
    d'appliquer une évolution POWER (200 crédits) et CADENCE (150 crédits) sur chaque tour
    en gérant l'exception TypeTowerException levée pour IceTower et SlowdownTower.

    Livrable4a.java : scénario A avec plateau aléatoire (LeftStartRandomBoard). Le chemin
    part obligatoirement du bord gauche. Les tours sont achetées via le joueur, les évolutions
    appliquées, puis la manche est lancée avec le même Player (crédits déjà déduits).

    Livrable4b.java : scénario B avec plateau classique (ClassicalBoard). Même logique que 4a
    mais avec un chemin rectiligne fixe.

    GameEngineTest.java : ajout de deux nouveaux tests JUnit :
    - testPlayerLosesLifeWhenBalloonEscapes : vérifie que la santé baisse si un ballon passe.
    - testPlayerCreditsPositiveAfterGame : vérifie que les crédits restent positifs après la partie.

    Makefile mis à jour pour créer livrable4a.jar et livrable4b.jar.

Pour exécuter les livrables :

    - java -jar livrable4a.jar <hauteur> <largeur> <nbBallons>
    - java -jar livrable4b.jar <hauteur> <largeur> <nbBallons>


Habiba:

Logique des Ballons : Finalisation du système de mutation. Quand un ballon est touché, il change de niveau (ex: Rose vers Bleu) et sa vitesse s'adapte automatiquement.
Documentation & Qualité : Rédaction de la Javadoc technique et création des protocoles de tests pour valider l'économie et les cas limites.

Gestion des évolutions : Nous avons choisi d'utiliser un HashSet<EvolutionType> dans la classe ProjectileTower. Ce choix permet de garantir l'unicité des améliorations (une tour ne peut pas avoir deux fois la même évolution) et offre une complexité constante O(1) pour vérifier (hasEvolution) ou supprimer (removeEvolution) une amélioration.Mutation des ballons : La méthode takeDamage du ballon gère dynamiquement le changement de niveau et de vitesse. Cela permet une transition fluide entre les types de ballons sans avoir à recréer d'objets.Affichage par événements : Pour respecter les consignes de performance et de lisibilité, l'affichage ne rafraîchit pas la grille entière, mais génère des logs horodatés pour chaque action significative (tir, destruction, sortie).
### Difficultés restant à résoudre

## Livrable 5

![UML Livrable 5](UML/UML%20LIVRABLE%205.png)

Commandes de compilation et d'exécution :

    make classes

Pour générer les JARs exécutables :

    make jar

Pour exécuter le livrable :

    java -jar livrable5.jar <largeur> <hauteur> <nbBallons>

Pour générer la javadoc :

    make docs

Pour compiler et lancer les tests :

    make tests
    make runtests

### Atteinte des objectifs

Amadou :

    Livrable5.java (classe parent) : implémentation de la boucle d'actions du joueur
    via la méthode playerActionPhase(). Cette méthode utilise l'interface ListChooser
    pour proposer les choix au joueur de manière générique (interactif ou aléatoire).
    Le joueur peut effectuer autant d'actions qu'il le souhaite tant que ses crédits
    restent positifs. Cinq actions sont disponibles : acheter une tour, évoluer une
    tour, vendre une tour, vendre une évolution, ou terminer le tour.

    handleBuyTower() : filtre les types de tours achetables selon les crédits du
    joueur, propose les cases libres (hors chemin et sans tour), puis appelle
    player.buyTower() pour placer et payer la tour.

    handleEvolveTower() : affiche les ProjectileTower présentes sur le plateau,
    filtre les évolutions non encore appliquées et compatibles avec les crédits,
    puis appelle player.buyEvolution() avec gestion de TypeTowerException.

    handleSellTower() : propose les tours du plateau à la vente et appelle
    player.sellTower() pour rembourser le joueur.

    handleSellEvolution() : affiche les tours ayant des évolutions, propose celles
    à revendre, puis appelle player.sellEvolution() (méthode implémentée par Serhii)
    qui se charge de retirer l'évolution, rembourser le joueur et enregistrer dans
    le Journal. Gestion de TypeTowerException et NoEvolutionException.

    Livrable5.java (programme unique) : programme principal avec plateau aléatoire
    (LeftStartRandomBoard) et choix automatiques via RandomListChooser. Le programme
    génère un plateau, place 2 tours de chaque type, puis exécute 10 manches avec
    application automatique d'évolutions (manches 1–5) et suppression automatique
    (manches 6–10).

    Création de trois classes utilitaires dans le package game.choice :
    - PlayerAction (enum) : les 5 actions du joueur avec label français pour
      l'affichage dans le ListChooser.
    - TowerChoice : wrapper affichant le nom, coût, portée et cadence d'une tour
      pour une sélection lisible.
    - EvolutionChoice : wrapper affichant le type et coût d'une évolution.

    Makefile mis à jour pour créer livrable5.jar.

    Intégration avec le travail des autres membres :
    - Utilisation de Player.sellEvolution() (Serhii) dans handleSellEvolution().
    - Compatibilité avec removeEvolution(Evolution e) qui prend désormais un objet
      Evolution complet, et non plus un simple EvolutionType.
    - Compilation et tests validés : make classes, make jar, exécution de
      livrable5.jar OK.

Serhii :

    Player.sellEvolution() : implémentation de la méthode de vente d'évolution
    dans Player.java avec gestion des exceptions TypeTowerException et
    NoEvolutionException. La méthode retire l'évolution de la tour, rembourse
    les crédits et enregistre la vente dans le Journal.

    ProjectileTower : ajout de la méthode abstraite removeEvolution(Evolution e)
    et implémentation dans chaque sous-classe (DartMonkey, BombTower, etc.) pour
    réinitialiser correctement les stats modifiées par l'évolution.

    Journal.java : ajout de recordTowerSold() et recordEvolutionSold() pour
    enregistrer les ventes dans les statistiques du joueur.

    NoEvolutionException : nouvelle exception levée quand on tente de vendre
    une évolution que la tour ne possède pas.

Yassin :

    Création de l'interface ListChooser et de ses implémentations
    (RandomListChooser, InteractiveListChooser). Participation aux tests
    unitaires vérifiant le bon fonctionnement du mécanisme de choix.


Habiba :

    Mise à jour du diagramme UML : Intégration du nouveau package game.choice 
    (enum PlayerAction, wrappers de choix) et ajout des méthodes de revente 
    pour refléter la nouvelle architecture du Livrable 5.

    Documentation (Javadoc) : Rédaction complète des commentaires pour la 
    nouvelle classe parente Livrable5 et ses sous-méthodes de gestion d'actions.

    Rédaction du README : Explication détaillée des choix de modélisation 
    pour le Livrable 5, notamment la séparation des menus et la sécurité des évolutions.

    Relecture et validation : Vérification croisée du code pour s'assurer que 
    la méthode removeEvolution est bien implémentée dans chaque tour spécifique 
    (DartMonkey, BombTower...) avant la fusion finale.

### Difficultés rencontrées

Amadou :

    Utilisation des types génériques avec ListChooser : l'interface ListChooser<T>
    est paramétrée, mais la phase d'actions nécessite de choisir des objets de
    types différents (PlayerAction, TowerChoice, Position, EvolutionChoice).
    La solution retenue est d'utiliser le type brut (raw type) avec @SuppressWarnings
    pour permettre un ListChooser unique tout au long de la phase d'actions.

    Accès au champ evolutions de ProjectileTower : le champ est protected dans le
    package game.tower, donc inaccessible depuis game.Livrable5. La solution a été
    d'utiliser hasEvolution() en itérant sur les 4 types d'évolution possibles
    plutôt que d'accéder directement au HashSet.

    Intégration avec Serhii : la signature de removeEvolution() a changé (prend
    un Evolution au lieu d'un EvolutionType). Résolu en créant un objet Evolution
    complet et en utilisant player.sellEvolution() directement.

Habiba :

    Assurer la cohérence de la documentation technique et du README entre le 
    travail de Serhii (la logique de revente) et celui d'Amadou (la gestion 
    des menus) pour que le rapport final soit parfaitement aligné avec le sujet.

### Choix de modélisation

- **Séparation des menus :** Nous avons créé un package `game.choice` pour séparer l'affichage des menus de la logique du jeu. Cela rend le code plus propre.
- **Sécurité des évolutions :** L'utilisation d'une méthode abstraite `removeEvolution` oblige chaque tour à définir comment elle annule ses bonus. Cela évite les bugs lors de la revente.
- **Clarté de la console :** Au lieu d'afficher la grille à chaque seconde, le jeu affiche seulement des messages d'événements (ex: "Ballon touché"). C'est beaucoup plus lisible pour des parties longues.

**Mécanisme d'action via ListChooser**

Le cœur du Livrable 5 est l'interface `ListChooser<T>` qui abstrait la sélection
d'un élément parmi une liste. Deux implémentations existent :
- `RandomListChooser` : sélection aléatoire, utilisée dans Livrable5 pour
  le mode automatique (placement et évolutions gérés sans intervention humaine).
- `InteractiveListChooser` : saisie clavier, disponible pour un mode interactif.

Ce choix permet de réutiliser la totalité de la logique de `Livrable5.java` dans
les deux scénarios sans duplication de code.

**Boucle de 10 manches dans Livrable5**

La boucle des 10 manches a été placée dans `Livrable5.main()` plutôt que dans
`GameEngine` pour respecter la séparation des responsabilités : `GameEngine`
gère une seule manche (spawn, mouvement, tirs), tandis que la logique de progression
(évolutions, numérotation des manches) appartient au programme principal.

**Placement garanti de 2 tours de chaque type**

La méthode `placeTowersViaActions()` itère explicitement sur les 7 types de tours
en 2 exemplaires chacun, et utilise `chooser.choose()` pour sélectionner la
position via le mécanisme d'action. Le joueur est initialisé avec 15 000 crédits
(coût total des 14 tours ≈ 9 300) pour garantir le placement complet.

**Filtrage des tours avant proposition**

Dans `applyOneEvolutionViaAction()`, seules les tours ayant au moins une évolution
non encore appliquée ET abordable sont proposées au mécanisme de choix. De même,
`removeOneEvolutionViaAction()` ne propose que les tours qui possèdent effectivement
des évolutions. Ce filtrage évite les no-ops et garantit qu'une action aboutit
toujours à un effet observable.

**Gestion des types d'évolution**

L'évolution est modélisée par un enum `EvolutionType` (POWER, CADENCE, SCOPE,
PROJECTILE) stocké dans un `HashSet` dans chaque `ProjectileTower`. Cette structure
garantit l'unicité et permet de tester en O(1) si une évolution est déjà appliquée.
Seules les `ProjectileTower` peuvent évoluer ; `IceTower` et `SlowdownTower` héritent
de `NonProjectileTower` et ne disposent pas de ce mécanisme.

### État du développement

| Fonctionnalité | État |
|---|---|
| Plateau aléatoire avec chemin depuis le bord gauche | Implémenté (LeftStartRandomBoard) |
| Plateau classique multi-chemins | Implémenté (ClassicalBoard) |
| Placement de 2 tours de chaque type via action | Implémenté (placeTowersViaActions) |
| Phase d'actions interactive (achat, évolution, vente) | Implémenté (playerActionPhase + 4 handlers) |
| Boucle de 10 manches avec nbBallons par manche | Implémenté (Livrable5) |
| Application automatique d'évolution (manches 1–5) via action | Implémenté (applyOneEvolutionViaAction) |
| Suppression automatique d'évolution (manches 6–10) via action | Implémenté (removeOneEvolutionViaAction) |
| Affichage du numéro de manche et état des évolutions | Implémenté (displayTowerEvolutions) |
| Affichage des événements (touché, détruit, arrêté, ralenti, sorti) | Implémenté (GameEngine) |
| La manche s'arrête quand tous les ballons ont fini leur parcours | Implémenté (condition GameEngine.game()) |
| Tests des méthodes publiques | 18 fichiers de tests JUnit |

## Livrable 6

Commandes de compilation et d'exécution :

    make classes

Pour générer les JARs exécutables :

    make jar

Pour exécuter les livrables finaux :

    - java -jar jar/towerdefense-a-interactive.jar <largeur> <hauteur>
    - java -jar jar/towerdefense-a-random.jar <largeur> <hauteur>
    - java -jar jar/towerdefense-b-interactive.jar <largeur> <hauteur> <nbChemins>
    - java -jar jar/towerdefense-b-random.jar <largeur> <hauteur> <nbChemins>

Pour générer la javadoc :

    make doc
    make docs

Pour compiler et lancer les tests :

    make tests
    make runtests

### Atteinte des objectifs

Amadou : 
    Implémentation de la boucle finale complète tant que le joueur est en vie (`while player.isAlife()`). Connexion de la phase d’actions entre les manches. Garantie de générer au minimum 15 ballons par manche avec une progression de la difficulté. Gestion des deux modes de plateau (a/b) dans une logique centrale et fourniture des 4 wrappers main (`TowerDefenseAInteractive`, etc.).

Yassin : 
    Rendre le `InteractiveListChooser` totalement robuste en gérant le rebouclage jusqu’à une entrée valide. Correction des tests cassés (`ChoiceTest`) et ajout de tests de non-régression sur la boucle finale. Vérification globale qu'aucune saisie invalide n'interrompt le jeu, comme exigé.

Serhii : 
    Production de la modélisation complète (`UML.png` à la racine, et `Livrable1.png` à `Livrable5.png` dans le dossier `uml/`). Mise à jour du Makefile pour supporter les cibles `doc` et `docs`, et générer les 4 JARs obligatoires dans le dossier spécifié `jar/`. Validation de l'exécution sur les machines M5.

Habiba : 
    Finalisation du `README.md` (section Livrable 6 et mode d'emploi des JARs). Vérification stricte de la structure du dépôt (absence de .class, présence des bons dossiers). Nettoyage final demandé (suppression des fichiers de configuration IDE et dossiers temporaires). Passage de la checklist de conformité avant le rendu final.


### Difficultés rencontrées

Amadou :
    Coordonner les deux modes de plateau (A et B) et les deux types d'interfaces (Interactive et Aléatoire) au sein d'une seule boucle de jeu sans créer de duplication de code massive. La création de 4 "wrappers" distincts a résolu ce problème.

Yassin :
    Gérer toutes les exceptions possibles lors de la saisie utilisateur (InputMismatchException, entrées vides) pour empêcher le jeu de planter, ce qui nécessitait de bien vider le buffer du scanner à chaque erreur.

Serhii :
    Adapter le Makefile pour répondre très précisément aux exigences de nommage du professeur, notamment la création automatique du répertoire `jar/` avant la génération des exécutables.

Habiba :
    S'assurer de la conformité stricte de l'arborescence (absence totale de fichiers `.class` cachés) et compiler le travail de tout le groupe dans le README en respectant la structure des livrables précédents.

### Choix de modélisation

- **Architecture des Wrappers :** Pour générer les 4 exécutables distincts demandés, nous avons créé 4 classes Main (`TowerDefenseAInteractive`, etc.). Ces classes se contentent de configurer le bon type de plateau et le bon `ListChooser`, puis délèguent l'exécution à un moteur central.
- **Boucle de jeu infinie :** Contrairement au Livrable 5 (limité à 10 manches), le jeu boucle désormais sur l'état de santé du joueur (`player.isAlife()`).
- **Difficulté adaptative :** Le nombre de ballons est instancié à un minimum de 15 par manche, avec une incrémentation à chaque tour pour augmenter la difficulté de survie.

### État du développement

| Fonctionnalité | État |
|---|---|
| Création des 4 archives JAR conformes | Implémenté (Makefile) |
| Boucle de jeu infinie (jusqu'à Game Over) | Implémenté |
| Augmentation de la difficulté et base de 15 ballons | Implémenté |
| Robustesse des saisies clavier | Implémenté (InteractiveListChooser) |
| Nettoyage du Git (.class exclus, UML présent) | Implémenté |


# Journal de bord

Le journal de bord doit être rempli à la fin de chaque séance encadrée, et **avant** de quitter la salle. 

Pour chaque semaine on y trouvera :
- ce qui a été réalisé, les difficultés rencontrées et comment elles ont été surmontées (on attend du contenu, pas uniquement une phrase du type "tous les objectifs ont été atteints")
- la liste des objectifs à réaliser d'ici à la prochaine séance encadrée

## Semaine 1

On a commencer l'uml avec les diffèrentes classes, pas encore finit à 100%. 
Difficultées: Compléter la classe plateau et la création de la classe plateau en java avec les tests. Aussi le calcul du chemin.
### Ce qui a été réalisé
On a complété l'UML et commencé le code des classe Board et RandomBoard, Tower Cell
### Difficultés rencontrées
Le calcul du chemin pour le Plateau Random
### Objectifs pour la semaine et répartition du travail par membre
Faire le chemin des deux plateaux 
## Semaine 2
Semaine 2
Ce qui a été réalisé

    Finalisation du Livrable 1 : Nous avons terminé la modélisation des deux types de plateaux de jeu prévus.

    Algorithmes de génération de chemin :

        Implémentation réussie de ClassicalBoard avec des chemins linéaires traversant la grille.

        Développement de l'algorithme de génération aléatoire dans RandomBoard.

    Validation par tests : Mise en place de tests unitaires JUnit pour vérifier la validité des sorties, l'absence de boucles (isDoingCircle) et le respect des dimensions de la grille (6x11).

    Mise à jour de l'UML : Intégration des classes de base pour les ballons et les tours afin de préparer la suite.

Difficultés rencontrées

    Gestion des impasses : Dans RandomBoard, l'algorithme de "marche aléatoire" se retrouvait souvent bloqué dans des culs-de-sac avant d'atteindre la longueur minimale de 12 cases.

    Conditions de sortie : Il était complexe de s'assurer mathématiquement que le chemin ne ressorte pas par le même côté que l'entrée.

Solutions apportées

    Réinitialisation dynamique : Nous avons implémenté une méthode qui vide le chemin et recommence la génération depuis le point de départ si aucun mouvement valide n'est possible (système de backtracking simplifié).

    Filtres de validation : Ajout de la méthode isSameSide pour rejeter automatiquement les chemins invalides lors de la vérification finale.

### Objectifs pour la semaine prochaine (Livrable 2) et répartition

L'objectif est d'attaquer la gestion des ballons et du temps.

Membre	Tâche spécifique

Habiba	

    Création de la hiérarchie des classes pour les 3 types de ballons (niveaux 1, 2 et 4).
	
Yassin	

    Développement de la logique de progression infra-case (mouvement fluide).
	
Amadou	

    Mise en place du moteur de "tics" d'horloge et de la boucle de manche.
	
Ivkin	

    Implémentation de la classe Player (2500 crédits, 20 vies) et gestion des gains.


## Semaine 3

Ce qui a été réalisé

    Finalisation du moteur de jeu (GameEngine) : Mise en place de la boucle de jeu principale gérant le temps par "tics" et la gestion des vagues de ballons.

    Système de progression : Implémentation du mouvement "infra-case" permettant aux ballons de se déplacer avec fluidité sur le chemin grâce à des coordonnées réelles (x,y).

    Gestion des entités : Création de la hiérarchie des ballons avec gestion automatique des niveaux de santé et de la vitesse.

    Interface Joueur et Stats : Développement de la classe Player et du Journal pour le suivi des crédits, des vies et des statistiques de fin de manche.

### Difficultés rencontrées

    Synchronisation du chemin : Problème d'affichage où le chemin généré par RandomBoard ne s'affichait pas avec les symboles 'X' sur la grille car la méthode applyPathToGrid n'était pas appelée au bon moment.

    Mouvement et Cellules : Difficulté à faire correspondre les coordonnées double des ballons avec les index int de la grille pour mettre à jour la présence des ballons dans chaque Cell.

### Objectifs pour la semaine et répartition du travail par membre

Terminer les tests du Livrable 2 et préparer l'architecture des tours.

    Habiba : Création des classes de base pour les tours et les projectiles.

    Yassin : Optimisation du calcul de distance pour le futur radar des tours.

    Amadou : Tests unitaires du moteur de jeu et affichage du bilan final.

    Ivkin : Gestion des achats de tours dans la classe Player.

## Semaine 4

Ce qui a été réalisé

    Affichage dynamique : Amélioration de la méthode display() pour s'adapter à toutes les tailles de plateaux avec des indices de colonnes et de lignes clairs.

    Architecture du Combat : Mise en place de l'héritage pour les tours (ProjectileTower, NonProjectileTower) et les différents types de projectiles (Dart, Bomb, etc.).

    Validation du Livrable 2 : Passage réussi des tests de simulation où les ballons traversent le plateau et impactent les points de vie du joueur.

### Difficultés rencontrées

    Mémorisation du chemin : Le chemin changeait à chaque appel de la méthode path(), empêchant les ballons de suivre les 'X' affichés. Nous avons dû implémenter une mémorisation du chemin dans RandomBoard.

    Dimensions fixes : Le constructeur de Board forçait une taille de 6x11, ce qui créait des erreurs lors des tests sur des plateaux plus petits.

### Objectifs pour la semaine et répartition du travail par membre

    L'objectif est de rendre les tours opérationnelles (Livrable 3).
    Membre	Tâche spécifique

    Amadou	Intégrer la phase d'attaque dans la boucle du GameEngine selon la cadence des tours.

    Yassin	Développer l'algorithme de détection de cible (distance entre tour et ballon).

    Ivkin	Sécuriser le placement des tours sur la grille (interdiction de poser sur le chemin).

    Habiba	Initialiser les statistiques réelles (portée, coût, dégâts) dans les constructeurs des tours.

## Semaine 5

### Ce qui a été réalisé

    Yassin : Finalisation de la méthode TargetBalloon pour viser le premier ballon de la file
    tant qu'il est dans le scope de la tour. Intégration de la classe TargetingBalloon dans
    la classe Board pour plus de clarté et de compréhension.

    Amadou : Intégration de la phase de tir des tours dans la boucle principale du GameEngine.
    Mise en place de la logique de cadence (une tour tire uniquement quand son compteur de tics
    atteint sa cadence). Implémentation de l'attaque des ProjectileTower (méthode shot()) et
    des effets de zone des NonProjectileTower (méthode freeze()). Ajout de l'affichage du
    bilan final de la manche (ballons détruits, vies restantes, crédits).

    Habiba : Initialisation des statistiques réelles (portée, coût, dégâts) dans les
    constructeurs des tours. Chaque type de tour possède désormais ses propres valeurs
    de départ conformes au sujet (ex. DartMonkey, BombTower, IceTower).

    Ivkin : Sécurisation du placement des tours sur la grille : interdiction de poser une
    tour sur une case appartenant au chemin. Vérification dans la méthode buyTower() de Player.

### Difficultés rencontrées

    Yassin : Calcul de distance entre le ballon et la tour. Viser le ballon uniquement
    tant qu'il est dans le scope de la tour et changer de cible une fois le ballon sorti.

    Amadou : Synchronisation entre la phase de mouvement des ballons et la phase de tir
    des tours pour éviter de tirer sur un ballon déjà supprimé de la liste active.

### Objectifs pour la semaine et répartition du travail par membre

    Clôturer la méthode TargetBalloon et effectuer les tests de la méthode.
    Finaliser l'intégration des tours dans le GameEngine et corriger les conflits Git.

## Semaine 6

### Ce qui a été réalisé

    Yassin : Clôture et correction de la méthode TargetBalloon (TargetingBalloon.java).
    Finalisation de l'algorithme de calcul de distance entre tour et ballon. La tour cible
    désormais le ballon le plus avancé dans le chemin à portée, et change de cible dès
    que le ballon sort de sa portée.

    Amadou : Intégration et débogage de la phase d'attaque des tours dans la boucle du
    GameEngine. Les tirs sont déclenchés selon la cadence de chaque tour (ProjectileTower)
    et les effets de zone (IceTower, SlowdownTower) sont appliqués à tous les ballons à portée.
    Correction des conflits Git dans Board.java et des méthodes manquantes (getBallons(),
    getTowers()).

    Habiba : Correction de la classe Balloon : ajout de baseSpeed pour restaurer la vitesse
    après un ralentissement. Correction de takeDamage() pour que le niveau suive la santé
    lors des mutations de ballons.

    Ivkin : Correction et fiabilisation du placement des tours sur la grille (interdiction
    de poser sur le chemin). Correction de ClassicalBoard qui confondait height et width.

### Difficultés rencontrées

    Intégration des tours : Il était difficile de synchroniser les tirs des tours avec
    la boucle de jeu basée sur les tics, notamment pour éviter qu'une tour tire plusieurs
    fois sur le même ballon dans le même tic.

    Conflits Git : La fusion des branches de chaque membre a généré des conflits dans
    Board.java qui ont nécessité une résolution manuelle.

### Objectifs pour la semaine et répartition du travail par membre

    Finaliser et tester le Livrable 3 (Livrable3a et Livrable3b opérationnels).

    Amadou : Réécriture du GameEngine avec logs horodatés [t=N] et création du Makefile.
    Yassin : Implémentation de Livrable3a avec LeftStartRandomBoard.
    Serhii : Implémentation de Livrable3b avec N chemins rectilignes.
    Habiba : Correction de Journal.recordHealthLost() et ajout des méthodes isFrozen()/isSlowed().

## Semaine 7

### Ce qui a été réalisé
le livrable 3 a été fait et corrigé 
### Difficultés rencontrées

### Objectifs pour la semaine et répartition du travail par membre
Amadou —
    Livrable4.java : plateau, placement de tours, achat d'évolutions, lancement du moteur 
    Makefile : ajouter livrable4.jar 

Yassin 

    TowerTest.java: 6 nouveaux tests JUnit (testEvolutionPower, testEvolutionCadence, testEvolutionProjectile, testEvolutionUnique, testCannotUpgradeIceTower, testCannotUpgradeSlowdownTower) 
    Vérifier que BombTower
    PROJECTILE (Bomb→ExtraBomb) fonctionne bien

Serhii 

    PlayerTest.java
    : 3 tests (testBuyUpgrade, testCanUpgrade, testCannotUpgradeIceTower) 
    Journal
    : ajouter comptage par type d'évolution (POWER/CADENCE/SCOPE/PROJECTILE) 

Habiba — 

    Javadoc complète pour Evolution, Player.buyUpgrade(), Player.canUpgrade(), ProjectileTower.getEvolution() 
    Mise à jour du diagramme UML (
    uml/diagramme.puml
    ) : ajouter
    canUpgrade(), nouvelle signature buyUpgrade()
 
    Relecture et validation que les tests de Yassin couvrent bien les cas limites définis dans le sujet 
## Semaine 8

### Ce qui a été réalisé

  Yassin : 
  
    Réalisations de six nouveaux test en lien avec les différentes classes que j'ai réalisées (testEvolutionPower, testEvolutionCadence, testEvolutionProjectile, testEvolutionUnique, testCannotUpgradeIceTower, testCannotUpgradeSlowdownTower).

    Vérifications de BombTower/PROJECTILE (Bomb→ExtraBomb) fonctionne bien.

Amadou :

    Implémentation du Livrable 4 (partie commune + deux scénarios).
    Création de la classe parent Livrable4.java regroupant les méthodes utilitaires communes :
    placeTowers() qui achète et place 2 tours de chaque type via player.buyTower() en
    déduisant les crédits, buyEvolutions() qui applique des évolutions POWER et CADENCE
    sur toutes les ProjectileTower avec gestion de l'exception TypeTowerException pour les
    tours non éligibles (IceTower, SlowdownTower), et buildTower() comme fabrique de tours.
    Création de Livrable4a.java (plateau aléatoire LeftStartRandomBoard) et Livrable4b.java
    (plateau classique ClassicalBoard), chacun héritant de Livrable4 et définissant leur
    propre main() comme precisé par la prof.
    Mise à jour du Makefile pour générer livrable4a.jar et livrable4b.jar.
    Ajout de deux tests JUnit dans GameEngineTest.java : testPlayerLosesLifeWhenBalloonEscapes
    et testPlayerCreditsPositiveAfterGame.

Serhii :
    - Des tests de Player pour acheter l'evolution et verification d'erreur a cause de evoluer NonProjectileTower
    - Fin de fonction buyEvolution de la classe Player et creation l'exeption TypeTowerException
    - L'adition de comptage des evolution par type dans Journal avec deux fonctions recordNbTypeEvolution et getNbTypeEvolution
    - Verification des nouveaux fonctions avec des tests dans JournalTest

Habiba:
Rédaction de la Javadoc complète pour les nouvelles méthodes de la classe Player (buyUpgrade, canUpgrade) et de ProjectileTower.

Définition des règles de gestion des évolutions (unicité via le HashSet) pour guider l'implémentation d'Amadou et les tests de Yassin.

### Difficultés rencontrées

    Amadou : La méthode buyEvolutions() devait gérer correctement le cas des tours non
    éligibles (IceTower, SlowdownTower) sans interrompre le programme ; l'utilisation
    du try/catch sur TypeTowerException à l'intérieur de la boucle m'a permis de
    continuer l'achat sur les autres tours.

    Yassin :
    Quelques difficultées rencontrés lors des test par exemple le lien  entre toutes les classes, vérification que le solde du joueur ne bouge pas lors d'une amélioration ou évolution interdite...

    Difficultées aussi pour le TestCannotUpgradeIceTower car comparer aux autres test je devais vérifier que l'action n'a pas lieu, je devais comparer l'état du joueur avant et après l'appel de la méthode.

    Habiba : 
    Représenter proprement la relation d'héritage entre les tours à projectiles et les tours à effets (IceTower) pour que la Javadoc reflète bien que seules les premières peuvent évoluer.

### Objectifs pour la semaine et répartition du travail par membre

## Semaine 9

### Ce qui a été réalisé


    Yassin :

    Finalisation des différents test realisé les semaines précédentes.

    Amadou :

    Implémentation du Livrable 4 avec une factorisation du code commun dans
    Livrable4.java. Ajout de la méthode placeTowers() pour acheter et placer
    automatiquement des tours via le joueur, de la méthode buyEvolutions() pour
    appliquer des évolutions POWER et CADENCE sur les tours éligibles, et de la
    méthode buildTower() pour centraliser la création des différentes tours.

    Création des deux scénarios Livrable4a.java et Livrable4b.java à partir de
    la classe parent Livrable4, avec génération du plateau, achat des tours,
    achat des évolutions, création des ballons puis lancement de la manche.

    Mise à jour du Makefile pour générer les exécutables livrable4a.jar et
    livrable4b.jar.

    Serhii :

    Finalisation de la classe Journal.
    Des test pour Player et Journal

    ProjectileTower:
        removeEvolution() une methode abstraite qui permet d'enlever l'evolution de tour choisi
        
        getEvoAplied() retourne HashSet des evolutions acquises sur cette tour

    Habiba: 

    Relecture et vérification des tests JUnit de Yassin. S'assurer que les cas limites (solde insuffisant, double évolution) sont bien couverts pour garantir la robustesse du système d'évolution.
    Révision de la méthode takeDamage dans la classe Balloon pour s'assurer que la vitesse se met à jour correctement lors de la mutation entre les niveaux 1 à 4.
   Structuration de la section "Choix de modélisation" pour expliquer l'usage du HashSet dans la gestion des types d'évolutions.

### Difficultés rencontrées

    Amadou :

    La principale difficulté a été de garder une logique commune entre les deux
    scénarios du Livrable 4 sans dupliquer le code. Il fallait aussi gérer
    proprement le cas des tours non éligibles aux évolutions, en particulier
    IceTower et SlowdownTower, sans interrompre l'exécution du programme.

    Il a également fallu conserver le même joueur entre les phases d'achat et
    de simulation afin de garder des crédits cohérents et un journal de partie
    correct jusqu'à la fin de la manche.

    Habiba: 
    S'assurer que les modifications de vitesse des ballons n'entraînent pas de bugs de positionnement sur le plateau lors des changements de manche.


### Objectifs pour la semaine et répartition du travail par membre

    Yassin : 

    Terminer les tests et finaliser mes différentes classes

    Amadou :

    Finaliser et stabiliser le Livrable 4 en préparant une version propre des
    deux scénarios, avec placement automatique des tours, gestion des évolutions
    et exécution complète d'une partie sur les deux types de plateaux.

## Semaine 10

### Ce qui a été réalisé

    Amadou :

    Implémentation complète de la gestion des actions du joueur (Livrable 5).
    Création de la classe Livrable5.java contenant la boucle d'actions via
    ListChooser : le joueur peut acheter et placer des tours, évoluer des tours,
    vendre des tours, ou vendre des évolutions, tant que ses crédits sont positifs.

    Création de trois classes utilitaires dans game.choice :
    - PlayerAction (enum) pour les 5 actions du joueur
    - TowerChoice (wrapper) pour l'affichage des tours dans le ListChooser
    - EvolutionChoice (wrapper) pour l'affichage des évolutions

    Création de Livrable5.java (programme unique avec RandomListChooser pour
    le mode automatique).

    Mise à jour du Makefile pour générer livrable5.jar.
    Rédaction de la section Livrable 5 dans le README.

    Serhii :

    Un système de choix a été mis en place dans le package `game.choice`.

    Une classe abstraite `Choice<T>` permet de représenter une option de choix pour le joueur.  
    Cette classe est générique afin de pouvoir être utilisée pour différents types de choix (actions, évolutions, etc.).

    Chaque classe concrète doit étendre `Choice` et implémenter la méthode `getChoice()`.


    Des methodes displayChoice() et getChoice()

    displayChoice() permet d'afficher dans une console le methode qui nous avons choisi

    getChoide() Retourne l'objet correspondant au choix (par exemple une évolution, une action, etc.), afin de l'utiliser dans le jeu.

    Player.java :
        sellEvolution - permet de vendre l'evolution, autrement dit l'enlever et retourner des credits depenser
        On verifie que la tour est de type projectileTower
        On verifie que la tour a cette amelioration

Habiba : 

    - Mise à jour du diagramme UML pour inclure les nouveaux menus.
    - Rédaction de la Javadoc pour les nouvelles classes afin d'expliquer le mode interactif.


### Difficultés rencontrées

    Amadou :

    Gestion des types génériques avec ListChooser<T> : la phase d'actions
    nécessite de choisir des objets de types différents (PlayerAction,
    TowerChoice, Position, EvolutionChoice). Utilisation du type brut avec
    @SuppressWarnings pour contourner cette limitation.

    Accès au champ protected evolutions de ProjectileTower depuis un autre
    package : résolu en itérant sur les EvolutionType avec hasEvolution()
    plutôt qu'en accédant directement au HashSet.
    
    Serhii :
    Trouver l'option de retourne pour des chois de actionchoice et towerchoice

### Objectifs pour la semaine et répartition du travail par membre

    Amadou :

    Tester et stabiliser le Livrable 5, vérifier que livrable5.jar
    fonctionne correctement.

    Yassin :

    Création des classes de choix (PlayerAction, TowerChoice, EvolutionChoice)
    et des tests JUnit pour le Livrable 5 (Livrable5Test.java).

    Serhii :

    Modifications de Player.java (ajout de sellEvolution), correction de
    removeEvolution() dans ProjectileTower, et mise à jour du Journal.

    Habiba :

    Mise à jour du diagramme UML, rédaction de la section Livrable 5 du README
    pour les autres membres, et Javadoc des nouvelles classes.

## Semaine 11

### Ce qui a été réalisé

    Amadou :

    Intégration et correction de compatibilité du Livrable 5. La méthode
    handleSellEvolution() a été mise à jour pour utiliser Player.sellEvolution()
    implémenté par Serhii, au lieu du code manuel (removeEvolution + addCredits)
    qui ne compilait plus depuis que removeEvolution() prend un objet Evolution
    (et non un EvolutionType).

    Vérification complète de la chaîne de compilation et d'exécution :
    - make classes : compilation de 61 fichiers Java sans erreur
    - make jar : génération des 5 JARs (3a, 3b, 4a, 4b, 5)
    - java -jar livrable5.jar 8 12 5 : exécution OK

    Mise à jour du README : section Livrable 5 complétée avec les contributions
    de tous les membres (Serhii, Yassin) et journal de bord semaine 11.


    Habiba :
    - Finalisation du diagramme UML avec les derniers changements sur les signatures des méthodes.
    - Rédaction de la partie "Choix de modélisation" pour le Livrable 5 dans le README.


    Serhii :
    - correction d'UML 4 et creation UML 5. 
    - Creation des exceptions NegativeValueException, TooLongPathException et ZeroValueException et les integration dans une classe Board et Livrable


### Difficultés rencontrées

    Amadou :

    Changement de signature de removeEvolution() entre les semaines : la méthode
    prenait auparavant un EvolutionType mais prend désormais un Evolution complet.
    Résolu en créant un objet Evolution(cost, type) et en déléguant à
    player.sellEvolution().

### Objectifs pour la semaine et répartition du travail par membre

    Amadou :

    Préparer le Livrable 6 (boucle de jeu complète avec manches successives).

    Yassin :

    Compléter les tests JUnit pour le Livrable 5 (couvrir achat, vente,
    évolution, vente d'évolution, fin de tour).

    Habiba :

    Mise à jour du diagramme UML et Javadoc des nouvelles classes du Livrable 5.

## Semaine 12

### Ce qui a été réalisé

Amadou :

    Finalisation du Livrable 5 : implémentation de la boucle de 10 manches dans
    Livrable5.java avec les évolutions automatiques via le mécanisme d'action.

    Ajout de 4 nouvelles méthodes statiques dans Livrable5.java :
    - placeTowersViaActions() : place exactement 2 tours de chaque type en utilisant
      chooser.choose() pour sélectionner la position via le mécanisme d'action.
      Le joueur est initialisé avec 15 000 crédits (coût total ≈ 9 300).
    - applyOneEvolutionViaAction() : applique une évolution à une tour choisie au
      hasard parmi celles qui peuvent encore évoluer (filtrage : évolution disponible
      ET crédits suffisants). Appelle player.buyEvolution() via l'action.
    - removeOneEvolutionViaAction() : retire une évolution d'une tour choisie au
      hasard parmi celles qui en possèdent au moins une. Appelle player.sellEvolution()
      via l'action.
    - displayTowerEvolutions() : affiche l'état des évolutions de toutes les tours
      du plateau (type d'évolution et position) à chaque début de manche.

    Mise à jour du README : section "Choix de modélisation", "État du développement",
    intégration du diagramme UML Livrable 5, et complétion du journal de bord
    semaine 12.

    Vérification complète :
    - make classes : compilation sans erreur
    - make jar : génération des 5 JARs (3a, 3b, 4a, 4b, 5)
    - java -jar livrable5.jar 8 12 5 : 10 manches exécutées, évolutions appliquées
      manches 1–5, évolutions retirées manches 6–10

Habiba :
    - Relecture globale du projet pour s'assurer que toutes les consignes du Livrable 5 sont respectées.
    - Vérification de la documentation technique et test de la commande `make docs` pour s'assurer qu'il n'y a pas d'erreurs.

### Difficultés rencontrées

    Amadou :

    La principale difficulté a été de s'assurer que le filtrage des tours éligibles
    dans applyOneEvolutionViaAction() soit correct : une tour peut être une
    ProjectileTower sans avoir d'évolution disponible abordable. Le filtrage en deux
    étapes (vérifier hasEvolution() ET crédits suffisants) résout ce problème.

    La méthode displayTowerEvolutions() devait accéder à getPosition() de Tower.
    Ce getter existe bien dans la hiérarchie Tower, ce qui a permis d'afficher
    proprement les coordonnées de chaque tour.

### Objectifs pour finaliser le projet et répartition du travail par membre

    Amadou : Finalisation et soumission du Livrable 5 — vérification finale de
    la compilation, des tests et de la documentation.
