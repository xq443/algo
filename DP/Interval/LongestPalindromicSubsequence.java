package Interval;

public class LongestPalindromicSubsequence {
    /**
     * A subsequence is a sequence that can be derived from another sequence
     * by deleting some or no elements without changing the order of the remaining elements.
     *
     * Input: s = "bbbab"
     * Output: 4
     */
    public static int longestPalindromeSubseq(String s){
        int n = s.length();
        s = "#" + s;
        int [][] dp = new int[n+1][n+1];

        for (int len = 1; len <= n; len++) {
            for (int i = 1; i + len - 1 <= n ; i++) {
                int j= i +len - 1;
                if(len == 1) dp[i][j] = 1;
                else if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] +2;
                }else{
                    dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
                }
            }
        }
        return dp[1][n];

    }

    public static void main(String[] args) {
        String s = "bbbab";
        System.out.println(longestPalindromeSubseq(s));
    }
}
