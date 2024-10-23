package Trie;

public class TrieNode {
  /** Array of child nodes, each corresponding to a letter of the alphabet. */
  public TrieNode[] children;
  /** Indicates whether this node represents the end of a word. */
  public boolean isEnd;
  public int count;    // nodes passing through
  public int countEnd; // nodes ending here

  /**
   * Constructs a new TrieNode.
   * Initializes the children array to hold references to child nodes.
   */
  public TrieNode() {
    this.children = new TrieNode[26]; // Assuming lowercase English letters
    this.isEnd = false;
  }
}
