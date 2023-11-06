package Double;

public class editDistance {
    public static int minDistance(String text1, String text2){
        /**
         * Input: word1 = "horse", word2 = "ros"
         * Output: 3
         * Explanation:
         * horse -> rorse (replace 'h' with 'r')
         * rorse -> rose (remove 'r')
         * rose -> ros (remove 'e')
         */
        int n = text1.length(), m = text2.length();
        text1 = "#" + text1; // start from index 1 to index n
        text2 = "#" + text2; // start from index 1 to index m
        int [][]dp = new int[n+1][m+1];

        for (int i = 0; i <=n ; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= m; j++) {
                if(text1.charAt(i) == text2.charAt(j)){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = Math.min(dp[i-1][j] + 1, Math.min(dp[i][j-1] + 1, dp[i-1][j-1] +1 ));
                }
            }
        }
        return dp[n][m];
    }
    public static void main(String[] args) {
        String text1 = "intention";
        String text2 = "execution";
        System.out.println(minDistance(text1,text2));
    }
}
