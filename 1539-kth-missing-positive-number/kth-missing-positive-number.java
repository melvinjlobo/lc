/**

Kth Missing number brute force
arr = [2, 3, 4, 7, 11], K =5
Ans = 9
Why?
- If we consider ALL the elements, then ideally the sequence will be:
1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
|  |  |  |  |  |  |  |  |  |   |
M  NM NM NM M  M  NM M  M  M   NM

where:
M - Mising from array
NM - Not missing from array

So, mising elements are:
1,   5,   6, 8,  9,  10
^    ^    ^  ^   ^    ^
1st 2nd 3rd 4th 5th  6th

So, 9 is the ans since it's the 5th missing no (k = 5).

For Brute force we just imagine that we move in the sequence in the array right up to the last element. 
One way to do this is run a counter from 1 to 11. Then for each number check if it's in the array and increment the counter if the number is not int he array.
When the counter hits K, we have found the missing number. Something like:
int missing = 0;
while(missing < maxNumInArray) {
  if(missing == k)
    break;
  
 if(i in array) {
  continue;
 } else {
  missing++;
 }
}
return missing;

This is highly inefficient though since we have to search the whole array to find every number.

Another way to do this is fast pointer slow pointer. Start the fast pointer from K and the slow pointer from the start of nums. We will increment k if the slow pointer 
has not caught up to k. Since the slow pointer skips the missing numbers in the array, it will catch up in "missing number" steps to k.
condition: if (nums[i]) < k -> k++;

1. nums[0] = 2, condition: 2 < 5 is true, k = 6 (5 + 1)
2. nums[1] = 3, condition: 3 < 6 is true, k = 7 (6 + 1)
3. nums[2] = 4, condition: 4 < 7 is true, k = 8 (7 + 1)
4. nums[3] = 7, condition: 7 < 8 is true, k = 9 (8 + 1)
4. nums[4] = 11, condition: 11 < 9 is false, break. We have found missing number = 9

--------------------------------------------------------------------------------------------------------------------

Binary search
Key Observations
If arr[i] is the value in the array at index i, then the number of missing positive integers before arr[i] is:
missing = arr[i] - (i + 1)
To solve this, let's first identify all the missing positive integers step by step:

The perfect sequence of numbers starts as: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
The given array is: [2, 3, 4, 7, 11].
From this, the missing numbers are:

Before 2: 1 is missing.
Between 4 and 7: 5, 6 are missing.
Between 7 and 11: 8, 9, 10 are missing.
Missing numbers: 1, 5, 6, 8, 9, ...

The 5th missing number is 9.

Key Observation: Formula for Missing Numbers
For each index i, the number of missing integers before arr[i] is:
missing = arr[i] - (i + 1)

Step . 1

arr[i] is the actual value at index i.
(i + 1) is the value we expect if there were no missing numbers.

Subtracting the two gives the count of missing numbers.
Apply to the Array:
At i = 0: missing = arr[0] - (0 + 1) = 2 - 1 = 1
→ There is 1 missing number before 2.

At i = 1: missing = arr[1] - (1 + 1) = 3 - 2 = 1
→ There is still 1 missing number before 3.

At i = 2: missing = arr[2] - (2 + 1) = 4 - 3 = 1
→ There is still 1 missing number before 4.

At i = 3: missing = arr[3] - (3 + 1) = 7 - 4 = 3
→ Now there are 3 missing numbers before 7.

At i = 4: missing = arr[4] - (4 + 1) = 11 - 5 = 6
→ There are 6 missing numbers before 11.

Where is the 5th Missing Number?
Before 7, only 3 numbers are missing (1, 5, 6).
At 11, 6 numbers are missing (1, 5, 6, 8, 9, 10).
The 5th missing number is between 7 and 11.

Step 2: Find the Missing Number
We know the 5th missing number lies between 7 and 11.

At 7, 3 numbers are missing.
To find the 5th missing number:
Add the difference: 7 + (5 - 3) = 7 + 2 = 9.
Answer: The 5th missing number is 9.

Let’s use binary search to solve the problem programmatically.

With binary search, we don't have the actual answer space (9 is not present in the array), so we try to get the boundaries between which the missing number Will be present. We know that if the sequence did not have missing numbers, an array index i would have the number i+1. For example, in an array of natural numbers from 1 to 10, arr[6] = 7 (since the array has a zero index). Similarly, arr[3] = 4 or arr[5] = 6 and so on. With this knowledge we can derive a formula at a specific index that missing number at index i is: missing[i] = arr[i] - (i + 1). Taking the array: [2, 3, 4, 7, 11], at index 4, the missing number is arr[2] - (2 + 1) -> 4 - 3 -> 1. There is one missing number at index 2. This is true because, even without a formula, we can see that at index 2, we have a 4 instead of a 3. If 1 was not missing, the array should have been [1, 2, 3, ...]. Now that we have the missing number formula, we can use it at every mid calculation to check how many numbers are missing at the `mid` index. Then if the number of missing numbers are < k, we do  `low = mid + 1`, else `high = mid - 1` At the end of the loop, `high > low`. So `high` points to the lower boundary of the value in the array where the missing number will be and `low` points to  the higher boundary. From there the problem becomes to find the actual number. For example, in the above array : [2, 3, 4, 7, 11], we will get high = 3 (arr[3] = 7) and  low = 4 (arr[4] = 11). We know that at low, the missing numbers are: arr[3] - (3 + 1) -> 7 - 4 = 3 and the missing numbers at 4 are: arr[4] - (4 + 1) -> 11 - 5 = 6 so at `7`, we have 3 missing numbers and at `11`, we have 6. So 5th missing number has to be between 7 and 11. Since we know that 3 numbers are missing at `7`,  then we just need more = 5 - 3 = 2 more numbers or k - 3 numbers. This would mean that we add `2` to `7` and get the missing number `9`. Let's deduce it. Missing number at high is:
missing_number = nums[high] + k - (more_missing_numbers)
missing_number = nums[high] + k - (nums[high] - (high + 1)) -> where nums[high] - (high + 1) are the missing numbers at high
missing_number = nums[high] + k - nums[high] + (high + 1) -> opening brackets and changing signs
missing_number = k + (high + 1) -> high + 1 + k -> nums[high] gets cancelled
Now, high + 1 after binary search is also low (remember, high just crossed low to the left, so high + 1 would be low)
Therefore missing_number = low + k -> replacing `high + 1` with `low`

Answer: The 5th missing number is 9.
 */


class Solution {

     public int findKthPositiveBruteForce(int[] arr, int k) {
        for (int num : arr) {
            if (num <= k) {
                k++;
            } else {
                break;
            }
        }
        return k;
    }


    public int findKthPositive(int[] arr, int k) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int missing = arr[mid] - (mid + 1);

            if (missing < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low + k;
    }
}