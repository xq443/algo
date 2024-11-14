# Class representing a leaf node in the Fibonacci tree
class Leaf:
    # Returns the length of a leaf, which is always 1 (a single node)
    def __len__(self):
        return 1

    # Returns the path to itself, which is an empty string (no movement needed)
    def path(self, dest):
        assert 0 <= dest < len(self)  # Ensure dest is within the valid range (0 to 0 for a leaf)
        return ""


# Class representing a branch node in the Fibonacci tree, with left and right subtrees
class Branch:
    def __init__(self, left, right):
        # Initialize left and right subtrees
        self._left = left
        self._right = right
        # Total length of this branch is 1 (for itself) + length of left + length of right
        self._len = 1 + len(left) + len(right)

    # Returns the total number of nodes under this branch (including itself)
    def __len__(self):
        return self._len

    # Finds the path to a given destination node within this branch
    def path(self, dest):
        assert 0 <= dest < len(self)  # Ensure dest is within the valid range for this branch
        if dest < 1:  # If destination is this node itself (the root of this branch)
            return ""
        dest -= 1  # Adjust dest to account for this node itself
        # If destination is in the left subtree
        if dest < len(self._left):
            return "L" + self._left.path(dest)  # Add "L" and recurse on left subtree
        dest -= len(self._left)  # Adjust dest to account for nodes in the left subtree
        # If destination is in the right subtree
        return "R" + self._right.path(dest)  # Add "R" and recurse on right subtree


# Function to create a Fibonacci tree of a given order
def fib_tree(order):
    # Start with the base Fibonacci trees of order 0 and 1, both as leaf nodes
    fib_trees = [Leaf(), Leaf()]
    # Build up Fibonacci trees up to the specified order
    while len(fib_trees) <= order:
        # Append a new branch combining the last two trees in the list (fibonacci sequence)
        fib_trees.append(Branch(fib_trees[-2], fib_trees[-1]))
    # Return the Fibonacci tree of the specified order
    return fib_trees[order]


# Function to find the relative path between two paths within the tree
def left_divide(source_path, dest_path):
    # Find the length of the common prefix of the two paths
    n = min(len(source_path), len(dest_path))
    # Find where the paths first differ
    i = min((j for j in range(n) if source_path[j] != dest_path[j]), default=n)
    # Return the path: "U" for going up to the common ancestor, then down to dest_path
    return "U" * (len(source_path) - i) + dest_path[i:]


# Main function to find the path between two nodes in a Fibonacci tree of a given order
def fib_path(order, source, dest):
    # Build the Fibonacci tree of the given order
    tree = fib_tree(order)
    
    # Ensure source and dest are within bounds of the tree
    if not (0 <= source < len(tree)) or not (0 <= dest < len(tree)):
        raise ValueError("Source or destination index is out of range for the specified tree order.")
    
    # If the source and destination are the same, return an empty path
    if source == dest:
        return "No path available"
    
    # Calculate the paths for source and dest
    source_path = tree.path(source)
    dest_path = tree.path(dest)
    
    # If either path is None or invalid, return "No path available"
    if source_path is None or dest_path is None:
        return "No path available"
    
    # Calculate and return the path from source to dest
    return left_divide(source_path, dest_path)



try:
    print(fib_path(5, 5, 7)) # UUURL
    print(fib_path(5, 5, 5))  # Expected output: "" (no movement needed)
    print(fib_path(5, 0, 7))  # Source is the root node (0) and destination is deep in the tree: Expected path that navigates from root to a leaf node
    print(fib_path(5, 7, 0))  # Destination is the root node (0) and source is deep in the tree: Expected path that moves up to the root node from a leaf
    print(fib_path(5, 5, 10))  # Source and destination are both leaf nodes on opposite branches: Expected path that includes both "U" (up) and "L"/"R" (left/right)
    print(fib_path(5, 6, 7))  # Expected path that moves within the right subtree
    print(fib_path(1, 0, 0))  # Expected output: "" (tree has only one node)
    print(fib_path(6, 10, 15))  # Expected path in a larger tree with more nodes
    print(fib_path(1, 0, 1))  # Expected path navigating from root to the first leaf

except ValueError as e:
    print(f"Error: {e}")
    
# Time Complexity
# The fib_path function involves several steps:

# Building the Fibonacci Tree: This step has a time complexity of (O(\phi^n)), where (\phi) (the golden ratio) is approximately 1.618.

# Calculating Paths: The path method in the Branch class involves traversing the tree to find the path to the destination node.

# In the worst case, this traversal involves visiting all nodes in the tree, which is (O(\phi^n)).

# Calculating the Relative Path: The left_divide function calculates the relative path between two paths.

# This involves finding the common prefix and constructing the path, which is (O(min(len(source_path), len(dest_path)))). In the worst case, this is (O(\phi^n)).
# Combining these steps, the overall time complexity of the fib_path function is (O(\phi^n)).

# Space Complexity
# The space complexity involves:

# Storing the Fibonacci Tree: This is (O(\phi^n)) as discussed above.
# Storing Paths: The paths are strings representing the traversal, and their length is proportional to the height of the tree, which is (O(n)).
# Thus, the overall space complexity is dominated by the storage of the tree, which is (O(\phi^n)).