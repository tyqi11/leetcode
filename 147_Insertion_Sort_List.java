/*
# Insertion Sort

1. This is easy to implement but time consuming. See the solution of 148 for
a O(nlong) method: Merge Sort.

2. One thing to take care of is that we only do pre = pre.next when current node
is at a temporary good place. If cur.val < pre.val, pre.next points to a new
node, and we do not move pre but to check the new pre.next in the next round.


*/

class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = head;
        while (pre != null && pre.next != null) {
            ListNode cur = pre.next;
            if (cur.val < pre.val) {
                pre.next = cur.next;
                insert(dummy, cur);
            } else {
                pre = pre.next; // do only if cur.val > pre.val
            } 
        }
        return dummy.next;
    }
    
    private void insert(ListNode dummy, ListNode cur) {
        ListNode pre = dummy;
        while (pre.next.val < cur.val) {
            pre = pre.next;
        }
        cur.next = pre.next;
        pre.next = cur;
    }
}

/*
Time complexity: O(n^2)
Space complexity: O(1)
*/