import java.util.*;
public class ClassicalBoard extends Board {
    public ClassicalBoard(int height, int width){
        super( height , width);
    }

    public boolean canPlaceTower( int x, int y){
        return x >= 0 && x < this.getWidth() && y >= 0 && y < this.getHeight();
    }

    /* Returns the list of all boundary cells */
    public List<Point> getPointsBound(){
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < this.getWidth(); i++){
            points.add(new Point(i, 0));
            points.add(new Point(i, (this.getHeight() - 1)));
        }
        for (int j = 1; j < this.getHeight() - 1; j++){
            points.add(new Point(0, j));
            points.add(new Point((this.getWidth() - 1), j));
        }

        return points;
    }

    /* Returns a random path from a boundary point */
    public List<Point> randomPath(){
        List<Point> path = new ArrayList<>();
        List<Point> boundaryPoints = this.getPointsBound();
        Point start = boundaryPoints.get((int) (Math.random() * boundaryPoints.size()));

        if(start.getX() == 0){
            for(int i = 0; i < this.getWidth(); i++){
                path.add(new Point(i, start.getY()));
            }
        }
        else if(start.getX() == (this.getWidth() - 1)){
            for(int i = this.getWidth() - 1; i >= 0; i--){
                path.add(new Point(i, start.getY()));
            }
        }
        else if(start.getY() == 0){
            for(int j = 0; j < this.getHeight(); j++){
                path.add(new Point(start.getX(), j));
            }
        }
        else{
            for(int j = this.getHeight() - 1; j >= 0; j--){
                path.add(new Point(start.getX(), j));
            }
        }

        return path;
    }



}
