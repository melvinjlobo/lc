/**
Steps to Find the Next Permutation
Identify the Pivot:

Start from the end of the array and find the first pair of numbers where nums[i] < nums[i + 1]. Let’s call i the pivot.
This is the number that we want to make "bigger" to get the next permutation. If no such i exists, the array is in descending order, so we skip to Step 4.
Find the Smallest Number Greater than the Pivot:

From the end of the array, find the smallest number that is larger than nums[i]. This number is the next largest value for the pivot's position.
Swap the Pivot with This Number:

Swap nums[i] with this next largest value found in Step 2.
Reverse the Array from i + 1 to End:

Reverse the portion of the array from i + 1 to the end to get the smallest possible order for this segment, ensuring the "next permutation" is the smallest possible.

Example: nums = [1, 3, 5, 4, 2]
We want to find the next lexicographical permutation of this array.

Step 1: Find the Pivot
Start from the end of the array and look for the first decreasing element:

Compare nums[3] (4) and nums[4] (2) → 4 > 2, so keep moving left.
Compare nums[2] (5) and nums[3] (4) → 5 > 4, so keep moving left.
Compare nums[1] (3) and nums[2] (5) → 3 < 5. Pivot = nums[1] (3).
The pivot is 3 at index 1.
 */


class Solution {
    public void nextPermutation(int[] nums) {
        int ptr = nums.length - 2; //-2 because we need to compare ptr+1 with ptr

        //Starting from the right, first find the pivot element. This is the first 
        //element from the right which descends to left
        //E.g 23421..Here 
        while(ptr >= 0 && nums[ptr] >= nums[ptr + 1])
            ptr--;
        
        //Now that we have our pivot, we need to find the next larger integer 
        //from our pivot from the right. Note that if ptr == 0 that means that we 
        //could not find the descending point. So we reverse the whole array
        // Also, since all the elements to the right of pivot are in descending 
        //order from left to right, we will not have a just larger element to the
        //left of pivot..It HAS to be to the right...
        if(ptr >= 0) {
            int nxt = nums.length - 1;
            System.out.println("nxt is: " + nxt + ", and ptr is: " + ptr);
            while(nxt > 0 && nums[nxt] <= nums[ptr]) {
                nxt--;
            }
            
            //Now swap the two numbers
            swap(ptr, nxt, nums);
        }
        reverse(nums, ptr + 1);

    }

    public void swap(int i, int j, int[] nums) {
       int temp = nums[i];
       nums[i] = nums[j];
       nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int end = nums.length - 1;

        while (start < end) {
            swap(start, end, nums);
            start++;
            end--;
        }
    }
}