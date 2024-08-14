import java.io.IOException;
import java.util.PriorityQueue;

public class KthLargestElementinanArray {
  public int findKthLargest(int[] nums, int k) throws IOException {
    if(nums == null || nums.length == 0 || k <= 0) {
      throw new IOException("no kth largest element available");
    }
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
    for (int i = 0; i < nums.length; i++) {
      pq.offer(nums[i]);
    }

    int ret = Integer.MIN_VALUE;
    while(k > 0) {
      ret = pq.poll();
      k--;
    }
    return ret;
  }

  public static void main(String[] args) throws IOException {
    KthLargestElementinanArray kthLargestElementinanArray = new KthLargestElementinanArray();
    int[] nums = {3,2,1,5,6,4,4};
    int k = 4;
    System.out.println(kthLargestElementinanArray.findKthLargest(nums, k));
  }
}
