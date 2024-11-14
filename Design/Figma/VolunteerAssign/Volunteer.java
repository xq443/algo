package Figma.VolunteerAssign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Volunteer {
  int id;
  String name;
  List<Task> interestedTasks;
  Map<Task, Integer> preferenceMap;

  public Volunteer(int id, String name) {
    this.id = id;
    this.name = name;
    this.interestedTasks = new ArrayList<>();
    this.preferenceMap = new HashMap<>();
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<Task> getInterestedTasks() {
    return interestedTasks;
  }

  public void addInterestedTask(Task task, int rank) {
    interestedTasks.add(task);
    preferenceMap.put(task, rank);
  }

  public void removeInterestedTask(Task task, int rank) {
    interestedTasks.remove(task);
    preferenceMap.remove(task);
  }

  public boolean isInterestedTask(Task task) {
    return interestedTasks.contains(task);
  }

  public int getPreferenceRank(Task task) {
    return preferenceMap.getOrDefault(task, -1);
  }

  public Map<Task, Integer> getPreferenceMap() {
    return preferenceMap;
  }

  @Override
  public String toString() {
    return "Volunteer #" +
        id +  ": " + name;
  }
}
