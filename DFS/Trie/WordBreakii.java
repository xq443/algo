package Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordBreakii {
    static List<String> res;
    static TrieNode root;
    static int[] memo = new int[300];
    public static List<String> wordBreakII(String s, List<String> dic){
        //build up a trie
//        Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
//        Output: ["cats and dog","cat sand dog"]
        root = new TrieNode();
        for(String word : dic){
            TrieNode node = root;
            for(char ch : word.toCharArray()){
                if(node.next[ch - 'a'] == null){
                    node.next[ch - 'a'] = new TrieNode();
                }
                node = node.next[ch - 'a'];
            }
            node.isEnd = true;
        }
        for (int i = 0; i < 300; i++) {
            memo[i]= -1;
        }

        res  = new ArrayList<>();
        List<String> ans = new ArrayList<>();
        dfs(s, 0, ans);
        return res;
    }
    public static boolean dfs(String s, int curr, List<String> ans){

        boolean flag = false;
        if(curr == s.length()){
            StringBuilder sb = new StringBuilder();
            for(String word : ans){
                sb.append(word).append(" ");
            }
            sb.setLength(sb.length() -1);
            res.add(sb.toString());

            flag =  true;
        }
        if(memo[curr] == 0) return  false;

        TrieNode node = root;

        for(int i = curr; i < s.length(); i++){
            char ch = s.charAt(i);
            if(node.next[ch - 'a'] != null){
                node = node.next[ch - 'a'];
                if(node.isEnd){
                    //record the words into ans
                    ans.add(s.substring(curr, i+1));
                    if(dfs(s, i+1, ans)){
                        flag = true;
                    }
                    ans.remove(ans.size() - 1); //backtracking
                }

            }else{
                break;
            }

        }
        if(!flag) memo[curr] = 0;
        return flag;
    }

    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> li = Arrays.asList("cat","cats","and","sand","dog");
        System.out.println(wordBreakII(s, li));
    }

}
