package Figma.Layer;

import java.util.HashMap;
import java.util.Map;

class Layer {

  private final int layerId;
  Map<String, String> properties;

  public Layer(int layerId) {
    this.layerId = layerId;
    this.properties = new HashMap<>();
  }

  public void applyProperties(Map<String, String> newProperties) {
    properties.putAll(newProperties);
  }

  public void resetProperties(Map<String, String> previousProperties) {
    properties.clear();
    properties.putAll(previousProperties);
  }

  public void resetToEmpty() {
    properties.clear();
  }
}
