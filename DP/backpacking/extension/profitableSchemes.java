package backpacking.extension;

public class profitableSchemes {
    /**
     * The ith crime generates a profit[i] and requires group[i] members to participate in it.
     *
     * Let's call a profitable scheme any subset of these crimes that generates at least minProfit profit,
     * and the total number of members participating in that subset of crimes is at most n.
     *
     * Return the number of schemes that can be chosen. Since the answer may be very large, return it modulo 109 + 7.
     * Input: n = 5, minProfit = 3, group = [2,2], profit = [2,3]
     * Output: 2
     */
    public static int profitableSchemes(int n, int minProfit, int[] group, int[] profit){
         /**
         * dp[i][person][profit] = dp[i-1][person][profit] + dp[i-1][person-x][profit-y]
         dp[i][person][minProfit] = dp[i-1][person][profit] + dp[i-1][person-x][profit-y] + ....

         dp[i+1][person][minProfit] += dp[i][person][minProfit]
         dp[i+1][person+x][minProfit+y] += dp[i][person][minProfit]
         return sum{dp[group.length()][person][minProfit]}
         */
         int M=  10^9 +7;
         int m = group.length;
         int [][][] dp = new int[m+1][n+1][minProfit+1]; //group[i] = group[i-1];profit[i] = profit[i-1];
         dp[0][0][0] = 1;

         for (int i = 0; i < m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    dp[i+1][j][k] += dp[i][j][k];
                    dp[i+1][j][k] %= M;

                    if(j+group[i] <= n){
                        dp[i+1][j+group[i]][Math.min(minProfit,k+profit[i])] += dp[i][j][k];
                        dp[i+1][j+group[i]][Math.min(minProfit,k+profit[i])] %= M;
                    }
                }
            }
        }
         int ret = 0;
        for (int i = 0; i < n; i++) {
            ret += dp[m][i][minProfit];
            ret %= M;
        }
        return ret;
    }

    public static void main(String[] args) {
        int n = 5, minProfit = 3;
        int[]group = {2,2}, profit = {2,3};
        System.out.println(profitableSchemes(n, minProfit,group, profit));
    }
}
