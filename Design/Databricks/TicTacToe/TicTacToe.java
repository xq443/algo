package Databricks.TicTacToe;

class TicTacToe {
  int[][] board;
  int[] rows;
  int[] cols;
  int diag;
  int revdiag;
  int m;
  int n;

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
    if (row == col) diag += sign;
    if (row + col == n - 1) revdiag += sign;

    // Check for a win
    if (Math.abs(rows[row]) == n
        || Math.abs(cols[col]) == n
        || Math.abs(diag) == n
        || Math.abs(revdiag) == n) {
      //printBoard();
      System.out.println("Player " + player + " won");
      return player;
    }

    printBoard();
    return 0;
  }

  public static void main(String[] args) {
    TicTacToe game = new TicTacToe(3, 3);

    // Normal moves
    game.move(0, 0, 1);
    game.move(0, 1, 2);
    game.move(1, 1, 1);
    game.move(1, 0, 2);
    game.move(2, 2, 1); // Player 1 wins

    // Edge cases
    game.move(3, 3, 1); // Out of bounds
    game.move(0, 0, 2); // Cell already occupied
    game.move(2, 0, 2);
    game.move(2, 1, 2); // Player 2 wins
  }
}
