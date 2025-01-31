class Solution {
    

    public int threeSumClosest(int[] nums, int target) {
        int smallestDiff = Integer.MAX_VALUE;
        //Sort the array
        Arrays.sort(nums);

        for(int i = 0; i < nums.length; i++) {

            int left = i + 1;
            int right = nums.length - 1;

            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                //System.out.println("Curr Sum is :  " + sum);
                int diff = target - sum;
                //System.out.println("Diff  for: " + nums[i] + ", " + nums[left] + ", " + nums[right] + ", is: " + diff);

                if(diff == 0)
                    return sum;
                
                //System.out.println("Smallest diff between; " + Math.abs(smallestDiff) + " and " + Math.abs(diff));
                //smallestDiff = Math.min(Math.abs(smallestDiff), Math.abs(diff));
                if(Math.abs(diff) < Math.abs(smallestDiff))
                    smallestDiff = diff;

                //System.out.println("is " + Math.abs(smallestDiff));

                if(sum < target)
                    left++;
                else
                    right--;
            }
        }

        return target - smallestDiff;
    }
}