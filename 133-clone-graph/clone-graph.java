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

class Solution {
    // Use BFS
    public Node cloneGraph(Node node) {
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
}