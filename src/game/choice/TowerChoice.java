package game.choice;

import game.Tower;
import game.Position;

/**
 * Wrapper représentant un choix de tour pour le ListChooser.
 * Encapsule le type de tour et sa position, avec un affichage
 * lisible pour le joueur.
 */
public class TowerChoice {
    private String towerType;
    private int cost;
    private int scope;
    private int cadence;
    private Tower tower;     // null si c'est un choix de type (achat)
    private Position position; // null si c'est un choix de tour existante

    /**