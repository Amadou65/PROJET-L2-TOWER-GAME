public class ClassicalBoard extends Board {
    public ClassicalBoard(int height, int width){
        super( height , width);
    }

    public boolean canPlaceTower( int x, int y){
        return x >= 0 && x < width && y >= 0 && y < height;
    }

}
