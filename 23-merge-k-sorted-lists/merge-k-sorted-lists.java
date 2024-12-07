/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

 /**
    Solution 1: Using priorityQueue O(kN)
        0. Create a result head and dummy and point the head to the dummy
        1. Create a priority Queue MinHeap of size K
        2. Put the head of each node in the heap
        3. Pick the top of the minHeap and assign the next of the head of the new list to this
        4. Add the next node of the listnode that we picked from the heap and add it to the heap
        5. Repeat 3. and 4. 
        6. Once we exhaust all ListNodes, we are done

        Complexity:
        Time complexity : O(kN) where k is the number of linked lists.
        Almost every selection of node in final linked costs O(k) (k-1 times comparison).
        There are N nodes in the final linked list.
    
    Solution 2: Using Merge sort (N Log k)
        1. Divide the lists using mid (binary search way or DFS). Do this until we have two lists 
        2. Merge two lists using the merge list problem solution for two lists

        Complexity:
        Some people are having difficulty understanding why the last method is Nlog(k). I've seen some pretty 
        good responses, but I think I can maybe help explain it another way.

        The mistake in some peoples' logic is that you are doing k/2 work then k/4 work then k/8 work ... and so 
        on. This isn't entirely incorrect, but it obscures the true answer.

        Consider the problem in levels. For this example, let's also assume we have 8 lists. We can assume the 
        length of each list is about N/8. Sure in reality each list might have more or less than N/8 nodes, but 
        it doesn't really matter.

        Level 1
        On the first level, we consider all 8 of our lists. We pair each list up with another list, so we have 4 
        pairings. Now, how long does it take to merge a pair? Well it takes O(len(list_1) + len(list_2), but 
        since we defined each list to be about N/8 nodes, we can say that it takes O(N/4) to merge one pair. How 
        many pairs do we have? 4! This means that in total we are doing an O(N/4) operation 4 times - this is 
        just 4 x O(N/4) = O(N).

        In summary:

        Level 1	
        Lists	8
        Pairs	4
        Work per Pair	O(N/4)
        Total Work	O(N)
        Level 2
        For level 2, we take our output from Level 1 as our input. That is, we now have 4 lists and we can pair 
        
        those up into 2 pairs total. To merge a pair, it takes O(len(list_1) + len(list_2), but since each list 
        is now N/4 in length, our time complexity becomes O(N/2) for one merge. Since we have 2 pairs to merge, 
        we again have 2 x O(N/2) = O(N). Interesting right? You might have thought that at the second level we 
        were gonna do more work, but we do the exact same amount of work as the first level. If we construct a \
        similar table from above, it would look like:

        Level 2	
        Lists	4
        Pairs	2
        Work per Pair	O(N/2)
        Total Work	O(N)
        Level 3
        Okay, now we take again our output from level two as our input for Level 3. That is, we have 2 lists 
        which we need to merge. Now using the same logic as before, we can say that it takes O(N/2 + N/2) = O(N) 
        to merge one pair. Since we only have two lists, we only need to merge one pair, and for that reason, 
        the total work is again O(N). Table below for simplicity:

        Level 3	
        Lists	2
        Pairs	1
        Work per Pair	O(N)
        Total Work	O(N)
        Where Does log(k) Come From?
        Okay, so clearly we're doing O(N) work every level, but how does this relate to k and where do logs come 
        in? Well let me ask you this - How many levels are there? We're doing (# of Levels) x O(N) work, so all 
        we need to find out is the number of levels.

        We have to keep creating new levels until we've gotten to the point where we're just merging two lists 
        and we're done. Every level has half the number of lists from the previous level. So if we have 4 lists 
        to start, it goes 4 -> 2 -> 1. If we have 8 it goes 8 -> 4 ->2 ->1. Since we split the input in half for 
        every level, that means we have at most log2() levels. Since we start with k lists, we have log2(k) 
        levels, since each level is O(N) work - we have, in total: Nlog(k) work

  */
class Solution {

    public ListNode mergeKListsPQ(ListNode[] lists) {
        ListNode dummy = new ListNode(-1);
        ListNode pointer = dummy;

        //Edge case
        if(lists.length == 0)
            return null;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((l1, l2) -> l1.val - l2.val);
        

        // Add all the heads
        for(ListNode node: lists) {
            if(node != null)
                minHeap.offer(node);
        }

        //Pick the min value node, point the result pointer to it and put it's next into the Queue
        while(!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            pointer.next = node;
            pointer = node;
            
            if(node.next != null)
                minHeap.offer(node.next);    
        }

        return dummy.next;
    }


    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0 )
            return null;
        
        return mergeKListsRecurse(lists, 0, lists.length - 1);
    }

    /**
        This function keeps breaking the lists at mid and making them smaller using DFS with post order
        until we hit the point where there is only one list: base case.
        The idea is to form pairs and then user merge two lists to keep joining
     */
    public ListNode mergeKListsRecurse(ListNode[] lists, int start, int end) {
        //Base case: If there is only one list, return it
        if (start == end)
            return lists[start];        //or lists[end]

        // Find the mid
        int mid = start + (end - start) / 2;

        ListNode leftList = mergeKListsRecurse(lists, start, mid);
        ListNode rightList = mergeKListsRecurse(lists, mid + 1, end);

        ListNode mergedList = mergeTwoLists(leftList, rightList);

        return mergedList;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        while(l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }

            curr = curr.next;
        }

        curr.next = (l1 != null)  ? l1 : l2;
       
        return dummy.next;
    }

}