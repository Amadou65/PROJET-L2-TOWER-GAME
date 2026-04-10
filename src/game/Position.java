package game;
public class Position{
    private int x;
    private int y;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    // Display coordinates
    public String toString(){
        return "(" + this.x + ", " + this.y + ")";
    }

    // Getters
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    // Equality check
    public boolean equals(Object o){
        if (o instanceof Position){
            Position position = (Position) o;
            return this.x == position.x && this.y == position.y;
        }
        else{
            return false;
        }
    }

    @Override
    public int hashCode(){
        return 31 * this.x + this.y;
    }
}