package game;
/**
 * class that define the cell of the board
 */
public class Cell{
    //the state of the cell
    private String state;
    // The position of the cell
    private Position pos;
    private boolean isPath = false; 
    public Cell(String state, Position pos){
        this.state = state;
        this.pos = pos;
    }
    /**
     * methode that remove the things that is currently in the cell
     */
    public void remove(){
           this.state = "";
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

}