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

 /**
  The general idea was to keep track of the smallest diff (Note: Use Math.abs) and
  keep comparing the diff of the current node with the smallest diff. If it is smaller, then pick that value as the closestValue. Edge case: If the diff is equal to the smallestDiff, pick the Node with the smaller value. 
  E.g. in one of the test cases, I encountered diff = 0.5 with Node 4. Then I encountered the same diff, 0.5 with Node 3. The answer is Node 3. So I added the 
special case.
  */
class Solution {
    int closestValue = 0;
    double smallestDiff = Integer.MAX_VALUE;    // Keep this max so that we can pick the smallest diff
    public int closestValueDFS(TreeNode root, double target) {
        closestValue = (root != null) ? root.val : 0;
        helper(root, target);
        return closestValue;
    }

    public void helper(TreeNode root, double target) {
        if(root == null)
            return;
        
        double diff = Math.abs(target - root.val);
        if(diff < smallestDiff) {
            smallestDiff = diff;
            closestValue = root.val;
        } else if(diff == smallestDiff) {
            closestValue = (root.val < closestValue) ? root.val : closestValue;
        }

        helper(root.left, target);
        helper(root.right, target);
    }

    /**
    Solution with Binary Search:

    Complexity is better O(H), where H is height of the tree , rather than O(N) for the above solution

    BST Property Recap

    For any node X:
        1.	All values in the left subtree of X are less than X.val.
        2.	All values in the right subtree of X are greater than X.val.

    This property allows us to compare the target value with the current node’s value to decide which branch (left or right) to explore without needing to check every node.

    How Traversal Works

At every node:
	1.	Compare the target value with the current node’s value.
	2.	Based on the comparison:
	•	If target < current.val, we move to the left child because the left subtree contains smaller values that could be closer to the target.
	•	If target > current.val, we move to the right child because the right subtree contains larger values that could be closer to the target.

By doing this, we eliminate half of the tree at every step and focus only on the subtree that could potentially have the closest value to the target.

Why It Works

The BST property guarantees that:
	•	If the target is smaller than the current node’s value, then:
	•	Any potential closer value must be in the left subtree.
	•	The right subtree can be ignored because all values there are larger (and hence farther from the target).
	•	If the target is greater than the current node’s value, then:
	•	Any potential closer value must be in the right subtree.
	•	The left subtree can be ignored because all values there are smaller (and hence farther from the target).

This decision-making ensures we traverse only one branch of the tree at each step.
Let’s revisit the following BST:

       10
      /  \
     5    20
    / \   /
   3   8 15

   Suppose the target = 12. Here’s how the traversal works:
	1.	Start at the root (10):
	•	target = 12 > 10, so move to the right child (20).
	•	Why? Because the right subtree has values greater than 10, which might be closer to 12.
	2.	At node 20:
	•	target = 12 < 20, so move to the left child (15).
	•	Why? Because the left subtree of 20 has values smaller than 20, which might be closer to 12.
	3.	At node 15:
	•	target = 12 < 15, so we attempt to move to the left child, but it’s null.
	•	At this point, we stop traversing.
    
    Result

During the traversal, we can check the closeness of each visited node’s value to the target. In this example:
	•	Nodes visited: 10 → 20 → 15.
	•	Closest value: 15 (as it is closest to 12).

    Why It’s Efficient
	1.	The BST structure allows us to eliminate half the tree at every step.
	2.	In a balanced BST:
	•	The height of the tree is O(log N), where N is the number of nodes.
	•	Thus, we only visit O(log N) nodes in the worst case.

In contrast, if we were to perform a brute-force traversal of all nodes, the time complexity would be O(N).

Key Insight

The ability to decide whether to move left or right at each step works because:
	1.	The BST property guarantees that smaller values are on the left and larger values are on the right.
	2.	This property allows us to narrow the search space efficiently by only exploring the relevant subtree.
     */

     public int closestValue(TreeNode root, double target) {
        // Initialize the closest value as the root's value
        int closestVal = root.val;

        // No recursion required. Just a while loop for Binary search will do
        while(root != null) {
            // Update closest if the current value is closer to the target
            // The second condition  stmt handles the case when the difference is equal between two nodes and 
            //we need to pick the smaller one. The above case of nodes 3 and 4 and target 3.5. The diff is 0.
            //5 for both nodes, but we need to pick 3 as the answer
            if(Math.abs(root.val - target) < Math.abs(closestVal - target) ||
                (Math.abs(root.val - target) == Math.abs(closestVal - target) && root.val < closestVal))
                closestVal = root.val;
            
            //Decide to move left or right based on potential values which can reduce the difference
            //This is based on property of binary tree as explained above
            if(target < root.val)
                root = root.left;       // Go left if target is smaller
            else
                root = root.right;      // Go right if target is larger
        }

        return closestVal;

     }

    
}