import java.util.Stack;

public class scoreofParentheses {
    public static int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack<>();
        int curr = 0;
        for(char ch : S.toCharArray()){
            if(ch == ')'){
                if(curr == 0){
                    curr += 1;
                }else{
                    curr *= 2;
                }
                curr = stack.pop() + curr;
            }else{
                stack.push(curr);
                curr = 0;
            }
        }
        return curr;
    }
    public static void main(String[] args) {
        String s= "(())";
        System.out.println(scoreOfParentheses(s));
    }
}
