import java.util.Stack;

public class removeAllAdjacentDuplicatesinStringII {
    /**
     * Input: s = "abcd", k = 2
     * Output: "abcd"
     * Explanation: There's nothing to delete.
     * Example 2:
     *
     * Input: s = "deeedbbcccbdaa", k = 3
     * Output: "aa"
     * Explanation:
     * First delete "eee" and "ccc", get "ddbbbdaa"
     * Then delete "bbb", get "dddaa"
     * Finally delete "ddd", get "aa"
     */
    public static String removeDuplicates(String s, int k){
        Stack<Pair> stack = new Stack<>();
        for(char ch : s.toCharArray()){
            if(stack.isEmpty() || stack.peek().character != ch){
                stack.push(new Pair(1, ch));
            }else{
                stack.push(new Pair(stack.peek().count + 1, ch));
            }
            if(stack.peek().count == k){
                for (int i = 0; i < k; i++) {
                    stack.pop();
                }
            }
        }
        StringBuilder ret = new StringBuilder();
        while(!stack.empty()){
            ret.append(stack.peek().character);
            stack.pop();
        }
        return ret.reverse().toString();
    }

    public static void main(String[] args) {
        String s = "deeedbbcccbdaa";
        int k = 3;
        System.out.println(removeDuplicates(s, k));
        String s_1 = "abcd";
        int k_1 = 3;
        System.out.println(removeDuplicates(s_1, k_1));
    }
}
//TC:O(N) SC:O(N)
