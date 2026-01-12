public class Board{
    protected Cell cell;
    private int height;
    private int width;
    private String[][] grid;
    public Board(){
        this.grid = new String[6][11];
        for (int i = 0; i < grid.length; i ++){
            for (int j = 0; j< grid[0].length; j ++){
                this.grid[i][j] = cell;
            }
            this.height = 6;
            this.width = 11;
        }
    }
    public int getHeight(){
        return this.height;
    }
    public int getWidth(){
        return this.Width;
    }
 
}