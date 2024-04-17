package Basic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TwoBestNonOverlappingEvents {

  /**
   * Given a 0-indexed 2D integer array of events where events[i] = [startTimei, endTimei, valuei].
   * The ith event starts at startTimei and ends at endTimei,
   * and if you attend this event, you will receive a value of valuei.
   * You can choose at most two non-overlapping events to attend such that the sum of their values is maximized.
   *
   * Return this maximum sum.
   * Note that  cannot attend two events where one of them starts and the other ends at the same time.
   * More specifically, if you attend an event with end time t, the next event must start at or after t + 1.
   * @param events
   * @return
   */
  public int maxTwoEvents(int[][] events) {
    // Arrays.sort(events, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
    Arrays.sort(events, Comparator.comparingInt(a -> a[0]));
    PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) ->(a[0] - b[0]));

    int maxValue = 0, ret = 0;
    for(int[] event : events) {
      // get ride details
      int start = event[0];
      int end = event[1];
      int value = event[2];
      while(!queue.isEmpty()) {
        if(start <= queue.peek()[0]) {
          break; // inclusively overlap
        }
        int[] curr = queue.poll();
        // update the curr maxvalue
        maxValue = Math.max(maxValue, curr[1]);
      }
      // add new element into the min queue, with end time in asc order
      queue.offer(new int[]{end, value});
      // update the accumulated value
      ret = Math.max(ret, maxValue + value);
    }
    return ret;
  }
//  public int maxTwoEvents(int[][] events) {
//    // sort the 2d array by the starting time
//    Arrays.sort(events, Comparator.comparingInt(a -> a[0]));
//    // treemap : key-> starting time, value-> maxvalue to retrieve the min key each time
//    TreeMap<Integer, Integer> map = new TreeMap<>();
//    map.put(0,0);
//
//    int prev = 0, ret = 0;
//
//    for(int[] event : events) {
//      int start = event[0];
//      int end = event[1];
//      int value = event[2];
//
//      // update the prev maxvalue (event start time)
//      while(!map.isEmpty() && map.firstKey() < start) { // non inclusive
//        prev = Math.max(prev, map.remove(map.firstKey()));
//      }
//
//      // update the curr time's maxvalue
//      ret = Math.max(ret, prev + value);
//
//      // update the maxvalue record for the event end time in the map
//      map.put(end, Math.max(map.getOrDefault(end, 0), value));
//    }
//    return ret;
//  }

  public static void main(String[] args) {
    TwoBestNonOverlappingEvents twoBestNonOverlappingEvents = new TwoBestNonOverlappingEvents();
    int[][] events = {{1,3,2},{4,5,2},{2,4,3}};
    int[][] events2 = {{1,5,3},{1,5,1},{6,6,5}};
    System.out.println(twoBestNonOverlappingEvents.maxTwoEvents(events));
    System.out.println(twoBestNonOverlappingEvents.maxTwoEvents(events2));
  }
}
