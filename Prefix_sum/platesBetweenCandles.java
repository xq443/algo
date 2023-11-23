package Prefix_sum;

import java.util.Arrays;

public class platesBetweenCandles {
    /**
     * Input: s = "**|**|***|", queries = [[2,5],[5,9]]
     * Output: [2,3]
     * Explanation:
     * - queries[0] has two plates between candles.
     * - queries[1] has three plates between candles.
     */
    public int[] platesBetweenCandles(String s, int [][] queries){
        int n = s.length();
        int [] preSum = new int[n];
        int temp = 0;
        for (int i = 0; i < n; i++) {
            temp += (s.charAt(i) == '*')? 1 : 0;
            preSum[i] = temp;
        }

        temp = -1;
        int[] last = new int[n];
        for (int i = 0; i < n; i++) {
            if(s.charAt(i) == '|'){
                temp = i;
            }
            last[i] = temp;
        }
        temp = n;
        int[] prev = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            if(s.charAt(i) == '|'){
                temp = i;
            }
            prev[i] = temp;
        }
        int[] ret = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int x_boundary = queries[i][0];
            int y_boundary = queries[i][1];
            int left = prev[x_boundary];
            int right = last[y_boundary];

            if(left <= right && x_boundary <= left && right <= y_boundary){
                ret[i] = preSum[right] - preSum[left];
            }
        }
        return ret;
    }
    public static void main(String[] args) {
        String s=  "**|**|***|";
        int [][] queries = {{2,5},{5,9}};
        platesBetweenCandles platesBetweenCandles = new platesBetweenCandles();
        System.out.println(Arrays.toString(platesBetweenCandles.platesBetweenCandles(s, queries)));
    }
}
//TC O(N + M)
//SC O(N + M)
