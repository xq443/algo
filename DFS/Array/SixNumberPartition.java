package Array;


/**
 * Given a string, verify it can be segmented into 6 numbers
 * the first five is in [1,69]，last one in range [1, 25]
 */

public class SixNumberPartition {
  public boolean canPartitionToSixNumbers(String s) {
    return canPartitionToSixNumbersHelper(s, 0, 0);
  }

  public boolean canPartitionToSixNumbersHelper(String s, int index, int count) {
    if(count == 5) {
      String last = s.substring(index); // parse the string from the current index to the end
      return isValidString(last, 1, 25);
    }
    for(int len = 1; len <= 2; len++) {
      if(index + len > s.length()) break;
      String num = s.substring(index, index + len);
      if(isValidString(num, 1, 69)) {
        if(canPartitionToSixNumbersHelper(s, index + len, count + 1)) return true;
      }
    }
    return false;
  }

  public boolean isValidString(String str, int min, int max) {
    if(str.startsWith("0")) return false;
    int num = Integer.parseInt(str);
    return num >= min && num <= max;
  }

  public static void main(String[] args) {
    SixNumberPartition sixNumberPartition = new SixNumberPartition();
    String test1 = "123456712";
    System.out.println(sixNumberPartition.canPartitionToSixNumbers(test1));
  }
}
