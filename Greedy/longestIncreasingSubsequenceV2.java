public class longestIncreasingSubsequenceV2 {
    public static int lengthOfLIS_v2(int[] nums){
        /** nums = [10,9,2,5,3,7,101,18]
         * 10
         * 9
         * 2
         * 2, 5
         * 2, 3
         * 2, 3, 7
         * 2, 3, 7, 101
         * 2, 3, 7, 18
         *
         * TC O(NlogN)
         */
        int n = nums.length;
        if(n == 0) return 0;

        int[] res = new int[n];
        int len = 0;
        res[0]  = nums[0];
        for (int i = 1; i < n; i++) {
            int pos = binarySearch_v2(res, len, nums[i]);
            if(pos > len){
                len = pos;
                res[pos] = nums[i];
            }
            if(nums[i] < res[pos]){
                res[pos] = nums[i];
            }
        }
        return len +1;

    }
//        int n = nums.length;
//        if(nums.length == 0) return 0;
//        int[] res = new int[n];
//        res[0] = nums[0];
//        int len = 0;
//        for (int i = 1; i < n; i++) {
//            int pos = binarySearchLIS(res, nums[i], len);
//            if(nums[i] < res[pos]){
//                res[pos] = nums[i];
//            }
//            if(pos > len){
//                len = pos;
//                res[pos] = nums[i];
//            }
//        }
//        return  len+1;
//    }
    private static int binarySearch_v2(int[] nums, int len, int val){
        int left = 0;
        int right = len;

        while(left + 1 < right){
            int mid = left + (right - left) /2;
            if(nums[mid] == val) return mid;
            else if(nums[mid] < val) left = mid;
            else right = mid;
        }
        if(val > nums[right]) return len +1;
        else if(val <= nums[left]) return left;
        else return right;
    }

//    private static int binarySearchLIS(int[] nums, int val, int len){
//        int left = 0;
//        int right = len;
//        while(left + 1 < right){
//            int mid = left + (right - left) / 2;
//            if(nums[mid] == val) return mid;
//            else if(nums[mid] > val) right = mid;
//            else left = mid;
//        }
//        if(val <= nums[left]) return left;
//        else if(nums[right] < val) return len+1;
//        else return right;
//    }
    public static void main(String[] args) {
        int [] nums = {10,9,2,5,3,7,101,18};
        int [] nums_1 = {0,1,0,3,2,3};
        int [] nums_2 = {7,7,7,7,7,7,7};
        System.out.println(lengthOfLIS_v2(nums));
        System.out.println(lengthOfLIS_v2(nums_1));
        System.out.println(lengthOfLIS_v2(nums_2));
    }
}

