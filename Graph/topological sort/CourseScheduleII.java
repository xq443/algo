import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseScheduleII {

  public int[] findOrder(int numCourses, int[][] prerequisites) {
    //build graph
    List<List<Integer>> graph = new ArrayList<>();
    int[] inDegree = new int[numCourses];

    for (int i = 0; i < numCourses; i++) {
      graph.add(new ArrayList<>());
    }

    for (int[] prerequisite : prerequisites) {
      int pre = prerequisite[1];
      int course = prerequisite[0];
      graph.get(pre).add(course);
      inDegree[course]++;
    }

    int[] ret = new int[numCourses];
    int idx = 0;
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if (inDegree[i] == 0) {
        queue.add(i);
        ret[idx++] = i;
      }
    }
    while (!queue.isEmpty()) {
      int curr = queue.poll();
      ret[idx++] = curr;
      for (int next : graph.get(curr)) {
        inDegree[next]--;
        if (inDegree[next] == 0) {
          queue.add(next);
        }
      }
    }
    if (idx == numCourses) return ret;
    return new int[0];
  }

  public static void main(String[] args) {
    CourseScheduleII courseScheduleII = new CourseScheduleII();
    int numCourses = 4;
    int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
    int[] ret = courseScheduleII.findOrder(numCourses, prerequisites);
    for (int i = 0; i < ret.length; i++) {
      System.out.println(ret[i]);
    }
  }
}
