import java.util.Stack;

public class basicCalculator {
    public int calculate(String s) {
        // +(+1+(4+5+2)-3)+(+6+8)
        // +1-(+-2)"
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

        for(int i = 0; i < sb.length(); i++){
            if(sb.charAt(i) == '+') sign = 1;
            else if(sb.charAt(i) == '-') sign = -1;
            else if(Character.isDigit(sb.charAt(i))){
                //int i0 = i;
                int num = sb.charAt(i) - '0';
                while(i + 1 < sb.length() && Character.isDigit(sb.charAt(i + 1))){
                    num = num * 10 + (sb.charAt(i + 1) - '0');
                    i++;
                }
                //i = i0 - 1;
                //num = Integer.parseInt(sb.toString().substring(i0, i+1));
                sum += sign * num;
            }
            else if(sb.charAt(i) == '('){
                signs.push(sign);
                nums.push(sum);
                sum = 0;
            }else{ //')'
                sum = signs.pop() * sum + nums.pop();
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        basicCalculator a = new basicCalculator();
        String s1 = "(1+(4+5+2)-3)+(6+8)";
        String s2 = "1-(   -2)";
        System.out.println(a.calculate(s1));
        System.out.println(a.calculate(s2));
    }
}
