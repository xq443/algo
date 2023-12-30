package Matrix;

import java.util.LinkedList;
import java.util.Queue;
public class maxAreaofIsland {
    int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    boolean[][] visited;
    public int maxAreaOfIsland(int[][] grid) {
        this.visited = new boolean[grid.length][grid[0].length];
        int ret = 0;
        visited[0][0] = true;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1){
                    ret = Math.max(ret, bfs_maxofisland(grid, i, j));
                }
            }
        }
        return ret;
    }
    private int bfs_maxofisland(int[][] grid, int i, int j){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i,j});
        visited[i][j] = true;
        int cnt = 0;
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            cnt++;
            int x = curr[0], y = curr[1];
            for (int k = 0; k < 4; k++) {
                int new_x = x + dir[k][0];
                int new_y = y + dir[k][1];
                if(new_x >= 0 && new_x < grid.length && new_y >= 0 && new_y < grid[0].length
                    && !visited[new_x][new_y] &&  grid[new_x][new_y] == 1) {
                    visited[new_x][new_y] = true;
                    queue.offer(new int[]{new_x, new_y});
                }
            }
        }
        return cnt;
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

        maxAreaofIsland solution = new maxAreaofIsland();
        int result = solution.maxAreaOfIsland(grid);
        System.out.println("Output: " + result);
    }
}
