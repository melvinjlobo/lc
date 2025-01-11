/**
    Logic:
        We know that the minimum is the beginning of a sorted array. If we have a rotated sorted array, the min point will be the pivot point of the rotation. In Binary search, when we split the array between 2 halves, it has the property where one half will ALWAYS be sorted (this is true for all rotated sorted problems). In the sorted half, we are interested in ONLY ONE element: the minimum..aka arr[low]. All other elements in the sorted half are useless to us since we want to find the min. So, we can make a note of it and then discard the entire sorted half. Another point to note is that the unsorted half MAY have the minimum:
        Example: [4, 5, 6, 7, 0, 1, 2] -> l = 4, m = 7, h = 2 -> m to h is not sorted, but has minimum
        Example: [7., 8, 1, 2, 3, 4, 5, 6] -> l = 7, m = 2, h = 6 -> m to h IS sorted and has minimum

So, the logic is that whenever we find a sroted half, we make a note of the min of that half (low or mid) until low > high

 */

class Solution {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        int min = Integer.MAX_VALUE;

        while(low <= high) {
            int mid = low + (high - low) / 2;

            /**
                This is an optimization step. May or may not be required to do this. When we eliminate the sorted half, if the second half is also sorted, then we have a complete sorted array. This means that we have just about moved out of the rotation space and entered the part of the array that is completely sorted. In this case, the low is the minimum
                Example: [4, 5, 6, 0, 1, 2] -> l = 4, m = 0, h = 2
                Here, l to m is sorted and m to h is also sorted. This is the case where l < h
             */
             if(nums[low] <= nums[high]) {
                min = Math.min(min, nums[low]);
                break;  // We are done. No further BS required
             }


            if(nums[low] <= nums[mid]) {    // Left is sorted
                min = Math.min(min, nums[low]);     // Change the min if it's less than what we have from the left (aka low)
                low = mid + 1;          // Eliminate the left half since we got what we need from that half
            } else {                // right is sorted
                min = Math.min(min, nums[mid]);     // Change the min if it's less than what we have from the right (aka mid)
                high = mid - 1;         // Eliminate the right half since we got what we need from that half
            }

        }

        return min;
    }
}