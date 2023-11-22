public class validSudoku {
    public boolean isValidSudoku(char[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char num = board[i][j];
                if(num != '.'){
                    if(!isValid(num, board, i, j)){
                        return false;
                    }
                }

            }
        }
        return true;
    }
    public boolean isValid(char num, char[][] board, int row, int col){
        for (int k = 0; k < board.length ; k++) {
            if((k != row && board[k][col] == num) ||
                    (k != col && board[row][k] == num)) return false;
        }
        int startRow = 3 * (row / 3);
        int startCol = 3 * (col / 3);
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if(i != row && j != col && board[i][j] == num) return false;
            }

        }
        return true;
    }
    public static void main(String[] args) {
        validSudoku validSudoku = new validSudoku();
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
                };
        System.out.println(validSudoku.isValidSudoku(board));
    }
}
