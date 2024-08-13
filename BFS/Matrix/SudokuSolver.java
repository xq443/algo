package Matrix;

public class SudokuSolver {
  public void solveSudoku(char[][] board) {
    solve(board);
  }

  private boolean solve(char[][] board) {
    int size = board.length;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size ; j++) {
        if(board[i][j] == '.') {
          for (char k = '1'; k <= '9'; k++) {
            if(isValidSudoku(board, i, j, k)) {
              //if it's a valid sudoku in this step
              board[i][j] = k;
              // recursively check the next empty cell
              if(solve(board)) return true;
              else board[i][j] = '.'; // not valid in the next step with all possible cases, backtracking
            }
          }
          // no valid number found
          return false;
        }
      }
    }
    return true;
  }

  private boolean isValidSudoku(char[][] board, int row, int col, char target) {
    int n = board.length;
    int boxSize = (int) Math.sqrt(n);  // Calculate the size of the sub-box (3 for a 9x9 Sudoku)
    // check row and col
    for (int i = 0; i < n; i++) {
      if(board[i][col] == target
          || board[row][i] == target) return false;
    }

    // check sub-boxes
    int x_sub = (row / boxSize) * boxSize;
    int y_sub = (col / boxSize) * boxSize;
    for (int i = x_sub; i < x_sub + boxSize; i++) {
      for (int j = y_sub; j < y_sub + boxSize; j++) {
        if(board[i][j] == target) return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    SudokuSolver sudokuSolver = new SudokuSolver();
    char[][] board = {
        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    };

    sudokuSolver.solveSudoku(board);
    for (char[] row : board) {
      for (char num : row) {
        System.out.print(num + " ");
      }
      System.out.println();
    }
  }
}
