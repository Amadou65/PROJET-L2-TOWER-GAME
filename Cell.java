public class Cell{
    private String state;
    
    public Cell(){
        this.state = "";
    }

    public boolean isEmpty(){
        return this.state == "";
    }

    public void putTower(Tower t){
        this.state = t.name;
    }

    public void putBalloon(Balloon b){
        this.state = b.name;
    }

    public void remove(){
        this.state = "";
    }
}