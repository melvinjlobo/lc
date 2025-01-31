class Solution {
    

    public int threeSumClosest(int[] nums, int target) {
        int smallestDiff = Integer.MAX_VALUE;
        //Sort the array
        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 2; i++) {

            int left = i + 1;
            int right = nums.length - 1;

            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int diff = target - sum;

                if(diff == 0)
                    return sum;

                //NOTE: Math.min does not work since the final vlue to be assigned to `smallestDiff` is `diff` and not `Math.abs(diff)`
                //which the below statement will do....
                //smallestDiff = Math.min(Math.abs(smallestDiff), Math.abs(diff));
                if(Math.abs(diff) < Math.abs(smallestDiff))
                    smallestDiff = diff;

                if(sum < target)
                    left++;
                else
                    right--;
            }
        }

        return target - smallestDiff;       // We do this since we already have the target and the difference between the sum and the target. By subtracting, we get the sum. i.e., we have `sum + smallestDiff = target`. Therefore, `sum = target - smallestDiff`
    }
}