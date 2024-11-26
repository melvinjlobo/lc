/** 
https://leetcode.com/problems/random-pick-with-weight/solutions/5955471/prefix-binary-search-with-example
*/



class Solution {

    ArrayList<Integer> runningSums = new ArrayList<>();
    int totalSum = 0;

    public Solution(int[] w) {
       // ArrayList<Integer> runningSums = new ArrayList<>();
       // int totalSum = 0;
        int runningSum = 0;
        for(int weight: w) {
            runningSum += weight;
            runningSums.add(runningSum);
        }

        this.totalSum = runningSum;
    }
    
    public int pickIndex() {
        Random r = new Random();
        int target = r.nextInt(this.totalSum) + 1;

        int start = 0, end = runningSums.size();

        while (start < end) {
            int mid = start + (end - start) / 2;
            if(target > runningSums.get(mid))
                start = mid + 1;
            else
                end = mid;
        }

        return start;
        
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */