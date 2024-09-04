
public class NumberOfIslands {
    public int numIslands(char[][] grid){
        int ret = 0;
        int row = grid.length;
        int col = grid[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(grid[i][j] != '1'){
                    ret++;
                    numIslands_dfs(grid, i, j);
                }
            }
        }
        return  ret;
    }
    public void numIslands_dfs(char[][] grid, int x, int y){
        if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != '1') return;

        grid[x][y] = '0';
        numIslands_dfs(grid, x+1, y);
        numIslands_dfs(grid, x-1, y);
        numIslands_dfs(grid, x, y+1);
        numIslands_dfs(grid, x, y-1);
    }
    public static void main(String[] args) {
        NumberOfIslands numberOfIslands = new NumberOfIslands();
        char[][] grid = {
                        {'1','1','0'},
                        {'1','0','1'}
                    };
        System.out.println(numberOfIslands.numIslands(grid));
    }
}
