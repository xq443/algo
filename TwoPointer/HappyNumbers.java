public class HappyNumbers {
  public boolean isHappy(int n) {
    int slow = n;
    int fast = getNumberofSquare(n);
    while (fast != 1 && slow != fast) {
      slow = getNumberofSquare(slow);
      fast = getNumberofSquare(getNumberofSquare(fast));
    }

    return fast == 1;
  }
  private int getNumberofSquare(int m) {
    // 19
    int sum = 0;
    while(m != 0) {
      int digit = m % 10; // 9 1
      sum += digit * digit; // 9 * 9 + 1 * 1
      m /= 10; // 1 0
    }
    return sum;
  }

  public static void main(String[] args) {
    HappyNumbers happyNumbers = new HappyNumbers();
    int n1 = 19;
    int n2 = 2;
    System.out.println(happyNumbers.isHappy(n1));
    System.out.println(happyNumbers.isHappy(n2));
  }
}
