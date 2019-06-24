/*

1. This is not difficult except that we need special care to the tail. If the 
last node has a value of val, fast skip it and points to null, which ends the 
loop. At this time, slow points at the last valid node, while the last valid 
node links to a list of invalid nodes. So when exit the loop, we should cut 
the tail by set slow.next = null.

*/

class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = head;
        while (fast != null) {
            if (fast.val != val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        slow.next = null; // IMP!!!
        return dummy.next;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/