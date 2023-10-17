package Trie;

public class TrieNode {
    TrieNode[] next;
    boolean isEnd;

    int size;

    int val;

    TrieNode(){
        this.isEnd = false;
        this.size = 0;
        this.val = 0;
        this.next = new TrieNode[26];
        for (int i = 0; i < 26 ; i++) {
            this.next[i] = null;

        }
    }

}
