/**
    Brute Force: Just browse through the matrix and find the element
    TC: O(nxm)
    n - rows of matrix
    m - cols of matrix

    Binary Search:
    To improve O(nxm), we need to go logarithmic. One way we can do this is to flatten the list and then do a binary search since each row is sorted. For example the matrix below is converted to a flat list
    M = 1   2  8
        9  11  12   -> [1, 2, 8, 9, 11, 12, 13, 16, 18]
        13 16  18

    But in order to do that we need to copy elements one by one into the array which again is O(nxm) as we have to traverse the entire array. So, we have to find a way to apply BS on the matrix itself without the 1D flattening. To do this, all we need to do is create a fake flatten and convert the 1D array position to 2D array position.
    Example: 
    Array - [1, 2, 8, 9, 11, 12, 13, 16, 18]
    Index -  0, 1, 2, 3, 4 , 5 , 6 , 7 ,  8

    Now lets say we find the mid = 0 + (8 - 0) / 2 = 4.  <-- idx
    How do we translate index 8 in the flat array to the 2D array? We do it with the below formulas:
    - row = idx / m
    - col = idx % m

    Let's convert 4 to the position in the 2D array and see if we indeed land up on the mid element in the flat array (11)
    row = 4/3 = 1
    col = 4%3 = 1
    position = [1, 1]
    As we see indeed in the matrix, the element at position [1, 1] is 11

    We can try other positions as well. Once we find the `mid`, we then compare the target element with mid and as in typical BS, we will move ahead with recalculation of low or high dependeing on the situation until we find the element


 */
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;
        int low = 0, high = n * m - 1; //n * m = total number of elements in the array

        while(low <= high) {
            int mid = low + ( high - low ) / 2;

            // Translate this position into a valid row and col in the 2D matrix to find the value of the element
            int row = mid / m;
            int col = mid % m;
            int element = matrix[row][col];

            if ( element == target )
                return true;
            else if(target > element)
                low = mid + 1;  // target on the right. Discard left half
            else
                high = mid - 1; // Target on the left. Discard right half
        }

        return false;
    }
}