package Databricks.Transportation;

import java.util.*;

class GridPath {
  static class Cell {
    int row, col, time, cost;
    Cell(int row, int col, int time, int cost) {
      this.row = row;
      this.col = col;
      this.time = time;
      this.cost = cost;
    }
  }

  public static int[] findShortestPath(char[][] grid, int[] timeArray, int[] costArray) {
    int rows = grid.length;
    int cols = grid[0].length;

    boolean[][] visited = new boolean[rows][cols];
    PriorityQueue<Cell> pq = new PriorityQueue<>(Comparator.comparingInt((Cell c) -> c.time).thenComparingInt(c -> c.cost));

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
      System.out.println("starting points or destination points not found.");
      return new int[]{-1, -1};
    }

    pq.offer(new Cell(startRow, startCol, 0, 0));
    visited[startRow][startCol] = true;

    // Array for direction movements (up, down, left, right)
    int[] directions = {-1, 0, 1, 0, -1};

    while (!pq.isEmpty()) {
      Cell current = pq.poll();
      System.out.println("当前处理单元格: (" + current.row + ", " + current.col + "), 时间: " + current.time + ", 开销: " + current.cost);
      if (current.row == endRow && current.col == endCol) {
        return new int[]{current.time, current.cost};
      }

      for (int d = 0; d < 4; d++) {
        int newRow = current.row + directions[d];
        int newCol = current.col + directions[d + 1];
        if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && !visited[newRow][newCol] && grid[newRow][newCol] != 'X') {
          char transport = grid[newRow][newCol];
          if (Character.isDigit(transport) || transport == 'D') {
            int transportType = transport == 'D' ? 0 : Character.getNumericValue(transport);
            int newTime = current.time + (transport == 'D' ? 0 : timeArray[transportType - 1]);
            int newCost = current.cost + (transport == 'D' ? 0 : costArray[transportType - 1]);
            pq.offer(new Cell(newRow, newCol, newTime, newCost));
            visited[newRow][newCol] = true;
            System.out.println("加入队列: (" + newRow + ", " + newCol + "), 时间: " + newTime + ", 开销: " + newCost);
          }
        }
      }
    }
    return new int[]{-1, -1}; // If no path found
  }

  public static void main(String[] args) {
    // Test Case 1
    char[][] grid1 = {
        {'3', '3', 'S', '2', 'X', 'X'},
        {'3', '1', '1', '2', 'X', '2'},
        {'3', '1', '1', '2', '2', '2'},
        {'3', '1', '1', '1', 'D', '3'},
        {'3', '3', '3', '3', '3', '4'},
        {'4', '4', '4', '4', '4', '4'}
    };
    int[] timeArray1 = {3, 2, 1, 1};
    int[] costArray1 = {0, 1, 3, 2};
    int[] result1 = findShortestPath(grid1, timeArray1, costArray1);
    System.out.println("Grid 1 - 最短时间: " + result1[0] + ", 最小开销: " + result1[1]);

    // Test Case 2: not reachable
    char[][] grid2 = {
        {'S', '1', 'X'},
        {'X', 'X', 'D'},
        {'2', '2', 'X'}
    };
    int[] timeArray2 = {3, 2};
    int[] costArray2 = {0, 1};
    int[] result2 = findShortestPath(grid2, timeArray2, costArray2);
    System.out.println("Grid 2 - 最短时间: " + result2[0] + ", 最小开销: " + result2[1]);

    // Test Case 3: no need to take time
    char[][] grid3 = {
        {'S', 'D'}
    };
    int[] timeArray3 = {3};
    int[] costArray3 = {0};
    int[] result3 = findShortestPath(grid3, timeArray3, costArray3);
    System.out.println("Grid 3 - 最短时间: " + result3[0] + ", 最小开销: " + result3[1]);

    // Test Case 4: blocked
    char[][] grid4 = {
        {'S', 'X', 'X'},
        {'X', '1', 'X'},
        {'2', 'X', 'D'}
    };
    int[] timeArray4 = {3, 2};
    int[] costArray4 = {1, 1};
    int[] result4 = findShortestPath(grid4, timeArray4, costArray4);
    System.out.println("Grid 4 - 最短时间: " + result4[0] + ", 最小开销: " + result4[1]);

    // Test Case 5
    char[][] grid5 = {
        {'S', '1'},
        {'4', 'D'}
    };
    int[] timeArray5 = {5, 3, 2, 5};
    int[] costArray5 = {4, 2, 1, 0};
    int[] result5 = findShortestPath(grid5, timeArray5, costArray5);
    System.out.println("Grid 5 - 最短时间: " + result5[0] + ", 最小开销: " + result5[1]);
  }
}
//time complexity : O(m×nlog(m×n))
//Space Complexity: O(m×n)
