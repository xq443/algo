package Basic;

public class maximumSubarraySumwithOneDeletion {
    public static int  maximumSum(int[] arr){
        /**
         * no: maximum sum for a non-empty subarray ending at i, w/o any deletion.
         * yes: maximum sum for a non-empty subarray ending at i, w/ one deletion.
         *
         * no    yes
         * no    yes
         */
        int yes = 0;
        int no = arr[0];
        int ret = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int yes_temp = yes, no_temp = no;

            yes = Math.max(yes_temp + arr[i], no_temp);
            no = Math.max(no_temp +arr[i], arr[i]); //subarray, it can count from any index itself
            ret = Math.max(ret, Math.max(yes,no));
        }
        return ret;
    }
    public static void main(String[] args) {
        int[] nums = {1,-2,0,3};
        System.out.println(maximumSum(nums));
    }
}
