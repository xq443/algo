public class ValidParentheses_v2 {
  public boolean isValid(String s) {
    if(s.length() % 2 != 0) return false;
    char[] stack = new char[s.length()];

    int index = -1;
    for(char ch : s.toCharArray()) {
      if(ch == '(' || ch == '[' || ch == '{') {
        stack[++index] = ch;
      } else if(index >= 0 && (
          (ch == ')' && stack[index] == '(') ||
              (ch == '}' && stack[index] == '{') ||
              (ch == ']' && stack[index] == '['))) index--;
      else return false;
    }
    return index == -1;
  }

  public static void main(String[] args) {
    ValidParentheses_v2 validParenthesesV2 = new ValidParentheses_v2();
    String s = "([[)";
    System.out.println(validParenthesesV2.isValid(s));
  }
}
