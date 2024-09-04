public class NQueensII {
  int ret;
  int n;
  char[][] board;
  public int totalNQueens(int n) {
    this.ret = 0;
    this.board = new char[n][n];
    this.n = n;

    if(n == 0) return ret;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        board[i][j] = '.';
      }
    }

    totalNQueens_dfs(0);
    return ret;
  }

  private void totalNQueens_dfs(int row) {
    if(row == n) {
      ret++;
      return;
    }
    for (int i = 0; i < n; i++) {
      if(isValid_totalNQueens(row, i)) {
        board[row][i] = 'Q';
        totalNQueens_dfs(row + 1);
        board[row][i] = '.';
      }
    }
  }

  private boolean isValid_totalNQueens(int row, int col) {
    for(int i = 0; i < row; i++) {
      if(board[i][col] == 'Q') return false;
    }

    for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
      if(board[i][j] == 'Q') return false;
    }

    for(int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
      if(board[i][j] == 'Q') return false;
    }
    return true;
  }

  public static void main(String[] args) {
    NQueensII nQueensII = new NQueensII();
    int n = 4;
    System.out.println(nQueensII.totalNQueens(n));
  }
}
