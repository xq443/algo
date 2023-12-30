package Matrix;

import java.util.LinkedList;
import java.util.Queue;

public class shortestDistancefromAllBuildings {
    int[][] dir = {{1,0}, {-1,0}, {0,1},{0,-1}};
    int[][] dis;
    int[][] visited;
    int cnt;
    public int shortestDistance(int[][] grid) {
        this.cnt = 0;
        this.dis = new int[grid.length][grid[0].length];
        this.visited = new int[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1){
                    cnt++;
                    bfs_shortestDistancefromAllBuildings(grid, i, j);
                }
            }
        }
        int ret = Integer.MAX_VALUE;
        boolean found = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(visited[i][j] == cnt && grid[i][j] == 0){
                    ret = Math.min(ret, dis[i][j]);
                    found = true;
                }
            }
        }
        return found ? (ret == Integer.MAX_VALUE ? -1 : ret) : -1;
    }
    private void bfs_shortestDistancefromAllBuildings(int[][] grid, int i, int j){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        int distance = 1;

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int m = 0; m < size; m++) {
                int[] curr = queue.poll();
                for (int k = 0; k < 4; k++) {
                    assert curr != null;
                    int x = curr[0] + dir[k][0];
                    int y = curr[1] + dir[k][1];
                    if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length
                            && visited[x][y] == cnt - 1 && grid[x][y] == 0) {
                        queue.offer(new int[]{x, y});
                        dis[x][y] += distance;
                        visited[x][y] += 1;
                    }
                }
            }
            distance++;
        }
    }
    public static void main(String[] args) {
        int[][] grid = {{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        int[][] grid2 = {{1,2,0}};
        shortestDistancefromAllBuildings s = new shortestDistancefromAllBuildings();
        System.out.println(s.shortestDistance(grid));
        System.out.println(s.shortestDistance(grid2));
    }
}
//1 cnt == 0 visited[x][y] = -1
//2 cnt == -1 visited[x][y] = -2
//3 cnt == -2 visited[x][y] = -3
//cnt == -3 = visited[x][y]