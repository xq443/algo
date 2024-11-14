package Figma.VolunteerAssign;

import java.io.IOException;
import java.util.Map;

public class Runner {

  public static void main(String[] args) throws IOException {
    Map<Integer, Task> tasks = Util.loadTask("/Users/cathyqin/IdeaProjects/algo/Design/Figma.VolunteerAssign/task.csv");
    Map<Integer, Volunteer> volunteers = Util.loadVolunteer("/Users/cathyqin/IdeaProjects/algo/Design/Figma.VolunteerAssign/volunteer.csv", tasks);
    AssignmentServer server = new AssignmentServer(tasks, volunteers);
    server.compare();
  }
}
