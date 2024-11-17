/**
    The idea is to keep track of the number of ones and keep increasing the window.
    when the window size - no. of ones > k, then we ned to reduce the window size 
    from the left to accommodate k. When we reduce the window from left, if we 
    encounter a 1, reduce it's count. Once we are done with this reduction, make
    a note of the max window size.
 */

class Solution {
    public int longestOnes(int[] nums, int k) {
        int windowStart = 0;
        int maxWindowSize = 0;
        int oneCount = 0;

        for(int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            if(nums[windowEnd] == 1)
                oneCount++;
            
            //Looks like the window went overboard. Reduce it until we meet
            //the `k` criteria
            while(((windowEnd - windowStart + 1) - oneCount) > k ) {
                if (nums[windowStart] == 1)
                    oneCount--;
                windowStart++;
            }

            //Keep noting the maxSize;
            maxWindowSize = Math.max(maxWindowSize, windowEnd - windowStart + 1);

        }

        return maxWindowSize;
    }
}