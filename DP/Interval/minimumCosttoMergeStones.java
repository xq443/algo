package Interval;

import java.util.Arrays;

public class minimumCosttoMergeStones {
    /**
     * There are n piles of stones arranged in a row. The ith pile has stones[i] stones.
     * A move consists of merging exactly k consecutive piles into  one  pile,
     * and the cost of this move is equal to the total number of stones in these k piles.
     *
     * Return the minimum cost to merge all piles of stones into one pile. If it is impossible, return -1.
     */
    public static int minimumCosttoMergeStones(int[] s, int k){
        int n = s.length;
        int [][][] dp = new int[n][n][k+1];
        int[] sum = new int[n+1];
        for (int i = 0; i < n ; i++) {
            sum[i+1] = sum[i] + s[i];
        }
        for (int[][] matrix: dp){
            for(int[] row : matrix){
                Arrays.fill(row, Integer.MAX_VALUE);
            }
        }
        for (int i = 0; i < n; i++) {
            dp[i][i][1] = 0;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                for (int kval = 2; kval <= k ; kval++) {
                    for (int l = i; l < j ; l++) {
                        if(dp[i][l][1] == Integer.MAX_VALUE || dp[l+1][j][kval-1] == Integer.MAX_VALUE) continue;
                        dp[i][j][kval] = Math.min(dp[i][j][kval] , dp[i][l][1] + dp[l+1][j][kval-1]);
                    }
                }
                if(dp[i][j][k] != Integer.MAX_VALUE){
                    dp[i][j][1] = dp[i][j][k] + (sum[j+1] - sum[i]);
                }
            }
        }
        if(dp[0][n - 1][1] == Integer.MAX_VALUE)return  -1;
        else return dp[0][n - 1][1];
    }
    public static void main(String[] args) {
        int[] stones = {3,5,1,2,6};
        int k = 3;
        System.out.println(minimumCosttoMergeStones(stones,k));
    }




    /**
     * * Input: stones = [3,2,4,1], k = 2
     *      * Output: 20
     *      * Explanation: We start with [3, 2, 4, 1].
     *      * We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
     *      * We merge [4, 1] for a cost of 5, and we are left with [5, 5].
     *      * We merge [5, 5] for a cost of 10, and we are left with [10].
     *      * The total cost was 20, and this is the minimum possible.
     */
}
