/*

1. As shown in Example 2, we may need to delete the head node. So we create a 
dummy node to keep track of the head of the list.

2. To avoid an extra pre node, which spends us much effort to track it, we use
fast.next.val == fast.val to indicate duplicate nodes. And the trick is, when we
exit the inner while loop, fast points to the last of continuous duplicate nodes.
And slow.next point to the first of a series of nodes, but this is temporary. When
The current slow.next may still be duplicate. We move the slow pointer until 
slow.next = fast. slow.next is the start of duplicate nodes and fast is the end of 
duplicate nodes. When they are the same, the node is the only node with this value.

*/

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = head;
        while (fast != null) {
            while (fast.next != null && fast.next.val == fast.val) {
                fast = fast.next;
            }
            if (slow.next != fast) {
                slow.next = fast.next;
            } else {
                slow = slow.next;
            }
            fast = fast.next;
        }
        
        return dummy.next;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/