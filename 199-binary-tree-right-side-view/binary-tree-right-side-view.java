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

Why depth == result.size() Works:

If depth == result.size(), it means we are visiting this depth level for the first time in our traversal.
Because we are using a right-first DFS traversal, the first node we visit at any depth is the rightmost node for that level.
Thus, when depth == result.size(), we add the current node’s value to result as it’s the rightmost node visible from that depth.
Example:
      1
     / \
    2   3
     \   \
      5   4

Start at Root (1) at Depth 0:

result.size() == 0 (no levels added yet).
depth == 0, so we add 1 to result: result = [1].
Traverse to the right child.
Move to Node (3) at Depth 1:

result.size() == 1 (we've only added level 0).
depth == 1, so we add 3 to result: result = [1, 3].
Traverse to the right child.
Move to Node (4) at Depth 2:

result.size() == 2.
depth == 2, so we add 4 to result: result = [1, 3, 4].
Backtrack to Node (2) at Depth 1:

depth == 1, but result.size() == 3, so we don’t add 2 to result because the rightmost node for this level has already been added.
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