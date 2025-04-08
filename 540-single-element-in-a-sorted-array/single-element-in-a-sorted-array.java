/**
 Logic: Let's look at the example below. Below the array has been marked `E` for an even position and `O` for odd position. The single element in this array is 4. if you look at each element from the beginning that occurs twice, you will notice that they follow the pattern (E,O), in that order. However, once we hit the single element, 4, it switches to (O,E) in that order. If the mid element is the single element, this just means that the mid element is not equal to it's left or to it's right. Also, past the single element, others will switch the order from (E,O) to (O,E). Now that we have identified patterns, how do we use binary search to remove the unwanted half? The steps are:

 1. Find the mid: if nums[mid] != nums[mid-1] && nums[mid] != nums[mid+1], nums[mid] is the ans
 else
 2. If the mid is O, check the right element (order is E,O) to see if it's the same. 
 3. If the mid is E, check the left element (order is E,O) to see if it's the same.
 4. If 2. and 3. are true, we have found the left, so the elment is in the right. low = mid + 1
 5. else, we are in the right and the element is in the left, high = mid - 1

 Special cases:
 We have to write special cases for:
 1.  nums[0]..something like if nums[0] != nums[1], return nums[0]
 2. nums[n-1]...something like if nums[n-1] != nums[n-2], return nums[n-1]

Handling these cases ensure we have a cleaner code and we don't have to handle these edge cases in the loop. We can also shorten the loop by one position on each side

 
 nums = [1,1,2,2,3,3,4,8,8] 
        [E,O,E,O,E,O,E,O,E]

 */


class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        if(nums[0] != nums[1]) return nums[0];
        if(nums[n-1] != nums[n-2]) return nums[n-1];

        int low = 1, high = n-2;

        while(low <= high) {
            int mid = low + (high - low) / 2;

            if(nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1])
                return nums[mid];
            
            //Are we on the left of the single element?
            if((mid % 2 == 0 && nums[mid] == nums[mid + 1]) ||
               (mid % 2 == 1 && nums[mid] == nums[mid - 1]))
               low = mid + 1;       // discard the left
            else
                high = mid - 1;
        }

        return -1;
    }
}