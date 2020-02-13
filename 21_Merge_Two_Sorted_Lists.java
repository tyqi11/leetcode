/*

1. This problem can be solved both iteratively and recursively. But as in most cases,
the iteration way is better, because the space complexity for recursion is the 
lengths of the two lists combined and recursion may cause stack overflow.

*/

// Solution 1: Iteration
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        ListNode p1 = l1;
        ListNode p2 = l2;
        while (p1 != null || p2 != null) {
            if (p1 != null && (p2 == null || p1.val < p2.val)) {
                cur.next = p1;
                p1 = p1.next;
            } else {
                cur.next = p2;
                p2 = p2.next;
            }
            cur = cur.next;
        }
        // this while loop can be optimized by checking ? == null separately
        // while (h1 != null || h2 != null) {
        //     if (h2 == null) {
        //         pre.next = h1;
        //         return dummy.next; // exit very early
        //     }
        //     if (h1 == null) {
        //         pre.next = h2;
        //         return dummy.next;
        //     }
        //     if (h1 != null && h1.val <= h2.val) {
        //         pre.next = h1;
        //         h1 = h1.next;
        //     } else {
        //         pre.next = h2;
        //         h2 = h2.next;
        //     }
        //     pre = pre.next;
        // }
        return dummy.next;
    }
}

/*
Time complexity: O(m + n), m and n are the lengths of l1 and l2 separately
Space complexity: O(1)
*/

/*********************************************************/
// Solution 2: recursion
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}

/*
Time complexity: O(m + n), m and n are the lengths of l1 and l2 separately
Space complexity: O(m + n)
*/
