package Databricks.Transportation;

import java.util.*;

public class MinCost {

  public static class Result {
    int mode;
    int cost;

    Result(int mode, int cost) {
      this.mode = mode;
      this.cost = cost;
    }
  }

  public List<Result> findBestTransportationModes(char[][] grid, int[] times, int[] costs) {

    List<Result> results = new ArrayList<>();

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

    if (startRow == -1 || startCol == -1 || endRow == -1 || endCol == -1) {
      System.out.println("Starting point or destination not found");
      return results;
    }

    // Array for direction movements (up, down, left, right)
    int[] directions = {-1, 0, 1, 0, -1};

    // Try each mode independently and find the best one
    for (int mode = 1; mode <= times.length; mode++) {
      Queue<int[]> queue = new LinkedList<>();
      queue.offer(new int[]{startRow, startCol, 0, 0}); // start from S with mode-specific time and cost
      boolean[][] visited = new boolean[rows][cols];
      visited[startRow][startCol] = true;

      int bestCost = Integer.MAX_VALUE;

      while (!queue.isEmpty()) {
        int[] curr = queue.poll();
        int r = curr[0], c = curr[1], time = curr[2], cost = curr[3];

        // Reached the destination
        if (r == endRow && c == endCol) {
          if (cost < bestCost) {
            bestCost = cost;
          }
          break; // Exit the BFS as soon as we reach the destination for this mode
        }

        // Explore neighbors
        for (int d = 0; d < 4; d++) {
          int nr = r + directions[d];
          int nc = c + directions[d + 1];

          // Check if neighbor is within bounds, not a blocked cell, and matches the mode
          if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] != 'X' &&
              (grid[nr][nc] == 'S' || grid[nr][nc] == 'D' || Character.getNumericValue(grid[nr][nc]) == mode) &&
              !visited[nr][nc]) {

            visited[nr][nc] = true;
            queue.offer(new int[]{nr, nc, time + times[mode - 1], cost + costs[mode - 1]});
          }
        }
      }

      if (bestCost != Integer.MAX_VALUE) {
        results.add(new Result(mode, bestCost));
      }
    }

    return results;
  }

  public static void main(String[] args) throws Exception {
    MinCost transportation = new MinCost();
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
      List<Result> results1 = transportation.findBestTransportationModes(grid1, timeArr1, costArr1);
      System.out.println("Case 1:");
      for (Result result : results1) {
        System.out.println("Mode: " + result.mode + ", Cost: " + result.cost);
      }

      char[][] grid3 = {
          {'S', '1'},
          {'2', 'D'}
      };
      int[] timeArr3 = {2, 2};
      int[] costArr3 = {5, 8};
      List<Result> results3 = transportation.findBestTransportationModes(grid3, timeArr3, costArr3);
      System.out.println("Case 3:");
      for (Result result : results3) {
        System.out.println("Mode: " + result.mode + ", Cost: " + result.cost);
      }

      char[][] grid4 = {
          {'S', '2'},
          {'1', 'D'}
      };
      int[] timeArr4 = {2, 2};
      int[] costArr4 = {3, 3};
      List<Result> results4 = transportation.findBestTransportationModes(grid4, timeArr4, costArr4);
      System.out.println("Case 4:");
      for (Result result : results4) {
        System.out.println("Mode: " + result.mode + ", Cost: " + result.cost);
      }

      char[][] grid2 = {
          {'S', 'X', 'X', 'X', 'X'},
          {'X', '1', 'X', '1', 'X'},
          {'X', 'X', 'X', 'X', 'D'},
      };
      int[] timeArr2 = {1};
      int[] costArr2 = {0};
      List<Result> results2 = transportation.findBestTransportationModes(grid2, timeArr2, costArr2);
      System.out.println("Case 2:");
      for (Result result : results2) {
        System.out.println("Mode: " + result.mode + ", Cost: " + result.cost);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
// O(mn log(mn))