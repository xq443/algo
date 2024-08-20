public class WordSearch {
  public boolean exist(char[][] board, String word) {
    if(board == null || board.length == 0 || word == null || word.length() == 0) return false;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if(board[i][j] == word.charAt(0)) {
          if(wordsearch_dfs(i, j, 0, board, word)) return true;
        }
      }
    }
    return false;
  }

  private boolean wordsearch_dfs (int i, int j, int index, char[][] board, String word) {
    if(index == word.length()) return true;
    if(i < 0 || i >= board.length || j < 0 || j >= board[0].length
     || board[i][j] != word.charAt(index)) return false;
    char temp = board[i][j];
    board[i][j] = ' ';
    if(wordsearch_dfs(i + 1, j, index + 1, board, word) ||
    wordsearch_dfs(i - 1, j, index + 1, board, word) ||
    wordsearch_dfs(i, j + 1, index + 1, board, word) ||
    wordsearch_dfs(i, j - 1, index + 1, board, word)) return true;

    //backtracking
    board[i][j] = temp;
    return false;
  }

  public static void main(String[] args) {
    WordSearch wordSearch = new WordSearch();
    char[][] board = {
        {'A', 'B', 'C', 'E'},
        {'S', 'F', 'C', 'S'},
        {'A', 'D', 'E', 'E'}
    };
    String word = "ABCCED";
    System.out.println(wordSearch.exist(board, word));
  }

  // TC O(N * M * 4^L) SC O(L)


}
