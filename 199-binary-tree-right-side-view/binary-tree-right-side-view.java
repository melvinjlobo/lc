/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();

        if(root == null)
            return result;
        
        queue.add(root);

        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            for(int i = 0; i < levelSize; i++) {
                TreeNode curr = queue.remove();

                if(i == levelSize - 1)
                    result.add(curr.val);
                
                if(curr.left != null) queue.add(curr.left);
                if(curr.right != null) queue.add(curr.right);
            }
        }

        return result;
        
    }


    //DFS:
    /**
    Approach: DFS with Right-First Traversal
Here’s how the DFS solution works:

We start from the root and traverse the tree in a right-first order (visit the right child before the left child).
We maintain the current depth (or level) of traversal.
For each level, if it’s the first time we’re visiting that level, the current node is the rightmost node for that level.
We add this node’s value to the result list.
As we continue traversing the tree, we only add the first node we encounter at each depth (level) to the result, ensuring it’s the rightmost node for that level.
     */

     public List<Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }
    
    private void dfs(TreeNode node, int depth, List<Integer> result) {
        if (node == null) {
            return;
        }
        
        // If this is the first time we're visiting this depth, add the node's value
        if (depth == result.size()) {
            result.add(node.val);
        }
        
        // Right-first DFS
        dfs(node.right, depth + 1, result);
        dfs(node.left, depth + 1, result);
    }
}