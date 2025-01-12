/**
    Logic: Each block will have x units of water above the block. This x is dependent on the min height on either the left or right. Explanation below:
        For index 5 in [0,1,0,2,1,0,1,3,2,1,2,1] i.e. 0, the max on the left is index 3 i.e. 2 and the max on right is index 7 i.e. 3. These `walls` will hold the water in and around index 5 (including it). So we take the min of 2 and 3. Why? This is because if you think of a container, the max water it can store in terms of height is the smallest of the two walls. All other water will overflow out... Imagine if I have a container of left wall 2 and right wall 3, the height of water it can store will be 2 ...anything above two will overflow from the wall of height 2 and spill out...
Now that we have understood the height, we need to understand how much water can we store. Note that on index 5, the height of the block is zero and since the min height of the left and right block is 2, we can sotre 2 units of water. But if the height was 1, like for 4, we can store only 1 unit of water. Essentially, the amount of water we can store for a given block is:
Min(leftSide, rightSide) - height[i]

This is our equation. 

Brute force 1:
For each i, find the max leftSide from i back to `0` and max rightSide from 1 to `height.length - 1`. So, for each i, we find the max LeftSide, max rightSide by running two loops (0 to i-1 and i+1 to n-1) and then use the above formula to calculate how many units of water we can store (Min(leftSide, rightSide) - height[i])
TC - O(N ^ 2), SC - O(1)

Brute force 2:
Create two arrays, prefix and postfix. One runs from left to right and second one from right to left. The goal of these arrays is to store the max from from left at the given position (prefix array) and vice versa from the right. For the above example, we will get the arrays as:
prefix -  [0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3] <- Max from left for each position in heights
postfix - [3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 1] <- Max from right for each position in heights
So, when we run the loop for a given index, say 5, the max leftSide will be prefix[5] (2) and max rightSide will be postfix[5] (3). So then it becomes easy to calculate: Min(leftSide, rightSide) - height[i]
TC - O(N), SC - O(2N) {For each array, postfox and prefix}

Optimized (Two pointers):
For each element, we figure out if height[left] <= height[right]. If yes, we use formula (leftSideMax - height[i]). Similarly, if height[left] > height[right], we use rightSideMax - height[i]. The first `if` is to figure out which side of the wall is smaller (we did this earlier with Min(leftSide, rightSide)). The second one is to figure out how many units we need to store. We store leftSideMax as a running value since it is quite possible that the current height[left] may be smaller than some previous left that was bigger. This means we can store more water (leftSideMax). For example, for index 4, the left is index 4 and right is index 7. The height of index 4 is 1. And as we can see from the image, we can store 1 unit of water since index 3 has a height of 2, (leftSideMax - height[left]).
Once the calculation is done with either left or right side, increment or decrement it to fulfil the two pointer while condition
Once we do a dry run, we will understand more
 */


class Solution {
    public int trap(int[] height) {
        int leftSideMax = 0, rightSideMax = 0, left = 0, right = height.length - 1;
        int res = 0;

        while(left <= right) {
            if(height[left] <= height[right]) { // Pick min of either left wall or right wall
                if(height[left] >= leftSideMax)
                    leftSideMax = height[left];
                else
                    res += leftSideMax - height[left];
                // leftSideMax = Math.max(leftSideMax, height[left]);
                // res += leftSideMax - height[left];
                left++;
            } else {
                if(height[right] >= rightSideMax)
                    rightSideMax = height[right];
                else
                    res += rightSideMax - height[right];
                // rightSideMax = Math.max(rightSideMax, height[right]);
                // res += rightSideMax - height[right];
                right--;
            }
        }

        return res;
    }
}