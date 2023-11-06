package Double;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class interleavingString {
    public static boolean isInterleave(String text1, String text2, String text3){
        /**
         * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
         * Output: true
         * Explanation: One way to obtain s3 is:
         * Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
         * Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
         * Since s3 can be obtained by interleaving s1 and s2, we return true.
         */
        int n = text1.length(), m = text2.length(), k = text3.length();
        text1 = "#" + text1; // start from index 1 to index n
        text2 = "#" + text2; // start from index 1 to index m
        text3 = "#" + text3;
        boolean [][]dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        for (int i = 1; i <= n ; i++) {
            if(text3.charAt(i) == text1.charAt(i) && dp[i - 1][0]){
                dp[i][0] = true;
            }
        }
        for (int j = 1; j <= m ; j++) {
            if(text3.charAt(j) == text2.charAt(j) && dp[0][j - 1]){
                dp[0][j] = true;
            }
        }
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= m ; j++) {
                if(text3.charAt(i + j) == text1.charAt(i) && dp[i - 1][j]){
                    dp[i][j] = true;
                }else if(text3.charAt(i + j) == text2.charAt(j) && dp[i][j - 1]){
                    dp[i][j] = true;
                }else{
                    dp[i][j] = false;
                }
            }
        }
        return dp[n][m];
    }
    public static void main(String[] args) {
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc";
        System.out.println(isInterleave(s1, s2, s3));
    }
}
