/**
We take two pointers, one at the beginning and one at the end of the array constituting the length of the lines. Further, we maintain a variable maxarea to store the maximum area obtained till now. At every step, we find out the area formed between them, update maxarea, and move the pointer pointing to the shorter line towards the other end by one step.

Time complexity: O(n). Single pass.

Space complexity: O(1). Constant space is used.

 */

class Solution {
    public int maxArea(int[] height) {
        
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while(left < right) {
            int containerWidth = Math.abs(right - left); // Calculate the width of the container
            int containerHeight = Math.min(height[left], height[right]); // Calculate the height of the container 
            int area = Math.abs(containerHeight * containerWidth);
            maxArea = Math.max(maxArea, area);
            if(height[left] > height[right])
                right--;
            else 
                left++;
        }

        return maxArea;
    }
}