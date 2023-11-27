import java.util.Stack;

public class decodeString {
    /**
     * Input: s = "3[a]2[bc]"
     * Output: "aaabcbc"
     *
     * Input: s = "3[a2[c]]"
     * Output: "accaccacc"
     */
    public static String decodeString(String s){
        Stack<String> str = new Stack<>();
        Stack<Integer> num = new Stack<>();
        String ret= "";

        for (int i = 0; i < s.length(); i++) {
            if(Character.isDigit(s.charAt(i))){
                int start = i;
                while(i < s.length() && Character.isDigit(s.charAt(i))){
                    i++;
                }
                int nums = Integer.parseInt(s.substring(start, i));
                num.push(nums);
                str.push("[");
            } else if (s.charAt(i) == ']') {
                int nums = num.peek();
                String temp = "";
                while(!str.isEmpty() && !str.peek().equals("[")){
                    temp = str.pop() + temp;
                }

                for (int j = 0; j < nums; j++) {
                    ret += temp;
                }
                str.pop();
                num.pop();
                str.push(ret);
                ret = "";

            }else{
                str.push(String.valueOf(s.charAt(i)));
            }
        }
        StringBuilder result = new StringBuilder();
        while (!str.isEmpty()){
            result.insert(0, str.pop());
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String s_1 = "3[a]2[bc]";
        String s_2 = "3[a2[c]]";
        System.out.println(decodeString(s_1));
        System.out.println(decodeString(s_2));


    }
}
