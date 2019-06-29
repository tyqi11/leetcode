/*

1. If m = 1, the head node is changes, so we need a dummy head.

2. We need m - 1 steps to move to the target dummy head for reversing. 

*/

class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        int count = 0;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (count < m - 1) {
            pre = pre.next;
            count++;
        }
        ListNode cur = pre.next;
        for (int i = m; i < n; i++) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;            
        }
        return dummy.next;
    }
}

/*
Time complexity: O(n - m)
Space complexity: O(1)
*/