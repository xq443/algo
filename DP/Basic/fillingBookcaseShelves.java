package Basic;

public class fillingBookcaseShelves {
    public static int minHeightShelves(int[][] books, int shelfWidth){
        //dp[i] = min{dp[i], dp[j] + max(dp[:j-1]}
        int n = books.length;
        int [] dp = new int[n + 1]; //size: n+1
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = books[i - 1][1] + dp[i - 1];
            int maxHeight = books[i -1][0];
            int totalWidth = books[i -1][1];
            for (int j = i - 1; j > 0 ; j--) {
                maxHeight = Math.max(maxHeight, books[j-1][1]);
                totalWidth += books[j - 1][0];
                if(totalWidth > shelfWidth) break;
                dp[i] = Math.min(dp[i], maxHeight + dp[j - 1]);
            }
        }
        return dp[n];
    }
    public static void main(String[] args) {
        int[][] nums = {{1,1},{2,3},{2,3},{1,1},{1,1},{1,1},{1,2}};
        int[][] nums_1 = {{1,3},{2,4},{3,2}};
        System.out.println(minHeightShelves(nums,4));
        System.out.println(minHeightShelves(nums_1,6));

    }
}
