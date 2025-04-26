/**
    Logic: This is a Binary Search (BS) over the answer space. let's try to find the bounds of the answer. Important note here is that once we divide an element, we round it up to the ceiling. For example, 5/10 = 0.5 -> Ceil(0.5) = 1. The lowest bound is obviously 1. This is because we cannot divide by zero and the question does not have negative input. For the max, let's take a random number with the example nums = [1,2,5,9]. let's take 1000 say for example. As per the problem, we need to sume every element after we divide by the divisor (1000, which we picked randomly). 
 Sum = Ceil(1/1000) + Ceil(2/1000) + Ceil(5/1000) + Ceil(9/1000) = Ceil(0.001) + Ceil(0.002) + Ceil(0.005) + Ceil(0.009) = 1 + 1 + 1 + 1 = 4.

 Now, let, say we try 10000, we get the same ans. Why? All numbers above 9 will result in all divisions being below 1 and above 0. This means that when we take the ceiling of all the numbers, they will always be 1. Hence the sum is always 4. The interesting change comes when the number is below 9. Let's take 5
  Sum = Ceil(1/5) + Ceil(2/5) + Ceil(5/5) + Ceil(9/5) = Ceil(0.2) + Ceil(0.4) + Ceil(1) + Ceil(1.8) = 1 + 1 + 1 + 2 = 5.

So, thie tells us that the max upper bound has to be the max number in the array (In the above example 9).

Now that we have our lower bound and upper bound, we can run a loop for 1 to Max in array and then for each element, we find the sum. if the sum is < threashold, we have our answer. THe complexity here is O(n) though. This means that we can actually run a BS over the ans array with low = 1, high = max in array.
- If we find an ans with mid <= threshold, we note it as the possible ans and go lower. Why? Since the question has asked us to calculate the smallest divisor.
- If we find the ans with mid > threshold, we need to go higher in our answer space so that a division with a higher number will reduce the sum and there is a possibility of it being <= threshold.

 */

class Solution {

    // Find the sum as per the problem for a given divisor
    public long sumWithDivisor(int[] nums, int divisor) {
        long sum = 0;
        for(int n: nums) {
            sum += (int)Math.ceil((double)n / (double)divisor);
        }

        return sum;
    }

    public int smallestDivisor(int[] nums, int threshold) {

        int maxInRange = Integer.MIN_VALUE;

        for(int n: nums) {
            maxInRange = Math.max(n, maxInRange);
        }

        int low = 1, high = maxInRange;
        int ans = 0;

        while(low <= high) {
            int mid = low + (high - low) / 2;
            long currentValue = sumWithDivisor(nums, mid);

            if(currentValue <= threshold) { // We have our number which is lower than the threshold. Can we go higher, closer to the threshold? This means that we need to lower our divisor so that the sum is higher        
                high = mid - 1;
                ans = mid;
            } else {
                low = mid + 1;
            }

        }
        return ans;
    }
}