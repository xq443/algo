public class basicCalculator_v2 {
    public int calculate(String s) {
        return cals(s,0)[0];
    }
    private int[] cals(String s, int i){
        int ret = 0, curr = 0, sign = 1;
        while(i < s.length()){
            if(Character.isDigit(s.charAt(i))){
                curr = curr * 10 + s.charAt(i) - '0';
            } else if (s.charAt(i) == '(') {
                int[] res = cals(s, i + 1);
                i = res[1];
                curr = res[0];
            } else if (s.charAt(i) == ')') {
                ret += curr * sign;
                return new int[]{ret, i};

            }else if(s.charAt(i) == '+' || s.charAt(i) == '-'){
                ret += curr * sign;
                curr = 0;
                sign = s.charAt(i) == '-' ? -1 : 1;
            }
            i++;
        }
        ret += curr * sign;
        return new int[]{ret, i};
    }
    public static void main(String[] args) {
        basicCalculator_v2 a = new basicCalculator_v2();
        String s1 = "(1+(4+5+2)-3)+(6+8)";
        String s2 = "1-(   -2)";
        System.out.println(a.calculate(s1));
        System.out.println(a.calculate(s2));
    }
}
