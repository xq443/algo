package Basic;

public class houseRobber {
    public static int rob(int[] nums){
        if(nums.length == 0) return 0;
        int rob = nums[0];
        int no_rob = 0;

        for(int i = 1; i < nums.length; i++){
            int norob_temp = no_rob;
            int rob_temp = rob;
            rob = norob_temp + nums[i];
            no_rob = Math.max(norob_temp, rob_temp);
        }
        return Math.max(rob, no_rob);
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        int[] nums_1 = {2,7,9,3,1};
        System.out.println(rob(nums));
        System.out.println(rob(nums_1));
    }

}
//TC O(n)
//SC O(1)
