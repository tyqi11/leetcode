/*

1. Create two dummy nodes to keep track of the heads of two parts.

2. Set the tail of the second part to null after exiting the loop.

3. The tail of the first part should connect to the node after the second dummy.

*/

class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        ListNode less = dummyHead;
        ListNode dummyHalf = new ListNode(0);
        ListNode greater = dummyHalf;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                less.next = cur;
                less = less.next;
            } else {
                greater.next = cur;
                greater = greater.next;
            }
            cur = cur.next;
        }
        greater.next = null;
        less.next = dummyHalf.next;
        return dummyHead.next;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/