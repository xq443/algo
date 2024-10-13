package Interval;

import java.util.Arrays;

public class MaximumProfitinJobScheduling {
  static class job {
    int startTime;
    int endTime;
    int profit;

    public job(int startTime, int endTime, int profit) {
      this.startTime = startTime;
      this.endTime = endTime;
      this.profit = profit;
    }
  }
  static job[] jobs;
  // dp[i] = max{dp[i - 1], val[i] + non-overlapping interval}; -> binary search
  public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
    int n = startTime.length;
    jobs = new job[n];

    int[] dp = new int[n + 1];
    for (int i = 0; i < n; i++) {
      int start = startTime[i];
      int end = endTime[i];
      int profits = profit[i];
      jobs[i] = new job(start, end, profits);
    }
    // sort based on the end time in asc order
    Arrays.sort(jobs, (a, b) -> (a.endTime - b.endTime));
    for(int i = 1; i <= n; i++) {
      int currProfit = jobs[i - 1].profit;
      int currStart = jobs[i - 1].startTime;
      dp[i] = Math.max(dp[i - 1], currProfit + dp[lastNonOverlapping(0, i - 1, currStart)]); // 0 to i -1 time, the last non-overlapping time that is less than current startTime
    }
    return dp[n];
  }

  public int lastNonOverlapping(int start, int end, int target) {
    while(start < end) {
      int mid = start + (end - start) / 2;
      if(jobs[mid].endTime > target) end = mid;
      else start = mid + 1;
    }
    return start;
  }

  public static void main(String[] args) {
    MaximumProfitinJobScheduling m = new MaximumProfitinJobScheduling();
    int[] startTime = {1, 2, 3, 4, 6};
    int[] endTime = {3, 5, 10, 6, 9};
    int[] profit = {20, 20, 100, 70, 60};
    System.out.println(m.jobScheduling(startTime, endTime, profit));
  }
}
