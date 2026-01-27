package game;
import java.util.*;
import java.util.ArrayList;
public class GameEngine {
    private ArrayList<Balloon> reserve;
    private ArrayList<Balloon> actif;
    public GameEngine(){
        this.reserve = new ArrayList<>();
        this.actif = new ArrayList<>();
    }
}
