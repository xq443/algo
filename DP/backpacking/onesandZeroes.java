package backpacking;

import java.util.Arrays;

public class onesandZeroes {
    /**
     * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
     * Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
     * Output: 4
     * Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
     * Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
     * {"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.
     */
    public static int findMaxForm(String[] s, int m , int n){
        int[][] dp = new int[m+1][n+1];
        int N = s.length;
        for (int i = 0; i < N; i++) {
            int zeros = 0, ones = 0;
            for(char ch: s[i].toCharArray()){
                if(ch == '1') ones++;
                else zeros++;
            }
            int[][] new_dp = new int[m+1][n+1];
            for (int k = 0; k <= m; k++) {
                new_dp[k] = Arrays.copyOf(dp[k], n+1);

            }
            for (int p = zeros; p <= m; p++) {
                for (int j = ones; j <= n; j++) {
                    new_dp[p][j] = Math.max(dp[p][j] , dp[p - zeros][j - ones] + 1); // Deep copy for inner arrays
                }
            }
            dp = new_dp;
        }
        return dp[m][n];
    }
    public static void main(String[] args) {
        String[] s = {"10","0001","111001","1","0"};
        //int m = 5, n = 3;
        String[] s_1 = {"10","0","1"};
        int m = 1, n = 1;
        System.out.println(findMaxForm(s_1, m, n));
    }
}
