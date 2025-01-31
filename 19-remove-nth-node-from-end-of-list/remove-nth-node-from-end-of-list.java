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

        ListNode fast = head, slow = head;

        // Move fast ahead by `n` steps
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // If fast reaches null, that means we need to remove the head node
        if (fast == null) {
            return head.next; // Skip the head
        }

        // Move fast and slow together until fast reaches the last node
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Remove the nth node
        slow.next = slow.next.next;

        return head;

    }
}