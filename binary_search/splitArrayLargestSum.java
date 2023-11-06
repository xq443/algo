public class splitArrayLargestSum {
    /**
     * the largest sum of any subarray is minimized.
     *
     * Return the minimized largest sum of the split.
     *
     * A subarray is a contiguous part of the array.

     * Input: nums = [7,2,5,10,8], k = 2
     * Output: 18
     * Explanation: There are four ways to split nums into two subarrays.
     * The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
     */
    public static int splitArray(int[] nums, int k){
        int left = 0;
        long right = Integer.MAX_VALUE;
        while (left < right){
            long mid = left + (right - left) / 2;
            if(checkOK(nums, mid, k)){
                right = mid;
            }else{
                left = (int) (mid + 1);
            }
        }
        return left;
    }
//    public static boolean checkOK(int[] nums, long val, int k){
//        int count = 0;
//        long sum = 0;
//        for (int num :  nums) {
//            if(num > val) return  false;
//            sum += num;
//            if(sum > val){
//                count++;
//                sum = num;
//            }
//        }
//        count++;
//        return count <= k;
//    }
    public static boolean checkOK(int[] nums, long val, int k){
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > val) return  false;
            long sum = 0;
            int j = i;
            while(j < nums.length && (long)nums[j] + sum <= val){
                sum += nums[j];
                j++;
            }
            count++;
            i = j -1;
        }
        return count <= k;
    }
    public static void main(String[] args) {
        int[] nums =  {7,2,5,10,8};
        int k = 2;
        int[] nums_1 = {1,2,3,4,5};
        int k_1 = 2;
        System.out.println(splitArray(nums, k));
        System.out.println(splitArray(nums_1, k_1));
    }
}
