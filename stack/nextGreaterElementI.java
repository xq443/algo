import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class nextGreaterElementI {
    /**
     * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
     * Output: [-1,3,-1]
     * Explanation: The next greater element for each value of nums1 is as follows:
     * - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
     * - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
     * - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
     */
    public static int[] nextGreaterElement(int[] s1, int[] s2){
        int[] res = new int[s1.length];
        Stack<Integer> stack = new Stack<>();
        Map<Integer,Integer> mp = new HashMap<>();

        for (int i = 0; i < s2.length; i++) {
            while(!stack.isEmpty() && s2[i] > stack.peek()){
                mp.put(stack.pop(), s2[i]);
            }
            stack.push(s2[i]);
        }
        int i = 0;
        for(int num : s1){
            res[i++] = mp.getOrDefault(num,-1);
        }
        return res;

    }
    public static void main(String[] args) {
        int[] s1 = {4,1,2};
        int[] s2 = {1,3,4,2};
        System.out.println(Arrays.toString(nextGreaterElement(s1, s2)));
    }
}
