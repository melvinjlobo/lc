/**
    https://takeuforward.org/data-structure/combination-sum-1/
 */

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        helper(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    // Recursive call helper
    private void helper(int[] candidates, int target, int index, List<Integer> currCombo, List<List<Integer>> result) {
        // Base case
        if(target == 0) {
            result.add(new ArrayList<>(currCombo));
            return;
        }
        
        if(index == candidates.length) {
            return;
        }

        // Here we don't need to run the whole loop. We run two recursions one with and one without.
        // The below is for with (interested in index)
        if(candidates[index] <= target) {
            currCombo.add(candidates[index]);
            helper(candidates, target - candidates[index], index, currCombo, result);
            currCombo.remove(currCombo.size() - 1);
        }

        // This is for without (Not interested in the index). So we move on to the next Index
        helper(candidates, target, index + 1, currCombo, result);
    }
}