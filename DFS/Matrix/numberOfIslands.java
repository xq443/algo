import java.util.Objects;
public class numberOfIslands {
    public int numIslands(String[][] grid){
        int ret = 0;
        int row = grid.length;
        int col = grid[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(Objects.equals(grid[i][j], "1")){
                    ret++;
                    numIslands_dfs(i, j, grid);
                }
            }
        }
        return  ret;
    }
    public void numIslands_dfs(int x, int y, String[][] grid){
        if(x < 0 || x > grid.length || y < 0 || y > grid[0].length || grid[x][y] == "0") return;
        grid[x][y] = "0";
        numIslands_dfs(x+1, y, grid);
        numIslands_dfs(x-1, y, grid);
        numIslands_dfs(x, y+1, grid);
        numIslands_dfs(x, y-1, grid);
    }
    public static void main(String[] args) {
        numberOfIslands numberOfIslands = new numberOfIslands();
        String[][] grid = {
                        {"1","1","0","1","0"},
                        {"1","1","0","1","0"},
                        {"1","1","0","0","0"},
                        {"0","0","0","0","0"}
                    };
        System.out.println(numberOfIslands.numIslands(grid));
    }
}
