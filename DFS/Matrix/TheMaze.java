public class TheMaze {
  public boolean hasPath(int[][] maze, int[] start, int[] destination) {
    if(maze == null || start == null || destination == null) return false;
    int m = maze.length;
    int n = maze[0].length;
    boolean[][] visited = new boolean[m][n];

    return hasPath_dfs(maze, visited, destination, start[0], start[1]);
  }

  private boolean hasPath_dfs(int[][] maze, boolean[][] visited, int[] destination,
      int i, int j) {
    if(i < 0 || i >= maze.length || j < 0 || j >= maze[0].length || visited[i][j]) return false;
    if(i == destination[0] && j == destination[1]) return true;

    visited[i][j] = true;
    int[][] dirs =  {{1,0}, {-1,0}, {0,1}, {0,-1}};

    for (int[] dir: dirs) {
      int x = i;
      int y = j;

      while(x + dir[0] >= 0 && x + dir[0] < maze.length && y + dir[1] >= 0 && y + dir[1] < maze[0].length
      && maze[x + dir[0]][y + dir[1]] == 0) {
        x += dir[0];
        y += dir[1];
      }
      // Recurse from the last valid position
      if (hasPath_dfs(maze, visited, destination, x, y)) {
        return true;
      }
    }
    return false; // no path
  }

  public static void main(String[] args) {
    TheMaze theMaze = new TheMaze();
    int[][] maze = {
        {0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 1, 0},
        {1, 1, 0, 1, 1},
        {0, 0, 0, 0, 0}
    };
    int[] start = {0, 4};
    int[] destination = {4, 4};
    System.out.println(theMaze.hasPath(maze, start, destination));
  }
}
