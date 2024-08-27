package Trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearchII {
  public List<String> findWords(char[][] board, String[] words) {
    List<String> ret = new ArrayList<>();
    if(board == null || board.length == 0 || words == null || words.length == 0) return ret;

    Set<String> retSet = new HashSet<>();
    TrieNode node = buildTrie(words);
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        findWords_dfs(retSet, board, i, j, "", node);
      }
    }
    ret.addAll(retSet);
    return ret;
  }
  private void findWords_dfs(Set<String> retSet, char[][] board, int x, int y, String sub, TrieNode node) {
    int m = board.length;
    int n = board[0].length;
    if(x < 0 || x >= m || y < 0 || y >= n || board[x][y] == '!') return;

    char curr = board[x][y];
    if(node.children[curr - 'a'] == null) return; // pruning
    node = node.children[curr - 'a'];
    sub += curr;

    if(node.isWord) retSet.add(sub);

    board[x][y] = '!';  // mark the cell as visited

    findWords_dfs(retSet, board, x + 1, y, sub, node);
    findWords_dfs(retSet, board, x - 1, y, sub, node);
    findWords_dfs(retSet, board, x, y + 1, sub, node);
    findWords_dfs(retSet, board, x, y - 1, sub, node);

    board[x][y] = curr; // backtracking
  }


  static class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isWord;
  }

  public TrieNode buildTrie(String[] words) {
    TrieNode root = new TrieNode();
    for(String word : words) {
      TrieNode node = root;
      for(char c : word.toCharArray()) {
        if(node.children[c - 'a'] == null) {
          node.children[c - 'a'] = new TrieNode();
        }
        node = node.children[c - 'a'];
      }
      node.isWord = true;
    }
    return root;
  }

  public static void main(String[] args) {
    WordSearchII wordSearchII = new WordSearchII();
    char[][] board = {
        {'o','a','a','n'},
        {'e','t','a','e'},
        {'i','h','k','r'},
        {'i','f','l','v'}
    };
    String[] words = {"oath","pea","eat","rain"};
    System.out.println(wordSearchII.findWords(board, words));
  }
}
