/**
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 */
public class min_size_subarray_sum {
    public static int minSubArrayLen (int[] num, int target) {
        //two pointers
        int start = 0, sum = 0;
        int minLength = Integer.MAX_VALUE;

        for(int end = 0; end < num.length; end++) {
            sum += num[end];
            while (start <= end && sum >= target) {
                minLength = Math.min(minLength, (end - start + 1));
                sum -= num[start++];
            }
        }
        return (minLength == Integer.MAX_VALUE) ? 0 : minLength;
    }
    public static void main (String[] args) {
        int[] arr = {2,3,1,2,4,3};
        int target = 7;
        int res = minSubArrayLen(arr,target);
        System.out.println(res);
    }
}
