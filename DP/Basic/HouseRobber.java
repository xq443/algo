package Basic;

public class HouseRobber {
//    public int rob(int[] nums) {
//         if(nums == null || nums.length == 0) return 0;
//         int[][] dp = new int[nums.length][2];
//         dp[0][0] = 0;
//         dp[0][1] = nums[0];
//         for(int i = 1; i < nums.length; i++) {
//             dp[i][0] = Math.max(dp[i-1][1], dp[i-1][0]);
//             dp[i][1] = dp[i-1][0] + nums[i];
//         }
//         return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
//     }
    public  int rob(int[] nums){
        if(nums == null || nums.length == 0) return 0;
        int prevYes = nums[0];
        int prevNo = 0;
        // j - 1    n  y
        // j        y  n
        for(int i = 1; i < nums.length; i++){
            int currYes = prevNo + nums[i];
            int currNo = Math.max(prevNo, prevYes);
            prevYes = currYes;
            prevNo = currNo;
        }
        return Math.max(prevNo, prevYes);
    }
    public static void main(String[] args) {
        HouseRobber houseRobber = new HouseRobber();
        int[] nums = {1,2,3,1};
        int[] nums_1 = {2,7,9,3,1};
        System.out.println(houseRobber.rob(nums));
        System.out.println(houseRobber.rob(nums_1));
    }

}
//TC O(n)
//SC O(1)
