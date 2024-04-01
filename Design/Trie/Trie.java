package Trie;

public class Trie {
  private final TrieNode root;

  /**
   * Initializes the trie object.
   */
  public Trie() {
    this.root = new TrieNode();
  }

  /**
   * Inserts the string word into the trie.
   * @param word
   */
  public void insert(String word) {
    TrieNode node = root;
    for(char c : word.toCharArray()) {
      int index = c - 'a';
      if(node.children[index] == null) {
        node.children[index] = new TrieNode();
      }
      node = node.children[index];
    }
    node.isEnd = true;
  }

  /**
   * Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
   * @param word
   * @return
   */
  public boolean search(String word) {
    TrieNode node = searchPrefix(word);
    return node != null && node.isEnd;
  }

  /**
   * Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
   * @param prefix
   * @return
   */
  public boolean startsWith(String prefix) {
    return searchPrefix(prefix) != null;
  }

  /**
   * Searches for the node in the trie that corresponds to the given prefix.
   *
   * @param word The prefix to search for.
   * @return The TrieNode representing the last character of the prefix if found, null otherwise.
   */
  public TrieNode searchPrefix(String word) {
    TrieNode node = root;
    for(char ch : word.toCharArray()) {
      int idx = ch - 'a';
      if(node.children[idx] == null) return null;
      node = node.children[idx];
    }
    return node;
  }
}
