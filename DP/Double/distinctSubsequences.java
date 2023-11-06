package Double;

public class distinctSubsequences {
    public static int numDistinct(String text1, String text2){
        /**
         * Input: s = "rabbbit", t = "rabbit"
         * Output: 3
         * Explanation:
         * As shown below, there are 3 ways you can generate "rabbit" from s.
         * rabbbit
         * rabbbit
         * rabbbit
         */
        int n = text1.length(), m = text2.length();
        text1 = "#" + text1; // start from index 1 to index n
        text2 = "#" + text2; // start from index 1 to index m
        int [][]dp = new int[n+1][m+1];
        dp[0][0] = 1;

        for (int i = 1; i <= n ; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j <= m ; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m ; j++) {
                if(text1.charAt(i) == text2.charAt(j)){
                    dp[i][j] = dp[i -1][j-1] + dp[i- 1][j];
                }else{
                    dp[i][j] = dp[i -1][j];
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        String s = "rabbbit", t = "rabbit";
        System.out.println(numDistinct(s,t));

    }
}
