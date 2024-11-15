package Databricks.Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Intervals {

  static class Interval {
    int start; // Start
    int end; // End

    Interval(int s, int e) {
      this.start = s;
      this.end = e;
    }

    @Override
    public String toString() {
      return "{" +
          start +
          "," +  end +
          '}';
    }
  }

  // first try to find where is the element with the given index
  // Function to remove a covered element based on the given index
  public List<Interval> removeCoveredElement(List<Interval> list, int idx) throws Exception {

    List<Interval> res = new ArrayList<>();
    int curr = 0; // Accumulated number of covered elements
    int elem = -1; // The element to remove

    // Find the element to remove by calculating cumulative size
    for (Interval interval : list) {
      int size = interval.end - interval.start + 1;
      if (curr + size > idx) {
        elem = interval.start + (idx - curr); // The element to be removed
        break;
      }
      curr += size; // update by incrementing its corresponding size
    }

    // If the element to delete is not found, return the original intervals
    if (elem == -1) {
      throw new Exception("index is out of bounds");
      //return list;
    }

    // Adjust intervals and remove the specified element
    for (Interval iv : list) {
      if (elem < iv.start || elem > iv.end) {
        // Element is not in this interval, so keep the interval as is
        res.add(iv);
      } else {
        // Element lies inside the interval
        if (iv.start == iv.end) {
          // Single element interval, remove it (skip)
          continue;
        }
        if (elem == iv.start) {
          // Delete the starting element, adjust start
          res.add(new Interval(iv.start + 1, iv.end));
        } else if (elem == iv.end) {
          // Delete the ending element, adjust end
          res.add(new Interval(iv.start, iv.end - 1));
        } else {
          // Delete a middle element, split into two intervals
          res.add(new Interval(iv.start, elem - 1)); // First part of the split
          res.add(new Interval(elem + 1, iv.end));   // Second part of the split
        }
      }
    }

    // Merge overlapping intervals
    return mergeIntervals(res);
  }

  // Function to merge overlapping intervals
  public List<Interval> mergeIntervals(List<Interval> ivs) {
    if (ivs.isEmpty())
      return ivs;

    // Sort intervals by start time
    Collections.sort(ivs, Comparator.comparingInt(a -> a.start));

    List<Interval> merged = new ArrayList<>();
    Interval prev = ivs.get(0);

    for (int i = 1; i < ivs.size(); i++) {
      Interval current = ivs.get(i);
      if (current.start <= prev.end + 1) { // Can be merged
        prev.end = Math.max(prev.end, current.end);
      } else {
        merged.add(prev);
        prev = current;
      }
    }
    merged.add(prev);

    return merged;
  }

  // Function to print intervals
  public void printIntervals(List<Interval> ivs) {
    List<String> strs = new ArrayList<>();
    for (Interval iv : ivs) {
      strs.add(iv.toString());
    }
    System.out.println(strs);
  }

  // Function to run a test case with a list of intervals and a given index
  public void runTestCase(List<Interval> intervals, int index, String testCaseDescription)
      throws Exception {
    System.out.print(testCaseDescription + " output: ");
    List<Interval> result = removeCoveredElement(intervals, index);
    printIntervals(result);
  }

  public void main(String[] args) {
    try {

      runTestCase(Arrays.asList(new Interval(7, 7)),  1, "Test case 0");
      // Test case 1: Example from the problem
//      runTestCase(Arrays.asList(new Interval(4, 7), new Interval(10, 11), new Interval(13, 15)), 2,
//          "Test case 1");
//
//      // Test case 2: Remove the first element
//      runTestCase(List.of(new Interval(1, 3)), 0, "Test case 2");
//
//      // Test case 3: Remove the last element
//      runTestCase(List.of(new Interval(1, 3)), 2, "Test case 3");
//
//      // Test case 4: Remove a middle element, causing interval split
//      runTestCase(List.of(new Interval(1, 3)), 1, "Test case 4");
//
//      // Test case 5: Remove from multiple intervals
//      runTestCase(Arrays.asList(new Interval(1, 1), new Interval(3, 3)), 0, "Test case 5");
//
//      // Test case 6: Single interval, remove middle element
//      runTestCase(List.of(new Interval(4, 7)), 2, "Test case 6");
//
//      // Test case 7: Empty interval list
//      runTestCase(new ArrayList<>(), 0, "Test case 7");
//
//      // Test case 8: Remove element from a single element interval
//      runTestCase(List.of(new Interval(5, 5)), 0, "Test case 8");
//
//      // Test case 9: Remove elements from an interval list with overlapping intervals
//      runTestCase(Arrays.asList(new Interval(1, 5), new Interval(3, 10)), 2, "Test case: Remove from overlapping intervals");
//      // Expected output: [(1, 2), (4, 10)]
//
//      // Test case 10: Index out of range
//      runTestCase(Arrays.asList(new Interval(2, 4), new Interval(6, 8)), 10, "Test case 10");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
/**
 * removeCoveredElement Method
 *
 * Time Complexity
 *
 * Finding the Element to Remove:
 *
 * The loop iterates through all intervals to find the element to remove. This takes (O(n)) time, where (n) is the number of intervals.
 * Adjusting Intervals:
 *
 * The second loop iterates through all intervals to adjust them based on the element to remove. This also takes (O(n)) time.
 * Merging Intervals:
 *
 * The mergeIntervals method is called, which sorts the intervals and then merges them. Sorting takes (O(n \log n)) time, and merging takes (O(n)) time.
 * Combining these steps, the overall time complexity of the removeCoveredElement method is (O(n \log n)).
 *
 * Space Complexity
 * The space complexity is dominated by the space required to store the intervals. The additional space used for the result list and temporary variables is (O(n)).
 * mergeIntervals Method
 * Time Complexity
 * Sorting:
 *
 * Sorting the intervals by their start time takes (O(n \log n)) time.
 * Merging:
 *
 * The loop iterates through all intervals to merge them. This takes (O(n)) time.
 * Combining these steps, the overall time complexity of the mergeIntervals method is (O(n \log n)).
 *
 * Space Complexity
 * The space complexity is (O(n)) for storing the merged intervals.
 * Summary
 * Time Complexity:
 *
 * removeCoveredElement: (O(n \log n))
 * mergeIntervals: (O(n \log n))
 * Space Complexity:
 *
 * Both methods have a space complexity of (O(n)).
 */