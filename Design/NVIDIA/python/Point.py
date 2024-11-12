import math

class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y

class BoundingBox:
    def __init__(self):
        self.ll = None  # Lower-left point (Point)
        self.ur = None  # Upper-right point (Point)

    def add_point(self, p):
        """Expands the bounding box to include the given point."""
        if self.ll is None or self.ur is None:
            # Initialize the bounding box if it hasn't been set
            self.ll = Point(p.x, p.y)
            self.ur = Point(p.x, p.y)
        else:
            # Adjust the lower-left and upper-right points to include p
            self.ll.x = min(self.ll.x, p.x)
            self.ll.y = min(self.ll.y, p.y)
            self.ur.x = max(self.ur.x, p.x)
            self.ur.y = max(self.ur.y, p.y)

    def inside(self, p):
        """Checks if the point p is inside the bounding box."""
        if self.ll and self.ur:
            return (self.ll.x <= p.x <= self.ur.x) and (self.ll.y <= p.y <= self.ur.y)
        return False

    def distance(self, p):
        """Calculates the shortest distance from point p to the bounding box."""
        if self.inside(p):
            return 0  # If the point is inside the bounding box, distance is zero

        # Calculate the horizontal and vertical distances from p to the bounding box edges
        dx = max(self.ll.x - p.x, 0, p.x - self.ur.x)
        dy = max(self.ll.y - p.y, 0, p.y - self.ur.y)
        
        # Use the Pythagorean theorem to calculate the distance
        return math.sqrt(dx ** 2 + dy ** 2)

# Example Usage
# Define a few points
points = [Point(1, 1), Point(2, 5), Point(6, 3), Point(4, 4)]

# Initialize the bounding box and add points to it
bbox = BoundingBox()
for p in points:
    bbox.add_point(p)

# Check if a point is inside the bounding box
test_point = Point(3, 3)
print("Is the point inside?", bbox.inside(test_point))  # Should be True if inside, False otherwise

# Calculate the distance from a point to the bounding box
distance_point = Point(7, 7)
print("Distance to the bounding box:", bbox.distance(distance_point))
