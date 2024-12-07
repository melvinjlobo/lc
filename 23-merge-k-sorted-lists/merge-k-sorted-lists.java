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
    Solution 1: Using priorityQueue
        0. Create a result head and dummy and point the head to the dummy
        1. Create a priority Queue MinHeap of size K
        2. Put the head of each node in the heap
        3. Pick the top of the minHeap and assign the next of the head of the new list to this
        4. Add the next node of the listnode that we picked from the heap and add it to the heap
        5. Repeat 3. and 4. 
        6. Once we exhaust all ListNodes, we are done
    
    Solution 2: Using Merge sort
        1. Divide the lists using mid (binary search way or DFS). Do this until we have two lists 
        2. Merge two lists using the merge list problem solution for two lists

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