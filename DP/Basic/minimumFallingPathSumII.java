package Basic;

public class minimumFallingPathSumII {
    public static int minFallingPathSum(int[][] nums){
        //dp[i][j] = min/min2 dp[i - 1][j], j != k
        int m = nums.length;
        int n = nums[0].length;

        int[][] dp = new int[m][n];

        for (int i = 0; i < n; i++) {
            dp[0][i] = nums[0][i];
        }

        for (int i = 1; i < m; i++) {
            int[] temp = new int[n];
            //extract dp[i -1][j]
            for (int k = 0; k < n; k++) {
                temp[k]  = dp[i - 1][k];
            }
            //sort
            int min = Integer.MAX_VALUE;
            int second = Integer.MAX_VALUE;
            for (int q = 0; q < n; q++) {
                if(temp[q] <= min){
                    second = min;
                    min = temp[q];
                }
                if(temp[q] > min && temp[q] < second){
                    second = temp[q];
                }
            }
            //set the condition
            for (int j = 0; j < n; j++) {
                if (dp[i - 1][j] == min) {
                    dp[i][j] = second + nums[i][j];
                } else {
                    dp[i][j] = min + nums[i][j];
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
