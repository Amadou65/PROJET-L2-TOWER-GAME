package game;

import java.util.ArrayList;

/**
 * class that define the cell of the board
 */
public class Cell{

    private ArrayList<Balloon> list_ball;
    // The position of the cell
    private Position pos;
    private boolean isPath = false; 
    public Cell( Position pos){
        this.pos = pos;
        this.list_ball = new ArrayList<>();
    }
     /**
     * methode that return the position of the current cell
     * @return the postion
     */
    public Position getPosition(){
        return this.pos;
    }

    public void setAsPath(boolean path) {
    this.isPath = path;
    }

    public String getSymbol() {
    if (this.isPath) return "X"; // Symbole pour le chemin
        return " "; 
    }

    /**
     * methode that remove a ball
     * @param ball
     * 
     */
    public void removeBallon(Balloon ball){
        this.list_ball.remove(ball);
    }
    /**
     * method that put a balloon in a cell
     * @param ballon
     * 
     */
    public void putBallon(Balloon ball){
        this.list_ball.add(ball);
    }
}