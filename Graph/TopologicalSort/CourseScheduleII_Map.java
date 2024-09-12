import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CourseScheduleII_Map {
  public int[] findOrder_Map(int numCourses, int[][] prerequisites) {
    // graph
    Map<Integer, List<Integer>> graph = new HashMap<>();
    Map<Integer, Integer> inDegree = new HashMap<>();

    for(int[] prerequisite : prerequisites) {
      int course = prerequisite[0];
      int pre = prerequisite[1];
      graph.computeIfAbsent(pre, k -> new ArrayList<>()).add(course);
      inDegree.put(course, inDegree.getOrDefault(course, 0) + 1);
    }

    int[] ret = new int[numCourses];
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if(!inDegree.containsKey(i)) {
        queue.add(i);
      }
    }
    int idx = 0;
    while (!queue.isEmpty()) {
      int curr = queue.poll();
      ret[idx++] = curr;
      if (graph.containsKey(curr)) {
        for(int next : graph.get(curr)) {
          inDegree.put(next, inDegree.get(next) - 1);
          if (inDegree.get(next) == 0) {
            queue.add(next);
          }
        }
      }
    }
    if(idx == numCourses) return ret;
    return new int[0];
  }

  public static void main(String[] args) {
    CourseScheduleII_Map courseScheduleIIMap = new CourseScheduleII_Map();
    int numCourses = 4;
    int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
    System.out.println(
        Arrays.toString(courseScheduleIIMap.findOrder_Map(numCourses, prerequisites)));
  }
}
