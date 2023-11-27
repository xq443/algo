import java.util.Stack;

public class basicCalculator {
    public static int calculate(String s){
        //+(+1+(+4+5+2)-3)+(+6+8)
        StringBuilder sb = new StringBuilder();
        sb.append('+');
        for(char ch : s.toCharArray()){
            if(ch == ' ') continue;
            sb.append(ch);
            if(ch == '(') sb.append('+');
        }
        Stack<Integer> nums = new Stack<>();
        Stack<Integer> signs = new Stack<>();
        int sign = 0;
        int sum = 0;
        for (int i = 0; i < sb.length(); i++) {
            if(sb.charAt(i) == '+') sign = 1;
            else if(sb.charAt(i) == '-') sign = -1;
            else if (Character.isDigit(sb.charAt(i))) {
                //find the completed digits
                int i0 = i;
                int num = 0;
                while(i0 < sb.length() && Character.isDigit(sb.charAt(i0))){
                    num = num * 10 + (sb.charAt(i0) - '0');
                    i0++;
                }
                i = i0 - 1;
                sum += num * sign;
            } else if (sb.charAt(i) == '(') {
                signs.push(sign);
                nums.push(sum);
                sum = 0;
            }else{ //)
                sum = nums.pop() + signs.pop() * sum;
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        String s1 = "(1+(4+5+2)-3)+(6+8)";
        String s2 = "1-(   -2)";
        System.out.println(calculate(s1));
        System.out.println(calculate(s2));
    }
}
