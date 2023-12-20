import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class minRemovetoMakeValidParentheses {
    /**
     * Input: s = "lee(t(c)o)de)"
     * Output: "lee(t(c)o)de"
     * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
     * @param s
     * @return
     */
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if(Character.isLetter(s.charAt(i))){
                continue;
            }
            if(s.charAt(i) == '('){
                stack.push(i);
            }else {
                if(!stack.isEmpty() && s.charAt(stack.peek()) == '('){
                    stack.pop();
                }else stack.push(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        Set<Integer> set = new HashSet<>(stack);
        for (int i = 0; i < s.length(); i++) {
            if(!set.contains(i)){
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        minRemovetoMakeValidParentheses m = new minRemovetoMakeValidParentheses();
        System.out.println(m.minRemoveToMakeValid("lee(t(c)o)de)"));
    }
}
