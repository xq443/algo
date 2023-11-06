package Basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class largestDivisibleSubset {
    public static List<Integer> LargestDivisibleSubset(int[] nums){
        int n = nums.length;
        int [] dp = new int[n];
        int [] prev = new int[n];

        Arrays.sort(nums);

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            prev[i] = -1;
            for (int j = 0; j < i; j++) {
                if(nums[i] % nums[j] == 0){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                if(dp[i] == dp[j] + 1){
                    prev[i] = j;
                }
            }
        }
        int idx = 0;
        int len = 0;
        for (int i = 0; i < n; i++) {
            if(dp[i] > len){
                len = dp[i];
                idx = i;
            }
        }
        List<Integer> ret = new ArrayList<>();
        while (idx != -1){
            ret.add(nums[idx]);
            idx = prev[idx];
        }
        return ret;
    }
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int[] nums_1 = {1,2,4,8};
        System.out.println(LargestDivisibleSubset(nums));
        System.out.println(LargestDivisibleSubset(nums_1));
    }
}
//TC 0(N2)
