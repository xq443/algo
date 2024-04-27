package Interval;

public class BurstBalloon {
    /**
     * if you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins.
     * If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.
     *
     * Return the maximum coins you can collect by bursting the balloons wisely.
     *
     * Input: nums = [3,1,5,8]
     * Output: 167
     * Explanation:
     * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
     * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
     */
    public static int burstBalloon(int[] s){
        int n = s.length;
        int[] modified = new int[n+2];
        modified[0] = 1;
        modified[n+1] = 1;
        for(int i = 1; i <= n; i++) {
            modified[i] = s[i-1];
        }

        int [][] dp = new int[n+2][n+2];
        for (int i = 0; i <= n +1 ; i++) {
            for (int j = 0; j <= n +1 ; j++) {
                dp[i][j] = 0;
            }
        }
        for (int len = 1; len <= n ; len++) {
            for (int i = 1; i + len -1 <= n ; i++) {
                int j = i + len - 1;
                for (int k = i; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k-1] + modified[i-1]* modified[k] * modified[j+1] + dp[k+1][j]);
                }
            }
        }
        return dp[1][n];
    }
    public static void main(String[] args) {
        int [] s =  {3,1,5,8};
        System.out.println(burstBalloon(s));
    }
}
