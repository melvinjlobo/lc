/**
This is an interesting flavor of binary search and is much better
The idea can be explained with a dry run. Note that we can either start with row = 0, col = martix[0].length - 1 OR row = martix.length - 1, col = 0. For the sake of this example, let's go with the first option
Arr = 1   4   7   11   15
      2   5   8   12   19
      3   6   9   16   22
      10  13  14  17   24
      18  21  23  26   30

Target = 14. Note that all the rows and cols are sorted as per the problem

- We start with position 0,4 (15)
- Now, to the left of 15 in the row, the values are decreasing (11, 7, 4, 1) AND to the bottom of 15, values are increasing (19, 22, 24, 30). We want to find 14, so we know that it CAN'T be below 15, but it can be to the left of 15 (as 14 < 15), so we skip the column and go col--;
- Now we are at position 0, 3 (12)
- 14 > 12, so it can be below 12 in the same col, but it CANNOT be to the left of 12 as all values there are < 12. So, now we skip the row and go one row ahead (row++)
- Now we are at position 2, 3 (16)
- Here, we know that 14 < 16. This means that we cannot go down in the col as all values below are > 16 (17, 26). So , we go back one more col. col--, but stay in the same row as 14 < 16
- Now we are at 2, 2 (9)
- Here we know 14 > 9. This means we can go down the same col, but move one row ahead as we will not be able to find 14 there as all the values in the row to the left of 9 are smaller than it. So we go row++
- Now we are at position 3, 2 (14)
- Here, we found our target. So we peace out and send back the row and col

TC: O(n + m). How? If we see the steps, at each step, we either eliminate a row or col. This means that in worst case scenario, if we were to try to find 18, we would have to go 4 rows down and 4 cols left i.e. 8 steps, which are as follows:
15 -> 19 -> 12 -> 16 -> 17 -> 26 -> 23 -> 21 -> 18
 */


class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;
        int r = 0, c = m - 1;
        while(r < n && c >= 0) {
            int element = matrix[r][c];
            if( element == target)
                return true;
            else if(target > element)
                r++;
            else
                c--;
        }

        return false;
    }
}