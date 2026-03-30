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
    
     * Constructeur pour un choix de TYPE de tour (lors de l'achat).
     * 
     * @param towerType le nom du type de tour
     * @param cost      le coût d'achat
     * @param scope     la portée
     * @param cadence   la cadence de tir
     */
    public TowerChoice(String towerType, int cost, int scope, int cadence) {
        this.towerType = towerType;
        this.cost = cost;
        this.scope = scope;
        this.cadence = cadence;
        this.tower = null;
        this.position = null;
    }
    

    /**
     * Constructeur pour un choix de TOUR EXISTANTE (lors de la vente/évolution).
     * 
     * @param tower la tour existante sur le plateau
     */
    public TowerChoice(Tower tower) {
        this.towerType = tower.getNom();
        this.cost = tower.getCost();
        this.scope = tower.getScope();
        this.cadence = tower.getCadence();
        this.tower = tower;
        this.position = tower.getPosition();
    }
    
    public String getTowerType() {
        return towerType;
    }

