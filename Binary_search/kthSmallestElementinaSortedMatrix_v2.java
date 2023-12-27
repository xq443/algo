import java.util.PriorityQueue;

public class kthSmallestElementinaSortedMatrix_v2 {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
                (o1, o2) -> (o1-o2)
        );
        for(int row = 0; row < matrix.length; row++){
            for(int col = 0; col < matrix[0].length; col++){
                minHeap.offer(matrix[row][col]);
            }
        }
        for(int i = 0; i < k - 1; i++){
            minHeap.poll();
        }
        return minHeap.poll();
    }
    public static void main(String[] args) {
        kthSmallestElementinaSortedMatrix_v2 k = new kthSmallestElementinaSortedMatrix_v2();
        int [][] matrix = {{1,5,9},{10,11,13},{12,13,15}};
        int m = 8;
        System.out.println(k.kthSmallest(matrix, m));
    }
}
