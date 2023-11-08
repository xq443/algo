import java.util.Stack;

public class validParentheses {
    /**
     * Input: s = "()[]{}"
     * Output: true
     */
    public static boolean validParentheses(String s){
        Stack<Character> stack = new Stack<>();
        for(char ch : s.toCharArray()){
            if(ch == '('){
                stack.push(')');
            } else if (ch == '{') {
                stack.push('}');
                
            } else if (ch == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || ch != stack.pop()) {
                return false;
            }
        }
        return stack.isEmpty();
    }
    public static void main(String[] args) {
        String s = "()[]{}";
        System.out.println(validParentheses(s));
    }
}
