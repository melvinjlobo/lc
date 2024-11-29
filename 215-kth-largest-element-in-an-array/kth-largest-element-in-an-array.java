class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((n1, n2) -> n1 - n2);

        //First put k numbers in the heap
        for (int ctr = 0; ctr < k; ctr++)
            minHeap.add(nums[ctr]);
        
        //Now add the numbers one byb one ensuring that if the number at the top of the heap is
        //less than nums[i], replace it with nums[i], so that the smaller number is always at the top
        for(int ctr = k; ctr < nums.length; ctr++) {
            System.out.println("ctr now is " + nums[ctr]);
            if (nums[ctr] > minHeap.peek() ) {
                System.out.println("Top now is " + minHeap.peek());
                minHeap.poll();
                minHeap.add(nums[ctr]);
            }
        }

        return minHeap.peek();
    }
}