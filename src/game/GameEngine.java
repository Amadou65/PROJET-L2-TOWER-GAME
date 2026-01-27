package game;
import java.util.*;
import java.util.ArrayList;

import game.board.RandomBoard;
public class GameEngine {
    private ArrayList<Balloon> reserve;
    private ArrayList<Balloon> actif;
    private ArrayList<Position> path;
    private Board board;
    public GameEngine(ArrayList<Balloon> reserve, ArrayList<Position> path, Board board){

        this.reserve = reserve;
        this.actif = new ArrayList<>();
        this.path = path;
        this.board = board;
    }
    /**
     * methode that define the engine of the game
    */
    public void game(){
        int time = 0;
        while(! reserve.isEmpty() || ! actif.isEmpty()){
            time ++;
            if (time % 20 == 0 && ! reserve.isEmpty()){
                Balloon b = reserve.get(reserve.size() - 1);
                this.actif.add(b);
                reserve.remove(b);
                Position pos_depart = this.path.get(0);
                board.getCell((pos_depart)).putBallon(b);

            }

        
        }
    }
}
