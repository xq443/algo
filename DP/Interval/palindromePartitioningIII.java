package Interval;
public class palindromePartitioningIII {
    public static int palindromePartition(String s, int K){
        // xxxxxxxx
        //     k
        //  [j-1][k-1]  s[j:i]
        int n = s.length();
        s = "#" + s;
        int dp[][] = new int[n+1][K+1];
        for (int i = 0; i <= n ; i++) {
            for (int k = 0; k <= K; k++) {
                dp[i][k] = Integer.MAX_VALUE /2;
            }
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n ; i++) {
            for (int k = 1; k <= Math.min(i, K) ; k++) {
                for (int j = k; j <= i; j++) {
                    dp[i][k] = Math.min(dp[i][k], dp[j-1][k-1] + helper(j, i, s));
                }
            }
        }
        return dp[n][K];
    }
    public static int helper(int a, int b, String s){
        int count = 0;
        while(a < b){
            if(s.charAt(a) != s.charAt(b)){
                count ++;
            }
            a++;
            b--;
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "aabbc";
        int k = 3;
        System.out.println(palindromePartition(s, k));
    }
}
