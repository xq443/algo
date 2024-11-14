package Figma.Layer;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


class Batch {

  private final Map<Integer, Layer> layers;
  private final Stack<Map<Integer, Map<String, String>>> undoStack;
  private final Map<Integer, Map<String, String>> currentBatch;
  private int nextLayerId;

  public Batch() {
    this.layers = new HashMap<>();
    this.undoStack = new Stack<>();
    this.currentBatch = new HashMap<>();
    this.nextLayerId = 1;  // Starting layer ID
  }

  public void init(Map<String, String> properties) {
    int layerId = nextLayerId++;
    Layer layer = new Layer(layerId);
    layer.applyProperties(properties);
    layers.put(layerId, layer);
  }

  public void apply(int layerId, Map<String, String> properties) {
    Layer layer = layers.getOrDefault(layerId, new Layer(layerId));

    // Store the current properties for undo in batch
    Map<String, String> previousProperties = new HashMap<>(layer.properties);

    // Add to the current batch of changes
    currentBatch.put(layerId, previousProperties);

    // Apply the new properties to the layer
    layer.applyProperties(properties);

    // Update the layer in the layers map
    layers.put(layerId, layer);
  }

  public void commit_batch() {
    if (!currentBatch.isEmpty()) {
      // Push the entire batch to the undo stack
      undoStack.push(new HashMap<>(currentBatch));
      // Clear the batch after committing
      currentBatch.clear();
    }
  }

  public void undo() {
    if (undoStack.isEmpty()) {
      // Reset all layers to empty if no actions to undo
      for (Layer layer : layers.values()) {
        layer.resetToEmpty();
      }
      return;
    }

    // Pop the most recent changes from the undo stack (a full batch of changes)
    Map<Integer, Map<String, String>> lastChanges = undoStack.pop();

    // Restore the layers to their previous states (undo the batch)
    for (Map.Entry<Integer, Map<String, String>> entry : lastChanges.entrySet()) {
      int layerId = entry.getKey();
      Map<String, String> propertiesToRestore = entry.getValue();

      Layer layer = layers.get(layerId);
      if (layer != null) {
        layer.resetProperties(propertiesToRestore);
      }
    }
  }

  public void printLayer() {
    if (layers.isEmpty()) {
      System.out.println("No layers to display.");
    } else {
      for (Map.Entry<Integer, Layer> entry : layers.entrySet()) {
        Layer layer = entry.getValue();
        System.out.println("Layer " + entry.getKey() + ": " + layer.properties);
      }
    }
  }

  public static void main(String[] args) {
    // Create an instance of the Editor class
    Batch manager = new Batch();

    // Initialize layers and apply properties
    manager.init(Map.of("color", "green"));
    manager.apply(1, Map.of("color", "pink"));
    manager.apply(2, Map.of("shape", "triangle", "color", "blue"));
    manager.commit_batch(); // Commit the first batch

    System.out.println("\nAfter first batch commit:");
    manager.printLayer();

    manager.apply(1, Map.of("color", "blue"));
    manager.apply(1, Map.of("color", "white"));
    manager.commit_batch(); // Commit the second batch

    System.out.println("\nAfter second batch commit:");
    manager.printLayer();

  }
}
