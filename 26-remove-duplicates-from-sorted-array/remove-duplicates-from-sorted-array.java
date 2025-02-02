/**
    This problem is similar to the dutch flag problem. The idea is to have one fixed `replacement` pointer
    and then the other one just iterates through the array. When we encounter a number that is not a duplicate 
    of it's adjacent value, we swap it with our `replacement` position (aka Fast pointer - Slow pointer)
 */
class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for(int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;       // + 1 since we need to return number of elements vs zero based index
    }
}