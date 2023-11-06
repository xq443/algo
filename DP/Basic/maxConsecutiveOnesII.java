package Basic;

public class maxConsecutiveOnesII {
    public static int findMaxConsecutiveOnes(int[] arr){
        /**
         * yes    no
         *
         * yes    no
         */
        int yes = 0;
        int no = 0;
        int ret = 0;
        for (int i = 0; i < arr.length ; i++) {
            int yes_temp = yes, no_temp = no;

            if(arr[i] == 0){
                //yes = Math.max(no_temp + 1, 0);
                yes = no_temp + 1;
                no = 0;
            }else{
                yes = yes_temp + 1;
                no = no_temp + 1;
            }
            ret = Math.max(ret, yes);
        }
        return  ret;
    }
    public static void main(String[] args) {
        int[] nums = {1,0,1,1,0};
        int[] nums_2 = {1,0,1,1,0,1};
        System.out.println(findMaxConsecutiveOnes(nums));
        System.out.println(findMaxConsecutiveOnes(nums_2));
    }
}
