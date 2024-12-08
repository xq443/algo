package Basic;

public class LongestIncreasingSubsequence_binarysearch {
  public int lengthOfLIS(int[] nums){
    if(nums == null || nums.length == 0) return 0;
    int n = nums.length;
    int[] ret = new int[n];
    ret[0] = nums[0];

    int len = 0;
    for(int i = 1; i < n; i++){
      int pos = binarysearch(ret, len, nums[i]);
      ret[pos] = nums[i];
      if(pos > len) {
        len = pos;
      }
    }
    return len + 1;
  }

  public int binarysearch(int[] ret, int len, int value){
    int left = 0, right = len;
    while(left <= right){
      int mid = left + (right - left) / 2;
      if(ret[mid] == value) return mid;
      else if(ret[mid] < value) left = mid + 1;
      else right = mid - 1;
    }
    return left;
  }

  public static void main(String[] args) {
    LongestIncreasingSubsequence_binarysearch lis = new LongestIncreasingSubsequence_binarysearch();
    int [] nums = {10,9,2,5,3,7,101,18};
    int [] nums_1 = {0,1,0,3,2,3};
    int [] nums_2 = {7,7,7,7,7,7,7};
    System.out.println(lis.lengthOfLIS(nums));
    System.out.println(lis.lengthOfLIS(nums_1));
    System.out.println(lis.lengthOfLIS(nums_2));
  }

}
