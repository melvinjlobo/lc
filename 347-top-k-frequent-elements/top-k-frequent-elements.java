class Solution {
    public int[] topKFrequent(int[] nums, int k) {


        Map<Integer, Integer> freqMap = new HashMap<>();
        PriorityQueue<Integer> topK = new PriorityQueue<>((p1, p2) -> Integer.compare(freqMap.get(p1), freqMap.get(p2)));


        //Get the frequency
        for(int i = 0; i < nums.length; i++) {
            int val = nums[i];
            freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);
        }

        for(int c : freqMap.keySet()) {
            topK.add(c);
            if(topK.size() > k)
                topK.poll();
        }


        int[] result = new int[k];

        for(int i = k-1; i >=0;i--) {
            result[i] = topK.poll();
        }
        
        return result;
    }
}