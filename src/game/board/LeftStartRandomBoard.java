package game.board;

import game.*;
import game.exeptions.NegativeValueException;
import game.exeptions.ZeroValueException;

import java.util.*;

/**
 * A RandomBoard variant where the path always starts from the left border
 * (column 0).
 * This is required by Livrable 3a specification.
 */
public class LeftStartRandomBoard extends RandomBoard {

    /**
     * Creates a board where generated paths always start from the left border.
     * 
     * @param height number of rows
     * @param width  number of columns
     */
    public LeftStartRandomBoard(int height, int width) throws ZeroValueException, NegativeValueException {
        super(height, width);
    }

    /**
     * Returns only the left-border cells as potential starting positions.
     * Overrides the full-perimeter method from RandomBoard.
     * 
     * @return list of positions on the left border (column 0)
     */
    @Override
    public ArrayList<Position> creerListeDepart() {
        ArrayList<Position> leftBorder = new ArrayList<>();
        for (int i = 0; i < this.getHeight(); i++) {
            leftBorder.add(new Position(i, 0));
        }
        return leftBorder;
    }
}
