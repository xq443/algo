package Double;

public class longestCommonSubsequences {
    public static int longestCommonSubsequence(String text1, String text2){
        /**
         * Input: text1 = "abcde", text2 = "ace"
         * Output: 3
         * Explanation: The longest common subsequence is "ace" and its length is 3.
         */
        int n = text1.length(), m = text2.length();
        text1 = "#" + text1; // start from index 1 to index n
        text2 = "#" + text2; // start from index 1 to index m
        int[][] dp = new int[n+1][m+1];

        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= m; j++) {
                if(text1.charAt(i) == text2.charAt(j)){
                    dp[i][j] = dp[i-1][j-1] +1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[n][m];
    }
    public static void main(String[] args) {
        String txt1 = "abc";
        String txt3 = "abcd";
        System.out.println(longestCommonSubsequence(txt1,txt3));
    }
}
