package Trie;

public class Main {
  public static void main(String[] args) {
    Trie trie = new Trie();
    trie.insert("apple");
    if (trie.search("apple")) {
      System.out.println("true");
    } // return True
    if (!trie.search("app")) {
      System.out.println("false");
    } // return False
    if (trie.startsWith("app")) {
      System.out.println("true");
    } // return True
    trie.insert("app");
    if (trie.search("app")) {
      System.out.println("true");
    } // return True

    trie.insert("apple"); // Inserts "apple".
    trie.insert("apple"); // Inserts another "apple".
    System.out.println(trie.countWordsEqualTo(
        "apple")); // There are two instances of "apple" so return 2.
    System.out.println(trie.countWordsStartingWith(
        "app"));         // "app" is a prefix of "apple" so return 2.
    trie.erase("apple"); // Erases one "apple".
    System.out.println(trie.countWordsEqualTo(
        "apple")); // Now there is only one instance of "apple" so return 1.
    System.out.println(trie.countWordsStartingWith("app")); // return 1
    trie.erase("apple"); // Erases "apple". Now the trie is empty.
    System.out.println(trie.countWordsStartingWith("app")); // return 0
  }
}
