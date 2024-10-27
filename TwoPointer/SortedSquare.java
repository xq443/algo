import java.util.Arrays;

public class SortedSquare {
  public int[] sortedSquares(int[] A) {
    int[] ret = new int[A.length];
    int left = 0, right = A.length - 1;
    for(int i = A.length - 1; i >= 0; i--) {
      if(Math.abs(A[left]) > Math.abs(A[right])) {
        ret[i] = A[left] * A[left];
        left++;
      } else {
        ret[i] = A[right] * A[right];
        right--;
      }
    }
    return ret;
  }
  public static void main(String[] args) {
    SortedSquare sol = new SortedSquare();
    int[] test = {-7, -3, 0, 2, 5};
    System.out.println(Arrays.toString(sol.sortedSquares(test)));
  }
}
