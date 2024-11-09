package Databricks.TicTacToe;

import java.util.Random;

class AITicTacToe {
  int[][] board;
  int[] rows;
  int[] cols;
  int diag;
  int revdiag;
  int m;
  int n;
  boolean isAI;
  Random rand;

  public AITicTacToe(int m, int n, boolean isAI) {
    board = new int[m][n];
    rows = new int[m];
    cols = new int[n];
    diag = 0;
    revdiag = 0;
    this.m = m;
    this.n = n;
    this.isAI = isAI;
    if (isAI) {
      rand = new Random();
    }
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
    if (isAI && player == 2) {
      // AI move
      int[] aiMove = getAIMove();
      row = aiMove[0];
      col = aiMove[1];
    } else {
      // Human move
      if (row < 0 || row >= m || col < 0 || col >= n) {
        System.out.println("Move out of bounds");
        return -1;
      }
      if (board[row][col] != 0) {
        System.out.println("Cell already occupied");
        return -1;
      }
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

    //printBoard();
    return 0;
  }

  private int[] getAIMove() {
    int row, col;
    do {
      row = rand.nextInt(m);
      col = rand.nextInt(n);
    } while (board[row][col] != 0);
    return new int[]{row, col};
  }

  public static void main(String[] args) {
    AITicTacToe game = new AITicTacToe(3, 3, true);

    // Normal moves
    game.move(0, 0, 1);
    game.move(0, 1, 2); // AI move
    game.move(1, 1, 1);
    game.move(1, 0, 2); // AI move
    game.move(2, 2, 1); // Player 1 wins

    // Edge cases
    game.move(3, 3, 1); // Out of bounds
    game.move(0, 0, 2); // Cell already occupied
    game.move(2, 0, 2); // AI move
    game.move(2, 1, 2); // AI move, Player 2 wins
  }
}
