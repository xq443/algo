public class maxAreaofIsland_v2 {
    boolean[][] visited;
    public int maxAreaOfIsland(int[][] grid) {
        this.visited = new boolean[grid.length][grid[0].length];
        int ret = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 0) continue;
                int size = calculateMax(grid, i, j, 0);
                ret = Math.max(size, ret);
            }
        }
        return ret;
    }
    private int calculateMax(int[][] grid, int i, int j, int size){
        size++;
        visited[i][j] = true;
        if(i + 1 < grid.length && grid[i + 1][j] == 1 && !visited[i + 1][j]){
            size += calculateMax(grid, i + 1, j, 0);
            visited[i + 1][j] = true;
        }
        if(i - 1>= 0 && grid[i - 1][j] == 1 && !visited[i - 1][j]){
            size += calculateMax(grid, i - 1, j, 0);
            visited[i - 1][j] = true;
        }
        if(j - 1 >= 0 && grid[i][j - 1] == 1 && !visited[i][j - 1]){
            size += calculateMax(grid, i, j - 1, 0);
            visited[i][j - 1] = true;
        }
        if(j + 1 < grid[0].length && grid[i][j + 1] == 1 && !visited[i][j + 1]){
            size += calculateMax(grid, i, j + 1, 0);
            visited[i][j + 1] = true;
        }
        return size;
    }
    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };

        maxAreaofIsland_v2 solution = new maxAreaofIsland_v2();
        int result = solution.maxAreaOfIsland(grid);
        System.out.println("Output: " + result);
    }
}
