public class MakingALargeIsland {

    /**
     * For each 1 in the grid, we paint all connected 1 with the next available color (2, 3, and so
     * on). We also remember the size of the island we just painted with that color.
     * <p>
     * Then, we analyze all 0 in the grid, and sum sizes of connected islands (based on the island
     * color). Note that the same island can connect to 0 more than once.
     *
     * @param grid
     * @return
     */
    public int largestIsland(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int n = grid.length;
        int m = grid[0].length;
        // int[] count is the count of the 4-directional cells
        int[] count = new int[n * m + 2];
        int id = 2; // we have modified grid[i][j] in dfs, so in case of collision of id, it starts with 2
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(grid[i][j] == 1) {
                    largestIsland_dfs(grid, count, i, j, id);
                    max = Math.max(max, count[id]);
                    id++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(grid[i][j] == 0) {
                    max = Math.max(max, largestIsland_sum(grid, count, i, j) + 1);
                }
            }
        }
        return max;
    }
    private void largestIsland_dfs(int[][] grid, int[] count, int i, int j, int id) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) return; // including visited status check
        grid[i][j] = id;
        count[id]++;

        largestIsland_dfs(grid, count, i + 1, j, id);
        largestIsland_dfs(grid, count, i - 1, j, id);
        largestIsland_dfs(grid, count, i, j + 1, id);
        largestIsland_dfs(grid, count, i, j - 1, id);
    }

    private int largestIsland_sum(int[][] grid, int[] count, int i, int j) {
        int[] dirs = new int[4]; // 4 directions
        if(i - 1 >= 0) {
            dirs[0] = grid[i - 1][j];
        }
        if(i + 1 < grid.length) {
            dirs[1] = grid[i + 1][j];
        }
        if(j - 1 >= 0) {
            dirs[2] = grid[i][j - 1];
        }
        if(j + 1 < grid[0].length) {
            dirs[3] = grid[i][j + 1];
        }

        int sum = count[dirs[0]];
        if(dirs[0] != dirs[1]) {
            sum += count[dirs[1]];
        }
        if(dirs[2] != dirs[0] && dirs[2] != dirs[1]){
            sum += count[dirs[2]];
        }
        if(dirs[3] != dirs[0] && dirs[3] != dirs[1] && dirs[3] != dirs[2]){
            sum += count[dirs[3]];
        }

        return sum;
    }

    public static void main(String[] args) {
        MakingALargeIsland makingALargeIsland = new MakingALargeIsland();
        int[][] grid1 = {{1,0},{0,1}};
        System.out.println(makingALargeIsland.largestIsland(grid1));
    }
}