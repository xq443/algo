package Databricks.Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalRange {

  class Interval {
    int start; // Start
    int end; // End

    Interval(int s, int e) {
      this.start = s;
      this.end = e;
    }

    @Override
    public String toString() {
      return "(" + start + ", " + end + ")";
    }
  }

  // Function to remove elements within a specified index range
  public List<Interval> removeIntervalRange(List<Interval> list, int startIdx, int endIdx) throws Exception {
    List<Interval> res = new ArrayList<>();
    int cum = 0; // Accumulated number of covered elements
    int startElem = -1; // The starting element to remove
    int endElem = -1; // The ending element to remove

    // Find the starting and ending elements to remove by calculating cumulative size
    for (Interval interval : list) {
      int size = interval.end - interval.start + 1;
      if (cum + size > startIdx && startElem == -1) {
        startElem = interval.start + (startIdx - cum); // The starting element to be removed
      }
      if (cum + size > endIdx) {
        endElem = interval.start + (endIdx - cum); // The ending element to be removed
        break;
      }
      cum += size;
    }

    // If the elements to delete are not found, return the original intervals
    if (startElem == -1 || endElem == -1) {
      throw new Exception("index out of range");
    }

    // Adjust intervals and remove the specified range of elements
    for (Interval iv : list) {
      if (endElem < iv.start || startElem > iv.end) {
        // Range is not in this interval, so keep the interval as is
        res.add(iv);
      } else {
        // Range lies inside the interval
        if (startElem > iv.start) {
          res.add(new Interval(iv.start, startElem - 1)); // Part before the range
        }
        if (endElem < iv.end) {
          res.add(new Interval(endElem + 1, iv.end)); // Part after the range
        }
      }
    }

    return res;
  }

  // Function to print intervals
  public static void printIntervals(List<Interval> ivs) {
    List<String> strs = new ArrayList<>();
    for (Interval iv : ivs) {
      strs.add(iv.toString());
    }
    System.out.println(strs);
  }

  // Function to run a test case with a list of intervals and a given index range
  public void runTestCaseRange(List<Interval> intervals, int startIdx, int endIdx, String testCaseDescription)
      throws Exception {
    System.out.print(testCaseDescription + " output: ");
    List<Interval> result = removeIntervalRange(intervals, startIdx, endIdx);
    printIntervals(result);
  }

  public void main(String[] args) {
    try {
      // Test case 1: Example from the problem
      runTestCaseRange(Arrays.asList(new Interval(4, 7), new Interval(10, 11), new Interval(13, 15)), 1, 6,
          "Test case");

      // Test case 2: Remove from the start
      runTestCaseRange(Arrays.asList(new Interval(4, 7), new Interval(10, 11), new Interval(13, 15)), 0, 2, "Test case: Remove from start");
      // Expected output: [(7, 7), (10, 11), (13, 15)]

      // Test case 3: Remove from the end
      runTestCaseRange(Arrays.asList(new Interval(4, 7), new Interval(10, 11), new Interval(13, 15)), 5, 8, "Test case: Remove from end");
      // Expected output: [(4, 6), (13, 13)].

      // Test case 4: Remove spanning multiple intervals
      runTestCaseRange(Arrays.asList(new Interval(4, 7), new Interval(10, 11), new Interval(13, 15)), 2, 6, "Test case: Remove spanning multiple intervals");
      // Expected output: [(4, 5), (14, 15)] 4 5 6 7 10 11 13 14 15

      // Test case 5: Remove elements from single interval
      runTestCaseRange(Arrays.asList(new Interval(4, 7)), 1, 2, "Test case: Remove from single interval");
      // Expected output: [(4, 4), (7, 7)] 4 5 6 7

      // Test case 6: Remove elements that results in an empty interval
      runTestCaseRange(Arrays.asList(new Interval(4, 7)), 0, 3, "Test case: Remove resulting in empty interval");
      // Expected output: [] 4 5 6 7

      // Test case 7: Remove elements from an interval list with non-overlapping intervals
      runTestCaseRange(Arrays.asList(new Interval(1, 2), new Interval(4, 5), new Interval(7, 8)), 1, 4, "Test case: Remove from non-overlapping intervals");
      // Expected output: [(1, 1), (8, 8)] 1 2 4 5 7 8

      // Test case 8: Remove elements from an interval list with overlapping intervals
      runTestCaseRange(Arrays.asList(new Interval(1, 5), new Interval(4, 8)), 2, 6, "Test case: Remove from overlapping intervals");
      // Expected output: [(1, 1), (8, 8)] 1 2 3 4 5 6 7 8

      // Test case 9: Remove elements from an interval list with a single element interval
      runTestCaseRange(Arrays.asList(new Interval(4, 4), new Interval(6, 6)), 0, 1, "Test case: Remove from single element interval");
      // Expected output: [(6, 6)]

      // Test case 10: Remove elements with startIdx and endIdx out of range
      runTestCaseRange(Arrays.asList(new Interval(1, 3), new Interval(5, 7)), 10, 15, "Test case: Index out of range");
      // Expected output: Exception with message "index out of range"

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
