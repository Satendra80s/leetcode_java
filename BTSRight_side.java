import java.util.*;

// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class BTSRight_side {
    
    // Approach 1: Level Order Traversal (BFS) - Most Intuitive
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                
                // Add the rightmost node of each level
                if (i == levelSize - 1) {
                    result.add(node.val);
                }
                
                // Add children for next level
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        
        return result;
    }
    
    // Approach 2: DFS (Depth-First Search) - More Space Efficient
    public List<Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }
    
    private void dfs(TreeNode node, int level, List<Integer> result) {
        if (node == null) return;
        
        // If this is the first node we're visiting at this level,
        // it must be the rightmost (since we traverse right first)
        if (level == result.size()) {
            result.add(node.val);
        }
        
        // Traverse right first, then left
        dfs(node.right, level + 1, result);
        dfs(node.left, level + 1, result);
    }
    
    // Test method
    public static void main(String[] args) {
        BTSRight_side sol = new BTSRight_side();
        
        // Test case: [1,2,3,null,5,null,4]
        //       1
        //      / \
        //     2   3
        //      \   \
        //       5   4
        // Expected output: [1, 3, 4]
        
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);
        
        System.out.println("BFS Result: " + sol.rightSideView(root));
        System.out.println("DFS Result: " + sol.rightSideViewDFS(root));
    }
}
