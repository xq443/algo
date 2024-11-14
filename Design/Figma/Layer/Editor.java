package Figma.Layer;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


class Editor {

  private Map<Integer, Layer> layers;
  private Stack<Map<Integer, Map<String, String>>> undoStack;
  private int nextLayerId;

  public Editor() {
    this.layers = new HashMap<>();
    this.undoStack = new Stack<>();
    this.nextLayerId = 1;  // Starting layer ID
  }

  public void init(Map<String, String> properties) {
    int layerId = nextLayerId++;
    Layer layer = new Layer(layerId);
    layer.applyProperties(properties);
    layers.put(layerId, layer);
  }

  //  O(n), where n is the number of properties in the properties map being applied.
  public void apply(int layerId, Map<String, String> properties) {
    Layer layer = layers.getOrDefault(layerId, new Layer(layerId));

    // Store the current properties for undo
    Map<String, String> previousProperties = new HashMap<>(layer.properties);

    // Push the state of the current layer (before applying the new properties) onto the undo stack
    undoStack.push(Map.of(layerId, previousProperties));

    // Apply the new properties to the layer
    layer.applyProperties(properties);

    // Update the layer in the layers map
    layers.put(layerId, layer);
  }

  public void undo() {
    if (undoStack.isEmpty()) {
      // Reset all layers to empty if no actions to undo
      for (Layer layer : layers.values()) {
        layer.resetToEmpty();
      }
      return;
    }

    // Pop the most recent changes from the undo stack
    Map<Integer, Map<String, String>> lastChanges = undoStack.pop();

    // Restore the layers to their previous states
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
    Editor manager = new Editor();

    // Initialize layers and apply properties
    manager.init(Map.of("color", "green"));
    manager.apply(1, Map.of("color", "pink"));
    manager.apply(2, Map.of("shape", "circle", "color", "blue"));

    System.out.println("\nAfter first apply:");
    manager.printLayer();

    System.out.println("\nAfter one undo:");
    manager.undo();
    manager.printLayer();

    System.out.println("\nAfter second undo:");
    manager.undo();
    manager.printLayer();

    System.out.println("\nAfter third undo:");
    manager.undo();
    manager.printLayer();
  }
}
/**
 * Time Complexity:
 *
 * The most expensive operations are apply() and undo(), which are both O(n).
 * For printLayer(), the complexity depends on the number of layers and the number of properties,
 * so it is O(m * n).
 *
 *
 * Space Complexity:
 *
 * The main space complexity is driven by the undo stack,
 * which grows with the number of operations, O(k * n),
 * and by the properties of each layer (O(n) per layer).
 */