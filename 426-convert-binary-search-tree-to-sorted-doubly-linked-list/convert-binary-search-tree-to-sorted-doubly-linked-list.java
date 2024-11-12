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


/***

Key Points of the Approach
In-order Traversal: In a BST, in-order traversal naturally yields nodes in ascending order.
Link Nodes While Traversing: As we traverse, we keep a prev pointer to the last visited node. For each new node:
Set prev.right to the current node.
Set the current node's left to prev.
Connect Head and Tail: Once traversal is complete, connect the head and tail nodes to make the list circular.
Steps
Define a head pointer for the head of the list (the smallest node).
Define a prev pointer that keeps track of the previously visited node during in-order traversal.
During traversal:
If prev is null, set head to the current node (this happens only for the first node).
Otherwise, link prev to the current node and the current node back to prev.
Update prev to the current node.
After traversal is complete, link head and prev to close the circular doubly-linked list.
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