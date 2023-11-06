package Interval;

public class palindromePartitioningIII_V2 {
    public static int palindromePartition_V2(String s, int K){
        int n = s.length();
        s = "#" + s;
        int [][] count = new int[n+1][n+1];

        for (int i = 0; i <= n ; i++) {
            for (int j = 0; j <= n ; j++) {
                count[i][j] = 0;
            }
        }
        for (int len = 2; len <= n ; len++) {
            for (int i = 0; i + len -1 <= n; i++) {
                int j = i + len -1;
                if(s.charAt(i) != s.charAt(j)){
                    count[i][j] = count[i+1][j-1] +1;
                }else{
                    count[i][j] = count[i+1][j-1];
                }
            }
        }

        int [][] dp = new int[n+1][K+1];
        for (int i = 0; i <= n ; i++) {
            for (int j = 0; j <= K ; j++) {
                dp[i][j] = Integer.MAX_VALUE /2;
            }
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n ; i++) {
            for (int k = 1; k <= Math.min(i, K) ; k++) {
                for (int j = k; j <= i ; j++) {
                    dp[i][k] = Math.min(dp[i][k], dp[j-1][k-1] + count[j][i]);
                }
            }
        }
        return dp[n][K];
    }
    public static void main(String[] args) {
        String s = "abc";
        int k = 2;
        System.out.println(palindromePartition_V2(s, k));
    }
}
