/**
 *
 * example 1:
 * arr[] = {1,2,-5,4,3,8,5}
 * sum      1,3,-2 4
 * maxsum   1,3, 3 4
 * result = 20
 *
 * The subarray is (4,3,8,5)
 *
 */
public class maxSubArray {
    public static int maxSubArray(int[] nums) {

        int maxSum = nums[0];
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (sum < 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }

            maxSum = Math.max(sum, maxSum);
        }

        return maxSum;
    }
    //time complexity O(n)
    //space complexity O(1)
    public static void main(String[] args) {
        int arr[] = {1,2,-5,6,3,7,4};
        int result = maxSubArray(arr);

        System.out.println(result);

    }
}