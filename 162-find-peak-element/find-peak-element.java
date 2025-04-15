/**
    One of the key elements to understand is that beyond both the left and right borders is "-infinity" as per the problem, so this looks like: "-inf [] -inf".
    The means that:
    1. If the first element is greater than the second element (and it obviously is greater than - infinity), so it is one of the peaks and we don't need to search the array
    2. If the last element is greater than the penultimate, it's obviously greater than the -inf outside the array bounds, so it is the peak element and we don't need to search the array further
    3. For any other element, we run a loop from 1 to n-2 (since we already handled the cases on the edges of the array) and we check:
        I. If the mid element is greater than either sides, then it is the peak
        II. else if it is on an increasing slope peaking towards the left (mid element greater than the one on right), remove the right half
        III. else we are on the increasing slope peaking towards the right, remove the left half

 */


class Solution {
    // public int findPeakElement(int[] nums) {

    //     int low = 0, high = nums.length - 1, mid = 0;

    //     while(low < high) {
    //         mid = low + (high - low) / 2;

    //         if(nums[mid] > nums[mid+1]) {
    //             high = mid;
    //         } else  {
    //             low = mid + 1;
    //         }
    //     }

    //     return low;
        
    // }

    public int findPeakElement(int[] nums) {
        int n = nums.length;

        if (n == 1) return 0;
        if(nums[0] > nums[1]) return 0;
        if(nums[n-1] > nums[n-2]) return n-1;

        int high = n-2, low = 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if((nums[mid] > nums[mid+1]) && 
                nums[mid] > nums[mid-1])
                return mid;
            else if(nums[mid] > nums[mid+1])
                high = mid - 1;
            else
                low = mid + 1;
        }

        return -1;
    }
}