package Double;

public class minimumWindowSubsequence {
    /**
     * Input: s1 = "abcdebdde", s2 = "bde"
     * Output: "bcde"
     * Explanation:
     * "bcde" is the answer because it occurs before "bdde" which has the same length.
     * "deb" is not a smaller window because the elements of s2 in the window must occur in order.
     */
    public static String minWindowSub(String text1, String text2){
        int n = text1.length(), m = text2.length();
        text1 = "#" + text1; // start from index 1 to index n
        text2 = "#" + text2; // start from index 1 to index m
        int [][]dp = new int[n+1][m+1];

        for (int i = 0; i <=n ; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = Integer.MAX_VALUE /2;
        }

        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + 1;
                }
            }
        }
        int len = Integer.MAX_VALUE /2;
        int end = -1;
        for (int i = 0; i <= n ; i++) {
            if (dp[i][m] < len){
                len = dp[i][m];
                end = i;
            }
        }
        if(len == Integer.MAX_VALUE /2) return "";
        return text1.substring(end - len +1 ,end+1);
    }
    public static void main(String[] args) {
        String s1 = "abcdebdde", s2 = "bde";
        System.out.println(minWindowSub(s1, s2));
    }
}
