/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 /**
 Steps:
Convert Tree to Graph:

1. Traverse the tree and create a map where each node points to a list of its neighbors (both parent and children).
2. Perform BFS:Starting from the target node, perform a Breadth-First Search (BFS) to find all nodes at distance K.
3. Use a Visited Set:Keep track of visited nodes to prevent revisiting and infinite loops
  */
class Solution {

    
    /**
        Function to build a graph recursively with DFS
     */
    private void buildGraph(TreeNode node, TreeNode parent, Map<TreeNode, List<TreeNode>> graph) {
        // Base case
        if(node == null)
            return;
        
        //pre-order, root. Add the node to parent && parent to node's adjacency list
        graph.computeIfAbsent(node, val -> new ArrayList<>()).add(parent);
        graph.computeIfAbsent(parent, val -> new ArrayList<>()).add(node);

        // left - right
        buildGraph(node.left, node, graph);
        buildGraph(node.right, node, graph);

    }


    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        //Graph
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();

        // Collections for DFS
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        //Step 1. Build grpah
        buildGraph(root, null, graph);

        //Step 2. Traverse the graph from the target node. Use BFS since it's simpler
        queue.offer(target);
        visited.add(target);
        int currentDistance = 0;

        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            // If the current distance is k, add all the nodes at this level to the result
            //Note: Once we find a level / distance == k, we don't need to go further as the 
            // distance will only increase from hereon. So, we break;
            if(currentDistance == k) {
                for(TreeNode node : queue) {
                    if(node != null)
                        result.add(node.val);
                }
                break;
            }

            //Continue BFS with all neightbors
            currentDistance++;
            for(int i = 0; i < levelSize; i++) {
                TreeNode curr = queue.poll();

                // Check if all neighbors are visited and prepare for BFS
                // Note that this is the same as adding two children (Left and Right) in the BFS queue
                for(TreeNode neighbor : graph.get(curr)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }

            }


        }

        return result;

    }
}