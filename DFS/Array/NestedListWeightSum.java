package Array;

import java.util.List;

public class NestedListWeightSum {

  public interface NestedInteger {
    // Constructor initializes an empty nested list.
    // Constructor initializes a single integer.

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value);

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni);

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
  }

  public int depthSum(List<NestedInteger> nestedList) {
    if(nestedList == null || nestedList.size() == 0) return 0;
    return depthSum_dfs(nestedList, 1);
  }
  private int depthSum_dfs(List<NestedInteger> nestedList, int depth) {
    int sum = 0;
    for(NestedInteger n : nestedList) {
      if(n.isInteger()) {
        sum += n.getInteger() * depth;
      } else {
        sum += depthSum_dfs(n.getList(), depth + 1);
      }
    }
    return sum;
  }
}
