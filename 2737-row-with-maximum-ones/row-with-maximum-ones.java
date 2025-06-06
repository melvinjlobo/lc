/***
Brute Force: For each row, count the number of 1's. Keep a variable which tracks max 1's in any row and update it
TC: O(nxm)

Binary Search (since we want to make the linear nxm TC smaller && the rows are sorted):
For each row, treat as a 1D sorted array. Find the first occurence of 1. Subtract from the number of elements in that row.
E.g if the row looks like : 0001111
First occurence of 1: 3 (0 based index)
Total number of 1's (Since the row is sorted and we only have zeroes and ones, the rest of the row is guaranteed to be only 1's) = total number of elements in the row - 3 -> 7-3 = 4
IMP NOTE: In this problem, the individual rows are NOT SORTED. So we hve to go through an extra step to sort them...
 */

class Solution {
    public int[] rowAndMaximumOnes(int[][] mat) {
        int maxOnes = 0, maxOnesRow = 0;
        //For each row, use BS to find the index of the first occurence of 1
        for(int row = 0; row < mat.length; row++) {
            //NOTE: Sort the row array before doing a BS on it
            Arrays.sort(mat[row]);
            int oneCount = findFirstOccurenceOfOne(mat[row]);
            if(oneCount > maxOnes) {
                maxOnes = oneCount;
                maxOnesRow = row;
            }
        }

        return new int[] {maxOnesRow, maxOnes};
    }

    // Index returns -1 if no 1's are found and number of Ones otherwise
    public int findFirstOccurenceOfOne(int[] arr) {
        int low = 0, high = arr.length - 1, index = -1;

        while(low <= high) {

            int mid = low + (high - low) / 2;

            // If mid is 1, we can probably go left to find the first occurence
            if(arr[mid] == 1) {
                index = mid;
                high = mid - 1;
            } else {    // Since we have a zero, we can move right to find 1's
                low = mid + 1;
            }
        }

        return (index != -1) ? (arr.length - index) : index;
    }
}