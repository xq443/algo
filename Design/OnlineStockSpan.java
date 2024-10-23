import java.util.Stack;

public class OnlineStockSpan {
  Stack<int[]> stack;
  /**
   * Initializes the object of the class.
   */
  public OnlineStockSpan() { this.stack = new Stack<>(); }

  /**
   * Returns the span of the stock's price given that today's price is price.
   * @param price
   * @return
   */
  public int next(int price) {
    int ret = 1;
    while (!stack.isEmpty() && stack.peek()[0] <= price) {
      ret += stack.pop()[1];
    }
    stack.push(new int[] {price, ret});
    return ret;
  }

  public static void main(String[] args) {
    OnlineStockSpan onlineStockSpan = new OnlineStockSpan();
    System.out.println(onlineStockSpan.next(100)); // 1
    System.out.println(onlineStockSpan.next(80));  // 1
    System.out.println(onlineStockSpan.next(75));  // 1
    System.out.println(onlineStockSpan.next(85));  // 3
  }
}
