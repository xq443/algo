package Basic;

public class HouseRobberII {
    // max([0, n-1], [1, n])
    public int rob(int[] nums){
        int n = nums.length;
        if(n == 0) return 0;
        int max1 = robHelper(nums, 0, n - 1);
        int max2 = robHelper(nums, 1, n);

        return Math.max(max1, max2);
    }
    public int robHelper(int[] nums, int start, int end){
        int prevYes = nums[start], prevNo = 0;
        for(int i = start + 1; i < end; i++){
            int currYes = prevNo + nums[i];
            int currNo = Math.max(prevNo, prevYes);
            prevYes = currYes;
            prevNo = currNo;
        }
        return Math.max(prevNo,prevYes);
    }

    public static void main(String[] args) {
        HouseRobberII houseRobberII = new HouseRobberII();
        int[] nums = {2,3,2};
        int[] nums_1 = {1,2,3,1};
        int[] nums_2 = {1,2,3};
        System.out.println(houseRobberII.rob(nums));
        System.out.println(houseRobberII.rob(nums_1));
        System.out.println(houseRobberII.rob(nums_2));
    }
}
