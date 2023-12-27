public class kthSmallestElementinaSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int left = matrix[0][0];
        int right = matrix[matrix.length -1][matrix[0].length - 1];
        int ret = -1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(countEqualOrLess(matrix, mid) >= k){
                ret = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return ret;
    }
    private int countEqualOrLess(int[][] matrix, int mid){
        int col = matrix[0].length - 1;
        int cnt = 0;
        for(int row = 0; row < matrix.length; row++){
            while(col >= 0 && matrix[row][col] > mid) col--;
            cnt += (col + 1);
        }
        return cnt;
    }
    public static void main(String[] args) {
        kthSmallestElementinaSortedMatrix k = new kthSmallestElementinaSortedMatrix();
        int [][] matrix = {{1,5,9},{10,11,13},{12,13,15}};
        int m = 8;
        System.out.println(k.kthSmallest(matrix, m));
    }
}
//Time: O((M+N) * logD), where M <= 300 is the number of rows, N <= 300 is the number of columns, D <= 2*10^9 is the difference between the maximum element and the minimum element in the matrix.
//Space: O(1)
