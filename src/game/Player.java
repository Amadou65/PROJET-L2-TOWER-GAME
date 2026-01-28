package game;

public class Player {
    private int health; // number of health of Player
    private int credits; // number of Credits of Player

    public Player(){
        this.health = 20;
        this.credits = 2500;
    }

    // geters
    public int getHealth(){
        return this.health;
    }

    public int getCredits(){
        return this.credits;
    }

    /*
     * Predicate that return True if current Player have more than 0 point of life
     * @return boolean
     */
    public boolean isAlife(){
        return this.health > 0;
    }

    // func decrease health of player by 1
    public void onHit(){
        this.health -= 1;
    }

    /**
     * metode that buy a tower
     * 
     * @param t is the tower to buy
     * @param p is the position to place the tower
     * @param b is board of the game
     */
    public void buyTower(Tower t, Position p, Board b){
       
       if(this.credits >= t.cost){
            b.grid[p.getX()][p.getY()].addTower(t);
            this.credits -= t.cost;
        }
        else{
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
    public void sellTower(Tower t, Board b, Position p){
        this.credits += t.cost;
        b.grid[p.getX()][p.getY()].removeTower(t);
    }

    /**
     * methode that  set the credits of the player
     */
    public void setCredits(int i){
        this.credits = i;
    }
}
