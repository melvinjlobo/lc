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
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        //Corner case
        if(root == null)
            return result;

        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        Queue<Pair<Integer, TreeNode>> queue = new ArrayDeque<>();

        //Start
        int colIndex = 0;
        queue.add(new Pair<Integer, TreeNode>(colIndex, root));
        int minCol = 0, maxCol = 0;
        while(!queue.isEmpty()) {
            Pair<Integer, TreeNode> currPair = queue.remove(); 
            int currCol = currPair.getKey();
            TreeNode currNode = currPair.getValue();

            if(currNode != null)  {
                minCol = Math.min(minCol, currCol);
                maxCol = Math.max(maxCol, currCol);
                // System.out.println("Curr col - " + currCol);
                // System.out.println("curr Node - " + currNode.val);

                // List<Integer> valList = indexMap.getOrDefault(currCol, new ArrayList<Integer>()) ;
                // valList.add(currNode.val)
                // indexMap.put(currCol, valList);

                indexMap.computeIfAbsent( currCol, v -> new ArrayList<Integer>()).add(currNode.val);


                queue.add(new Pair(currCol - 1, currNode.left));
                queue.add(new Pair(currCol + 1, currNode.right));
            }
        }

        //Sort the keys first. This is a weird step, but we have to do it
        /**
        System.out.println("Keyset is - " + indexMap.keySet());
        List<Integer> sortedKeys = new ArrayList<>(indexMap.keySet());
        Collections.sort(sortedKeys);
        for(int key: sortedKeys) {
            System.out.println("Adding list for col - " + key + ":\n" + indexMap.get(key));
            result.add(indexMap.get(key));
        }
         */

        //Avoid sorting. Just Store the min col and max col...Since the columns are made up artificially, we can just run a loop in a range from min to max
    for(int ctr = minCol; ctr <= maxCol; ctr++) {
        //System.out.println("Adding list for col - " + ctr + ":\n" + indexMap.get(ctr));
        result.add(indexMap.get(ctr));
    }
       
        
        return result;

    }
}