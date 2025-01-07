/**
Why skip duplicates?
Key idea:
“I’ve already used this number at this level of decision-making. If I use it again here, it will lead to duplicate combinations. But if I use it at a lower level, that’s okay because it’s part of a different sequence.”

Why Skipping at the Same Level Works

The input array is sorted, which ensures that all duplicates are grouped together. When we encounter a duplicate, we know:
	1.	We’ve already processed this number at the current level.
	2.	If we include it again, it won’t lead to a new unique combination.

We don’t skip duplicates in deeper recursion because:
	•	Each recursive call explores a new “path” where the duplicate might be meaningful.

  What About Missing a Needed Duplicate?

You might wonder:

	“What if skipping the duplicate causes us to miss a valid combination?”

The answer is no, because:
	1.	The first occurrence of the number has already explored all valid paths.
By the time we consider skipping the duplicate, the combination that includes it has already been formed using the earlier occurrence.
	2.	Duplicates are only skipped at the same level.
In deeper recursion levels, duplicates are still considered because they belong to a new “path” or “branch” in the decision tree.

Simplified Analogy

Imagine you’re solving a puzzle where you need to form numbers using colored blocks. Each block has a number, and there are duplicates:
	•	Box 1: [1, 1, 2]

If you pick the first 1 to start building, you don’t need to start again with the second 1 at the same decision level because it will lead to the same result.

However, if you’re adding more blocks (deeper recursion), the duplicate 1 becomes meaningful again because now you’re combining it with other numbers in a new way.

Key Takeaway
	•	Skipping duplicates at the same recursion level avoids duplicate combinations because the first occurrence of a number already explores all its possibilities.
	•	Skipping duplicates doesn’t miss valid combinations because deeper recursion levels will still consider duplicates if they’re meaningful.
 */

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        helper(candidates, target, 0, result, new ArrayList<>());
        return result;
    }

    private void helper(int[] candidates, int target, int index, List<List<Integer>> result, List<Integer> partialList) {

        // Base case
        // if(index == candidates.length)
        //     return;
        
        if(target <= 0) {
            result.add(new ArrayList(partialList));
        }

        for(int i = index; i < candidates.length; i++) {
            if(i > index && candidates[i] == candidates[i-1])
                continue;

            // Since the array is sorted, we can use this optimzation that if a number is greater than the target,
            // the rest of the elements are bound to be larger than the target and we don't need the rest of the recursions anymore...
            if(candidates[i] > target)
                return;
            
            partialList.add(candidates[i]);
            helper(candidates, target - candidates[i], i + 1, result, partialList);
            partialList.remove(partialList.size() - 1);
            
        }
    }
}