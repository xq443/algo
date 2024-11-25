import java.util.*;

public class DesignatePartition {
  public List<List<String>> designate(List<String> V, List<List<String>> E) {
    // Create a graph from the list of wrestlers and rivalries
    Map<String, List<String>> graph = new HashMap<>();
    for (String wrestler : V) {
      graph.put(wrestler, new ArrayList<>());
    }
    for (List<String> rivalry : E) {
      String u = rivalry.get(0);
      String v = rivalry.get(1);
      graph.get(u).add(v);
      graph.get(v).add(u);
    }

    // Map to store the designation of each wrestler
    Map<String, String> designation = new HashMap<>();

    // Check each component of the graph
    for (String wrestler : V) {
      if (!designation.containsKey(wrestler)) {
        Queue<String> queue = new LinkedList<>();
        queue.add(wrestler);
        designation.put(wrestler, "face");

        while (!queue.isEmpty()) {
          String current = queue.poll();
          String currentDesignation = designation.get(current);
          String nextDesignation = currentDesignation.equals("face") ? "heel" : "face";

          for (String neighbor : graph.get(current)) {
            if (!designation.containsKey(neighbor)) {
              designation.put(neighbor, nextDesignation);
              queue.add(neighbor);
            } else if (designation.get(neighbor).equals(currentDesignation)) {
              return null;
            }
          }
        }
      }
    }

    // Separate the wrestlers into faces and heels
    List<String> faces = new ArrayList<>();
    List<String> heels = new ArrayList<>();
    for (Map.Entry<String, String> entry : designation.entrySet()) {
      if (entry.getValue().equals("face")) {
        faces.add(entry.getKey());
      } else {
        heels.add(entry.getKey());
      }
    }

    List<List<String>> result = new ArrayList<>();
    result.add(faces);
    result.add(heels);
    return result;
  }

  public static void main(String[] args) {
    // Test cases
    List<String> V1 = Arrays.asList("The Rock", "Steve Austin");
    List<List<String>> E1 = Arrays.asList(Arrays.asList("The Rock", "Steve Austin"));
    System.out.println(new DesignatePartition().designate(V1, E1));  // Should return [["The Rock"], ["Steve Austin"]] or [["Steve Austin"], ["The Rock"]]

    List<String> V2 = Arrays.asList("The Rock", "Steve Austin", "Triple H");
    List<List<String>> E2 = Arrays.asList(
        Arrays.asList("The Rock", "Steve Austin"),
        Arrays.asList("The Rock", "Triple H"),
        Arrays.asList("Steve Austin", "Triple H")
    );
    System.out.println(new DesignatePartition().designate(V2, E2));  // Should return null
  }
}