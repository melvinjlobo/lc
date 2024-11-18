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