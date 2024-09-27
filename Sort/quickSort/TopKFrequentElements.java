import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElements {
  public int[] topKFrequent(int[] nums, int k) {

    //freq map
    Map<Integer, Integer> map = new HashMap<>();
    for(int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    List<int[]> list = new ArrayList<>(); // num, freq
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      list.add(new int[] {entry.getKey(), entry.getValue()});
    }

    quickSelect(list, 0, list.size() - 1, k);
    int[] ret = new int[k];
    int idx = 0;
    for(int i = 0; i < k; i++) {
      ret[idx++] = list.get(i)[0];
    }
    return ret;
  }

  private void quickSelect(List<int[]>  list, int left, int right, int k) {
    int pivot = partition(list, left, right);
    if(pivot == k - 1) return;
    else if(pivot < k - 1){
      quickSelect(list, pivot + 1, right, k);
    } else quickSelect(list, left, pivot - 1, k);
  }

  private int partition(List<int[]> list, int left, int right) {
    int pivotFreq = list.get(right)[1];
    int j = left;
    for(int i = left; i < right; i++) {
      if(list.get(i)[1] > pivotFreq) {
        Collections.swap(list, i, j);
        j++;
      }
    }
    Collections.swap(list, j, right);
    return j;
  }

  public static void main(String[] args) {
    TopKFrequentElements topKFrequentElements = new TopKFrequentElements();
    int[] nums = {1,1,1,2,2,3};
    int k = 2;
    System.out.println(Arrays.toString(topKFrequentElements.topKFrequent(nums, k)));
  }
}
