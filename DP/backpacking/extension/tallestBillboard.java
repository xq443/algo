package backpacking.extension;

import java.util.Arrays;

public class tallestBillboard {
    /**
     * Each steel support must be an equal height.
     * Return the largest possible height of your billboard installation. If you cannot support the billboard, return 0.
     *
     * Input: rods = [1,2,3,6]
     * Output: 6
     * Explanation: We have two disjoint subsets {1,2,3} and {6}, which have the same sum = 6.
     *
     *
     * Constraints:
     * 1 <= rods.length <= 20
     * 1 <= rods[i] <= 1000
     * sum(rods[i]) <= 5000
     */
    public static int tallest(int[] rods){
        //diff as the max left value of left - right
        int n = rods.length;
        int offset = 5000;
        int[] res = new int[2 * offset + 1];
        Arrays.fill(res, -1);

        res[0 + offset] = 0;
        for (int i = 0; i < n; i++) {
            int rod = rods[i];
            int []res_prev = res.clone();
            for (int diff = -offset; diff <= offset ; diff++) {
                if(res_prev[diff + offset] ==  -1) continue;
                if(diff + rod <= offset)
                    res[diff + rod + offset] = Math.max(res[diff + rod + offset], res_prev[diff + offset] + rod);
                if(diff - rod >= -offset)
                    res[diff - rod + offset] = Math.max(res[diff - rod + offset], res_prev[diff + offset]);
            }
        }
        return res[0 + offset];
    }
    public static void main(String[] args) {
        int[] test = {1,2,3,6};
        System.out.println(tallest(test));
    }
}
