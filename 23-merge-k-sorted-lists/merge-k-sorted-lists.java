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
    0. Create a result head and dummy and point the head to the dummy
    1. Create a priority Queue MinHeap of size K
    2. Put the head of each node in the heap
    3. Pick the top of the minHeap and assign the next of the head of the new list to this
    4. Add the next node of the listnode that we picked from the heap and add it to the heap
    5. Repeat 3. and 4. 
    6. Once we exhaust all ListNodes, we are done

  */
class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
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
}