import java.util.*;

public class MeetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return 0;
        // sort the arrays by the starting time in asc order
        Arrays.sort(intervals, (int[] a, int[] b) -> a[0] - b[0]);
        // set up a min heap
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (a - b));
        pq.offer(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            // no intersection
            if(intervals[i][0] >= pq.peek()) pq.poll();
            pq.offer(intervals[i][1]);
        }
        return pq.size();
    }

    public static void main(String[] args) {
        MeetingRoomsII meetingRoomsII = new MeetingRoomsII();
        int[][] intervals = {{0,30},{5,10},{15,20}};
        int ret =meetingRoomsII.minMeetingRooms(intervals);
        System.out.println("the min number of meeting room is " + ret);
    }
}