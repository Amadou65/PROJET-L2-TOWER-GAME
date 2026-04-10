package game;

import game.tower.ProjectileTower;
import game.exeptions.*;

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
        Cell targetCell = b.getCell(p);
        if (targetCell.getTowers().contains(t)) {
            this.credits += t.cost;
            b.removeTower(t, targetCell);
            journal.recordTowerSold(t.cost);
        }
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
     * Buys an evolution for a given tower if the player has enough credits and the tower does not already have that evolution
     *
     * @param t the tower to upgrade
     * @param e the evolution to apply
     */
    public void buyEvolution(Tower t, Evolution e) throws TypeTowerException {
        
        // Check if the tower is a ProjectileTower. Only ProjectileTowers can be upgraded
        if(t instanceof ProjectileTower) {

            // change the type of t to ProjectileTower to access getEvolution method
            ProjectileTower pt = (ProjectileTower) t;


            if (this.credits >= e.getCost()) {

                if(!pt.hasEvolution(e.getEvoType())) {
                    // deduct credits and apply evolution to the tower
                    this.credits -= e.getCost();
                    ((ProjectileTower) pt).getEvolution(e);

                    // add record in journal
                    journal.recordEvolutionApplied(e);

                    // show evolution applied and credits left in console
                    System.out.println("✨ Évolution " + e.getEvoType() + " appliquée à " + t.getNom()
                        + " | Crédits restants : " + this.credits);
                }
                else {
                    System.out.println("This evolution already done");
                }
            } else {
                System.out.println("Not enough credits to upgrade this upgrade.");
            }
        }
        else {
            throw new TypeTowerException("Only Projectile Towers can be upgraded with evolutions.");
        }
    }

    /**
     * Sells an evolution from the given tower.
     * Adds the cost back to the player's credits and removes the evolution.
     * 
     * @param t tower from which the evolution will be removed
     * @param e evolution that we want to remove
     * @throws TypeTowerException if the tower iss not a ProjectileTower
     * @throws NoEvolutionException if this tower don't have this evolution
     */
    public void sellEvolution(Tower t, Evolution e) throws TypeTowerException, NoEvolutionException {
        if(t instanceof ProjectileTower) {
            ProjectileTower pt = (ProjectileTower) t;
            if(pt.hasEvolution(e.getEvoType())){

                pt.removeEvolution(e);
                this.addCredits(e.getCost());
                journal.recordEvolutionSold(e.getCost());
            }
            else{
                throw new NoEvolutionException("This evolution is not contains in this tower");
            }
        }
        else{
            throw new TypeTowerException("Only Projectile Towers can be upgraded with evolutions.");
        }
    }
}
