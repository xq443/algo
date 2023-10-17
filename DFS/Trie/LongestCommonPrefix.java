package Trie;

public class LongestCommonPrefix {
    static TrieNode root;
    public static String longestCommonPrefix(String[] str){
        root = new TrieNode();
        if(str.length == 0) return "";

        for(String word : str){
            insert(word);
        }
        return search(str[0], str.length);
    }
    public static void insert(String s){
        TrieNode node = root;
        for(char ch: s.toCharArray()){
            if(node.next[ch - 'a'] == null){
                node.next[ch - 'a'] = new TrieNode();

            }
            node = node.next[ch - 'a'];
            node.size++;
        }
        node.isEnd = true;
    }
    public static String search(String s, int n){
        TrieNode node = root;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(node.next[ch - 'a'] != null && node.next[ch - 'a'].size == n){
                node = node.next[ch - 'a'];
            }else{
                return s.substring(0, i);
            }
        }
        return s;
    }
    public static void main(String[] args) {
        String [] str = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(str));
    }

    /**
     * Let n = str.length and m = max(strs[i].length) for i in range [0, n].
     *      Time Complexity: O(nm) due to storing every string in Trie.
     *      Space Complexity: O(nm) worst case if each string has unique characters.
     *
     */
}
