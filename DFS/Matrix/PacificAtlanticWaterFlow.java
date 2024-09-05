import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacificAtlanticWaterFlow {
  public List<List<Integer>> pacificAtlantic(int[][] heights) {
    List<List<Integer>> ret = new ArrayList<>();
    if(heights == null || heights.length == 0) return ret;

    int m = heights.length;
    int n = heights[0].length;

    boolean[][] pacific = new boolean[m][n];
    boolean[][] atlantic = new boolean[m][n];

    for(int i = 0; i < m; i++) {
      waterflow_dfs(heights, pacific, i, 0, Integer.MIN_VALUE);
      waterflow_dfs(heights, atlantic, i, n - 1, Integer.MIN_VALUE);
    }

    for(int j = 0; j < n; j++) {
      waterflow_dfs(heights, pacific, 0, j, Integer.MIN_VALUE);
      waterflow_dfs(heights, atlantic, m - 1, j, Integer.MIN_VALUE);
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if(pacific[i][j] && atlantic[i][j]) {
          ret.add(Arrays.asList(i, j));
        }
      }
    }
    return ret;
  }

  private void waterflow_dfs(int[][] heights, boolean[][] visited, int i, int j, int pre) {
    if(i < 0 || i >= heights.length || j < 0 || j >= heights[0].length) return;
    if(pre > heights[i][j]) return;
    if(visited[i][j]) return;

    visited[i][j] = true;
    waterflow_dfs(heights, visited, i + 1, j, heights[i][j]);
    waterflow_dfs(heights, visited, i - 1, j, heights[i][j]);
    waterflow_dfs(heights, visited, i, j + 1, heights[i][j]);
    waterflow_dfs(heights, visited, i, j - 1, heights[i][j]);
  }

  public static void main(String[] args) {
    PacificAtlanticWaterFlow pacificAtlanticWaterFlow = new PacificAtlanticWaterFlow();
    int[][] heights = {
        {1, 2, 2, 3, 5},
        {3, 2, 3, 4, 4},
        {2, 4, 5, 3, 1},
        {6, 7, 1, 4, 5},
        {5, 1, 1, 2, 4}
    };
    System.out.println(pacificAtlanticWaterFlow.pacificAtlantic(heights));
  }
}
