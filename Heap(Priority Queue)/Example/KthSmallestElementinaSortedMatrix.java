import java.io.IOException;
import java.util.PriorityQueue;

public class KthSmallestElementinaSortedMatrix {
  public int kthSmallest(int[][] matrix, int k) throws IOException {
    if(matrix == null || matrix.length == 0 || k <= 0) {
      throw new IOException("no kth smallest element available.");
    }
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (a - b));
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        pq.offer(matrix[i][j]);
      }
    }
    int ret = Integer.MAX_VALUE;
    while(k > 0) {
      ret = pq.poll();
      k--;
    }
    return ret;
  }

  public static void main(String[] args) throws IOException {
    KthSmallestElementinaSortedMatrix kthSmallestElementinaSortedMatrix = new KthSmallestElementinaSortedMatrix();
    int[][] matrix = {{1,5,9}, {10,11,13},{12,13,15}};
    int k = 8;
    System.out.println(kthSmallestElementinaSortedMatrix.kthSmallest(matrix, k));
  }
}
