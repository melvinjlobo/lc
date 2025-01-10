class Solution {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        while(low <= high) {
            int mid = low + (high - low) / 2;

            if(nums[mid] == target)
                return mid;

            // Check if left side sorted
            if(nums[low] <= nums[mid]) {
                // Check if value lies in this half
                if(nums[low] <= target && target <= nums[mid])
                    high = mid - 1;
                else        // else it is in this half
                    low = mid + 1;
            } else {    // Check if right side sorted
                // Check if value lies in this half
                if(nums[mid] <= target && target <= nums[high])
                    low = mid + 1;
                else    // else it is in this half
                    high = mid - 1;
            }

        }

        return -1;
    }
}