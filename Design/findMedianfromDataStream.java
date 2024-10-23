import java.util.Collections;
import java.util.PriorityQueue;

public class findMedianfromDataStream {
  /**
   * Input
   * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
   * [[], [1], [2], [], [3], []]
   * Output
   * [null, null, null, 1.5, null, 2.0]
   */
  PriorityQueue<Integer> small;
  PriorityQueue<Integer> large;
  public findMedianfromDataStream() {
    small = new PriorityQueue<>(Collections.reverseOrder());
    large = new PriorityQueue<>();
  }
  public void addNum(int num) {
    small.offer(num);
    large.offer(small.poll());
    if (large.size() > small.size()) {
      small.offer(large.poll());
    }
  }
  public double findMedian() {
    if (small.size() == large.size()) {
      return (small.peek() + large.peek()) / 2.0;
    } else {
      return small.peek();
    }
  }
  public static void main(String[] args) {
    findMedianfromDataStream findMedianfromDataStream =
        new findMedianfromDataStream();
    findMedianfromDataStream.addNum(1);
    findMedianfromDataStream.addNum(2);
    System.out.println(findMedianfromDataStream.findMedian());
    findMedianfromDataStream.addNum(3);
    System.out.println(findMedianfromDataStream.findMedian());
  }
}
// TC: O(nlogn)
// SC: O(n)
