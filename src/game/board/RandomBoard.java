package game.board;

import game.Balloon;
import game.Board;
import game.Position;
import game.exeptions.NegativeValueException;
import game.exeptions.ZeroValueException;
import game.Cell;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class RandomBoard extends Board {
    private Random randomNumber = new Random();
    private ArrayList<Position> generatedPath = null; // Pour mémoriser le chemin

    public RandomBoard(int height, int width) throws ZeroValueException, NegativeValueException {
        super(height, width);
    }

    /**
     * method that gives a random path for the grid
     * 
     * @return the list of the path
     */
    @Override
    public ArrayList<Position> path() {
        // Si le chemin a déjà été généré, on renvoie le même
        if (this.generatedPath != null) {
            return this.generatedPath;
        }

        // Sinon, on procède à la génération
        ArrayList<Position> liste_depart = this.creerListeDepart();
        Position pos_depart = liste_depart.get(randomNumber.nextInt(liste_depart.size()));

        ArrayList<Position> path = new ArrayList<>();
        path.add(pos_depart);

        boolean found = false;
        while (!found) {
            Position current = path.get(path.size() - 1);

            if (path.size() >= 12 && isEdge(current) && !isSameSide(current, pos_depart)) {
                found = true;
            } else {
                ArrayList<Position> possible_path = this.nextPositions(current);
                ArrayList<Position> valid_choices = new ArrayList<>();

                for (Position p : possible_path) {
                    boolean alreadyVisited = false;
                    for (Position visited : path) {
                        if (p.equals(visited)) {
                            alreadyVisited = true;
                            break;
                        }
                    }
                    if (!alreadyVisited) {
                        valid_choices.add(p);
                    }
                }

                if (valid_choices.isEmpty()) {
                    path.clear();
                    pos_depart = liste_depart.get(randomNumber.nextInt(liste_depart.size()));
                    path.add(pos_depart);
                } else {
                    path.add(valid_choices.get(randomNumber.nextInt(valid_choices.size())));
                }
            }
        }

        this.generatedPath = path;
        return path;
    }

    /**
     * methode that apply the path to the grid visually
     */
    @Override
    public void applyPathToGrid(List<Position> positions) {

        for (Position p : positions) {
            // On marque la cellule dans la grille héritée de Board
            this.grid[p.getX()][p.getY()].setAsPath(true);
        }
    }

    /**
     * method that create the list of the starting positions
     */
    public ArrayList<Position> creerListeDepart() {
        ArrayList<Position> liste_depart = new ArrayList<>();
        int h = this.getHeight();
        int w = this.getWidth();

        for (int i = 0; i < h; i++) {
            liste_depart.add(new Position(i, 0));
            liste_depart.add(new Position(i, w - 1));
        }
        for (int j = 1; j < w - 1; j++) {
            liste_depart.add(new Position(0, j));
            liste_depart.add(new Position(h - 1, j));
        }
        return liste_depart;
    }

    public ArrayList<Position> nextPositions(Position pos) {
        ArrayList<Position> nextPoList = new ArrayList<>();
        int x = pos.getX();
        int y = pos.getY();

        // Ordre spécifique : bas, droite, haut, gauche (correspond aux tests attendus)
        int[][] directions = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        for (int[] d : directions) {
            int nx = x + d[0];
            int ny = y + d[1];
            if (nx >= 0 && nx < getHeight() && ny >= 0 && ny < getWidth()) {
                nextPoList.add(new Position(nx, ny));
            }
        }
        return nextPoList;
    }

    public boolean isDoingCircle(ArrayList<Position> path) {
        Position last = path.get(path.size() - 1);
        for (int i = 0; i < path.size() - 1; i++) {
            if (path.get(i).equals(last))
                return true;
        }
        return false;
    }

    public boolean isEdge(Position pos) {
        return pos.getX() == 0 || pos.getX() == this.getHeight() - 1 ||
                pos.getY() == 0 || pos.getY() == this.getWidth() - 1;
    }

    public boolean isSameSide(Position pos1, Position pos2) {
        if (pos1.getX() == 0 && pos2.getX() == 0)
            return true;
        if (pos1.getX() == getHeight() - 1 && pos2.getX() == getHeight() - 1)
            return true;
        if (pos1.getY() == 0 && pos2.getY() == 0)
            return true;
        if (pos1.getY() == getWidth() - 1 && pos2.getY() == getWidth() - 1)
            return true;
        return false;
    }

    @Override
    public void putBallon(Balloon ball, Cell cell) {
        if (!cell.isPath()) {
            cell.putBallon(ball);
        }
    }
}