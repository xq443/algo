package Basic;

public class wiggleSequence {
    public static int wiggleMaxLength(int[] nums){
        /**
         * down up
         *
         * down up
         */
        int down_len = 1, up_len = 1;
        for (int i = 1; i < nums.length; i++) {
            int down_temp = down_len, up_temp = up_len;

            if(nums[i] > nums[i - 1]){
                up_len = down_temp + 1;
            }
            if(nums[i] < nums[i - 1]){
                down_len = up_temp + 1;
            }
        }
        return Math.max(down_len,up_len);
    }

    public static void main(String[] args) {
        int[] nums = {1,7,4,9,2,5};
        int[] nums_1 = {1,17,5,10,13,15,10,5,16,8};
        int[] nums_2 = {1,2,3,4,5,6,7,8,9};
        System.out.println(wiggleMaxLength(nums));
        System.out.println(wiggleMaxLength(nums_1));
        System.out.println(wiggleMaxLength(nums_2));
    }
}
