/**
    This is a typical Binary Search (BS) algo problem since it's a sorted array
    First Occurrence: Find position of the number using BS. if found, try going left to find first value
    Last Occurrence: FInd position of the number using BS. If found, try gpoing right to find the last value

    Note that apart from above, rest of the code is pure BS. SO we try to optimize the code and just add it in one function with a boolean for the above cases. The rest of the code remains the same.
 */


class Solution {

    //Lower bound
    public int findOccurrence(int nums[], int target, boolean findFirst) {
        int low = 0, high = nums.length - 1;
        int occurrence = -1;

        while(low <= high) {
            int mid = low + (high - low) / 2;

            // Maybe this is the ans.
            if(nums[mid] == target) {
                occurrence = mid;
                if(findFirst)
                    high = mid - 1; // look for a smaller index on the left that might be first occurence
                else
                    low = mid + 1;  // look for a larger index on the right that might be first occurence
            } else if(target > nums[mid]) {
                low = mid + 1;      // Target is higher, eliminate the left search space
            } else {
                high = mid - 1;     // Target is lower than mid, eliminate the right search space
            }
        }

        return occurrence;   // Return -1 if we could not find the ans, else return first occurrence
    }

    public int[] searchRange(int[] nums, int target) {
        int firstOcc = findOccurrence(nums, target, true);
        if (firstOcc == -1) return new int[] { -1, -1};     // Optimize if we could not find the first Occ..no point searching for last;
        int lastOcc = findOccurrence(nums, target, false);
        return new int[] {firstOcc, lastOcc};
    }
}