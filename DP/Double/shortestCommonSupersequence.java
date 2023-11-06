package Double;

public class shortestCommonSupersequence {
    /**
     * Input: str1 = "abac", str2 = "cab"
     * Output: "cabac"
     * Explanation:
     * str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
     * str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
     * The answer provided is the shortest such string that satisfies these properties.
     */
    public static String shortestCommonSupersequence(String text1, String text2){
        int m = text1.length();
        int n = text2.length();
        text2 = "#" + text2;
        text1 = "#" + text1;
        int [][]dp = new int[m+1][n+1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int i = m, j = n;
        StringBuilder res = new StringBuilder();
        while(j > 0 && i > 0){
            if(text1.charAt(i) == text2.charAt(j)){
                res.append(text1.charAt(i));
                i--;
                j--;
            }else if(dp[i][j] == dp[i - 1][j]){
                res.append(text1.charAt(i));
                i--;
            }else{
                res.append(text2.charAt(j));
                j--;
            }
        }
        while (i > 0) {
            res.append(text1.charAt(i));
            i--;
        }
        while(j > 0){
            res.append(text2.charAt(j));
            j--;
        }
        return res.reverse().toString();
    }
    public static void main(String[] args) {
        String text1 = "mbadm";
        String text2 = "mdabm";
        System.out.println(shortestCommonSupersequence(text1,text2));
    }
}
