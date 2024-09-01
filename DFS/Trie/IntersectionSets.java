package Trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class IntersectionSets {

  // Inner class representing a Trie node
  static class Trie {
    Trie[] next; // Array to store child nodes for each character
    boolean isWord; // Flag to indicate if this node marks the end of a word

    // Constructor initializing the Trie node
    public Trie() {
      this.next = new Trie[130]; // Array to hold references to children nodes (size 130 for extended ASCII)
      this.isWord = false; // Initially, no word ends at this node
    }

    // Method to check if the given word is a prefix of any word in the Trie
    public Trie isPrefix(String word) {
      Trie node = this; // Start from the root node
      for (char ch : word.toCharArray()) { // Iterate through each character in the word
        if (node.next[ch] == null) return null; // If no child node exists for the character, return null
        node = node.next[ch]; // Move to the next node
      }
      return node; // Return the node where the prefix ends
    }

    // Method to insert a word into the Trie
    public void insert(String word) {
      Trie node = this; // Start from the root node
      for (char ch : word.toCharArray()) { // Iterate through each character in the word
        if (node.next[ch] == null) { // If no child node exists for the character, create a new node
          node.next[ch] = new Trie();
        }
        node = node.next[ch]; // Move to the child node
      }
      node.isWord = true; // Mark the end of the word
    }

    // Method to find if a word exists in the Trie
    public boolean find(String word) {
      Trie node = isPrefix(word); // Check if the word is a prefix in the Trie
      return node != null && node.isWord; // Return true if the word is found and is marked as a complete word
    }
  }

  // Main method to test the Trie operations
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in); // Create a Scanner object to read input

    // Read two lines of input and split them into arrays of words
    String[] a = sc.nextLine().split(" ");
    String[] b = sc.nextLine().split(" ");

    List<String> ret = new ArrayList<>(); // List to store the intersection of words

    Trie t1 = new Trie(); // Create a new Trie instance for the first set of words
    for (String word : a) {
      t1.insert(word); // Insert each word from the first input into the Trie
    }

    Trie t2 = new Trie(); // Create a new Trie instance for tracking intersection
    for (String word : b) {
      // Check if the word is present in the first Trie and not already in the intersection Trie
      if (t1.find(word) && !t2.find(word)) {
        ret.add(word); // Add the word to the result list
        t2.insert(word); // Insert the word into the intersection Trie to avoid duplicates
      }
    }

    if (ret.isEmpty()) {
      System.out.println("NULL"); // If no common words, print "NULL"
    } else {
      Collections.sort(ret); // Sort the result list in asc order
      System.out.println(String.join(" ", ret)); // as a single space-separated string
    }
  }
}
