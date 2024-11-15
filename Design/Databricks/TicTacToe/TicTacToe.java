package Databricks.TicTacToe;

public class TicTacToe {
  int[][] board;
  int[] rows;
  int[] cols;
  int diag; // Count for main diagonal
  int revdiag; // Count for reverse diagonal
  int m; // Number of rows
  int n; // Number of columns

  public TicTacToe(int m, int n) {
    board = new int[m][n];
    rows = new int[m];
    cols = new int[n];
    diag = 0;
    revdiag = 0;
    this.m = m;
    this.n = n;
  }

  public void printBoard() {
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public int move(int row, int col, int player) {
    if (row < 0 || row >= m || col < 0 || col >= n) {
      System.out.println("Move out of bounds");
      return -1;
    }
    if (board[row][col] != 0) {
      System.out.println("Cell already occupied");
      return -1;
    }

    int sign = player == 1 ? 1 : -1;
    board[row][col] = player;
    rows[row] += sign;
    cols[col] += sign;

    // Update main diagonal (only valid if row == col in the rectangular board)
    if (row == col) {
      diag += sign;
    }

    // Update reverse diagonal (only valid if row + col == n - 1 in the rectangular board)
    if (row + col == n - 1) {
      revdiag += sign;
    }

    // Check for a win
    boolean hasWon = Math.abs(rows[row]) == n || // Row win
        Math.abs(cols[col]) == m || // Column win
        (Math.abs(diag) == Math.min(m, n)) || // Main diagonal win
        (Math.abs(revdiag) == Math.min(m, n)); // Reverse diagonal win

    if (hasWon) {
      printBoard();
      System.out.println("Player " + player + " won");
      return player;
    }

    printBoard();
    return 0;
  }

  public static void main(String[] args) {
    // Test case 1: Square board
    TicTacToe game1 = new TicTacToe(3, 3);
    game1.move(0, 0, 2);
    game1.move(0, 2, 1);
    game1.move(1, 1, 2);
    game1.move(2, 1, 1);
    game1.move(2, 2, 2); // Player 2 wins on main diagonal

    // Test case 2: Reverse diagonal on a rectangular board
    TicTacToe game2 = new TicTacToe(4, 3);
    game2.move(1, 0, 2);
    game2.move(0, 0, 1);
    game2.move(2, 1, 2);
    game2.move(3, 2, 2); // Player 2 wins on reverse diagonal

    // Test case 3: Row win on rectangular board
    TicTacToe game3 = new TicTacToe(4, 3);
    game3.move(2, 0, 1);
    game3.move(2, 1, 1);
    game3.move(2, 2, 1); // Player 1 wins with a row

    // Test case 4: Column win on rectangular board
    TicTacToe game4 = new TicTacToe(4, 3);
    game4.move(0, 1, 2);
    game4.move(1, 1, 2);
    game4.move(2, 1, 2);
    game4.move(3, 1, 2); // Player 2 wins with a column
  }
}
