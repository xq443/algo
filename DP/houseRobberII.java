public class houseRobberII {
    public static int rob(int[] nums){
        int n = nums.length;
        if(n == 0) return 0;
        int max1 = robHelper(nums, 0, n -2);
        int max2 = robHelper(nums, 1, n -1);

        return Math.max(max1, max2);
    }

    public static int robHelper(int[] nums, int start, int end){
        int rob = nums[start], no_rob = 0;
        for(int i = start + 1; i <= end; i++){
            int rob_temp = rob;
            int norob_temp = no_rob;
            rob = norob_temp + nums[i];
            no_rob = Math.max(rob_temp, norob_temp);
        }
        return Math.max(rob,no_rob);

    }
    public static void main(String[] args) {
        int[] nums = {2,3,2};
        int[] nums_1 = {1,2,3,1};
        int[] nums_2 = {1,2,3};
        System.out.println(rob(nums));
        System.out.println(rob(nums_1));
        System.out.println(rob(nums_2));
    }

}
