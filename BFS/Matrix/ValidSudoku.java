package Matrix;

import java.util.Objects;

public class ValidSudoku {
  public boolean isValidSudoku(String[][] board) {
    if(board.length == 0 || board[0].length == 0) return false;
    int size = board.length;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if(!Objects.equals(board[i][j], ".")) {
          if(!isValidInteger(board[i][j], size)) return false;
          if(!isCheckValid(board, i, j)) return false;
        }
      }
    }
    return true;
  }

  // Method to check if a character is a valid integer between '1' and '9'
  private boolean isValidInteger(String value, int size) {
    int c = value.charAt(0);
    return c >= '1' && c <= ('0' + size);
  }
  private boolean isCheckValid(String[][] board, int x, int y) {
    // check each row and col
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if((i != x && board[i][y] == board[x][y])
            || (i != y && board[x][i] == board[x][y])) return false;
      }
    }
    // check sub-boxes
    int x_sub = (x / 3) * 3;
    int y_sub = (y / 3) * 3;
    for (int i = x_sub; i < x_sub + 3; i++) {
      for (int j = y_sub; j < y_sub + 3; j++) {
        if((i != x || j != y) && Objects.equals(board[i][j], board[x][y])) return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    ValidSudoku validSudoku = new ValidSudoku();
    String[][] board =
        {{"5",".","1",".","7",".",".",".","."}
      ,{"6",".",".","1","9","5",".",".","."}
      ,{".","9","2",".",".",".",".","6","."}
      ,{"8",".",".",".","6",".",".",".","3"}
      ,{"4",".",".","8",".","3",".",".","1"}
      ,{"7",".",".",".","2",".",".",".","6"}
      ,{".","6",".",".",".",".","2","8","."}
      ,{".",".",".","4","1","9",".",".","5"}
      ,{".",".",".",".","8",".",".","7","9"}};
    System.out.println(validSudoku.isValidSudoku(board));
  }

}
