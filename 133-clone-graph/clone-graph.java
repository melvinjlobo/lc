/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/


/**
To solve the problem, we use:

DFS (Depth-First Search) or BFS (Breadth-First Search) to traverse the graph.
A HashMap to store mappings of the original nodes to their cloned counterparts. This ensures that:
A node is cloned only once.
The neighbors of each node are correctly assigned.

Code WalkThrough and Approach
A very simple approach is to use a hashmap to keep track of all the nodes and their clones . Lets walk through the code and get a better understanding.

In the below part of the code , we set up a hashmap to track clones and a queue to perform breadth first search. It is important because we want to keep track of an entire level at a time.

After that , we add a node to the queue and also add that node to the map as a key and it's cloned node as value.

Now , moving forward and using a while loop we remove the front of the queue and store it in curr. As we do this , we retrieve the value of this node which is the cloned node that we added in the first step from the map and store it in the currClone.

Why all of this ?
Because we need to also keep track of the neighbor nodes and their clones too to make sure we have a correct clone of the original graph.
That's what we are doing in the below part of the code.

Using a loop , we store each element of the neighbors list inside neighbor . if the map doesn't contain that node already , we add it as a key and it's clone as the value and add that node(original) node in the queue to process it's neighbors next.

Then , we retrieve the value of the neighbor (which is the cloned neighbor) and make it as the neighbor of the cloned node just like it should be from the original graph!

IMP NOTE: We use the queue for the current Nodes and the map to get clones. SO always  add curr node to the queue and put the cloned Node in the map. it's easy to mix up these two

Complexity O(V + E)
 */

class Solution {
    // Use BFS
    public Node cloneGraphBFS(Node node) {
        // Null scenario
        if (node == null) return node;

        Queue<Node> queue = new LinkedList<>();
        Map<Node, Node> cloneMap = new HashMap<>();

        Node cloneHead = new Node(node.val);
        cloneMap.put(node, cloneHead);
        queue.add(node);
        
        while(!queue.isEmpty()) {
            Node curr = queue.poll();
            Node clone = cloneMap.get(curr); //Note: we will always have a Node for the node that we 
            //push in the queue

            for(Node nodeN : curr.neighbors) {
                //Get or clone the neighbor
                Node clonedN = null;
                if(!cloneMap.containsKey(nodeN)) {
                    clonedN = new Node(nodeN.val);
                    cloneMap.put(nodeN, clonedN);
                    queue.add(nodeN);
                } else {
                    clonedN = cloneMap.get(nodeN);
                }
                
                //Add to the neighbor list of the clone
                clone.neighbors.add(clonedN);
            }
            
        }

        return cloneMap.get(node);
        
    }

    /**
        Use : DFS
        The basic idea is that the recurrent work is: If there is a clone, return (base case),
        else, create a new Node, add it to the map. Now run a for loop to add it's cloned neighbors by 
        iterating over the original graph neighbors. We will need to get teh cloned nodes from the
        recursed helper function so that it takes care of cloning the node and it's neighbors
    */
    public Node cloneGraph(Node node) {
        if (node == null) return null;

        Map<Node, Node> cloneMap = new HashMap<>();
        Node newHead = dfsHelper(node, cloneMap);

        return newHead;
    }

    public Node dfsHelper(Node node, Map<Node, Node> cloneMap) {
        //Base case: if you have a clone, return
        if(cloneMap.containsKey(node))
            return cloneMap.get(node);
        
        //Create a new node
        Node newNode = new Node(node.val);
        cloneMap.put(node, newNode);

        // Clone neighbors
        for(Node neighbor: node.neighbors) {
            newNode.neighbors.add(dfsHelper(neighbor, cloneMap));
        }

        return newNode;
    }
}