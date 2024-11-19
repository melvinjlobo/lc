/**
Here we have two approaches:
Approach 1: Priority Queue.. Well known. SOlution below in topkFrequentPQ
Approach 2 : Bucket sort
Bucket Sort is a sorting algorithm that groups elements into buckets (or "bins") based on some property, like frequency. Then, the algorithm processes the buckets to get the sorted result.

For Top K Frequent Elements, the "buckets" are lists of elements grouped by their frequencies. This allows us to efficiently identify the most frequent elements.

Steps of Bucket Sort for This Problem
Example Input:
nums = [1, 1, 1, 2, 2, 3], k = 2

1. Count the Frequencies
        First, use a HashMap to count how often each number appears in the array.

        Input: nums = [1, 1, 1, 2, 2, 3]
        Frequency map:
        {1: 3, 2: 2, 3: 1}
        This tells us:
        1 appears 3 times.
        2 appears 2 times.
        3 appears 1 time.
2. Step 2: Create Buckets
        Next, we create an array of buckets, where:

        Bucket index represents frequency.
        Bucket content is a list of numbers with that frequency.
        Since the maximum frequency of any element is at most the size of nums (n), the array has n + 1 buckets.

        Initialize Buckets:
        Create an array of lists:
        buckets = [[], [], [], [], [], [], []]  // (size = nums.length + 1 = 7)

        Fill Buckets:
            Place each number into the bucket corresponding to its frequency.
            1 has frequency 3 → goes into buckets[3].
            2 has frequency 2 → goes into buckets[2].
            3 has frequency 1 → goes into buckets[1].
            Buckets After Filling:
            buckets = [[], [3], [2], [1], [], [], []]

            buckets[1] = [3]: Numbers that appear 1 time.
            buckets[2] = [2]: Numbers that appear 2 times.
            buckets[3] = [1]: Numbers that appear 3 times.
3. Collect Top K Frequent Elements
                To find the top k frequent elements, start from the highest frequency bucket and move backward. Add elements from each 
                bucket to the result until we have k elements.

                Start at the end of buckets (highest frequency):
                buckets[3] = [1]: Add 1 to the result.
                Move to the next bucket:
                buckets[2] = [2]: Add 2 to the result.
                Stop when we have k = 2 elements.
                Final Result:
                [1, 2]
Visualization :

                Step 1: Count frequencies
                nums = [1, 1, 1, 2, 2, 3]
                freqMap = {1: 3, 2: 2, 3: 1}

                Step 2: Fill buckets (index = frequency)
                buckets:
                Index 0: []
                Index 1: [3]
                Index 2: [2]
                Index 3: [1]
                Index 4: []
                Index 5: []
                Index 6: []

                Step 3: Collect top k = 2 elements
                Start from highest frequency:
                    Index 3: Add [1]
                    Index 2: Add [2]
                Result = [1, 2]

Why is Bucket Sort Efficient Here?
        Buckets Group by Frequency:
        Instead of sorting all elements, we use the bucket index to organize them directly.

        Direct Access to Top Frequencies:
        We only need the most frequent elements, so we start from the highest bucket and stop when we have enough (k).

        No Extra Sorting:
        Since the bucket indices (frequencies) are already sorted in ascending order, we simply process them in reverse.
        Linear Time: Counting frequencies and filling buckets both take O(n).
        No Sorting Overhead: Sorting elements explicitly (e.g., using a heap or quicksort) isn’t required.

IMP NOTE:
Instead if List<List<Integer>> for bucket sort, we use an array of Lists: `List<Integer>[]`
1. What is List<Integer>[]?
List<Integer>[] is an array where:
Each slot of the array contains a List<Integer>.
The type of data stored in each list is Integer.
For example:
    List<Integer>[] buckets = new List[5];

Here:
buckets is an array with 5 slots (indices: 0, 1, 2, 3, 4).
Each slot can hold a List<Integer>.

2. Why Use List<Integer>[] Instead of Just a List<List<Integer>>?
While you can use List<List<Integer>>, an array (List<Integer>[]) is:

    More Efficient:
        Arrays in Java have a fixed size and are faster for indexing operations compared to lists.
        When the size is predetermined (like in bucket sort), an array is a better fit.

    Clearer for Indexed Access:
        In bucket sort, the index of the array directly corresponds to the frequency of elements.
        buckets[frequency] gives you the list of numbers with that frequency.

Key Points About List<Integer>[]
    Array of Lists:
        Combines the strengths of arrays (fixed size, fast access) and lists (dynamic grouping of elements).
    Initialization:
        The array itself is initialized with new List[size].
        Each list inside the array must be explicitly initialized (e.g., with new ArrayList<>()).
    Usage:
        Use the array index for direct access.
        Use the list inside each bucket to group integers dynamically.

 */

class Solution {
    
     public int[] topKFrequent(int[] nums, int k) { 
        Map<Integer, Integer> freqMap = new HashMap<>();

        //1. Get the frequency
        for(int i = 0; i < nums.length; i++) {
            int val = nums[i];
            freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);
        }

        //2. Create the Bucket List and fill the buckets
        // The maximum frequency any element can have is nums.length
        // E.g. if the array is [1, 1, 1, 1, 1, 1], the freq of 1 is 6 viz nums.length
        // Since it is inclusive, the buckets is nums.length + 1
        // THe drawback of using List of integers array is that we have to init every element since it would be 
        //a null otherwise
        List<Integer>[] bucketList = new List[nums.length + 1];
        for(int i = 0; i < nums.length + 1; i++) {
            bucketList[i] = new ArrayList<Integer>();
        }

        // Add the elements to the bucket list
        for(Map.Entry<Integer, Integer> entry: freqMap.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();
            bucketList[freq].add(num);
        }

        //3. Get the topK elements from the bucket list in the reverse order
        int[] result = new int[k];
        int pos = 0;
        for(int i = bucketList.length - 1; i >=0; i--) {
            //Note: Since the bucket is sparse (all values may not be filled as only certain numbers are present)
            // we have to check if the associated arrayList with an index is empty and only pick those as 
            //candidates for the result
            if(!bucketList[i].isEmpty()) {
                for (int j = 0; j < bucketList[i].size() && pos < k; j++) {
                    result[pos] = bucketList[i].get(j);
                    pos++;
                }
            }
        }

        return result;

     }
    
    
    public int[] topKFrequentPQ(int[] nums, int k) {


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