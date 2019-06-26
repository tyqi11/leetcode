/*

1. There are two ways to solve the problem: iteratively  and recursively.

*/

// Solution 1: Iteration
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}

// also iteration, but more like swap nodes in pairs
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur.next != null) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = dummy.next;
            dummy.next = next;
        }
        return dummy.next;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/

/****************************************************/

// Solution 2: Recursion
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/