/**
    NOTES:
    1. We need index in this case so that we can stop when it reaches 3. The only job of index is so that we can count how many items we have added to partial. Note that we can also do without index by just comparing partial size() to nums length (if partial.size() >= nums.length)
    2. We run the for loop from 0 always. This is because in permutations, we visit each number in round robin. For example, we visit 1, then if we skip 2, we go to 3 and come back to 2 (This is because the index is not yet 3 as in the below example [1, 2, 3] and we already have 1 and 2 in the array). So the only remaining number to be added is the one we skipped earlier...2


    Input: nums = [1,2,3]
    Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]       

    TC: Since it is permutations, it is N*N!. THe first N is to copy the partial array into the answer when the index >= nums.length. The second one is just recursion for N!
 */

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void helper(int[] nums, int index, List<Integer> partial, List<List<Integer>> result) {
        if(index >= nums.length) {
            result.add(new ArrayList<>(partial));
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            if(partial.contains(nums[i]))
                continue;
            partial.add(nums[i]);
            helper(nums, index + 1, partial, result);
            partial.remove(partial.size() - 1);
        }
    }
}