/**
Brute Force: Go through every element and find the max

Logic:
 The general idea is that we will do a BS on cols. We move between the cols in a binary fashion. So what decides on which cols to eliminate?
THe idea is:
1. Find the `mid` col
2. Go through every row in the col and find the `max` element in that col. Why? That has the highest chance of being a peak, since the definition of peak in this problem is it is the largest number on either of the 4 sides (Top, Down, Left and Right).
3. Once we have the max element, check on either sides (mid - 1, mid + 1) to see if there are any numbers bigger than the max number we found in the `mid` column. This idea is similar to 1D peak element where we compare the mid to either sides to figure out if we are the peak or on an upward / downward slope.
4. If this we find a number bigger than the one we have, we will adjust high and low as we did in the 1D array problem..

Why does this work?

Lets' take the example matrix:
10 20 15
21 30 14
7  16 32

Here, `mid` will be Col no - 0 + 2 / 2 = 1. Now in Col 1, 30 is the highest. 

Since this is a 2D matrix, once we pick the largest element in the column, then we only have to look for the peak values in the other dimension (row), so we actually halve our problem by picking the max in the column (as it is the peak in that col).

Intuition analogy:

Think of a column as a vertical ridge in the mountains.
	•	You’re trying to climb to the highest point on the ridge before looking around to see if it’s the highest peak in the area.
	•	That gives you the best chance to either find the peak or decide which direction to go.

Guarantee of progress:
If mat[i][j] (the column max) is not a peak, then:
	•	One of its left or right neighbors is greater.
	•	So, a peak must exist in that direction.
	•	That gives us a valid direction to move in binary search.
    
We find the global max in a column because:
	•	It gives us the most promising candidate in that column.
	•	If it’s not a peak, it guarantees a better neighbor exists in adjacent columns.
	•	This helps our binary search converge faster.
 */


class Solution {

    // For a given column, find the row with the max value
    public int findMaxInCol(int[][] mat, int col) {
        int maxRow = 0;
        for(int i = 0; i < mat.length; i++) {
            if(mat[i][col] > mat[maxRow][col])
                maxRow = i;
        }

        return maxRow;

    }

    public int[] findPeakGrid(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int low = 0, high = n - 1;

        while(low <= high) {
            int mid = low + (high - low) / 2;

            //Get the peak vertically
            int maxRowInCol = findMaxInCol(mat, mid);

            // Now check for the peak horizontally 
            int left = (mid - 1 >= 0) ? mat[maxRowInCol][mid - 1] : -1;
            int right = (mid + 1 < n) ? mat[maxRowInCol][mid + 1] : -1;
            if(mat[maxRowInCol][mid] > left && mat[maxRowInCol][mid] > right)
                return new int[] {maxRowInCol, mid};
            else if (mat[maxRowInCol][mid] < left)
                high = mid - 1;     // The peak is on the left
            else
                low = mid + 1;      // The peak is on the right
        }

        return new int[] {-1, -1};      // In case we are not able to find anything
    }
}