package backpacking;

import java.util.Arrays;
public class lastStoneWeightII {
    /**
     * We are playing a game with the stones. On each turn, we choose any two stones and smash them together.
     * Suppose the stones have weights x and y with x <= y. The result of this smash is:
     *
     * If x == y, both stones are destroyed, and
     * If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
     * At the end of the game, there is at most one stone left.
     *
     * Return the smallest possible weight of the left stone. If there are no stones left, return 0.
     *
     * Input: stones = [2,7,4,1,8,1]
     * Output: 1
     */
    public static int lastStoneWeightII(int[] s){
        int n = s.length;
        int sum = Arrays.stream(s).sum();
        int [][]dp = new int[n+1][2 * sum + 2];
        int offset = sum;
        dp[0][0 + offset] = 1;
        for (int i = 1; i <= n; i++) {
            for (int k = -sum; k <= sum ; k++) {
                if(k - s[i-1] >= -sum && k - s[i-1] <= sum){
                    dp[i][k+ offset] |= dp[i-1][k - s[i-1] + offset];
                }
                if(k + s[i-1] >= -sum && k + s[i-1] <= sum){
                    dp[i][k+ offset] |= dp[i-1][k + s[i-1]+ offset];
                }
            }
        }
        int ret = Integer.MAX_VALUE;
        for(int su = 0; su <= sum; su++){
            if(dp[n][su + offset] == 1){
                ret = Math.min(ret, su);
            }
        }
        return ret;
    }
    public static void main(String[] args) {
        int[] s = {2,7,4,1,8,1};
        System.out.println(lastStoneWeightII(s));
    }
}
