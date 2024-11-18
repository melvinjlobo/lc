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
    Approach 1
     */
    
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        
        //Step 1: Create a duplicate node for every node in the list beside it and interleave
        Node curr = head;

        while(curr != null) {
            Node newNode = new Node(curr.val, null, null);

            // Inserting the cloned node just next to the original node.
            // If A->B->C is the original linked list,
            // Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
            newNode.next = curr.next;
            curr.next = newNode;
            curr = newNode.next;
        }

        // Step 2. Assign Randoms
         curr = head;
         
        // Now link the random pointers of the new nodes created.
        // Iterate the newly created list and use the original nodes' random pointers,
        // to assign references to random pointers for cloned nodes.
        while(curr != null) {
            if(curr.random != null)
                curr.next.random = curr.random.next;
            if(curr.next != null)
                curr = curr.next.next; 
        }

         //Step 3: Detach the new nodes and remove interleaving
        Node oldList = head;
        Node newHead = head.next;
        Node newList = head.next;

        // Unweave the linked list to get back the original linked list and the cloned list.
        // i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'
        while(oldList != null) {
            oldList.next = (oldList.next != null) ? oldList.next.next : null; 
            newList.next = (newList.next != null) ? newList.next.next : null;

            newList = newList.next;
            oldList = oldList.next;
        }

        return newHead;
    }



    ///////////////////////////////////////
       
    /**
    Approach 1:
        */
    
    public Node getClonedNode1(Node node) {
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

    public Node copyRandomList1(Node head) {
        if(head == null)
            return null;
        Node curr = head;

        //Start the chain
        Node newNode = new Node(curr.val, null, null);
        this.visited.put(curr, newNode);

        while(curr != null) {
            newNode.random = getClonedNode1(curr.random);
            newNode.next = getClonedNode1(curr.next);

            curr = curr.next;
            newNode = newNode.next;

        }

        return this.visited.get(head);
    }
        
    
}