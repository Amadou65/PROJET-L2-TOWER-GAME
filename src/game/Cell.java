package game;

import java.util.ArrayList;

/**
 * class that define the cell of the board
 */
public class Cell{

    private ArrayList<Balloon> list_ball;
    private ArrayList<Tower> tower_list;
    // The position of the cell
    private Position pos;
    private boolean isPath; 
    public Cell( Position pos){
        this.pos = pos;
        this.list_ball = new ArrayList<>();
        this.tower_list = new ArrayList<>();
        this.isPath = false;
    }
     /**
     * methode that return the position of the current cell
     * @return the postion
     */
    public Position getPosition(){
        return this.pos;
    }
    /**
     * methode that put a cell as a path
     * @param path
    */
    public void setAsPath(boolean path) {
      this.isPath = path;
    }

    /**
     * methode that say if a cell is a path
     * @return
    */
   public boolean isPath(){
    return isPath;
    
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

      /**
   * methods that add a tower to the board
   * @param Tower
   */
  public void addTower(Tower t){
    this.tower_list.add(t);
  }
    /**
   * methode that remove a tower in the cell
   */
  public void removeTower(Tower t){
    tower_list.remove(t);
  }
}