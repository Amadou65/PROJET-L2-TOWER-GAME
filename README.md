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
## Livrable 3

### Atteinte des objectifs

### Difficultés restant à résoudre

## Livrable 4

### Atteinte des objectifs

### Difficultés restant à résoudre

## Livrable 5

### Atteinte des objectifs

### Difficultés restant à résoudre

## Livrable 6

### Atteinte des objectifs

### Difficultés restant à résoudre

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

Objectifs pour la semaine prochaine (Livrable 2) et répartition

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

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine et répartition du travail par membre

## Semaine 4

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine et répartition du travail par membre

## Semaine 5

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine et répartition du travail par membre

## Semaine 6

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine et répartition du travail par membre

## Semaine 7

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine et répartition du travail par membre

## Semaine 8

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine et répartition du travail par membre

## Semaine 9

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine et répartition du travail par membre

## Semaine 10

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine et répartition du travail par membre

## Semaine 11

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour la semaine et répartition du travail par membre

## Semaine 12

### Ce qui a été réalisé

### Difficultés rencontrées

### Objectifs pour finaliser le projet et répartition du travail par membre
