import java.util.HashSet;

public class longest_substring_without_repeating_characters {
    public static int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0;
        int res = 0;
        HashSet<Character> hash_set = new HashSet<>();
        while (right < s.length()){
            if(!hash_set.contains(s.charAt(right))) {
                hash_set.add(s.charAt(right));
                right++;
                res = Math.max(res, hash_set.size());
            } else {
                hash_set.remove(s.charAt(left++));
            }
        }
        return res;
    }
    public static void main(String[] args) {
        String test = "abdccab";
        System.out.println(lengthOfLongestSubstring(test));
    }
}
// s = "abcabcbb"
// 3