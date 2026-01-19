
public class Position{
    private int x;
    private int y;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    public String display(){
        return "(" + this.x + ", " + this.y + ")";
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
}