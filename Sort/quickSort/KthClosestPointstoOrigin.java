import java.util.Arrays;
import java.util.PriorityQueue;

public class KthClosestPointstoOrigin {
    public int[][] kClosest(int[][] points, int k) {
        //put the dis to the origin to the pq in asc order
        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (a,b)->(a[0] * a[0] + a[1] * a[1] - b[0] * b[0] - b[1] * b[1]));

        //insert into queue
        for(int[] point : points){
            queue.offer(point);
        }
        //print out the closest dis to the origin
        int[][] ret = new int[k][2];
        int idx = 0;
        while (idx < k){
            ret[idx++] = queue.poll();
        }
        return  ret;
    }
    public static void main(String[] args) {
        KthClosestPointstoOrigin k = new KthClosestPointstoOrigin();
        int[][] test = {{3,3},{5,-1},{-2,4}};
        int m = 2;
        System.out.println(Arrays.deepToString(k.kClosest(test, m)));
    }
}
