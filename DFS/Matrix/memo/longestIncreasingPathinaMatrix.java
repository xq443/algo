public class longestIncreasingPathinaMatrix {
    //prune dfs
    int[][] dir = {{1,0}, {-1,0}, {0,1},{0,-1}};
    public int longestIncreasingPath(int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] memo = new int[m][n];
        if(m == 1 && n == 1) return 1;
        int ret = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(memo[i][j] == 0){
                    ret = Math.max(ret, dfs_longestIncreasingPath(matrix, memo, i, j));
                }
            }
        }
        return ret;
    }
    private int dfs_longestIncreasingPath(int[][] matrix, int[][] memo, int i, int j){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        if(memo[i][j] > 0) return memo[i][j];
        int m = matrix.length;
        int n = matrix[0].length;
        int ret = 1;
        for(int k = 0; k < 4; k++){
            int new_x = i + dir[k][0];
            int new_y = j + dir[k][1];
            if(new_x < 0 || new_x >= m || new_y < 0 || new_y >= n) continue;
            if(matrix[new_x][new_y] <= matrix[i][j]) continue;
            ret = Math.max(ret, dfs_longestIncreasingPath(matrix, memo, new_x, new_y) + 1);
        }
        memo[i][j] = ret;
        return ret;
    }

    public static void main(String[] args) {
        longestIncreasingPathinaMatrix l = new longestIncreasingPathinaMatrix();
        int[][] matrix = {{9,9,4},{6,6,8},{2,1,1}};
        System.out.println(l.longestIncreasingPath(matrix));
    }
}
//Time Complexity: O(mn)
//Space Complexity: O(mn)
