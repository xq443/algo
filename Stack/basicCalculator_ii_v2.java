public class basicCalculator_ii_v2 {
    public int calculate(String s) {
        //+ 13 + 2 * 29 - 6
        if(s.length() == 0) return 0;
        int cur = 0, prev = 0, ret = 0;
        char sign = '+';
        for (int i = 0; i < s.length(); i++) {
            //extract number from string
            if(Character.isDigit(s.charAt(i))){
                cur = s.charAt(i) - '0';
                while(i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    cur = cur * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                //operator calculation
                if(sign == '+') {
                    ret += cur;
                    prev = cur;
                } else if (sign == '-') {
                    ret -= cur;
                    prev = -cur;

                } else if (sign == '*') {
                    ret -= prev;
                    ret += prev * cur;
                    prev = prev * cur;
                }else{
                    ret -= prev;
                    ret += prev / cur;
                    prev = prev / cur;
                }
                cur = 0;
                }
            else if(s.charAt(i) != ' ') {
                sign = s.charAt(i);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        basicCalculator_ii_v2 b = new basicCalculator_ii_v2();
        System.out.println(b.calculate("3+5 / 2"));
    }
}
