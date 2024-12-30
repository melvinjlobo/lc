/**
Approach 1: Preprocessing with a Map (Efficient for Frequent Calls)
	1.	Preprocess the Array:
	•	Create a map where the keys are the unique elements of  \text{nums} , and the values are lists of their indices.
	•	For example:
            nums = [1, 2, 3, 3, 3]
            Preprocessed map: {1: [0], 2: [1], 3: [2, 3, 4]}
    2.	Randomly Pick an Index:
	•	When pick(target) is called, fetch the list of indices for  \text{target}  and randomly select one using Random.
    Time Complexity: O(n)


 */


class Solution {

    //Solution 1
    // private Map<Integer, List<Integer>> freqMap;
    private Random random;

    //Solution 2
    private int[] nums;

    public Solution(int[] nums) {
       
        random = new Random();

        //Solution 1
        // freqMap = new HashMap<>();

        // for(int i = 0; i < nums.length; i++) {
        //     freqMap.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        // }

        //Solution 2
        this.nums = nums;
    }
    
    public int pick(int target) {
        //Solution 1
        // List<Integer> indices = freqMap.get(target);
        // return indices.get(random.nextInt(indices.size()));

        // Solution 2
        //Count to keep track of how many times we see the target so that we can adjust the pick probability accordingly
        int count = 0, result = -1; //result is out of bounds of the array

        for(int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if(n == target) {
                count++;        // Increment target count

                if(random.nextInt(count) == 0)
                    result = i;
            }

            
        }

        return result;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */