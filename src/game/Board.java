package game;

import java.util.ArrayList;

public abstract class Board{
    // The cell of the Board
    // 
    protected int height;
    protected int width;
    protected ArrayList<Tower> tower_list;
    protected Cell[][] grid;


    public Board(){
        this.grid = new Cell[6][11];
        this.height = 6;
        this.width = 11;
        for (int i = 0; i < this.height; i ++){
            for (int j = 0; j < this.width; j ++){
                this.grid[i][j] = new Cell(new Position(i, j));
            }
            }
        this.tower_list = new ArrayList<>();
        
    }
    /**
     * methode that show the grid to the player
     * @return the grid
     */
    public String display(){
        String s = " 0 1 2 3 4 5 6 7 8 9 10 11\n" ;

        for (int i = 0; i < grid.length; i++){
            s += " +-+-+-+-+-+-+-+-+-+-+-+-+\n";
            s += i;
            for (int j = 0; j < grid[0].length; j++){
                s += "|" + " ";
            }
            s += "|\n";
        }
        s += " +-+-+-+-+-+-+-+-+-+-+-+-+";
        return s;
    }
    /**
     * methode that return the height of the grid
     * @return the height
     */
    public int getHeight(){
        return this.height;
    }
    /**
     * methode that return the width of the grid
     * @return the width
     */
    public int getWidth(){
        return this.width;
    }
    
    /**
     * methode that put a ballloon at a scepecific cell
     */
    public void putBallon(Balloon ball, Cell cell){};

    /**
     * methode that set all the cell in the with the positin as path 
    */  
   public void setCellAsPath(){};
   /**
    * method that give the cell at the position
    * @param pos
   */
  public Cell getCell(Position pos){
    return this.grid[pos.getY()][pos.getX()];
  } 
  
  /**
   * methods that add a tower to the board
   * @param Tower
   */
  public void addTower(Tower t, Cell cell){
    cell.addTower(t);
  }

  /**
   * methode that remove a tower in the board
   */
  public void removeTower(Tower t, Cell cell){
    cell.removeTower(t);
  }
}