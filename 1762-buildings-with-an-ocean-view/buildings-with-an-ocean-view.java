/**
Traverse through the list of building heights. 
The idea is to push the bldgs in the stack. Ocen view to right means that all bldgs
in the stack should be in descending order from bottom to top...
In other words if the current building being processed is taller than the building at the top of the stack we need to pop since it will block the ocean view of the bldg at top of stack (as it is taller in height)
If stack is empty or the current building is shorter than the top of the stack push the building onto the stack.
At the end the stack will contain an ascending list of the buildings that have a view of the ocean.
 */

class Solution {
    public int[] findBuildings(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int start = 0;
        while(start < heights.length) {
            int currBldgHeight = heights[start];

            while(!stack.isEmpty() && currBldgHeight >= heights[stack.peek()]) {
                stack.pop();
            }

            stack.push(start);
            start++;
        }

        // The stack has all the bldgs that have ocen view, but in reverse order
        //pop them and fill them in an array
        int[] result = new int[stack.size()];
        
        for(int i = stack.size() - 1; i >=0; i--) {
            result[i] = stack.pop();
        }


        return result;
    }

    
}