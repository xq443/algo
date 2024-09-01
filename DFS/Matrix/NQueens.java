import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NQueens {
  int n;
  char[][] board;
  List<List<String>> ret;
  public List<List<String>> solveNQueens(int n) {
    this.n = n;
    this.board = new char[n][n];
    this.ret = new ArrayList<>();
    if(n == 0) return ret;

    // build up the board
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        board[i][j] = '#';
      }
    }
    solveNQueens_dfs(0);
    return ret;
  }
  private void solveNQueens_dfs(int row) {
    if(row == n) {
      ret.add(construct(board));
      return;
    }
    for (int col = 0; col < n; col++) {
      if(isValid_solveNQueens(row, col)) {
        board[row][col] = 'Q';
        solveNQueens_dfs(row + 1);
        board[row][col] = '#'; // backtracking
      }
    }
  }

  private boolean isValid_solveNQueens(int row, int col) {
    // row
    for (int i = 0; i < row; i++) {
      if(board[i][col] == 'Q') return false;
    }

    // col
    for (int i = 0; i < col; i++) {
      if(board[row][i] == 'Q') return false;
    }

    // upper diagonal
    for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
      if(board[i][j] == 'Q') return false;
    }

    // upper anti-diagonal
    for(int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
      if(board[i][j] == 'Q') return false;
    }
    return true;
  }

  private List<String> construct(char[][] board) {
    List<String> sub = new ArrayList<>();
    for (int i = 0; i < board.length; i++) {
      sub.add(new String(board[i]));
    }
    return sub;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    NQueens nQueens = new NQueens();
    System.out.println(nQueens.solveNQueens(n));
  }
}
