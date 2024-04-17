package Basic;

public class DecodeWays {

  /**
   * A message containing letters from A-Z can be encoded into numbers using the following mapping:
   *
   * 'A' -> "1"
   * 'B' -> "2"
   * ...
   * 'Z' -> "26"
   * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
   *
   * "AAJF" with the grouping (1 1 10 6)
   * "KJF" with the grouping (11 10 6)
   * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
   *
   * Given a string s containing only digits, return the number of ways to decode it.
   * The test cases are generated so that the answer fits in a 32-bit integer.
   * @param s
   * @return
   */
  public int numDecodings(String s) {
    int[] dp = new int[s.length() + 1];
    dp[0] = 1; // 1 way to decode an empty string
    for (int i = 0; i < s.length(); i++) {
      // 1 chars to decode
      if(s.charAt(i) != '0') dp[i + 1] += dp[i];
      // 2 chars to decode
      if(i < s.length() - 1) { // i + 2 < s.length() + 1
        if(s.charAt(i) == '1' ||
            (s.charAt(i) == '2' && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '6')) {
          dp[i + 2] += dp[i];
        }
      }
    }
    return dp[s.length()];
  }

  public static void main(String[] args) {
    DecodeWays decodeWays = new DecodeWays();
    String s1 = "12";
    System.out.println(decodeWays.numDecodings(s1));
    String s2 = "226";
    System.out.println(decodeWays.numDecodings(s2));
  }
}
