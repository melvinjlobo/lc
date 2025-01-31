class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        // Sort the array first
        Arrays.sort(nums);

        // Loop for 4 sum
        for (int i = 0; i < nums.length - 3; i++) {

            // Skip duplicates
            if(i > 0 && nums[i] == nums[i - 1]) 
                continue;
            
            // Loop for 3 sum
            for(int j = i + 1; j < nums.length - 2; j++) {
                
                // Skip duplicates
                if(j > i + 1 && nums[j] == nums[j - 1]) 
                    continue;
                
                int left = j + 1, right = nums.length - 1;

                while(left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if(sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;

                        //Skip duplicates
                        while(left < right && nums[left] == nums[left - 1]) 
                            left++;

                        while(left < right && nums[right] == nums[right + 1])
                            right--;
                    } else if(sum < target)
                        left++;
                    else
                        right--;

                }
            }

        }

        return result;
    }
}