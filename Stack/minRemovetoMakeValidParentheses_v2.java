import java.util.SplittableRandom;

public class minRemovetoMakeValidParentheses_v2 {
    public String minRemoveToMakeValid(String s) {
        StringBuilder ret = new StringBuilder();
        int count = 0;
        for(char ch : s.toCharArray()){
            if(ch == '('){
                count++;
                ret.append(ch);
            }else if(ch == ')'){
                if(count > 0){
                    count--;
                    ret.append(')');
                }
            }else{
                ret.append(ch);
            }
        }
        StringBuilder ans = new StringBuilder();
        for (int i = ret.length()-1; i >= 0 ; i--) {
            if(count > 0 && ret.charAt(i) == '('){
                count--;
            }else ans.append(ret.charAt(i));

        }
        return ans.reverse().toString();
    }
    public static void main(String[] args) {
        minRemovetoMakeValidParentheses_v2 m = new minRemovetoMakeValidParentheses_v2();
        System.out.println(m.minRemoveToMakeValid("lee(t(c)o)de)"));
    }

}

