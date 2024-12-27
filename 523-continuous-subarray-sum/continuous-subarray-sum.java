/**
    This is a prefix sum problem:
    Example: nums = [23, 2, 4, 6, 7], k = 6
    Index	Element	Cumulative Sum	Remainder (Cumu Sum % k)	What We Check / Do
        0	23	        23	        23 % 6 = 5 	                Store  5  in the hashmap with index 0.
        1	2	        25	        25 % 6 = 1 	                Store  1  in the hashmap with index 1.
        2	4	        29	        29 % 6 = 5 	                Remainder  5  was already seen at index 0!
    
    Key Question: Why Does the Repeated Remainder Work?
	1.	What the Remainder Means:
	•	At index 0, the cumulative sum is  23 , and  23 \% 6 = 5 .
	•	At index 2, the cumulative sum is  29 , and  29 \% 6 = 5 .
	•	Both cumulative sums leave the same remainder when divided by  6 .
	2.	Subarray Sum Between Index 1 and 2:
	•	The sum of the subarray between these indices (exclusive) is:

    Subarray Sum = cumsum[2] - cumsum[0] = 29 - 23 = 6

	•	 6  is divisible by  k = 6 .

    Imagine the cumulative sum as a “progress tracker,” and the remainders as “checkpoints.” If you pass the same checkpoint twice, it means you’ve completed a loop of size  k . Why is this so? Because with mods, we have cycles of remainders, which start repeating after k. Example, if k = 6, then the mod of all values before 6 are the numbers themselves (0, 1, 2, 3, 4, 5). Once we reach 6, it starts again for 6, 7, 8, 9, 10, 11 viz also (0, 1, 2, 3, 4, 5) and then starts again until 17. The pattern is that this repeats for multiples of 6.

In Simple Terms
	•	Same remainder means: The subarray in between is “evenly spaced” by  k  multiples.
	•	The hashmap helps track where we last saw a specific remainder, so we can identify subarrays efficiently.

Why do we add `remainderMap.put(0, -1)`?

When a subarray that is divisible by  k  starts at the very beginning of the array, the cumulative sum up to a certain index itself must be divisible by  k . For this to work correctly:
	1.	The remainder of the cumulative sum modulo  k  must be  0 .
	2.	We need a way to detect this scenario without requiring subtraction of any earlier cumulative sums.

By pre-setting the remainder  0  in the hashmap with an index of  -1 , we treat this as a “virtual prefix” for subarrays starting from index  0 .

Why -1?

The value  -1  in the hashmap represents an index before the start of the array. This allows us to calculate the subarray length properly when the subarray starts at index  0 .

Example: nums = [6, 1, 2], k = 6

Step-by-Step Execution:
	1.	Initialization:
	•	remainderMap = {0: -1}
	•	This means the remainder  0  is “pre-associated” with an imaginary index  -1 .
	2.	Index 0 (nums[0] = 6):
	•	Cumulative sum:  6 
	•	Remainder:  6 \% 6 = 0 
	•	Remainder  0  is found in remainderMap at index  -1 .
	•	Subarray length:  0 - (-1) = 1  (valid because subarray length ≥ 2 is not required here).
	•	Subarray: [6]
Output is true because this subarray is divisible by  k .

What Happens Without This Line?

If we don’t add  \text{remainderMap.put(0, -1)} , the program won’t correctly detect subarrays that start at index  0 , because there’s no earlier cumulative sum to subtract from. As a result, valid solutions may be missed.
 */


class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> remainderMap = new HashMap<>();

        remainderMap.put(0, -1);    // Check above explanation of why we do this...
        int prefixSum = 0;

        for(int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int remainder = prefixSum % k;

            if(remainderMap.containsKey(remainder)) {
                if(i - remainderMap.get(remainder) >= 2)
                    return true;

            } else {
                remainderMap.put(remainder, i);
            }
        }

        return false;
    }
}