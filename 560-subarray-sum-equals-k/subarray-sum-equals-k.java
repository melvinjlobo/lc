/**
NOTE: This does not work with a sliding window as we have negative numbers and the assumption that we can shrink the window does not hold true as the criterion for shrinking (if sum > k ) may not always work (There may be a negative number and then a positive number that adds up to the sum). So we have to use the prefix method for this...


  run a single loop and maintain an array with prefix sums. The logic is that if we want the sum of elements from a given position i to J, then similar to above then either the prefix sum should be equal to K, or if the current sum - K = K, then we have some elements from some point which add up to K
  E.g. Look at PS[5] at PS[5], we have total prefix sum of all elements until that point = 14. Now, 14 - K, which is 7 is equal to 7 which is K again. This means that there is some point or index in the array from which if we add elements, we get the sum = 7. Which is that point? It is basically "current sum - K", which is PS[5]-K = 14-7 = 7. This means that from the index of 7 in the PS array (which is 1, not included), our elements start adding up to K. So, from index 2 in the original array, until up to 5, we should have elements which add up to 7. Looking at the array slice from index 3 to 5, we get [7, 2, -3, 1] . If we add these, then it comes to 7 which is K. So the logic is:
  at each index in the loop, if the PS[index] != K, then do val = PS[index]] - K. If val is present in array, then from the last occurrence of val's index in PS (+ 1 more), until the curr sum index in original array, all elements should add up to K. This is a bit involved, so re-read. Since we use a prefix array, when we want to find the last occurrence of PS[index] - K in PS, we have to iterate all the elements until the current index that we are working on, i.e. look at all historic values in PS until now. To avoid this, we can use a Map where key = PS value and value= how many times the PS value occurred. So, the Map (based on the PS array above) will look like
  3,1
  7,1
  14,2
  16,1
  13,1
  18,1
  20,1
  Now the final algo is as follows:
  1. Create cum_sum = 0
  2. Add first element "3" to cum_sum (0) -> 3+0 = 3
  3. Check if 3 == K (7). if not, then add 3 to map with freq 1
  4. Add second element to cum_sum -> 3+4 = 7
  5. Check if 7 == 7...it is, so result_count ++
  6. Add 7 to Map with freq 1
  7. Add third element to cum_sum -> 7 + 7 = 14
  8. Check if 14 == 7. It's not.
  9. Check if 14 - K viz 14-7 = 7 is in the Map. it is, so result_count++
  9. Add 14 to Map with freq 1
  10. Add fourth element to cum_sum -> 2 + 14 = 16
  11. Check if 16 == 7. It's not
  12. Check if 16 - K viz 16-7 = 9 is in the Map. it's not, so ignore result_count
  13. Add fifth element to cum_sum -> 16 + (-3) = 13
  14 Check if 13 == 7. it's not
  15. Check if 13 - K viz 13 - 7 = 6 is in the Map. it's not , so ignore result_count
  16. Add 13 to Map with freq 1
  17 Add sixth element to cum_sum -> 13 + 1 = 14
  18. Check if 14 == 7. it's not
  19. Check if 14 - K viz 14 - 7 = 7 is in the Map, it is, so result_count++
  20. Increment 14 freq count in Map since it is already present to 2
 */

class Solution {
    public int subarraySum(int[] nums, int k) {
        // Map to store the counts of sum-k that occurred in the past
        Map<Integer, Integer> count = new HashMap<>();
        count.put(0, 1);            // This is added so that when we get prefixSum == k from index 0, we should be able to count it
        int sum = 0;
        int prefixSum = 0;
        int result = 0;
        for(int i =0; i < nums.length; i++) {
            int currNum = nums[i];
            prefixSum += currNum;
            int remainder = prefixSum - k;
            //Do we have occurrences of remainder in the map?
            if(count.containsKey(remainder)) {
                result += count.get(remainder);
            }
            count.put(prefixSum, (count.getOrDefault(prefixSum, 0) + 1));
        }

        return result;
    }
}