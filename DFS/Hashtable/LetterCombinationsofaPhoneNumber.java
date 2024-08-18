import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsofaPhoneNumber {
  public List<String> letterCombinations(String digits) {
    if(digits == null || digits.length() == 0) return new ArrayList<>();
    List<String> ret = new ArrayList<>();

    Map<Character, String> map = new HashMap<>();
    map.put('2', "abc");
    map.put('3', "def");
    map.put('4', "ghi");
    map.put('5', "jkl");
    map.put('6', "mno");
    map.put('7', "pqrs");
    map.put('8', "tuv");
    map.put('9', "wxyz");

    phonenumber_dfs(ret, new StringBuilder(), 0, digits, map);
    return ret;
  }

  private void phonenumber_dfs(List<String> ret, StringBuilder sub, int position, String digits,Map<Character, String> map)  {
    if(position == digits.length()) {
      ret.add(sub.toString());
      return;
    }

    char digit = digits.charAt(position);
    String mappingString = map.get(digit);

    for(int i = 0; i < mappingString.length(); i++) {
      sub.append(mappingString.charAt(i));
      phonenumber_dfs(ret, sub, position + 1, digits, map);
      sub.setLength(sub.length() - 1);
    }
  }

  public static void main(String[] args) {
    LetterCombinationsofaPhoneNumber letterCombinationsofaPhoneNumber = new LetterCombinationsofaPhoneNumber();
    String digits = "2";
    System.out.println(letterCombinationsofaPhoneNumber.letterCombinations(digits));
  }
}

// TC O(4^N) SC O(N)
