class Solution {
    public int removeElement(int[] nums, int val) {
        int replacement = 0;
        for(int i = 0; i < nums.length;i++) {
            if(nums[i] != val) {
                nums[replacement] = nums[i];
                replacement++;      
            }
        }

        return replacement;
    }
}