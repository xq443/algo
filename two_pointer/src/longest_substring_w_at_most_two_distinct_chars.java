import java.util.HashMap;
import java.util.Map;

public class longest_substring_w_at_most_two_distinct_chars {
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        //base case
        if(s.length() <= 2) return s.length();
        //define pointers
        int left = 0, right = 0;
        //define maxLen
        int res = 0;
        //define the hashmap
        HashMap<Character, Integer> hash_map = new HashMap<>();

        while(right < s.length()){
            hash_map.put(s.charAt(right), hash_map.getOrDefault(s.charAt(right),0) + 1);
            while(hash_map.size() > 2) {
                //contract the sliding window which doesn't meet the condition
                hash_map.put(s.charAt(left), hash_map.get(s.charAt(left)) - 1);
                if(hash_map.get(s.charAt(left)) == 0) {
                    hash_map.remove(s.charAt(left));
                }
                left++;
            }
            res = Math.max(res, (right - left + 1));
            right++;
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "eceba";
        System.out.println(lengthOfLongestSubstringTwoDistinct(s));
    }

}
//Input: s = "eceba"
//Output: 3