package game;

import java.util.*;
import java.util.ArrayList;

import game.board.RandomBoard;

public class GameEngine {
    private ArrayList<Balloon> reserve;
    private ArrayList<Balloon> actif;
    private ArrayList<Position> path;
    private Board board;
    private Player player;

    public GameEngine(ArrayList<Balloon> reserve, ArrayList<Position> path, Board board) {
        this.reserve = reserve;
        this.actif = new ArrayList<>();
        this.path = path;
        this.board = board;
        this.player = new Player();
    }

    /**
     * methode that define the engine of the game
     */
    public void game() {
        int time = 0;
        while ((!reserve.isEmpty() || !actif.isEmpty()) && player.isAlife()) {
            time++;
            if (time % 20 == 0) {
                if (!reserve.isEmpty()) {
                    Balloon b = reserve.get(reserve.size() - 1);
                    this.actif.add(b);
                    reserve.remove(b);
                    Position pos_depart = this.path.get(0);
                    board.getCell((pos_depart)).putBallon(b);
                }
            }

                for (int i = actif.size() - 1; i >= 0; i--) {
                    Balloon b = actif.get(i);
                    int ancien = b.getPathIndex();
                    b.move();
                    int nouveau = b.getPathIndex();
                    Position pos_ancien = path.get(ancien);

                    if (b.isPopped()) {
                        actif.remove(b);
                        player.setCredits(player.getCredits() + 10);
                        board.getCell(pos_ancien).removeBallon(b);
                    }
                    else if (ancien != nouveau) {
                    
                        if (nouveau < path.size()) {
                            Position pos = path.get(nouveau);
                            board.getCell(pos_ancien).removeBallon(b);
                            board.getCell(new Position(pos.getX(), pos.getY())).putBallon(b);
                    } 
                    else{
                        actif.remove(b);
                        board.getCell(pos_ancien).removeBallon(b);
                        player.onHit();
                    }                      
                }
              
            }
        } // Fin du while
    } // Fin de la méthode
} // Fin de la classe