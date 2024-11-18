/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/


/**
NOTES:
- Problem:
Why a Simple Copy Fails?
In a singly linked list:

Each node has only one pointer (next), so copying the list is straightforward.
You can create a new node for every node in the original list and link them sequentially.
However, in this problem:

random Pointer Complexity:

The random pointer can point to:
Any node in the list, even one that appears before the current node.
null, meaning no random pointer.
A simple traversal doesn’t provide enough information to establish these random connections because you cannot determine the random target’s position easily without additional bookkeeping.
Deep Copy Requirement:

A deep copy ensures that the copied list is independent of the original. If you naively copy nodes and their connections, the random pointers in the copied list may still reference the original nodes, breaking the requirement.

Example of Failure with a Simple Copy
Original List:
rust
Copy code
1 -> 2 -> 3 -> null
|    |    |
v    v    v
3    1    2
Naive Approach
Copy nodes and next pointers:
rust
Copy code
1' -> 2' -> 3' -> null
Attempt to copy random pointers:
How do you know which random pointer in the original list maps to which in the copied list? Without additional mapping (e.g., a hashmap) or interleaving

Conclusion
A simple copy doesn’t work because of the additional complexity introduced by the random pointer.

------------------------------------------

Approach 1: Create a Hashmap to store visited Nodes. Whenever we traverse the existing list and try to re-create random, we will:
1. Create a new node if it doesn't exist in our visited hashmap, and mark it as visited. The random pointer will point to it
2. If a node already exists and is visited, return it.

This way, we don't create duplicate nodes. The space complexity in this case is O(N) though

Approach 2: Re-use the existing list. interleave new Nodes int he existing list (create a new node and point the original Node `next` to it, and the cloned node's next will point to the original node's `next`, this creating a chain). This way, the space complecity is O(1)


 */
class Solution {

    Map<Node, Node> visited = new HashMap<>();
       
    /**
    Approach 1:
        */
    
    public Node getClonedNode(Node node) {
        // If the node exists then
        if(node == null)
            return node;
        
        Node newNode;
        //Return existing Node
        if (this.visited.containsKey(node)) {
            newNode = this.visited.get(node);
        } else {    //Create a new node and return
            newNode = new Node(node.val, null, null);
            this.visited.put(node, newNode);
        }

        return newNode;
        
    }

    public Node copyRandomList(Node head) {
        if(head == null)
            return null;
        Node curr = head;

        //Start the chain
        Node newNode = new Node(curr.val, null, null);
        this.visited.put(curr, newNode);

        while(curr != null) {
            newNode.random = getClonedNode(curr.random);
            newNode.next = getClonedNode(curr.next);

            curr = curr.next;
            newNode = newNode.next;

        }

        return this.visited.get(head);
    }

        /**
        if (head == null) {
                return null;
            }

            Node oldNode = head;

            // Creating the new head node.
            Node newNode = new Node(oldNode.val);
            this.visited.put(oldNode, newNode);

            // Iterate on the linked list until all nodes are cloned.
            while (oldNode != null) {
                // Get the clones of the nodes referenced by random and next pointers.
                newNode.random = this.getClonedNode1(oldNode.random);
                newNode.next = this.getClonedNode1(oldNode.next);

                // Move one step ahead in the linked list.
                oldNode = oldNode.next; 
                newNode = newNode.next;
            }
            return this.visited.get(head); */

            /**
            if (node != null) {
                // Check if the node is in the visited dictionary
                if (this.visited.containsKey(node)) {
                    // If its in the visited dictionary then return the new node reference from the dictionary
                    return this.visited.get(node);
                } else {
                    // Otherwise create a new node, add to the dictionary and return it
                    this.visited.put(node, new Node(node.val, null, null));
                    return this.visited.get(node);
                }
            }
            return null;
             */
        
    
}