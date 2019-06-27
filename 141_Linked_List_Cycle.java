/*

1. The most important to remember after solving this problem: 1) if after k steps,
slow == fast, then 2k - k = m, which is the perimeter of the cycle, 2) slow is 
m steps away from head. The second is helpful to solve Linked List Cycle II.

*/

public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}

/*
Time complexity: O(m), m is the perimeter（周长）of the cycle
              or O(n), n is the length of the list
              before slow == fast, they go k steps, 2k - k = m
Space complexity: O(1)
*/