package Trie;

import java.util.ArrayList;
import java.util.List;

public class LexicographicalNumber {
    static TrieNode root;
    public static List<Integer> lexicalOrder(int n){

        root = new TrieNode();

        for (int i = 1; i < n+1 ; i++) {
            TrieNode node = root;
            for(char ch: Integer.toString(i).toCharArray()){//concatenation
                if(node.next[ch - '0'] == null){
                    node.next[ch - '0'] = new TrieNode();
                }
                node = node.next[ch - '0'];
            }
            node.isEnd = true;
            node.val = i;
        }
        List<Integer> res = new ArrayList<>();
        callDfs(res, root);
        return res;

    }
    public static void callDfs(List<Integer> res, TrieNode root){
        TrieNode node = root;
        if(node.isEnd) res.add(node.val);

        for(int i = 0; i < 10; i++){
            if(node.next[i] != null){
                callDfs(res, node.next[i]);
            }
        }
    }
    public static void main(String[] args) {
        int n = 13;
        System.out.println(lexicalOrder(n));
    }
}
