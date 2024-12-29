class Solution {

    private Map<Integer, List<Integer>> freqMap;
    private Random random;

    public Solution(int[] nums) {
        freqMap = new HashMap<>();
        random = new Random();

        for(int i = 0; i < nums.length; i++) {
            freqMap.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
    }
    
    public int pick(int target) {
        List<Integer> indices = freqMap.get(target);
        return indices.get(random.nextInt(indices.size()));
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */