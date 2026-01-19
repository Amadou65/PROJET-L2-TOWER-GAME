
/**
 * class that define the cell of the board
 */
public class Cell{
    //the state of the cell
    private String state;
    // The position of the cell
    private Position pos;
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

}