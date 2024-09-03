package Array;

import java.util.List;

public class NestedListWeightSumII {
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
    return depthSumII_dfs(nestedList, maxDepth(nestedList));
  }

  private int depthSumII_dfs(List<NestedInteger> nestedList, int maxDepth) {
    int sum = 0;
    for(NestedInteger i : nestedList) {
      if(i.isInteger()) {
        sum += i.getInteger() * maxDepth;
      } else {
        sum += depthSumII_dfs(i.getList(), maxDepth - 1);
      }
    }
    return sum;
  }

  private int maxDepth(List<NestedInteger> nestedList) {
    int depth = 1;
    for(NestedInteger i : nestedList) {
      if(!i.isInteger()) {
        depth = Math.max(depth, maxDepth(i.getList()) + 1);
      }
    }
    return depth;
  }
}
