public class MaximumValueataGivenIndexinaBoundedArray {
  public int maxValue(int n, int index, int maxSum) {
    int left = 0, right = maxSum;
    while(left < right) {
      int mid = right - (right - left) / 2;
      if(testSum(mid, index, n) <= maxSum) {
        left = mid;
      } else right = mid - 1;
    }
    return left;
  }

  // sum of the elements in the array
  private long testSum(int h, int index, int n) {
    long sum = 0;
    if (h > index) { // no 1 in the left side
      sum += (long) (h + h - index) * (index + 1) / 2;
    } else { // 1 1 2 3 4 3
      sum += (long) (1 + h) * h / 2;
      sum += index + 1 - h;
    }

    if (h > n - index) { // no 1 in the right side: 1 1 2 3 4 3 2
      sum += (long) (h + h - (n - (index + 1))) * (n - index) / 2;
    } else { // 2 3 4 3 2 1 1
      sum += (long) (h + 1) * h / 2;
      sum += (n - (index + h));
    }
    return sum - h;
  }

  public static void main(String[] args) {
    MaximumValueataGivenIndexinaBoundedArray maximumValueataGivenIndexinaBoundedArray = new MaximumValueataGivenIndexinaBoundedArray();
    int n = 4, index = 2,  maxSum = 6;
    System.out.println(maximumValueataGivenIndexinaBoundedArray.maxValue(n, index, maxSum));
  }
}
