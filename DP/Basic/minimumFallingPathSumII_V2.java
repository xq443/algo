package Basic;

import java.util.Arrays;
import java.util.Comparator;

public class minimumFallingPathSumII_V2 {
    public static int minFallingPathSum(int[][] nums){
        //dp[i][j] = min/min2 dp[i - 1][j], j != k
        int m = nums.length;
        int n = nums[0].length;

        int[][] dp = new int[m][n];

        System.arraycopy(nums[0], 0, dp[0], 0, n);

        for (int i = 1; i < m; i++) {
            int[][] temp = new int[n][2];
            //extract dp[i -1][k], k
            for (int k = 0; k < n; k++) {
                temp[k][0]  = dp[i - 1][k];
                temp[k][1] = k;
            }
            //sort
            Arrays.sort(temp, Comparator.comparingInt(a -> a[0]));
            //set the condition
            for (int j = 0; j < n; j++) { //compare j
                if (j == temp[0][1]) {
                    dp[i][j] = temp[1][0] + nums[i][j];
                } else {
                    dp[i][j] = temp[0][0] + nums[i][j];
                }
            }
        }
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ret = Math.min(ret, dp[m-1][i]);
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] nums = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(minFallingPathSum(nums));
    }
}

