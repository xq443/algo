/**
 * Input: s = "00110011"
 * Output: 6
 * Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
 * Notice that some of these substrings repeat and are counted the number of times they occur.
 * Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
 */
public class count_binary_substrings {
    public static int countBinarySubstrings(String s) {
        int cur = 1, prev = 0, sum = 0;

        for (int i = 1; i < s.length(); i++) {
            if(s.charAt(i) != s.charAt(i-1)) {
                sum += Math.min(prev, cur);
                prev = cur;
                cur = 1;
            } else {
                cur++;
            }
        }
        return sum+Math.min(prev,cur);
    }
    public static void main (String[] args) {
        String s = "00110011";
        int res = countBinarySubstrings(s);

        System.out.println(res);
    }
}
