/**
    Logic:
    we want to find the sum of 3 elements that add up to zero. i.e. x + y + z = 0. This means x + y = -z. So we make it a 2 sum problem with target == -z 
    Main Funtion:
       0. Sort the array
       1. Run a loop for every element from the left until length - 2 (since we want to find 3 sum). Skip if we find duplicates (when we move ahead of index 0)
       2. For every element, call the searchPair function with target `-nums[i]` here nums[i] is our `z`

    searchPair:
        1. We have left pointer. The right pointer is the end of the array.
        2. Run two pointer loop
            I. Sum == target -> If the value of two pointers add up to target (-nums[i]), add it to the result (Arrays.asList(x, y, -z)). Note that we negate target again since we switched it to negative when we called searchPair. Move left and right pointers by one step
            II. Skip duplicates. Check `if` conditions accordingly since we have moved one step ahead in 2.
            III. Sum > target -> try to reduce the sum, `right--`
            IV. Sum < target -> try to increase the sum `left++`

 */

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // First sort the array
        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 2; i++) {
                
            // Skip duplicates
            if(i > 0 && nums[i] == nums[i - 1]) 
                continue;
            
            int left = i + 1, right = nums.length - 1;

            while(left < right) {
                long sum = (long) nums[i] + nums[left] + nums[right];

                if(sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;

                    //Skip duplicates
                    while(left < right && nums[left] == nums[left - 1]) 
                        left++;

                    while(left < right && nums[right] == nums[right + 1])
                        right--;
                } else if(sum < 0)
                    left++;
                else
                    right--;
            }
              
        }
        return result;
    }

    // private void searchPair(int[] nums, int left, int target, List<List<Integer>> result) {
    //     int right = nums.length - 1;
    //     while(left < right) {
    //         int sum = nums[left] + nums[right];
    //         if(sum == target) {
    //             result.add(Arrays.asList(nums[left], nums[right], -target));
    //             left++;
    //             right--;
    //             // skip dulicates. The comparisons have to happen after we do one increment and one decrement. This is useful for the `if`
    //             while(left < right && nums[left] == nums[left - 1])
    //                 left++;
    //             while(left < right && nums[right] == nums[right + 1])
    //                 right--;

    //         } else if(sum > target) {
    //             right--;
    //         } else {
    //             left++;
    //         }
    //     }
   //}
}