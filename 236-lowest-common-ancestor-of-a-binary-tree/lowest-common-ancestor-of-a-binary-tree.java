/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    private TreeNode ans;
    
    public Solution() {
        this.ans = null;
    }
    
    // public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //     this.recurse(root, p, q);
    //     return this.ans;
    // }

    // /**
    // * Logic: Each node will have 3 ways to find out if this is the common ancestor:
    // * 1. mid: Current Node itself is one of either `p` or `q` - 1 if true
    // * 2. left: Current Node left subtree has either `p` or `q` - 1 if true
    // * 3. right: Current Node right subtree has either  `p` or `q` - 1 if true
    // * Add mid + left + right - If the value is >= 2 (two of the above conditions are true),
    // * we have found the common ancestor
    //  */
    // public boolean recurse(TreeNode currentNode, TreeNode p, TreeNode q) {
    //     //Reached a leaf Node
    //     if(currentNode == null)
    //         return false;

    //     int left = 0, mid = 0, right = 0;


    //     //Check the current Node
    //     mid = (currentNode.val == p.val || currentNode.val == q.val) ? 1: 0;

    //     //Check the left subtree
    //     left = recurse(currentNode.left, p, q) ? 1 : 0;

    //     // Check the right subtree
    //     right = recurse(currentNode.right, p, q) ? 1 : 0;

    //     //This means that we have satisfied LCA for this node (either the node or children are `p` or `q`)
    //     if (mid + left + right >= 2)
    //         this.ans = currentNode;
        
    //     // We return true if any of the current, left or right subtree is true since we need to propagate this up the tree.
    //     return (mid + left + right > 0);
    // }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val)
            return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null)
            return root;
        else if (left != null)
            return left;
        else
            return right;

    }
}