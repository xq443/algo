package Figma.VolunteerAssign;

public class Task {
  int id;
  String name;
  String description;
  int volunteers_needed;

  public Task(int id, String name, int volunteers_needed, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.volunteers_needed = volunteers_needed;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public int getVolunteers_needed() {
    return volunteers_needed;
  }

  @Override
  public String toString() {
    return "Task #" + id + ": " + name;
  }
}
