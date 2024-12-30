/**
    Observation:
        1. When we move upwards, we ALWAYS start FROM left or bottom and switch when we hit TOP or RIGHT
        2. When we move downwards, we ALWAYS start from right or top and switch when we hit LEFT or BOTTOM
    So, for 1. we need to check if we hit the left or bottom boundaries of the matrix and then increment either row or col accordingly. Similarly for 2. we need to check boundaries and modify either row or col accordingly. If we are not on the boundaries, we simply change row and col both as we are in the middle of the matrix and have to move either upwards or downwards depending on the direction.
    To keep a track of whether we are in the upwards or downwards direction, we can use (r+c) % 2. r+c determines which diagonal you are on. Even is upwards and odd is downwards.

    ** IMP NOTE **
    The sequence of the conditions matter:
    Why This Sequence Works:
    The order of checks matters not based on which boundary is hit first physically, but based on how the code behaves when processing elements diagonally.

When moving upward, there are two cases to consider:
	1.	Reaching the right boundary happens only for elements in the last column.
	2.	Reaching the top boundary happens for all other elements before the right boundary.
When you’re moving upward and hit the right boundary, the next valid move is downward from the next row. However:
	•	If the right boundary condition is not checked first, you’ll overwrite it with the top boundary logic, causing incorrect behavior.
	•	Checking the right boundary first ensures that all elements in the last column are correctly processed.

The sequence matters because it prioritizes the natural flow of diagonals:
	1.	When moving upward:
        •	The natural flow is top-right ( r–, c++ ).
        •	If you hit the right boundary, you must stop moving right and go down.
        •	If you hit the top boundary, you stop moving up and go right.
	2.	When moving downward:
        •	The natural flow is bottom-left ( r++, c– ).
        •	If you hit the bottom boundary, you must stop moving down and go right.
        •	If you hit the left boundary, you stop moving left and go down.

    TC - m*n
 */

class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        // Edge case
        if(mat.length == 0 || mat[0].length == 0) {
            return new int[0];
        }

        // Initialize variables
        int rows = mat.length;
        int cols = mat[0].length;
        int[] result = new int[rows * cols];

        // Start traversal
        int r = 0, c = 0, idx = 0;

        while(idx < (rows * cols)) {
            System.out.println("idx = " + idx);
            System.out.println("r = " + r + ", c = " + c);
            result[idx] = mat[r][c];

            if((r + c) % 2 == 0) { // Moving upwards
                // We are on the rightmost boundary. Move One row down in the same col to switch to the downwards journey.
                //This condition needs to be checked first as explained above since the natural order when moving up is to hit top boundary first and then we move right. If we keep doing this, there is a chance that we will go over bounds when moving right, so we check right first.
                if(c == cols - 1) {
                    System.out.println("Up Case 1");
                    r++;
                }
                // We are in top row and moving upwards, so we hit the top boundary. Move one col to the right to switch to the downwards journey
                else if( r == 0) {
                    System.out.println("Up Case 2");
                    c++;
                }
                // We are in the middle of the matrix, continue upwards
                else {
                    System.out.println("Up Case 3");
                    r--;
                    c++;
                }

            } else {        // Moving Downwards
                
                // We have hit the bottom boundary and moving downwards. Move one col to the right to  switch to the upwards journey
                //This condition needs to be checked first as explained above since the natural order when moving down is to hit left boundary first and then we move down. If we keep doing this, there is a chance that we will go over bounds when moving down, so we check down first.
                if(r == rows - 1) {
                    System.out.println("Down Case 2");
                    c++;
                }
                // We have hit the leftmost boundary and moving downwards. Move one row down to switch to the upwards journey
                else if(c == 0) {
                    System.out.println("Down Case 1");
                    r++;
                }
                // Else we just continue downwards
                else {
                    System.out.println("Down Case 3");
                    c--;
                    r++;
                }
            }

            idx++;
        }

        return result;
    }
}