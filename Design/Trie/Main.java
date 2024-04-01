package Trie;

public class Main {

  public static void main(String[] args) {
    Trie trie = new Trie();
    trie.insert("apple");
    if(trie.search("apple")){
      System.out.println("true");
    }   // return True
    if(!trie.search("app")){
      System.out.println("false");
    }    // return False
    if(trie.startsWith("app")){
      System.out.println("true");
    } // return True
    trie.insert("app");
    if(trie.search("app")){
      System.out.println("true");
    }     // return True
  }
}
