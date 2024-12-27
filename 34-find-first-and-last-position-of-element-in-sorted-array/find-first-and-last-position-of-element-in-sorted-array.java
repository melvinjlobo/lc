/**
    This is a typical Binary Search algo problem since it's a sorted array
    The first position can be found with lower bound.The lower bound is the smallest index, ind, where arr[ind] >= x. But if any such index is not found, the lower bound algorithm returns n i.e. size of the given array.

    The last position is found with modified upper bound for == instead of >. The upper bound is the smallest index, ind, where arr[ind] > x. But if any such index is not found, the upper bound algorithm returns n i.e. size of the given array. For us, now it would be `arr[ind] == x`. The main difference between the lower and upper bound is in the condition. For the lower bound the condition was arr[ind] >= x and here, in the case of the upper bound, it is arr[ind] > x.


 */


class Solution {

    //Lower bound
    public int firstOccurrence(int nums[], int target) {
        int low = 0, high = nums.length - 1;
        int first = -1;

        while(low <= high) {
            int mid = low + (high - low) / 2;

            // Maybe this is the ans.
            if(nums[mid] == target) {
                first = mid;
                high = mid - 1; // look for a smaller index on the left that might be first occurence
            } else if(target > nums[mid]) {
                low = mid + 1;      // Target is higher, eliminate the left search space
            } else {
                high = mid - 1;     // Target is lower than mid, eliminate the right search space
            }
        }

        return first;   // Return -1 if we could not find the ans, else return first occurrence
    }

    // Upper Bound
    public int lastOccurrence(int[] nums, int target) {
        int low = 0, high = nums.length -1;
        int last = -1;

        while(low <= high) {
            int mid = low + (high - low) / 2;

            // This may be the ans
            if(nums[mid] == target) {
                last = mid;
                low = mid + 1;  // We found the last occurrence. Now keep going right to find the highest index
            } else if(target > nums[mid]) {
                low = mid + 1;      // Target is higher, eliminate the left search space
            } else {
                high = mid - 1;     // Target is lower than mid, eliminate the right search space
            }

        }

        return last;
    }

    public int[] searchRange(int[] nums, int target) {
        int firstOcc = firstOccurrence(nums, target);
        System.out.println("First - " + firstOcc);
        if (firstOcc == -1) return new int[] { -1, -1};     // Optimize if we could not find the first Occ..no point searching for last;
        int lastOcc = lastOccurrence(nums, target);
        System.out.println("last - " + lastOcc);
        return new int[] {firstOcc, lastOcc};
    }
}