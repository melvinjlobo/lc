/**
Brute Force:
    Go through every row and create a 1D array (O(nxm)), then sort it (O(log(nxm))). Now the median is the N/2th element (as per the definition of median). But why N/2 (a typical odd elements calculation) and not N/2 - 1, a typical even element calculation? Because the question itself  states that m and n are ODD and multiplication of 2 odd elements is always ODD. So complexity is O(mxn log(mxn))

Logic:
The optimum solution is a binary search solution as we have to move away from the linear mxn solution.. The only way to better it is a log based solution

E.g:
matrix = [
  [1, 3, 5],
  [2, 6, 9],
  [3, 6, 9]
]

Flattened and sorted: [1, 2, 3, 3, 5, 6, 6, 9, 9]
The median is the middle element, i.e., the 5th element

We know:
Since the number of elements are odd, if we consider a sorted 1D array, the median will divide the elements into two halves with equal number of elements on either side. 
For example: if m = 3 and n = 3, total elements = 3*3 = 9, then the median will be elment no 5 ((9 + 1)/2), which will divide the 1D matrix into 4 elements on either side of the median (0-3, 5-8). This is an important point since it means that if we can actually figure out the number of elements that are smaller than our assumed median and it is equal to m*n / 2, we have our answer or median.
One way to do this is:
Assume a median. For each row, figure our how many elements are below the median and count them. The final count will be the number of elements below the median. If it it more than N/2, we need a smaller number as our median assumption and vice versa
Instead of doing it linearly for each row (worst case, mxn), we can do a binary search over the answer space. 
But what is the original low and high here. One way is to take column 0 (since each row is sorted) and find the lowest. Same with Column n-1 and find the highest (again, using the each row is sorted property ). Now we have min and max for our search space. 

Now, how do we do this? Let's take the above example:

matrix = [
  [1, 3, 5],
  [2, 6, 9],
  [3, 6, 9]
]

 Iteration 1
	•	low = 1, high = 9
	•	mid = (1 + 9) / 2 = 5
	•	Count how many numbers ≤ 5 in each row:
    Row         Elements ≤ 5            Count
    [1, 3, 5]   1, 3, 5                 3
    [2, 6, 9]   2                       1
    [3, 6, 9]   3                       1

    Total = 5
    That’s enough to be the median. But maybe there's something better, so high = mid -1

 Iteration 2
	•	low = 1, high = 5
	•	mid = (1 + 5) / 2 = 3
    Row         Elements ≤ 5            Count
    [1, 3, 5]   1, 3                    2
    [2, 6, 9]   2                       1
    [3, 6, 9]   3                       1

    Total = 4
    Count = 4 < 5 → not enough. low = mid + 1 = 4

Iteration 3
	•	low = 4, high = 5
	•	mid = (4 + 5) / 2 = 4
    Row         Elements ≤ 5            Count
    [1, 3, 5]   1, 3                    2
    [2, 6, 9]   2                       1
    [3, 6, 9]   3                       1

    Total = 4
    Still not enough, low = mid + 1. Low and high cross over, low = 5 is our answer

At every step, we ask:
“Is the number of elements ≤ this mid enough to put it at the middle?”
We binary search until we find the smallest number that satisfies the median condition.
 */


class Solution {
    public int matrixMedian(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;

        // IMP NOTE: low is initialized to MAX and high is initialized to MIN. REMEMBER THIS!
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;

        //Find the rang of our answer space. Min in first col and max in last col since
        // all the rows are sorted
        for(int i = 0; i < r; i++) {
            low = Math.min(low, grid[i][0]);
            high = Math.max(high, grid[i][c-1]);
        }

        // Get the desired number of elements that are less than (or more than) the medium
        int required = (r * c ) / 2;

        // Run the Binary Search
        while(low <= high) {
            int mid = low + (high - low) / 2;

            int count = getSmallCount(grid, r, c, mid);
            if(count <= required) {  //We need to go higher and need more elements
                low = mid + 1;
            } else {
                high = mid - 1;
            }

        }

        return low;
    }

    public int getSmallCount(int[][] grid, int r, int c, int target) {
        int count = 0;
        for(int i = 0; i < r; i++) {
            count += upperbound(grid[i], target);
        }

        return count;
    }

    public int upperbound(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int ans = arr.length;

        while(low <= high) {
            int mid = low + (high - low) / 2;

            //Maybe the ans
            if(arr[mid] > target) {
                ans = mid;
                high = mid - 1;     // Look for a smaller index that fits the bill
            } else {
                low = mid + 1;      // We need a bigger index
            }
        }

        return ans;
    }
}