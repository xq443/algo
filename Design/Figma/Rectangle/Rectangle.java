package Figma.Rectangle;

class Rectangle {
  int x1, y1, x2, y2;  // x1, y1 -> bottom-left corner, x2, y2 -> top-right corner

  public Rectangle(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  public static Rectangle intersect(Rectangle rect1, Rectangle rect2) {
    // Calculate the bottom-left corner of the intersection
    int x1 = Math.max(rect1.x1, rect2.x1);
    int y1 = Math.max(rect1.y1, rect2.y1);

    // Calculate the top-right corner of the intersection
    int x2 = Math.min(rect1.x2, rect2.x2);
    int y2 = Math.min(rect1.y2, rect2.y2);

    // Check if the intersection is valid
    if (x1 < x2 && y1 < y2) {
      // Return the intersecting rectangle
      return new Rectangle(x1, y1, x2, y2);
    } else {
      // No intersection
      return null;
    }
  }

  @Override
  public String toString() {
    return String.format("Rectangle[x1=%d, y1=%d, x2=%d, y2=%d]", x1, y1, x2, y2);
  }

  public static void main(String[] args) {
    // Create two rectangles
    Rectangle rect1 = new Rectangle(1, 1, 5, 5);  // Bottom-left (1,1), Top-right (5,5)
    Rectangle rect2 = new Rectangle(3, 3, 7, 7);  // Bottom-left (3,3), Top-right (7,7)

    // Find the intersection of the two rectangles
    Rectangle intersection = Rectangle.intersect(rect1, rect2);

    // Output the result
    if (intersection != null) {
      System.out.println("Intersection: " + intersection);
    } else {
      System.out.println("No intersection.");
    }

    // Test case where there's no intersection
    Rectangle rect3 = new Rectangle(6, 6, 8, 8);
    intersection = Rectangle.intersect(rect1, rect3);
    if (intersection != null) {
      System.out.println("Intersection: " + intersection);
    } else {
      System.out.println("No intersection.");
    }
  }
}
