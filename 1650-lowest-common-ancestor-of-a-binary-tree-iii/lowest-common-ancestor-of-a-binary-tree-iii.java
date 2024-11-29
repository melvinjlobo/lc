/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {

        Set<Node> ancestors = new HashSet<>();

        if(p == null || q == null)
            return null;
        
        //Add all p's ancestors to this Hash set, right up to the root
        while(p != null) {
            ancestors.add(p);
            p = p.parent;
        }

        //Starting from q, keep moving upwards (parent, grandparent and so on), until we reach an ancestor that is also a part of
        // the set that has p's ancestors. That is the LCA
        while(!ancestors.contains(q)) {
            q = q.parent;
        }

        return q;
    }


    ///// ALTERNATIVES
    ///ALTERNATIVE 1. Use heights:
    // 1. Find Height of each p and q
    // 2. Reduce both heights until they are the same with each other
    // 3. Now that they are at the same height, keep going upwards in tandem, and they will meet at some point
    /*
     public Node lowestCommonAncestor(Node p, Node q) {
        int pDepth=0, qDepth=0;
        // Get the depth of p
        Node next=p;
        while(next!=null) {
            next=next.parent;
            pDepth++;
        }
        // Get the depth of q
        next=q;
        while(next!=null) {
            next=next.parent;
            qDepth++;
        }
        // If p is deeper than q bring it to the same level
        while(qDepth<pDepth) {
            p=p.parent;
            pDepth--;
        }
        // If q is deeper than p bring it to the same level
        while(pDepth<qDepth) {
            q=q.parent;
            qDepth--;
        }
        // Now that they are in the same level we are sure
        // that is we increase the level of both together
        // they will meet in the LCA
        while(p!=q) {
            q=q.parent;
            p=p.parent;
        }
        return q;
    }
    */


    // ALTERNATIVE 2. Find the root.. Onve the root is found, it is regular LCA
    //
    /*
    public Node lowestCommonAncestor(Node p, Node q) {
        Node root = findRoot(p);
        return lowestCommonAncestor1(root, p, q);
    }

    private Node findRoot(Node p) {
        Node root = null;
        while(p != null ) {
            root = p;
            p = p.parent;
        }
        return root;
    }
    //


    //Original LCA. Root Node given, p and q given
    //
    public Node lowestCommonAncestor1(Node root, Node p, Node q) {
        if(root == null || root.val == p.val || root.val == q.val)
            return root;
        
        Node left = lowestCommonAncestor1(root.left, p, q);
        Node right = lowestCommonAncestor1(root.right, p, q);

        if (left != null && right != null)
            return root;
        else if (left != null)
            return left;
        else
            return right;
    }*/


    //ALTERNATIVE 3. Fast pointer , slow pointer
    /*
    In this solution (code below), we are maintaining two pointers pointerP and pointerQ, initialized to the node P and Q respectively. Then we keep traversing the tree upwards towards the root of the tree using the parent reference in each node.

When pointerP reaches the root, then we will redirect it to the node Q. Similarly when pointerQ reaches the root, then we will redirect it to the node P. If at any point pointerP == pointerQ, then pointerP or pointerQ is the Lowest Common Ancestor (LCA) of node P and Q.

Let us consider the following binary tree that fulfills all the given constraints.

          3
         /
        5
         \
          2
           \
            4
Root Node = 3, P = 5, Q = 4
Path from node P to root: 5 -> 3
Path from Node Q to root: 4 -> 2 -> 5 -> 3
In this case, pointerP will reach the root node first, as it's closer to the root node and thus has to travel less distance. But pointerQ will take three hops to reach the root node. The difference in depth of both the nodes is two. By redirecting the pointerP to node Q, and pointerQ to node P, we are making pointerP travel two extra hops and pointerQ travel two fewer hops. Thus, they end up traveling the same number of hops and are guaranteed to reach the lowest common node at the same time.

Total number of hops required to find the LCA = Depth of P + Depth of Q - Depth of LCA node.

Time Complexity = O(depthP + depthQ - depthLCA)
depthP -> Depth of the Node p
depthQ -> Depth of the Node q
depthLCA -> Depth of the LCA

Space Complexity = O(1). We are using constant space in this solution.

********************* CODE ****************************

 public Node lowestCommonAncestor(Node p, Node q) {
        // Invalid Input Condition
        // Node P and Q must exist in the tree as per the given constraint
        if (p == null || q == null) {
            throw new IllegalArgumentException("Invalid Input");
        }
        
        // Optional Base Condition. Due to the given constraint (p != q), this check is redundant
        // If both the nodes are the same, then the LCA is P or Q.
        if (p == q) {
            return p;
        }
        
        // Pointer to Node P
        Node pointerP = p;
        
        // Pointer to Node Q
        Node pointerQ = q;
        
        // This while loop will run till both the node pointers refer to the same node.
        // Once both the node pointers are referring to the same node, that value will be the Lowest Common Ancestor of node P & Q.
        while (pointerP != pointerQ) {
            
            // Moving the pointers to their parent nodes. 
            pointerP = pointerP.parent;
            pointerQ = pointerQ.parent;
            
            // If both pointers reach null at the same time, then Node P and Q are not part of the same tree.
            if (pointerP == null && pointerQ == null) {
                throw new IllegalArgumentException("Node P & Q are not part of the same tree");
            }
            
            if (pointerP == null) {
                // If pointerP reaches the root node of the tree, then we will redirect it to node Q
                pointerP = q;
            } else if (pointerQ == null) {
                // If pointerQ reaches the root node of the tree, then we will redirect it to node P
                pointerQ = p;
            }
        }
        
        // Both pointerP and pointerQ point to the Lowest Common Ancestor node.
        return pointerP;
    }
    */
}