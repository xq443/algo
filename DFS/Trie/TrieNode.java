package Trie;

public class TrieNode {
    TrieNode[] next;
    boolean isEnd;

    TrieNode(){
        this.isEnd = false;
        this.next = new TrieNode[26];
        for (int i = 0; i < 26 ; i++) {
            this.next[i] = null;

        }
    }

}
