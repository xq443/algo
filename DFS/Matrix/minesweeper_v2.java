import java.util.Arrays;

public class minesweeper_v2 {
    int[][] dirs = {{1,0}, {-1,0}, {0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        int r = click[0], c = click[1];
        if(board[r][c] == 'M') {
            board[r][c] = 'X';
            return board;
        }
        int count = countAdjacentMines(board,r,c);
        if(count == 0){
            dfs_updateBoard(board, r, c);
        }else{
            board[r][c] = (char)('0' + count);
        }
        return board;

    }
    private int countAdjacentMines(char[][] board, int i, int j){
        int ret = 0;
        for(int[] dir : dirs){
            int new_x = i + dir[0];
            int new_y = j + dir[1];
            if(new_x >= 0 && new_x < board.length && new_y >= 0 && new_y < board[0].length){
                if(board[new_x][new_y] == 'M'){
                    ret++;
                }
            }
        }
        return ret;
    }
    private void dfs_updateBoard(char[][] board, int new_x, int new_y){
        if(new_x < 0 || new_x >= board.length || new_y < 0 || new_y >= board[0].length) return;
        if(board[new_x][new_y] == 'E'){
            int currCount = countAdjacentMines(board, new_x, new_y);
            if(currCount == 0){
                board[new_x][new_y] = 'B';
                for(int[] dir : dirs){
                    dfs_updateBoard(board, new_x + dir[0], new_y + dir[1]);
                }
            }else{
                board[new_x][new_y] = (char) (currCount + '0');
                return;
            }
        }
    }
    public static void main(String[] args) {
        minesweeper_v2 m = new minesweeper_v2();
        char[][] board = {
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}
        };
        int[] click = {3, 0};
        System.out.println(Arrays.deepToString(m.updateBoard(board, click)));
    }
}
