import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseScheduleII {

  public int[] findOrder(int numCourses, int[][] prerequisites) {
    List<List<Integer>> adjList = new ArrayList<>();
    int[] inDegree = new int[numCourses];
    //build up graph
    for (int i = 0; i < numCourses; i++) {
      adjList.add(new ArrayList<>());
    }
    for(int[] prerequisite : prerequisites) {
      int course = prerequisite[0];
      int prerequisiteCourse = prerequisite[1];
      adjList.get(prerequisiteCourse).add(course);
      inDegree[course]++;
    }
    // bfs
    Queue<Integer> queue = new LinkedList<>();
    int[] ret = new int[numCourses];
    for (int i = 0; i < numCourses; i++) {
      if(inDegree[i] == 0) {
        queue.add(i);
      }
    }
    int idx = 0;
    //topological sort
    while(!queue.isEmpty()){
      int curr = queue.poll();
      ret[idx++] = curr;
      for(int next : adjList.get(curr)) {
        inDegree[next]--;
        if(inDegree[next] == 0){
          queue.add(next);
        }
      }
    }
    if(idx == numCourses) return ret;
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
