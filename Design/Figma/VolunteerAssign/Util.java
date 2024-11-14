package Figma.VolunteerAssign;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Util {

  /**
   * load tasks from CSV files
   */

  public static Map<Integer, Task> loadTask(String filename) {
    Map<Integer, Task> tasks = new LinkedHashMap<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      reader.readLine();

      String line;
      while ((line = reader.readLine()) != null) {
        // split the line into columns using comma as the delimiter
        String[] tokens = line.split(",");

        // parse task properties from columns
        int id = Integer.parseInt(tokens[0]);
        String name = tokens[1];
        int volunteers_needed = Integer.parseInt(tokens[2]);
        String description = tokens[3];

        Task task = new Task(id, name, volunteers_needed, description);
        tasks.put(id, task);
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return tasks;
  }

  public static Map<Integer, Volunteer> loadVolunteer(String filename, Map<Integer, Task> tasks)
      throws IOException {
    Map<Integer, Volunteer> volunteers = new LinkedHashMap<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      reader.readLine(); // Skip the header line

      String line;
      while ((line = reader.readLine()) != null) {
        String[] tokens = line.split(",");
        int id = Integer.parseInt(tokens[0].trim());
        String name = tokens[1].trim();
        Volunteer volunteer = new Volunteer(id, name);

        // Split interested task IDs (tokens[2]) and load them with ranks
        String[] tasksIdRank = tokens[2].trim().split(" ");
        for (int i = 0; i < tasksIdRank.length; i++) {
          int taskId = Integer.parseInt(tasksIdRank[i]);
          Task task = tasks.get(taskId);

          if (task != null) {
            int rank = switch (i) {
              case 0 -> 4;  // Top-preferred task
              case 1 -> 3;  // Second-choice task
              case 2 -> 2;  // Third-choice task
              default -> 1; // Other tasks of interest
            };
            volunteer.getPreferenceMap().put(task, rank);
            volunteer.addInterestedTask(task, rank);
          }
        }
        volunteers.put(id, volunteer);
      }
    }
    return volunteers;
  }
}