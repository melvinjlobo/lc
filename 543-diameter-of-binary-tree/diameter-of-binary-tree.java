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

 //Post-Order
class Solution {
    private int treeDiameter = 0;


    public int findDiameterRecursive(TreeNode curr) {
        if(curr == null)
            return 0;

        int leftTreeHeight = findDiameterRecursive(curr.left);
        int rightTreeHeight = findDiameterRecursive(curr.right);

        treeDiameter = Math.max(treeDiameter,  leftTreeHeight + rightTreeHeight);

        return Math.max(leftTreeHeight, rightTreeHeight) + 1;
    }
    
    public int diameterOfBinaryTree(TreeNode curr) {
        findDiameterRecursive(curr);
        return treeDiameter;
    }
}