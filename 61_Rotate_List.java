/*

1. It takes three steps to solve this problem, as shown in the code comment.
In step 1, set cur.next != null as the condition to get the last valid node. 
(also count start at 1.)

Summary to count the length of a list:
1. cur = head, count = 0, cur != null
2. cur = head, count = 1, cur.next != null
3. cur = dummy, count = 0, cur.next != null

*/

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        // 1. get length, and tail node
        ListNode cur = head;
        int count = 1;
        while (cur.next != null) {
            cur = cur.next;
            count++;
        }
        cur.next = head; // tail connect to head
        k %= count; // in case k > count
        // 2. get last node of the first part
        cur = head;
        int tail = 0;
        while (tail < count - k - 1) {
            cur = cur.next;
            tail++;
        }
        // 3. break two parts and set null
        ListNode h2 = cur.next;
        cur.next = null;
        return h2;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/