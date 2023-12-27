public class validNumber {
    public boolean isNumber(String S) {
        if(S == null || S.isEmpty()) return false;
        boolean decimal_used = false;
        boolean number_seen = false;

        int i = 0;
        if(S.charAt(i) == '+' || S.charAt(i) == '-') i++;
        while(i < S.length()){
            char curr = S.charAt(i);
            if(Character.isAlphabetic(curr)){
                if(curr != 'e' && curr != 'E') return false;
                else return number_seen && valid_after_exp(S.substring(i+1));
            }else if(curr == '.'){
                if(decimal_used) return false;
                else decimal_used = true;
            }else if(curr == '+' || curr == '-') return false;
            else{
                number_seen = true;
            }
            i++;
        }
        return number_seen;
    }
    private boolean valid_after_exp(String s){
        if(s == null || s.isEmpty()) return false;
        boolean number_seen = false;
        int idx = 0;
        if(s.charAt(idx) == '+' || s.charAt(idx) == '-') idx++;
        while(idx < s.length()){
            char ch = s.charAt(idx);
            if(!Character.isDigit(ch)) return false;
            else number_seen = true;
            idx++;
        }
        return number_seen;
    }
    public static void main(String[] args) {
        validNumber v = new validNumber();
        String t1 = "-123.456e789", t2 = "+6e-1", t3 = "-.9", t4 = "0089", t5 = "--6", t6 = "95a54e53";
        System.out.println(v.isNumber(t3));
    }
}
//TC O(N)
//SC O(1)
