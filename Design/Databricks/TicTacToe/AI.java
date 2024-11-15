package Databricks.TicTacToe;

import java.util.Random;

public class AI {
  int[][] board;
  int[] rows;
  int[] cols;
  int diag; // Count for main diagonal
  int revdiag; // Count for reverse diagonal (for square boards)
  int m; // Number of rows
  int n; // Number of columns
  boolean isAI; // Flag to determine if AI is controlling this player
  Random rand;
  int startingPlayer;

  // Constructor with isAI flag and dimensions
  public AI(int m, int n, boolean isAI) {
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
    this.startingPlayer = 1; // Default starting player is Player 1
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

  // AI logic to choose a random available move
  private int[] getAIMove() {
    while (true) {
      int row = rand.nextInt(m);
      int col = rand.nextInt(n);
      if(validate(row, col)){
        if (board[row][col] == 0) {
          return new int[]{row, col};
        }
      }
    }
  }

  public boolean validate(int rowToMove, int colToMove){
    if (rowToMove < 0 || rowToMove >= m || colToMove < 0 || colToMove >= n) {
      System.out.println("Invalid move: (" + rowToMove + ", " + colToMove + "). Out of bounds!");
      return false;
    }
    if (board[rowToMove][colToMove] != 0) {
      System.out.println("Invalid move: (" + rowToMove + ", " + colToMove + "). Cell already occupied!");
      return false;
    }
    return true;
  }

  // For manual moves by players with row and column directly passed
  private int[] getManualMove(int player, int row, int col) {
    // Validate the move
    if (row >= 0 && row < m && col >= 0 && col < n && board[row][col] == 0) {
      return new int[]{row, col};
    } else {
      System.out.println("Invalid move or position already occupied. Try again.");
      return new int[]{-1, -1}; // Return an invalid move indicator
    }
  }



  public int move(int player, int rowToMove, int colToMove) {

    // Validate the move
    if(!validate(rowToMove, colToMove)) return -1;
    // If the player is AI, get a random valid move
    if (isAI) {
      int[] aiMove = getAIMove();
      rowToMove = aiMove[0];
      colToMove = aiMove[1];
      // if(!validate(rowToMove, colToMove)) return -1;
      System.out.println("AI plays at (" + rowToMove + ", " + colToMove + ")");
    } else{
      // human move
      System.out.println("Human plays at (" + rowToMove + ", " + colToMove + ")");
    }

    int sign = player == 1 ? 1 : -1;
    board[rowToMove][colToMove] = player;
    rows[rowToMove] += sign;
    cols[colToMove] += sign;



      // Update main diagonal (only valid if row == col in the rectangular board)
      if (rowToMove == colToMove) {
        diag += sign;
      }

      // Update reverse diagonal (only valid if row + col == n - 1 in the rectangular board)
      if (rowToMove + colToMove == n - 1) {
        revdiag += sign;
      }

    // Check for a win
    boolean hasWon = Math.abs(rows[rowToMove]) == n || // Row win
        Math.abs(cols[colToMove]) == m || // Column win
        Math.abs(diag) == Math.abs(Math.min(m,n)) ||
        Math.abs(revdiag) == Math.abs(Math.min(m,n));

    if (hasWon) {
      printBoard();
      System.out.println("Player " + player + " won");
      return player;
    }

    printBoard();
    return 0;
  }

  // Method to alternate players (called inside move logic)
  public int rotatePlayer(int currentPlayer) {
    return (currentPlayer == 1) ? 2 : 1;
  }

  // Start the game
  public void startGame(int startingPlayer) {
    int player = startingPlayer;

    // Example of a manual move input for player 1 (row 1, col 1) and player 2 (row 2, col 2)
    while (true) {
      int result = move(player, 1, 1); // Example: Player 1 plays at (1,1)
      if (result != 0) {
        break;
      }
      player = rotatePlayer(player);

      result = move(player, 1, 1); // Example: Player 2 plays at (2,2)
      if (result != 0) {
        break;
      }
      player = rotatePlayer(player);
    }
  }

  public static void main(String[] args) {
    // Game with both players as AI
    AI player1 = new AI(3, 3, true); // Player 1 is AI
    player1.startGame(1);

//    AI game2 = new AI(4, 3, false);
//    game2.move( 2, 0, 2);
//    game2.move(1, 1, 2); // 1
//    game2.move( 2, 1, 1);
//    game2.move(1, 2, 1); // Player 2 wins on reverse diagonal
//    game2.move(1, 3, 0);
  }
}
