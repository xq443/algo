import java.util.HashSet;
import java.util.Set;

public class RobotRoomCleaner {
  interface Robot {

    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
    public boolean move();

    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    public void turnLeft();
    public void turnRight();

    // Clean the current cell.
    public void clean();
 }
  public void cleanRoom(Robot robot) {
    Set<String> visited = new HashSet<>();
    dfs(robot, 0, 0, 0, visited);
  }

  private void dfs(Robot robot, int x, int y, int dir, Set<String> visited) {
    robot.clean();
    visited.add(x + "," + y);

    int[][] direction = {{0,1},{1,0},{0,-1},{-1,0}}; // up left down right

    for (int i = 0; i < direction.length; i++) {
      int x_delta = x + direction[dir][0];
      int y_delta = y + direction[dir][1];

      String new_position = x_delta + "," + y_delta;
      if(!visited.contains(new_position) && robot.move()) {
        dfs(robot, x_delta, y_delta, dir, visited);
        goback(robot);
      }

      dir = (dir + 1) % 4;
      robot.turnLeft();
    }
  }
  private void goback(Robot robot) {
    robot.turnLeft();
    robot.turnLeft();
    robot.move();
    robot.turnRight();
    robot.turnRight();
  }
}
