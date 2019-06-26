/*

1. In the iteration solution, take care of the while loop conditions.

2. In the recursion solution, take care of 1) what the current level asks for,
2) what the next level provides, 3) what are the relationship between them

*/

// Solution 1: iteration
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        } // 0 or 1 node, no need to swap
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur, next;
        while (pre.next != null && pre.next.next != null) {
            cur = pre.next;
            next = cur.next;
            cur.next = next.next;
            next.next = cur;
            pre.next = next;
            pre = cur;
        }
        return dummy.next;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/

/****************************************************************/
// Solution 2: recursion
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        } // 0 or 1 node, no need to swap
        ListNode next = swapPairs(head.next.next);
        ListNode newHead = head.next;
        head.next.next = head;
        head.next = next;
        return newHead;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/