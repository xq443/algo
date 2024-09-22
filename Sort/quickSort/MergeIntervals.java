import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
  public int[][] merge(int[][] intervals) {
    if(intervals.length <= 1) return intervals;
    Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
    List<int[]> list = new ArrayList<>();
    list.add(intervals[0]);

    for(int i = 1; i < intervals.length; i++) {
      int[] retInterval = list.get(list.size() - 1);
      if(retInterval[1] > intervals[i][0]) {
        retInterval[1] = Math.max(retInterval[1], intervals[i][1]);
      } else list.add(intervals[i]);
    }

    return list.toArray(new int[list.size()][2]);
  }

  public static void main(String[] args) {
    MergeIntervals mergeIntervals = new MergeIntervals();
    int[][] intervals = {
        {1, 3},
        {2, 6},
        {8, 10},
        {15, 18}
    };
    // Merge the intervals
    int[][] mergedIntervals = mergeIntervals.merge(intervals);
    for (int[] interval : mergedIntervals) {
      System.out.println(Arrays.toString(interval));
    }
  }
}

