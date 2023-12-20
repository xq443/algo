import java.util.Stack;

public class basicCalculator_ii {
    public int calculate(String s) {
        if(s.length() == 0 || s == null) return 0;
        //+ 13 + 2 * 29 - 6
        int ret = 0;
        int cur = 0;
        char sign = '+';
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                cur = s.charAt(i) - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    cur = cur * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                if (sign == '+') {
                    stack.push(cur);
                } else if (sign == '-') {
                    stack.push(-cur);
                } else if (sign == '*') {
                    stack.push(stack.pop() * cur);
                } else {
                    stack.push(stack.pop() / cur);
                }
                cur = 0;
            } else if (s.charAt(i) != ' ') {
                sign = s.charAt(i);
            }
        }
        for(int m : stack){
            ret += m;

        }
        return ret;
    }
    public static void main(String[] args) {
        basicCalculator_ii b = new basicCalculator_ii();
        System.out.println(b.calculate("3+5 / 2"));
    }
}
