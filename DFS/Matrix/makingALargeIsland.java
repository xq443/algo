public class makingALargeIsland {
    /**
     * For each 1 in the grid, we paint all connected 1 with the next available color (2, 3, and so on).
     * We also remember the size of the island we just painted with that color.
     *
     * Then, we analyze all 0 in the grid,
     * and sum sizes of connected islands (based on the island color).
     * Note that the same island can connect to 0 more than once.
     * @param grid
     * @return
     */
    public int largestIsland(int[][] grid) {
        int n = grid[0].length;
        int max = Integer.MIN_VALUE;
        int id = 2;
        int[] count = new int[n* n +2];
        for (int i = 0; i < n;i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 1){
                    largestIsland_dfs(grid, i, j, id, count);
                    max = Math.max(max, count[id]);
                    id++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 0){
                    max = Math.max(max, sumCount(grid, count, i, j) + 1);
                }
            }
        }
        return max;
    }
    // calculate the count of each separate part
    private void largestIsland_dfs(int[][] grid, int i, int j, int id, int [] count) {
        if(i < 0 || i >= grid.length || j <0 || j >= grid[0].length) return;
        if(grid[i][j] != 1) return;
        grid[i][j] = id;
        count[id]++;

        largestIsland_dfs(grid, i+1, j, id, count);
        largestIsland_dfs(grid, i-1, j, id, count);
        largestIsland_dfs(grid, i, j+1, id, count);
        largestIsland_dfs(grid, i, j-1, id, count);

    }
    //connect separate part with potential adjacent 0
    private int sumCount(int[][] grid, int [] count ,int i, int j){
        int [] ids = new int[4];
        if(i - 1>= 0){
            ids[0] = grid[i-1][j];
        }
        if(i + 1 < grid.length){
            ids[1] = grid[i+1][j];
        }
        if(j - 1>= 0){
            ids[2] = grid[i][j-1];
        }
        if(j + 1< grid.length){
            ids[3] = grid[i][j+1];
        }
        int sum = count[ids[0]];
        if(ids[0] != ids[1]){
            sum += count[ids[1]];
        }
        if(ids[2] != ids[0] && ids[2] != ids[1]){
            sum += count[ids[2]];
        }
        if(ids[3] != ids[0] && ids[3] != ids[1] && ids[3] != ids[2]){
            sum += count[ids[3]];
        }
        return sum;
    }

    public static void main(String[] args) {
        makingALargeIsland large = new makingALargeIsland();
        int[][] grid = {{1,1}, {1,1}};
        System.out.println(large.largestIsland(grid));

    }

}
