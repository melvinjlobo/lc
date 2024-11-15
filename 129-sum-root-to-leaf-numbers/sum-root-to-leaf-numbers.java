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
    int result = 0;

    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return result;
    }

    public void dfs(TreeNode curr, int sum) {
        if(curr == null) {
            return;
        }
        
        //Form the number...
        sum = sum*10 + curr.val;

        if(curr.left == null && curr.right == null)
            result += sum;
        else {
            dfs(curr.left, sum);
            dfs(curr.right, sum);
        }
    }
}