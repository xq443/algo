package Trie;

import java.util.Arrays;
import java.util.List;

/**
 * 我们将所有的单词构建一棵字典树。我们从前往后遍历字符串，如果发现字符串的某段前缀[0:i]对应着Trie中的一个单词，那么我们就可以从对前缀之后的子串[i+1:n-1]进行递归处理。直至发现恰好递归到字符串尾部。
 *
 * 注意DFS要配合记忆化使用，用memo[i]来标记[i:n-1]是否能够已经失败过
 */
public class WordBreak {
    static TrieNode root;
    static int []memo = new int[300];
    public static boolean wordBreak(String s, List<String> dic){

        //build up trie
        root = new TrieNode();
        for(String word :  dic){
            TrieNode node = root;
            for(char ch : word.toCharArray()){
                if(node.next[ch - 'a'] != null){
                    node.next[ch - 'a'] = new TrieNode();
                    node = node.next[ch - 'a'];
                }
                node.isEnd = true;
            }

        }
        Arrays.fill(memo, 1);
        return dfs(s , 0);

    }
    public static boolean dfs(String s, int curr){
        if(curr == s.length()) return true;
        if(memo[curr] == -1) return false;
        TrieNode node = root;
        for(int i = curr; i < s.length(); i++){
            char ch = s.charAt(i);
            if(node.next[ch - 'a'] != null){
                node = node.next[ch - 'a'];
                if(node.isEnd && dfs(s, i+1)) {
                    return true;
                }
            }else break;
        }
        memo[curr] = -1;
        return false;
    }

    //Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
    //Output: false

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> dic = Arrays.asList("leet", "code");
        System.out.println(wordBreak(s,dic));
    }


}
