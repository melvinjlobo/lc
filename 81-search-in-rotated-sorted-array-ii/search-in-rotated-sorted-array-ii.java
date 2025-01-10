/**
    Here the logic is similar to find target in rotated sorted array. 
    NOTE:
    Except for one example where low, mid and high point to the repeated element, we will almost always
    be able to find the sorted half (left or right). For every other example, we can find atleast one sorted half
    E.g [6, 7, 1, 2, 3, 3, 5, 5] -> l = 6, m = 2, h = 5. Here we can make out that mid to high is sorted (m < h)
    [4, 5, 1, 2, 3, 3, 4, 4] -> l = 4, m = 2, h = 4. Even in this case, we can find sorted half (m < h)

    So, we cannot find sorted half iff we have an example like below:
    [3, 1, 2, 3, 3, 3, 3, 3] -> l = 3, m = 3, h = 3. Cannot find sorted half

    SO , this is the only extra step from the rotated sorted array I problem
 */

class Solution {
    public boolean search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while(low <= high) {
            int mid = low + (high - low) / 2;

            // If the target is found
            if(nums[mid] == target) return true;

            // Check if the low, mid and high pointer point to duplicates and take care of the only edge case here
            // NOTE: We cannot write a while loop here as we need to calculate mid everytime. If we want a while loop here, we must also calculate the mid here again since the while loop condition uses mid
            if(nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low++; 
                high--;
                continue;
            }

            // Rest of the problem is like Rotated Sorted Array I
            // Check if the left half is sorted
            if(nums[low] <= nums[mid]) {
                // Is the target in the left half?
                if(nums[low] <= target && target <= nums[mid])
                    high = mid - 1;
                else
                    low = mid + 1;
            } else {        // Right half is sorted
                if(nums[mid] <= target && target <= nums[high])
                    low = mid + 1;
                else
                    high = mid - 1;
            }


        }

        return false;
    }
}