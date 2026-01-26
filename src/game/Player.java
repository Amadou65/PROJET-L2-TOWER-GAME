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
    
}
