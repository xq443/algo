import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class letterCombinationsofaPhoneNumber {
    public List<String> letterCombinations(String digits){
        List<String> ret = new ArrayList<>();
        if(digits.length() == 0) return ret;

        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        StringBuilder currCombo = new StringBuilder();
        letterCombinationsofaPhoneNumber_dfs(ret, map, digits, currCombo, 0);
        return ret;
    }
    private void letterCombinationsofaPhoneNumber_dfs(List<String> ret, Map<Character, String> map, String digits, StringBuilder currCombo, int index){
        if(index == digits.length()){
            ret.add(currCombo.toString());
            return;
        }
        char ch = digits.charAt(index);
        String letters = map.get(ch);
        for(char letter : letters.toCharArray()){
            currCombo.append(letter);
            letterCombinationsofaPhoneNumber_dfs(ret, map, digits, currCombo, index+1);
            currCombo.deleteCharAt(currCombo.length() -1);
        }
    }
    public static void main(String[] args) {
        letterCombinationsofaPhoneNumber letterCombinationsofaPhoneNumber = new letterCombinationsofaPhoneNumber();
        String digits = "23";
        System.out.println(letterCombinationsofaPhoneNumber.letterCombinations(digits));
    }
}
//TC O(4^N)
//SC O(N)