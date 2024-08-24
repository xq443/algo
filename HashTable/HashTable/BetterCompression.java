package HashTable;
public class BetterCompression {
  public String betterCompression(String s) {
    long[] count = new long[200]; // frequency array
    int n = s.length();
    for (int i = 0; i < n; i++) {
      char ch = s.charAt(i);
      if(ch >= 'a' && ch <= 'z') {
        // extract the following number
        int j = i + 1;
        long curr = 0;
        while (j < n && s.charAt(j) >= '0' && s.charAt(j) <= '9') {
          curr = curr * 10 + s.charAt(j) - '0';
          j++;
        }
        i = j - 1;
        count[ch] += curr;
      }
    }
    StringBuilder ret = new StringBuilder();
    for (int i = 'a'; i <= 'z'; i++) {
      if(count[i] == 0) continue;
      ret.append((char)i);
      ret.append(count[i]);
    }
    return ret.toString();
  }

  public static void main(String[] args) {
    BetterCompression betterCompression = new BetterCompression();
    String s = "a12b2c44a1";
    System.out.println(betterCompression.betterCompression(s));
  }
}
