package game;

import game.tower.ProjectileTower;

public class Player {
    private int health; // number of health of Player
    private int credits; // number of Credits of Player
    private Journal journal; // Journal for statistics

    public Player() {
        this.health = 20;
        this.credits = 2500;
        this.journal = new Journal();
    }

    public Player(Journal journal) {
        this.health = 20;
        this.credits = 2500;
        this.journal = journal;
    }

    // geters
    public int getHealth() {
        return this.health;
    }

    public int getCredits() {
        return this.credits;
    }

    /**
     * Returns the Journal tracking statistics for this player.
     * 
     * @return the journal
     */
    public Journal getJournal() {
        return this.journal;
    }

    // seters
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /*
     * Predicate that return True if current Player have more than 0 point of life
     * 
     * @return boolean
     */
    public boolean isAlife() {
        return this.health > 0;
    }

    // func decrease health of player by 1
    public void onHit() {
        this.health -= 1;
        this.journal.recordHealthLost();
    }

    /**
     * metode that buy a tower
     * 
     * @param t is the tower to buy
     * @param p is the position to place the tower
     * @param b is board of the game
     */
    /**
     * Buys a tower and places it on the board at position p.
     * Refuses placement if the cell is on the path or lacks credits.
     * 
     * @param t the tower to buy
     * @param p the position where the tower is placed
     * @param b the game board
     */
    public void buyTower(Tower t, Position p, Board b) {
        Cell targetCell = b.getCell(p);

        // Safety check: cannot place on the path
        if (targetCell.isPath()) {
            System.out.println("Impossible : On ne peut pas construire sur le chemin !");
            return;
        }

        if (this.credits >= t.cost) {
            b.addTower(t, targetCell); // adds to both Cell and board.tower_list
            this.credits -= t.cost;
            // add record in journal
            journal.recordTowerPurchased(t.cost);
        } else {
            System.out.println("Not enough credits to buy this tower.");
        }
    }

    /**
     * metode that sell a tower
     * 
     * @param t is the tower to sell
     * @param p is the position of the tower
     * @param b is board of the game
     */
    public void sellTower(Tower t, Board b, Position p) {
        this.credits += t.cost;
        b.grid[p.getX()][p.getY()].removeTower(t);
    }

    /**
     * methode that add the credits to the player
     */
    public void addCredits(int i) {
        this.credits += i;
    }

    /**
     * Predicate: returns true if the tower can be upgraded with the given evolution
     * (sufficient credits and the tower is a ProjectileTower).
     *
     * @param t the tower to check
     * @param e the evolution to apply
     * @return true if the upgrade is possible
     */
    public boolean canUpgrade(Tower t, Evolution e) {
        return (t instanceof ProjectileTower) && (this.credits >= e.getCost());
    }

    /**
     * Buys an evolution for the given tower.
     * Deducts the cost from the player's credits and applies the evolution.
     * Only works for ProjectileTower (IceTower and SlowdownTower cannot evolve).
     *
     * @param t the tower to upgrade
     * @param e the evolution to apply
     */
    public void buyUpgrade(Tower t, Evolution e) {
        if (!canUpgrade(t, e)) {
            if (!(t instanceof ProjectileTower)) {
                System.out.println("Cette tour ne peut pas évoluer.");
            } else {
                System.out.println("Crédits insuffisants pour acheter cette évolution.");
            }
            return;
        }
    }
    /**
     * @deprecated Use buyUpgrade(Tower, Evolution) instead.
     *             Kept for backward compatibility.
     */
    @Deprecated
    public void buyUpgrade(Tower t, Board b, Position p, Evolution e) {
        buyUpgrade(t, e);
    }
}
