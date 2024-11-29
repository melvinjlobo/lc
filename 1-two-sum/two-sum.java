class Solution {
    public int[] twoSum(int[] nums, int target) {
        int maxSum = Integer.MIN_VALUE;
        int[] result = new int[2];
        // for(int i = 0; i < nums.length; i++) {
        //     for(int j = i+1; j< nums.length;j++) {
        //         if (nums[i] + nums[j] == target) {
        //             result[0] = i;
        //             result[1] = j;
        //             break;
        //         }

        //     }
        // }
        // return result;

        Map<Integer, Integer> lookup = new HashMap<>();
        for(int ctr = 0; ctr < nums.length; ctr++) {
            if(lookup.containsKey(nums[ctr])) {
                result[0] = lookup.get(nums[ctr]);
                result[1] = ctr;
                break;
            }
            lookup.put(target - nums[ctr], ctr);
        }
        return result;
    }
}