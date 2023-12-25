import java.util.Arrays;

public class KthClosestPointstoOrigin_v2 {
    public int[][] kClosest(int[][] points, int k) {
        int left = 0;
        int right = points.length - 1;
        while(left < right){
            int pivotIdx = partition(points, left, right);
            if(pivotIdx == k - 1) break;
            else if(pivotIdx > k - 1) right = pivotIdx - 1;
            else left = pivotIdx + 1;
        }
        return Arrays.copyOfRange(points, 0, k);
    }
    private int partition(int[][] points, int left, int right){
        //int[] pivot = points[right];
        int pivot = getDistance(points[right]);
        int i = left;
        for(int j = left; j < right; j++){
            if(getDistance(points[j]) < pivot){
                kClosestSwap(points, i, j);
                i++;
            }
        }
        kClosestSwap(points, i, right);
        return i;
    }
    private void kClosestSwap(int[][] points, int i, int j){
        int [] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }
    private int getDistance(int[] points){
        return points[0] * points[0] + points[1] * points[1];
    }
    public static void main(String[] args) {
        KthClosestPointstoOrigin_v2 k = new KthClosestPointstoOrigin_v2();
        int[][] test = {{3,3},{5,-1},{-2,4}};
        int m = 2;
        System.out.println(Arrays.deepToString(k.kClosest(test, m)));
    }
}
