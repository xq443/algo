import java.util.*;

public class wordLadder {
    //beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord)) return 0;

        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int ret = 1;
        while (!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String curr = q.poll();
                assert curr != null;
                if(curr.equals(endWord)) return ret;
                for (int j = 0; j < Objects.requireNonNull(curr).length(); j++) {
                    for (int k = 'a'; k <= 'z'; k++) {
                        char[] temp = curr.toCharArray();
                        temp[j] = (char) k;

                        String new_str = new String(temp);
                        if(!visited.contains(new_str) && set.contains(new_str)){
                            q.offer(new_str);
                            visited.add(new_str);
                        }
                    }
                }
            }
            ret++;
        }
        return 0;
    }
    public static void main(String[] args) {
        wordLadder w = new wordLadder();
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = List.of(new String[]{"hot", "dot", "dog", "lot", "log", "cog"});
        System.out.println(w.ladderLength(beginWord, endWord, wordList));
    }
}
