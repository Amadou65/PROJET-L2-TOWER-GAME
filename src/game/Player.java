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

    public void onHit(){
        this.health -= 1;
    }

    public void Buy(Tower t, Position p, Board b){
       /*
       if(this.credits >= t.cost){
            b.grid
        }
            TO DO */
    }
    /**
     * methode that  set the credits of the player
     */
    public void setCredits(int i){
        this.credits = i;
    }
}
