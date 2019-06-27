/*

1. Be sure to check why we leave the first while loop after the loop. If it is 
because of the break, we can continue to find the meeting node. If not, it means
there is no cycle in the given linked list.

*/

public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null; 
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}

/*
Time complexity: O(m), from O(2m), m is the perimeter of the cycle
              or O(n), if there is no cycle
Space complexity: O(1) 
*/