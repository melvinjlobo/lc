/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

class Solution {
    Node prev = null;
    Node head = null;
    public Node treeToDoublyList(Node root) {
        if (root == null)
            return null;

        dfsHelper(root);

        prev.right = head;
        head.left = prev;

        return head;
    }

    public void dfsHelper(Node curr) {
        // Do the inorder traversal: left - root - right
        if (curr == null)
            return;

        dfsHelper(curr.left);
        
        if(prev == null) {
            head = curr;
        } else {
            prev.right = curr;
            curr.left = prev;
        }

        prev = curr;

        dfsHelper(curr.right);

    }
}