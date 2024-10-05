package BinarySearchTree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
  public Node searchIter(Node root, int key) {
    Node curr = root;
    while(curr != null) {
      if(curr.value == key) {
        return curr;
      } else if(curr.value > key) {
        curr = curr.left;
      } else
        curr = curr.right;
    }
    System.out.println("Cannot find the given key: " + key);
    return null;
  }

  public Node searchRecur(Node root, int key) {
    if (root == null || root.value == key) {
      return root;
    }
    if (root.value > key) {
      return searchRecur(root.left, key);
    } else {
      return searchRecur(root.right, key);
    }
  }

  public void insertRecur(Node root, int key) {
    if(root.value == key) {
      System.out.println("Key " + key + " already exists");
      return;
    }
    if(root.value > key) {
      if(root.left != null) {
        insertRecur(root.left, key);
      } else {
        root.left = new Node(key);
      }
    } else {
      if (root.right != null) {
        insertRecur(root.right, key);
      } else {
        root.right = new Node(key);
      }
    }
  }

  public void insertIter(Node root, int key) {
    Node curr = root;
    while (curr != null) {
      if(curr.value == key) {
        System.out.println("Key " + key + " already exists");
        return;
      } else if(curr.value > key) {
        if(curr.left != null) {
          curr = curr.left;
        } else{
          curr.left = new Node(key);
          return;
        }
      } else {
        if(curr.right != null) {
          curr = curr.right;
        } else {
          curr.right = new Node(key);
          return;
        }
      }
    }
  }

  public Node findMin(Node root) {
    Node curr = root;
    while(curr.left != null) {
      curr = curr.left;
    }
    return curr;
  }

  public void transplant (Tree tree, Node u, Node v) {
    if(u.parent == null) { // u is root
      tree.root = v;
    } else if(u.parent.left == u) {
      u.parent.left = v;
    } else {
      u.parent.right = v;
    }
    if(v != null) {
      v.parent = u.parent;
    }
  }

  public void delete(Tree tree, int key) {
    Node curr = searchIter(tree.root, key);

    if(curr != null) {
      if(curr.left == null) {
        transplant(tree, curr, curr.right);
      } else if(curr.right == null) {
        transplant(tree, curr, curr.left);
      } else {
        Node succ = findMin(curr.right); // Find the in-order successor
        curr.value = succ.value;  // Copy successor's value to the current node
        transplant(tree, succ, succ.right); // Replace successor with its right child
      }
    }
  }

  public Node successor(Node root) {
    if (root.right != null) {
      return findMin(root.right);
    }
    Node curr = root;
    // find the first ancestor where curr is in the left subtree
    while (curr.parent != null && curr == curr.parent.right) {
      curr = curr.parent;
    }
    return curr.parent;
  }
        //    20
        //   /  \
        //  10   30
        //  / \. / \
        // 5  15 22 32

  public List<Integer> inorder(Tree tree) {
    Node curr = findMin(tree.root);
    List<Integer> ret = new ArrayList<>();
    while(curr != null) {
      ret.add(curr.value);
      curr = successor(curr);
    }
    return ret;
  }

  public void printBST(Tree tree) {
    printHelper(tree.root, 1);
  }

  public void printHelper(Node root, int depth) {
    String tab = "    ".repeat(depth); // Adjust the tab size
    if(root == null) {
      System.out.println(tab + "None");
      return;
    }
    printHelper(root.right, depth + 1);
    System.out.println(tab + root.value);
    printHelper(root.left, depth + 1);
  }

  public Tree arrayToBST(int[] nums){
    Tree tree = new Tree(nums[0]);
    for(int i = 1; i < nums.length; i++){
      insertIter(tree.root, nums[i]);
    }
    return tree;
  }

  public static void main(String[] args) {
    BinarySearchTree binarySearchTree = new BinarySearchTree();
    int[] arr =  {6, 2, 10, 4, 9, 1, 11, 3, 8, 12, 5};
    Tree tree = binarySearchTree.arrayToBST(arr);
    binarySearchTree.printBST(tree);
    binarySearchTree.insertIter(tree.root, 85);
    System.out.println("==========================================");
    binarySearchTree.printBST(tree);
    binarySearchTree.delete(tree, 6);
    binarySearchTree.printBST(tree);
  }
}
