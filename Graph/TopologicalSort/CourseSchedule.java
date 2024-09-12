import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    List<List<Integer>> adjList = new ArrayList<>();
    int[] inDegree = new int[numCourses];

    // build up graph
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
    int count = 0;

    for (int i = 0; i < numCourses; i++) {
      if(inDegree[i] == 0) {
        queue.add(i);
        count++;
      }
    }
    // perform topological sort
    while(!queue.isEmpty()){
      int curr = queue.poll();
      for(int next : adjList.get(curr)) {
        inDegree[next]--;
        if(inDegree[next] == 0){
          queue.add(next);
          count++;
        }
      }
    }
    return count == numCourses;
  }
  public static void main(String[] args) {
    CourseSchedule courseSchedule = new CourseSchedule();
    int numCourses = 2;
    int[][] prerequisites = {{1,0},{0,1}};
    System.out.println(courseSchedule.canFinish(numCourses, prerequisites));
  }
}
// tc :  O(V + E) sc: O(V + E)
// where V is the number of vertices (courses) and E is the number of edges (prerequisites).


