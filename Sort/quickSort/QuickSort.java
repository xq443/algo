public class QuickSort {

  /**
   * Given an array of integers nums, sort the array in ascending order and return it.
   *
   * You must solve the problem without using any built-in functions in O(nlog(n)) time complexity
   * and with the smallest space complexity possible.
   * @param arr int[]
   * @return int[]
   */
//  public void quickSort(int[] arr) {
//    if (arr == null || arr.length == 0) return;
//    quickSort(arr, 0, arr.length - 1);
//  }
//
//  private void quickSort(int[] arr, int low, int high) {
//    if (low < high) {
//      int pi = partition(arr, low, high);
//      quickSort(arr, low, pi - 1);
//      quickSort(arr, pi + 1, high);
//    }
//  }

//  private int[] partition(int[] arr, int low, int high) {
//    int pivot = arr[low];
//    int i = high;
//    for (int j = low; j < high; j++) {
//      if (arr[j] < pivot) {
//        i--;
//        swap(arr, i, j);
//      }
//    }
//    swap(arr, i + 1, high);
//    return arr;
//  }

  private int[] partition (int arr[], int low, int high) {
    // first element as pivot
    int pivot = arr[low];
    int k = high;
    for (int i = high; i > low; i--) {
      if (arr[i] > pivot){
        swap (arr, i, k);
        k--;
      }
    }
    swap(arr, k, low);
    return arr;
  }

  private void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void main(String[] args) {
    QuickSort quickSort = new QuickSort();
    int[] arr = {822, 35, 294, 322, 175, 67, 517, 987, 154, 473};
    quickSort.partition(arr, 0, arr.length - 1);
    for (int num : arr) {
      System.out.print(num + " ");
    }
  }
}
// O(NlogN) O(N)
