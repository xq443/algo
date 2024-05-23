import java.util.*;

public class allNodesDistanceKinBinaryTree {
    Map<TreeNode, List<TreeNode>> map = new HashMap<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> ret = new ArrayList<>();
        if(root == null) return ret;
        buildMap(root, null);

        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        visited.add(target);

        while (!queue.isEmpty()){
            int size = queue.size();
            if(K == 0) {
                for (int i = 0; i < size; i++) {
                    ret.add(Objects.requireNonNull(queue.poll()).val);
                }
                return ret;
            }
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                for(TreeNode next : map.get(curr)){
                    if(!visited.contains(next)){
                        visited.add(next);
                        queue.add(next);
                    }
                }
            }
            K--;
        }
        return ret;
    }
    public void buildMap(TreeNode node, TreeNode parent){
        if(node == null) return;
        if(!map.containsKey(node)){
            map.put(node, new ArrayList<>());
        }
        if(parent != null) {
            map.get(node).add(parent);
            map.get(parent).add(node);
        }
        buildMap(node.right, node);
        buildMap(node.left, node);
    }

    public static void main(String[] args) {
        allNodesDistanceKinBinaryTree a = new allNodesDistanceKinBinaryTree();
        // Creating the binary tree nodes
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        // Setting up the target node and distance
        TreeNode target = root.left;
        int k = 2;
        // Calling the distanceK method
        List<Integer> result = a.distanceK(root, target, k);
        // Displaying the result
        System.out.println("Nodes at distance " + k + " from target " + target.val + ": " + result);
    }
}
