package Matrix;

import java.util.Arrays;

public class YoungTableau {
  int[][] matrix;
  int m;
  int n;
  int size;

  public YoungTableau(int m, int n) {
    this.m = m;
    this.n = n;
    this.size = 0;
    this.matrix = new int[m][n];
    for (int i = 0; i < m; i++) {
      Arrays.fill(matrix[i], Integer.MAX_VALUE);
    }
  }

  public void print() {
    for (int i = 0; i < m; i++) {
      System.out.println(Arrays.toString(matrix[i]));
    }
  }

  public int extractMin() throws Exception {
    if (size == 0)
      throw new Exception("Matrix is Empty");
    int min = matrix[0][0];
    matrix[0][0] = Integer.MAX_VALUE;
    size--;
    bubbledown(0, 0);
    return min;
  }

  private void bubbledown(int i, int j) {
    int curr = matrix[i][j];
    int minRow = i;
    int minCol = j;
    while (true) {
      if (i + 1 < m && matrix[i + 1][j] < curr) {
        minRow = i + 1;
      }
      if (j + 1 < n && matrix[i][j + 1] < curr) {
        minRow = i;
        minCol = j + 1;
      }
      if (minRow == i && minCol == j)
        break;

      // swap the value
      swapBubble(minRow, minCol, i, j);
      i = minRow;
      j = minCol;
    }
  }

  public void insert(int v) throws Exception {
    if (size == m * n)
      throw new Exception("Matrix has already been filled");
    // insert in the bottom-right corner
    int row = size / n;
    int col = size % n;
    matrix[row][col] = v;
    size++;
    bubbleup(row, col);
  }

  private void bubbleup(int i, int j) {
    int curr = matrix[i][j];

    while (i > 0 && matrix[i - 1][j] > curr) {
      swapBubble(i - 1, j, i, j);
      i--;
    }
    while (j > 0 && matrix[i][j - 1] > curr) {
      swapBubble(i, j - 1, i, j);
      j--;
    }
  }

  private void swapBubble(int i1, int j1, int i2, int j2) {
    int temp = matrix[i1][j1];
    matrix[i1][j1] = matrix[i2][j2];
    matrix[i2][j2] = temp;
  }

  public int search(int v) {
    // start from the top-right corner
    int row = 0, col = n - 1;
    while (row < m && col >= 0) {
      if (v == matrix[row][col]) {
        return matrix[row][col];
      } else if (v > matrix[row][col]) {
        row++;
      } else
        col--;
    }
    return -1;
  }

  public static void main(String[] args) throws Exception {
    YoungTableau yt =
        new YoungTableau(2, 3); // Create an empty 2x3 Young tableau
    try {
      yt.insert(7); // Insert 7 into the Young tableau
      yt.insert(6); // Insert 6 into the Young tableau
      yt.insert(9); // Insert 9 into the Young tableau
      yt.insert(1); // Insert 1 into the Young tableau
      yt.insert(3); // Insert 3 into the Young tableau
      yt.insert(5); // Insert 5 into the Young tableau
      yt.insert(2); // Cannot insert 2 because the Young tableau is full.
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    yt.print(); // Print the Young tableau
    System.out.println("===========");

    System.out.println(yt.search(1));  // Should output 0
    System.out.println(yt.search(2));  // Should output null
    System.out.println(yt.search(3));  // Should output 1
    System.out.println(yt.search(4));  // Should output null
    System.out.println(yt.search(5));  //
    System.out.println(yt.search(6));  // Should output 3
    System.out.println(yt.search(7));  // Should output 4
    System.out.println(yt.search(8));  // Should output null
    System.out.println(yt.search(9));  //
    System.out.println(yt.search(10)); // Should output null

    System.out.println("===========");

    System.out.println(yt.extractMin()); // Should output 1.
    yt.print(); // Print the Young tableau after extraction

    System.out.println(yt.extractMin()); // Should output 3.
    yt.print(); // Print the Young tableau after extraction
  }
}
