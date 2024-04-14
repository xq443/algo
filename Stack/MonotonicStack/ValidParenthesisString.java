package MonotonicStack;

import java.util.Stack;

public class ValidParenthesisString{

  /**
   * Given a string s containing only three types of characters: '(', ')' and '*',
   * return true if s is valid.
   *
   * The following rules define a valid string:
   *
   * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
   * Any right parenthesis ')' must have a corresponding left parenthesis '('.
   * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
   * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
   * @param s
   * @return boolean
   */
  public boolean checkValidString(String s) {
    if(s == null || s.length() == 0) return  false;
    int max = 0, min = 0;
    for(char ch : s.toCharArray()) {
      if(ch == '(') {
        max++;
        min++;
      } else if(ch == '*') {
        max++;
        min--;
      } else{
        max--;
        min--;
      }
    }
    if(max < 0) return  false;
    min = Math.max(min, 0);
    return  min == 0;
  }
  //  public boolean checkValidString(String s) {
  //    if(s == null || s.length() == 0) return false;
  //    Stack<Character> left = new Stack<>();
  //    Stack<Character> asterisk = new Stack<>();
  //    for(char ch : s.toCharArray()) {
  //      if(ch == '(') {
  //        left.push(ch);
  //      } else if(ch == '*') {
  //        asterisk.push(ch);
  //      } else{
  //        if(!left.isEmpty()) left.pop();
  //        else {
  //          if(!asterisk.isEmpty()) asterisk.pop();
  //          else return false;
  //        }
  //      }
  //    }
  //    while(!left.isEmpty()) {
  //      if(!asterisk.isEmpty() && left.peek() < asterisk.peek()) {
  //        left.pop();
  //        asterisk.pop();
  //      } else{
  //        return false;
  //      }
  //    }
  //    return true;
  //  }

  public static void main(String[] args) {
    ValidParenthesisString validParenthesisString = new ValidParenthesisString();
    String s = "*())";
    System.out.println(validParenthesisString.checkValidString(s));
  }
}
