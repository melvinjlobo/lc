class Solution {
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        List<List<Integer>> result = new ArrayList<>();

        // The idea is to have two values curr and prev. If the difference between them is exactly 2 (not 1), then we are 
        //missing one number. Example, 5-3 = 2; i.e, we are missing 4. For others, the range is prev+1 and curr - 1

        //Edge case
        if(nums.length == 0) {
            result.add(Arrays.asList(lower, upper));
            return result;
        }


        int prev = lower -1; //- 1 because we need to include lower as well
        int curr = nums[0];

        // IMP NOTE: the loop runs till `= nums.length`, since we have to consider the range beyond nums as well
        for(int i = 0; i <= nums.length; i++) {
            curr = (i < nums.length) ? nums[i]: upper + 1;  //upper + 1 since upper is inclusive
            if((curr - prev) > 1) {
                result.add(Arrays.asList(prev + 1, curr - 1));
            }

            prev = curr;
        }

        return result;
    }
}