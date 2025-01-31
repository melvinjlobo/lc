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
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        if(head == null)
            return head;

        // The reason we use dummy is because of the edge case where the head will be removed
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode front = dummy;
        ListNode behind = dummy;
        
        
        for(int i = 0; i <= n; i++)
            front = front.next;

        while(front != null) {
            front = front.next;
            behind = behind.next;
        }
        
        if(behind.next != null) {
            behind.next = behind.next.next;
        }

        return dummy.next;
    }
}