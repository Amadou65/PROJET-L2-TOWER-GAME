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
      // Si la liste des ballons dans cette case n'est pas vide, on affiche B
      if (this.list_ball !=null && !this.list_ball.isEmpty()) {
        return "B";
      }
      // Si pas de ballon on regarde si ya tour.
      if (this.tower_list != null && !this.tower_list.isEmpty()) {
        return "T";
      }
      // sinon on affiche le chemain 
      return this.isPath ? "X" : " ";
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

  /**
   * methode that returns the list of balloons in this cell
   * @return ArrayList of balloons
   */
  public ArrayList<Balloon> getBallons(){
    return this.list_ball;
  }

  /**
   * methode that adds a balloon to this cell
   * @param ball the balloon to add
   */
  public void addBalloon(Balloon ball){
    this.list_ball.add(ball);
  }
}