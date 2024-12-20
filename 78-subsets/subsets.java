class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }

    public void helper(int[] nums, int index, List<Integer> partialList, List<List<Integer>> result) {
        result.add(new ArrayList(partialList));

        for(int i = index; i < nums.length; i++) {
            partialList.add(nums[i]);
            helper(nums, i + 1, partialList, result);
            partialList.remove(partialList.size() - 1);
        }

    }
}