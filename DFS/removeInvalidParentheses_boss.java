import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class removeInvalidParentheses_boss {
    int maxSize = -1;
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        Set<String> filteredRes = new HashSet<>();
        dfs(filteredRes, s, 0, new StringBuilder(), 0, 0);

        res.addAll(filteredRes);
        return res;
    }

    public void dfs(Set<String> filteredRes, String s, int cur_idx, StringBuilder cur_res, int l_count, int r_count) {
        if (cur_idx == s.length()) {
            if (l_count == r_count) {
                int curLength = cur_res.length();
                if (curLength > maxSize) {
                    maxSize = curLength;
                    filteredRes.clear(); // Clear previous results of smaller lengths
                    filteredRes.add(cur_res.toString());
                }
                if (curLength == maxSize) {
                    filteredRes.add(cur_res.toString());
                }
            }
            return;
        }

        char cur_char = s.charAt(cur_idx);
        if (cur_char == '(') {
            cur_res.append(cur_char);
            dfs(filteredRes, s, cur_idx + 1, cur_res, l_count + 1, r_count);
            cur_res.deleteCharAt(cur_res.length() - 1);
            dfs(filteredRes, s, cur_idx + 1, cur_res, l_count, r_count);
        } else if (cur_char == ')') {
            dfs(filteredRes, s, cur_idx + 1, cur_res, l_count, r_count);
            if (l_count > r_count) {
                cur_res.append(cur_char);
                dfs(filteredRes, s, cur_idx + 1, cur_res, l_count, r_count + 1);
                cur_res.deleteCharAt(cur_res.length() - 1);
            }
        } else {
            cur_res.append(cur_char);
            dfs(filteredRes, s, cur_idx + 1, cur_res, l_count, r_count);
            cur_res.deleteCharAt(cur_res.length() - 1);
        }
    }
    public static void main(String[] args) {
        removeInvalidParentheses_boss r = new removeInvalidParentheses_boss();
        String s = "()())()";
        List<String> ret = r.removeInvalidParentheses(s);
        System.out.println(ret.toString());
    }
}
//TC O(2^N)
//SC O(N)


