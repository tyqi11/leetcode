/*
# Two pointers

1. Make the fast pointer n steps ahead of the slow pointer, so that when fast
reaches the last node (not null), slow reaches the node before the one to be
removed.

2. [IMP] The first node can be removed (when the number of nodes equals n), so 
we need a dummy node.

3. [IMP] while (fast.next != null). If the condition is fast != null, when exit,
fast == null, and slow points to the one to be removed. We cannot go back and remove
it at that time.

4. [IMP] Even if head.next == null, head can be removed if n = 1. So head.next == null
cannot be put into corner cases.


*/

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        for (int i = 1; i <= n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/