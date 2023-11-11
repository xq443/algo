package backpacking;

import java.util.HashSet;

public class lastStoneWeightII_V2 {
    public static int lastStoneWeightII(int[] s) {
        HashSet<Integer> set = new HashSet<>();
        set.add(0);
        // Input: stones = [2,7,4,1,8,1] Output: 1
        for (int x : s) {
            HashSet<Integer> temp = new HashSet<>(set);
            set.clear();
            for(int m : temp){
                set.add(m + x);
                set.add(m - x);
            }
        }
        int ret = Integer.MAX_VALUE;
        for(int x : set){
            if(x >= 0 && x < ret){
                ret = x;
            }
        }
        return ret;
    }
    public static void main(String[] args) {
        int[] s = {2,7,4,1,8,1};
        System.out.println(lastStoneWeightII(s));
    }
 }