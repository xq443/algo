package Figma.VolunteerAssign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssignmentServer {
  Map<Integer, Task> tasks;
  Map<Integer, Volunteer> volunteers;
  Map<Task, List<Volunteer>> assignments;

  public AssignmentServer(Map<Integer, Task> tasks, Map<Integer, Volunteer> volunteers) {
    this.tasks = tasks;
    this.volunteers = volunteers;
    this.assignments = new HashMap<Task, List<Volunteer>>();
  }

  public List<Volunteer> getInterestedVolunteers(Task task) {
    List<Volunteer> interestedVolunteers = new ArrayList<Volunteer>();
    for(Volunteer volunteer : volunteers.values()) {
      if(volunteer.isInterestedTask(task)) {
        interestedVolunteers.add(volunteer);
      }
    }
    return interestedVolunteers;
  }

  public void assignTask() {
    assignments.clear();
    for(Task task : tasks.values()) {
      List<Volunteer> interestedVolunteers = getInterestedVolunteers(task);
      List<Volunteer> assignedVolunteers = new ArrayList<>();
      if(!interestedVolunteers.isEmpty()) {
        assignedVolunteers.addAll(interestedVolunteers);
      }

      if(assignedVolunteers.size() < task.getVolunteers_needed()) {
        for(Volunteer volunteer : volunteers.values()) {
          if(!assignedVolunteers.contains(volunteer)) {
            assignedVolunteers.add(volunteer);
          }
          if(assignedVolunteers.size() == task.getVolunteers_needed()) break;
        }
      }
      assignments.put(task, assignedVolunteers);
    }
  }

  public void assignTaskNew() {
    assignments.clear();
    for(Task task : tasks.values()) {
      List<Volunteer> interestedVolunteers = getInterestedVolunteers(task);

      // sort by preference of volunteers for the task
      interestedVolunteers.sort((v1, v2) -> {
        return Integer.compare(v2.getPreferenceRank(task), v1.getPreferenceRank(task));
      });

      List<Volunteer> assignedVolunteers = new ArrayList<>();
      for(Volunteer volunteer : volunteers.values()) {
        if(assignedVolunteers.size() >= task.getVolunteers_needed()) break;
        assignedVolunteers.add(volunteer);
      }

      if(assignedVolunteers.size() < task.getVolunteers_needed()) {
        for(Volunteer volunteer : volunteers.values()) {
          if(!assignedVolunteers.contains(volunteer)) {
            assignedVolunteers.add(volunteer);
            if(assignedVolunteers.size() == task.getVolunteers_needed()) break;
          }
        }
      }
      assignments.put(task, assignedVolunteers);
    }
  }

  public void printAssignments() {
    for(Task task: tasks.values()) {
      List<Volunteer> interestedVolunteers = assignments.get(task);
      System.out.print(task);
      if(interestedVolunteers != null && !interestedVolunteers.isEmpty()) {
        System.out.print(" assigned to: ");
        for(Volunteer volunteer : interestedVolunteers) {
          System.out.print(volunteer + " ");
        }
        System.out.println();
      }else System.out.println(" Unassigned");
    }
  }

  public int calculateOverallScore() {
    int overallScore = 0;
    Map<Volunteer, Integer> taskCount = new HashMap<>();
    for(Volunteer volunteer : volunteers.values()) {
      taskCount.put(volunteer, 0);
    }

    for(Map.Entry<Task, List<Volunteer>> entry : assignments.entrySet()) {
      Task task = entry.getKey();
      List<Volunteer> interestedVolunteers = entry.getValue();
      for(Volunteer volunteer : interestedVolunteers) {
        taskCount.put(volunteer, taskCount.get(volunteer) + 1);

        int preferenceRank = volunteer.getPreferenceRank(task);
        if(preferenceRank == 1) {
          overallScore += 4;
        } else if(preferenceRank == 2) {
          overallScore += 3;
        } else if(preferenceRank == 3) {
          overallScore += 2;
        } else if(preferenceRank > 3 && preferenceRank != -1) {
          overallScore += 1;
        } else if(preferenceRank == -1) {
          overallScore -= 1;
        }
      }
    }

    int max = (int)Math.ceil((double) tasks.size() / (double)volunteers.size());

    for(Map.Entry<Volunteer, Integer> entry : taskCount.entrySet()) {
      int count = entry.getValue();
      if(count > max) {
        overallScore -= (count - max);
      }
    }
    return overallScore;
  }

  public void compare() {
    assignTask();
    int original = calculateOverallScore();
    System.out.println("Original Assignment:");
    printAssignments();
    System.out.println("Original score: " + original);


    assignTaskNew();
    int newScore = calculateOverallScore();
    System.out.println("New Assignment:");
    printAssignments();
    System.out.println("New score: " + newScore);

    if(newScore > original) {
      System.out.println("New algo is better");
    } else if(newScore == original) {
      System.out.println("equal");
    } else
      System.out.println("Original algo is better");
  }
}
