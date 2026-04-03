package game.board;

import java.util.*;
import game.Position;
import game.exeptions.NegativeValueException;
import game.exeptions.ZeroValueException;
import game.Board;

/**
 * A board with straight-line paths (horizontal or vertical).
 * The path() method generates a line crossing the whole board from one side to
 * the opposite.
 */
public class ClassicalBoard extends Board {
    private List<Position> generatedPath;

    public ClassicalBoard(int height, int width) throws ZeroValueException, NegativeValueException {
        super(height, width);
        this.generatedPath = null;
    }

    /**
     * Returns all boundary cells of the board (perimeter).
     * Convention: Position(row, col) where row ∈ [0, height-1], col ∈ [0, width-1].
     */
    public List<Position> getPointsBound() {
        List<Position> points = new ArrayList<>();
        // Bords haut et bas (toutes les colonnes)
        for (int col = 0; col < this.getWidth(); col++) {
            points.add(new Position(0, col));
            points.add(new Position(this.getHeight() - 1, col));
        }
        // Bords gauche et droite (lignes intérieures)
        for (int row = 1; row < this.getHeight() - 1; row++) {
            points.add(new Position(row, 0));
            points.add(new Position(row, this.getWidth() - 1));
        }
        return points;
    }

    /**
     * Generates a straight-line path crossing the whole board.
     * If start is on top/bottom border → vertical line.
     * If start is on left/right border → horizontal line.
     */
    public List<Position> path() {
        if (this.generatedPath != null) {
            return this.generatedPath;
        }

        List<Position> path = new ArrayList<>();

        List<Position> boundaryPoints = this.getPointsBound();
        Position start = boundaryPoints.get((int) (Math.random() * boundaryPoints.size()));

        if (start.getX() == 0) {
            // départ bord haut → chemin vertical descendant
            for (int row = 0; row < this.getHeight(); row++) {
                path.add(new Position(row, start.getY()));
            }
        } else if (start.getX() == this.getHeight() - 1) {
            // départ bord bas → chemin vertical montant
            for (int row = this.getHeight() - 1; row >= 0; row--) {
                path.add(new Position(row, start.getY()));
            }
        } else if (start.getY() == 0) {
            // départ bord gauche → chemin horizontal droite
            for (int col = 0; col < this.getWidth(); col++) {
                path.add(new Position(start.getX(), col));
            }
        } else {
            // départ bord droit → chemin horizontal gauche
            for (int col = this.getWidth() - 1; col >= 0; col--) {
                path.add(new Position(start.getX(), col));
            }
        }

        this.generatedPath = path;
        return this.generatedPath;
    }

    /**
     * Generates a new random straight-line path each time (no caching).
     * Useful for creating multiple distinct paths for mode b,
     * where each balloon follows its own trajectory.
     *
     * @return a new list of positions forming a rectilinear path
     */
    public List<Position> generateNewPath() {
        List<Position> newPath = new ArrayList<>();
        List<Position> boundaryPoints = this.getPointsBound();
        Position start = boundaryPoints.get((int) (Math.random() * boundaryPoints.size()));

        if (start.getX() == 0) {
            for (int row = 0; row < this.getHeight(); row++) {
                newPath.add(new Position(row, start.getY()));
            }
        } else if (start.getX() == this.getHeight() - 1) {
            for (int row = this.getHeight() - 1; row >= 0; row--) {
                newPath.add(new Position(row, start.getY()));
            }
        } else if (start.getY() == 0) {
            for (int col = 0; col < this.getWidth(); col++) {
                newPath.add(new Position(start.getX(), col));
            }
        } else {
            for (int col = this.getWidth() - 1; col >= 0; col--) {
                newPath.add(new Position(start.getX(), col));
            }
        }

        return newPath;
    }

    /**
     * Marks each position in the path as a path cell on the grid.
     */
    public void applyPathToGrid(List<Position> positions) {
        for (Position p : positions) {
            this.grid[p.getX()][p.getY()].setAsPath(true);
        }
    }
}
