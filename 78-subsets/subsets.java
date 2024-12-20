/**
There are two ways we can solve this problem (ignoring the iterative approach)
1. Backtracking with recursion
2. Bit manipulation

1. Note for backtracking. We don't have to check with the size of nums in the base case as the for loop will run a fixed number of times. Also
when index is == num.length, the for loop does not run and the recursive function just returns

2. Bit manipulation: 
    - We run from zero to 2^n-1. Why? To generate power set of 3 bits we do:
    000, 001, 010, 100, 101, 110, 111 i.e 7 times i.e. 8-1 i.e 2^3-1 . So, for n bits, we run it 2^n-1
    - We use bit operator `&` with a 1  bit (which we keep moving), per bit position. If we get a 1 as the result of an and, we use that bit position index and get the number from nums for that same index and append to the answer
 */

class Solution {
    public List<List<Integer>> subsetsRecurse(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }

    public void helper(int[] nums, int startIndex, List<Integer> partialList, List<List<Integer>> result) {
        result.add(new ArrayList(partialList));

        for(int i = startIndex; i < nums.length; i++) {
            partialList.add(nums[i]);
            helper(nums, i + 1, partialList, result);
            partialList.remove(partialList.size() - 1);
        }

    }

    /**
    Bit Manipulation.
    Advantage: No recustion stack, so no extra space
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length; //no. of bits
        //For 2^n - 1
        for(int i = 0; i < Math.pow(2, n); i++) {       //Mapth.pow(2, n) can also be written as 1 << n since `<<` is equivalent to multiplying by 2, and we do it , `n` times viz 2^n 
            List<Integer> subSet = new ArrayList<>();
            //Run over `n` bits to check which bit positions have ones and ad them to the subset. This is one row of the power set (001, 010, etc.)
            for(int j = 0; j < n; j ++) {
                if((i & (1 << j)) > 0)
                    subSet.add(nums[j]);
            }
            result.add(subSet);
        }

        return result;
    }
}
