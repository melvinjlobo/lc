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
    Algo:
        1. Find kth Node. This gives us the `end` of the k group
        2. Set a curr to head. 
        3. Sever the smaller k list from the larger linked list. But before that, make a note of the next node with `nextNode`
        4. Reverse the severed list
        5. Re-align heads. 
            5.a. ONLY for the first group head will be the same as curr. This is because, even though we reverse a list in a function, when we come out of the function, the position of curr itself does not change and it will point to the end of the reversed list. 
            5. b. For others:
            If there is a previous `endOfGroup`, point it to `kthNode`, which is now the new head of the reversed group
        6. Prepare for the next iteration. Update curr to the nextGroup and `endOfGroup` to the end of the current group for step 5.
 
  */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        
        ListNode curr = head;
        ListNode nextGroup = null;
        ListNode kthNode = null;
        ListNode endOfGroup = null;

        while(curr != null) {
            //Find kth node
            kthNode = findKthNode(curr, k);

            // Not enough members in group
            if(kthNode == null) {
                // If there was a previous group, link the rest of the list to it
                if(endOfGroup != null) {
                    endOfGroup.next = curr;
                }

                break;      // Exit now as we are done;
            }

            // Sever the list
            nextGroup = kthNode.next;       // Make a note of the next group first
            kthNode.next = null;

            ListNode newHead = reverseLL(curr);

            if(curr == head) { // This is the very first group
                head = kthNode;     // This is the head of the entire list
            } else {
                // Link the last group to kthNode as kthNode is now the start of the new group.
                // Don't confuse this with the start of next group which we already store with `nextGroup`
                if (endOfGroup != null)
                    endOfGroup.next = kthNode;
            }

            // Re-assign for the next iteration
            endOfGroup = curr;
            curr = nextGroup;

        }

        return head;
    }


    public ListNode reverseLL(ListNode head) {
        ListNode prev = null, curr = head, next = null;

        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }


    public ListNode findKthNode(ListNode temp, int k) {
        k -=1;      // Decrement as we already are on one of the K nodes (head of the list)
        while(temp != null && k > 0) {
            temp = temp.next;
            k--;
        }

        return temp;
    }
}