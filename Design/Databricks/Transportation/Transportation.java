package Databricks.Transportation;

import java.util.*;

public class Transportation {

  public int findBestTransportationMode(char[][] grid, int[] times, int[] costs) throws Exception {
    int rows = grid.length;
    int cols = grid[0].length;

    // Find 'S' and 'D' positions
    int startRow = -1, startCol = -1, endRow = -1, endCol = -1;
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        if (grid[r][c] == 'S') {
          startRow = r;
          startCol = c;
        } else if (grid[r][c] == 'D') {
          endRow = r;
          endCol = c;
        }
      }
    }

    // Exclude cases where the starting or ending point is not found
    if (startRow == -1 || startCol == -1 || endRow == -1 || endCol == -1) {
      throw new Exception("Starting point or destination not found");
      // return -1;
    }

    // Track the best time and cost
    int bestMode = -1;
    int bestTime = Integer.MAX_VALUE;
    int bestCost = Integer.MAX_VALUE;

    // Array for direction movements (left, up, right, down)
    int[] directions = {-1, 0, 1, 0, -1};

    // Try each mode independently and find the best mode
    for (int mode = 1; mode <= times.length; mode++) {
      Queue<int[]> queue = new LinkedList<>();
      queue.offer(new int[]{startRow, startCol, 0, 0}); // start from S with mode-specific time and cost
      boolean[][] visited = new boolean[rows][cols];
      visited[startRow][startCol] = true;

      while (!queue.isEmpty()) {
        int[] curr = queue.poll();
        int r = curr[0], c = curr[1], time = curr[2], cost = curr[3];

        // Reached the destination
        if (r == endRow && c == endCol) {
          if (time < bestTime || (time == bestTime && cost < bestCost)) {
            bestTime = time;
            bestCost = cost;
            bestMode = mode;
          }
          // break; // Exit the BFS as soon as we reach the destination for this mode
        }

        // Explore neighbors
        for (int d = 0; d < 4; d++) {
          int nr = r + directions[d];
          int nc = c + directions[d + 1];

          // Check if neighbor is within bounds, not a blocked cell, and matches the mode
          if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] != 'X' &&
              (grid[nr][nc] == 'S' || grid[nr][nc] == 'D' || Character.getNumericValue(grid[nr][nc]) == mode) &&
              !visited[nr][nc]) {  // convert a character (from the grid) into its numeric value.

            visited[nr][nc] = true;
            queue.offer(new int[]{nr, nc, time + times[mode - 1], cost + costs[mode - 1]});
          }
        }
      }
    }

    return bestMode;
  }

  public static void main(String[] args) throws Exception {
    Transportation transportation = new Transportation();
    try {

      char[][] grid1 = {
          {'3', '3', 'S', '2', 'X', 'X'},
          {'3', '1', '1', '2', 'X', '2'},
          {'3', '1', '1', '2', '2', '2'},
          {'3', '1', '1', '1', 'D', '3'},
          {'3', '3', '3', '3', '3', '4'},
          {'4', '4', '4', '4', '4', '4'}
      };
      int[] timeArr1 = {3, 2, 1, 1};
      int[] costArr1 = {0, 1, 3, 2};
      int result1 = transportation.findBestTransportationMode(grid1, timeArr1, costArr1);
      System.out.println("Best mode case 1: " + result1);

      char[][] grid3 = {
          {'S', '1'},
          {'2', 'D'}
      };
      int[] timeArr3 = {2, 2};
      int[] costArr3 = {5, 8};
      int result3 = transportation.findBestTransportationMode(grid3, timeArr3, costArr3);
      System.out.println("Best mode case 3 equal times but with different costs: " + result3);

      char[][] grid4 = {
          {'S', '2'},
          {'1', 'D'}
      };
      int[] timeArr4 = {2, 2};
      int[] costArr4 = {3, 3};
      int result4 = transportation.findBestTransportationMode(grid4, timeArr4, costArr4);
      System.out.println("Best mode case 4 equal times: " + result4);

      char[][] grid2 = {
          {'S', 'X', 'X', 'X', 'X'},
          {'X', '1', 'X', '1', 'X'},
          {'X', 'X', 'X', 'X', 'D'},
      };
      int[] timeArr2 = {1};
      int[] costArr2 = {0};
      int result2 = transportation.findBestTransportationMode(grid2, timeArr2, costArr2);
      System.out.println("Best mode case 2 no path exist: " + result2);


      char[][] grid5 = {
          {'X', 'X', 'X', 'X', 'X'},
          {'X', '1', 'X', '1', 'X'},
          {'X', 'X', 'X', 'X', 'D'},
      };
      int result5 = transportation.findBestTransportationMode(grid5, timeArr2, costArr2);
      System.out.println("Best mode case 5 no exist STARTING POINT: " + result5);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
// asymptotically O(mn)