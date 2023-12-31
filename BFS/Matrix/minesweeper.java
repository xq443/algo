import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class minesweeper {
    int[][] dirs = {{1,0}, {-1,0}, {0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        int r = click[0], c = click[1];
        if(board[r][c] == 'M') {
            board[r][c] = 'X';
            return board;
        }
        int count = countAdjacentMines(board,r,c);
        if(count != 0){
            board[r][c] = (char)(count + '0');
        }else {
            Queue<int[]> q = new LinkedList<>();
            q.offer(click);

            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] curr = q.poll();
                    assert curr != null;
                    int x = curr[0], y = curr[1];
                    board[x][y] = 'B';
                    for (int[] dir : dirs) {
                        int new_x = x + dir[0];
                        int new_y = y + dir[1];
                        if (new_x < 0 || new_x >= board.length || new_y < 0 || new_y >= board[0].length) continue;
                        if (board[new_x][new_y] == 'E') {
                            int currCount = countAdjacentMines(board, new_x, new_y);
                            if (currCount != 0) {
                                board[new_x][new_y] = (char) (currCount + '0');
                            }else{
                                board[new_x][new_y] = 'B';
                                q.offer(new int[]{new_x, new_y});
                            }
                        }
                    }
                }
            }
        }
        return board;
    }
    private int countAdjacentMines(char[][] board, int i, int j){
        int ret = 0;
        for(int[] dir : dirs){
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) continue;
            if(board[x][y] == 'M'){
                ret++;
            }
        }
        return ret;
    }
    public static void main(String[] args) {
        minesweeper m = new minesweeper();
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
